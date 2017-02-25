/*
 * Copyright(c) 2016 
 */
package cn.com.prescription.framework.util;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.com.prescription.framework.StandardConstantsIF;
import cn.com.prescription.framework.common.session.UserSessionUtils;
import cn.com.prescription.framework.exception.SystemException;

import org.apache.log4j.RollingFileAppender;
import org.apache.log4j.helpers.LogLog;
import org.apache.log4j.spi.LoggingEvent;

/**
 * ログファイルを、日付＋サイズでローテートするLog4J用アペンダ
 * 
 * @author bpchikazawa
 */
public class DailyOperateRollingAppender extends RollingFileAppender {

    /** 最終書込ファイル */
    private String lastFile = null;

    /** ファイル名パターン */
    private Pattern PATTERN_FILE_NAME = Pattern.compile("^((\\d{8})_(.+?)_)?+(.+?)(\\.\\d+?)??$");

    /**
     * ログ追加時処理.
     * 
     * @param _event ログevent
     */
    @Override
    protected void subAppend(LoggingEvent _event) {

        // ファイル生成
        this.createNewDateLog();

        // 出力
        super.subAppend(_event);

    }

    /**
     * 新規ファイル生成処理.
     */
    private void createNewDateLog() {
        try {

            // 現在日付を取得
            Date nowDate_ = DateUtils.getSysDateFromApServer();

            // ファイル名
            String fileName_ = this.getDateFileName(nowDate_);
            // 前回書込みファイルと同一
            if (fileName_.equals(this.lastFile)) {
                return;
            }

            // 一旦クローズ
            this.closeFile();

            // 不要ファイルがあれば消す
            // deleteConfigFile();

            // 古いファイルがあれば消す
            deleteOldFile(nowDate_);

            // ファイル名保存
            this.lastFile = fileName_;

            // オープン
            this.setFile(fileName_, new File(fileName_).exists(), this.bufferedIO, this.bufferSize);

        } catch (IOException e) {
            // コンソールログ
            LogLog.debug(e.getMessage(), e);
            // ファイル名クリア
            this.lastFile = null;
        }
    }

    // 2013/01/24 未使用メッソドをコメントする
    // /**
    // * 不要ファイル削除
    // */
    // private void deleteConfigFile() {
    //
    // // ファイルオブジェクト
    // File file_ = new File(this.fileName);
    //
    // // ファイル名先頭に日付がついている場合消す
    // String deleteFile_ = getOriginFileName(file_.getName());
    //
    // // ファイルオブジェクト
    // file_ = new File(file_.getParent(), deleteFile_);
    //
    // // 存在する場合削除
    // if (file_.exists()) {
    // file_.delete();
    // }
    //
    // // ダミーログ
    // file_ = new File(file_.getParent(), "dummy.log");
    //
    // // 存在する場合削除
    // if (file_.exists()) {
    // file_.delete();
    // }
    //
    // }

    /**
     * 古いファイル削除
     * 
     * @param _nowDate 今日の日付
     * @param _schema スキーマ名
     */
    private void deleteOldFile(Date _nowDate) {
        try {

            // ログ保存期間
            int saveRange_ = NumberUtils.toInt(StandardConstantsIF.KYOTU_SOSA_LOG_SAVE_KIKAN);

            // フォルダ中のファイル一覧
            File[] files_ = new File(new File(this.fileName).getParent()).listFiles();

            // 全てチェック
            for (File file_ : files_) {

                // ファイルでない
                if (!file_.isFile()) {
                    continue;
                }

                // ファイル名チェック
                Matcher matcher_ = PATTERN_FILE_NAME.matcher(file_.getName());

                // ファイル名パターン不一致
                if (!matcher_.matches()) {
                    continue;
                }

                // ダミーファイルをスキップ
                if (CheckUtils.isEmpty(matcher_.group(1)) || CheckUtils.isEmpty(matcher_.group(2))
                                || CheckUtils.isEmpty(matcher_.group(3))) {
                    continue;
                }

                // ファイル名の日付
                Date fileDate_ = DateUtils.parseYMD(matcher_.group(2));

                // 保存期間を足す
                fileDate_ = DateUtils.addMonths(fileDate_, saveRange_);

                // 今日の日付以降ならOK
                if (0 <= fileDate_.compareTo(_nowDate)) {
                    continue;
                }

                // ファイルを消す
                file_.delete();

            }

        } catch (SystemException e) {
            // コンソールログ
            LogLog.debug(e.getMessage(), e);
        }
    }

    // 2013/01/24 未使用メッソドをコメントする
    // /**
    // * @param _fileName ファイル名
    // * @return 本来のファイル名
    // */
    // private String getOriginFileName(String _fileName) {
    //
    // // ファイル名チェック
    // Matcher matcher_ = PATTERN_FILE_NAME.matcher(new File(_fileName).getName());
    //
    // // ファイル名パターン不一致
    // matcher_.matches();
    //
    // // ファイル名取得
    // return matcher_.group(4);
    //
    // }

    /**
     * @param _logDate ログ日付
     * @return 日付ファイル名
     */
    private String getDateFileName(Date _logDate) {

        // 日付を文字列に変換
        String dateString_ = DateUtils.format(_logDate, StandardConstantsIF.DATE_FORMAT_YYYYMMDD);

        // 日付＋スキーマ名を設定
        String newFile_ = StringUtils.join(new Object[] { dateString_, ".log" }, '_');

        // ファイル名生成
        return new File(StandardConstantsIF.KYOTU_SOSA_LOG_SAVE_FOLDER_ROOT_PATH, newFile_).getAbsolutePath();

    }

}