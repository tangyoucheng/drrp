/*
 * Copyright(c) 2016 
 */

package cn.com.prescription.leshan.rpa.action.form;

import cn.com.prescription.framework.action.form.AbstractForm;

/**
 * 患者信息导出dto。
 * 
 * @author fsb
 */
/*
 * 新規作成
 * DATE: 2012.11.09 NAME: fsb
 */
public class RPA0020501Dto extends AbstractForm {

    /**
     * 串行版本UID
     */
    private static final long serialVersionUID = 1L;

    /**
     * 是否选中
     */
    private boolean checkedFlag = false;

    /**
     * 导出信息ID
     */
    private String itemId = null;

    /**
     * 导出信息名
     */
    private String itemName = null;

    /**
     * 是否选中的取得
     * @return 是否选中
     */
    public boolean isCheckedFlag() {
        return checkedFlag;
    }

    /**
     * 是否选中的设定
     * @param _checkedFlag 是否选中
     */
    public void setCheckedFlag(boolean _checkedFlag) {
        this.checkedFlag = _checkedFlag;
    }

    /**
     * 导出信息ID的取得
     * @return 导出信息ID
     */
    public String getItemId() {
        return itemId;
    }

    /**
     * 导出信息ID的设定
     * @param _itemId 导出信息ID
     */
    public void setItemId(String _itemId) {
        this.itemId = _itemId;
    }

    /**
     * 导出信息名的取得
     * @return 导出信息名
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * 导出信息名的设定
     * @param _itemName 导出信息名
     */
    public void setItemName(String _itemName) {
        this.itemName = _itemName;
    }

}
