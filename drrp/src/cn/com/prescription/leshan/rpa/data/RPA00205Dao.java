/*
 * Copyright(c) 2016 NTT DATA KANSAI CORPORATION
 */

package cn.com.prescription.leshan.rpa.data;

import java.util.List;

import org.intra_mart.framework.base.data.DAO;

import cn.com.prescription.leshan.rpa.data.condition.RPA00205Condition;
import cn.com.prescription.leshan.rpa.data.model.RPA00205Model;

/**
 * 患者基本信息表DAO インタフェース。
 *
 * @author gl
 */
/*
 * 新規作成
 * DATE: 2012.02.13 NAME: gl
 */
public interface RPA00205Dao extends DAO {

    /**
     * 患者基本信息表BEAN
     */
    Class<?> BEAN = RPA00205Model.class;

    /**
     * 患者基本信息表のデータを取得する。
     * <p>
     * 指定された条件の複数データの取得を行う。<br>
     * sqlはS2Daoにて自動生成される。
     * <br>
     * 【注意事項】
     * <ul>
     * <li>該当データが存在しない場合は、空リストが返却される。</li>
     * </ul>
     * </p>
     *
     * @param _condition 取得条件
     * @return 取得結果
     */
    List<RPA00205Model> selectPatientList(RPA00205Condition _condition);

}
