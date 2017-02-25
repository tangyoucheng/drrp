/*
 * Copyright(c) 2011 
 */
package cn.com.prescription.framework.common.session;

import java.io.Serializable;

/**
 * BackupDataセッション情報.
 * 
 * @author nttdc
 */
public class BackupDataSessionInfo implements Serializable {

    /** 串行版本号 */
    private static final long serialVersionUID = 779043845576160528L;
    
    /**
     * コード分類マスタ(業務ID)
     */
    private String cdBunruiMasutaGyoumuId = null;
    
    /**
     * コード分類マスタ(コード分類コード)
     */
    private String cdBunruiMasutaCdBunruiCd = null;
    
    /**
     * 操作した直近の住宅番号（団地コード）
     */
    private String housingNoDanchiCd = null;

    /**
     * 操作した直近の住宅番号（棟コード）
     */
    private String housingNoTouCd = null;

    /**
     * 操作した直近の住宅番号（住戸コード）
     */
    private String housingNoJukoCd = null;

    /**
     * 操作した直近の住宅番号（枝番）
     */
    private String housingNoBranch = null;

    /**
     * 操作した直近の住宅番号(至)（団地コード）
     */
    private String housingNoToDanchiCd = null;

    /**
     * 操作した直近の住宅番号(至)（棟コード）
     */
    private String housingNoToTouCd = null;

    /**
     * 操作した直近の住宅番号(至)（住戸コード）
     */
    private String housingNoToJukoCd = null;

    /**
     * 操作した直近の住宅番号(至)（枝番）
     */
    private String housingNoToBranch = null;

    /**
     * 操作した直近の駐車場番号（団地コード）
     */
    private String chushajoNoDanchiCd = null;

    /**
     * 操作した直近の駐車場番号（区画コード）
     */
    private String chushajoNoKukakuCd = null;

    /**
     * 操作した直近の 駐車場番号（枝番）
     */
    private String chushajoNoBranch = null;

    /**
     * 操作した直近の収納日（yyyyMMdd）
     */
    private String storageDate = null;

    /**
     * 掲示件名連番
     */
    private String keijiKenmeiSeqNo = null;

    /**
     * 検索条件
     */
    private String searchString;

    /**
     * 掲示件名登録年月
     */
    private String keijiKenmeiTorokuYm = null;

    /**
     * 操作した直近の傾斜区分
     */
    private String keishakubun = null;

    /**
     * 操作した直近の入居日の年度(新居情報)
     */
    private String nyukyoNendo = null;

    /**
     * 住宅番号
     */
    private String housingNo = null;

    /**
     * 駐車場番号
     */
    private String chuushajouNo = null;

    /**
     * 年度
     */
    private String nenDo = null;
    
    /**
     * 画面遷移区分
     */
    private String gamenHyojiKubun = null;

    /**
     * コード分類マスタ(業務ID)的取得。
     * @return コード分類マスタ(業務ID)
     */
    public String getCdBunruiMasutaGyoumuId() {
        return cdBunruiMasutaGyoumuId;
    }

    /**
     * コード分類マスタ(業務ID)的设定。
     * @param cdBunruiMasutaGyoumuId コード分類マスタ(業務ID)
     */
    public void setCdBunruiMasutaGyoumuId(String cdBunruiMasutaGyoumuId) {
        this.cdBunruiMasutaGyoumuId = cdBunruiMasutaGyoumuId;
    }

    /**
     * コード分類マスタ(コード分類コード)的取得。
     * @return コード分類マスタ(コード分類コード)
     */
    public String getCdBunruiMasutaCdBunruiCd() {
        return cdBunruiMasutaCdBunruiCd;
    }

    /**
     * コード分類マスタ(コード分類コード)的设定。
     * @param cdBunruiMasutaCdBunruiCd コード分類マスタ(コード分類コード)
     */
    public void setCdBunruiMasutaCdBunruiCd(String cdBunruiMasutaCdBunruiCd) {
        this.cdBunruiMasutaCdBunruiCd = cdBunruiMasutaCdBunruiCd;
    }

    /**
     * 操作した直近の住宅番号（団地コード）を取得します。
     * 
     * @return 操作した直近の住宅番号（団地コード）
     */
    public String getHousingNoDanchiCd() {
        return housingNoDanchiCd;
    }

    /**
     * 操作した直近の住宅番号（団地コード）を設定します。
     * 
     * @param housingNoDanchiCd 操作した直近の住宅番号（団地コード）
     */
    public void setHousingNoDanchiCd(String housingNoDanchiCd) {
        this.housingNoDanchiCd = housingNoDanchiCd;
    }

    /**
     * 操作した直近の住宅番号（棟コード）を取得します。
     * 
     * @return 操作した直近の住宅番号（棟コード）
     */
    public String getHousingNoTouCd() {
        return housingNoTouCd;
    }

