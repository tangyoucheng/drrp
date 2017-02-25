/*
 * Copyright(c) 2011 
 */
package cn.com.prescription.framework.common.listener;

import java.io.File;
import java.util.ResourceBundle;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import cn.com.prescription.framework.StandardConstantsIF;
import cn.com.prescription.framework.util.CheckUtils;
import cn.com.prescription.framework.util.FileUtils;
import cn.com.prescription.framework.util.LogUtils;

/**
 * コンテキストリスナ.
 * 
 * @author nttdc
 */
public class ContextListener implements ServletContextListener {

    /**
     * コンテキスト初期化.
     * 
     * @param _event event
     */
    public void contextInitialized(ServletContextEvent _event) {

        try {

            // 系统関連ファイル保存フォルダを作成する
            ResourceBundle pattern_ = ResourceBundle.getBundle("sys_config");
            for (String key : pattern_.keySet()) {
                if (!CheckUtils.isEmpty(pattern_.getString(key))
                                && pattern_.getString(key).contains("/opt/StorageData")) {
                    if (!new File(pattern_.getString(key)).exists()) {
                        new File(pattern_.getString(key)).mkdirs();
                    }
                }
            }
            System.setProperty("log.system.path", StandardConstantsIF.KYOTU_SYSTEM_RELATED_LOG_SAVE_FOLDER_ROOT_PATH);
            System.setProperty("log.run.path", StandardConstantsIF.KYOTU_EXECUTE_LOG_SAVE_FOLDER_ROOT_PATH);
            System.setProperty("log.operate.path", StandardConstantsIF.KYOTU_SOSA_LOG_SAVE_FOLDER_ROOT_PATH);

        } catch (Exception e) {
            // ログ出力
            LogUtils.error(e.getMessage(), e);
        } finally {
            // 生成時は特になにもしない
            LogUtils.debug("contextInitialized: ".concat(_event.getServletContext().getRealPath("")).concat(" - ")
                .concat(_event.getServletContext().getServerInfo()));
        }

    }

    /**
     * コンテキスト終了.
     * 
     * @param _event event
     */
    public void contextDestroyed(ServletContextEvent _event) {
        // テンポラリフォルダ：CSVファイル一時保存フォルダ
        removeTempFile(StandardConstantsIF.KYOTU_CSV_FILE_TMP_SAVE_FOLDER);
        // テンポラリフォルダ：顔写真イメージ一時保存フォルダ
        // removeTempFile(StandardConstantsIF.KYOTU_KAO_PICTURE_TMP_HOZON_FOLDER);
        // テンポラリフォルダ：校章イメージ一時保存フォルダ
        // removeTempFile(StandardConstantsIF.KYOTU_KOSYO_IMAGE_TMP_HOZON_FOLDER);
        // テンポラリフォルダ：出力ファイル一時保存フォルダ
        removeTempFile(StandardConstantsIF.KYOTU_OUT_FILE_TMP_SAVE_FOLDER);
        // 終了
        LogUtils.debug("contextDestroyed: ".concat(_event.getServletContext().getRealPath("")));
    }

    /**
     * 作業ファイルを削除.
     * 
     * @param _parentPath 削除対象フォルダパス
     */
    private void removeTempFile(String _parentPath) {
        // ファイルリストを取得
        File[] files_ = new File(FileUtils.getAbsolutePath(_parentPath)).listFiles();
        // 削除
        if (files_ != null) {
            for (File file_ : files_) {
                // ログ
                LogUtils.debug("delete temporary file: ".concat(file_.getName()));
                // 削除
                file_.delete();
            }
        }
    }

}
