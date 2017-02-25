/*
 * Copyright(c) 2011 
 */
package cn.com.prescription.framework.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;
import java.util.UUID;

import javax.sql.XADataSource;
import javax.transaction.UserTransaction;

import cn.com.prescription.framework.StandardConstantsIF;
import cn.com.prescription.framework.action.AbstractAction;
import cn.com.prescription.framework.common.session.UserSessionInfo;
import cn.com.prescription.framework.common.session.UserSessionUtils;
import cn.com.prescription.framework.exception.ApplicationException;
import cn.com.prescription.framework.exception.SystemException;

import org.apache.struts2.ServletActionContext;
import org.intra_mart.framework.base.data.DAO;
import org.intra_mart.framework.base.event.Event;
import org.intra_mart.framework.base.event.EventException;
import org.intra_mart.framework.base.event.EventManager;
import org.intra_mart.framework.base.event.EventManagerException;
import org.intra_mart.framework.base.event.EventPropertyException;
import org.intra_mart.framework.base.event.EventResult;
import org.seasar.extension.datasource.DataSourceFactory;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

import com.opensymphony.xwork2.ActionContext;

/**
 * サービスユーティリティ.
 * 
 * @author nttdc
 */
public class ServiceUtils {

    /** パッケージ */
    public static final String PACKAGE = "jp.co.nttdata_chugoku.koumu";

    /**
     * event生成.
     * 
     * @param <T> 戻り型
     * @param _application アプリケーション
     * @param _key eventキー
     * @return event
     * @throws SystemException 予期せぬ例外発生時
     */
    public static <T extends Event> T createEvent(String _application, String _key) throws SystemException {
        try {
            return (T) EventManager.getEventManager().createEvent(_application, _key);
        } catch (EventPropertyException e) {
            throw new SystemException(e.getMessage(), e);
        } catch (EventException e) {
            throw new SystemException(e.getMessage(), e);
        } catch (EventManagerException e) {
            throw new SystemException(e.getMessage(), e);
        }
    }

    /**
     * event実行.
     * 
     * @param <T> 戻り型
     * @param event eventオブジェクト
     * @return event処理結果
     * @throws SystemException 予期せぬ错误発生時
     * @throws ApplicationException アプリケーション错误発生時
     * @throws LoginErrorException 登录错误発生時
     */
    public static <T extends EventResult> T dispatchEvent(Event event) throws ApplicationException, SystemException {
        try {
            return (T) EventManager.getEventManager().dispatch(event);
        } catch (EventException e) {
            throw new SystemException(e.getMessage(), e);
        } catch (EventManagerException e) {
            throw new SystemException(e.getMessage(), e);
        } catch (org.intra_mart.framework.system.exception.SystemException e) {
            throw new SystemException(e.getMessage(), e);
        } catch (ApplicationException e) {
            throw e;
        } catch (org.intra_mart.framework.system.exception.ApplicationException e) {
            throw new ApplicationException(e);
        }
    }

    /**
     * @return 現在のセッションID
     */
    public static String getSessionId() {

        // セッションIDを取得
        String sessionId_ = ServletActionContext.getRequest().getSession().getId();

        // try {
        // // このサーバー自身を取得
        // InetAddress localHost_ = InetAddress.getLocalHost();
        // // 自身のIPアドレスを取得
        // String localAddress_ = localHost_.getHostAddress();
        // // IPアドレスを付与
        // sessionId_ = localAddress_.replaceAll("\\.", "_").concat("_").concat(sessionId_);
        // } catch (UnknownHostException e) {
        // // 失敗した場合何もしない
        // }

        // 用户情報を取得
//        UserSessionInfo info_ = UserSessionUtils.getUserSessionInfo();
        // スキーマ名を付与
//        if (info_ != null && !CheckUtils.isEmpty(info_.getSchemaName())) {
//            sessionId_ = info_.getSchemaName().concat("_").concat(sessionId_);
//        }

        // セッションIDを返す
//        return sessionId_.replaceAll("\\.", "_");
        return sessionId_;

    }

    /**
     * HttpSessionの取得
     * 
     * @return HttpSession
     */
    private static Map<String, Object> getHttpSession() {
        if (ActionContext.getContext() == null) {
            return null;
        }
        if (ActionContext.getContext().getSession() == null
                        || ServletActionContext.getRequest().getSession(false) == null) {
            return null;
        }
        return ActionContext.getContext().getSession();
    }

