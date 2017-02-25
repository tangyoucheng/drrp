/*
 * Copyright(c) 2011 
 */
package cn.com.prescription.leshan.scz.data;

import java.util.List;

import cn.com.prescription.leshan.scz.data.condition.SCZ9901Condition;
import cn.com.prescription.leshan.scz.data.model.SCZ9901Model;

import org.intra_mart.framework.base.data.DAO;

/**
 * SCZ9901 门户画面DAO インタフェース。
 * 
 * @author zfy
 */
/*
 * 新規作成 DATE:2012.04.10 NAME: zfy
 */
public interface SCZ9901Dao extends DAO {

    /**
     * 菜单基本BEAN
     */
    Class<?> BEAN = SCZ9901Model.class;

    /**
     * 1階層菜单情報的取得。
     * 
     * @param _condition 取得条件
     * @return 取得結果
     */
    List<SCZ9901Model> selectMenuHierarchy1(SCZ9901Condition _condition);

    /**
     * 2階層菜单情報的取得。
     * 
     * @param _condition 取得条件
     * @return 取得結果
     */
    List<SCZ9901Model> selectMenuHierarchy2(SCZ9901Condition _condition);

    /**
     * 3階層菜单情報的取得。
     * 
     * @param _condition 取得条件
     * @return 取得結果
     */
    List<SCZ9901Model> selectMenuHierarchy3(SCZ9901Condition _condition);

}
