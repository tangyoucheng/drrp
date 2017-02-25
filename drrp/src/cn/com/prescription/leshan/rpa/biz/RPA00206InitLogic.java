/*
 * Copyright(c) 2016 
 */
package cn.com.prescription.leshan.rpa.biz;

import com.barcode_coder.java_barcode.Barcode;
import com.barcode_coder.java_barcode.BarcodeFactory;
import com.barcode_coder.java_barcode.BarcodeType;

import cn.com.prescription.framework.biz.StandardBiz;
import cn.com.prescription.framework.biz.StandardLogic;
import cn.com.prescription.framework.event.StandardEvent;
import cn.com.prescription.framework.event.StandardEventResult;
import cn.com.prescription.framework.exception.ApplicationException;
import cn.com.prescription.framework.exception.SystemException;
import cn.com.prescription.framework.message.MessageUtils;
import cn.com.prescription.framework.util.FileUtils;
import cn.com.prescription.leshan.common.LeshanConstantsIF;
import cn.com.prescription.leshan.common.data.RpmPatientDao;
import cn.com.prescription.leshan.common.data.RpmStoreDao;
import cn.com.prescription.leshan.common.data.condition.RpmPatientCondition;
import cn.com.prescription.leshan.common.data.condition.RpmStoreCondition;
import cn.com.prescription.leshan.common.data.model.RpmPatientModel;
import cn.com.prescription.leshan.common.data.model.RpmStoreModel;
import cn.com.prescription.leshan.rpa.action.form.RPA00206Form;

/**
 * 打印患者信息导出初期化处理。
 * 新規作成
 * DATE: 2016.03.24 NAME: tyc
 */
public class RPA00206InitLogic extends StandardBiz implements StandardLogic {
    /**
     * 用户基本テーブル DAO
     */
    private RpmPatientDao rpmPatientDao = null;

    /**
     * 门店DAO
     */
    private RpmStoreDao rpmStoreDao = null;

    /**
     * 患者信息导出画面 初期化業務クラス的构造。
     */
    public RPA00206InitLogic() {
        super();
    }

    /**
     * event処理を行う。
     * 
     * @return event処理結果
     * @param _event
     *            event
     * @throws ApplicationException
     *             event処理里应用程序例外发生的情况
     * @throws SystemException
     *             event処理里系统例外发生的情况
     */
    public StandardEventResult service(StandardEvent _event) throws ApplicationException, SystemException {

        // event処理結果
        RPA00206Form inForm_ = (RPA00206Form) _event.getForm();
        try {

            RpmPatientCondition patientCondition_ = new RpmPatientCondition();
            // 用户ID
            patientCondition_.setUserId(inForm_.getUserId());
            // 消除标识＝ [定数：消除标识．有効レコード]
            patientCondition_.setDeleteFlag(LeshanConstantsIF.DEL_FLG_YUKO_RECORD);
            // [排他情報：用户プロファイル情報]
            RpmPatientModel patientModel_ = rpmPatientDao.select(patientCondition_);
            if (patientModel_ != null) {
                inForm_.setUserName(patientModel_.getUserName());
                inForm_.setCeelNumber(patientModel_.getCeelNumber());

                // 画像プレビュー一時保存ファイル名
                String imagePreviewFileName = inForm_.getUserId() + "_barcode.png";
                // 画像プレビュー一時保存フルパス
                String imagePreviewFullPath = LeshanConstantsIF.KYOTU_OUT_FILE_TMP_SAVE_FOLDER + "/"
                                + imagePreviewFileName;
                // 画像一時保存絶対パス
                String dstPath_ = FileUtils.getAbsolutePath(imagePreviewFullPath);
                Barcode barcode = BarcodeFactory.createBarcode(BarcodeType.Code39, inForm_.getUserId());
                FileUtils.mkdirsParent(dstPath_);
                barcode.export("png", 1, 30, true, dstPath_);
                inForm_.setBarcodeFilePath(imagePreviewFullPath);

                // // QRコードを生成する
                // QRCode qrcode = QRCode.getMinimumQRCode(
                // // QRコードに変換する文字列
                // inForm_.getUserId(),
                // // 誤り訂正レベル(30%が復元可能)
                // ErrorCorrectLevel.H);

                // qrcode.make();
                // // 画像プレビュー一時保存ファイル名
                // String qrcodePreviewFileName = inForm_.getUserId() + "_qrcode.png";
                // // 画像プレビュー一時保存フルパス
                // String qrcodePreviewFullPath = LeshanConstantsIF.KYOTU_OUT_FILE_TMP_SAVE_FOLDER + "/"
                // + qrcodePreviewFileName;
                // // 画像一時保存絶対パス
                // String dstQrcodePath_ = FileUtils.getAbsolutePath(qrcodePreviewFullPath);
                // // QRコードをJPEG形式で出力する
                // BufferedImage image = qrcode.createImage(2, 0);
                // FileUtils.mkdirsParent(dstQrcodePath_);
                // ImageIO.write(image, "png", new File(dstQrcodePath_));

                RpmStoreModel storeModel = rpmStoreDao.select(new RpmStoreCondition());
                if (storeModel != null && storeModel.getQrCode() != null) {
                    inForm_.setQrcodeFilePath(storeModel.getQrCode());
                }
            } else {
                this.addErrorMessage(MessageUtils.getMessage("E00004", "削除", "表示"));
            }

        } catch (Exception e) {
            throw new SystemException(e);
        }
        // 出力情報設定
        return this.getEventResult(inForm_);
    }

    /**
     * 用户基本テーブル DAO的设定
     * 
     * @param _rpmPatientDao 用户基本テーブル DAO
     */
    public void setRpmPatientDao(RpmPatientDao _rpmPatientDao) {
        this.rpmPatientDao = _rpmPatientDao;
    }

    /**
     * 门店DAO的设定
     * 
     * @param _rpmStoreDao 门店DAO
     */
    public void setRpmStoreDao(RpmStoreDao _rpmStoreDao) {
        this.rpmStoreDao = _rpmStoreDao;
    }

}
