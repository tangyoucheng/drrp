/*
 * Copyright(c) 2011 
 */
package cn.com.prescription.framework.biz;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cn.com.prescription.leshan.common.LeshanConstantsIF;
import cn.com.prescription.framework.action.form.AbstractForm;
import cn.com.prescription.framework.action.form.StandartForm;
import cn.com.prescription.framework.common.file.CSVTokenizer;
import cn.com.prescription.framework.event.StandardEventResult;
import cn.com.prescription.framework.event.exception.EventAjaxApplicationException;
import cn.com.prescription.framework.event.exception.EventApplicationException;
import cn.com.prescription.framework.event.exception.EventSystemException;
import cn.com.prescription.framework.event.exception.EventValidateException;
import cn.com.prescription.framework.exception.SystemException;
import cn.com.prescription.framework.message.MessageUtils;
import cn.com.prescription.framework.util.CheckUtils;
import cn.com.prescription.framework.util.DateUtils;
import cn.com.prescription.framework.util.FileUtils;
import cn.com.prescription.framework.util.LogUtils;
import cn.com.prescription.framework.util.NumberUtils;
import cn.com.prescription.framework.util.ReflectUtils;
import cn.com.prescription.framework.util.ServiceUtils;
import cn.com.prescription.framework.util.StringUtils;
import cn.com.prescription.framework.util.TimestampUtils;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.intra_mart.framework.base.event.EventResult;
import org.intra_mart.framework.system.exception.ApplicationException;

import com.opensymphony.xwork2.ActionContext;

/**
 * 標準の抽象ビジネスロジック
 * 
 * @author nttdc
 */
public abstract class StandardBiz implements StandardLogic {

    /** ロジッククラスのセッションキープリフィックス */
    public static final String BIZ_SESSION_KEY = StandardBiz.class.getName().concat(".");

    /** 错误メッセージリスト */
    private ArrayList<String> errorMessages = new ArrayList<String>();

    /** event処理結果 */
    private EventResult eventResult = null;

    String XlsxFlgOn = "1";

    String FileTypeCsv = ".csv";
    String FileTypePdf = ".pdf";
    String FileTypeXlsx = ".xlsx";
    String FileTypeXls = ".xls";

    /**
     * 終了処理（ service とはトランザクションが異なる )
     * 
     * @param _systemException 例外错误オブジェクト（予期せぬ例外発生中のみ）
     * @throws ApplicationException アプリケーション例外発生時
     * @throws org.intra_mart.framework.system.exception.SystemException 系统例外発生時
     * @throws org.intra_mart.framework.system.exception.SystemException
     */
    public void cleanup(Throwable _systemException) throws ApplicationException, SystemException,
                    org.intra_mart.framework.system.exception.SystemException {
        LogUtils.debug("default cleanup: ".concat(this.getClass().getName()));
    }

    /**
     * 生成返回结果
     * 
     * @throws ApplicationException アプリケーション例外発生時
     * @throws SystemException 系统例外発生時
     */
    public void createEventResult() throws ApplicationException, SystemException {
        // 初めての生成
        if (this.eventResult == null) {
            this.eventResult = ReflectUtils.newInstance(StandardEventResult.class);
        }
    }

    /**
     * @return 呼出時点で错误が設定されている場合 true
     */
    protected final Boolean hasError() {
        return errorMessages != null && errorMessages.size() > 0;
    }

    /**
     * 処理で错误が発生したメッセージ的设定。
     * <p>
     * 処理で错误が発生した場合に错误内容に対応する項目名とメッセージ的设定。
     * </p>
     * 
     * @param _message メッセージ
     */
    protected final void addErrorMessage(String _message) {
        errorMessages.add(_message);
    }

    /**
     * チェック処理終了 チェック错误が発生する場合、event処理を終了する
     * 
     * @throws EventValidateException　eventバリデーション例外をスローする
     */
    protected final void checkErrorEnd() throws EventValidateException {
        if (hasError()) {
            throw new EventValidateException("event処理でチェック错误が発生しました。", errorMessages);
        }
    }

    /**
     * event処理でアプリケーション例外が発生する場合、
     * 
     * @throws EventApplicationException　event処理でアプリケーション例外が発生する場合、アプリケーション例外をスロー
     */
    protected final void errorEnd() throws EventApplicationException {
        errorEnd("", null, null);
    }

    /**
     * アプリケーション错误例外発生.
     * <p>
     * アプリケーション错误ダイアログを閉じた時に実行する処理を指定します
     * </p>
     * 
     * @param _okAction 「OK」押下時に実行するaction
     * @throws EventApplicationException　event処理でアプリケーション例外が発生する場合、アプリケーション例外をスロー
     */
    protected final void errorEnd(ActionInfo _okAction) throws EventApplicationException {
        errorEnd("", _okAction, null);
    }

