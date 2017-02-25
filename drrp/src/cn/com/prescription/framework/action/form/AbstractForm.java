/*
 * Copyright(c) 2011 
 */
package cn.com.prescription.framework.action.form;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import cn.com.prescription.framework.StandardConstantsIF;
import cn.com.prescription.framework.util.NumberUtils;

/**
 * 標準の抽象form.
 * 
 * @author NTTDC
 */
public abstract class AbstractForm extends StandartForm {

    /** 串行版本号 */
    private static final long serialVersionUID = 6089978703639675612L;

    /** 错误メッセージ画面で「はい」の選択标识 */
    private static final int OK_EVENT_KEY_INIT = 0;

    /** 错误メッセージ画面で「はい」の選択标识 1 */
    public static final int OK_EVENT__KEY1 = 1;
    /** 错误メッセージ画面で「はい」の選択标识 2 */
    public static final int OK_EVENT__KEY2 = 2;
    /** 错误メッセージ画面で「はい」の選択标识 3 */
    public static final int OK_EVENT__KEY3 = 3;
    /** 错误メッセージ画面で「はい」の選択标识 4 */
    public static final int OK_EVENT__KEY4 = 4;
    /** 错误メッセージ画面で「はい」の選択标识 5 */
    public static final int OK_EVENT__KEY5 = 5;
    /** 错误メッセージ画面で「はい」の選択标识 6 */
    public static final int OK_EVENT__KEY6 = 6;

    /** 1ページの表示件数 */
    private int pageSize = NumberUtils.toInt(StandardConstantsIF.KYOTU_PAGE_SIZE);

    /** レコード取得件数 */
    private long recordCount = -1;

    /** 現在ページの開始行番号 */
    private int pageStartRowNo = 1;

    /** ページボタンsubmit标识 */
    private transient String pageBtnSubmitFlg = "false";

    /** idTabsのindex (シリアライズしない) */
    private transient int idTabsIndex = 0;

    /** 最大連番クリア标识 */
    private Boolean maxNoClearFlg = null;

    /** 上传ファイル */
    private List<File> file;
    /** 上传ファイル名 */
    private List<String> fileFileName;
    /** 上传ファイル種別 */
    private List<String> fileContentType;

    /** uploadファールのDto */
    public UploadFileDto uploadFile = null;

    /** バッチキー */
    public String batchKey = null;

    /** 印刷指示画面ID */
    public String printOrderGamenID = null;

    /** 错误メッセージ画面で「はい」の選択标识 */
    private int isClickOkFg = OK_EVENT_KEY_INIT;

    /** 错误メッセージ画面で「はい」の選択キー */
    private int isClickOkKey = OK_EVENT_KEY_INIT;

    /** 初期化のシリアルform */
    public String initSerialForm = "";

    /** 戻る标识 (シリアライズしない) */
    public transient boolean backFlag = false;

    /** PDFファイルパス一覧 */
    public List<ReportFileDto> pdfFilePathList = new ArrayList<ReportFileDto>();

    /** PDFファイルパス */
    public String pdfFilePath = "";

    /** 画面表示区分 */
    public String gamenHyojiKubun = "";

    /** 错误メッセジ */
    private String errorMessage;

    /**
     * 1ページの表示件数的取得。
     * 
     * @return 1ページの表示件数
     */
    public int getPageSize() {
        return pageSize;
    }

    /**
     * 1ページの表示件数的设定。
     * 
     * @param __pageSize 1ページの表示件数
     */
    public void setPageSize(int _pageSize) {
        pageSize = _pageSize;
    }

    /**
     * 物理パス的取得
     * 
     * @param _path 論理パス
     * @return 物理パス
     */
    public String getRealPath(String _path) {
        return ServletActionContext.getServletContext().getRealPath(_path);
    }

    /**
     * レコード取得件数的取得。
     * 
     * @return レコード取得件数
     */
    public long getRecordCount() {
        return recordCount;
    }

    /**
     * レコード取得件数的设定。
     * 
     * @param __recordCount レコード取得件数
     */
    public void setRecordCount(long _recordCount) {
        this.recordCount = _recordCount;
    }

    /**
     * 現在ページの開始行番号的取得。
     * 
     * @return 現在ページの開始行番号
     */
    public int getPageStartRowNo() {
        return pageStartRowNo;
    }

    /**
     * 現在ページの開始行番号的设定。
     * 
     * @param __pageStartRowNo 現在ページの開始行番号
     */
    public void setPageStartRowNo(int _pageStartRowNo) {
        pageStartRowNo = _pageStartRowNo;
    }

    /**
     * ページボタンsubmit标识的取得。
     * 
     * @return ページボタンsubmit标识
     */
    public String getPageBtnSubmitFlg() {
        return pageBtnSubmitFlg;
    }

    /**
     * ページボタンsubmit标识的设定。
     * 
     * @param _pageBtnSubmitFlg ページボタンsubmit标识
     */
    public void setPageBtnSubmitFlg(String _pageBtnSubmitFlg) {
        this.pageBtnSubmitFlg = _pageBtnSubmitFlg;
    }

    /**
     * idTabsのindex (シリアライズしない)的取得。
     * 
     * @return idTabsのindex (シリアライズしない)
     */
    public int getIdTabsIndex() {
        return idTabsIndex;
    }

    /**
     * idTabsのindex (シリアライズしない)的设定。
     * 
     * @param _idTabsIndex idTabsのindex (シリアライズしない)
     */
    public void setIdTabsIndex(int idTabsIndex) {
        this.idTabsIndex = idTabsIndex;
    }

    /**
     * 最大連番クリア标识的取得。
     * 
     * @return 最大連番クリア标识
     */
    public Boolean getMaxNoClearFlg() {
        return maxNoClearFlg;
    }

