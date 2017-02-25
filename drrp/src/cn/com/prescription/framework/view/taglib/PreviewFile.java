/*
 * Copyright(c) 2011 
 */
package cn.com.prescription.framework.view.taglib;

import java.io.IOException;
import java.io.Writer;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import cn.com.prescription.framework.action.form.ReportFileDto;
import cn.com.prescription.framework.util.CheckUtils;

import org.apache.struts2.components.Component;

import com.opensymphony.xwork2.util.ValueStack;

/**
 * プレビューファイルタグ制御クラス.
 * 
 * @author nttdc
 */
public class PreviewFile extends Component {

    /** 名称 */
    private String name;

    /** PDFファイルパス */
    private String pdfFilePath;

    /** PDFファイルパス一覧 */
    private List<ReportFileDto> pdfFilePathList;

    /**
     * コンストラクタ
     * 
     * @param _stack スタック
     * @param _request リクエスト
     */
    public PreviewFile(ValueStack _stack) {
        super(_stack);
    }

    /**
     * タグ出力開始.
     * 
     * @param _writer 出力先
     * @return 出力結果
     */
    @Override
    public boolean start(Writer _writer) {

        boolean result = super.start(_writer);
        try {
            StringBuilder str = new StringBuilder();

            // 从ValueStack中取出数值
            pdfFilePath = (String) this.getStack().findValue(name + ".pdfFilePath");
            pdfFilePathList = (List<ReportFileDto>) this.getStack().findValue(name + ".pdfFilePathList");

            if (!CheckUtils.isEmpty(pdfFilePath)) {
                str.append(renderHiddenElement("report_pdfFile_1", pdfFilePath));
            }

            if (pdfFilePathList != null && !pdfFilePathList.isEmpty()) {
                for (int i = 0; i < pdfFilePathList.size(); i++) {
                    str.append(renderHiddenElement("report_pdfFile_" + (i + 1), pdfFilePathList.get(i).getPdfFilePath()));
                }
            }

            _writer.write(str.toString());

        } catch (IOException ex) {
            Logger.getLogger(Pages.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    /**
     * hiddenタイプのaタグを生成する。
     * 
     * @param _id フィールドID
     * @param _href href値
     * @return aタグ
     */
    protected String renderHiddenElement(String _id, String _href) {

        StringBuilder handler = new StringBuilder();

        handler.append("<a");
        appendAttribute(handler, "id", _id);
        appendAttribute(handler, "style", "display: none;");
        appendAttribute(handler, "href", _href);
        handler.append("/>");

        return handler.toString();
    }

    /**
     * 属性を生成.
     * 
     * @param _handlers 追加先バッファ
     * @param _name 属性名
     * @param _value 属性値
     */
    protected void appendAttribute(StringBuilder _handlers, String _name, Object _value) {
        if (_value != null && !CheckUtils.isEmpty(_value.toString())) {
            _handlers.append(" ");
            _handlers.append(_name);
            _handlers.append("=\"");
            _handlers.append(_value);
            _handlers.append("\"");
        }
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
