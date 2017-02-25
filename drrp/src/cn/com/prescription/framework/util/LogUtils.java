/*
 * Copyright(c) 2011 
 */
package cn.com.prescription.framework.util;

import java.io.File;
import java.io.FilenameFilter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;

import cn.com.prescription.framework.StandardConstantsIF;
import cn.com.prescription.framework.action.ActionMessages;
import cn.com.prescription.framework.common.session.UserSessionInfo;
import cn.com.prescription.framework.common.session.UserSessionUtils;
import cn.com.prescription.framework.exception.SystemException;

/**
 * ログ出力ユーティリティ.
 * 
 * @author nttdc
 */
public final class LogUtils {

    /** 系统の改行コード */
    private static final String LINE_SEPARATOR = System.getProperty("line.separator");

    /** 基盤用ファイル名チェックパターン */
    private static Pattern FILE_NAME_FRAMEWORK = Pattern.compile("([0-9]{8})_[a-zA-Z0-9]+?\\.log(\\.[0-9]+?)??");

    /** アプリ用ファイル名チェックパターン */
    private static Pattern FILE_NAME_APP = Pattern
        .compile("(([a-zA-Z0-9-]+?)_)??([a-zA-Z0-9-]+?)_([a-zA-Z0-9]+?)_([0-9]{8})_([0-9]{6})_([0-9]{3})\\.log");

    /** 基盤用ロガー */
    public static Logger loggerFramework = LogManager.getLogger("framework");

    /** 操作ロガー */
    private static Logger loggerOperate = LogManager.getLogger("operate");

    /** 错误用ロガー */
    private static Logger loggerException = LogManager.getLogger("exception");

    /** タイムスタンプ */
    private Timestamp timeStamp = null;

    /** ログフォルダ */
    private String logFolder = null;

    /** ログファイル名 */
    private String logFile = null;

    /** ログメッセージ */
    private List<String> logContent = new ArrayList<String>();

    /** ロック */
    private static final Object LOCK_OBJECT = new Object();

    /**
     * /** LogUtils 的构造。
     */
    private LogUtils() {
    }

    /**
     * インスタンスを作成
     * 
     * @return インスタンス.
     */
    public static LogUtils newInstance() {
        // インスタンスを生成
        return new LogUtils();
    }

    /**
     * アプリケーション汎用ログ出力開始.
     * 
     * @param _screenId 画面ID
     */
    public void beginApp(String _screenId) {

        // ログリストをクリア
        logContent.clear();

        // 開始日時を取得
        timeStamp = TimestampUtils.getSysTimestampFromApServer();

        // ログフォルダ
        logFolder = StandardConstantsIF.KYOTU_APP_HANYO_LOG_SAVE_FOLDER;

        // ファイル名
        logFile = StringUtils.join(new String[] { _screenId, "_", UserSessionUtils.getUserId(), "_",
                        // TimestampUtils.format(timeStamp, StandardConstantsIF.DATE_FORMAT_LOG_FILE), ".log" });
                        TimestampUtils.format(timeStamp, StandardConstantsIF.DATE_FORMAT_BATCH_LOG_FILE), ".log" });

    }

    /**
     * バッチログ出力開始.
     * 
     * @param _screenId 画面ID
     * @throws SystemException
     */
    public void beginAppBatch(String _screenId) throws SystemException {
        beginAppBatch(_screenId, "");
    }

    /**
     * バッチログ出力開始.
     * 
     * @param _screenId 画面ID
     * @param _beginMessage 開始メッセージ
     * @throws SystemException
     */
    public void beginAppBatch(String _screenId, String _beginMessage) throws SystemException {
        // 初期化
        beginApp(_screenId);
        // ログフォルダを「バッチログ保存フォルダ」に変更
        logFolder = StandardConstantsIF.KYOTU_BATCH_LOG_SAVE_FOLDER;
        // 開始メッセージ
        if (!CheckUtils.isEmpty(_beginMessage)) {
            appLog(_beginMessage);
        }
    }

    /**
     * ログ内容出力.
     * 
     * @param _logs ログメッセージリスト
     */
    public void appLog(Collection<String> _logs) {
        for (String log_ : _logs) {
            appLog(log_);
        }
    }

    /**
     * ログ内容出力 (例外).
     * 
     * @param _throwable 例外
     */
    public void appLog(Throwable _throwable) {
        appLog(expandThrowable(_throwable));
    }