    /**
     * 最大連番クリア标识的设定。
     * 
     * @param _maxNoClearFlg 最大連番クリア标识
     */
    public void setMaxNoClearFlg(Boolean maxNoClearFlg) {
        this.maxNoClearFlg = maxNoClearFlg;
    }

    /**
     * 上传ファイル的取得。
     * 
     * @return 上传ファイル
     */
    public List<File> getFile() {
        return file;
    }

    /**
     * 上传ファイル的设定。
     * 
     * @param _file 上传ファイル
     */
    public void setFile(List<File> file) {
        this.file = file;
    }

    /**
     * 上传ファイル名的取得。
     * 
     * @return 上传ファイル名
     */
    public List<String> getFileFileName() {
        return fileFileName;
    }

    /**
     * 上传ファイル名的设定。
     * 
     * @param _fileFileName 上传ファイル名
     */
    public void setFileFileName(List<String> fileFileName) {
        this.fileFileName = fileFileName;
    }

    /**
     * 上传ファイル種別的取得。
     * 
     * @return 上传ファイル種別
     */
    public List<String> getFileContentType() {
        return fileContentType;
    }

    /**
     * 上传ファイル種別的设定。
     * 
     * @param _fileContentType 上传ファイル種別
     */
    public void setFileContentType(List<String> fileContentType) {
        this.fileContentType = fileContentType;
    }

    /**
     * uploadファールのDto的取得。
     * 
     * @return uploadファールのDto
     */
    public UploadFileDto getUploadFile() {
        return uploadFile;
    }

    /**
     * uploadファールのDto的设定。
     * 
     * @param _uploadFile uploadファールのDto
     */
    public void setUploadFile(UploadFileDto uploadFile) {
        this.uploadFile = uploadFile;
    }

    /**
     * バッチキー的取得。
     * 
     * @return バッチキー
     */
    public String getBatchKey() {
        return batchKey;
    }

    /**
     * バッチキー的设定。
     * 
     * @param _batchKey バッチキー
     */
    public void setBatchKey(String batchKey) {
        this.batchKey = batchKey;
    }

    /**
     * 印刷指示画面ID的取得。
     * 
     * @return 印刷指示画面ID
     */
    public String getPrintOrderGamenID() {
        return printOrderGamenID;
    }

    /**
     * 印刷指示画面ID的设定。
     * 
     * @param _printOrderGamenID 印刷指示画面ID
     */
    public void setPrintOrderGamenID(String printOrderGamenID) {
        this.printOrderGamenID = printOrderGamenID;
    }

    /**
     * 错误メッセージ画面で「はい」の選択标识的取得。
     * 
     * @return 错误メッセージ画面で「はい」の選択标识
     */
    public int getIsClickOkFg() {
        return isClickOkFg;
    }

    /**
     * 错误メッセージ画面で「はい」の選択标识的设定。
     * 
     * @param _isClickOkFg 错误メッセージ画面で「はい」の選択标识
     */
    public void setIsClickOkFg(int isClickOkFg) {
        this.isClickOkFg = isClickOkFg;
    }

    /**
     * 错误メッセージ画面で「はい」の選択キー的取得。
     * 
     * @return 错误メッセージ画面で「はい」の選択キー
     */
    public int getIsClickOkKey() {
        return isClickOkKey;
    }

    /**
     * 错误メッセージ画面で「はい」の選択キー的设定。
     * 
     * @param _isClickOkKey 错误メッセージ画面で「はい」の選択キー
     */
    public void setIsClickOkKey(int isClickOkKey) {
        this.isClickOkKey = isClickOkKey;
    }

    /**
     * 初期化のシリアルform的取得。
     * 
     * @return 初期化のシリアルform
     */
    public String getInitSerialForm() {
        return initSerialForm;
    }

    /**
     * 初期化のシリアルform的设定。
     * 
     * @param __initSerialForm 初期化のシリアルform
     */
    public void setInitSerialForm(String _initSerialForm) {
        initSerialForm = _initSerialForm;
    }

    /**
     * 戻る标识 (シリアライズしない)的取得。
     * 
     * @return 戻る标识 (シリアライズしない)
     */
    public boolean isBackFlag() {
        return backFlag;
    }

    /**
     * 戻る标识 (シリアライズしない)的设定。
     * 
     * @param _backFlag 戻る标识 (シリアライズしない)
     */
    public void setBackFlag(boolean backFlag) {
        this.backFlag = backFlag;
    }

    /**
     * PDFファイルパス一覧的取得。
     * 
     * @return PDFファイルパス一覧
     */
    public List<ReportFileDto> getPdfFilePathList() {
        return pdfFilePathList;
    }

    /**
     * PDFファイルパス一覧的设定。
     * 
     * @param _pdfFilePathList PDFファイルパス一覧
     */
    public void setPdfFilePathList(List<ReportFileDto> pdfFilePathList) {
        this.pdfFilePathList = pdfFilePathList;
    }

    /**
     * PDFファイルパス的取得。
     * 
     * @return PDFファイルパス
     */
    public String getPdfFilePath() {
        return pdfFilePath;
    }

    /**
     * PDFファイルパス的设定。
     * 
     * @param _pdfFilePath PDFファイルパス
     */
    public void setPdfFilePath(String pdfFilePath) {
        this.pdfFilePath = pdfFilePath;
    }

    /**
     * 画面表示区分的取得。
     * 
     * @return 画面表示区分
     */
    public String getGamenHyojiKubun() {
        return gamenHyojiKubun;
    }

    /**
     * 画面表示区分的设定。
     * 
     * @param _gamenHyojiKubun 画面表示区分
     */
    public void setGamenHyojiKubun(String gamenHyojiKubun) {
        this.gamenHyojiKubun = gamenHyojiKubun;
    }

}
