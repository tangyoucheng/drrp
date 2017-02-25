/*
 * Copyright(c) 2016 
 */
package cn.com.prescription.leshan.rpm.action;

import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import com.sun.org.apache.xml.internal.security.utils.Base64;

import cn.com.prescription.framework.CodeValueRecord;
import cn.com.prescription.framework.action.AbstractAction;
import cn.com.prescription.framework.message.MessageUtils;
import cn.com.prescription.framework.util.CheckUtils;
import cn.com.prescription.framework.util.DateUtils;
import cn.com.prescription.framework.util.UnitUtils;
import cn.com.prescription.leshan.common.LeshanConstantsIF;
import cn.com.prescription.leshan.common.action.form.COMFileUploadResultDto;
import cn.com.prescription.leshan.rpm.action.form.RPM00203Form;
import cn.com.prescription.leshan.rpm.biz.RPM00203DeleteLogic;
import cn.com.prescription.leshan.rpm.biz.RPM00203InitLogic;
import cn.com.prescription.leshan.rpm.biz.RPM00203UpdateLogic;

/**
 * 用户情報設定 action。
 * 
 * @author fsb
 */
/*
 * 新規作成
 * DATE: 2012.11.07 NAME: fsb
 */
public class RPM00203Action extends AbstractAction {

    /**
     * 串行版本UID
     */
    private static final long serialVersionUID = 1L;

    /**
     * 添加用户的form
     */
    private RPM00203Form rpm00203Form = new RPM00203Form();

    /**
     * SCM0301Action 的构造。
     */
    public RPM00203Action() {
        super();
    }

    /**
     * 用户情報設定 初期表示的处理。
     * 
     * @return 返却結果
     * @throws Exception 処理実行時に系统例外发生的情况
     */
    public String doInit() throws Exception {
        // 用户情報設定eventを実行する
        doDispatchEvent(rpm00203Form, RPM00203InitLogic.class);
        return SUCCESS;
    }

    /**
     * 登録処理の相関チック的处理。
     * 
     * @return 返却結果
     * @throws Exception
     *             処理実行時に系统例外发生的情况
     */
    public void validateDoUpdate() throws Exception {

        if (CheckUtils.isEmpty(rpm00203Form.getStartDate())) {
            List<String> checkDate = new ArrayList<String>();
            checkDate.add("rpm00203Form.startDate");
            this.addFieldError(checkDate, MessageUtils.getMessage("E30001", "有效期间（开始）"));
        }
        String startDate = "";
        String endDate = "";
        if (!CheckUtils.isEmpty(rpm00203Form.getStartDate())) {
            startDate = DateUtils.format(rpm00203Form.getStartDate(),
                LeshanConstantsIF.DATE_FORMAT_YYYY_YEAR_MM_MONTH_DD_DATE, LeshanConstantsIF.DATE_FORMAT_YYYYMMDD);
        }
        if (!CheckUtils.isEmpty(rpm00203Form.getEndDate())) {
            endDate = DateUtils.format(rpm00203Form.getEndDate(),
                LeshanConstantsIF.DATE_FORMAT_YYYY_YEAR_MM_MONTH_DD_DATE, LeshanConstantsIF.DATE_FORMAT_YYYYMMDD);
        }
        if (!CheckUtils.isEmpty(startDate) && !CheckUtils.isEmpty(endDate) && startDate.compareTo(endDate) > 0) {
            List<String> checkDate = new ArrayList<String>();
            checkDate.add("rpm00203Form.startDate");
            checkDate.add("rpm00203Form.endDate");
            this.addFieldError(checkDate, MessageUtils.getMessage("E30032", "有效期间（终了）", "有效期间（开始）"));
        }

        if (CheckUtils.isEmpty(rpm00203Form.getOldIdentityImage())
                        && CheckUtils.isEmpty(rpm00203Form.getNewIdentityImage())) {
            this.addFieldError("", MessageUtils.getMessage("E30001", "证件图片"));
        }
    }

    /**
     * 用户情報設定的处理。
     * 
     * @return 返却結果
     * @throws Exception 処理実行時に系统例外发生的情况
     */
    public String doUpdate() throws Exception {
        doDispatchEvent(rpm00203Form, RPM00203UpdateLogic.class);
        return SUCCESS;
    }

    /**
     * 用户情報削除的处理。
     * 
     * @return 返却結果
     * @throws Exception 処理実行時に系统例外发生的情况
     */
    public String doDelete() throws Exception {
        doDispatchEvent(rpm00203Form, RPM00203DeleteLogic.class);
        return SUCCESS;
    }

    /**
     * 写真ファイル一時保存 写真取込的处理。
     * 
     * @return 返却結果
     * @throws Exception
     *             処理実行時に系统例外发生的情况
     */
    public String doImage() throws Exception {

        RPM00203Form form_ = (RPM00203Form) getSaveFormObject();
        rpm00203Form.setSessionImageDataSource(form_.getSessionImageDataSource());
        // 終了
        return SUCCESS;

    }

    /**
     * 上传
     * 
     * @return ページパス
     */
    public String uploadAjax() {

        COMFileUploadResultDto dto = new COMFileUploadResultDto();

        try {
            File _upLoadFile = rpm00203Form.getFile().get(0);

            long fileSize = _upLoadFile.length();

            // 画像イメージファイルサイズ上限値
            BigDecimal imageFileMaxSize = UnitUtils.byteToMByte(2 * 1024 * 1024);
            if (CheckUtils.isMoreThan(UnitUtils.byteToMByte(fileSize), imageFileMaxSize)) {
                List<CodeValueRecord> newIdentityImage = new ArrayList<CodeValueRecord>();
                CodeValueRecord record = new CodeValueRecord();
                record.setRecordCode("error");
                record.setRecordValue("图片不能大于2M。");
                newIdentityImage.add(record);
                rpm00203Form.setSessionImageDataSource(newIdentityImage);
            } else {
                String _fileName = rpm00203Form.getFileFileName().get(0);
                String fileType = _fileName.substring(_fileName.lastIndexOf(".") + 1).toLowerCase();
                BufferedImage bufferedImage = ImageIO.read(_upLoadFile);

                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                BufferedOutputStream bos = new BufferedOutputStream(baos);
                bufferedImage.flush();
                ImageIO.write(bufferedImage, fileType, bos);
                bos.flush();
                bos.close();
                byte[] bImage = baos.toByteArray();
                StringBuffer identityImage = new StringBuffer("data:");
                identityImage.append("image/");
                identityImage.append(fileType);
                identityImage.append(";base64,");
                identityImage.append(Base64.encode(bImage));
                dto.setThumbnailUrl(identityImage.toString());

                List<CodeValueRecord> newIdentityImage = new ArrayList<CodeValueRecord>();
                CodeValueRecord record = new CodeValueRecord();
                record.setRecordCode("1");
                record.setRecordValue(identityImage.toString());
                newIdentityImage.add(record);
                rpm00203Form.setSessionImageDataSource(newIdentityImage);
            }

        } catch (Exception e) {
            dto.setError(e.getLocalizedMessage());
        }

        request.setAttribute("uploadResult", dto);

        return "upload_json";

    }

    /**
     * 添加用户的formを取得する。
     * 
     * @return 添加用户的form
     */
    public RPM00203Form getRpm00203Form() {
        return rpm00203Form;
    }

    /**
     * 添加用户的formを設定する。
     * 
     * @param _rpm00203Form 添加用户的form
     */
    public void setRpm00201Form(RPM00203Form _rpm00203Form) {
        this.rpm00203Form = _rpm00203Form;
    }

}
