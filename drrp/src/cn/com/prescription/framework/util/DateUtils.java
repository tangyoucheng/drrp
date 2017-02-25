/*
 * Copyright(c) 2011 
 */
package cn.com.prescription.framework.util;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import cn.com.prescription.framework.exception.SystemException;

import org.apache.commons.lang.time.DurationFormatUtils;

/**
 * 日付に関するユーティリティクラス。
 * 
 * @author t.d.m
 */
public final class DateUtils {

    /**
     * 時間計算で端数が発生した場合に切り捨てるモード。
     */
    public static final int ROUND_DOWN = 1;

    /**
     * 時間計算で端数が発生した場合に切り上げるモード。
     */
    public static final int ROUND_UP = 2;

    /**
     * 時間計算で端数が発生した場合にもっとも近い時間に丸めるモード。
     * <p>
     * 両隣りの時間が等距離の場合は切り上げる。
     * </p>
     */
    public static final int ROUND_HALF_UP = 3;

    /**
     * 時間計算で端数が発生した場合にもっとも近い時間に丸めるモード。
     * <p>
     * 両隣りの時間が等距離の場合は切り下げる。
     * </p>
     */
    public static final int ROUND_HALF_DOWN = 4;

    /**
     * 時間計算で端数が発生した場合にもっとも近い時間に丸めるモード。
     * <p>
     * 両隣りの時間が等距離の場合は偶数側に丸める。
     * </p>
     */
    public static final int ROUND_HALF_EVEN = 5;

    /**
     * デフォルトの和暦フォーマット(yyMMdd)
     */
    public static final String DEFAULT_WAREKI_FORMAT = "yyMMdd";

    /**
     * デフォルトの西暦フォーマット(yyyyMMdd)
     */
    public static final String DEFAULT_SEIREKI_FORMAT = "yyyyMMdd";

    /**
     * DateUtils 的构造。
     */
    private DateUtils() {
    }

    /**
     * APサーバーから系统日付的取得。
     * 
     * @return 現在の系统日付
     */
    public static Date getSysDateFromApServer() {
        return Calendar.getInstance().getTime();
    }

    /**
     * メモリの系统運用日的取得。
     * 
     * @return メモリで保存された系统運用日
     * @throws SystemException 失敗した場合スロー
     */
    public static Date getSysDate() {
        return Calendar.getInstance().getTime();
    }

