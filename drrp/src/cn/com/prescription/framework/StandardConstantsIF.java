/*
 * Copyright(c) 2011 
 */
package cn.com.prescription.framework;

import static cn.com.prescription.framework.common.SysConfigHandler.create;

import cn.com.prescription.framework.util.NumberUtils;

/**
 * 基盤定数.
 * 
 * @author nttdc
 */
public interface StandardConstantsIF {

    /** 共通：画面ロック */
    String KYOTU_GAMEN_LOCK = create().getPattern("kyotu_gamen_lock");
    /** 共通：PDF一時保存フォルダ */
    String KYOTU_KAO_PDF_TMP_HOZON_FOLDER = create().getPattern("kyotu_kao_pdf_hozon_folder");
    /** 共通：WORD保存フォルダ */
    String KYOTU_KAO_WORD_HOZON_FOLDER = create().getPattern("kyotu_kao_word_hozon_folder");
    /** 共通：サービス制御許可IPアドレス */
    String KYOTU_SERVICE_CONTROL_KYOKA_IP_ADD = create().getPattern("kyotu_service_control_kyoka_ip_add");
    /** 共通：取込ログ保存フォルダ */
    String KYOTU_TORIKOMI_LOG_HOZON_FOLDER = create().getPattern("kyotu_torikomi_log_hozon_folder");

    /** 共通：CSV ファイルのエンコーディング名 */
    String CSV_ENCODE = create().getPattern("csv_encode");
    /** 共通：CSV ファイルのエンコーディング名 */
    String CSV_ADJUST = create().getPattern("csv_adjust");
    /** 共通：CSV ファイルのエンコーディング名 */
    String CSV_ADJUST_RPHH09102 = create().getPattern("csv_adjust_RPHH09102");
    /** 共通：CSV ファイルのエンコーディング名 */
    String CSV_ADJUST_RPHH09201 = create().getPattern("csv_adjust_RPHH09201");

    /** 共通：一覧表示行数 */
    Integer KYOTU_LIST_HYOJI_LINE_SU = 10;
    /** 共通：検索件数上限 */
    String SEARCH_COUNT_JOGEN = create().getPattern("search_count_jogen");
    /** 共通：菜单上限値 */
    Integer KYOTU_MENU_JOGEN_VALUE = 50;
    /** 共通：菜单タブ段上限値 */
    Integer KYOTU_MENU_TABDAN_JOGEN_VALUE = 3;
    /** 共通：菜单タブ上限値 */
    Integer KYOTU_MENU_TAB_JOGEN_VALUE = 6;
    /** 共通：階層菜单上限値 */
    Integer KYOTU_HIERARCHY_MENU_JOGEN_VALUE = 15;

    /** 共通：元号開始年月日（明治） */
    Integer KYOTU_GENGO_START_YMD_MEIJI = 18681023;
    /** 共通：元号開始年月日（大正） */
    Integer KYOTU_GENGO_START_YMD_TAISHO = 19120730;
    /** 共通：元号開始年月日（昭和） */
    Integer KYOTU_GENGO_START_YMD_SHOWA = 19261225;
    /** 共通：元号開始年月日（平成） */
    Integer KYOTU_GENGO_START_YMD_HEISEI = 19890108;
    /** 共通：日历表示年数 */
    Integer KYOTU_CALENDAR_HYOJI_YEAR_SU = 150;
    /** 共通：曖昧検索区分 */
    Integer KYOTU_AIMAI_SEARCH_KBN = 1;
    /** 共通：画像イメージ保存フォルダルートパス */
    String KYOTU_IMAGE_IMAGE_SAVE_ROOT_PATH = create().getPattern("kyotu_image_image_save_root_path");
    /** 共通：取込ファイルサイズ上限値 */
    Integer KYOTU_TORIKOMI_FILE_SIZE_JOGEN_VALUE = 10485760;
    /** 共通：画像イメージファイルサイズ上限値 */
    Integer KYOTU_IMAGE_IMAGE_FILE_SIZE_JOGEN_VALUE = 1048576;
    /** 共通：画像イメージファイル形式 */
    String KYOTU_IMAGE_IMAGE_FILE_KEISHIKI = "image/bmp,image/gif,image/png,image/x-png,image/jpeg,image/pjpeg";
    /** 共通：画像サムネイル用サイズ長辺 */
    String KYOTU_IMAGE_THUMBNAIL_YO_SIZE_CHOHEN = "100";
    /** 共通：画像サムネイル用サイズ短辺 */
    String KYOTU_IMAGE_THUMBNAIL_YO_SIZE_TANPEN = "75";
    /** 共通：画像表示用サイズ長辺 */
    String KYOTU_IMAGE_HYOJI_YO_SIZE_CHOHEN = "800";
    /** 共通：画像表示用サイズ短辺 */
    String KYOTU_IMAGE_HYOJI_YO_SIZE_TANPEN = "600";
    /** 共通：消込データ保存フォルダルートパス（口座振替用） */
    String KYOTU_NEGATION_DATA_SAVE_ROOT_PATH_KOZA_FURIKAE = create()
        .getPattern("kyotu_negation_data_save_root_path_koza_furikae");
    /** 共通：消込データ保存フォルダルートパス（ＯＣＲ用） */
    String KYOTU_NEGATION_DATA_SAVE_ROOT_PATH_OCR = create().getPattern("kyotu_negation_data_save_root_path_ocr");
    /** 共通：消込データ保存フォルダルートパス（コンビニ収納用） */
    String KYOTU_NEGATION_DATA_SAVE_ROOT_PATH_CONVENIENT = create()
        .getPattern("kyotu_negation_data_save_root_path_convenient");
    /** 共通：消込データ保存フォルダルートパス（生活保護代理納付用） */
    String KYOTU_NEGATION_DATA_SAVE_ROOT_PATH_SEIKATU_HOGO = create()
        .getPattern("kyotu_negation_data_save_root_path_seikatu_hogo");
    /** 共通：取込ファイル形式 */
    String KYOTU_TORIKOMI_FILE_KEISHIKI = "csv,txt";
    /** 共通：掲示添付資料保存フォルダルートパス */
    String KYOTU_KEIJI_TEMP_SHIRYO_SAVE_ROOT_PATH = create().getPattern("kyotu_keiji_temp_shiryo_save_root_path");
    /** 共通：ファイル上传サイズ上限値 */
    Integer KYOTU_FILE_UPLOAD_SIZE_JOGEN_VALUE = 20480000;
    /** 共通：消込データ処理済フォルダルートパス（口座振替用） */
    String KYOTU_NEGATION_DATA_SHORI_ZUMI_ROOT_PATH_KOZA_FURIKAE = create()
        .getPattern("kyotu_negation_data_shori_zumi_root_path_koza_furikae");
    /** 共通：消込データ処理済フォルダルートパス（ＯＣＲ用） */
    String KYOTU_NEGATION_DATA_SHORI_ZUMI_ROOT_PATH_OCR = create()
        .getPattern("kyotu_negation_data_shori_zumi_root_path_ocr");
    /** 共通：消込データ処理済フォルダルートパス（財務納入通知書） */
    String KYOTU_NEGATION_DATA_SHORI_ZUMI_ROOT_PATH_ZAIMU = create()
        .getPattern("kyotu_negation_data_shori_zumi_root_path_zaimu");
    /** 共通：消込データ処理済フォルダルートパス（コンビニ収納用） */
    String KYOTU_NEGATION_DATA_SHORI_ZUMI_ROOT_PATH_CONVENIENT = create()
        .getPattern("kyotu_negation_data_shori_zumi_root_path_convenient");
    /** 共通：消込データ処理済フォルダルートパス（生活保護代理納付用） */
    String KYOTU_NEGATION_DATA_SHORI_ZUMI_ROOT_PATH_SEIKATU_HOGO = create()
        .getPattern("kyotu_negation_data_shori_zumi_root_path_seikatu_hogo");
    /** 共通：掲示添付資料ファイルサイズ上限値 */
    Integer KYOTU_KEIJI_TEMP_SHIRYO_FILE_SIZE_JOGEN_VALUE = 3145728;
    /** 共通：掲示添付資料ファイル形式 */
    String KYOTU_KEIJI_TEMP_SHIRYO_FILE_KEISHIKI = "image/bmp,image/gif,image/jpeg,image/png,image/x-png,application/msword,application/vnd.openxmlformats-officedocument.wordprocessingml.documentapplication/vnd.ms-excel,application/vnd.openxmlformats-officedocument.spreadsheetml.sheetapplication/pdf,application/zip,application/x-compress,application/x-lha-compressed,application/x-zip-compressed,multipart/x-zip,text/plain,application/octet-stream";
    /** 共通：添付資料ファイルサイズ上限値 */
    Integer KYOTU_TEMPU_SHIRYO_FILE_SIZE_JOGENCHI = 1048576;
    /** 共通：添付資料ファイル形式 */
    String KYOTU_TEMPU_SHIRYO_FILE_KEISHIKI = "image/bmp,image/gif,image/jpeg,image/png,image/x-png,application/msword,application/vnd.openxmlformats-officedocument.wordprocessingml.documentapplication/vnd.ms-excel,application/vnd.openxmlformats-officedocument.spreadsheetml.sheetapplication/pdf,application/zip,application/x-compress,application/x-lha-compressed,application/x-zip-compressed,multipart/x-zip,text/plain,application/octet-stream";
    /** 共通：年度別修繕費帳票出力列数 */
    Integer KYOTU_NENDO_BETU_SHUUZEN_COST_CHOHYO_OUT_LINE_SU = 8;
    /** 共通：収入データ保存フォルダルートパス */
    String KYOTU_REVENUE_DATA_SAVE_ROOT_PATH = create().getPattern("kyotu_revenue_data_save_root_path");
    /** 共通：収入データ処理済フォルダルートパス */
    String KYOTU_REVENUE_DATA_SHORI_ZUMI_ROOT_PATH = create().getPattern("kyotu_revenue_data_shori_zumi_root_path");
    /** 共通：修繕データ保存フォルダルートパス */
    String KYOTU_SHUUZEN_DATA_SAVE_ROOT_PATH = create().getPattern("kyotu_shuuzen_data_save_root_path");
    /** 共通：修繕データ処理済フォルダルートパス */
    String KYOTU_SHUUZEN_DATA_SHORI_ZUMI_ROOT_PATH = create().getPattern("kyotu_shuuzen_data_shori_zumi_root_path");
    /** 共通：生活保護データ保存フォルダルートパス */
    String KYOTU_SEIKATU_HOGO_DATA_SAVE_ROOT_PATH = create().getPattern("kyotu_seikatu_hogo_data_save_root_path");
    /** 共通：生活保護データ処理済フォルダルートパス */
    String KYOTU_SEIKATU_HOGO_DATA_SHORI_ZUMI_ROOT_PATH = create()
        .getPattern("kyotu_seikatu_hogo_data_shori_zumi_root_path");
    /** 共通：金融機関マスタデータ処理済フォルダルートパス */
    String KYOTU_JKM_KINYUUKIKAN_DATA_SHORI_SUMI_FOLDER_ROUTE_PATH = create()
        .getPattern("kyotu_jkm_kinyuukikan_data_shori_sumi_folder_route_path");
    /** 共通：入居希望者データ処理済フォルダルートパス */
    String KYOTU_NYUKYO_KIBOU_SHA_DATA_SHORI_ZUMI_ROOT_PATH = create()
        .getPattern("kyotu_nyukyo_kibou_sha_data_shori_zumi_root_path");
    /** 共通：入居者データ処理済フォルダルートパス */
    String KYOTU_NYUKYO_SHA_DATA_SHORI_ZUMI_ROOT_PATH = create()
        .getPattern("kyotu_nyukyo_sha_data_shori_zumi_root_path");
    /** 共通：郵便番号データ処理済フォルダルートパス */
    String KYOTU_ZIPCD_DATA_SHORI_ZUMI_ROOT_PATH = create().getPattern("kyotu_zipcd_data_shori_zumi_root_path");
    /** 共通：課税基準データ処理済フォルダルートパス */
    String KYOTU_TAXATION_KIJUN_DATA_SHORI_ZUMI_ROOT_PATH = create()
        .getPattern("kyotu_taxation_kijun_data_shori_zumi_root_path");