    /**
     * ログ内容出力.
     * 
     * @param _log ログメッセージ
     */
    public void appLog(String _log) {
        // 溜めすぎ防止
        if (logContent.size() > 100) {
            endApp();
        }
        // ログ追加
        logContent.add(LINE_SEPARATOR.length() == 2 ? _log : _log.concat("\r"));
    }

    /**
     * ログ出力終了.
     * 
     * @return 出力したファイル名.
     */
    public String endApp() {

        // ファイルパス生成
        String filePath_ = ServiceUtils.getAbsolutePath(logFolder, logFile);

        // ロガーのメンバに設定する為、同期を取る
        synchronized (LOCK_OBJECT) {
            // ロガー取得
            Logger logger_ = LoggerContext.getContext().getLogger("app");
            // アペンダを取得 TODO 20170222
            // FileAppender appender_ = (FileAppender) logger_.getAppender("app");
            // アペンダにファイル名設定
            // appender_.setFile(filePath_);
            // アペンダを初期化
            // appender_.activateOptions();
            // ログ出力
            for (String write_ : logContent) {
                logger_.info(write_);
            }
        }

        // ログ内容をクリア
        logContent.clear();

        // ファイル名を返す
        return logFile;

    }

    /**
     * 取込ログ出力開始.
     * 
     * @param _screenId 画面ID
     * @param _screenName 画面名
     */
    public void beginImport(String _screenId, String _screenName) {

        // ログ開始
        beginApp(_screenId);

        // ログフォルダ
        logFolder = StandardConstantsIF.KYOTU_TORIKOMI_LOG_HOZON_FOLDER;

        // ヘッダ情報を出力
        StringBuilder log_ = new StringBuilder();
        log_.append("<");
        log_.append(_screenName);
        log_.append("（");
        log_.append("実行用户ID：");
        log_.append(UserSessionUtils.getUserId());
        log_.append("）");
        log_.append(">");
        log_.append(" ");
        log_.append(TimestampUtils.formatUpd(timeStamp));

        // ログ追加
        appLog(log_.toString());

    }

    /**
     * 取込ログ出力.
     * 
     * @param _log ログメッセージ
     */
    public void importLog(String _log) {
        appLog("*,".concat(_log));
    }

    /**
     * 取込ログ出力.
     * 
     * @param _line 取込行番号
     * @param _log ログメッセージ
     */
    public void importLog(Long _line, String _log) {
        appLog("行".concat(String.valueOf(_line)).concat(",").concat(_log.replaceAll("<br>", "")));
    }

    /**
     * 取込ログ出力.
     * 
     * @param _line 取込行番号
     * @param _log ログメッセージ
     */
    public void importLog(Long _line, ActionMessages messages) {
        LinkedHashMap<String, LinkedList<String>> msg = messages.getMessages();
        Iterator<String> ite = msg.keySet().iterator();

        while (ite.hasNext()) {
            List<String> msgList = msg.get(ite.next());
            for (int i = 0; i < msgList.size(); i++) {
                if (_line > 0) {
                    importLog(_line, msgList.get(i));
                } else {
                    importLog(msgList.get(i));
                }
            }
        }
    }

    /**
     * 取込ログ出力.
     * 
     * @param _line 取込行番号
     * @param _log ログメッセージ
     */
    public void importLog(Long _line, ActionMessages messages, String _record) {
        LinkedHashMap<String, LinkedList<String>> msg = messages.getMessages();
        Iterator<String> ite = msg.keySet().iterator();

        while (ite.hasNext()) {
            List<String> msgList = msg.get(ite.next());
            for (int i = 0; i < msgList.size(); i++) {
                if (_line > 0) {
                    importLog(_line, _record + msgList.get(i));
                } else {
                    importLog(msgList.get(i));
                }
            }
        }
    }

    /**
     * 取込ログ出力終了.
     * 
     * @param _total 処理件数
     * @param _successful 成功件数
     * @param _failed 失敗件数
     * @return 出力したファイル名.
     */
    public String endImport(Long _total, Long _successful, Long _failed) {

        StringBuilder log_ = new StringBuilder();

        // ヘッダ情報を出力
        log_.append("<");
        log_.append("取込処理件数：");
        log_.append(_total);
        log_.append("件　成功件数：");
        log_.append(_successful);
        log_.append("件　失敗件数：");
        log_.append(_failed);
        log_.append("件>");
        log_.append(" ");
        log_.append(TimestampUtils.formatUpd(TimestampUtils.getSysTimestampFromApServer()));

        // ログ追加
        appLog(log_.toString());

        // ファイル名を返す
        return endApp();

    }

