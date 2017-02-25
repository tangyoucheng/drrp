/*
 * Copyright(c) 2016 
 */

package cn.com.prescription.leshan.rpa.action.form;

import java.util.ArrayList;
import java.util.List;

import cn.com.prescription.framework.action.form.AbstractForm;

/**
 * 患者信息导出 form。
 * 
 * @author fsb
 */
/*
 * 新規作成
 * DATE: 2012.11.09 NAME: fsb
 */
public class RPA00205Form extends AbstractForm {

    /**
     * 串行版本UID
     */
    private static final long serialVersionUID = 1L;

    /**
     * subForm1
     */
    private List<RPA0020501Dto> subForm1 = new ArrayList<RPA0020501Dto>();

    /**
     * subForm1的取得
     * @return subForm1
     */
    public List<RPA0020501Dto> getSubForm1() {
        return subForm1;
    }

    /**
     * subForm1的设定
     * @param _subForm1 subForm1
     */
    public void setSubForm1(List<RPA0020501Dto> _subForm1) {
        this.subForm1 = _subForm1;
    }
}