    /**
     * 指定された形式の日付時刻文字列を日付型に変換する。
     * 
     * @param _text 日付時刻文字列
     * @param _format 日付時刻文字列の形式
     * @param _locale ロカール
     * @return 変換後の日付
     * @throws SystemException 日付時刻文字列の変換に失敗した場合スロー
     */
    public static Date parse(String _text, String _format, Locale _locale) throws SystemException {

        if (CheckUtils.isEmpty(_text) || CheckUtils.isEmpty(_format)) {
            throw new IllegalArgumentException();
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat(_format, _locale);
        dateFormat.setLenient(false);
        ParsePosition pos = new ParsePosition(0);

        Date date = dateFormat.parse(_text, pos);

        if (pos.getErrorIndex() >= 0) {
            throw new SystemException(new ParseException("日付時刻文字列(" + _text + ")の日付型変換に失敗しました。" + "形式=" + _format,
                pos.getErrorIndex()));
        }

        return date;
    }

    /**
     * 指定された形式の日付時刻文字列を日付型に変換する。
     * 
     * @param _text 日付時刻文字列
     * @param _format 日付時刻文字列の形式
     * @return 変換後の日付
     * @throws SystemException 日付時刻文字列の変換に失敗した場合スロー
     */
    public static Date parse(String _text, String _format) throws SystemException {

        if (CheckUtils.isEmpty(_text) || CheckUtils.isEmpty(_format)) {
            throw new IllegalArgumentException();
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat(_format);
        dateFormat.setLenient(false);
        ParsePosition pos = new ParsePosition(0);

        Date date = dateFormat.parse(_text, pos);

        if (pos.getErrorIndex() >= 0) {
            throw new SystemException(new ParseException("日付時刻文字列(" + _text + ")の日付型変換に失敗しました。" + "形式=" + _format,
                pos.getErrorIndex()));
        }

        return date;
    }

    /**
     * 指定された形式の配列から日付時刻文字列を日付型に変換する。
     * <p>
     * 指定された形式の配列から日付時刻文字列に一致したフォーマットで変換し日付型を返却する。
     * </p>
     * 
     * @param _text 日付時刻文字列
     * @param _formats 日付時刻文字列の形式の配列
     * @return 変換後の日付
     * @throws SystemException 日付時刻文字列の変換に失敗した場合スロー
     */
    public static Date parse(String _text, String[] _formats) throws SystemException {
        if (CheckUtils.isEmpty(_text) || _formats == null) {
            throw new IllegalArgumentException();
        }

        SystemException exception = null;
        for (String format : _formats) {
            try {
                return parse(_text, format);
            } catch (SystemException e) {
                exception = e;
            }
        }
        throw exception;
    }

    /**
     * yyyyMMdd形式の日付文字列を日付型に変換する。
     * 
     * @param _text yyyyMMdd形式の日付文字列
     * @return 変換後の日付
     * @throws SystemException 変換に失敗した場合スロー
     */
    public static Date parseYMD(String _text) throws SystemException {
        return parse(_text, "yyyyMMdd");
    }

    /**
     * yyyy/MM/dd形式の日付文字列を日付型に変換する。
     * 
     * @param _text yyyy/MM/dd形式の日付文字列
     * @return 変換後の日付
     * @throws SystemException 変換に失敗した場合スロー
     */
    public static Date parseFmtYMD(String _text) throws SystemException {
        return parse(_text, "yyyy/MM/dd");
    }

    /**
     * yyyyMMddHHmmss形式の日付文字列を日付型に変換する。
     * 
     * @param _text yyyyMMddHHmmss形式の日付文字列
     * @return 変換後の日付
     * @throws SystemException 変換に失敗した場合スロー
     */
    public static Date parseYMDHMS(String _text) throws SystemException {
        return parse(_text, "yyyyMMddHHmmss");
    }

    /**
     * yyyy/MM/dd|HH:mm:ss形式の日付文字列を日付型に変換する。
     * 
     * @param _text yyyy/MM/dd|HH:mm:ss形式の日付文字列
     * @return 変換後の日付
     * @throws SystemException 変換に失敗した場合スロー
     */
    public static Date parseImYMDHMS(String _text) throws SystemException {
        return parse(_text, "yyyy/MM/dd|HH:mm:ss");
    }

    /**
     * yyyy/MM/dd HH:mm:ss形式の日付文字列を日付型に変換する。
     * 
     * @param _text yyyy/MM/dd HH:mm:ss形式の日付文字列
     * @return 変換後の日付
     * @throws SystemException 変換に失敗した場合スロー
     */
    public static Date parseFmtYMDHMS(String _text) throws SystemException {
        return parse(_text, "yyyy/MM/dd HH:mm:ss");
    }

    /**
     * 日付を指定された形式の日付時刻文字列に変換する。
     * 
     * @param _date 日付
     * @param _format 日付時刻文字列の形式
     * @param _locale ロカール
     * @return 変換後の日付時刻文字列
     */
    public static String format(Date _date, String _format, Locale _locale) {
        if (_date == null || CheckUtils.isEmpty(_format)) {
            throw new IllegalArgumentException();
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat(_format, _locale);
        return dateFormat.format(_date);
    }

    /**
     * 日付を指定された形式の日付時刻文字列に変換する。
     * 
     * @param _date 日付
     * @param _format 日付時刻文字列の形式
     * @return 変換後の日付時刻文字列
     */
    public static String format(Date _date, String _format) {
        if (_date == null || CheckUtils.isEmpty(_format)) {
            throw new IllegalArgumentException();
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat(_format);
        return dateFormat.format(_date);
    }

    /**
     * 日付文字列を指定された日付書式に変換し返却する。
     * <p>
     * 入力値を入力書式で日付変換を行い、出力書式に変換し返却する。
     * </p>
     * 
     * @param _text 変換対象の文字列
     * @param _informat 入力値の書式
     * @param _outformat 出力する書式
     * @return 書式変換後の文字列
     * @throws SystemException 変換に失敗した場合スロー
     */
    public static String format(String _text, String _informat, String _outformat) throws SystemException {
        String outDate = "";
        try {
            Date date = parse(_text, _informat);
            outDate = format(date, _outformat);
        } catch (Exception e) {
            outDate = _text;
        }
        ;
        return outDate;

    }

    /**
     * 日付文字列を指定された日付書式に変換し返却する。
     * <p>
     * 入力値の書式は複数指定可能、入力値に一致する書式で日付変換を行い、出力書式に変換し返却する。
     * </p>
     * 
     * @param _text 変換対象の文字列
     * @param _informat 入力値の書式
     * @param _outformat 出力する書式
     * @return 書式変換後の文字列
     * @throws SystemException 変換に失敗した場合スロー
     */
    public static String format(String _text, String[] _informat, String _outformat) throws SystemException {

        Date date = parse(_text, _informat);
        return format(date, _outformat);

    }

    /**
     * 日付文字列を指定された日付書式に変換し返却する。
     * <p>
     * 入力された日付文字列の書式を出力書式から推測して日付変換を行う。<br>
     * 入力値の入力書式は、以下の書式に限定する。
     * </p>
     * 
     * <pre>
     * ・年月日時分秒
     * yyyyMMddHHmmss
     * yyyy/MM/dd|HH:mm:ss
     * yyyy/MM/ddHH:mm:ss
     * yyyy年MM月dd日 HH時mm分ss秒
     * yyyy年MM月dd日HH時mm分ss秒
     * 
     * ・年月日時分
     * yyyyMMddHHmm
     * yyyy/MM/dd|HH:mm
     * yyyy/MM/ddHH:mm
     * yyyy年MM月dd日 HH時mm分
     * yyyy年MM月dd日HH時mm分
     * 
     * ・年月日時
     * yyyyMMddHH
     * yyyy/MM/dd|HH
     * yyyy/MM/ddHH
     * yyyy年MM月dd日 HH時
     * yyyy年MM月dd日HH時
     * 
     * ・年月日
     * yyyyMMdd
     * yyyy/MM/dd
     * yyyy年MM月dd日
     * 
     * ・年月
     * yyyyMM
     * yyyy/MM
     * yyyy年MM月
     * 
     * ・年
     * yyyy
     * yyyy年
     * 
     * ・月日
     * MM/dd
     * MM月dd日
     * MMdd
     * 
     * ・月
     * MM
     * MM月
     * 
     * ・日
     * dd
     * dd日
     * 
     * ・時分秒
     * HHmmss
     * HH:mm:ss
     * HH時mm分ss秒
     * 
     * ・時分
     * HHmm
     * HH:mm
     * HH時mm分
     * 
     * ・時
     * HH
     * HH時
     * 
     * ・分秒
     * mmss
     * mm:ss
     * mm分ss秒
     * 
     * ・分
     * mm
     * mm分
     * 
     * ・秒
     * ss
     * ss秒
     * </pre>
     * 
     * @param _text 変換対象の文字列
     * @param _outformat 出力する書式
     * @return 書式変換後の文字列
     * @throws SystemException 変換に失敗した場合スロー
     */
    public static String format(String _text, String _outformat) throws SystemException {

        List<String> patterns = new LinkedList<String>();

        boolean isexists = false;

        // 長いフォーマットのパターンから設定していく
        // 年月日時分秒
        patterns.add("yyyyMMddHHmmss");
        patterns.add("yyyy/MM/dd|HH:mm:ss");
        patterns.add("yyyy/MM/dd HH:mm:ss");
        patterns.add("yyyy年MM月dd日 HH時mm分ss秒");
        patterns.add("yyyy年MM月dd日HH時mm分ss秒");
        isexists = patterns.contains(_outformat);

        // 年月日時分
        if (!isexists) {
            patterns.add("yyyyMMddHHmm");
            patterns.add("yyyy/MM/dd|HH:mm");
            patterns.add("yyyy/MM/dd HH:mm");
            patterns.add("yyyy年MM月dd日 HH時mm分");
            patterns.add("yyyy年MM月dd日HH時mm分");
            isexists = patterns.contains(_outformat);
        }

        // 年月日時
        if (!isexists) {
            patterns.add("yyyyMMddHH");
            patterns.add("yyyy/MM/dd|HH");
            patterns.add("yyyy/MM/dd HH");
            patterns.add("yyyy年MM月dd日 HH時");
            patterns.add("yyyy年MM月dd日HH時");
            isexists = patterns.contains(_outformat);
        }

        if (!isexists) {
            if (_outformat.startsWith("y")) {
                // 年月日
                patterns.add("yyyyMMdd");
                patterns.add("yyyy/MM/dd");
                patterns.add("yyyy年MM月dd日");
                isexists = patterns.contains(_outformat);

                // 年月
                if (!isexists) {
                    patterns.add("yyyyMM");
                    patterns.add("yyyy/MM");
                    patterns.add("yyyy年MM月");
                    isexists = patterns.contains(_outformat);
                }

                // 年
                if (!isexists) {
                    patterns.add("yyyy");
                    patterns.add("yyyy年");
                    isexists = patterns.contains(_outformat);
                }
            }
        }

        if (!isexists) {
            if (_outformat.startsWith("M")) {
                // 月日
                patterns.add("MM/dd");
                patterns.add("MM月dd日");
                patterns.add("MMdd");
                isexists = patterns.contains(_outformat);

                // 月
                if (!isexists) {
                    patterns.add("MM");
                    patterns.add("MM月");
                    isexists = patterns.contains(_outformat);
                }
            }
        }

        // 日
        if (!isexists) {
            if (_outformat.startsWith("d")) {
                patterns.add("dd");
                patterns.add("dd日");
                isexists = patterns.contains(_outformat);
            }
        }

        if (!isexists) {
            if (_outformat.startsWith("H")) {
                // 時分秒
                patterns.add("HHmmss");
                patterns.add("HH:mm:ss");
                patterns.add("HH時mm分ss秒");
                isexists = patterns.contains(_outformat);

                // 時分
                if (!isexists) {
                    patterns.add("HHmm");
                    patterns.add("HH:mm");
                    patterns.add("HH時mm分");
                    isexists = patterns.contains(_outformat);
                }

                // 時
                if (!isexists) {
                    patterns.add("HH");
                    patterns.add("HH時");
                    isexists = patterns.contains(_outformat);
                }
            }
        }

        if (!isexists) {
            if (_outformat.startsWith("m")) {
                // 分秒
                patterns.add("mmss");
                patterns.add("mm:ss");
                patterns.add("mm分ss秒");
                isexists = patterns.contains(_outformat);

                // 分
                if (!isexists) {
                    patterns.add("mm");
                    patterns.add("mm分");
                    isexists = patterns.contains(_outformat);
                }
            }
        }

        if (!isexists) {
            if (_outformat.startsWith("s")) {
                // 秒
                if (!isexists) {
                    patterns.add("ss");
                    patterns.add("ss秒");
                    isexists = patterns.contains(_outformat);
                }
            }
        }

        if (!isexists) {
            // それでもなかったら、変換フォーマットを追加
            patterns.add(_outformat);
        }

        String[] pattern = patterns.toArray(new String[patterns.size()]);
        return format(_text, pattern, _outformat);
    }

    /**
     * 年数を加算後の日付的取得。
     * 
     * @param _date 計算対象の日付
     * @param _amount 加算する年数
     * @return 日付に年数を加算した結果の日付
     */
    public static Date addYears(Date _date, int _amount) {
        return org.apache.commons.lang.time.DateUtils.addYears(_date, _amount);
    }

    /**
     * 月数を加算後の日付的取得。
     * <p>
     * addMonths(2008/01/30, 2) = 2008/03/30<br>
     * addMonths(2008/01/30, 1) = 2008/02/29<br>
     * addMonths(2008/03/30, -1) = 2008/02/29
     * </p>
     * 
     * @param _date 計算対象の日付
     * @param _amount 加算する月数
     * @return 日付に月数を加算した結果の日付
     */
    public static Date addMonths(Date _date, int _amount) {
        return org.apache.commons.lang.time.DateUtils.addMonths(_date, _amount);
    }

    /**
     * 週数を加算後の日付的取得。
     * 
     * @param _date 計算対象の日付
     * @param _amount 加算する週数
     * @return 日付に週数を加算した結果の日付
     */
    public static Date addWeeks(Date _date, int _amount) {
        return org.apache.commons.lang.time.DateUtils.addWeeks(_date, _amount);
    }

    /**
     * 日数を加算後の日付的取得。
     * 
     * @param _date 計算対象の日付
     * @param _amount 加算する日数
     * @return 日付に日数を加算した結果の日付
     */
    public static Date addDays(Date _date, int _amount) {
        return org.apache.commons.lang.time.DateUtils.addDays(_date, _amount);
    }

    /**
     * 時間数を加算後の日付的取得。
     * 
     * @param _date 計算対象の日付
     * @param _amount 加算する時間数
     * @return 日付に時間数を加算した結果の日付
     */
    public static Date addHours(Date _date, int _amount) {
        return org.apache.commons.lang.time.DateUtils.addHours(_date, _amount);
    }

    /**
     * 分数を加算後の日付的取得。
     * 
     * @param _date 計算対象の日付
     * @param _amount 加算する分数
     * @return 日付に分数を加算した結果の日付
     */
    public static Date addMinutes(Date _date, int _amount) {
        return org.apache.commons.lang.time.DateUtils.addMinutes(_date, _amount);
    }

    /**
     * 秒数を加算後の日付的取得。
     * 
     * @param _date 計算対象の日付
     * @param _amount 加算する秒数
     * @return 日付に秒数を加算した結果の日付
     */
    public static Date addSeconds(Date _date, int _amount) {
        return org.apache.commons.lang.time.DateUtils.addSeconds(_date, _amount);
    }

    /**
     * 指定された単位で日時を切捨てる
     * 
     * @param _date 計算対象の日付
     * @param _amount 単位
     * @return 結果の日付
     */
    public static Date truncate(Date _date, int _amount) {
        return org.apache.commons.lang.time.DateUtils.truncate(_date, _amount);
    }

    /**
     * 開始から終了の時間の差を月数で取得する。
     * <p>
     * 端数は切り捨てる。
     * </p>
     * 
     * @param _startDate 開始日時
     * @param _endDate 終了日時
     * @return 月数
     */
    public static int pireodMonths(Date _startDate, Date _endDate) {
        return pireodMonths(_startDate, _endDate, ROUND_DOWN);
    }

    /**
     * 開始から終了の時間の差を月数で取得する。
     * <p>
     * 端数処理（丸め）は以下を対象とし丸めモードに指定する。丸め幅は対応しない。
     * <ul>
     * <li>切り捨て({@link DateUtils#ROUND_DOWN})</li>
     * <li>切り上げ({@link DateUtils#ROUND_UP})</li>
     * <li>近い方へ丸める({@link DateUtils#ROUND_HALF_UP})</li>
     * <li>近い偶数へ丸める({@link DateUtils#ROUND_HALF_EVEN})</li>
     * </ul>
     * </p>
     * 
     * @param _startDate 開始日時
     * @param _endDate 終了日時
     * @param _roundMode 丸めモード
     * @return 月数
     * @since 1.0
     */
    public static int pireodMonths(Date _startDate, Date _endDate, int _roundMode) {

        if (_startDate == null || _endDate == null) {
            throw new IllegalArgumentException();
        }

        Date sdate = null;
        Date edate = null;

        if (_startDate.after(_endDate)) {
            sdate = _endDate;
            edate = _startDate;
        } else {
            sdate = _startDate;
            edate = _endDate;
        }

        String diffMonths = DurationFormatUtils.formatPeriod(sdate.getTime(), edate.getTime(), "M");

        int pireod = new Integer(diffMonths).intValue();

        long days = pireodDays(addMonths(sdate, pireod), edate, ROUND_UP);

        if (days > 0) {
            switch (_roundMode) {
                case ROUND_UP:
                    pireod++;
                    break;
                case ROUND_HALF_DOWN:
                case ROUND_HALF_UP:
                    if (days > 15) {
                        pireod++;
                    }
                    break;
                case ROUND_HALF_EVEN:
                    if (CheckUtils.isEven(pireod + 1)) {
                        pireod++;
                    }
                    break;
                default:
                    break;
            }
        }

        return pireod;
    }

    /**
     * 開始から終了の時間の差を日数で取得する。
     * <p>
     * 端数は切り捨てる。
     * </p>
     * 
     * @param _startDate 開始日時
     * @param _endDate 終了日時
     * @return 日数
     */
    public static int pireodDays(Date _startDate, Date _endDate) {
        return pireodDays(_startDate, _endDate, ROUND_DOWN);
    }

    /**
     * 開始から終了の時間の差を日数で取得する。
     * <p>
     * 端数処理（丸め）は以下を対象とし丸めモードに指定する。丸め幅は対応しない。
     * <ul>
     * <li>切り捨て({@link DateUtils#ROUND_DOWN})</li>
     * <li>切り上げ({@link DateUtils#ROUND_UP})</li>
     * <li>近い方へ丸める、真ん中は切り上げ({@link DateUtils#ROUND_HALF_UP})</li>
     * <li>近い方へ丸める、真ん中は切り下げ({@link DateUtils#ROUND_HALF_DOWN})</li>
     * <li>近い偶数へ丸める({@link DateUtils#ROUND_HALF_EVEN})</li>
     * </ul>
     * </p>
     * 
     * @param _startDate 開始日時
     * @param _endDate 終了日時
     * @param _roundMode 丸めモード
     * @return 日数
     */
    public static int pireodDays(Date _startDate, Date _endDate, int _roundMode) {

        if (_startDate == null || _endDate == null) {
            throw new IllegalArgumentException();
        }

        Date sdate = null;
        Date edate = null;

        if (_startDate.after(_endDate)) {
            sdate = _endDate;
            edate = _startDate;
        } else {
            sdate = _startDate;
            edate = _endDate;
        }

        String diffDays = DurationFormatUtils.formatPeriod(sdate.getTime(), edate.getTime(), "d");

        int pireod = new Integer(diffDays).intValue();

        long hours = pireodHours(addDays(sdate, pireod), edate, ROUND_UP);

        if (hours > 0) {
            switch (_roundMode) {
                case ROUND_UP:
                    pireod++;
                    break;
                case ROUND_HALF_DOWN:
                    if (hours > 12) {
                        pireod++;
                    }
                    break;
                case ROUND_HALF_UP:
                    if (hours >= 12) {
                        pireod++;
                    }
                    break;
                case ROUND_HALF_EVEN:
                    if (CheckUtils.isEven(pireod + 1)) {
                        pireod++;
                    }
                    break;
                default:
                    break;
            }
        }

        return pireod;
    }

    /**
     * 開始から終了の時間の差を時間数で取得する。
     * <p>
     * 端数は切り捨てる。
     * </p>
     * 
     * @param _startDate 開始日時
     * @param _endDate 終了日時
     * @return 時間数
     */
    public static long pireodHours(Date _startDate, Date _endDate) {
        return pireodHours(_startDate, _endDate, ROUND_DOWN);
    }

    /**
     * 開始から終了の時間の差を時間数で取得する。
     * <p>
     * 端数処理（丸め）は以下を対象とし丸めモードに指定する。丸め幅は対応しない。
     * <ul>
     * <li>切り捨て({@link DateUtils#ROUND_DOWN})</li>
     * <li>切り上げ({@link DateUtils#ROUND_UP})</li>
     * <li>近い方へ丸める、真ん中は切り上げ({@link DateUtils#ROUND_HALF_UP})</li>
     * <li>近い方へ丸める、真ん中は切り下げ({@link DateUtils#ROUND_HALF_DOWN})</li>
     * <li>近い偶数へ丸める({@link DateUtils#ROUND_HALF_EVEN})</li>
     * </ul>
     * </p>
     * 
     * @param _startDate 開始日時
     * @param _endDate 終了日時
     * @param _roundMode 丸めモード
     * @return 時間数
     * @since 1.0
     */
    public static long pireodHours(Date _startDate, Date _endDate, int _roundMode) {

        if (_startDate == null || _endDate == null) {
            throw new IllegalArgumentException();
        }

        Date sdate = null;
        Date edate = null;

        if (_startDate.after(_endDate)) {
            sdate = _endDate;
            edate = _startDate;
        } else {
            sdate = _startDate;
            edate = _endDate;
        }

        String diffHours = DurationFormatUtils.formatPeriod(sdate.getTime(), edate.getTime(), "H");

        long pireod = new Long(diffHours).longValue();

        long minutes = pireodMinutes(addHours(sdate, (int) pireod), edate, ROUND_UP);

        if (minutes > 0) {
            switch (_roundMode) {
                case ROUND_UP:
                    pireod++;
                    break;
                case ROUND_HALF_DOWN:
                    if (minutes > 30) {
                        pireod++;
                    }
                    break;
                case ROUND_HALF_UP:
                    if (minutes >= 30) {
                        pireod++;
                    }
                    break;
                case ROUND_HALF_EVEN:
                    if (CheckUtils.isEven((int) pireod + 1)) {
                        pireod++;
                    }
                    break;
                default:
                    break;
            }
        }

        return pireod;
    }

    /**
     * 開始から終了の時間の差を分数で取得する。
     * <p>
     * 端数は切り捨てる。
     * </p>
     * 
     * @param _startDate 開始日時
     * @param _endDate 終了日時
     * @return 分数
     */
    public static long pireodMinutes(Date _startDate, Date _endDate) {
        return pireodMinutes(_startDate, _endDate, ROUND_DOWN);
    }

    /**
     * 開始から終了の時間の差を分数で取得する。
     * <p>
     * 端数処理（丸め）は以下を対象とし丸めモードに指定する。丸め幅は対応しない。
     * <ul>
     * <li>切り捨て({@link DateUtils#ROUND_DOWN})</li>
     * <li>切り上げ({@link DateUtils#ROUND_UP})</li>
     * <li>近い方へ丸める、真ん中は切り上げ({@link DateUtils#ROUND_HALF_UP})</li>
     * <li>近い方へ丸める、真ん中は切り下げ({@link DateUtils#ROUND_HALF_DOWN})</li>
     * <li>近い偶数へ丸める({@link DateUtils#ROUND_HALF_EVEN})</li>
     * </ul>
     * </p>
     * 
     * @param _startDate 開始日時
     * @param _endDate 終了日時
     * @param _roundMode 丸めモード
     * @return 分数
     * @since 1.0
     */
    public static long pireodMinutes(Date _startDate, Date _endDate, int _roundMode) {

        if (_startDate == null || _endDate == null) {
            throw new IllegalArgumentException();
        }

        Date sdate = null;
        Date edate = null;

        if (_startDate.after(_endDate)) {
            sdate = _endDate;
            edate = _startDate;
        } else {
            sdate = _startDate;
            edate = _endDate;
        }

        String diffMinutes = DurationFormatUtils.formatPeriod(sdate.getTime(), edate.getTime(), "m");

        long pireod = new Long(diffMinutes).longValue();

        long seconds = pireodSeconds(addMinutes(sdate, (int) pireod), edate);

        if (seconds > 0) {
            switch (_roundMode) {
                case ROUND_UP:
                    pireod++;
                    break;
                case ROUND_HALF_DOWN:
                    if (seconds > 30) {
                        pireod++;
                    }
                    break;
                case ROUND_HALF_UP:
                    if (seconds >= 30) {
                        pireod++;
                    }
                    break;
                case ROUND_HALF_EVEN:
                    if (CheckUtils.isEven((int) pireod + 1)) {
                        pireod++;
                    }
                    break;
                default:
                    break;
            }
        }

        return pireod;
    }

    /**
     * 開始から終了の時間の差を秒数で取得する。
     * 
     * @param _startDate 開始日時
     * @param _endDate 終了日時
     * @return 秒数
     */
    public static long pireodSeconds(Date _startDate, Date _endDate) {

        if (_startDate == null || _endDate == null) {
            throw new IllegalArgumentException();
        }

        Date sdate = null;
        Date edate = null;

        if (_startDate.after(_endDate)) {
            sdate = _endDate;
            edate = _startDate;
        } else {
            sdate = _startDate;
            edate = _endDate;
        }

        String diffSeconds = DurationFormatUtils.formatPeriod(sdate.getTime(), edate.getTime(), "s");

        long pireod = new Long(diffSeconds).longValue();

        return pireod;
    }

    /**
     * 開始から終了の時間の差を指定した書式で取得する。
     * 
     * @param _startMillis 開始ミリ秒
     * @param _endMillis 終了ミリ秒
     * @param _format 取得する文字列書式
     * @return 経過時間
     */
    public static String pireod(long _startMillis, long _endMillis, String _format) {
        return DurationFormatUtils.formatPeriod(_startMillis, _endMillis, _format);
    }

    /**
     * 指定された日付の月末日的取得。
     * 
     * @param _date 日付
     * @return 月末日
     */
    public static Date getMonthEndDay(Date _date) {

        if (_date == null) {
            throw new IllegalArgumentException();
        }

        Calendar calendar = Calendar.getInstance();

        calendar.setTime(_date);
        int monthEndDay = calendar.getActualMaximum(Calendar.DATE);
        calendar.set(Calendar.DATE, monthEndDay);

        return calendar.getTime();
    }

    /**
     * 指定された値にintra-mart標準に準拠した開始時間を付与する
     * 
     * @param _value 指定日付
     * @return String 指定日付+"|00:00:00"
     */
    public static String addIntramartStart(String _value) {

        StringBuilder builder = new StringBuilder();

        builder.append(_value);
        builder.append("|00:00:00");

        return builder.toString();
    }

    /**
     * 指定された値にintra-mart標準に準拠した終了時間を付与する
     * 
     * @param _value 指定日付
     * @return String 指定日付+"|23:59:59"
     */
    public static String addIntramartEnd(String _value) {

        StringBuilder builder = new StringBuilder();

        builder.append(_value);
        builder.append("|23:59:59");

        return builder.toString();
    }
}
