/*
 * Copyright(c) 2016
 */

package cn.com.prescription.leshan.common.data;

import org.intra_mart.framework.base.data.DAO;

import cn.com.prescription.leshan.common.data.condition.RpmPrescriptionCondition;
import cn.com.prescription.leshan.common.data.model.RpmPrescriptionModel;

/**
 * 处方基本信息表DAO インタフェース。
 *
 * @author gl
 */
/*
 * 新規作成
 * DATE: 2012.02.13 NAME: gl
 */
public interface RpmPrescription01Dao extends DAO {

    /**
     * 处方基本信息表BEAN
     */
    Class<?> BEAN = RpmPrescriptionModel.class;

    /**
     * 处方基本信息表のデータを取得する。
     * <p>
     * 指定された条件の単一データの取得を行う。<br>
     * 条件はデータベーステーブルの主キーとなる項目。<br>
     * sqlはS2Daoにて自動生成される。<br>
     * <br>
     * 【注意事項】
     * <ul>
     * <li>取得結果が複数データの場合は1件のみ返却されるため注意すること。</li>
     * <li>該当データが存在しない場合は、<code>null</code>が返却される。</li>
     * </ul>
     * </p>
     *
     * @param _condition 取得条件
     * @return 取得結果
     */
    int selectPrescriptionNumber(RpmPrescriptionCondition _condition);

}
