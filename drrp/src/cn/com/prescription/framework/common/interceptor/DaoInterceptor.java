/*
 * Copyright(c) 2011 
 */
package cn.com.prescription.framework.common.interceptor;

import cn.com.prescription.framework.exception.ApplicationException;
import cn.com.prescription.framework.exception.DbExclusiveException;
import cn.com.prescription.framework.message.MessageUtils;
import cn.com.prescription.framework.util.LogUtils;

import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.lang.StringUtils;
import org.seasar.dao.NotSingleRowUpdatedRuntimeException;
import org.seasar.framework.aop.interceptors.AbstractInterceptor;
import org.seasar.framework.exception.SQLRuntimeException;

/**
 * DAOインターセプター.
 * 
 * @author nttdc
 */
public class DaoInterceptor extends AbstractInterceptor {

    /** 串行版本号 */
    private static final long serialVersionUID = 1L;

    /**
     * インターセプト処理.
     * 
     * @param _invocation action呼出
     * @return 処理結果
     * @throws Throwable 予期せぬ错误発生時
     */
    public Object invoke(MethodInvocation _invocation) throws Throwable {

        // ターゲットクラス
        String class_ = getTargetClass(_invocation).getName();
        // メソッド
        String method_ = _invocation.getMethod().getName();
        // 処理開始時間
        long startMillis_ = System.currentTimeMillis();
        // 処理開始ログ
        LogUtils.start(class_, method_);

        try {

            // 実行
            return _invocation.proceed();

        } catch (NotSingleRowUpdatedRuntimeException e) {
            // DELETE, UPDATE は错误としない
            if (method_.startsWith("delete") || method_.startsWith("update")) {
                return 0;
            }
            // 排他错误とする
            throw newDbExclusiveException(e);

        } catch (SQLRuntimeException e) {
            // 排他错误チェック
            if (isDbExclusive(e)) {
                throw newDbExclusiveException(e);
            }
            // そのまま投げ返す
            throw e;

        } finally {
            // 処理終了ログ
            LogUtils.end(class_, method_);
            // 処理終了
            LogUtils.process(class_, method_, startMillis_, System.currentTimeMillis());
        }

    }

    /**
     * 排他错误チェック.
     * 
     * @param _throwable 発生した例外
     * @return 排他错误の場合 true.
     */
    private boolean isDbExclusive(Throwable _throwable) {
        while (_throwable != null) {
            if (StringUtils.contains(_throwable.getMessage(), "ErrorCode=55P03")) {
                return true;
            }
            _throwable = _throwable.getCause();
        }
        return false;
    }

    /**
     * 排他例外生成
     * 
     * @param _throwable 元となる例外
     * @return 排他例外
     */
    private ApplicationException newDbExclusiveException(Throwable _throwable) {
        return new DbExclusiveException(_throwable.getMessage(), MessageUtils.getMessage("E00005"));
    }

}