    /**
     * アプリケーション错误例外発生.
     * <p>
     * アプリケーション错误ダイアログを閉じた時に実行する処理を指定します
     * </p>
     * 
     * @param _okAction 「はい」押下時に実行するaction
     * @param _cancelAction 「いいえ」押下時に実行するaction
     * @throws EventApplicationException　event処理でアプリケーション例外が発生する場合、アプリケーション例外をスロー
     */
    protected final void errorEnd(ActionInfo _okAction, ActionInfo _cancelAction) throws EventApplicationException {
        errorEnd("", _okAction, _cancelAction);
    }

    /**
     * event処理でアプリケーション例外が発生する場合、
     * 
     * @param _callBackFunction ダイアログボタン押下時のコールバックファンクション名
     * @throws EventApplicationException　event処理でアプリケーション例外が発生する場合、アプリケーション例外をスロー
     */
    protected final void errorEnd(String _callBackFunction) throws EventApplicationException {
        errorEnd(_callBackFunction, null, null);
    }

    /**
     * アプリケーション错误例外発生.
     * <p>
     * アプリケーション错误ダイアログを閉じた時に実行する処理を指定します
     * </p>
     * 
     * @param _callBackFunction ダイアログボタン押下時のコールバックファンクション名
     * @param _okAction 「OK」押下時に実行するaction
     * @throws EventApplicationException　event処理でアプリケーション例外が発生する場合、アプリケーション例外をスロー
     */
    protected final void errorEnd(String _callBackFunction, ActionInfo _okAction) throws EventApplicationException {
        errorEnd(_callBackFunction, _okAction, null);
    }

    /**
     * アプリケーション错误例外発生.
     * <p>
     * アプリケーション错误ダイアログを閉じた時に実行する処理を指定します
     * </p>
     * 
     * @param _callBackFunction ダイアログボタン押下時のコールバックファンクション名
     * @param _okAction 「はい」押下時に実行するaction
     * @param _cancelAction 「いいえ」押下時に実行するaction
     * @throws EventApplicationException　event処理でアプリケーション例外が発生する場合、アプリケーション例外をスロー
     */
    protected final void errorEnd(String _callBackFunction, ActionInfo _okAction, ActionInfo _cancelAction)
                    throws EventApplicationException {
        if (hasError()) {
            if (isCallAjaxAction()) {
                throw new EventAjaxApplicationException("event処理でアプリケーション例外が発生しました。", errorMessages, _callBackFunction,
                    _okAction, _cancelAction);
            }
            throw new EventApplicationException("event処理でアプリケーション例外が発生しました。", errorMessages, _callBackFunction,
                _okAction, _cancelAction);
        }
    }

    /**
     * event処理結果生成.
     * 
     * @param <T> 処理結果クラス
     * @return 処理結果インスタンス
     * @throws SystemException 予期せぬ错误発生時
     */
    protected final <T extends EventResult> T getEventResult() throws SystemException {
        return getEventResult(StandardEventResult.class);
    }

    /**
     * event処理結果生成.
     * 
     * @param <T> 処理結果クラス
     * @param _resultClass 処理結果クラス
     * @return 処理結果インスタンス
     * @throws SystemException 予期せぬ错误発生時
     */
    protected final <T extends EventResult> T getEventResult(Class<? extends EventResult> _resultClass)
                    throws SystemException {

        // 初めての生成
        if (this.eventResult == null) {
            this.eventResult = ReflectUtils.newInstance(_resultClass);
        }

        // 生成済みインスタンスを返す
        return (T) this.eventResult;

    }

    /**
     * event処理結果生成.
     * 
     * @param _form 格納するform
     * @return 処理結果インスタンス
     * @throws SystemException 予期せぬ错误発生時
     */
    protected final StandardEventResult getEventResult(AbstractForm _form) throws SystemException {

        // オブジェクト取得
        StandardEventResult result_ = this.getEventResult();

        // form設定
        result_.setForm(_form);

        // 返却
        return result_;

    }

    /**
     * 画面formを設定します
     * 
     * @param _form 画面form
     */
    protected final void setForm(StandartForm _form) throws SystemException {
        // オブジェクト取得
        StandardEventResult result_ = this.getEventResult();

        // form設定
        result_.setForm(_form);
    }

