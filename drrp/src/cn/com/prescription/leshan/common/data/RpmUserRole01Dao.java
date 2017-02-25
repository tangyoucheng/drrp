/*
 * Copyright(c) 2016 NTT DATA KANSAI CORPORATION
 */

package cn.com.prescription.leshan.common.data;

import org.intra_mart.framework.base.data.DAO;

import cn.com.prescription.leshan.common.data.condition.RpmUserRoleCondition;
import cn.com.prescription.leshan.common.data.model.RpmUserRoleModel;

/**
 * 用?基本信息表DAO インタフェース。
 *
 * @author gl
 */
/*
 * 新規作成
 * DATE: 2012.02.13 NAME: gl
 */
public interface RpmUserRole01Dao extends DAO {

    /**
     * 用?基本信息表BEAN
     */
    Class<?> BEAN = RpmUserRoleModel.class;

    /**
     * 用?基本信息表のデータを更新する。
     * <p>
     * 更新内容のモデルBeanのうちnull以外の項目を更新対象とする。<br>
     * またデータベーステーブルの主キーとなる項目に設定された値を元に更新条件が生成される。<br>
     * sqlはS2Daoにて自動生成される。
     * </p>
     *
     * @param _model 更新内容
     * @return 登録件数
     */
    int updateUnlessNull(RpmUserRoleModel _model);

    /**
     * 用?基本信息表のデータを物理削除する。
     * <p>
     * 指定された条件のデータの物理削除を行う。<br>
     * sqlはS2Daoにて自動生成される。
     * </p>
     *
     * @param _model 削除条件
     * @return 削除件数
     */
    int deleteUserRole(RpmUserRoleCondition _condition);

}
