/*
 * Copyright(c) 2016 
 */
package cn.com.prescription.leshan.rpm.data;

import java.util.List;

import cn.com.prescription.leshan.rpm.data.condition.RPM00101Condition;
import cn.com.prescription.leshan.rpm.data.model.RPM00101Model;

import org.intra_mart.framework.base.data.DAO;

/**
 * 登录画面DAO インタフェース。
 * 
 * @author mhy
 */
/*
 * 新規作成 DATE:2012.11.20 NAME: mhy
 */
public interface RPM00101Dao extends DAO {

    /**
     * 登录画面BEAN
     */
    Class<?> BEAN = RPM00101Model.class;

    /**
     * 用户情報的取得。
     * 
     * @param _condition 取得条件
     * @return 取得結果
     */
    List<RPM00101Model> selectUserInfo(RPM00101Condition _condition);

}
