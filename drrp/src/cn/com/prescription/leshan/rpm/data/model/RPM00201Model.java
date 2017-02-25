/*
 * Copyright(c) 2016 
 */
package cn.com.prescription.leshan.rpm.data.model;

import java.sql.Timestamp;

import cn.com.prescription.leshan.common.data.model.RpmUserModel;

/**
 * 用户情報設定画面のモードル
 * 
 * @author fsb
 */
/*
 * 新規作成
 * DATE: 2012.11.07 NAME: fsb
 */
public class RPM00201Model extends RpmUserModel{

    /**
     * 串行版本UID
     */
    private static final long serialVersionUID = 1L;

    /**
     * 最終更新日基本
     */
    private Timestamp lastUpdateDateKihon = null;

    /**
     * 最終更新日プロファイル
     */
    private Timestamp lastUpdateDateProfile = null;

    /**
     * 最終更新日ロール権限
     */
    private Timestamp lastUpdateDateRollKengen = null;

    /**
     * 氏名・かな（姓）
     */
    private String shimeiKanaSei = null;

    /**
     * 氏名・かな（名）
     */
    private String shimeiKanaMei = null;

    /**
     * 氏名・かな（中間名）
     */
    private String shimeiKanaChuukammei = null;

    /**
     * 氏名・漢字（姓）
     */
    private String shimeiKanjiSei = null;

    /**
     * 氏名・漢字（名）
     */
    private String shimeiKanjiMei = null;

    /**
     * 氏名・漢字（中間名）
     */
    private String shimeiKanjiChuukammei = null;

    /**
     * 氏名・英字（姓）
     */
    private String shimeiEijiSei = null;

    /**
     * 氏名・英字（名）
     */
    private String shimeiEijiMei = null;

    /**
     * 氏名・英字（中間名）
     */
    private String shimeiEijiChuukammei = null;

    /**
     * 通称名・かな
     */
    private String tsuushoMeiKana = null;

    /**
     * 通称名・漢字
     */
    private String tsuushoMeiKanji = null;

    /**
     * 生年月日
     */
    private String seinengappi = null;

    /**
     * 性別コード
     */
    private String seibetsuCd = null;

    /**
     * 郵便番号
     */
    private String yuubinBango = null;

    /**
     * 正式住所名
     */
    private String seishikiJuushoMei = null;

    /**
     * 通称住所名
     */
    private String tsuushoJuushoMei = null;

    /**
     * 電話番号
     */
    private String denwaBango = null;

    /**
     * 携帯電話番号
     */
    private String keitaiDenwaBango = null;

    /**
     * メール・アドレス
     */
    private String mailAddress = null;
    
    /**
     * 使用端末（コンピュータ名）
     */
    private String shiyoTammatsuComputerName = null;

    /**
     * 役割番号
     */
    private String yakuwariBango = null;
    
    /**
     * ログイン画面モデル 的构造。
     */
    public RPM00201Model() {
        super();
    }

    /**
     * 最終更新日基本を取得する。
     * @return 最終更新日基本
     */
    public Timestamp getLastUpdateDateKihon() {
        return lastUpdateDateKihon;
    }

    /**
     * 最終更新日基本を設定する。
     * @param _lastUpdateDateKihon 最終更新日基本
     */
    public void setLastUpdateDateKihon(Timestamp _lastUpdateDateKihon) {
        this.lastUpdateDateKihon = _lastUpdateDateKihon;
    }

    /**
     * 最終更新日プロファイルを取得する。
     * @return 最終更新日プロファイル
     */
    public Timestamp getLastUpdateDateProfile() {
        return lastUpdateDateProfile;
    }

    /**
     * 最終更新日プロファイルを設定する。
     * @param _lastUpdateDateProfile 最終更新日プロファイル
     */
    public void setLastUpdateDateProfile(Timestamp _lastUpdateDateProfile) {
        this.lastUpdateDateProfile = _lastUpdateDateProfile;
    }

    /**
     * 最終更新日ロール権限を取得する。
     * @return 最終更新日ロール権限
     */
    public Timestamp getLastUpdateDateRollKengen() {
        return lastUpdateDateRollKengen;
    }

    /**
     * 最終更新日ロール権限を設定する。
     * @param _lastUpdateDateRollKengen 最終更新日ロール権限
     */
    public void setLastUpdateDateRollKengen(Timestamp _lastUpdateDateRollKengen) {
        this.lastUpdateDateRollKengen = _lastUpdateDateRollKengen;
    }

