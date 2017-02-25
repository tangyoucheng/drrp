/*
 * Copyright(c) 2016 NTT DATA KANSAI CORPORATION
 */

package cn.com.prescription.leshan.rpm.data;

import java.util.List;

import org.intra_mart.framework.base.data.DAO;

import cn.com.prescription.leshan.rpm.data.condition.RPM00401Condition;
import cn.com.prescription.leshan.rpm.data.model.RPM00401Model;

/**
 * 角色权限管理DAO インタフェース。
 *
 * @author gl
 */
/*
 * 新規作成
 * DATE: 2012.02.13 NAME: gl
 */
public interface RPM00401Dao extends DAO {

    /**
     * 角色权限管理BEAN
     */
    Class<?> BEAN = RPM00401Model.class;

    /**
     * 権限情報（役割）登録 菜单リストを取得する。
     * 
     * @param _condition 取得条件
     * @return 取得結果
     */
    List<RPM00401Model> selectMenuList(RPM00401Condition _condition);

    /**
     * 権限情報（役割）登録 菜单情報リストを取得する。
     * 
     * @param _condition 取得条件
     * @return 取得結果
     */
    List<RPM00401Model> selectMenuInfoList(RPM00401Condition _condition);

    /**
     * 権限情報（役割）登録 菜单情報件数を取得する
     * 
     * @param _condition 取得条件
     * @return 取得結果
     */
    long selectMenuInfoCount(RPM00401Condition _condition);

}