    /** 共通：外字使用区分 */
    String KYOTU_GAIJI_USE_KBN = "0";
    /** 共通：ゆうちょ銀行（金融機関コード） */
    String KYOTU_POST_BANK_FINANCE_ORGANIZATION_CD = "9900";
    /** 共通：消込データ削除上限値 */
    Integer KYOTU_NEGATION_DATA_DEL_JOGEN_VALUE = 100;
    /** 共通：共通スキーマ */
    String KYOTU_KYOTU_SCHEMA = create().getPattern("kyotu_kyotu_schema");
    /** 共通：アプリケーション汎用ログ保存フォルダ */
    String KYOTU_APP_HANYO_LOG_SAVE_FOLDER = create().getPattern("kyotu_app_hanyo_log_save_folder");
    /** 共通：バッチログ保存フォルダ */
    String KYOTU_BATCH_LOG_SAVE_FOLDER = create().getPattern("kyotu_batch_log_save_folder");
    /** 共通：出力ファイル一時保存フォルダ */
    String KYOTU_OUT_FILE_TMP_SAVE_FOLDER = create().getPattern("kyotu_out_file_tmp_save_folder");
    /** 共通：レポート定義ファイル保存フォルダ */
    String KYOTU_REPORT_TEIGI_FILE_SAVE_FOLDER = create().getPattern("kyotu_report_teigi_file_save_folder");
    /** 共通：CSVファイル一時保存フォルダ */
    String KYOTU_CSV_FILE_TMP_SAVE_FOLDER = create().getPattern("kyotu_csv_file_tmp_save_folder");
    /** 共通：CSVファイル一時保存フォルダ */
    String KYOTU_EXCEL_FILE_TMP_SAVE_FOLDER = create().getPattern("kyotu_excel_file_tmp_save_folder");
    /** 共通：ページサイズ */
    String KYOTU_PAGE_SIZE = "10";
    /** 共通：密码有効日数 */
    Integer KYOTU_PASSWORD_EFFECTIVE_DATE_SU = 180;
    /** 共通：ログファイル保存日数 */
    String KYOTU_LOG_FILE_HOZON_NISSU = create().getPattern("kyotu_log_file_hozon_nissu");

    /** 処理結果：正常終了 */
    String SHORI_KEKKA_NORMAL_END = "0";
    /** 処理結果：異常終了 */
    String SHORI_KEKKA_IJO_END = "1";
    /** 処理結果：その他 */
    String SHORI_KEKKA_SONOTA = "9";

    /** 区分（共通）：有 */
    String KBN_KYOTU_ARI = "1";
    /** 区分（共通）：無 */
    String KBN_KYOTU_NASI = "0";
    /** 区分（共通）：しない */
    String KBN_KYOTU_SINAI = "0";
    /** 区分（共通）：する */
    String KBN_KYOTU_SURU = "1";
    /** 区分（共通）：対象外 */
    String KBN_KYOTU_TAISYO_GAI = "0";
    /** 区分（共通）：対象 */
    String KBN_KYOTU_TAISYO = "1";
    /** 区分（共通）：不可 */
    String KBN_KYOTU_FUKA = "0";
    /** 区分（共通）：可 */
    String KBN_KYOTU_KA = "1";
    /** 区分（共通）：未入力 */
    String KBN_KYOTU_MI_IN = "0";
    /** 区分（共通）：入力済 */
    String KBN_KYOTU_IN_ZUMI = "1";
    /** 区分（共通）：不認定 */
    String KBN_KYOTU_FU_NINTEI = "0";
    /** 区分（共通）：認定 */
    String KBN_KYOTU_NINTEI = "1";
    /** 区分（共通）：不要 */
    String KBN_KYOTU_FUYO = "0";
    /** 区分（共通）：必要 */
    String KBN_KYOTU_HITUYO = "1";
    /** 区分（共通）：該当しない */
    String KBN_KYOTU_GAITO_SINAI = "0";
    /** 区分（共通）：該当する */
    String KBN_KYOTU_GAITO_SURU = "1";
    /** 区分（共通）：いいえ */
    String KBN_KYOTU_NO = "0";
    /** 区分（共通）：はい */
    String KBN_KYOTU_YES = "1";
    /** 区分（共通）：未定 */
    String KBN_KYOTU_MITEI = "0";
    /** 区分（共通）：決定 */
    String KBN_KYOTU_KETTEI = "1";
    /** 区分（共通）：未確定 */
    String KBN_KYOTU_MI_KAKUTEI = "0";
    /** 区分（共通）：確定済 */
    String KBN_KYOTU_KAKUTEI_ZUMI = "1";
    /** 区分（共通）：未解決 */
    String KBN_KYOTU_MI_KAIKETU = "0";
    /** 区分（共通）：解決済 */
    String KBN_KYOTU_KAIKETU_ZUMI = "1";
    /** 区分（共通）：印刷未了 */
    String KBN_KYOTU_PRINT_MIRYOU = "0";
    /** 区分（共通）：印刷完了 */
    String KBN_KYOTU_PRINT_KANRYO = "1";
    /** 区分（共通）：選択区分コード全て */
    String KBN_KYOTU_SENTAKU_KBN_CD_SUBETE = "すべて";

    /** 登録标识：未登録 */
    String TOROKU_FLG_MI_TOROKU = "0";
    /** 登録标识：登録済 */
    String TOROKU_FLG_TOROKU_ZUMI = "1";

    /** 消除标识：有効レコード */
    String DEL_FLG_YUKO_RECORD = "0";
    /** 消除标识：削除レコード */
    String DEL_FLG_DEL_RECORD = "1";

    /** ソート順：昇順 */
    String SORT_SEQ_ASC = "ASC";
    /** ソート順：降順 */
    String SORT_SEQ_DESC = "DESC";

