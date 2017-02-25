/*
 * Copyright(c) 2011 
 */
package cn.com.prescription.framework.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import cn.com.prescription.framework.exception.SystemException;

import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang.time.DateUtils;

/**
 * 和暦クラス
 */
public class ImperialCalendar {

    // Lenient設定のデフォルト値
    // false : 年月日情報を厳密に判定する（"2月30日"や"昭和70年"は错误となる）
    // true : 年月日を寛大に判定する（"2月30日"は"3月2日"として判定される）
    private static final boolean DEFAULT_LENIENT = false;

    /** 読み込んだ元号情報を保持 */
    // 外部ファイル、データベースから読み込むほうがいい
    // #元号コード# #元号略称# #元号漢字# #元号開始日（西暦）# #元号最終日（西暦）# #和暦年と西暦年との差異#
    // 1 M 明治 18681023 19120729 1867
    // 2 T 大正 19120730 19261224 1911
    // 3 S 昭和 19261225 19890107 1925
    // 4 H 平成 19890108 99991231 1988
    private static final String[][] earSettings = { { "1", "M", "明治", "18681023", "19120729", "1867" },
                    { "2", "T", "大正", "19120730", "19261224", "1911" },
                    { "3", "S", "昭和", "19261225", "19890107", "1925" },
                    { "4", "H", "平成", "19890108", "99991231", "1988" } };

    // 元号コードをキーとして、読み込んだ情報を保持
    private static Map<String, Map<String, Object>> calendarSettings = new HashMap<String, Map<String, Object>>();

    // 位置情報
    /* 元号コード */
    private static final int POS_EARCODE = 0;
    /* 　元号略称 */
    private static final int POS_EAR = 1;
    /* 　元号（漢字） */
    private static final int POS_EARJP = 2;
    /* 　元号開始日（西暦） */
    private static final int POS_FIRSTDAY = 3;
    /* 　元号最終日（西暦） */
    private static final int POS_LASTDAY = 4;
    /* 　和暦年と西暦年との差異 */
    private static final int POS_DIFFYEAR = 5;

    // 元号（略称）のキー
    private static final String KEY_ERA = "era";
    // 元号（漢字表記）のキー
    private static final String KEY_ERA_JA = "era_ja";
    // 元号の開始日（西暦）
    private static final String KEY_FIRST_DAY_OF_ERA = "first_day_of_era";
    // 元号の終了日（西暦）
    private static final String KEY_LAST_DAY_OF_ERA = "last_day_of_era";
    // 元号年度と西暦年度との差分
    private static final String KEY_ERA_GREG_YEAR_DIFF = "era_greg_year_diff";

    /**
     * 内部状態 GregorianCalendar
     */
    private GregorianCalendar _gcal = null;

    /**
     * <p>
     * 保持する和暦の精度を表します
     * </p>
     */
    public static enum WAREKI_MODE {
        /**
         * <p>
         * "年月日"（yyMMdd）
         * </p>
         */
        YMD,
        /**
         * <p>
         * "年月"（yyMM）
         * </p>
         * このモードの場合、"日"に対する操作を行うと、例外が発生します
         */
        YM;
    }