    /**
     * テンポラリログ出力
     * 
     * @param _screenId 画面ID
     * @throws SystemException 予期せぬ错误発生時
     */
    public void beginTemp(String _screenId) throws SystemException {
        // 初期化
        beginApp(_screenId);
        // ログフォルダを「テンポラリフォルダ」に変更
        logFolder = StandardConstantsIF.KYOTU_OUT_FILE_TMP_SAVE_FOLDER;
        // ログファイル名
        logFile = FileUtils.getTemporaryFileNameSyncExt("*.log");
    }

    /**
     * メソッドの開始時にログを出力する。
     * 
     * @param _params パラメータ
     */
    public static void start(Object... _params) {

        // 処理が重いので事前に出力レベルをチェック
        if (!loggerFramework.isEnabled(Level.DEBUG)) {
            return;
        }

        // 開始ログ
        loggerFramework.debug(expandString("[START]", sessionInfo(), quoteCollection(_params)));

    }

    /**
     * メソッドの終了時にログを出力する。
     * 
     * @param _params パラメータオブジェクト
     */
    public static void end(Object... _params) {

        // 処理が重いので事前に出力レベルをチェック
        if (!loggerFramework.isEnabled(Level.DEBUG)) {
            return;
        }

        // 終了ログ
        loggerFramework.debug(expandString("[END  ]", sessionInfo(), quoteCollection(_params)));

    }

    /**
     * メソッドの終了時に処理実行に経過した時間を出力する。
     * 
     * @param _className クラス名
     * @param _methodName メソッド名
     * @param _startMillis 開始時刻（ミリ秒）
     * @param _endMillis 終了時刻（ミリ秒）
     */
    public static void process(Object _className, Object _methodName, long _startMillis, long _endMillis) {
        process(_className, _methodName, _startMillis, _endMillis, "");
    }

    /**
     * メソッドの終了時に処理実行に経過した時間を出力する。
     * 
     * @param _className クラス名
     * @param _methodName メソッド名
     * @param _startMillis 開始時刻（ミリ秒）
     * @param _endMillis 終了時刻（ミリ秒）
     * @param _message メッセージ
     */
    public static void process(Object _className, Object _methodName, long _startMillis, long _endMillis,
                    String _message) {

        // 経過時間を取得
        String time_ = DateUtils.pireod(_startMillis, _endMillis, "mm'm'ss's'SSS'ms'");

        // 処理が重いので事前に出力レベルをチェック
        if (loggerFramework.isEnabled(Level.DEBUG)) {
            // ログ出力
            loggerFramework.debug(
                expandString("[PROC ]", sessionInfo(), quoteCollection(_className, _methodName), time_, _message));
            return;
        }

        // 処理が重いので事前に出力レベルをチェック
        if (loggerFramework.isEnabled(Level.INFO)) {
            // ログ出力
            loggerFramework
                .info(expandString("[PROC ]", sessionInfo(), quoteCollection(_className, _methodName), time_));
            return;
        }

    }

    /**
     * 任意の箇所に埋め込み、デバッグ情報を出力する。
     * 
     * @param _message デバッグメッセージ
     */
    public static void debug(String _message) {
        // 処理が重いので事前に出力レベルをチェック
        if (!loggerFramework.isEnabled(Level.DEBUG)) {
            return;
        }
        // ログ出力
        loggerFramework.debug(expandString("debug", sessionInfo(), _message));
    }

    /**
     * 任意の箇所に埋め込み、デバッグ情報を出力する。
     * 
     * @param _message デバッグメッセージ
     */
    public static void debugOperate(String _message, boolean _flg) {
        // 処理が重いので事前に出力レベルをチェック
        if (!loggerOperate.isEnabled(Level.INFO)) {
            return;
        }
        if (_flg == true) {
            // ログ出力
            loggerOperate.info(expandString(TimestampUtils.formatUpd(TimestampUtils.getSysTimestampFromApServer()),
                sessionInfoOprate(), _message));
        } else {
            // ログ出力
            loggerOperate.info(_message);
        }

    }