    /** 日付フォーマットパターン：YYYYMMDD（年月日） */
    String DATE_FORMAT_YYYYMMDD = "yyyyMMdd";
    /** 日付フォーマットパターン：YYYYMM（年/月） */
    String DATE_FORMAT_YYYYMM = "yyyyMM";
    /** 日付フォーマットパターン：YYYY/MM/DD（年/月/日） */
    String DATE_FORMAT_YYYY_MM_DD = "yyyy/MM/dd";
    /** 日付フォーマットパターン：MM/DD（月/日） */
    String DATE_FORMAT_MM_DD = "MM/dd";
    /** 日付フォーマットパターン：YYYY（年） */
    String DATE_FORMAT_YYYY = "yyyy";
    /** 日付フォーマットパターン：MM（月） */
    String DATE_FORMAT_MM = "MM";
    /** 日付フォーマットパターン：M（月） */
    String HIZUKE_FORMAT_PTN_M_MONTH = "M";
    /** 日付フォーマットパターン：H（時） */
    String HIZUKE_FORMAT_PTN_H_HOUR = "H";
    /** 日付フォーマットパターン：m（分） */
    String HIZUKE_FORMAT_PTN_M_MINUTE = "m";
    /** 日付フォーマットパターン：DD（日） */
    String DATE_FORMAT_DD = "dd";
    /** 日付フォーマットパターン：a（AM/PM） */
    String DATE_FORMAT_AM_PM = "a";
    /** 日付フォーマットパターン：yyyy/MM/dd|HH:mm:ss（年/月/日|時:分:秒） */
    String DATE_FORMAT_YYYY_MM_DD_HH_MM_SS_VERTICAL_BAR = "yyyy/MM/dd|HH:mm:ss";
    /** 日付フォーマットパターン：yyyy/MM/dd|HH:mm:ss.SSS（年/月/日|時:分:秒.ミリ秒） */
    String DATE_FORMAT_YYYY_MM_DD_HH_MM_SS_SSS_VERTICAL_BAR = "yyyy/MM/dd|HH:mm:ss.SSS";
    /** 日付フォーマットパターン：yyyyMMddHHmmss（年月日時分秒） */
    String DATE_FORMAT_YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
    /** 日付フォーマットパターン：yyyyMMddHHmmssSSS（年月日時分秒ミリ秒） */
    String DATE_FORMAT_YYYYMMDDHHMMSSSSS = "yyyyMMddHHmmssSSS";
    /** 日付フォーマットパターン：yyyy/MM/dd HH:mm（年/月/日 時:分） */
    String DATE_FORMAT_YYYY_MM_DD_HH_MM = "yyyy/MM/dd HH:mm";
    /** 日付フォーマットパターン：yyyy/MM/dd HH:mm:ss.SSS（年/月/日 時:分:秒.ミリ秒） */
    String DATE_FORMAT_YYYY_MM_DD_HH_MM_SS_SSS = "yyyy/MM/dd HH:mm:ss.SSS";
    /** 日付フォーマットパターン：yyyy/MM（年/月） */
    String DATE_FORMAT_YYYY_MM = "yyyy/MM";
    /** 日付フォーマットパターン：ログファイル生成用日付書式 */
    String DATE_FORMAT_LOG_FILE = "yyyyMMdd_HHmmss_SSS";
    /** 日付フォーマットパターン：MM/dd HH:mm（月/日 時:分） */
    String DATE_FORMAT_MM_DD_HH_MM = "MM/dd HH:mm";
    /** 日付フォーマットパターン：yyyy年MM月dd日 */
    String DATE_FORMAT_YYYY_YEAR_MM_MONTH_DD_DATE = "yyyy年MM月dd日";
    /** 日付フォーマットパターン：yyyy年MM月dd日（E） */
    String DATE_FORMAT_YYYY_YEAR_MM_MONTH_DD_DATE_E = "yyyy年MM月dd日（E）";
    /** 日付フォーマットパターン：yyyy年MM月dd日（E曜） */
    String DATE_FORMAT_YYYY_YEAR_MM_MONTH_DD_DATE_E_YOU = "yyyy年MM月dd日（E曜）";
    /** 日付フォーマットパターン：yyyy年MM月dd日（E曜日） */
    String DATE_FORMAT_YYYY_YEAR_MM_MONTH_DD_DATE_E_YOBI = "yyyy年MM月dd日（E曜日）";
    /** 日付フォーマットパターン：yyyy年MM月 */
    String DATE_FORMAT_YYYY_YEAR_MM_MONTH = "yyyy年MM月";
    /** 日付フォーマットパターン：MM月dd日 */
    String DATE_FORMAT_MM_MONTH_DD_DATE = "MM月dd日";
    /** 日付フォーマットパターン：E */
    String DATE_FORMAT_E = "E";
    /** 日付フォーマットパターン：E曜 */
    String DATE_FORMAT_E_YOU = "E曜";
    /** 日付フォーマットパターン：E曜日 */
    String DATE_FORMAT_E_YOBI = "E曜日";
    /** 日付フォーマットパターン：（E） */
    String DATE_FORMAT_E_KAKO = "（E）";
    /** 日付フォーマットパターン：（E曜） */
    String DATE_FORMAT_E_YOU_KAKO = "（E曜）";
    /** 日付フォーマットパターン：（E曜日） */
    String DATE_FORMAT_E_YOBI_KAKO = "（E曜日）";
    /** 日付フォーマットパターン：タイムスタンプ */
    String DATE_FORMAT_TIMESTAMP = "yyyy-MM-dd HH:mm:ss.SSS";
    /** 日付フォーマットパターン：MM月dd日（E） */
    String DATE_FORMAT_MM_MONTH_DD_DATE_E = "MM月dd日（E）";
    /** 日付フォーマットパターン：MM月dd日（E曜） */
    String DATE_FORMAT_MM_MONTH_DD_DATE_E_YOU = "MM月dd日（E曜）";
    /** 日付フォーマットパターン：MM月dd日（E曜日） */
    String DATE_FORMAT_MM_MONTH_DD_DATE_E_YOBI = "MM月dd日（E曜日）";
    /** 日付フォーマットパターン：dd日（E） */
    String DATE_FORMAT_DD_DATE_E = "dd日（E）";
    /** 日付フォーマットパターン：dd日（E曜） */
    String DATE_FORMAT_DD_DATE_E_YOU = "dd日（E曜）";
    /** 日付フォーマットパターン：dd日（E曜日） */
    String DATE_FORMAT_DD_DATE_E_YOBI = "dd日（E曜日）";
    /** 日付フォーマットパターン：dd（E） */
    String DATE_FORMAT_DD_E = "dd（E）";
    /** 日付フォーマットパターン：dd（E曜） */
    String DATE_FORMAT_DD_E_YOU = "dd（E曜）";
    /** 日付フォーマットパターン：dd（E曜日） */
    String DATE_FORMAT_DD_E_YOBI = "dd（E曜日）";
    /** 日付フォーマットパターン：Gyy */
    String DATE_FORMAT_GYY = "Gyy";
    /** 日付フォーマットパターン：GGGGyy年MM月dd日 HH時mm分 */
    String DATE_FORMAT_GGGGYY_YEAR_MM_MONTH_DD_DATE_HH_JI_MM_MINUTE = "GGGGyy年MM月dd日 HH時mm分";
    /** 日付フォーマットパターン：GGGGyy年MM月dd日 */
    String DATE_FORMAT_GGGGYY_YEAR_MM_MONTH_DD_DATE = "GGGGyy年MM月dd日";
    /** 日付フォーマットパターン：GGGGyy年MM月 */
    String DATE_FORMAT_GGGGYY_YEAR_MM = "GGGGyy年MM月";
    /** 日付フォーマットパターン：Gyy.MM.dd */
    String DATE_FORMAT_GYY_MM_DD = "Gyy.MM.dd";
    /** 日付フォーマットパターン：Gyy.MM */
    String DATE_FORMAT_GYY_MM = "Gyy.MM";
    /** 日付フォーマットパターン：GyyMM */
    String DATE_FORMAT_GYYMM = "GyyMM";
    /** 日付フォーマットパターン：GyyMMdd */
    String DATE_FORMAT_GYYMMDD = "GyyMMdd";
    /** 日付フォーマットパターン：GGGGyy */
    String DATE_FORMAT_GGGGYY = "GGGGyy";
    /** 日付フォーマットパターン：Gyy年 */
    String DATE_FORMAT_GYY_YEAR = "Gyy年";
    /** 日付フォーマットパターン：GGYY.MM.DD（元号年月日） */
    String DATE_FORMAT_GGYY_MM_DD = "GGyy.MM.dd";
    /** 日付フォーマットパターン：GGGGYY.MM.DD（元号年月日） */
    String DATE_FORMAT_GGGGYY_MM_DD = "GGGGyy.MM.dd";
    /** 日付フォーマットパターン（時刻）：HHmmss（時分秒） */
    String DATE_FORMAT_TIME_HHMMSS = "HHmmss";
    /** 日付フォーマットパターン（時刻）：HH:mm:ss（時:分:秒） */
    String DATE_FORMAT_TIME_HH_MM_SS = "HH:mm:ss";
    /** 日付フォーマットパターン（時刻）：HHmm（時分） */
    String DATE_FORMAT_TIME_HHMM = "HHmm";
    /** 日付フォーマットパターン（時刻）：HH:mm（時:分） */
    String DATE_FORMAT_TIME_HH_MM_JI_MINUTE = "HH:mm";
    /** 日付フォーマットパターン（時刻）：HH（時） */
    String DATE_FORMAT_TIME_HH = "HH";
    /** 日付フォーマットパターン（時刻）：mm（分） */
    String DATE_FORMAT_TIME_MM = "mm";
    /** 日付フォーマットパターン（時刻）：ss（秒） */
    String DATE_FORMAT_TIME_SS = "ss";

    /** 数値フォーマットパターン：#,### */
    String SUTI_FORMAT_PTN_NUM = "#,###";
    /** 数値フォーマットパターン：#,###円 */
    String SUTI_FORMAT_PTN_NUM_YEN = "#,###円";
    /** 数値フォーマットパターン：#,###.00 */
    String SUUI_FORMAT_PTN_NUM_POINT_DOUBLE_ZERO = "#,###.00";

    /** 操作区分：初期化 */
    String SOSA_KBN_INIT = "00";
    /** 操作区分：検索 */
    String SOSA_KBN_SEARCH = "01";
    /** 操作区分：新規 */
    String SOSA_KBN_SHINKI = "02";
    /** 操作区分：更新 */
    String SOSA_KBN_UPDATE = "03";
    /** 操作区分：削除 */
    String SOSA_KBN_DEL = "04";
    /** 操作区分：取消 */
    String SOSA_KBN_TORIKESI = "05";
    /** 操作区分：新規確認 */
    String SOSA_KBN_SHINKI_CONFIRM = "06";

    /** 制御ステータス：実行待ち */
    String CONTROL_STATUS_EXECUTE_WAIT = "1";
    /** 制御ステータス：実行中 */
    String CONTROL_STATUS_EXECUTE_TYU = "2";
    /** 制御ステータス：異常終了 */
    String CONTROL_STATUS_IJO_END = "4";
    /** 制御ステータス：正常終了 */
    String CONTROL_STATUS_NORMAL_END = "3";

    /** バッチ処理区分：バッチ */
    String BATCHI_SHORI_KUBUN_BATCHI = "0";
    /** バッチ処理区分：オンライン */
    String BATCHI_SHORI_KUBUN_ONLINE = "1";

    /** 処理結果区分：成功 */
    String SHORI_KEKKA_KBN_SUCCESS = "01";
    /** 処理結果区分：排他错误（新規） */
    String SHORI_KEKKA_KBN_HAITA_ERR_SHINKI = "02";
    /** 処理結果区分：排他错误（更新） */
    String SHORI_KEKKA_KBN_HAITA_ERR_UPDATE = "03";
    /** 処理結果区分：排他错误（削除） */
    String SHORI_KEKKA_KBN_HAITA_ERR_DEL = "04";

    /** チェックボックス标识：チェック済 */
    String CHECKBOX_FLG_CHECKED = "1";
    /** チェックボックス标识：チェック未 */
    String CHECKBOX_FLG_UNCHECKED = "0";

    /** チェックボックス値：True */
    boolean CHECKBOX_VALUE_TRUE = true;
    /** チェックボックス値：False */
    boolean CHECKBOX_VALUE_FALSE = false;

    /** コメント行判定文字：CSV取込ファイル */
    String COMMENT_LINE_HANTEI_MOJI_CSV_TORIKOMI_FILE = "#";

    /** 文字：ダブルクォーテーション */
    String MOJI_DOUBLE_QUOTATION = "\"";

    /** バッチ業務ステータス：処理中止 */
    String BATCH_GYOUMU_STATUS_SHORI_TYUSI = "1";
    /** バッチ業務ステータス：処理完了 */
    String BATCH_GYOUMU_STATUS_SHORI_KANRYO = "2";

    /** 日付フォーマットパターン：yyyy/MM/dd HH:mm:ss（年/月/日|時:分:秒） */
    String DATE_FORMAT_YYYY_MM_DD_HH_MM_SS = "yyyy/MM/dd HH:mm:ss";