    /**
     * 初期化処理 外部リソースから元号情報を取得します。
     * 
     * @throws SystemException 系统错误
     */
    static {

        try {
            // #元号コード# #元号略称# #元号漢字# #元号開始日（西暦）# #元号最終日（西暦）# #和暦年と西暦年との差異#
            // 1 M 明治 18681023 19120729 1867
            // 2 T 大正 19120730 19261224 1911
            // 3 S 昭和 19261225 19890107 1925
            // 4 H 平成 19890108 99991231 1988
            for (String[] earSetting : earSettings) {
                // 元号情報作成
                Map<String, Object> calendarSetting = new HashMap<String, Object>();
                // 元号（略称）
                calendarSetting.put(KEY_ERA, earSetting[POS_EAR]);
                // 元号（漢字表記）
                calendarSetting.put(KEY_ERA_JA, earSetting[POS_EARJP]);
                // 元号の開始日・終了日（西暦）はDateで保持
                calendarSetting.put(KEY_FIRST_DAY_OF_ERA,
                    DateUtils.parseDate(earSetting[POS_FIRSTDAY], new String[] { "yyyyMMdd" }));
                calendarSetting.put(KEY_LAST_DAY_OF_ERA,
                    DateUtils.parseDate(earSetting[POS_LASTDAY], new String[] { "yyyyMMdd" }));
                // 元号年度と西暦年度との差分
                calendarSetting.put(KEY_ERA_GREG_YEAR_DIFF, earSetting[POS_DIFFYEAR]);
                // 元号情報を保持
                calendarSettings.put(earSetting[POS_EARCODE], calendarSetting);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    /**
     * 指定した<code>Date</code>で新しいインスタンスを生成します
     * 
     * @param date date
     */
    public ImperialCalendar(Date date) {
        _gcal = new GregorianCalendar();
        _gcal.setTime(date);
    }

    /**
     * 年号コード・年・月・日を指定してインスタンスを生成します。 年号コードはプロパティファイルで設定したコードである必要があります。 <h5>年月日の判定について</h5> 存在しない年月日の扱いは、lenient
     * の指定に従います。<br>
     * <ul>
     * <li>lenient が true の場合、判定は"寛大に"行われます。例えば"03(昭和) 65年1月1日" は、1990年1月1日 として判定されます。</li>
     * <li>lenient が true の場合、判定は"厳密に"行われます。例えば"03(昭和) 65年1月1日" は、错误となり、<code>SystemException</code>が発生します。</li>
     * </ul>
     * 
     * @param eraCode 年号コード
     * @param year 年
     * @param month 月
     * @param day 日
     * @param lenient 年月日情報を寛大に評価するかどうか
     * @throws SystemException 元号コードが登録されていない場合。lenientがfalseであり、指定された年月日に誤りがある場合。
     */
    public ImperialCalendar(String eraCode, int year, int month, int day, boolean lenient, WAREKI_MODE mode)
                    throws SystemException {

        // 元号コードの存在チェック
        if (!calendarSettings.containsKey(eraCode)) {
            throw new SystemException("元号コード[" + eraCode + "]は登録されていません");
        }

        // 指定された条件でカレンダ生成
        _gcal = new GregorianCalendar(year
                        + NumberUtils.toInt((String) calendarSettings.get(eraCode).get(KEY_ERA_GREG_YEAR_DIFF)),
            month - 1, day);

        // 年月日情報の妥当性チェック
        if (!lenient) {
            if (BooleanUtils.isFalse(validate(eraCode, year, month, day, mode))) {
                throw new SystemException("指定された年月日情報[元号:" + eraCode + " 年:" + year + " 月:" + month + " 日:" + day
                                + "]の整合性が取れていません");
            }
        }
    }

    /**
     * <p>
     * 年号コード・年・月・日を指定してインスタンスを生成します。
     * </p>
     * 年号コードはプロパティファイルで設定したコードである必要があります。<br>
     * 指定された年月日情報は厳密に判定され、存在しない年月日を指定した場合は、<code>SystemException</code>が発生します。
     * 
     * @param eraCode　年号コード
     * @param year　年
     * @param month　月
     * @param day　日
     * @throws SystemException　元号コードが登録されていない場合。指定された年月日に誤りがある場合。
     */
    public ImperialCalendar(String eraCode, int year, int month, int day) throws SystemException {
        this(eraCode, year, month, day, DEFAULT_LENIENT, WAREKI_MODE.YMD);
    }

    /**
     * <p>
     * "yyMMdd"形式の和暦文字列を元に、インスタンスを生成します。
     * </p>
     * <h5>年月日の判定について</h5> 存在しない年月日の扱いは、lenient の指定に従います。<br>
     * <ul>
     * <li>lenient が true の場合、判定は"寛大に"行われます。例えば"03(昭和) 65年1月1日" は、1990年1月1日 として判定されます。</li>
     * <li>lenient が true の場合、判定は"厳密に"行われます。例えば"03(昭和) 65年1月1日" は、错误となり、<code>SystemException</code>が発生します。</li>
     * </ul>
     * 
     * @param eraCode　年号コード
     * @param yyMMdd　和暦文字列(yyMMdd形式固定)
     * @param lenient　年月日を寛大に評価するか
     * @throws SystemException 元号コードが登録されていない場合。lenientがfalseであり、指定された年月日に誤りがある場合。
     */
    public ImperialCalendar(String eraCode, String yyMMdd, boolean lenient, WAREKI_MODE mode) throws SystemException {
        this(eraCode, NumberUtils.toInt(yyMMdd.substring(0, 2)), NumberUtils.toInt(yyMMdd.substring(2, 4)), NumberUtils
            .toInt(yyMMdd.substring(4, 6)), lenient, mode);
    }

    /**
     * <p>
     * "yyMMdd"形式の和暦文字列を元に、指定した和暦モードでインスタンスを生成します。
     * </p>
     * 指定した年月日は厳密に判定され、存在しない年月日の場合は<code>SystemException</code>が発生します
     * 
     * @param eraCode 元号コード
     * @param yyMMdd 和暦文字列
     * @param mode 和暦モード
     */
    public ImperialCalendar(String eraCode, String yyMMdd, WAREKI_MODE mode) throws SystemException {
        this(eraCode, yyMMdd, DEFAULT_LENIENT, mode);
    }

    /**
     * <p>
     * "yyMMdd"形式の和暦文字列を元に、インスタンスを生成します。
     * </p>
     * 指定した年月日は厳密に判定され、存在しない年月日の場合は<code>SystemException</code>が発生します また、指定した年月日は日まで考慮されます。
     * 
     * @param eraCode 年号コード
     * @param yyMMdd 和暦文字列(yyMMdd形式固定)
     * @throws SystemException 元号コードが登録されていない場合。指定された年月日に誤りがある場合。
     */
    public ImperialCalendar(String eraCode, String yyMMdd) throws SystemException {
        this(eraCode, yyMMdd, DEFAULT_LENIENT, WAREKI_MODE.YMD);
    }

    /**
     * 現在保持している<code>Calendar</code>から、<code>Date</code>を取得します。
     * 
     * @return Date
     */
    public Date getDate() {
        return _gcal.getTime();
    }

    /**
     * <p>
     * 現在保持している<code>Calendar</code>に対応する、和暦コードを取得します。
     * </p>
     * 
     * @return 元号コード
     * @throws SystemException　該当する元号コードが登録されていない場合。
     */
    public String getEraCode() throws SystemException {
        Date date = _gcal.getTime();
        for (Map.Entry<String, Map<String, Object>> e : calendarSettings.entrySet()) {
            if (date.after(DateUtils.addDays((Date) e.getValue().get(KEY_FIRST_DAY_OF_ERA), -1))
                            && date.before(DateUtils.addDays((Date) e.getValue().get(KEY_LAST_DAY_OF_ERA), 1))) {
                return e.getKey();
            }
        }

        throw new SystemException("該当する元号がありません");
    }

    /**
     * <p>
     * 現在保持している<code>Calendar</code>に対応する、和暦年を取得します。
     * </p>
     * 
     * @return 和暦年
     */
    public String getImperialYear() throws SystemException {
        // 保持している西暦と、西暦と和暦の差分から、和暦を求める
        return ObjectUtils.toString(_gcal.get(Calendar.YEAR)
                        - NumberUtils.toInt((String) calendarSettings.get(getEraCode()).get(KEY_ERA_GREG_YEAR_DIFF)));
    }

    /**
     * <p>
     * 現在の日历を、指定した書式でフォーマットした文字列を取得します。
     * </p>
     * 日付書式は基本的にJava標準の日付書式に従いますが、以下の点が異なります。
     * <ul>
     * <li>"G"は"元号"を表します。4個未満の"G"は元号の略称(M T S H etc)を表し、4個以上"G"は、元号(明治・大正 etc)を表します</li>
     * <li>"y"は全て和暦年として扱われます</li>
     * <li>"g"は年号コードを表します</li>
     * </ul>
     * <p>
     * 書式の例
     * </p>
     * 19801101 (昭和55年11月01日)の場合
     * <ul>
     * <li>GGGGyy年MM月dd日 ⇒ "昭和55年11月01日"</li>
     * <li>GGyy.MM.dd ⇒S55.11.01</li>
     * </ul>
     * 
     * @param format 日付書式
     * @return 指定した書式でフォーマットされた日付文字列
     */
    public String format(String format) throws SystemException {
        String outWarekiFormat = format;
        outWarekiFormat = StringUtils.replace(outWarekiFormat, "MM", "置換MM");
        outWarekiFormat = StringUtils.replace(outWarekiFormat, "dd", "置換dd");
        outWarekiFormat = StringUtils.replace(outWarekiFormat, "HH", "置換HH");
        outWarekiFormat = StringUtils.replace(outWarekiFormat, "mm", "置換mm");
        // 指定されたフォーマットの"年"部分を和暦で上書き
        outWarekiFormat = outWarekiFormat.replaceAll("G{4,}", "'" + getEraLongName() + "'")
            .replaceAll("G{1,3}", "'" + getEraShortName() + "'")
            .replaceAll("y{2,}", StringUtils.leftPad(getImperialYear(), 2, "　"))
            .replaceAll("y{1,1}", getImperialYear()).replaceAll("g{1,}", getEraCode());
        DateFormat df = new SimpleDateFormat(outWarekiFormat, Locale.JAPAN);

        String warekiString = df.format(_gcal.getTime());

        // String ieVersion = UserSessionUtils.getUserSessionInfo().getUserBrowser();
        // TODO
        warekiString = StringUtils.replace(warekiString, "置換0", "　");
        warekiString = StringUtils.replace(warekiString, "置換", "");
        // for ie9.0
        // if (CheckUtils.isEqual(ieVersion, "MSIE 9.0")) {
        // warekiString = StringUtils.replace(warekiString, "　", " ");
        // }
        return warekiString;
    }

    /**
     * <p>
     * 現在の日历を、指定した書式でフォーマットした文字列を取得します。
     * </p>
     * 日付書式は基本的にJava標準の日付書式に従いますが、以下の点が異なります。
     * <ul>
     * <li>"G"は"元号"を表します。4個未満の"G"は元号の略称(M T S H etc)を表し、4個以上"G"は、元号(明治・大正 etc)を表します</li>
     * <li>"y"は全て和暦年として扱われます</li>
     * <li>"g"は年号コードを表します</li>
     * </ul>
     * <p>
     * 書式の例
     * </p>
     * 19801101 (昭和55年11月01日)の場合
     * <ul>
     * <li>GGGGyy年MM月dd日 ⇒ "昭和55年11月01日"</li>
     * <li>GGyy.MM.dd ⇒S55.11.01</li>
     * </ul>
     * 
     * @param format 日付書式
     * @return 指定した書式でフォーマットされた日付文字列
     */
    public String format_wareki(String format) throws SystemException {
        String outWarekiFormat = format;
        // 指定されたフォーマットの"年"部分を和暦で上書き
        outWarekiFormat = outWarekiFormat.replaceAll("G{4,}", "'" + getEraLongName() + "'")
            .replaceAll("G{1,3}", "'" + getEraShortName() + "'")
            .replaceAll("y{2,}", StringUtils.leftPad(getImperialYear(), 2, "0"))
            .replaceAll("y{1,1}", getImperialYear()).replaceAll("g{1,}", getEraCode());
        DateFormat df = new SimpleDateFormat(outWarekiFormat, Locale.JAPAN);

        String warekiString = df.format(_gcal.getTime());

        return warekiString;
    }

    /**
     * <p>
     * 現在の日历を、指定した書式でフォーマットした文字列を取得します。
     * </p>
     * 日付書式は基本的にJava標準の日付書式に従いますが、以下の点が異なります。
     * <ul>
     * <li>"G"は"元号"を表します。4個未満の"G"は元号の略称(M T S H etc)を表し、4個以上"G"は、元号(明治・大正 etc)を表します</li>
     * <li>"y"は全て和暦年として扱われます</li>
     * <li>"g"は年号コードを表します</li>
     * </ul>
     * <p>
     * 書式の例
     * </p>
     * 19801101 (昭和55年11月01日)の場合
     * <ul>
     * <li>GGGGyy年MM月dd日 ⇒ "昭和55年11月01日"</li>
     * <li>GGyy.MM.dd ⇒S55.11.01</li>
     * </ul>
     * 
     * @param format 日付書式
     * @return 指定した書式でフォーマットされた日付文字列
     */
    public String format_array_input(String format) throws SystemException {
        String outWarekiFormat = format;
        outWarekiFormat = StringUtils.replace(outWarekiFormat, "yy", "y");
        outWarekiFormat = StringUtils.replace(outWarekiFormat, "MM", "M");
        outWarekiFormat = StringUtils.replace(outWarekiFormat, "dd", "d");
        outWarekiFormat = StringUtils.replace(outWarekiFormat, "HH", "H");
        outWarekiFormat = StringUtils.replace(outWarekiFormat, "mm", "m");
        // 指定されたフォーマットの"年"部分を和暦で上書き
        outWarekiFormat = outWarekiFormat.replaceAll("G{4,}", "'" + getEraLongName() + "'")
            .replaceAll("G{1,3}", "'" + getEraShortName() + "'")
            .replaceAll("y{2,}", StringUtils.leftPad(getImperialYear(), 2, "0"))
            .replaceAll("y{1,1}", getImperialYear()).replaceAll("g{1,}", getEraCode());
        DateFormat df = new SimpleDateFormat(outWarekiFormat, Locale.JAPAN);

        String warekiString = getEraCode() + "." + df.format(_gcal.getTime());
        return warekiString;
    }

    /**
     * <p>
     * 現在の日历を、指定した書式でフォーマットした文字列を取得します。
     * </p>
     * 日付書式は基本的にJava標準の日付書式に従いますが、以下の点が異なります。
     * <ul>
     * <li>"G"は"元号"を表します。4個未満の"G"は元号の略称(M T S H etc)を表し、4個以上"G"は、元号(明治・大正 etc)を表します</li>
     * <li>"y"は全て和暦年として扱われます</li>
     * <li>"g"は年号コードを表します</li>
     * </ul>
     * <p>
     * 書式の例
     * </p>
     * 19801101 (昭和55年11月01日)の場合
     * <ul>
     * <li>GGGGyy年MM月dd日 ⇒ "昭和55年11月01日"</li>
     * <li>GGyy.MM.dd ⇒S55.11.01</li>
     * </ul>
     * 
     * @param format 日付書式
     * @return 指定した書式でフォーマットされた日付文字列
     */
    public String format_array(String format) throws SystemException {
        String outWarekiFormat = format;
        outWarekiFormat = StringUtils.replace(outWarekiFormat, "yy", "y");
        outWarekiFormat = StringUtils.replace(outWarekiFormat, "MM", "M");
        outWarekiFormat = StringUtils.replace(outWarekiFormat, "dd", "d");
        outWarekiFormat = StringUtils.replace(outWarekiFormat, "HH", "H");
        outWarekiFormat = StringUtils.replace(outWarekiFormat, "mm", "m");
        // 指定されたフォーマットの"年"部分を和暦で上書き
        outWarekiFormat = outWarekiFormat.replaceAll("G{4,}", "'" + getEraLongName() + "'")
            .replaceAll("G{1,3}", "'" + getEraShortName() + "'")
            .replaceAll("y{2,}", StringUtils.leftPad(getImperialYear(), 2, "0"))
            .replaceAll("y{1,1}", getImperialYear()).replaceAll("g{1,}", getEraCode());
        DateFormat df = new SimpleDateFormat(outWarekiFormat, Locale.JAPAN);

        String warekiString = getEraLongName() + "." + df.format(_gcal.getTime());
        return warekiString;
    }

    /**
     * 現在保持している<code>Calendar</code>に対応する、元号の略称(M,T,S,H etc)を取得します
     * 
     * @return 元号（略称）
     */
    public String getEraShortName() throws SystemException {
        return (String) calendarSettings.get(getEraCode()).get(KEY_ERA);
    }

    /**
     * 現在保持している<code>Calendar</code>に対応する、元号の名称(明治,対象,昭和,平成 etc)を取得します
     * 
     * @return 元号（漢字名称）
     */
    public String getEraLongName() throws SystemException {
        return (String) calendarSettings.get(getEraCode()).get(KEY_ERA_JA);
    }

    /**
     * 日付の妥当性チェックを行います
     * 
     * @param eraCode 元号
     * @param year 年
     * @param month 月
     * @param day 日
     * @param mode フォーマット
     * @return チェック結果
     */
    private boolean validate(String eraCode, int year, int month, int day, WAREKI_MODE mode) {

        // 月日の妥当性チェック
        // Calendarに厳密検査モードで日付を喰わせて、例外が出るかを調べる
        try {
            Calendar c = Calendar.getInstance();
            c.setLenient(false);
            c.set(NumberUtils.toInt((String) calendarSettings.get(eraCode).get(KEY_ERA_GREG_YEAR_DIFF)) + year,
                month - 1, day);
            c.getTime();
        } catch (Exception e) {
            return false;
        }
        // 元号の範囲チェック。年は妥当でも、開始日以前 or 最終日以後は错误
        Date start = (Date) calendarSettings.get(eraCode).get(KEY_FIRST_DAY_OF_ERA);
        Date end = (Date) calendarSettings.get(eraCode).get(KEY_LAST_DAY_OF_ERA);

        // 月での判定の場合、日を無視するため、
        // 開始日：元号の開始月の月初日　終了日：元号の終了月の月末日
        // に設定する
        if (mode == WAREKI_MODE.YM) {
            start = DateUtils.truncate(start, Calendar.MONTH);
            // "終了月の月末日" は "終了日の翌月の月初日 - 1日" で求める
            end = DateUtils.addDays(DateUtils.truncate(DateUtils.addMonths(end, 1), Calendar.MONTH), -1);
        }

        if (_gcal.getTime().before(start) || _gcal.getTime().after(end)) {
            return false;
        }

        return true;
    }

    /**
     * 日付の妥当性チェックを行います
     * 
     * @param eraCode 元号
     * @param year 年
     * @param month 月
     * @param day 日
     * @param mode フォーマット
     * @return チェック結果
     */
    public static boolean isWarekiDate(String eraCode, int year, int month, int day, WAREKI_MODE mode) {

        // 月日の妥当性チェック
        // Calendarに厳密検査モードで日付を喰わせて、例外が出るかを調べる
        Calendar c = Calendar.getInstance();
        try {
            c.setLenient(false);
            c.set(NumberUtils.toInt((String) calendarSettings.get(eraCode).get(KEY_ERA_GREG_YEAR_DIFF)) + year,
                month - 1, day);
            c.getTime();
        } catch (Exception e) {
            return false;
        }
        // 元号の範囲チェック。年は妥当でも、開始日以前 or 最終日以後は错误
        Date start = (Date) calendarSettings.get(eraCode).get(KEY_FIRST_DAY_OF_ERA);
        Date end = (Date) calendarSettings.get(eraCode).get(KEY_LAST_DAY_OF_ERA);

        // 月での判定の場合、日を無視するため、
        // 開始日：元号の開始月の月初日　終了日：元号の終了月の月末日
        // に設定する
        if (mode == WAREKI_MODE.YM) {
            start = DateUtils.truncate(start, Calendar.MONTH);
            // "終了月の月末日" は "終了日の翌月の月初日 - 1日" で求める
            end = DateUtils.addDays(DateUtils.truncate(DateUtils.addMonths(end, 1), Calendar.MONTH), -1);
        } else {
            // 翌日（00:00:00）を設定
            end = DateUtils.addDays(end, 1);
        }

        if (c.getTime().before(start) || c.getTime().after(end)) {
            return false;
        }

        return true;
    }

    /**
     * 日付の妥当性チェックを行います
     * 
     * @param eraCode 元号
     * @param year 年
     * @param month 月
     * @return
     * @throws SystemException 例外が発生した
     */
    public static boolean isWarekiDate(String eraCode, String year, String month) throws SystemException {
        // 月の範囲チェック
        if (CheckUtils.isMoreThan(NumberUtils.toInt(month), 0) && CheckUtils.isLessThan(NumberUtils.toInt(month), 13)) {
            int checkData = NumberUtils.toInt(wareki_to_seireki(eraCode, year, month));

            String strFirstDay = cn.com.prescription.framework.util.DateUtils.format((Date) calendarSettings.get(eraCode)
                .get(KEY_FIRST_DAY_OF_ERA), "yyyyMMdd");
            int firstData = NumberUtils.toInt(strFirstDay.substring(0, 6));

            String strLastDay = cn.com.prescription.framework.util.DateUtils.format((Date) calendarSettings.get(eraCode)
                .get(KEY_LAST_DAY_OF_ERA), "yyyyMMdd");
            int lastDay = NumberUtils.toInt(strLastDay.substring(0, 6));

            if (firstData <= checkData && checkData <= lastDay) {
                return true;
            }
        }

        return false;
    }

    /**
     * 日付の妥当性チェックを行います
     * 
     * @param eraCode 元号
     * @param year 年
     * @return
     * @throws SystemException 例外が発生した
     */
    public static boolean isWarekiDate(String eraCode, String year) throws SystemException {

        int checkData = NumberUtils.toInt(wareki_to_seireki(eraCode, year));

        String strFirstDay = cn.com.prescription.framework.util.DateUtils.format(
            (Date) calendarSettings.get(eraCode).get(KEY_FIRST_DAY_OF_ERA), "yyyyMMdd");
        int firstData = NumberUtils.toInt(strFirstDay.substring(0, 4));

        String strLastDay = cn.com.prescription.framework.util.DateUtils.format(
            (Date) calendarSettings.get(eraCode).get(KEY_LAST_DAY_OF_ERA), "yyyyMMdd");
        int lastDay = NumberUtils.toInt(strLastDay.substring(0, 4));

        if (firstData <= checkData && checkData <= lastDay) {
            return true;
        }
        return false;
    }

    /**
     * 西暦から和暦に変更する
     * 
     * @param _date 西暦
     * @return
     * @throws SystemException 例外が発生した
     */
    public static String[] seireki_to_wareki_array_input(String _date) throws SystemException {
        int currentYear = 0;
        int currentMonth = 0;
        String[] result = new String[] { "", "", "" };
        if (CheckUtils.isEmpty(_date)) {
            throw new SystemException();
        }
        if (_date.length() == 4) {
            currentYear = NumberUtils.toInt(_date);
            for (int i = calendarSettings.size(); i > 0; i--) {
                int diffYear = NumberUtils.toInt(calendarSettings.get(String.valueOf(i)).get(KEY_ERA_GREG_YEAR_DIFF)
                    .toString());
                if (currentYear > diffYear) {
                    result[0] = String.valueOf(i);
                    result[1] = String.valueOf(currentYear - diffYear);
                    break;
                }
            }
        }
        if (_date.length() == 6) {
            currentMonth = NumberUtils.toInt(_date.substring(4, 6));
            if (currentMonth < 1 || currentMonth > 12) {
                throw new SystemException();
            }
            currentYear = NumberUtils.toInt(_date.substring(0, 4));
            for (int i = calendarSettings.size(); i > 0; i--) {
                int diffYear = NumberUtils.toInt(calendarSettings.get(String.valueOf(i)).get(KEY_ERA_GREG_YEAR_DIFF)
                    .toString());
                if (currentYear > diffYear) {
                    result[0] = String.valueOf(i);
                    result[1] = String.valueOf(currentYear - diffYear);
                    result[2] = String.valueOf(NumberUtils.toInt(_date.substring(4, 6)));
                    break;
                }
            }

        }
        return result;
    }

    /**
     * 西暦から和暦に変更する
     * 
     * @param _date 西暦
     * @return
     * @throws SystemException 例外が発生した
     */
    public static String[] seireki_to_wareki_array(String _date) throws SystemException {
        int currentYear = 0;
        int currentMonth = 0;
        String[] result = new String[] { "", "", "" };
        if (CheckUtils.isEmpty(_date)) {
            throw new SystemException();
        }
        if (_date.length() == 4) {
            currentYear = NumberUtils.toInt(_date);
            for (int i = calendarSettings.size(); i > 0; i--) {
                int diffYear = NumberUtils.toInt(calendarSettings.get(String.valueOf(i)).get(KEY_ERA_GREG_YEAR_DIFF)
                    .toString());
                if (currentYear > diffYear) {
                    result[0] = calendarSettings.get(String.valueOf(i)).get(KEY_ERA_JA).toString();
                    result[1] = String.valueOf(currentYear - diffYear);
                    break;
                }
            }
        }
        if (_date.length() == 6) {
            currentMonth = NumberUtils.toInt(_date.substring(4, 6));
            if (currentMonth < 1 || currentMonth > 12) {
                throw new SystemException();
            }
            currentYear = NumberUtils.toInt(_date.substring(0, 4));
            for (int i = calendarSettings.size(); i > 0; i--) {
                int diffYear = NumberUtils.toInt(calendarSettings.get(String.valueOf(i)).get(KEY_ERA_GREG_YEAR_DIFF)
                    .toString());
                if (currentYear > diffYear) {
                    result[0] = calendarSettings.get(String.valueOf(i)).get(KEY_ERA_JA).toString();
                    result[1] = String.valueOf(currentYear - diffYear);
                    result[2] = String.valueOf(NumberUtils.toInt(_date.substring(4, 6)));
                    break;
                }
            }

        }
        return result;
    }

    /**
     * 西暦から和暦に変更する
     * 
     * @param _date 西暦
     * @return
     * @throws SystemException 例外が発生した
     */
    public static String seireki_to_wareki(String _date) throws SystemException {
        int currentYear = 0;
        int currentMonth = 0;
        String result = "";
        if (CheckUtils.isEmpty(_date)) {
            throw new SystemException();
        }
        if (_date.length() == 4) {
            currentYear = NumberUtils.toInt(_date);
            for (int i = calendarSettings.size(); i > 0; i--) {
                int diffYear = NumberUtils.toInt(calendarSettings.get(String.valueOf(i)).get(KEY_ERA_GREG_YEAR_DIFF)
                    .toString());
                if (currentYear > diffYear) {
                    result = calendarSettings.get(String.valueOf(i)).get(KEY_ERA_JA).toString()
                                    + String.format("%2d", currentYear - diffYear);
                    break;
                }
            }
        }
        if (_date.length() == 6) {
            currentMonth = NumberUtils.toInt(_date.substring(4, 6));
            if (currentMonth < 1 || currentMonth > 12) {
                throw new SystemException();
            }
            currentYear = NumberUtils.toInt(_date.substring(0, 4));
            for (int i = calendarSettings.size(); i > 0; i--) {
                int diffYear = NumberUtils.toInt(calendarSettings.get(String.valueOf(i)).get(KEY_ERA_GREG_YEAR_DIFF)
                    .toString());
                if (currentYear > diffYear) {
                    result = calendarSettings.get(String.valueOf(i)).get(KEY_ERA_JA).toString()
                                    + String.format("%2d", currentYear - diffYear) + "."
                                    + String.format("%2d", NumberUtils.toInt(_date.substring(4, 6)));
                    break;
                }
            }

        }
        return result;
    }

    /**
     * 和暦から西暦に変更する
     * 
     * @param _era 元号
     * @param _year 年
     * @return
     * @throws SystemException 例外が発生した
     */
    public static String wareki_to_seireki(String _era, String _year) throws SystemException {
        int diffYear = NumberUtils.toInt(calendarSettings.get(_era).get(KEY_ERA_GREG_YEAR_DIFF).toString());
        int result = diffYear + NumberUtils.toInt(_year);
        return String.valueOf(result);
    }

    /**
     * 和暦から西暦に変更する
     * 
     * @param _era 元号
     * @param _year 年
     * @param _month 月
     * @return
     * @throws SystemException 例外が発生した
     */
    public static String wareki_to_seireki(String _era, String _year, String _month) throws SystemException {
        String result = wareki_to_seireki(_era, _year);
        result = result + StringUtils.leftPad(String.valueOf(NumberUtils.toInt(_month)), 2, "0");
        return result;
    }

    /**
     * 和暦から西暦年度に変更する
     * 
     * @param _era 元号
     * @param _year 年
     * @param _month 月
     * @return
     * @throws SystemException 例外が発生した
     */
    public static String wareki_to_nendo(String _era, String _year, String _month) throws SystemException {
        String result = wareki_to_seireki(_era, _year);
        String strMonth = StringUtils.leftPad(String.valueOf(NumberUtils.toInt(_month)), 2, "0");
        if ("01".compareTo(strMonth) >= 0 && "03".compareTo(strMonth) <= 0) {
            result = String.valueOf(NumberUtils.toInt(result) - 1);
        }
        result = result + strMonth;
        return result;
    }
}