    /**
     * HttpSessionへの保存
     * 
     * @param <T> 保存値の型
     * @param _key セッションキー
     * @param _value 保存値
     * @return 保存値
     */
    public static <T> T putSession(String _key, T _value) {
        if (getHttpSession() == null) {
            ActionContext.getContext().getSession().put(_key, _value);
        } else {
            getHttpSession().put(_key, _value);
        }
        return _value;
    }

    /**
     * HttpSessionからの取得
     * 
     * @param <T> 保存値の型
     * @param _key セッションキー
     * @return 保存値
     */
    public static <T> T getSession(String _key) {
        if (getHttpSession() == null) {
            return null;
        }
        return (T) getHttpSession().get(_key);
    }

    /**
     * HttpSessionから削除
     * 
     * @param <T> 保存値の型
     * @param _key セッションキー
     * @return 保存値
     */
    public static <T> T removeSession(String _key) {
        if (getHttpSession() == null) {
            return null;
        }
        return (T) getHttpSession().remove(_key);
    }

    /**
     * HttpSessionから取得&削除
     * 
     * @param <T> 保存値の型
     * @param _key セッションキー
     * @return 保存値
     */
    public static <T> T popSession(String _key) {
        T result_ = getSession(_key);
        removeSession(_key);
        return result_;
    }

    /**
     * 現在のactionクラスを返します
     * 
     * @param <T> actionクラス型
     * @return 現在のactionクラス
     */
    public static <T extends AbstractAction> T getAction() {
        return (T) ActionContext.getContext().getActionInvocation().getAction();
    }

    /**
     * 現在のactionクラス型を返します
     * 
     * @param <T> actionクラス型
     * @return 現在のactionクラス
     */
    public static <T extends AbstractAction> Class<T> getActionClass() {
        return (Class<T>) getAction().getClass();
    }

    /**
     * URL用に、現在のactionクラス名を返します
     * 
     * @return 現在のactionクラス
     */
    public static String getActionUrl() {
        return getActionUrl(getActionClass());
    }

    /**
     * URL用に、指定されたactionクラス名を返します
     * 
     * @param _actionClass actionクラス
     * @return URL用actionクラス名
     */
    public static String getActionUrl(Class<? extends AbstractAction> _actionClass) {

        // actionクラスを取得
        String action_ = _actionClass.getName();

        // パッケージを分割
        action_ = action_.substring(action_.lastIndexOf(".") + 1).toLowerCase();

        // クラス名を返す
        return action_.replaceAll("action$", "Action");

    }

    /**
     * コンポーネント取得
     * 
     * @param <T> コンポーネント型
     * @param _key 取得キー
     * @return コンポーネント
     */
    public static <T> T getComponent(Object _key) {
        return (T) SingletonS2ContainerFactory.getContainer().getComponent(_key);
    }

    /**
     * トランザクション開始
     * 
     * @throws SystemException 予期せぬ错误発生時
     */
    public static void beginTransaction() throws SystemException {
        try {
            // トランザクションを取得
            UserTransaction transaction_ = getComponent("UserTransaction");
            // トランザクション開始
            transaction_.begin();
        } catch (Exception e) {
            // 系统例外
            throw new SystemException(e.getMessage(), e);
        }
    }

    /**
     * トランザクションコミット
     * 
     * @throws SystemException 予期せぬ错误発生時
     */
    public static void commitTransaction() throws SystemException {
        try {
            // トランザクションを取得
            UserTransaction transaction_ = getComponent("UserTransaction");
            // コミット
            transaction_.commit();
        } catch (Exception e) {
            // 系统例外
            throw new SystemException(e.getMessage(), e);
        }
    }

    /**
     * トランザクションロールバック
     * 
     * @throws SystemException 予期せぬ错误発生時
     */
    public static void rollbackTransaction() throws SystemException {
        try {
            // トランザクションを取得
            UserTransaction transaction_ = getComponent("UserTransaction");
            // ロールバック
            transaction_.rollback();
        } catch (Exception e) {
            // 系统例外
            throw new SystemException(e.getMessage(), e);
        }
    }

    /**
     * 絶対パス取得.
     * 
     * @param _paths パス構成要素
     * @return 絶対パス
     */
    public static String getAbsolutePath(String... _paths) {
        return FileUtils.getAbsolutePath(_paths);
    }