    /** 数値フォーマットパターン：面積 */
    String SUTI_FORMAT_PTN_MENSEKI = "#,##0.00";

    /** 共通：棟添付画像保存フォルダルートパス */
    String KYOTU_TOU_TEMP_IMAGE_SAVE_FOLDER_ROOT_PATH = create()
        .getPattern("kyotu_tou_temp_image_save_folder_root_path");
    /** 共通：団地添付画像保存フォルダルートパス */
    String KYOTU_DANCHI_TEMP_IMAGE_SAVE_FOLDER_ROOT_PATH = create()
        .getPattern("kyotu_danchi_temp_image_save_folder_root_path");
    /** 共通：住戸添付画像保存フォルダルートパス */
    String KYOTU_JUKO_TEMP_IMAGE_SAVE_FOLDER_ROOT_PATH = create()
        .getPattern("kyotu_juko_temp_image_save_folder_root_path");
    /** 共通：住戸タイプ添付画像保存フォルダルートパス */
    String KYOTU_JUKO_TYPE_TEMP_IMAGE_SAVE_FOLDER_ROOT_PATH = create()
        .getPattern("kyotu_juko_type_temp_image_save_folder_root_path");
    /** 共通：基準月 */
    String KYOTU_KIJUN_MONTH = "04";
    /** 共通：文字コード */
    String KYOTU_MOJI_CD = create().getPattern("kyotu_moji_cd");
    /** 共通：家賃・駐車場使用料・共益費徴収データ作成パス */
    String KYOTU_YACHIN_CHUSHAJO_KYOEKI_PATH = create().getPattern("kyotu_yachin_chushajo_kyoeki_path");
    /** 共通：変更件数上限値 */
    Integer KYOTU_HENKO_KENSUU_JOGENCHI = NumberUtils.toInt(create().getPattern("kyotu_henko_kensuu_jogenchi"));
    /** 共通：消込データ消込上限値 */
    Integer KYOTU_NEGATION_DATA_NEGATION_JOGEN_VALUE = 100;

    /** 共通：口座振替データ出力フォルダルートパス */
    String KYOTU_KOZA_FURIKAE_DATA_OUT_FOLDER_ROOT_PATH = create()
        .getPattern("kyotu_koza_furikae_data_out_folder_root_path");

    /** 日付フォーマットパターン：MM.dd（点） */
    String DATE_FORMAT_MM_DD_TEN = "MM.dd";

    /** 共通：ヘッダーレコード */
    String KYOTU_HEADER_RECORD = "1";
    /** 共通：データレコード */
    String KYOTU_DATA_RECORD = "2";
    /** 共通：トレーラレコード */
    String KYOTU_TRAILER_RECORD = "8";
    /** 共通：エンドレコード */
    String KYOTU_END_RECORD = "9";
    /** 共通：外字サーバー有効 */
    String KYOTU_GAIJI_SERVER_YUKO = create().getPattern("kyotu_gaiji_server_yuko");
    /** 共通：外字サーバーアドレス */
    String KYOTU_GAIJI_SERVER_ADD = create().getPattern("kyotu_gaiji_server_add");
    /** 共通：系统リビジョン */
    String PACKAGE_REVISION = "20110224";
    /** 共通：バッチ用户ID */
    String KYOTU_BATCH_USER_ID = create().getPattern("kyotu_batch_user_id");

    /** セッションキー：掲示板．検索条件 */
    String SESSION_KEY_KEIJIBAN_SEARCH_JOKEN = "schm_search_joken";
    /** セッションキー：掲示板．掲示件名登録年月 */
    String SESSION_KEY_KEIJIBAN_KEIJI_KENMEI_TOROKU_YM = "schm_kenmei_toroku_ym";
    /** セッションキー：掲示板．掲示件名連番 */
    String SESSION_KEY_KEIJIBAN_KEIJI_KENMEI_SEQ_NO = "schm_keiji_kenmei_seq_no";
    /** セッションキー：掲示板．掲示カテゴリコード */
    String SESSION_KEY_KEIJIBAN_KEIJI_CATEGORY_CD = "schm_keiji_category_cd";

    /** 曖昧検索区分：設定値 */
    String AIMAI_SEARCH_KBN_SETTEI_VALUE = create().getPattern("aimai_search_kbn_settei_value");

    /** 日付フォーマットパターン：GGGGyy年MM月dd日（E） HH:mm:ss */
    String DATE_FORMAT_GGGGYY_YEAR_MM_MONTH_DD_DATE_E_HH_MM_SS = "GGGGyy年MM月dd日（E） HH:mm:ss";

    /** 共通：登录失敗回数上限 */
    String KYOTU_LOGIN_SIPPAI_SU_JOGEN = create().getPattern("kyotu_login_sippai_su_jogen");
    /** 共通：登录失敗回数無効 */
    String KYOTU_LOGIN_SIPPAI_SU_MUKO = create().getPattern("kyotu_login_sippai_su_muko");

    /** 日付フォーマットパターン：BATCHログファイル生成用日付書式 */
    String DATE_FORMAT_BATCH_LOG_FILE = "yyyyMMdd_HHmmss";
    /** 菜单タイプ：ページ */
    String MENU_TYPE_PAGE = "0";
    /** 菜单タイプ：菜单 */
    String MENU_TYPE_MENU = "1";

    /** 日付フォーマットパターン：GGGGYY.MM（元号年月） */
    String DATE_FORMAT_GGGGYY_MM = "GGGGyy.MM";
    /** 共通：年間基礎日数 */
    Integer KYOTU_ANNUAL_KISO_NISSU = 365;

    /** 帳票出力：定期点検結果時系列一覧表明細数 */
    Integer CHOHYO_OUT_TEIKI_CHECKOUT_KEKKA_JI_KEI_LINE_LIST_HYO_DETAIL_SU = 65;
    /** 帳票出力：定期点検記録表（総括表）明細数 */
    Integer CHOHYO_OUT_TEIKI_CHECKOUT_KIROKU_HYO_SOUKATU_HYO_DETAIL_SU = 65;
    /** 帳票出力：定期点検結果ID出力列数 */
    Integer CHOHYO_OUT_TEIKI_CHECKOUT_KEKKA_ID_OUT_LINE_SU = 5;
    /** 帳票出力：建物健全度一覧表明細数 */
    Integer CHOHYO_OUT_BUILDING_KENZENDO_LIST_HYO_DETAIL_SU = 30;
    /** 帳票出力：部位別劣化状態出力列数 */
    Integer CHOHYO_OUT_BUI_BETU_REKA_JOTAI_OUT_LINE_SU = 4;
    /** 帳票出力：団地別管理状況明細数 */
    Integer CHOHYO_OUT_DANCHI_BETU_KANRI_JOKYO_DETAIL_SU = 30;

    /** 共通：帳票ファイル保存期間 */
    Integer KYOTU_PRINT_FIILE_SAVE_KIKAN = 6;
    /** 共通：帳票ファイル保存フォルダルートパス */
    String KYOTU_PRINT_FILE_SAVE_ROOT_PATH = create().getPattern("kyotu_print_file_save_root_path");

    /** 共通：一覧表示行数14行 */
    Integer KYOTU_LIST_HYOJI_LINE_SU_14_LINE = 14;
    /** 共通：定期点検添付画像保存フォルダルートパス */
    String KYOTU_TEIKI_TENKEN_TEMP_IMAGE_SAVE_FOLDER_ROOT_PATH = create()
        .getPattern("kyotu_teiki_tenken_temp_image_save_folder_root_path");
    /** 共通：金融機関ファイル保存フォルダルートパス */
    String KINNYU_KIKAN_FILE_SAVE_ROOT_PATH = create().getPattern("kinnyu_kikan_file_save_root_path");
    /** 共通：金融機関ファイル処理済フォルダルートパス */
    String KINNYU_KIKAN_FILE_SYORI_ZUMI_ROOT_PATH = create().getPattern("kinnyu_kikan_file_syori_zumi_root_path");

    /** 日付フォーマットパターン：GGGGyy年 */
    String DATE_FORMAT_GGGGYY_YEAR = "GGGGyy年";

    /** 日付フォーマットパターン：GGGGyy年度 */
    String DATE_FORMAT_GGGGYY_NENDO = "GGGGyy年度";

    /** 共通：郵便番号データ保存フォルダルートパス */
    String YUBIN_BANGOU_FILE_SAVE_ROOT_PATH = create().getPattern("yubin_bangou_file_save_root_path");
    /** 共通：法定点検添付画像保存フォルダルートパス */
    String KYOTU_HOTEI_CHECKOUT_TEMP_IMAGE_SAVE_FOLDER_ROOT_PATH = create()
        .getPattern("kyotu_hotei_checkout_temp_image_save_folder_root_path");

    /** 共通：年度開始日 */
    String KYOTU_NENDO_START_DATE = "0401";
    /** 共通：年度終了日 */
    String KYOTU_NENDO_END_DATE = "0331";

