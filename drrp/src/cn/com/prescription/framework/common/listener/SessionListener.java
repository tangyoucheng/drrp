/*
 * Copyright(c) 2011 
 */
package cn.com.prescription.framework.common.listener;

import java.io.File;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import cn.com.prescription.framework.StandardConstantsIF;
import cn.com.prescription.framework.util.FileUtils;
import cn.com.prescription.framework.util.LogUtils;

import com.opensymphony.xwork2.ActionContext;

/**
 * セッションリスナ.
 * 
 * @author nttdc
 */
public final class SessionListener implements HttpSessionListener {

    /**
     * セッション生成時.
     * 
     * @param _event セッション生成情報
     */
    public synchronized void sessionCreated(HttpSessionEvent _event) {
        // 生成時は特になにもしない
        LogUtils.debug("sessionCreated: ".concat(_event.getSession().getId()));
    }

    /**
     * セッション破棄時.
     * 
     * @param _event セッション破棄情報
     */
    public synchronized void sessionDestroyed(HttpSessionEvent _event) {
        // null チェック
        if (_event != null && _event.getSession() != null) {
            // 基盤動作チェック以外の場合
            if (!"true".equals(_event.getSession().getAttribute("isHealthCheck"))) {
                // テンポラリフォルダ：CSVファイル一時保存フォルダ
                removeTempFile(_event.getSession().getId(), StandardConstantsIF.KYOTU_CSV_FILE_TMP_SAVE_FOLDER);
                // テンポラリフォルダ：出力ファイル一時保存フォルダ
                removeTempFile(_event.getSession().getId(), StandardConstantsIF.KYOTU_OUT_FILE_TMP_SAVE_FOLDER);
            }
        }
        // セッション破棄ログ
        LogUtils.debug("sessionDestroyed: ".concat(_event.getSession().getId()));
    }

    /**
     * 当セッションでの作業ファイルを削除.
     * 
     * @param _sessionId セッションID
     * @param _parentPath 削除対象フォルダパス
     */
    private void removeTempFile(String _sessionId, String _parentPath) {

        // ファイルリストを取得
        File[] files_ = null;
        File folder_ = null;
        if (ActionContext.getContext() != null) {
            folder_ = new File(FileUtils.getAbsolutePath(_parentPath));
        } else {
            folder_ = new File(FileUtils.getRelativePath(_parentPath));
        }

        if (folder_ != null) {
            files_ = folder_.listFiles();
        }
        // 削除
        if (files_ != null) {
            for (File file_ : files_) {
                // ファイル名をチェック
                if (file_.getName().contains(_sessionId)) {
                    // ログ
                    LogUtils.debug("delete temporary file: ".concat(file_.getName()));
                    // 削除
                    file_.delete();
                }
            }
        }
    }
}
