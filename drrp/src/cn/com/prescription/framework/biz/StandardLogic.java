/*
 * Copyright(c) 2011 
 */
package cn.com.prescription.framework.biz;

import cn.com.prescription.framework.event.StandardEvent;
import cn.com.prescription.framework.event.StandardEventResult;

import org.intra_mart.framework.system.exception.ApplicationException;
import org.intra_mart.framework.system.exception.SystemException;

/**
 * 標準のビジネスロジックインターフェース
 *
 * @author nttdc
 */
public interface StandardLogic {

    /**
     * 業務処理を行う。
     *
     * @param _event event
     * @return 処理結果
     * @throws ApplicationException event処理里应用程序例外发生的情况
     * @throws SystemException event処理里系统例外发生的情况
     */
    StandardEventResult service(StandardEvent _event) throws ApplicationException, SystemException;

    /**
     * 終了処理を行う（ service とはトランザクションが異なる )
     *
     * @param _systemException 例外错误オブジェクト（予期せぬ例外発生中のみ）
     * @throws ApplicationException アプリケーション例外発生時
     * @throws SystemException 系统例外発生時
     */
    void cleanup(Throwable _systemException) throws ApplicationException, SystemException;

    /**
     * 生成返回结果
     *
     * @throws ApplicationException アプリケーション例外発生時
     * @throws SystemException 系统例外発生時
     */
    void createEventResult() throws ApplicationException, SystemException;

}