    /** 共通：入居希望者一括登録保存期間（月数） */
    String KYOTU_NYUKYO_KIBOU_SHA_BATCH_TOROKU_SAVE_KIKAN = create()
        .getPattern("kyotu_nyukyo_kibou_sha_batch_toroku_save_kikan");
    /** 共通：入居者一括登録保存期間（月数） */
    String KYOTU_NYUKYO_SHA_BATCH_TOROKU_SAVE_KIKAN = create().getPattern("kyotu_nyukyo_sha_batch_toroku_save_kikan");
    /** 共通：口座振替結果データ保存期間（月数） */
    String KYOTU_KOZA_FURIKAE_KEKKA_DATA_SAVE_KIKAN = create().getPattern("kyotu_koza_furikae_kekka_data_save_kikan");
    /** 共通：ＯＣＲ消込データ保存期間（月数） */
    String KYOTU_OCR_NEGATION_DATA_SAVE_KIKAN = create().getPattern("kyotu_ocr_negation_data_save_kikan");
    /** 共通：コンビニ収納消込データ保存期間（月数） */
    String KYOTU_CONVENIENT_STORE_STORAGE_NEGATION_DATA_SAVE_KIKAN = create()
        .getPattern("kyotu_convenient_store_storage_negation_data_save_kikan");
    /** 共通：生活保護代理収納消込データ保存期間（月数） */
    String KYOTU_SEIKATU_PROTECTION_PROCURATION_STORAGE_NEGATION_DATA_SAVE_KIKAN = create()
        .getPattern("kyotu_seikatu_protection_procuration_storage_negation_data_save_kikan");
    /** 共通：課税基準情報（収入情報）保存期間（月数） */
    String KYOTU_TAXATION_KIJUN_INFO_REVENUE_INFO_SAVE_KIKAN = create()
        .getPattern("kyotu_taxation_kijun_info_revenue_info_save_kikan");
    /** 共通：調定情報保存期間（月数） */
    String KYOTU_CHOTEI_INFO_SAVE_KIKAN = create().getPattern("kyotu_chotei_info_save_kikan");
    /** 共通：口座振替依頼情報保存期間（月数） */
    String KYOTU_KOZA_FURIKAE_IRAI_INFO_SAVE_KIKAN = create().getPattern("kyotu_koza_furikae_irai_info_save_kikan");
    /** 共通：収入情報保存期間（月数） */
    String KYOTU_REVENUE_INFO_SAVE_KIKAN = create().getPattern("kyotu_revenue_info_save_kikan");
    /** 共通：修繕情報保存期間（月数） */
    String KYOTU_SHUUZEN_INFO_SAVE_KIKAN = create().getPattern("kyotu_shuuzen_info_save_kikan");
    /** 共通：生活保護情報保存期間（月数） */
    String KYOTU_SEIKATU_PROTECTION_INFO_SAVE_KIKAN = create().getPattern("kyotu_seikatu_protection_info_save_kikan");
    /** 共通：出力データ保存期間（月数） */
    String KYOTU_OUT_DATA_SAVE_KIKAN = create().getPattern("kyotu_out_data_save_kikan");
    /** 共通：金融機関マスタ保存期間（月数） */
    String KYOTU_JKM_KINYUUKIKAN_HOZON_KIKAN_TSUKI_SUU = create()
        .getPattern("kyotu_jkm_kinyuukikan_hozon_kikan_tsuki_suu");
    /** 共通：郵便番号情報保存期間（月数） */
    String KYOTU_ZIPCD_INFO_SAVE_KIKAN = create().getPattern("kyotu_zipcd_info_save_kikan");
    /** 共通：操作ログ保存期間（月数） */
    String KYOTU_SOSA_LOG_SAVE_KIKAN = create().getPattern("kyotu_sosa_log_save_kikan");
    /** 共通：カンマ */
    char KYOTU_COMMA = ',';
    /** 共通：ハイフン */
    String KYOTU_HYPHEN = "-";
    /** 共通：操作ログ保存フォルダルートパス */
    String KYOTU_SOSA_LOG_SAVE_FOLDER_ROOT_PATH = create().getPattern("kyotu_sosa_log_save_folder_root_path");
    /** 共通：公印画像保存フォルダルートパス */
    String KYOTU_KOIN_IMAGE_SAVE_FOLDER_ROOT_PATH = create().getPattern("kyotu_koin_image_save_folder_root_path");
    /** 共通：处方画像保存フォルダルートパス */
    String KYOTU_PRESCRIPTION_IMAGE_SAVE_FOLDER_ROOT_PATH = create()
        .getPattern("kyotu_prescription_image_save_folder_root_path");
    /** 日付フォーマットパターン：GGGGyy.MM.dd HH:mm */
    String DATE_FORMAT_GGGGYY_MM_DD_HH_MM = "GGGGyy.MM.dd HH:mm";
    /** 共通：出力データ保存フォルダルートパス */
    String KYOTU_OUT_DATA_SAVE_FOLDER_ROOT_PATH = create().getPattern("kyotu_out_data_save_folder_root_path");
    /** 共通：調定情報保存フォルダルートパス */
    String KYOTU_CHOTEI_INFO_SAVE_FOLDER_ROOT_PATH = create().getPattern("kyotu_chotei_info_save_folder_root_path");
    /** 共通：出力データ処理済フォルダルートパス */
    String KYOTU_OUT_DATA_SHORI_ZUMI_FOLDER_ROOT_PATH = create()
        .getPattern("kyotu_out_data_shori_zumi_folder_root_path");
    /** 共通：調定情報処理済フォルダルートパス */
    String KYOTU_CHOTEI_INFO_SHORI_ZUMI_FOLDER_ROOT_PATH = create()
        .getPattern("kyotu_chotei_info_shori_zumi_folder_root_path");
    /** 共通：メール本文保存パス．未処理件数の定期通知 */
    String KYOTU_MAIL_HOMBUN_HOZON_PATH_MI_SHORI_KENSUU_NO_TEIKI_TSUUCHI = create()
        .getPattern("kyotu_mail_hombun_hozon_path_mi_shori_kensuu_no_teiki_tsuuchi");

    /** 帳票検索件数上限値：無制限 */
    Integer CHOHYO_SEARCH_KENSU_JOGEN_VALUE_UNLIMITED = 0;

    /** 日付フォーマットパターン：HHmmss（時分秒） */
    String DATE_FORMAT_HHMMSS = "HHmmss";

    /** 共通：バッチPDFファイル保存フォルダ */
    String KYOTU_BATCH_PDF_FILE_SAVE_FOLDER = create().getPattern("kyotu_batch_pdf_file_save_folder");
    /** 共通：バッチCSVファイル保存フォルダ */
    String KYOTU_BATCH_CSV_FILE_SAVE_FOLDER = create().getPattern("kyotu_batch_csv_file_save_folder");
    /** 日付フォーマットパターン：GGGGyy/MM/dd HH:mm */
    String DATE_FORMAT_GGGGYY_MM_DD_HH_MM_SYASEN = "GGGGyy/MM/dd HH:mm";
    /**
     * カナ文字の記号
     * <ul>
     * <li>(:0x0028</li>
     * <li>):0x0029</li>
     * <li>-(ハイフン):0x002D</li>
     * <li>.(ピリオド):0x002E</li>
     * <li>スペース:0x0020</li>
     * </ul>
     */
    char[] KANA_KIGOU = { 0x0028, 0x0029, 0x002D, 0x002E, 0x0020 };

    /** 共通：画像イメージファイル数上限 */
    String KYOTU_IMAGE_IMAGE_FILE_SU_JOGEN = "100";

    /** 共通：実行ログ保存期間（月数） */
    String KYOTU_EXECUTE_LOG_SAVE_KIKAN_MONTH_SU = create().getPattern("kyotu_execute_log_save_kikan_month_su");
    /** 共通：実行ログ保存フォルダルートパス */
    String KYOTU_EXECUTE_LOG_SAVE_FOLDER_ROOT_PATH = create().getPattern("kyotu_execute_log_save_folder_root_path");
    /** 共通：系统関連ログ保存期間（月数） */
    String KYOTU_SYSTEM_RELATED_LOG_SAVE_KIKAN_MONTH_SU = create()
        .getPattern("kyotu_system_related_log_save_kikan_month_su");
    /** 共通：系统関連ログ保存フォルダルートパス */
    String KYOTU_SYSTEM_RELATED_LOG_SAVE_FOLDER_ROOT_PATH = create()
        .getPattern("kyotu_system_related_log_save_folder_root_path");

    /** 帳票サーバ：ポート */
    int CHOHYO_SERVER_PORT = NumberUtils.toInt(create().getPattern("chohyo_server_port"));
    /** 帳票サーバ：IPアドレス */
    String CHOHYO_SERVER_IP_ADD = create().getPattern("chohyo_server_ip_add");
    /** 帳票サーバ：resultFileName */
    String CHOHYO_SERVER_RESULTFILENAME = create().getPattern("chohyo_server_resultfilename");

    /** 共通：系统コード */
    String KYOTU_SYSTEM_CD = "HO";
    /** 共通：サブ系统コード */
    String KYOTU_SABU_SYSTEM_CD = "HR";

    /** 共通：口座振替データ作成対象上限値 */
    String KYOTU_KOZA_FURIKAE_DATA_SAKUSEI_TAISHO_JOGENCHI = create()
        .getPattern("kyotu_koza_furikae_data_sakusei_taisho_jogenchi");

    /** 共通：消込データ保存フォルダルートパス（納入通知書用） */
    String KYOTU_KESHIKOMI_DATA_HOZON_FOLDER_ROUTE_PATH_NONYUU_TSUUCHISHO_YO = create()
        .getPattern("kyotu_keshikomi_data_hozon_folder_route_path_nonyuu_tsuuchisho_yo");

    /** 共通：元号リスト */
    String KYOTU_GENGO_LIST = create().getPattern("kyotu_gengo_list");

    /** 以下に記載がない場合 */
    String IKA_NI_KISAI_NAI_BAAI = create().getPattern("ika_ni_kisai_nai_baai");

    /** 共通：検索件数上限値(表示) */
    String KYOTU_KENSAKU_KENSUU_JOGENCHI_HYOJI = "999";
    /** 共通：検索件数上限値(入力) */
    String KYOTU_KENSAKU_KENSUU_JOGENCHI_NYUURYOKU = "99";
    /** 共通：検索件数上限値(入力)例外 */
    String KYOTU_KENSAKU_KENSUU_JOGENCHI_NYUURYOKU_REIGAI = "999";
    /** 共通：検索件数上限値(業務管理情報設定入力) */
    String KYOTU_KENSAKU_KENSUU_JOGENCHI_GYOMU_KANRI_JOHO_SETTEI_NYUURYOKU = "365";