    /**
     * Bean形式クラスのオブジェクト間でプロパティ値をコピーする。
     * <p>
     * Bean形式クラスからBean形式クラスへ同名のプロパティへ値をコピーする データベース取得結果の Modelオブジェクトからコントローラオブジェクトへの値コピーに利用する。<br>
     * <ul>
     * <li>コピー対象が存在しない場合は、コピーが行われない。</li>
     * <li>データ型が異なる場合は、コピー対象プロパティのデータ型に併せて変換される。</li>
     * <li>Map形式の場合もコピーが可能。その場合、キー名にてマッピングが行われる。</li>
     * </ul>
     * </p>
     * 
     * @param _dest コピー先のBean形式クラス
     * @param _origenal コピー元のBean形式クラス
     * @throws EventSystemException 以下の場合に例外がスローされる
     *             <ul>
     *             <li>コピー先のアクセッサにアクセスできない場合</li>
     *             <li>コピー先のアクセッサメソッドが例外をスローした場合</li>
     *             </ul>
     * @since 1.0
     */
    protected final void copyProperties(Object _dest, Object _origenal) throws EventSystemException {
        try {
            BeanUtils.copyProperties(_dest, _origenal);

        } catch (IllegalAccessException e) {
            // アクセッサにアクセスできない場合
            throw new EventSystemException("オブジェクト間のプロパティ値のコピーに失敗しました。", e);
        } catch (InvocationTargetException e) {
            // アクセッサメソッドが例外をスローした場合
            throw new EventSystemException("オブジェクト間のプロパティ値のコピーに失敗しました。", e);
        }
    }

    /**
     * ファイルダウンロード.
     * 
     * @param _filePath ダウンロードファイルのパス
     * @param _tempFileName 一時のファイル名
     * @param _fileName ダウンロード時のファイル名
     * @throws SystemException 予期せぬ错误発生時
     */
    protected final void connectDownload(String _filePath, String _tempFileName, String _fileName)
                    throws SystemException {

        // 処理結果に标识設定
        StandardEventResult result_ = this.getEventResult();

        // 标识ON
        result_.setIsDownload(true);
        // ファイルパス
        result_.setDownloadPath(ServiceUtils.getAbsolutePath(_filePath, _tempFileName));
        // ファイル名
        result_.setDownloadFile(_fileName);

    }

    /**
     * ファイルダウンロード.
     * 
     * @param _tempFileName 一時のファイル名
     * @throws SystemException 予期せぬ错误発生時
     */
    protected final void connectDownload(String _tempFileName) throws SystemException {

        // 処理結果に标识設定
        StandardEventResult result_ = this.getEventResult();

        // 标识ON
        result_.setIsDownload(true);
        // ファイルパス
        result_.setDownloadPath(ServiceUtils.getAbsolutePath(_tempFileName));
        // ファイル名
        result_.setDownloadFile(FileUtils.getFileName(_tempFileName));
    }

    /**
     * 登録完了画面ダイアログ表示（action起動指定無し）.
     * 
     * @param _message メッセージ
     * @throws SystemException 予期せぬ错误発生時
     */
    protected final void connectCompleteDialog(String _message) throws SystemException {
        connectCompleteDialog(_message, ActionInfo.NON_ACTION);
    }

    /**
     * 登録完了画面ダイアログ表示.
     * 
     * @param _message メッセージ
     * @param _okAction ダイアログ「OK」押下時action
     * @throws SystemException 予期せぬ错误発生時
     */
    protected final void connectCompleteDialog(String _message, ActionInfo _okAction) throws SystemException {

        // 処理結果に标识設定
        StandardEventResult result_ = this.getEventResult();

        // 登録完了标识ON
        result_.setIsCompleteDialog(true);

        // メッセージ
        result_.setMessages(Arrays.asList(_message));

        // action
        result_.setActionInfo(_okAction);

    }

    /**
     * セッションにオブジェクトを保存.
     * 
     * @param <T> 保存オブジェクトの型
     * @param _sessionKey セッションキー
     * @param _target 保存するオブジェクト
     * @return 保存したオブジェクト（_targetと同値）
     */
    protected final <T> T storeSession(String _sessionKey, T _target) {
        return ServiceUtils.putSession(BIZ_SESSION_KEY.concat(_sessionKey), _target);
    }

    /**
     * セッションからオブジェクトを取得.
     * 
     * @param <T> 保存オブジェクトの型
     * @param _sessionKey セッションキー
     * @return 保存したオブジェクト
     */
    protected final <T> T loadSession(String _sessionKey) {
        return ServiceUtils.getSession(BIZ_SESSION_KEY.concat(_sessionKey));
    }

    /**
     * セッションからオブジェクトを削除.
     * 
     * @param <T> 保存オブジェクトの型
     * @param _sessionKey セッションキー
     * @return 削除したオブジェクト
     */
    protected final <T> T removeSession(String _sessionKey) {
        return ServiceUtils.removeSession(BIZ_SESSION_KEY.concat(_sessionKey));
    }

