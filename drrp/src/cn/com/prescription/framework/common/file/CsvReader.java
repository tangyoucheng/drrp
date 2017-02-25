/*
 * Copyright(c) 2011 
 */
package cn.com.prescription.framework.common.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import cn.com.prescription.framework.exception.SystemException;
import jp.sourceforge.csvparser.BasicCSVUtility;
import jp.sourceforge.csvparser.CSVReader;
import jp.sourceforge.csvparser.CSVUtility;

/**
 * 校務支援系统仕様のCSV読込クラス.
 * 
 * @author bpchikazawa
 */
public class CsvReader {

    /** ユーティリティ */
    private CSVUtility utility = new BasicCSVUtility();
    /** リーダー */
    private CSVReader reader = null;

    /**
     * コンストラクタ.
     * 
     * @param _file ファイルオブジェクト.
     * @param _fileCharSet ファイル文字コード
     * @throws ApplicationException 文字コード不正
     */
    public CsvReader(File _file, String _fileCharSet) throws SystemException {
        try {

            // リーダーを準備
            reader = utility.createCSVReader(new BufferedReader(new InputStreamReader(new FileInputStream(_file),
                _fileCharSet)));

        } catch (UnsupportedEncodingException e) {
            // 错误
            throw new SystemException("ファイルの文字コードが不正です。Shift_JIS で保存しなおして再試行して下さい。", e);
        } catch (FileNotFoundException e) {
            // 错误
            throw new SystemException("ファイルパスの取得が失敗しました。", e);
        }
    }

    /**
     * ファイルから1行を読み込み、項目分割したリストを返します.
     * 
     * @return 項目が設定されたリスト（ファイル最終に達した場合は null）
     * @throws SystemException 予期せぬ例外発生時
     */
    public String[] readLine() throws SystemException {
        try {
            // 1行読込
            return reader.readRecord();
        } catch (IOException e) {
            // 系统例外
            throw new SystemException(e);
        }
    }

    /**
     * ファイルから1行を読み込み、項目分割したリストを返します.
     * 
     * @return 項目が設定されたリスト（ファイル最終に達した場合は null）
     * @throws SystemException 予期せぬ例外発生時
     */
    public List<String[]> readAll() throws SystemException {

        // リスト
        List<String[]> result_ = new ArrayList<String[]>();

        // 1行レコード
        String[] record_ = null;

        // リストに格納する
        while ((record_ = readLine()) != null) {
            result_.add(record_);
        }

        // 返却
        return result_;

    }

    /**
     * ファイルクローズ.
     * 
     * @throws SystemException
     */
    public void close() throws SystemException {
        try {
            reader.close();
        } catch (IOException e) {
            throw new SystemException(e);
        }
    }

}
