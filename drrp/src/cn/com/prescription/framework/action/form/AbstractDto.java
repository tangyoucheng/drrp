/*
 * Copyright(c) 2011 
 */
package cn.com.prescription.framework.action.form;

import java.io.Serializable;

/**
 * 標準の抽象Dto.
 * 
 * @author NTTDC
 */
public abstract class AbstractDto implements Serializable {

    /**
     * 串行版本号
     */
    private static final long serialVersionUID = 1L;

    /**
     * 選択标识
     */
    private boolean check = false;

    /** NO */
    public String no = "";

    /**
     * 消除标识(false:有効;true:無効)
     */
    public boolean delete = false;

    /**
     * バリデーション标识(false:有効;true:無効)
     */
    private boolean notValidate = true;

    /**
     * バリデーション标识(1:有効;空文字:無効)
     */
    private String validateFlag = "1";

    /**
     * 有効データのIndex
     */
    private String validDataIndex = null;

    /**
     * 最終更新日付
     */
    public String updateDate = null;

    /** uploadファールのDto */
    public UploadFileDto uploadFile = null;

    /**
     * 選択标识を取得します。
     * @return 選択标识
     */
    public boolean isCheck() {
        return check;
    }

    /**
     * 選択标识を設定します。
     * @param check 選択标识
     */
    public void setCheck(boolean check) {
        this.check = check;
    }
    
    /**
     * NOを取得します。
     * @return NO
     */
    public String getNo() {
        return no;
    }

    /**
     * NOを設定します。
     * @param no NO
     */
    public void setNo(String no) {
        this.no = no;
    }

    /**
     * 消除标识(false:有効;true:無効)を取得します。
     * @return 消除标识(false:有効;true:無効)
     */
    public boolean isDelete() {
        return delete;
    }

    /**
     * 消除标识(false:有効;true:無効)を設定します。
     * @param delete 消除标识(false:有効;true:無効)
     */
    public void setDelete(boolean delete) {
        this.delete = delete;
    }

    /**
     * バリデーション标识(1:有効;空文字:無効)を取得します。
     * @return バリデーション标识(1:有効;空文字:無効)
     */
    public String getValidateFlag() {
        return validateFlag;
    }

    /**
     * バリデーション标识(1:有効;空文字:無効)を設定します。
     * @param validateFlag バリデーション标识(1:有効;空文字:無効)
     */
    public void setValidateFlag(String validateFlag) {
        this.validateFlag = validateFlag;
    }

    /**
     * バリデーション标识(false:有効;true:無効)を取得します。
     * @return バリデーション标识(false:有効;true:無効)
     */
    public boolean isNotValidate() {
        return notValidate;
    }

    /**
     * バリデーション标识(false:有効;true:無効)を設定します。
     * @param notValidate バリデーション标识(false:有効;true:無効)
     */
    public void setNotValidate(boolean notValidate) {
        this.notValidate = notValidate;
    }
    
    /**
     * 有効データのIndexを取得します。
     * @return 有効データのIndex
     */
    public String getValidDataIndex() {
        return validDataIndex;
    }

    /**
     * 有効データのIndexを設定します。
     * @param validDataIndex 有効データのIndex
     */
    public void setValidDataIndex(String validDataIndex) {
        this.validDataIndex = validDataIndex;
    }

    /**
     * 最終更新日付を取得します。
     * @return 最終更新日付
     */
    public String getUpdateDate() {
        return updateDate;
    }

    /**
     * 最終更新日付を設定します。
     * @param updateDate 最終更新日付
     */
    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * uploadファールのDtoを取得します。
     * @return uploadファールのDto
     */
    public UploadFileDto getUploadFile() {
        return uploadFile;
    }

    /**
     * uploadファールのDtoを設定します。
     * @param uploadFile uploadファールのDto
     */
    public void setUploadFile(UploadFileDto uploadFile) {
        this.uploadFile = uploadFile;
    }
}