    /**
     * 氏名・かな（姓）を取得する。
     * @return 氏名・かな（姓）
     */
    public String getShimeiKanaSei() {
        return shimeiKanaSei;
    }

    /**
     * 氏名・かな（姓）を設定する。
     * @param _shimeiKanaSei 氏名・かな（姓）
     */
    public void setShimeiKanaSei(String _shimeiKanaSei) {
        this.shimeiKanaSei = _shimeiKanaSei;
    }

    /**
     * 氏名・かな（名）を取得する。
     * @return 氏名・かな（名）
     */
    public String getShimeiKanaMei() {
        return shimeiKanaMei;
    }

    /**
     * 氏名・かな（名）を設定する。
     * @param _shimeiKanaMei 氏名・かな（名）
     */
    public void setShimeiKanaMei(String _shimeiKanaMei) {
        this.shimeiKanaMei = _shimeiKanaMei;
    }

    /**
     * 氏名・かな（中間名）を取得する。
     * @return 氏名・かな（中間名）
     */
    public String getShimeiKanaChuukammei() {
        return shimeiKanaChuukammei;
    }

    /**
     * 氏名・かな（中間名）を設定する。
     * @param _shimeiKanaChuukammei 氏名・かな（中間名）
     */
    public void setShimeiKanaChuukammei(String _shimeiKanaChuukammei) {
        this.shimeiKanaChuukammei = _shimeiKanaChuukammei;
    }

    /**
     * 氏名・漢字（姓）を取得する。
     * @return 氏名・漢字（姓）
     */
    public String getShimeiKanjiSei() {
        return shimeiKanjiSei;
    }

    /**
     * 氏名・漢字（姓）を設定する。
     * @param _shimeiKanjiSei 氏名・漢字（姓）
     */
    public void setShimeiKanjiSei(String _shimeiKanjiSei) {
        this.shimeiKanjiSei = _shimeiKanjiSei;
    }

    /**
     * 氏名・漢字（名）を取得する。
     * @return 氏名・漢字（名）
     */
    public String getShimeiKanjiMei() {
        return shimeiKanjiMei;
    }

    /**
     * 氏名・漢字（名）を設定する。
     * @param _shimeiKanjiMei 氏名・漢字（名）
     */
    public void setShimeiKanjiMei(String _shimeiKanjiMei) {
        this.shimeiKanjiMei = _shimeiKanjiMei;
    }

    /**
     * 氏名・漢字（中間名）を取得する。
     * @return 氏名・漢字（中間名）
     */
    public String getShimeiKanjiChuukammei() {
        return shimeiKanjiChuukammei;
    }

    /**
     * 氏名・漢字（中間名）を設定する。
     * @param _shimeiKanjiChuukammei 氏名・漢字（中間名）
     */
    public void setShimeiKanjiChuukammei(String _shimeiKanjiChuukammei) {
        this.shimeiKanjiChuukammei = _shimeiKanjiChuukammei;
    }

    /**
     * 氏名・英字（姓）を取得する。
     * @return 氏名・英字（姓）
     */
    public String getShimeiEijiSei() {
        return shimeiEijiSei;
    }

    /**
     * 氏名・英字（姓）を設定する。
     * @param _shimeiEijiSei 氏名・英字（姓）
     */
    public void setShimeiEijiSei(String _shimeiEijiSei) {
        this.shimeiEijiSei = _shimeiEijiSei;
    }

    /**
     * 氏名・英字（名）を取得する。
     * @return 氏名・英字（名）
     */
    public String getShimeiEijiMei() {
        return shimeiEijiMei;
    }

    /**
     * 氏名・英字（名）を設定する。
     * @param _shimeiEijiMei 氏名・英字（名）
     */
    public void setShimeiEijiMei(String _shimeiEijiMei) {
        this.shimeiEijiMei = _shimeiEijiMei;
    }

    /**
     * 氏名・英字（中間名）を取得する。
     * @return 氏名・英字（中間名）
     */
    public String getShimeiEijiChuukammei() {
        return shimeiEijiChuukammei;
    }

    /**
     * 氏名・英字（中間名）を設定する。
     * @param _shimeiEijiChuukammei 氏名・英字（中間名）
     */
    public void setShimeiEijiChuukammei(String _shimeiEijiChuukammei) {
        this.shimeiEijiChuukammei = _shimeiEijiChuukammei;
    }

    /**
     * 通称名・かなを取得する。
     * @return 通称名・かな
     */
    public String getTsuushoMeiKana() {
        return tsuushoMeiKana;
    }

    /**
     * 通称名・かなを設定する。
     * @param _tsuushoMeiKana 通称名・かな
     */
    public void setTsuushoMeiKana(String _tsuushoMeiKana) {
        this.tsuushoMeiKana = _tsuushoMeiKana;
    }