    /**
     * 操作した直近の住宅番号（棟コード）を設定します。
     * 
     * @param housingNoTouCd 操作した直近の住宅番号（棟コード）
     */
    public void setHousingNoTouCd(String housingNoTouCd) {
        this.housingNoTouCd = housingNoTouCd;
    }

    /**
     * 操作した直近の住宅番号（住戸コード）を取得します。
     * 
     * @return 操作した直近の住宅番号（住戸コード）
     */
    public String getHousingNoJukoCd() {
        return housingNoJukoCd;
    }

    /**
     * 操作した直近の住宅番号（住戸コード）を設定します。
     * 
     * @param housingNoJukoCd 操作した直近の住宅番号（住戸コード）
     */
    public void setHousingNoJukoCd(String housingNoJukoCd) {
        this.housingNoJukoCd = housingNoJukoCd;
    }

    /**
     * 操作した直近の住宅番号（枝番）を取得します。
     * 
     * @return 操作した直近の住宅番号（枝番）
     */
    public String getHousingNoBranch() {
        return housingNoBranch;
    }

    /**
     * 操作した直近の住宅番号（枝番）を設定します。
     * 
     * @param housingNoBranch 操作した直近の住宅番号（枝番）
     */
    public void setHousingNoBranch(String housingNoBranch) {
        this.housingNoBranch = housingNoBranch;
    }

    /**
     * 操作した直近の住宅番号(至)（団地コード）を取得します。
     * 
     * @return 操作した直近の住宅番号(至)（団地コード）
     */
    public String getHousingNoToDanchiCd() {
        return housingNoToDanchiCd;
    }

    /**
     * 操作した直近の住宅番号(至)（団地コード）を設定します。
     * 
     * @param housingNoToDanchiCd 操作した直近の住宅番号(至)（団地コード）
     */
    public void setHousingNoToDanchiCd(String housingNoToDanchiCd) {
        this.housingNoToDanchiCd = housingNoToDanchiCd;
    }

    /**
     * 操作した直近の住宅番号(至)（棟コード）を取得します。
     * 
     * @return 操作した直近の住宅番号(至)（棟コード）
     */
    public String getHousingNoToTouCd() {
        return housingNoToTouCd;
    }

    /**
     * 操作した直近の住宅番号(至)（棟コード）を設定します。
     * 
     * @param housingNoToTouCd 操作した直近の住宅番号(至)（棟コード）
     */
    public void setHousingNoToTouCd(String housingNoToTouCd) {
        this.housingNoToTouCd = housingNoToTouCd;
    }

    /**
     * 操作した直近の住宅番号(至)（住戸コード）を取得します。
     * 
     * @return 操作した直近の住宅番号(至)（住戸コード）
     */
    public String getHousingNoToJukoCd() {
        return housingNoToJukoCd;
    }

    /**
     * 操作した直近の住宅番号(至)（住戸コード）を設定します。
     * 
     * @param housingNoToJukoCd 操作した直近の住宅番号(至)（住戸コード）
     */
    public void setHousingNoToJukoCd(String housingNoToJukoCd) {
        this.housingNoToJukoCd = housingNoToJukoCd;
    }

    /**
     * 操作した直近の住宅番号(至)（枝番）を取得します。
     * 
     * @return 操作した直近の住宅番号(至)（枝番）
     */
    public String getHousingNoToBranch() {
        return housingNoToBranch;
    }

    /**
     * 操作した直近の住宅番号(至)（枝番）を設定します。
     * 
     * @param housingNoToBranch 操作した直近の住宅番号(至)（枝番）
     */
    public void setHousingNoToBranch(String housingNoToBranch) {
        this.housingNoToBranch = housingNoToBranch;
    }

    /**
     * 操作した直近の駐車場番号（団地コード）を取得します。
     * 
     * @return 操作した直近の駐車場番号（団地コード）
     */
    public String getChushajoNoDanchiCd() {
        return chushajoNoDanchiCd;
    }

    /**
     * 操作した直近の駐車場番号（団地コード）を設定します。
     * 
     * @param chushajoNoDanchiCd 操作した直近の駐車場番号（団地コード）
     */
    public void setChushajoNoDanchiCd(String chushajoNoDanchiCd) {
        this.chushajoNoDanchiCd = chushajoNoDanchiCd;
    }

    /**
     * 操作した直近の駐車場番号（区画コード）を取得します。
     * 
     * @return 操作した直近の駐車場番号（区画コード）
     */
    public String getChushajoNoKukakuCd() {
        return chushajoNoKukakuCd;
    }

    /**
     * 操作した直近の駐車場番号（区画コード）を設定します。
     * 
     * @param chushajoNoKukakuCd 操作した直近の駐車場番号（区画コード）
     */
    public void setChushajoNoKukakuCd(String chushajoNoKukakuCd) {
        this.chushajoNoKukakuCd = chushajoNoKukakuCd;
    }

