/*
 * Copyright(c) 2011 
 */
package cn.com.prescription.framework.common.file;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.commons.lang.text.StrTokenizer;

import cn.com.prescription.framework.StandardConstantsIF;
import cn.com.prescription.framework.exception.ApplicationException;
import cn.com.prescription.framework.exception.SystemException;
import cn.com.prescription.framework.util.LogUtils;
import cn.com.prescription.framework.util.StringUtils;

/**
 * CSVファイル、TSVファイルの操作を行う。
 * <p>
 * CSVファイル、TSVファイルからデータの読み込みを行う。 また、ヘッダ内容、データ内容からファイル内容（バイト配列）を生成する。<br>
 * </p>
 */
public final class CSVTokenizer {

    /** デフォルトトークナイザ */
    private static final StrTokenizer TOKENIZER_PROTOTYPE;

    static {
        TOKENIZER_PROTOTYPE = StrTokenizer.getCSVInstance();
    }
    /** デフォルトトークナイザ */
    private static long startMillis;
    /**
     * デリミタ。
     */
    private char delimiter = ',';

    /**
     * 囲み文字。
     */
    private char quote = '\"';

    /**
     * データがnullの場合は囲み文字を追加しない。
     */
    public boolean MinNull = false;
    /**
     * データ内容。
     */
    private LinkedList<StrTokenizer> records = null;

    /** 読込用：データ */
    private List<String[]> readRecords = null;

    /**
     * CSVTokenizer 的构造。
     */
    private CSVTokenizer() {
    }

    /**
     * CSVTokenizer 的构造。
     * <p>
     * デリミタ: 「,（カンマ）」 囲み文字: 「"（二重引用符）」
     * </p>
     * 
     * @return CSVTokenizer インスタンス
     */
    public static CSVTokenizer getInstance() {
        return new CSVTokenizer();
    }

    /**
     * CSVTokenizer 的构造。
     * <p>
     * 指定されたデリミタ、囲み文字で CSVTokenizer 的构造。
     * </p>
     * 
     * @param _delimiter デリミタ
     * @param _quote 囲み文字
     * @return CSVTokenizer インスタンス
     */
    public static CSVTokenizer getInstance(char _delimiter, char _quote) {
        // 処理開始時間
        startMillis = System.currentTimeMillis();
        // 処理開始ログ
        LogUtils.start(CSVTokenizer.class, "doCsv");

        CSVTokenizer token = new CSVTokenizer();
        token.setDelimiter(_delimiter);
        token.setQuote(_quote);
        return token;
    }

    /**
     * ファイル内容を読み込む。
     * <p>
     * CSVファイル内容のバイト配列からオブジェクト的构造。<br>
     * 内容は、全てデータ行として扱う。
     * </p>
     * 
     * @param _file バイト配列
     * @throws IOException ファイルの読み込みで例外
     */
    public void load(File _file) throws SystemException {
        load(_file, "shift-jis", false);
    }

    /**
     * ファイル内容を読み込む。
     * <p>
     * CSVファイル内容のバイト配列からオブジェクト的构造。<br>
     * 内容は、全てデータ行として扱う。
     * </p>
     * 
     * @param _file バイト配列
     * @throws IOException ファイルの読み込みで例外
     */
    public void load(File _file, boolean _header) throws SystemException {
        load(_file, "shift-jis", _header);
    }

    /**
     * ファイル型のファイル内容を読み込む。
     * 
     * @param _file バイト配列
     * @param _encoding ファイルのエンコーディング
     * @param _header 1行目をヘッダとして扱う場合は <code>ture</code>
     * @throws IOException ファイル文字コード不正
     * @throws ApplicationException ファイル文字コード不正
     * @throws SystemException 予期せぬ错误発生時
     */
    public void load(File _file, String _encoding, boolean _header) throws SystemException {

        // 文字コードを少し修正・・
        if ("shift-jis".equalsIgnoreCase(_encoding)) {
            _encoding = "Windows-31J";
        }

        // リーダーを準備
        CsvReader reader_ = new CsvReader(_file, _encoding);

        try {

            // 全行読込
            readRecords = reader_.readAll();
            // null
            if (this.readRecords == null) {
                this.readRecords = new ArrayList<String[]>();
            }

            // header
            if (_header && 1 <= this.readRecords.size()) {

                // 1行目をヘッダとする
                // this.readHeader =
                this.readRecords.remove(0);

            }

        } finally {

            // ファイルを閉じる
            reader_.close();
        }
    }

