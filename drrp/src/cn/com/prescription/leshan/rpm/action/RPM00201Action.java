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
import java.util.Base64;
import java.util.List;

import javax.imageio.ImageIO;


import cn.com.prescription.framework.CodeValueRecord;
import cn.com.prescription.framework.action.AbstractAction;
import cn.com.prescription.framework.message.MessageUtils;
import cn.com.prescription.framework.util.CheckUtils;
import cn.com.prescription.framework.util.DateUtils;
import cn.com.prescription.framework.util.UnitUtils;
import cn.com.prescription.leshan.common.LeshanConstantsIF;
import cn.com.prescription.leshan.common.action.form.COMFileUploadResultDto;
import cn.com.prescription.leshan.rpm.action.form.RPM00201Form;
import cn.com.prescription.leshan.rpm.biz.RPM00201EntryLogic;
import cn.com.prescription.leshan.rpm.biz.RPM00201InitLogic;

/**
 * 用户情報設定 action。
 * 
 * @author fsb
 */
/*
 * 新規作成
 * DATE: 2012.11.07 NAME: fsb
 */
public class RPM00201Action extends AbstractAction {

    /**
     * 串行版本UID
     */
    private static final long serialVersionUID = 1L;

    /**
     * 添加用户的form
     */
    private RPM00201Form rpm00201Form = new RPM00201Form();

    /**
     * SCM0301Action 的构造。
     */
    public RPM00201Action() {
        super();
    }

    /**
     * 用户情報設定 初期表示的处理。
     * 
     * @return 返却結果
     * @throws Exception 処理実行時に系统例外发生的情况
     */
    public String doInit() throws Exception {
        this.setRpm00201Form(new RPM00201Form());
        // 用户情報設定eventを実行する
        doDispatchEvent(rpm00201Form, RPM00201InitLogic.class);
        return SUCCESS;
    }

    /**
     * 用户情報設定的处理。
     * 
     * @return 返却結果
     * @throws Exception 処理実行時に系统例外发生的情况
     */
    public String doEntry() throws Exception {
        doDispatchEvent(rpm00201Form, RPM00201EntryLogic.class);
        return SUCCESS;
    }

    /**
     * 登録処理の相関チック的处理。
     * 
     * @return 返却結果
     * @throws Exception 処理実行時に系统例外发生的情况
     */
    public void validateDoEntry() throws Exception {

        if (CheckUtils.isEmpty(rpm00201Form.getStartDate())) {
            List<String> checkDate = new ArrayList<String>();
            checkDate.add("rpm00201Form.startDate");
            this.addFieldError(checkDate, MessageUtils.getMessage("E30001", "有效期间（开始）"));
        }
        String startDate = "";
        String endDate = "";
        if (!CheckUtils.isEmpty(rpm00201Form.getStartDate())) {
            startDate = DateUtils.format(rpm00201Form.getStartDate(),
                LeshanConstantsIF.DATE_FORMAT_YYYY_YEAR_MM_MONTH_DD_DATE, LeshanConstantsIF.DATE_FORMAT_YYYYMMDD);
        }
        if (!CheckUtils.isEmpty(rpm00201Form.getEndDate())) {
            endDate = DateUtils.format(rpm00201Form.getEndDate(),
                LeshanConstantsIF.DATE_FORMAT_YYYY_YEAR_MM_MONTH_DD_DATE, LeshanConstantsIF.DATE_FORMAT_YYYYMMDD);
        }
        if (!CheckUtils.isEmpty(startDate) && !CheckUtils.isEmpty(endDate) && startDate.compareTo(endDate) > 0) {
            List<String> checkDate = new ArrayList<String>();
            checkDate.add("rpm00201Form.startDate");
            checkDate.add("rpm00201Form.endDate");
            this.addFieldError(checkDate, MessageUtils.getMessage("E30032", "有效期间（终了）", "有效期间（开始）"));
        }

        if (CheckUtils.isEmpty(rpm00201Form.getNewIdentityImage())) {
            this.addFieldError("", MessageUtils.getMessage("E30001", "证件图片"));
        }
    }

    /**
     * 写真ファイル一時保存 写真取込的处理。
     * 
     * @return 返却結果
     * @throws Exception
     *             処理実行時に系统例外发生的情况
     */
    public String doImage() throws Exception {

        RPM00201Form form_ = (RPM00201Form) getSaveFormObject();
        rpm00201Form.setSessionImageDataSource(form_.getSessionImageDataSource());
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
            File _upLoadFile = rpm00201Form.getFile().get(0);

            long fileSize = _upLoadFile.length();

            // 画像イメージファイルサイズ上限値
            BigDecimal imageFileMaxSize = UnitUtils.byteToMByte(2 * 1024 * 1024);
            if (CheckUtils.isMoreThan(UnitUtils.byteToMByte(fileSize), imageFileMaxSize)) {
                List<CodeValueRecord> newIdentityImage = new ArrayList<CodeValueRecord>();
                CodeValueRecord record = new CodeValueRecord();
                record.setRecordCode("error");
                record.setRecordValue("图片不能大于2M。");
                newIdentityImage.add(record);
                rpm00201Form.setSessionImageDataSource(newIdentityImage);
            } else {
                String _fileName = rpm00201Form.getFileFileName().get(0);
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
                identityImage.append(Base64.getEncoder().encode(bImage));
                dto.setThumbnailUrl(identityImage.toString());

                List<CodeValueRecord> newIdentityImage = new ArrayList<CodeValueRecord>();
                CodeValueRecord record = new CodeValueRecord();
                record.setRecordCode("1");
                record.setRecordValue(identityImage.toString());
                newIdentityImage.add(record);
                rpm00201Form.setSessionImageDataSource(newIdentityImage);
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
    public RPM00201Form getRpm00201Form() {
        return rpm00201Form;
    }

    /**
     * 添加用户的formを設定する。
     * 
     * @param _rpm00201Form 添加用户的form
     */
    public void setRpm00201Form(RPM00201Form _rpm00201Form) {
        this.rpm00201Form = _rpm00201Form;
    }

}
