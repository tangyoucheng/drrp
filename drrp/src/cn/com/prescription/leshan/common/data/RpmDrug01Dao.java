/*
 * Copyright(c) 2016
 */

package cn.com.prescription.leshan.common.data;

import java.util.List;

import org.intra_mart.framework.base.data.DAO;

import cn.com.prescription.leshan.common.data.condition.RpmDrug01Condition;
import cn.com.prescription.leshan.common.data.model.RpmDrug01Model;

/**
 * ?品信息表DAO インタフェース。
 *
 * @author gl
 */
/*
 * 新規作成
 * DATE: 2012.02.13 NAME: gl
 */
public interface RpmDrug01Dao extends DAO {

    /**
     * ?品信息表BEAN
     */
    Class<?> BEAN = RpmDrug01Model.class;

    /**
     * 药品基本信息表のデータを取得する。
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
    long selectDrugListCount(RpmDrug01Condition _condition);

    /**
     * 药品基本信息表のデータを取得する。
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
    List<RpmDrug01Model> selectDrugList(RpmDrug01Condition _condition);

}