    /**
     * CSV文字列へ変換しカンマ区切りで連結する。
     * <p>
     * 取得した文字をCSV文字列へ変換し、デリミタで連結する。変換内容は以下の通り。
     * <ul>
     * <li>ヘッダ値内に囲み文字が存在する場合は、囲み文字2つに変換する</li>
     * <li>ヘッダ値を囲み文字で囲む</li>
     * </ul>
     * </p>
     * 
     * @param _values 変換対象の文字
     * @return カンマ区切りに連結されたCSV文字列
     */
    private String toCsvString(String... _values) {
        LinkedList<String> result = new LinkedList<String>();

        for (String value : _values) {
            if (quote == Character.MIN_VALUE) {
                result.add(StringUtils.defaultString(value));
            } else {
                if (MinNull && value == null) {
                    result.add(StringUtils.defaultString(value));
                } else {
                    result.add(new StringBuilder()
                        .append(quote).append(StringUtils.replace(StringUtils.defaultString(value),
                            String.valueOf(quote), StringUtils.repeat(String.valueOf(quote), 2)))
                        .append(quote).toString());
                }
            }
        }

        return StringUtils.join(result.toArray(), delimiter);
    }

    /**
     * CSVファイル内容をバイト配列で取得する。
     * <p>
     * 改行コードは、'\r\n' 改行コードを使用してファイル内容を生成する。
     * </p>
     * 
     * @return バイト配列
     * @throws SystemException 例外
     */
    public byte[] toByteArray() throws SystemException {
        // return toByteArray("Windows-31J");
        return toByteArray("UTF-8");
    }

    /**
     * CSVファイル内容をバイト配列で取得する。
     * <p>
     * 改行コードは、'\r\n' 改行コードを使用してファイル内容を生成する。
     * </p>
     * 
     * @param _encoding エンコード
     * @return バイト配列
     * @throws SystemException 例外
     */
    public byte[] toByteArray(String _encoding) throws SystemException {
        return toByteArray("\r\n", _encoding);
    }

