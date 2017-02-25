/*
 * Copyright(c) 2011 
 */
package cn.com.prescription.framework.common.interceptor;

import java.lang.reflect.UndeclaredThrowableException;

import org.aopalliance.intercept.MethodInvocation;
import org.apache.log4j.Level;
import org.apache.struts2.json.JSONUtil;
import org.intra_mart.framework.system.exception.SystemException;
import org.seasar.framework.aop.interceptors.AbstractInterceptor;

import cn.com.prescription.framework.event.exception.EventValidateException;
import cn.com.prescription.framework.exception.ApplicationException;
import cn.com.prescription.framework.util.LogUtils;

/**
 * eventインターセプター.
 * 
 * @author nttdc
 */
public class BizInterceptor extends AbstractInterceptor {

	/** 串行版本号 */
	private static final long serialVersionUID = 1L;

	/**
	 * インターセプト処理.
	 * 
	 * @param _invocation
	 *            action呼出
	 * @return 処理結果
	 * @throws Throwable
	 *             予期せぬ错误発生時
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
			try {
				return _invocation.proceed();
			} catch (UndeclaredThrowableException e) {
				throw e.getUndeclaredThrowable();
			}

		} catch (EventValidateException e) {
			// ログ出力
			LogUtils.debug(e.getMessage(), e);
			// 再スロー
			throw e;

		} catch (ApplicationException e) {
			// ログ出力
			LogUtils.warn(e.getMessage(), e);
			// 再スロー
			throw e;

		} catch (Exception e) {
			// ログ出力
			LogUtils.error(e.getMessage(), e);
			// 系统例外でラップ
			if (!(e instanceof SystemException)) {
				e = new SystemException(e.getMessage(), e);
			}
			// 再スロー
			throw e;

		} finally {
			// 処理終了ログ
			LogUtils.end(class_, method_);
			// 処理終了
			if (!method_.toLowerCase().equals("cleanup") && LogUtils.loggerFramework.isEnabledFor(Level.DEBUG)) {
				LogUtils.process(class_, method_, startMillis_, System.currentTimeMillis(),
						JSONUtil.serialize(_invocation.getArguments(), false));
			} else {
				LogUtils.process(class_, method_, startMillis_, System.currentTimeMillis());
			}
		}

	}

}
