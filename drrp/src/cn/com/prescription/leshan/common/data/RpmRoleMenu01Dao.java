/*
 * Copyright(c) 2016 NTT DATA KANSAI CORPORATION
 */

package cn.com.prescription.leshan.common.data;

import org.intra_mart.framework.base.data.DAO;

import cn.com.prescription.leshan.common.data.condition.RpmRoleMenuCondition;
import cn.com.prescription.leshan.common.data.model.RpmRoleMenuModel;

/**
 * 角色?限信息表DAO インタフェース。
 *
 * @author gl
 */
/*
 * 新規作成
 * DATE: 2012.02.13 NAME: gl
 */
public interface RpmRoleMenu01Dao extends DAO {

    /**
     * 角色?限信息表BEAN
     */
    Class<?> BEAN = RpmRoleMenuModel.class;

    /**
     * 角色?限信息表のデータを物理削除する。
     * <p>
     * 指定された条件のデータの物理削除を行う。<br>
     * sqlはS2Daoにて自動生成される。
     * </p>
     *
     * @param _model 削除条件
     * @return 削除件数
     */
    int deleteRoleMenu(RpmRoleMenuCondition _condition);

}