    /**
     * 通称名・漢字を取得する。
     * @return 通称名・漢字
     */
    public String getTsuushoMeiKanji() {
        return tsuushoMeiKanji;
    }

    /**
     * 通称名・漢字を設定する。
     * @param _tsuushoMeiKanji 通称名・漢字
     */
    public void setTsuushoMeiKanji(String _tsuushoMeiKanji) {
        this.tsuushoMeiKanji = _tsuushoMeiKanji;
    }

    /**
     * 生年月日を取得する。
     * @return 生年月日
     */
    public String getSeinengappi() {
        return seinengappi;
    }

    /**
     * 生年月日を設定する。
     * @param _seinengappi 生年月日
     */
    public void setSeinengappi(String _seinengappi) {
        this.seinengappi = _seinengappi;
    }

    /**
     * 性別コードを取得する。
     * @return 性別コード
     */
    public String getSeibetsuCd() {
        return seibetsuCd;
    }

    /**
     * 性別コードを設定する。
     * @param _seibetsuCd 性別コード
     */
    public void setSeibetsuCd(String _seibetsuCd) {
        this.seibetsuCd = _seibetsuCd;
    }

    /**
     * 郵便番号を取得する。
     * @return 郵便番号
     */
    public String getYuubinBango() {
        return yuubinBango;
    }

    /**
     * 郵便番号を設定する。
     * @param _yuubinBango 郵便番号
     */
    public void setYuubinBango(String _yuubinBango) {
        this.yuubinBango = _yuubinBango;
    }

    /**
     * 正式住所名を取得する。
     * @return 正式住所名
     */
    public String getSeishikiJuushoMei() {
        return seishikiJuushoMei;
    }

    /**
     * 正式住所名を設定する。
     * @param _seishikiJuushoMei 正式住所名
     */
    public void setSeishikiJuushoMei(String _seishikiJuushoMei) {
        this.seishikiJuushoMei = _seishikiJuushoMei;
    }

    /**
     * 通称住所名を取得する。
     * @return 通称住所名
     */
    public String getTsuushoJuushoMei() {
        return tsuushoJuushoMei;
    }

    /**
     * 通称住所名を設定する。
     * @param _tsuushoJuushoMei 通称住所名
     */
    public void setTsuushoJuushoMei(String _tsuushoJuushoMei) {
        this.tsuushoJuushoMei = _tsuushoJuushoMei;
    }

    /**
     * 電話番号を取得する。
     * @return 電話番号
     */
    public String getDenwaBango() {
        return denwaBango;
    }

    /**
     * 電話番号を設定する。
     * @param _denwaBango 電話番号
     */
    public void setDenwaBango(String _denwaBango) {
        this.denwaBango = _denwaBango;
    }

    /**
     * 携帯電話番号を取得する。
     * @return 携帯電話番号
     */
    public String getKeitaiDenwaBango() {
        return keitaiDenwaBango;
    }

    /**
     * 携帯電話番号を設定する。
     * @param _keitaiDenwaBango 携帯電話番号
     */
    public void setKeitaiDenwaBango(String _keitaiDenwaBango) {
        this.keitaiDenwaBango = _keitaiDenwaBango;
    }

    /**
     * メール・アドレスを取得する。
     * @return メール・アドレス
     */
    public String getMailAddress() {
        return mailAddress;
    }

    /**
     * メール・アドレスを設定する。
     * @param _mailAddress メール・アドレス
     */
    public void setMailAddress(String _mailAddress) {
        this.mailAddress = _mailAddress;
    }

    /**
     * 使用端末（コンピュータ名）を取得する。
     * @return 使用端末（コンピュータ名）
     */
    public String getShiyoTammatsuComputerName() {
        return shiyoTammatsuComputerName;
    }

    /**
     * 使用端末（コンピュータ名）を設定する。
     * @param _shiyoTammatsuComputerName 使用端末（コンピュータ名）
     */
    public void setShiyoTammatsuComputerName(String _shiyoTammatsuComputerName) {
        this.shiyoTammatsuComputerName = _shiyoTammatsuComputerName;
    }

    /**
     * 役割番号を取得する。
     * @return 役割番号
     */
    public String getYakuwariBango() {
        return yakuwariBango;
    }

    /**
     * 役割番号を設定する。
     * @param _yakuwariBango 役割番号
     */
    public void setYakuwariBango(String _yakuwariBango) {
        this.yakuwariBango = _yakuwariBango;
    }

}