    /**
     * ユニークな識別子を生成する.
     * 
     * @return 呼び出す毎に異なるユニークキー
     */
    public static String generateUniqueKey() {
        // ユニークキー
        return StringUtils.join(
            new String[] {
                            UUID.randomUUID().toString(),
                            TimestampUtils.format(TimestampUtils.getSysTimestampFromApServer(),
                                StandardConstantsIF.DATE_FORMAT_YYYYMMDDHHMMSSSSS),
                            StringUtils.defaultString(System.nanoTime()) }, '_').replaceAll("\\.", "_");
    }

    /**
     * データソースの切替
     * 
     * @param _schemaName スキーマ名
     * @return 変更前のスキーマ名
     */
    public static String changeDataSource(String _schemaName) {

        // コンテナからファクトリを取得
        DataSourceFactory dsFactory = (DataSourceFactory) SingletonS2ContainerFactory.getContainer().getComponent(
            DataSourceFactory.class);

        // 現スキーマ名
        String oldSchema_ = dsFactory.getSelectableDataSourceName();

        // データソース名を設定
        dsFactory.setSelectableDataSourceName(_schemaName);

        // ログ
        LogUtils.debug("changeDataSource: ".concat(oldSchema_).concat(" -> ").concat(_schemaName));

        // 変更前を返す
        return oldSchema_;

    }

    /**
     * @param _class コンポーネント型
     * @return DAO
     * @throws SystemException 予期せぬ例外発生時
     */
    public static DAO getDAO(Class<?> _class) throws SystemException {
        try {
            return getComponent(_class);
        } catch (Exception e) {
            throw new SystemException(e);
        }
    }

    /**
     * 指定したSQL文の検索結果的取得
     * 
     * @param _sql SQL文
     * @return 更新件数
     * @param コミット标识
     * @throws SystemException 予期せぬ错误発生時
     */
    public synchronized static int executeQuery(String _sql, boolean autoCommit) throws SystemException {

        // 処理開始時間
        long startMillis_ = System.currentTimeMillis();
        // 処理開始ログ
        LogUtils.start(ServiceUtils.class, "executeQuery");

        Connection connection_ = null;
        PreparedStatement preparedStatement_ = null;
        int count_ = 0;
        // データソース
        String ds = null;
        try {
            // コンテナからファクトリを取得
            DataSourceFactory dsFactory = (DataSourceFactory) SingletonS2ContainerFactory.getContainer().getComponent(
                DataSourceFactory.class);

            if (dsFactory != null) {
                ds = dsFactory.getSelectableDataSourceName();
            }

            // データソースが未設定の場合
            if (CheckUtils.isEmpty(ds)) {
                // デフォルトスキーマに接続
                ds = StandardConstantsIF.KYOTU_KYOTU_SCHEMA;
            }

            XADataSource xaDataSource_ = getComponent(ds);
            connection_ = xaDataSource_.getXAConnection().getConnection();
            connection_.setAutoCommit(autoCommit);
            preparedStatement_ = connection_.prepareStatement(_sql);

            // ログを出力
            LogUtils.info(_sql);
            // SQL文を実行する
            count_ = preparedStatement_.executeUpdate();
            if (connection_.isReadOnly()) {
                count_ = 0;
            }

            if (preparedStatement_ != null && !preparedStatement_.isClosed()) {
                preparedStatement_.close();
                preparedStatement_ = null;
            }
        } catch (Exception e) {
            // 系统例外
            throw new SystemException(e.getMessage(), e);
        } finally {
            try {
                if (connection_ != null && !connection_.isClosed()) {
                    connection_.close();
                    connection_ = null;
                }
                if (preparedStatement_ != null && !preparedStatement_.isClosed()) {
                    preparedStatement_.close();
                    preparedStatement_ = null;
                }
            } catch (SQLException e) {
                // 系统例外
                throw new SystemException(e.getMessage(), e);
            }
            // 処理終了ログ
            LogUtils.end(ServiceUtils.class, "executeQuery");
            // 処理終了
            LogUtils.process(ServiceUtils.class, "executeQuery", startMillis_, System.currentTimeMillis());
        }

        return count_;
    }

    /**
     * 指定したSQL文の検索結果的取得
     * 
     * @param _sql SQL文
     * @return 更新件数
     * @throws SystemException 予期せぬ错误発生時
     */
    public synchronized static int executeQuery(String _sql) throws SystemException {

        return executeQuery(_sql, true);
    }

}
