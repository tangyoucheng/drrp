/*
 * Copyright(c) 2016 NTT DATA KANSAI CORPORATION
 */

package cn.com.prescription.leshan.rpc.data;

import org.intra_mart.framework.base.data.DAO;

import cn.com.prescription.leshan.rpc.data.condition.RPC00105Condition;
import cn.com.prescription.leshan.rpc.data.model.RPC00105Model;

/**
 * 复诊编辑DAO インタフェース。
 *
 * @author gl
 */
/*
 * 新規作成 DATE: 2012.02.13 NAME: gl
 */
public interface RPC00105Dao extends DAO {

	/**
	 * 复诊编辑BEAN
	 */
	Class<?> BEAN = RPC00105Model.class;

	/**
	 * 门诊のデータを取得する。
	 * <p>
	 * 指定された条件の複数データの取得を行う。<br>
	 * sqlはS2Daoにて自動生成される。 <br>
	 * 【注意事項】
	 * <ul>
	 * <li>該当データが存在しない場合は、空リストが返却される。</li>
	 * </ul>
	 * </p>
	 *
	 * @param _condition
	 *            取得条件
	 * @return 取得結果
	 */
	RPC00105Model selectOutpatientInfo(RPC00105Condition _condition);

}
