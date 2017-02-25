/*
 * Copyright(c) 2016 NTT DATA KANSAI CORPORATION
 */

package cn.com.prescription.leshan.rpb.data;

import java.util.List;

import org.intra_mart.framework.base.data.DAO;

import cn.com.prescription.leshan.rpb.data.condition.RPB00101Condition;
import cn.com.prescription.leshan.rpb.data.model.RPB00101Model;

/**
 * 处方一览DAO インタフェース。
 *
 * @author gl
 */
/*
 * 新規作成
 * DATE: 2012.02.13 NAME: gl
 */
public interface RPB00101Dao extends DAO {

    /**
     * 处方一览BEAN
     */
    Class<?> BEAN = RPB00101Model.class;

    /**
     * 处方一览のデータを取得する。
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
    long selectPrescriptionListCount(RPB00101Condition _condition);

    /**
     * 处方一览のデータを取得する。
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
    List<RPB00101Model> selectPrescriptionList(RPB00101Condition _condition);

}