    /**
     * CSVファイル内容をバイト配列で取得する。
     * 
     * @param _lineSeparator 改行コード
     * @param _encoding エンコード
     * @return バイト配列
     * @throws SystemException I/O例外が発生する場合、
     */
    public byte[] toByteArray(String _lineSeparator, String _encoding) throws SystemException {

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            LinkedList<String> lines = new LinkedList<String>();

            // headerはずっとnullです。
            // if (header != null) {
            // lines.add(header.getContent());
            // }

            // record
            if (records != null) {
                for (StrTokenizer token : records) {
                    lines.add(token.getContent());
                }
            }
            // encoding
            if (_encoding != null) {
                try {
                    IOUtils.writeLines(lines, _lineSeparator, out, _encoding);
                } catch (IOException e) {
                    throw new SystemException("CSVファイル内容のバイト配列取得でストリームがクローズできません。", e);
                }
            } else {
                try {
                    IOUtils.writeLines(lines, _lineSeparator, out);
                } catch (IOException e) {
                    throw new SystemException("CSVファイル内容のバイト配列取得でストリームがクローズできません。", e);
                }
            }

        } finally {
            try {
                out.close();
            } catch (IOException e) {
                // log
                e.printStackTrace();
            }

            // 処理終了ログ
            LogUtils.end(this.getClass(), "doCsv");
            // 処理終了
            LogUtils.process(this.getClass(), "doCsv", startMillis, System.currentTimeMillis());
        }
        return out.toByteArray();
    }

    /**
     * レコード数的取得。
     * 
     * @return レコード数
     */
    public int size() {
        return readRecords == null ? 0 : readRecords.size();
    }

    /**
     * 指定されたインデックスのレコードを 配列形式で取得する。
     * 
     * @param _index 取得対象のインデックス
     * @return 配列形式のレコード
     */
    public String[] get(int _index) {
        if (size() <= _index) {
            throw new ArrayIndexOutOfBoundsException("指定されたインデックスのレコードは存在しないため、レコードの取得ができません。index=" + _index);
        }
        return readRecords.get(_index).length < 1 ? new String[] { "" } : readRecords.get(_index);
    }

    /**
     * レコードを追加する。
     * 
     * @param _values レコード
     */
    public void add(String... _values) {

        if (_values != null && _values.length > 0) {
            StrTokenizer token = (StrTokenizer) TOKENIZER_PROTOTYPE.clone();
            token.reset(toCsvString(_values));

            if (records == null) {
                records = new LinkedList<StrTokenizer>();
            }
            records.add(token);
        }
    }

    /**
     * レコードを先頭追加する。
     * 
     * @param _values レコード
     */
    public void addFirst(String... _values) {

        if (_values != null && _values.length > 0) {
            StrTokenizer token = (StrTokenizer) TOKENIZER_PROTOTYPE.clone();
            token.reset(toCsvString(_values));

            if (records == null) {
                records = new LinkedList<StrTokenizer>();
            }
            records.addFirst(token);
        }
    }

    /**
     * ヘッダを追加する。（取り込み用）
     * 
     * @param _values レコード
     */
    public void addImportHeader(List<String> _values) {
        if (_values != null && _values.size() > 0) {
            StrTokenizer token = (StrTokenizer) TOKENIZER_PROTOTYPE.clone();
            token.reset(StandardConstantsIF.COMMENT_LINE_HANTEI_MOJI_CSV_TORIKOMI_FILE
                            + toCsvString(_values.toArray(new String[_values.size()])));

            if (records == null) {
                records = new LinkedList<StrTokenizer>();
            }
            records.add(token);
        }
    }

    /**
     * ヘッダを先頭追加する。（取り込み用）
     * 
     * @param _values レコード
     */
    public void addImportHeaderFirst(List<String> _values) {
        if (_values != null && _values.size() > 0) {
            StrTokenizer token = (StrTokenizer) TOKENIZER_PROTOTYPE.clone();
            token.reset(StandardConstantsIF.COMMENT_LINE_HANTEI_MOJI_CSV_TORIKOMI_FILE
                            + toCsvString(_values.toArray(new String[_values.size()])));

            if (records == null) {
                records = new LinkedList<StrTokenizer>();
            }
            records.addFirst(token);
        }
    }

    /**
     * ヘッダを追加する。（取り込み以外の場合用）
     * 
     * @param _values レコード
     */
    public void addHeader(List<String> _values) {
        if (_values != null && _values.size() > 0) {
            StrTokenizer token = (StrTokenizer) TOKENIZER_PROTOTYPE.clone();
            token.reset(toCsvString(_values.toArray(new String[_values.size()])));

            if (records == null) {
                records = new LinkedList<StrTokenizer>();
            }
            records.add(token);
        }
    }

    /**
     * ヘッダを先頭追加する。（取り込み以外の場合用）
     * 
     * @param _values レコード
     */
    public void addHeaderFirst(List<String> _values) {
        if (_values != null && _values.size() > 0) {
            StrTokenizer token = (StrTokenizer) TOKENIZER_PROTOTYPE.clone();
            token.reset(toCsvString(_values.toArray(new String[_values.size()])));

            if (records == null) {
                records = new LinkedList<StrTokenizer>();
            }
            records.addFirst(token);
        }
    }

    /**
     * レコードを追加する。
     * 
     * @param _values レコード
     */
    public void add(List<String> _values) {
        if (_values != null && _values.size() > 0) {
            for (int i = 0; i < _values.size(); i++) {
                if (_values.get(i) != null) {
                    _values.set(i, _values.get(i).replaceAll("\\r\\n|\\r|\\n", ""));
                }
            }
            add(_values.toArray(new String[_values.size()]));
        }
    }

    /**
     * デリミタ的设定。
     * 
     * @param _delimiter デリミタ
     */
    private void setDelimiter(char _delimiter) {
        this.delimiter = _delimiter;
        TOKENIZER_PROTOTYPE.setDelimiterChar(_delimiter);
    }

    /**
     * 囲み文字的设定。
     * 
     * @param _quote 囲み文字
     */
    private void setQuote(char _quote) {
        this.quote = _quote;
        TOKENIZER_PROTOTYPE.setQuoteChar(_quote);
    }

    /**
     * データ内容。的取得。
     * 
     * @return データ内容。
     */
    public LinkedList<StrTokenizer> getRecords() {
        return records;
    }

    /**
     * データ内容。的设定。
     * 
     * @param records データ内容。
     */
    public void setRecords(LinkedList<StrTokenizer> records) {
        this.records = records;
    }

}
