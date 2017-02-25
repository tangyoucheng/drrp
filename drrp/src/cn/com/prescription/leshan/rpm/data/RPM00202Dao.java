/*
 * Copyright(c) 2016 
 */
package cn.com.prescription.leshan.rpm.data;

import java.util.List;

import org.intra_mart.framework.base.data.DAO;

import cn.com.prescription.leshan.rpm.data.condition.RPM00202Condition;
import cn.com.prescription.leshan.rpm.data.model.RPM00202Model;

/**
 * 用户情報設定画面DAO インタフェース。
 * 
 * @author fsb
 */
/*
 * 新規作成
 * DATE:2012.11.07 NAME: fsb
 */
public interface RPM00202Dao extends DAO {

    /**
     * ログイン画面BEAN
     */
    Class<?> BEAN = RPM00202Model.class;

    /**
     * 用户情報を取得する。
     * 
     * @param _condition 取得条件
     * @return 取得結果
     */
    long selectUserInfoCount(RPM00202Condition _condition);

    /**
     * 用户情報を取得する。
     * 
     * @param _condition 取得条件
     * @return 取得結果
     */
    List<RPM00202Model> selectUserInfo(RPM00202Condition _condition);

}
