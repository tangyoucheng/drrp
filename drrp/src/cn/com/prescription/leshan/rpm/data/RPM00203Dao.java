/*
 * Copyright(c) 2016 
 */
package cn.com.prescription.leshan.rpm.data;

import org.intra_mart.framework.base.data.DAO;

import cn.com.prescription.leshan.rpm.data.condition.RPM00203Condition;
import cn.com.prescription.leshan.rpm.data.model.RPM00203Model;

/**
 * 用户情報設定画面DAO インタフェース。
 * 
 * @author fsb
 */
/*
 * 新規作成
 * DATE:2012.11.07 NAME: fsb
 */
public interface RPM00203Dao extends DAO {

    /**
     * ログイン画面BEAN
     */
    Class<?> BEAN = RPM00203Model.class;

    /**
     * 用户情報を取得する。
     * 
     * @param _condition 取得条件
     * @return 取得結果
     */
    RPM00203Model selectUserInfo(RPM00203Condition _condition);

}
