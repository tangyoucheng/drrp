/*
 * Copyright(c) 2016 
 */
package cn.com.prescription.leshan.rpb.action;

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
import cn.com.prescription.framework.util.CheckUtils;
import cn.com.prescription.framework.util.UnitUtils;
import cn.com.prescription.leshan.common.action.form.COMFileUploadResultDto;
import cn.com.prescription.leshan.rpb.action.form.RPB00101Form;
import cn.com.prescription.leshan.rpb.biz.RPB00101DeleteLogic;
import cn.com.prescription.leshan.rpb.biz.RPB00101InitLogic;
import cn.com.prescription.leshan.rpb.biz.RPB00101UpdateLogic;

/**
 * 门店信息 action。
 * 
 * @author fsb
 */
/*
 * 新規作成
 * DATE: 2012.11.07 NAME: fsb
 */
public class RPB00101Action extends AbstractAction {

    /**
     * 串行版本UID
     */
    private static final long serialVersionUID = 1L;

    /**
     * 门店信息的form
     */
    private RPB00101Form rpb00101Form = new RPB00101Form();

    /**
     * RPB00101Action 的构造。
     */
    public RPB00101Action() {
        super();
    }

    /**
     * 初期表示的处理。
     * 
     * @return 返却結果
     * @throws Exception 処理実行時に系统例外发生的情况
     */
    public String doInit() throws Exception {
        this.setRpb00101Form(new RPB00101Form());
        // 用户情報設定eventを実行する
        doDispatchEvent(rpb00101Form, RPB00101InitLogic.class);
        return SUCCESS;
    }

    /**
     * 编辑。
     * 
     * @return 返却結果
     * @throws Exception 処理実行時に系统例外发生的情况
     */
    public String doUpdate() throws Exception {
        doDispatchEvent(rpb00101Form, RPB00101UpdateLogic.class);
        return SUCCESS;
    }

    /**
     * 删除。
     * 
     * @return 返却結果
     * @throws Exception 処理実行時に系统例外发生的情况
     */
    public String doDelete() throws Exception {
        doDispatchEvent(rpb00101Form, RPB00101DeleteLogic.class);
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

        RPB00101Form form_ = (RPB00101Form) getSaveFormObject();
        rpb00101Form.setSessionImageDataSource(form_.getSessionImageDataSource());
        // 終了
        return SUCCESS;

    }

    /**
     * 上传
     * 
     * @return 返却結果
     */
    public String uploadAjax() {

        COMFileUploadResultDto dto = new COMFileUploadResultDto();

        try {
            File _upLoadFile = rpb00101Form.getFile().get(0);

            long fileSize = _upLoadFile.length();

            // 画像イメージファイルサイズ上限値
            BigDecimal imageFileMaxSize = UnitUtils.byteToMByte(2 * 1024 * 1024);
            if (CheckUtils.isMoreThan(UnitUtils.byteToMByte(fileSize), imageFileMaxSize)) {
                List<CodeValueRecord> newIdentityImage = new ArrayList<CodeValueRecord>();
                CodeValueRecord record = new CodeValueRecord();
                record.setRecordCode("error");
                record.setRecordValue("图片不能大于2M。");
                newIdentityImage.add(record);
                rpb00101Form.setSessionImageDataSource(newIdentityImage);
            } else {
                String _fileName = rpb00101Form.getFileFileName().get(0);
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
                rpb00101Form.setSessionImageDataSource(newIdentityImage);
            }

        } catch (Exception e) {
            dto.setError(e.getLocalizedMessage());
        }

        request.setAttribute("uploadResult", dto);

        return "upload_json";

    }

    /**
     * 用户一览的form的取得
     * 
     * @return 用户一览的form
     */
    public RPB00101Form getRpb00101Form() {
        return rpb00101Form;
    }

    /**
     * 门店信息的form的设定
     * 
     * @param _rpb00101Form 门店信息的form
     */
    public void setRpb00101Form(RPB00101Form _rpb00101Form) {
        this.rpb00101Form = _rpb00101Form;
    }

}
