/*
 * Copyright(c) 2011 
 */
package cn.com.prescription.framework.event;

import cn.com.prescription.framework.biz.StandardLogic;
import cn.com.prescription.framework.exception.DbExclusiveException;
import cn.com.prescription.framework.util.ServiceUtils;

import org.intra_mart.framework.system.exception.ApplicationException;
import org.intra_mart.framework.system.exception.SystemException;

/**
 * 標準eventリスナー.
 *
 * @author nttdc
 * @param <P> eventクラス
 * @param <R> event処理結果クラス
 */
public class StandardEventListener<P extends StandardEvent, R extends StandardEventResult> extends
                AbstractStandardEventListener<P, R> {

    /**
     * event処理を行う。
     *
     * @param _event event
     * @throws ApplicationException event処理里应用程序例外发生的情况
     * @throws SystemException event処理里系统例外发生的情况
     */
    @Override
    protected void execute(P _event) throws ApplicationException, SystemException {

        // コンポーネントを実行
        R eventResult_ = executeComponent(_event.getObject(), _event);

        // 処理結果を設定
        setEventResult(eventResult_);

        // formを設定
        if (eventResult_ != null) {
            setForm(eventResult_.getForm());
        }

    }

    /**
     * コンポーネント実行.
     *
     * @param _component コンポーネントクラス
     * @param _event event
     * @return 処理結果
     * @throws ApplicationException アプリケーション错误発生時
     * @throws SystemException 予期せぬ错误発生時
     */
    protected R executeComponent(Object _component, P _event) throws ApplicationException, SystemException {

        // クラス指定の場合
        if (_component instanceof Class<?>) {
            // コンテナからコンポーネントを取得
            _component = ServiceUtils.getComponent(_component);
        }

        // ロジックへ変換
        StandardLogic logic_ = (StandardLogic) _component;
        // 例外
        Throwable throwable_ = null;

        try {
        	// 返回结果生成
        	logic_.createEventResult();

            // コンポーネントを実行
            return (R) logic_.service(_event);

        } catch (DbExclusiveException e) {
            // 例外保存
            throwable_ = e;
            // スロー
            throw e;
        } catch (ApplicationException e) {
            // スロー
            throw e;
        } catch (SystemException e) {
            // 例外保存
            throwable_ = e;
            // スロー
            throw e;
        } catch (Throwable e) {
            // 例外保存
            throwable_ = e;
            // スロー
            throw new SystemException(e);
        } finally {
            // 終了処理
            logic_.cleanup(throwable_);
        }

    }

}