    /**
     * 任意の箇所に埋め込み、デバッグ情報を出力する。
     * 
     * @param _message デバッグメッセージ
     * @param _throwable 例外オブジェクト
     */
    public static void debug(String _message, Throwable _throwable) {
        // 処理が重いので事前に出力レベルをチェック
        if (!loggerFramework.isEnabled(Level.DEBUG)) {
            return;
        }
        // ログ出力
        loggerFramework.debug(expandString("debug", sessionInfo(), _message, expandThrowable(_throwable)));
    }

    /**
     * 任意の箇所に埋め込み、インフォメーション情報を出力する。
     * 
     * @param _message デバッグメッセージ
     */
    public static void info(String _message) {
        // 処理が重いので事前に出力レベルをチェック
        if (!loggerFramework.isEnabled(Level.INFO)) {
            return;
        }
        // ログ出力
        loggerFramework.info(expandString("info", sessionInfo(), _message));
    }

    /**
     * 任意の箇所に埋め込み、警告情報を出力する。
     * 
     * @param _message デバッグメッセージ
     */
    public static void warn(String _message) {
        // 処理が重いので事前に出力レベルをチェック
        if (!loggerFramework.isEnabled(Level.WARN)) {
            return;
        }
        // ログ出力
        loggerFramework.warn(expandString("warn", sessionInfo(), _message));
    }

    /**
     * 任意の箇所に埋め込み、警告情報を出力する。
     * 
     * @param _message デバッグメッセージ
     * @param _throwable Throwable
     */
    public static void warn(String _message, Throwable _throwable) {
        // 処理が重いので事前に出力レベルをチェック
        if (!loggerFramework.isEnabled(Level.WARN)) {
            return;
        }
        // ログ出力
        loggerFramework.warn(expandString("warn", sessionInfo(), _message, expandThrowable(_throwable)));
    }

    /**
     * 例外発生箇所に埋め込み、错误情報を出力する。
     * 
     * @param _message デバッグメッセージ
     * @param _throwable Throwable
     */
    public static void error(String _message, Throwable _throwable) {
        // ログ出力
        loggerException.error(expandString(sessionInfo(), _message, expandThrowable(_throwable)));
    }

    /**
     * バッファにセッションの用户情報を出力します.
     * 
     * @return 追加後のバッファ
     */
    private static Object[] sessionInfo() {

        // バッファ
        List<Object> buffer_ = new ArrayList<Object>();

        // セッション用户情報を取得
        UserSessionInfo userInfo_ = UserSessionUtils.getUserSessionInfo();

        // セッション無し
        if (userInfo_ == null) {
            // 列数を合わせる為に空要素を追加
            buffer_.add("[");
            buffer_.add("");
            buffer_.add("]");
        } else {
            // 用户ID
            buffer_.add("[" + StringUtils.defaultString(userInfo_.getUserId()));
            // IPアドレス
            buffer_.add(StringUtils.defaultString(userInfo_.getUserIp()) + "]");
        }

        // 返却
        return buffer_.toArray();

    }

    /**
     * バッファにセッションの用户情報を出力します.
     * 
     * @return 追加後のバッファ
     */
    private static Object[] sessionInfoOprate() {

        // バッファ
        List<Object> buffer_ = new ArrayList<Object>();

        // セッション用户情報を取得
        UserSessionInfo userInfo_ = UserSessionUtils.getUserSessionInfo();

        // セッション無し
        if (userInfo_ == null) {
            // 列数を合わせる為に空要素を追加
            buffer_.add("");
            buffer_.add("");
            buffer_.add("");
        } else {
            // 用户ID
            buffer_.add(StringUtils.defaultString(userInfo_.getUserId()));
            // 用户名
            buffer_.add(StringUtils.defaultString(userInfo_.getUserName()));
            // IPアドレス
            buffer_.add(StringUtils.defaultString(userInfo_.getUserIp()));
        }

        // 返却
        return buffer_.toArray();

    }

    /**
     * スタックトレースを原因まで展開します
     * 
     * @param _throwable 例外
     * @return 文字列
     */
    public static String expandThrowable(Throwable _throwable) {

        // 例外調整
        _throwable = adjustStack(_throwable);

        // 遡って全てのスタックを出力
        StringBuilder sb_ = new StringBuilder();
        while (_throwable != null) {
            // 改行
            sb_.append(LINE_SEPARATOR);
            // 文字列出力先
            StringWriter sw = new StringWriter();
            // スタックを出力
            _throwable.printStackTrace(new PrintWriter(sw));
            // 追加
            sb_.append(sw.toString());
            // 原因を取得
            _throwable = _throwable.getCause();
        }
        return sb_.toString();

    }