    /**
     * AJAX処理判定.
     * 
     * @return TRUE:当処理はAJAX呼出
     */
    private Boolean isCallAjaxAction() {
        // action名により判定
        return ActionContext.getContext().getName().toLowerCase().endsWith("json");
    }

    /**
     * 他用户更新チェック（最終更新日比較）.
     * 
     * @param _fieldID 最終更新日が格納されているコントロールのID
     * @param _latestRecordDate データベースから読み込んだ最新の最終更新日
     * @param _formRecordDate formで保持していた最終更新日
     * @return 他用户が更新している場合は true
     */
    protected boolean isOtherUpd(String _fieldID, Timestamp _latestRecordDate, String _formRecordDate) {

        // タイムスタンプを文字列に変換
        String timeStamp_ = TimestampUtils.formatUpd(_latestRecordDate);

        // 他用户更新無し＝処理続行可能
        if (CheckUtils.isEqual(timeStamp_, _formRecordDate)) {
            return false;
        }

        // セッションに保存（この内容は PopUpErrorタグのダイアログ（dialog.jsp）で取り出して削除する）
        // PKG強制更新削除のため、2012/06/15　gl 修正
        // ServiceUtils.putSession(DbExclusiveChainInfo.SESSION_KEY, new DbExclusiveChainInfo(_fieldID, timeStamp_,
        // _formRecordDate));

        // 処理続行不可能
        return true;

    }

    /**
     * 错误メッセージ画面で「はい」の選択
     * 
     * @param okKey 「はい」の選択 key
     * @param _form
     * @param _okAction
     * @param msg
     * @throws EventApplicationException
     */
    protected void clickOkIsEnd(int okKey, AbstractForm _form, ActionInfo _okAction, String msg)
                    throws EventApplicationException {
        if (okKey <= 0) {
            okKey = AbstractForm.OK_EVENT__KEY1;
        }

        _form.setIsClickOkKey(okKey);

        if (_form.getIsClickOkFg() < okKey) {
            this.addErrorMessage(msg);
            this.errorEnd("_clickOkEvent", _okAction, ActionInfo.NON_ACTION);
        }

    }

    /**
     * 错误メッセージ画面で「はい」の選択(データ不整合時用)
     * 
     * @param okKey 「はい」の選択 key
     * @param _form
     * @param _okAction
     * @param msg
     * @throws EventApplicationException
     */
    protected void clickOkIsEnd(int okKey, AbstractForm _form, ActionInfo _okAction, String msg,
                    List<DbExclusiveChainListInfo> dataList) throws EventApplicationException {
        if (okKey <= 0) {
            okKey = AbstractForm.OK_EVENT__KEY1;
        }

        _form.setIsClickOkKey(okKey);

        if (_form.getIsClickOkFg() < okKey) {
            this.addErrorMessage(msg);
            // 不整合時のデータ的设定
            if (dataList != null) {
                ServiceUtils.putSession(DbExclusiveChainListInfo.SESSION_KEY, dataList);
            }
            this.errorEnd("_clickOkEvent", _okAction, ActionInfo.NON_ACTION);
        }

    }

    /**
     * 他ロジッククラス取得.
     * 
     * @param <T> ロジッククラス型
     * @param _logicClass ロジッククラス
     * @return 取得したコンポーネント
     */
    protected <T extends StandardBiz> T getLogic(Class<T> _logicClass) {
        return ServiceUtils.getComponent(_logicClass);
    }

    /**
     * 件数チェック処理 チェック错误が発生する場合、event処理を終了する
     * 
     * @param _form form
     * @param _count 件数
     * @throws EventValidateException　eventバリデーション例外をスローする
     */
    protected final void checkCount(AbstractForm _form, long _count) throws EventValidateException {
        int searchCountJogen = NumberUtils.toInt(LeshanConstantsIF.SEARCH_COUNT_JOGEN);
        // 0件の場合、错误を発生しないように
        if (_count == 0) {
            // 错误情報的设定
            errorMessages.add(MessageUtils.getMessage("W30001"));
        }
        if (_count > searchCountJogen) {
            // 错误情報的设定
            errorMessages.add(MessageUtils.getMessage("W30002"));
        }
        if (hasError()) {
            throw new EventValidateException("event処理でチェック错误が発生しました。", errorMessages);
        }
        // 最終ページに表示するデータが存在しなくなった場合は、１つ前のページに戻る
        if (_form.getPageStartRowNo() > _count) {
            int pageStartRowNo = (int) (((_count - 1) / _form.getPageSize()) * _form.getPageSize()) + 1;
            // ページスタートNO
            _form.setPageStartRowNo(pageStartRowNo);
        }

        // 件数を設定
        _form.setRecordCount(_count);
    }
}