    /** 共通：駐車場消込データ保存フォルダルートパス（口振） */
    String KYOTU_CHUUSHAJO_KESHIKOMI_DATA_HOZON_FOLDER_ROUTE_PATH_KOZA_FURIKAE = create()
        .getPattern("kyotu_chuushajo_keshikomi_data_hozon_folder_route_path_koza_furikae");
    /** 共通：駐車場消込データ保存フォルダルートパス（納通） */
    String KYOTU_CHUUSHAJO_KESHIKOMI_DATA_HOZON_FOLDER_ROUTE_PATH_NOTSUU = create()
        .getPattern("kyotu_chuushajo_keshikomi_data_hozon_folder_route_path_notsuu");
    /** 共通：駐車場消込データ保存フォルダルートパス（コンビニ） */
    String KYOTU_CHUUSHAJO_KESHIKOMI_DATA_HOZON_FOLDER_ROUTE_PATH_KOMBINI = create()
        .getPattern("kyotu_chuushajo_keshikomi_data_hozon_folder_route_path_kombini");
    /** 共通：駐車場消込データ処理済フォルダルートパス（口振） */
    String KYOTU_CHUUSHAJO_KESHIKOMI_DATA_SHORI_SUMI_FOLDER_ROUTE_PATH_KOZA_FURIKAE = create()
        .getPattern("kyotu_chuushajo_keshikomi_data_shori_sumi_folder_route_path_koza_furikae");
    /** 共通：駐車場消込データ処理済フォルダルートパス（納通） */
    String KYOTU_CHUUSHAJO_KESHIKOMI_DATA_SHORI_SUMI_FOLDER_ROUTE_PATH_NOTSUU = create()
        .getPattern("kyotu_chuushajo_keshikomi_data_shori_sumi_folder_route_path_notsuu");
    /** 共通：駐車場消込データ処理済フォルダルートパス（コンビニ） */
    String KYOTU_CHUUSHAJO_KESHIKOMI_DATA_SHORI_SUMI_FOLDER_ROUTE_PATH_KOMBINI = create()
        .getPattern("kyotu_chuushajo_keshikomi_data_shori_sumi_folder_route_path_kombini");

    /** 有無区分表示：有 */
    String UMU_KUBUN_HYOJI_ARI = "有り";
    /** 有無区分表示：無 */
    String UMU_KUBUN_HYOJI_NASHI = "無し";

    /** ファイル種別：拡大表示用 */
    String FILE_SHUBETSU_KAKUDAI_HYOJI_YO = "0";
    /** ファイル種別：サムネイル */
    String FILE_SHUBETSU_THUMBNAIL = "1";

    /** バッチ共通：0クリア */
    Integer BATCHI_KYOTU_0_KURIA = 0;

    /** 処理方式：バッチ処理 */
    String SHORI_HOSHIKI_BATCHI_SHORI = "0";
    /** 処理方式：オンラインバッチ処理 */
    String SHORI_HOSHIKI_ONLINE_BATCHI_SHORI = "1";

    /** 再実行可能标识：可能 */
    String SAI_JIKKO_KANO_FLG_KANO = "1";
    /** 再実行可能标识：不可 */
    String SAI_JIKKO_KANO_FLG_FUKA = "0";

    /** 実行可否：実行可能 */
    String JIKKO_KAHI_JIKKO_KANO = "1";
    /** 実行可否：実行不可 */
    String JIKKO_KAHI_JIKKO_FUKA = "0";

    /** 戻り値：正常 */
    String BACK_VALUE_SEIJO = "0";
    /** 戻り値：警告 */
    String BACK_VALUE_KEIKOKU = "4";
    /** 戻り値：異常 */
    String BACK_VALUE_IJO = "8";

    /** 必須标识：必須 */
    String HISSU_FLG_HISSU = "1";
    /** 必須标识：非必須 */
    String HISSU_FLG_HI_HISSU = "0";

    /** 出力形式：CSV */
    String SHUTSURYOKU_KEISHIKI_CSV = "1";
    /** 出力形式：PDF */
    String SHUTSURYOKU_KEISHIKI_PDF = "2";
    /** 出力形式：Excel */
    String SHUTSURYOKU_KEISHIKI_EXCEL = "3";

    /** 共通：地区管理員連絡添付資料保存フォルダルートパス */
    String KYOTU_CHIKU_KANRI_IN_RENRAKU_TEMPU_SHIRYO_HOZON_FOLDER_ROUTE_PATH = create()
        .getPattern("kyotu_chiku_kanri_in_renraku_tempu_shiryo_hozon_folder_route_path");

    /** 共通：送信元メールアドレス */
    String KYOTU_SOSHIN_MOTO_MAIL_ADDRESS = "hsuser01-06@hs.pref.hyogo.local";
    /** 共通：年度末月 */
    String KYOTU_NENDO_SUE_TSUKI = "03";
    /** 共通：事故優先関連コード */
    String KYOTU_JIKO_YUSEN_KANREN_CD = "03";

    /** 対象月：01 */
    String TAISHO_TSUKI_01 = "01";
    /** 対象月：02 */
    String TAISHO_TSUKI_02 = "02";
    /** 対象月：03 */
    String TAISHO_TSUKI_03 = "03";
    /** 対象月：04 */
    String TAISHO_TSUKI_04 = "04";
    /** 対象月：05 */
    String TAISHO_TSUKI_05 = "05";
    /** 対象月：06 */
    String TAISHO_TSUKI_06 = "06";
    /** 対象月：07 */
    String TAISHO_TSUKI_07 = "07";
    /** 対象月：08 */
    String TAISHO_TSUKI_08 = "08";
    /** 対象月：09 */
    String TAISHO_TSUKI_09 = "09";
    /** 対象月：10 */
    String TAISHO_TSUKI_10 = "10";
    /** 対象月：11 */
    String TAISHO_TSUKI_11 = "11";
    /** 対象月：12 */
    String TAISHO_TSUKI_12 = "12";

    /** 共通：振替日 */
    String KYOTU_FURIKAE_DATE = "01";

    /** バッチ共通：ALL0（ゼロ） */
    String BATCHI_KYOTU_ALL0_ZERO = "000000000000000000000000000000";

    /** データ区分：ヘッダーレコード */
    String DATA_KUBUN_HEADER_RECORD = "1";
    /** データ区分：データレコード */
    String DATA_KUBUN_DATA_RECORD = "2";
    /** データ区分：エンドレコード */
    String DATA_KUBUN_ENDO_RECORD = "9";

    /** 共通：基準年月日 */
    String KYOTU_KIJUN_NENGAPPI = "1001";

    /** 系统アドレス：メールアドレス */
    String SYSTEM_ADORESU_MAIL_ADDRESS = "hsuser01-06@hs.pref.hyogo.local";

    /** 共通：口座引落情報保存期間（月数） */
    String KYOTU_KOZA_HIKIOTOSHI_JOHO_HOZON_KIKAN_TSUKI_SUU = "24";
    /** 共通：口座引落結果情報保存期間（月数） */
    String KYOTU_KOZA_HIKIOTOSHI_KEKKA_JOHO_HOZON_KIKAN_TSUKI_SUU = "24";
    /** 共通：納付書収納結果保存期間（月数） */
    String KYOTU_NOFU_SHO_SHUUNO_KEKKA_HOZON_KIKAN_TSUKI_SUU = "24";
    /** 共通：口座振替・隔地払情報保存期間（月数） */
    String KYOTU_KOZA_FURIKAE_KAKUCHIBARAI_JOHO_HOZON_KIKAN_TSUKI_SUU = "24";
    /** 共通：隔地払情報保存期間（月数） */
    String KYOTU_KAKUCHIBARAI_JOHO_HOZON_KIKAN_TSUKI_SUU = "24";
    /** 共通：駐車場口座引落依頼情報（公社）保存期間（月数） */
    String KYOTU_CHUUSHAJO_KOZA_HIKIOTOSHI_IRAI_JOHO_KOSHA_HOZON_KIKAN_TSUKI_SUU = "24";
    /** 共通：駐車場口座引落結果情報（公社）保存期間（月数） */
    String KYOTU_CHUUSHAJO_KOZA_HIKIOTOSHI_KEKKA_JOHO_KOSHA_HOZON_KIKAN_TSUKI_SUU = "24";
    /** 共通：駐車場納付書収納情報（公社分）保存期間（月数） */
    String KYOTU_CHUUSHAJO_NOFU_SHO_SHUUNO_JOHO_KOSHA_BUN_HOZON_KIKAN_TSUKI_SUU = "24";
    /** 共通：駐車場納入通知書印刷情報（公社）保存期間（月数） */
    String KYOTU_CHUUSHAJO_NONYUU_TSUUCHISHO_INSATSU_JOHO_KOSHA_HOZON_KIKAN_TSUKI_SUU = "24";
    /** 共通：駐車場納付書情報（公社以外分）保存期間（月数） */
    String KYOTU_CHUUSHAJO_NOFU_SHO_JOHO_KOSHA_IGAI_BUN_HOZON_KIKAN_TSUKI_SUU = "24";
    /** 共通：駐車場口座引落依頼情報（公社以外）保存期間（月数） */
    String KYOTU_CHUUSHAJO_KOZA_HIKIOTOSHI_IRAI_JOHO_KOSHA_IGAI_HOZON_KIKAN_TSUKI_SUU = "24";
    /** 共通：駐車場口座引落結果情報（公社以外）保存期間（月数） */
    String KYOTU_CHUUSHAJO_KOZA_HIKIOTOSHI_KEKKA_JOHO_KOSHA_IGAI_HOZON_KIKAN_TSUKI_SUU = "24";
    /** 共通：駐車場納付書収納情報（公社以外分）保存期間（月数） */
    String KYOTU_CHUUSHAJO_NOFU_SHO_SHUUNO_JOHO_KOSHA_IGAI_BUN_HOZON_KIKAN_TSUKI_SUU = "24";
    /** 共通：コンビニくん用顧客情報保存期間（月数） */
    String KYOTU_KOMBINI_KON_YO_KOKYAKU_JOHO_HOZON_KIKAN_TSUKI_SUU = "24";
    /** 共通：代理納付情報（月次）保存期間（月数） */
    String KYOTU_DAIRI_NOFU_JOHO_TSUKI_TSUJI_HOZON_KIKAN_TSUKI_SUU = "24";
    /** 共通：債権回収委託者情報保存期間（月数） */
    String KYOTU_SAIKEN_KAISHU_ITAKU_SHA_JOHO_HOZON_KIKAN_TSUKI_SUU = "24";
    /** 共通：暴力団照会リスト保存期間（月数） */
    String KYOTU_BORYOKUDAN_SHOKAI_LIST_HOZON_KIKAN_TSUKI_SUU = "24";
    /** 共通：公社系统用会計データ保存期間（月数） */
    String KYOTU_KOSHA_SYSTEM_YO_KAIKEI_DATA_HOZON_KIKAN_TSUKI_SUU = "24";
    /** 共通：公社納通印刷データ保存期間（月数） */
    String KYOTU_KOSHA_NOTSUU_INSATSU_DATA_HOZON_KIKAN_TSUKI_SUU = "24";
    /** 共通：代理納付情報（年次）保存期間（月数） */
    String KYOTU_DAIRI_NOFU_JOHO_NEN_TSUJI_HOZON_KIKAN_TSUKI_SUU = "24";
    /** 共通：収納済通知書スキャンデータ保存期間（月数） */
    String KYOTU_SHUUNO_SUMI_TSUUCHISHO_SUKYAN_DATA_HOZON_KIKAN_TSUKI_SUU = "132";
    /** 共通：収納済通知書スキャン画像保存期間（月数） */
    String KYOTU_SHUUNO_SUMI_TSUUCHISHO_SUKYAN_GAZO_HOZON_KIKAN_TSUKI_SUU = "24";

