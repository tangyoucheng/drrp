/*
 * Copyright(c) 2016
 */

package cn.com.prescription.leshan.common.data;

import java.util.List;
import cn.com.prescription.leshan.common.data.condition.RpmUserRoleCondition;
import cn.com.prescription.leshan.common.data.model.RpmUserRoleModel;
import cn.com.prescription.framework.exception.DbExclusiveException;
import org.intra_mart.framework.base.data.DAO;
import org.seasar.dao.annotation.tiger.Sql;

/**
 * 用?基本信息表DAO インタフェース。
 *
 * @author gl
 */
/*
 * 新規作成
 * DATE: 2012.02.13 NAME: gl
 */
public interface RpmUserRoleDao extends DAO {

    /**
     * 用?基本信息表BEAN
     */
    Class<?> BEAN = RpmUserRoleModel.class;

    /**
     * 用?基本信息表にデータを追加する。
     * <p>
     * 指定された登録内容でデータを新規追加する。<br>
     * sqlはS2Daoにて自動生成される。
     * </p>
     *
     * @param _model 登録内容
     * @return 登録件数
     */
    int insert(RpmUserRoleModel _model);

    /**
     * 用?基本信息表のデータを更新する。
     * <p>
     * 更新内容のモデルBeanのうちnullを含め全ての項目を更新対象とする。<br>
     * またデータベーステーブルの主キーとなる項目に設定された値を元に更新条件が生成される。<br>
     * sqlはS2Daoにて自動生成される。
     * </p>
     *
     * @param _model 更新内容
     * @return 登録件数
     */
    int update(RpmUserRoleModel _model);

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
    int delete(RpmUserRoleModel _model);

    /**
     * 用?基本信息表のデータを取得する。
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
    RpmUserRoleModel select(RpmUserRoleCondition _condition);

    /**
     * 用?基本信息表のデータを取得する。
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
    List<RpmUserRoleModel> selectList(RpmUserRoleCondition _condition);

    /**
     * 排他処理の用?基本信息表のデータを取得する。
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
     * @throws DbExclusiveException データベース排他エラー例外が発生した場合
     */
    RpmUserRoleModel selectForUpdate(RpmUserRoleCondition _condition) throws DbExclusiveException;

    /**
     * 用?基本信息表のデータを取得する。
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
     * @param _condition ソート条件
     * @return 取得結果
     */
    List<RpmUserRoleModel> selectBySort(RpmUserRoleCondition _condition);

    /**
     * 用?基本信息表のテーブルをロックする。
     * <p>
     * sqlはS2Daoにて自動生成される。
     * </p>
     */
    @Sql("LOCK TABLE RPM_USER_ROLE IN EXCLUSIVE MODE NOWAIT")
    void updateLockTable();

}
