/*
 * Copyright(c) 2011 
 */
package cn.com.prescription.framework.action.form;

import java.io.Serializable;

/**
 * 標準の抽象Dto.
 * 
 * @author NTTDC
 */
public class ReportFileDto implements Serializable {

    /**
     * 串行版本号
     */
    private static final long serialVersionUID = 1L;

    /**
     * PDFファイルパス
     */
    private String pdfFilePath = null;

    /**
     * コンストラクタ.
     */
    public ReportFileDto() {
        super();
    }

    /**
     * コンストラクタ.
     * 
     * @param _pdfFilePath PDFファイルパス
     */
    public ReportFileDto(String _pdfFilePath) {
        super();
        pdfFilePath = _pdfFilePath;
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
     * @param pdfFilePath PDFファイルパス
     */
    public void setPdfFilePath(String pdfFilePath) {
        this.pdfFilePath = pdfFilePath;
    }
}