    /** 共通：口座引落情報保存フォルダルートパス */
    String KYOTU_KOZA_HIKIOTOSHI_JOHO_HOZON_FOLDER_ROUTE_PATH = create()
        .getPattern("kyotu_koza_hikiotoshi_joho_hozon_folder_route_path");
    /** 共通：口座引落結果情報保存フォルダルートパス */
    String KYOTU_KOZA_HIKIOTOSHI_KEKKA_JOHO_HOZON_FOLDER_ROUTE_PATH = create()
        .getPattern("kyotu_koza_hikiotoshi_kekka_joho_hozon_folder_route_path");
    /** 共通：口座引落結果情報処理済フォルダルートパス */
    String KYOTU_KOZA_HIKIOTOSHI_KEKKA_JOHO_SHORI_SUMI_FOLDER_ROUTE_PATH = create()
        .getPattern("kyotu_koza_hikiotoshi_kekka_joho_shori_sumi_folder_route_path");
    /** 共通：納付書収納結果処理済フォルダルートパス */
    String KYOTU_NOFU_SHO_SHUUNO_KEKKA_SHORI_SUMI_FOLDER_ROUTE_PATH = create()
        .getPattern("kyotu_nofu_sho_shuuno_kekka_shori_sumi_folder_route_path");
    /** 共通：口座振替・隔地払情報データフォルダルートパス */
    String KYOTU_KOZA_FURIKAE_KAKUCHIBARAI_JOHO_DATA_FOLDER_ROUTE_PATH = create()
        .getPattern("kyotu_koza_furikae_kakuchibarai_joho_data_folder_route_path");
    /** 共通：隔地払情報データフォルダルートパス */
    String KYOTU_KAKUCHIBARAI_JOHO_DATA_FOLDER_ROUTE_PATH = create()
        .getPattern("kyotu_kakuchibarai_joho_data_folder_route_path");
    /** 共通：駐車場口座引落依頼情報（公社）保存フォルダルートパス */
    String KYOTU_CHUUSHAJO_KOZA_HIKIOTOSHI_IRAI_JOHO_KOSHA_HOZON_FOLDER_ROUTE_PATH = create()
        .getPattern("kyotu_chuushajo_koza_hikiotoshi_irai_joho_kosha_hozon_folder_route_path");
    /** 共通：駐車場口座引落結果情報（公社）保存フォルダルートパス */
    String KYOTU_CHUUSHAJO_KOZA_HIKIOTOSHI_KEKKA_JOHO_KOSHA_HOZON_FOLDER_ROUTE_PATH = create()
        .getPattern("kyotu_chuushajo_koza_hikiotoshi_kekka_joho_kosha_hozon_folder_route_path");
    /** 共通：駐車場口座引落結果情報（公社）処理済フォルダルートパス */
    String KYOTU_CHUUSHAJO_KOZA_HIKIOTOSHI_KEKKA_JOHO_KOSHA_SHORI_SUMI_FOLDER_ROUTE_PATH = create()
        .getPattern("kyotu_chuushajo_koza_hikiotoshi_kekka_joho_kosha_shori_sumi_folder_route_path");
    /** 共通：駐車場納付書収納情報（公社分）保存フォルダルートパス */
    String KYOTU_CHUUSHAJO_NOFU_SHO_SHUUNO_JOHO_KOSHA_BUN_HOZON_FOLDER_ROUTE_PATH = create()
        .getPattern("kyotu_chuushajo_nofu_sho_shuuno_joho_kosha_bun_hozon_folder_route_path");
    /** 共通：駐車場納付書収納情報（公社分）処理済フォルダルートパス */
    String KYOTU_CHUUSHAJO_NOFU_SHO_SHUUNO_JOHO_KOSHA_BUN_SHORI_SUMI_FOLDER_ROUTE_PATH = create()
        .getPattern("kyotu_chuushajo_nofu_sho_shuuno_joho_kosha_bun_shori_sumi_folder_route_path");
    /** 共通：駐車場納入通知書印刷情報（公社）保存フォルダルートパス */
    String KYOTU_CHUUSHAJO_NONYUU_TSUUCHISHO_INSATSU_JOHO_KOSHA_HOZON_FOLDER_ROUTE_PATH = create()
        .getPattern("kyotu_chuushajo_nonyuu_tsuuchisho_insatsu_joho_kosha_hozon_folder_route_path");
    /** 共通：駐車場納付書情報（公社以外分）保存フォルダルートパス */
    String KYOTU_CHUUSHAJO_NOFU_SHO_JOHO_KOSHA_IGAI_BUN_HOZON_FOLDER_ROUTE_PATH = create()
        .getPattern("kyotu_chuushajo_nofu_sho_joho_kosha_igai_bun_hozon_folder_route_path");
    /** 共通：駐車場口座引落依頼情報（公社以外）保存フォルダルートパス */
    String KYOTU_CHUUSHAJO_KOZA_HIKIOTOSHI_IRAI_JOHO_KOSHA_IGAI_HOZON_FOLDER_ROUTE_PATH = create()
        .getPattern("kyotu_chuushajo_koza_hikiotoshi_irai_joho_kosha_igai_hozon_folder_route_path");
    /** 共通：駐車場口座引落結果情報（公社以外）保存フォルダルートパス */
    String KYOTU_CHUUSHAJO_KOZA_HIKIOTOSHI_KEKKA_JOHO_KOSHA_IGAI_HOZON_FOLDER_ROUTE_PATH = create()
        .getPattern("kyotu_chuushajo_koza_hikiotoshi_kekka_joho_kosha_igai_hozon_folder_route_path");
    /** 共通：駐車場口座引落結果情報（公社以外）処理済フォルダルートパス */
    String KYOTU_CHUUSHAJO_KOZA_HIKIOTOSHI_KEKKA_JOHO_KOSHA_IGAI_SHORI_SUMI_FOLDER_ROUTE_PATH = create()
        .getPattern("kyotu_chuushajo_koza_hikiotoshi_kekka_joho_kosha_igai_shori_sumi_folder_route_path");
    /** 共通：駐車場納付書収納情報（公社以外分）保存フォルダルートパス */
    String KYOTU_CHUUSHAJO_NOFU_SHO_SHUUNO_JOHO_KOSHA_IGAI_BUN_HOZON_FOLDER_ROUTE_PATH = create()
        .getPattern("kyotu_chuushajo_nofu_sho_shuuno_joho_kosha_igai_bun_hozon_folder_route_path");
    /** 共通：駐車場納付書収納情報（公社以外分）処理済フォルダルートパス */
    String KYOTU_CHUUSHAJO_NOFU_SHO_SHUUNO_JOHO_KOSHA_IGAI_BUN_SHORI_SUMI_FOLDER_ROUTE_PATH = create()
        .getPattern("kyotu_chuushajo_nofu_sho_shuuno_joho_kosha_igai_bun_shori_sumi_folder_route_path");
    /** 共通：コンビニくん用顧客情報データフォルダルートパス */
    String KYOTU_KOMBINI_KON_YO_KOKYAKU_JOHO_DATA_FOLDER_ROUTE_PATH = create()
        .getPattern("kyotu_kombini_kon_yo_kokyaku_joho_data_folder_route_path");
    /** 共通：代理納付情報（月次）保存フォルダルートパス */
    String KYOTU_DAIRI_NOFU_JOHO_TSUKI_TSUJI_HOZON_FOLDER_ROUTE_PATH = create()
        .getPattern("kyotu_dairi_nofu_joho_tsuki_tsuji_hozon_folder_route_path");
    /** 共通：代理納付情報（月次）処理済フォルダルートパス */
    String KYOTU_DAIRI_NOFU_JOHO_TSUKI_TSUJI_SHORI_SUMI_FOLDER_ROUTE_PATH = create()
        .getPattern("kyotu_dairi_nofu_joho_tsuki_tsuji_shori_sumi_folder_route_path");
    /** 共通：債権回収委託データフォルダルートパス */
    String KYOTU_SAIKEN_KAISHU_ITAKU_DATA_FOLDER_ROUTE_PATH = create()
        .getPattern("kyotu_saiken_kaishu_itaku_data_folder_route_path");
    /** 共通：暴力団照会データフォルダルートパス */
    String KYOTU_BORYOKUDAN_SHOKAI_DATA_FOLDER_ROUTE_PATH = create()
        .getPattern("kyotu_boryokudan_shokai_data_folder_route_path");
    /** 共通：公社用会計ファイル保存フォルダルートパス */
    String KYOTU_KOSHA_YO_KAIKEI_FILE_HOZON_FOLDER_ROUTE_PATH = create()
        .getPattern("kyotu_kosha_yo_kaikei_file_hozon_folder_route_path");
    /** 共通：公社納通印刷データ保存フォルダルートパス */
    String KYOTU_KOSHA_NOTSUU_INSATSU_DATA_HOZON_FOLDER_ROUTE_PATH = create()
        .getPattern("kyotu_kosha_notsuu_insatsu_data_hozon_folder_route_path");
    /** 共通：代理納付情報（年次）保存フォルダルートパス */
    String KYOTU_DAIRI_NOFU_JOHO_NEN_TSUJI_HOZON_FOLDER_ROUTE_PATH = create()
        .getPattern("kyotu_dairi_nofu_joho_nen_tsuji_hozon_folder_route_path");
    /** 共通：代理納付情報（年次）処理済フォルダルートパス */
    String KYOTU_DAIRI_NOFU_JOHO_NEN_TSUJI_SHORI_SUMI_FOLDER_ROUTE_PATH = create()
        .getPattern("kyotu_dairi_nofu_joho_nen_tsuji_shori_sumi_folder_route_path");
    /** 共通：収納済通知書スキャンデータ保存フォルダルートパス */
    String KYOTU_SHUUNO_SUMI_TSUUCHISHO_SUKYAN_DATA_HOZON_FOLDER_ROUTE_PATH = create()
        .getPattern("kyotu_shuuno_sumi_tsuuchisho_sukyan_data_hozon_folder_route_path");
    /** 共通：収納済通知書スキャンデータ処理済フォルダルートパス */
    String KYOTU_SHUUNO_SUMI_TSUUCHISHO_SUKYAN_DATA_SHORI_SUMI_FOLDER_ROUTE_PATH = create()
        .getPattern("kyotu_shuuno_sumi_tsuuchisho_sukyan_data_shori_sumi_folder_route_path");
    /** 共通：収納済通知書スキャン画像保存フォルダルートパス */
    String KYOTU_SHUUNO_SUMI_TSUUCHISHO_SUKYAN_GAZO_HOZON_FOLDER_ROUTE_PATH = create()
        .getPattern("kyotu_shuuno_sumi_tsuuchisho_sukyan_gazo_hozon_folder_route_path");

