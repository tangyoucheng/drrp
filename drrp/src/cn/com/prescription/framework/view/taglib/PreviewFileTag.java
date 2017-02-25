/*
 * Copyright(c) 2011 
 */
package cn.com.prescription.framework.view.taglib;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.prescription.framework.action.form.ReportFileDto;

import org.apache.struts2.components.Component;
import org.apache.struts2.views.jsp.ComponentTagSupport;

import com.opensymphony.xwork2.util.ValueStack;

/**
 * プレビューファイルタグ.
 * 
 * @author nttdc
 */
public class PreviewFileTag extends ComponentTagSupport {

    /** 串行版本号 */
    private static final long serialVersionUID = -6002576324387156112L;

    /** 名称 */
    private String name;

    /** PDFファイルパス */
    private String pdfFilePath;

    /** PDFファイルパス一覧 */
    private List<ReportFileDto> pdfFilePathList;

    /**
     * Bean取得
     * 
     * @param _stack スタック
     * @param _request リクエスト
     * @param _response レスポンス
     * @return Bean
     */
    @Override
    public Component getBean(ValueStack _stack, HttpServletRequest _request, HttpServletResponse _response) {
        return new PreviewFile(_stack);
    }

    /**
     * パラメータ設定.
     */
    @Override
    protected void populateParams() {
        super.populateParams();

        PreviewFile _pdfFile = (PreviewFile) this.getComponent();
        _pdfFile.setName(this.name);
        _pdfFile.setPdfFilePath(this.pdfFilePath);
        _pdfFile.setPdfFilePathList(this.pdfFilePathList);

    }

    /**
     * 名称的取得。
     * 
     * @return 名称
     */
    public String getName() {
        return name;
    }

    /**
     * 名称的设定。
     * 
     * @param name 名称
     */
    public void setName(String name) {
        this.name = name;
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
     * @param pdfFilePathList PDFファイルパス一覧
     */
    public void setPdfFilePathList(List<ReportFileDto> pdfFilePathList) {
        this.pdfFilePathList = pdfFilePathList;
    }

}
