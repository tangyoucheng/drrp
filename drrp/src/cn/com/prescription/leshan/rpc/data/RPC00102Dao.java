

package cn.com.prescription.leshan.rpc.data;

import java.util.List;

import org.intra_mart.framework.base.data.DAO;

import cn.com.prescription.leshan.rpc.data.condition.RPC00102Condition;
import cn.com.prescription.leshan.rpc.data.model.RPC00102Model;


/**
 * 门诊一览DAO インタフェース。
 *
 * @author gl
 */
/*
 * 新規作成
 * DATE: 2012.02.13 NAME: gl
 */
public interface RPC00102Dao extends DAO {

    /**
     * 门诊一览BEAN
     */
    Class<?> BEAN = RPC00102Model.class;

    /**
     * 门诊一览のデータを取得する。
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
    long selectOutpatientListCount(RPC00102Condition _condition);

    /**
     * 门诊一览のデータを取得する。
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
    List<RPC00102Model> selectOutpatientList(RPC00102Condition _condition);

}