    /** 共通：宛名 三井住友銀行神戸公務部 */
    String KYOTU_ATENA = "三井住友銀行神戸公務部";
    /** 共通：管理者 兵庫県会計管理者 */
    String KYOTU_KANRISHA = "兵庫県会計管理者";
    /** 共通：隔地（三井住友） */
    String KYOTU_KAKUCHI_MITSUISUMITOMO = "隔地（三井住友）";
    /** 共通：隔地（みなと） */
    String KYOTU_KAKUCHI_MINATO = "隔地（みなと）";
    /** 共通：隔地（郵便局） */
    String KYOTU_KAKUCHI_YUUBINKYOKU = "隔地（郵便局）";
    /** 共通：補修費充当 */
    String KYOTU_HOSHUUHI_JUUTO = "補修費充当";
    /** 共通：家賃充当 */
    String KYOTU_YACHIN_JUUTO = "家賃充当";
    /** 共通：共益費充当 */
    String KYOTU_KYOEKI_HI_JUUTO = "共益費充当";
    /** 共通：損賠金充当 */
    String KYOTU_SONBAI_KIN_JUUTO = "損賠金充当";
    /** 共通：県住ひ県 */
    String KYOTU_KENJYU_HIKEN = "県住、ひ県";
    /** 共通：借上県住 */
    String KYOTU_KARIAGE_KENJYU = "借上県住";
    /** 共通：各敷金 */
    String KYOTU_KAKU_SHIKIKIN = "各敷金";
    /** 共通：824 */
    String KYOTU_824 = "824";
    /** 共通：823 */
    String KYOTU_823 = "823";
    /** 共通：825 */
    String KYOTU_825 = "825";
    /** 共通：別紙のとおり */
    String KYOTU_BESSHI_NO_TOARI = "別紙のとおり";
    /** 共通：口座振替 */
    String KYOTU_KOZA_FURIKAE = "口座振替";

    /** 共通：申請番号 */
    String KYOTU_SHINSEI_BANGO = "0000000000";
    /** 共通：審議履歴番号 */
    String KYOTU_SHINGI_RIREKI_BANGO = "000";

    /** 共通：画像一時保存フォルダ */
    String KYOTU_GAZO_ICHIJI_HOZON_FOLDER = create().getPattern("kyotu_gazo_ichiji_hozon_folder");

    /** 共通：納入通知書兼領収書発行件数 */
    Integer KYOTU_NONYUU_TSUUCHISHO_KEN_RYOSHUU_SHO_HAKKO_KENSUU = 100;

    /** 共通：納付書収納結果通基盤フォルダ */
    String KYOTU_NOFU_SHO_SHUUNO_KEKKA_TSU_KIBAN_FOLDER = create()
        .getPattern("kyotu_nofu_sho_shuuno_kekka_tsu_kiban_folder");

    /** 共通：対象支払指定日 */
    String KYOTU_TAISHO_SHIHARAI_SHITEIBI = "10";

    /** 共通：年末日 */
    String KYOTU_NEN_SUE_DATE = "1231";

    /** 共通：出力ファイル保存期間（月数） */
    String KYOTU_SHUTSURYOKU_FILE_HOZON_KIKAN_TSUKI_SUU = "12";
    /** 共通：画像保存期間（月数） */
    String KYOTU_GAZO_HOZON_KIKAN_TSUKI_SUU = "24";
    /** 共通：以外のテーブル削除データ保存期間（月数） */
    String KYOTU_IGAI_NO_TABLE_SAKUJO_DATA_HOZON_KIKAN_TSUKI_SUU = "72";
    /** 共通：バッチ制御テーブルデータ保存期間（月数） */
    String KYOTU_JKT_BATCH_SEIGYO_DATA_HOZON_KIKAN_TSUKI_SUU = "24";
    /** 共通：仮テーブルデータ保存期間（月数） */
    String KYOTU_KARI_TABLE_DATA_HOZON_KIKAN_TSUKI_SUU = "24";
    /** 共通：削除データ保存月数（24ヶ月） */
    String KYOTU_DEL_DATA_HOZON_KIKAN_TSUKI_SUU_24 = "24";
    /** 共通：削除データ保存月数（36ヶ月） */
    String KYOTU_DEL_DATA_HOZON_KIKAN_TSUKI_SUU_36 = "36";
    /** 共通：削除データ保存年度数（3ヶ年度） */
    String KYOTU_DEL_DATA_HOZON_KIKAN_NENDO_SUU_3 = "3";
    /** 共通：削除データ保存年度数（6ヶ年度） */
    String KYOTU_DEL_DATA_HOZON_KIKAN_NENDO_SUU_6 = "6";

    /** 共通：ジフリくん用顧客情報保存期間（月数） */
    String KYOTU_JIFURI_KON_YO_KOKYAKU_JOHO_HOZON_KIKAN_TSUKI_SUU = "24";
    /** 共通：ジフリくん用顧客情報データフォルダルートパス */
    String KYOTU_JIFURI_KON_YO_KOKYAKU_JOHO_DATA_FOLDER_ROUTE_PATH = create()
        .getPattern("kyotu_jifuri_kon_yo_kokyaku_joho_data_folder_route_path");
    /** 共通：調定情報保存期間（月数） */
    String KYOTU_CHOTEI_JOHO_HOZON_KIKAN_TSUKI_SUU = "24";
    /** 共通：調定情報フォルダルートパス */
    String KYOTU_CHOTEI_JOHO_FOLDER_ROUTE_PATH = create().getPattern("kyotu_chotei_joho_folder_route_path");
    /** 共通：調定増減情報保存期間（月数） */
    String KYOTU_CHOTEI_ZOGEN_JOHO_HOZON_KIKAN_TSUKI_SUU = "24";
    /** 共通：調定増減情報フォルダルートパス */
    String KYOTU_CHOTEI_ZOGEN_JOHO_FOLDER_ROUTE_PATH = create().getPattern("kyotu_chotei_zogen_joho_folder_route_path");
    /** 共通：戻出/充当情報保存期間（月数） */
    String KYOTU_REISHUTSU_JUUTO_JOHO_HOZON_KIKAN_TSUKI_SUU = "24";
    /** 共通：戻出/充当情報フォルダルートパス */
    String KYOTU_REISHUTSU_JUUTO_JOHO_FOLDER_ROUTE_PATH = create()
        .getPattern("kyotu_reishutsu_juuto_joho_folder_route_path");
    /** 共通：入居申込者情報データ保存期間（月数） */
    String KYOTU_NYUUKYO_MOSHIKOMISHA_JOHO_DATA_HOZON_KIKAN_TSUKI_SUU = "24";
    /** 共通：入居申込者情報データフォルダルートパス */
    String KYOTU_NYUUKYO_MOSHIKOMISHA_JOHO_DATA_FOLDER_ROUTE_PATH = create()
        .getPattern("kyotu_nyuukyo_moshikomisha_joho_data_folder_route_path");
    /** 共通：収入申告ファイル保存期間（月数） */
    String KYOTU_SHUUNYUU_SHINKOKU_FILE_HOZON_KIKAN_TSUKI_SUU = "24";
    /** 共通：収入申告ファイルフォルダルートパス */
    String KYOTU_SHUUNYUU_SHINKOKU_FILE_FOLDER_ROUTE_PATH = create()
        .getPattern("kyotu_shuunyuu_shinkoku_file_folder_route_path");
    /** 共通：入居者メモ情報保存フォルダルートパス */
    String KYOTU_NYUUKYOSHA_MEMO_JOHO_HOZON_FOLDER_ROUTE_PATH = create()
        .getPattern("kyotu_nyuukyosha_memo_joho_hozon_folder_route_path");

    /** 共通：激変緩和保存情報作成标识 */
    String KYOTU_GEKIHENKANWA_HOZON_JOHO_SAKUSEI_FLG = "0";

    /** 共通：取込ファイル保存フォルダ */
    String KYOTU_TORIKOMI_FILE_HOZON_FOLDER = create().getPattern("kyotu_torikomi_file_hozon_folder");

    /** 共通：操作ログ出力対象外リスト */
    String KYOTU_SOSA_LOG_SHUTSURYOKU_TAISHO_GAI_LIST = create()
        .getPattern("kyotu_sosa_log_shutsuryoku_taisho_gai_list");

    /** 共通：AliasFakeName */
    String KYOTU_ALIAS_FAKE_NAME = create().getPattern("kyotu_alias_fake_name");

    /** 共通：オートコンプリートページサイズ */
    String KYOTU_AUTOCOMPLETE_PAGE_SIZE = create().getPattern("kyotu_autocomplete_page_size");

    /** 共通：基盤動作チェック用のステータスフォルダ */
    String HEALTHCHECK_STATUS_FOLDER = create().getPattern("healthcheck_status_folder");
    /** 共通：基盤動作チェック用のストレージフォルダ */
    String HEALTHCHECK_STORAGE_FOLDER = create().getPattern("healthcheck_storage_folder");

    /** 共通：OpenBOST_CSV保存フォルダルートパス */
    String KYOTU_OPENBOST_CSV_SAVE_FOLDER_ROUTE_PATH = create().getPattern("kyotu_openbost_csv_save_folder_route_path");

    /** 共通：エンコード（UTF8） */
    String KYOTU_ENCODE_UTF8 = "UTF-8";
    /** 共通：エンコード（Shift-JIS） */
    String KYOTU_ENCODE_SHIFT_JIS = "MS932";

    /** 共通：無視URLメソッドリスト */
    String KYOTU_IGNORE_URLMETHOD = create().getPattern("kyotu_ignore_urlmethod");

}