    /**
     * スタックトレースから不要な情報を削除します.
     * 
     * @param _throwable 例外
     * @return 文字列
     */
    public static Throwable adjustStack(Throwable _throwable) {

        // ルート
        Throwable root_ = _throwable;

        // 遡って全てのスタックを出力
        while (_throwable != null) {

            // 新しいスタックトレース
            List<StackTraceElement> stackTrace_ = new ArrayList<StackTraceElement>();

            // スタックを検索
            for (StackTraceElement element_ : _throwable.getStackTrace()) {

                // パッケージチェック
                if (!element_.getClassName().startsWith("cn.com.prescription.leshan.")) {
                    // JSP は出力する
                    if (!element_.getClassName().startsWith("org.apache.jsp.pages.")) {
                        continue;
                    }
                }

                // S2AOPを外す
                if (element_.getClassName().contains("$$EnhancedByS2AOP$$")) {
                    continue;
                }

                // スタック追加
                stackTrace_.add(element_);

            }

            // スタックトレースを上書き
            _throwable.setStackTrace(stackTrace_.toArray(new StackTraceElement[stackTrace_.size()]));

            // 原因を取得
            _throwable = _throwable.getCause();

        }

        // ルートを返却
        return root_;

    }

    /**
     * パラメータ配列を一次元に囲み文字した文字列を返します
     * 
     * @param _objects パラメータ
     * @return 文字列
     */
    private static String quoteCollection(Object... _objects) {

        List<Object> result_ = new ArrayList<Object>();

        // パラメータ配列ループ
        for (Object object_ : _objects) {
            if (object_ == null || !object_.getClass().isArray()) {
                // 配列でない場合は文字列として追加
                result_.add("[" + StringUtils.defaultString(object_) + "]");
            }
        }

        return StringUtils.join(result_.toArray(), ',');
    }

    /**
     * パラメータ配列を一次元に展開した文字列を返します
     * 
     * @param _objects パラメータ
     * @return 文字列
     */
    private static String expandString(Object... _objects) {
        return StringUtils.join(expandCollection(_objects).toArray(), ',');
    }

    /**
     * パラメータ配列を一次元に展開します
     * 
     * @param _objects パラメータ
     * @return リスト
     */
    private static List<Object> expandCollection(Object... _objects) {

        List<Object> result_ = new ArrayList<Object>();

        // パラメータ配列ループ
        for (Object object_ : _objects) {
            if (object_ == null || !object_.getClass().isArray()) {
                // 配列でない場合は文字列として追加
                result_.add(StringUtils.defaultString(object_));
            } else {
                // 内部要素を再帰で展開
                for (Object inner_ : (Object[]) object_) {
                    result_.addAll(expandCollection(inner_));
                }
            }
        }
        return result_;

    }

    /**
     * ログファイルリスト取得
     * 
     * @param _logFolder ログフォルダ
     * @return ログファイルリスト
     */
    public static List<File> getLogFileList(String _logFolder) {
        // リスト取得
        List<File> files_ = Arrays
            .asList(new File(ServiceUtils.getAbsolutePath(_logFolder)).listFiles(new LogFilter()));
        // ソート
        Collections.sort(files_, new LogComparator());
        // 結果を返す
        return files_;
    }

    /**
     * アプリケーションログファイルリスト取得
     * 
     * @return ログファイルリスト
     */
    public static List<File> getAppLogFileList() {
        return getLogFileList(StandardConstantsIF.KYOTU_APP_HANYO_LOG_SAVE_FOLDER);
    }

    /**
     * 取込ログファイルリスト取得
     * 
     * @return ログファイルリスト
     */
    public static List<File> getImportLogFileList() {
        return getLogFileList(StandardConstantsIF.KYOTU_TORIKOMI_LOG_HOZON_FOLDER);
    }

    /**
     * バッチログファイルリスト取得
     * 
     * @return ログファイルリスト
     */
    public static List<File> getBatchLogFileList() {
        return getLogFileList(StandardConstantsIF.KYOTU_BATCH_LOG_SAVE_FOLDER);
    }