    /**
     * 操作した直近の 駐車場番号（枝番）を取得します。
     * 
     * @return 操作した直近の 駐車場番号（枝番）
     */
    public String getChushajoNoBranch() {
        return chushajoNoBranch;
    }

    /**
     * 操作した直近の 駐車場番号（枝番）を設定します。
     * 
     * @param chushajoNoBranch 操作した直近の 駐車場番号（枝番）
     */
    public void setChushajoNoBranch(String chushajoNoBranch) {
        this.chushajoNoBranch = chushajoNoBranch;
    }

    /**
     * 操作した直近の収納日（yyyyMMdd）を取得します。
     * 
     * @return 操作した直近の収納日（yyyyMMdd）
     */
    public String getStorageDate() {
        return storageDate;
    }

    /**
     * 操作した直近の収納日（yyyyMMdd）を設定します。
     * 
     * @param storageDate 操作した直近の収納日（yyyyMMdd）
     */
    public void setStorageDate(String storageDate) {
        this.storageDate = storageDate;
    }

    /**
     * 掲示件名連番を取得します。
     * 
     * @return 掲示件名連番
     */
    public String getKeijiKenmeiSeqNo() {
        return keijiKenmeiSeqNo;
    }

    /**
     * 掲示件名連番を設定します。
     * 
     * @param keijiKenmeiSeqNo 掲示件名連番
     */
    public void setKeijiKenmeiSeqNo(String keijiKenmeiSeqNo) {
        this.keijiKenmeiSeqNo = keijiKenmeiSeqNo;
    }

    /**
     * 検索条件を取得します。
     * 
     * @return 検索条件
     */
    public String getSearchString() {
        return searchString;
    }

    /**
     * 検索条件を設定します。
     * 
     * @param searchString 検索条件
     */
    public void setSearchString(String searchString) {
        this.searchString = searchString;
    }

    /**
     * 掲示件名登録年月を取得します。
     * 
     * @return 掲示件名登録年月
     */
    public String getKeijiKenmeiTorokuYm() {
        return keijiKenmeiTorokuYm;
    }

    /**
     * 掲示件名登録年月を設定します。
     * 
     * @param keijiKenmeiTorokuYm 掲示件名登録年月
     */
    public void setKeijiKenmeiTorokuYm(String keijiKenmeiTorokuYm) {
        this.keijiKenmeiTorokuYm = keijiKenmeiTorokuYm;
    }

    /**
     * 操作した直近の傾斜区分を取得します。
     * 
     * @return 操作した直近の傾斜区分
     */
    public String getKeishakubun() {
        return keishakubun;
    }

    /**
     * 操作した直近の傾斜区分を設定します。
     * 
     * @param keishakubun 操作した直近の傾斜区分
     */
    public void setKeishakubun(String keishakubun) {
        this.keishakubun = keishakubun;
    }

    /**
     * 操作した直近の入居日の年度(新居情報)を取得します。
     * 
     * @return 操作した直近の入居日の年度(新居情報)
     */
    public String getNyukyoNendo() {
        return nyukyoNendo;
    }

    /**
     * 操作した直近の入居日の年度(新居情報)を設定します。
     * 
     * @param nyukyoNendo 操作した直近の入居日の年度(新居情報)
     */
    public void setNyukyoNendo(String nyukyoNendo) {
        this.nyukyoNendo = nyukyoNendo;
    }

    /**
     * 住宅番号を取得します。
     * 
     * @return 住宅番号
     */
    public String getHousingNo() {
        return housingNo;
    }

    /**
     * 住宅番号を設定します。
     * 
     * @param housingNo 住宅番号
     */
    public void setHousingNo(String housingNo) {
        this.housingNo = housingNo;
    }

    /**
     * 駐車場番号を取得します。
     * 
     * @return 駐車場番号
     */
    public String getChuushajouNo() {
        return chuushajouNo;
    }

    /**
     * 駐車場番号を設定します。
     * 
     * @param chuushajouNo 駐車場番号
     */
    public void setChuushajouNo(String chuushajouNo) {
        this.chuushajouNo = chuushajouNo;
    }

    /**
     * 年度を取得します。
     * 
     * @return 年度
     */
    public String getNenDo() {
        return nenDo;
    }

    /**
     * 年度を設定します。
     * 
     * @param nenDo 年度
     */
    public void setNenDo(String nenDo) {
        this.nenDo = nenDo;
    }
    
    /**
     * 画面遷移区分を取得します。
     * 
     * @return 画面遷移区分
     */
    public String getGamenHyojiKubun() {
        return gamenHyojiKubun;
    }

    /**
     * 画面遷移区分を設定します。
     * 
     * @param gamenHyojiKubun 画面遷移区分
     */
    public void setGamenHyojiKubun(String gamenHyojiKubun) {
        this.gamenHyojiKubun = gamenHyojiKubun;
    }

}