    /**
     * 基盤ログファイル名チェック
     * 
     * @param _fileName チェック対象ファイル名
     * @return true:基盤ログファイル名
     */
    private static Boolean isFrameworkLog(String _fileName) {
        return FILE_NAME_FRAMEWORK.matcher(_fileName).matches();
    }

    /**
     * アプリケーションログファイル名チェック
     * 
     * @param _fileName チェック対象ファイル名
     * @return true:基盤ログファイル名
     */
    private static Boolean isAppLog(String _fileName) {
        return FILE_NAME_APP.matcher(_fileName).matches();
    }

    /**
     * ファイル名の日付文字列を取得
     * 
     * @param _fileName ファイル名
     * @return yyyyMMddHHmmssSSS
     */
    private static String getFileNameDate(String _fileName) {

        // フレームワークログ
        if (isFrameworkLog(_fileName)) {
            // 検索エンジン
            Matcher matcher_ = FILE_NAME_FRAMEWORK.matcher(_fileName);
            // 検索
            matcher_.matches();
            // 抽出
            return matcher_.group(1).concat("000000000");
        }

        // アプリログ
        if (isAppLog(_fileName)) {
            // 検索エンジン
            Matcher matcher_ = FILE_NAME_APP.matcher(_fileName);
            // 検索
            matcher_.matches();
            // 抽出
            return matcher_.group(5).concat(matcher_.group(6)).concat(matcher_.group(7));
        }

        // 一致しない
        return "";

    }

    /**
     * ファイル名に含まれる用户名を取得
     * 
     * @param _fileName ファイル名
     * @return 用户名
     */
    private static String getFileNameUser(String _fileName) {

        // アプリケーションログ
        if (isAppLog(_fileName)) {
            // 検索エンジン
            Matcher matcher_ = FILE_NAME_APP.matcher(_fileName);
            // 検索
            matcher_.matches();
            // 抽出
            return matcher_.group(2);
        }

        // 一致しない
        return "";

    }

    /**
     * ファイル名に含まれる機能IDを取得
     * 
     * @param _fileName ファイル名
     * @return 用户名
     */
    private static String getFileNameFunctionId(String _fileName) {

        // アプリケーションログ
        if (isAppLog(_fileName)) {
            // 検索エンジン
            Matcher matcher_ = FILE_NAME_APP.matcher(_fileName);
            // 検索
            matcher_.matches();
            // 抽出
            return matcher_.group(1);
        }

        // 一致しない
        return "";

    }

    /**
     * ログファイル抽出用フィルタ
     */
    private static class LogFilter implements FilenameFilter {

        /**
         * ファイル名フィルタ
         * 
         * @param _dir フォルダ
         * @param _name ファイル名
         * @return true:フィルタリングしない
         */
        public boolean accept(File _dir, String _name) {
            return isFrameworkLog(_name) || isAppLog(_name);
        }

    }

    /**
     * ログファイルソート用クラス.
     */
    private static class LogComparator implements Comparator<File> {

        /**
         * 比較.
         * 
         * @param _file1 比較ファイル1
         * @param _file2 比較ファイル2
         * @return -1:_file1 < _file2 , 0:_file1 == _file2 , 1:_file1 > _file2
         */
        public int compare(File _file1, File _file2) {

            // ソート結果
            int result_ = 0;

            // 日付で比較（降順で判断）
            if (result_ == 0) {
                result_ = getFileNameDate(_file2.getName()).compareTo(getFileNameDate(_file1.getName()));
            }
            // 用户名で比較
            if (result_ == 0) {
                result_ = getFileNameUser(_file1.getName()).compareTo(getFileNameUser(_file2.getName()));
            }
            // 機能IDで比較
            if (result_ == 0) {
                result_ = getFileNameFunctionId(_file1.getName()).compareTo(getFileNameFunctionId(_file2.getName()));
            }

            // 比較結果
            return result_ != 0 ? result_ : _file1.compareTo(_file2);

        }

    }

    /**
     * @return 出力タイムスタンプ
     */
    public Timestamp getTimeStamp() {
        return timeStamp;
    }

    /**
     * @return 出力先フォルダ
     */
    public String getLogFolder() {
        return logFolder;
    }

    /**
     * @return 出力ファイル名
     */
    public String getLogFile() {
        return logFile;
    }

    /**
     * @return 出力先ファイル（フルパス）
     */
    public String getLogFullPath() {
        return FileUtils.getAbsolutePath(logFolder, logFile);
    }

}
