/*
 * Copyright(c) 2011 
 */
package cn.com.prescription.framework.view.taglib;

import java.io.IOException;
import java.io.Writer;
import java.math.BigDecimal;
import java.util.logging.Level;
import java.util.logging.Logger;

import cn.com.prescription.framework.util.CheckUtils;
import cn.com.prescription.framework.util.NumberUtils;

import org.apache.struts2.components.Component;

import com.opensymphony.xwork2.util.ValueStack;

/**
 * ページ切替タグ制御クラス.
 * 
 * @author nttdc
 */
public class Pages extends Component {

    /** レコード取得件数 */
    private String recordCount = "-1";

    /** 現在ページの開始行番号 */
    private String pageStartRowNo = "0";

    /** 1ページの表示件数 */
    private String pageSize;

    /** スタイル */
    private String styleClass;

    /** テーマ */
    private String theme;

    /** インクルード */
    private String includes;

    /** 名称 */
    private String name;

    /** unique标识 */
    private boolean uniqueFlag;

    /**
     * コンストラクタ
     * 
     * @param _stack スタック
     * @param _request リクエスト
     */
    public Pages(ValueStack _stack) {
        super(_stack);
    }

    /**
     * @return テーマ
     */
    public String getTheme() {
        return theme;
    }

    /**
     * @param _theme テーマ
     */
    public void setTheme(String _theme) {
        this.theme = _theme;
    }

    /**
     * @return インクルード
     */
    public String getIncludes() {
        return includes;
    }

    /**
     * @param _includes インクルード
     */
    public void setIncludes(String _includes) {
        this.includes = _includes;
    }

    /**
     * @return スタイル
     */
    public String getStyleClass() {
        return styleClass;
    }

    /**
     * @param _styleClass スタイル
     */
    public void setStyleClass(String _styleClass) {
        this.styleClass = _styleClass;
    }

    /**
     * @return レコード取得件数
     */
    public String getRecordCount() {
        return recordCount;
    }

    /**
     * @param _recordCount レコード取得件数
     */
    public void setRecordCount(String _recordCount) {
        this.recordCount = _recordCount;
    }

    /**
     * @return 開始行番号
     */
    public String getPageStartRowNo() {
        return pageStartRowNo;
    }

    /**
     * @param _pageStartRowNo 開始行番号
     */
    public void setPageStartRowNo(String _pageStartRowNo) {
        this.pageStartRowNo = _pageStartRowNo;
    }

    /**
     * @return 1ページ表示件数
     */
    public String getPageSize() {
        return pageSize;
    }

    /**
     * @param _pageSize 1ページ表示件数
     */
    public void setPageSize(String _pageSize) {
        this.pageSize = _pageSize;
    }

    /**
     * @return 名称
     */
    public String getName() {
        return name;
    }

    /**
     * @param _name 名称
     */
    public void setName(String _name) {
        this.name = _name;
    }

    /**
     * unique标识を取得します。
     * 
     * @return unique标识
     */
    public boolean getUniqueFlag() {
        return uniqueFlag;
    }

    /**
     * unique标识を設定します。
     * 
     * @param uniqueFlag unique标识
     */
    public void setUniqueFlag(boolean uniqueFlag) {
        this.uniqueFlag = uniqueFlag;
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
            Object obj = this.getStack().findValue(name + ".pageStartRowNo");
            pageStartRowNo = String.valueOf(obj);
            obj = this.getStack().findValue(name + ".recordCount");
            recordCount = String.valueOf(obj);
            obj = this.getStack().findValue(name + ".pageSize");
            pageSize = String.valueOf(obj);

            str.append("<div ");
            if (styleClass != null) {
                str.append(" class='");
                str.append(styleClass);
                str.append("'>");
            } else {
                str.append(">");
            }
            if (theme == null || "text".equals(theme)) {

                // 最終へ
                if ((NumberUtils.toInt(getRecordCount()) != 0) && getCurrentPageNo() != getLastPageNo()) {
                    str.append("<A class=\"arrowbtn_last\" href=\"javascript:void(0);\"");
                    this.appendAttribute(str, "onClick", getOnclickScript(getLastPageNo()));
                    str.append(" title=\"最終ページ\">");
                    str.append("</A>");
                } else if ((NumberUtils.toInt(getRecordCount()) != 0) && getCurrentPageNo() == getLastPageNo()) {
                    // str.append("<span class=\"arrowbtn_last_no\"");
                    str.append("<span style='display: block;float: right;margin-right:78px;'");
                    str.append(">");
                    str.append("&nbsp;</span>");

                }

                // 次へ
                if ((NumberUtils.toInt(getRecordCount()) != 0) && getCurrentPageNo() != getLastPageNo()) {
                    str.append("<A class=\"arrowbtn_next\" href=\"javascript:void(0);\"");
                    this.appendAttribute(str, "onClick", getOnclickScript(getCurrentPageNo() + 1));
                    str.append(" title=\"次ページ\">");
                    str.append("</A>");
                } else if ((NumberUtils.toInt(getRecordCount()) != 0) && getCurrentPageNo() == getLastPageNo()) {
                    // str.append("<span class=\"arrowbtn_next_no\"");
                    str.append("<span style='display: block;float: right;'");
                    str.append(">");
                    str.append("&nbsp;</span>");

                }

                str.append("<span class=\"arrowbtn_writingbox\">");
                str.append(recordCount);
                str.append("件中");
                str.append(pageStartRowNo);
                str.append("件目～");
                str.append(getPageEndRowNo());
                str.append("件目　</span>");

                // 前へ
                if ((NumberUtils.toInt(getRecordCount()) != 0) && getCurrentPageNo() != 1) {
                    str.append("<A class=\"arrowbtn_previous\" href=\"javascript:void(0);\"");
                    this.appendAttribute(str, "onClick", getOnclickScript(getCurrentPageNo() - 1));
                    str.append(" title=\"前ページ\">");
                    str.append("</A>");
                    // } else if ((NumberUtils.toInt(getRecordCount()) != 0) && getCurrentPageNo() == 1) {
                    // str.append("<span class=\"arrowbtn_previous_no\"");
                    // str.append(">");
                    // str.append("</span>");

                }

                // 最初へ
                if ((NumberUtils.toInt(getRecordCount()) != 0) && (getCurrentPageNo() != 1)) {
                    str.append("<A class=\"arrowbtn_first\" href=\"javascript:void(0);\"");
                    this.appendAttribute(str, "onClick", getOnclickScript(1));
                    str.append(" title=\"最初ページ\">");
                    str.append("</A>");
                    // } else if ((NumberUtils.toInt(getRecordCount()) != 0) && (getCurrentPageNo() == 1)) {
                    // str.append("<span class=\"arrowbtn_first_no\"");
                    // str.append(">");
                    // str.append("</span>");

                }
            }

            str.append("</div>");
            if (uniqueFlag) {
                str.append(renderHiddenElement("pageBtnSubmitFlg", name + ".pageBtnSubmitFlg", "false"));
                str.append(renderHiddenElement("pageStartRowNo", name + ".pageStartRowNo", pageStartRowNo));
                str.append(renderHiddenElement("recordCount", name + ".recordCount", recordCount));
                str.append(renderHiddenElement("pageSize", name + ".pageSize", pageSize));
            }

            _writer.write(str.toString());

        } catch (IOException ex) {
            Logger.getLogger(Pages.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    /**
     * 現在の表示ページ番号的取得。
     * 
     * @return 現在の表示ページ番号
     */
    public int getCurrentPageNo() {

        if (NumberUtils.toInt(pageStartRowNo) < 0 || NumberUtils.toInt(recordCount) < 0) {
            return -1;
        }

        BigDecimal[] pageNo = NumberUtils.divideAndRemainder(new BigDecimal(pageStartRowNo), new BigDecimal(pageSize));
        if (pageNo[1].intValue() > 0) {
            return pageNo[0].intValue() + 1;
        } else {
            return pageNo[0].intValue();
        }
    }

    /**
     * onclick時のjavascriptを生成する。
     * 
     * @param _pageNo ページ番号
     * @return onclick時のjavascript
     */
    private String getOnclickScript(int _pageNo) {

        // form属性が指定されている場合、そのまま使用
        String formName = getName();

        // ページ情報設定スクリプト
        StringBuilder pageScript = new StringBuilder();

        pageScript.append("if (is_form_changed()) {window.top.showAjaxMessageById('W30003');return false;}");

        pageScript.append("var pageBtnSubmitFlg = document.getElementById('pageBtnSubmitFlg').value;");
        pageScript.append("if(pageBtnSubmitFlg == 'true'){return false;}");

        pageScript.append("document.getElementById('pageBtnSubmitFlg').value ='true';");

        pageScript.append("document.getElementById('pageStartRowNo').value = '");
        /*
         * pageScript.append(formName);
         * pageScript.append(".pageStartRowNo.value = '");
         */
        pageScript.append(getPageStartRowNo(_pageNo));
        pageScript.append("'; ");

        pageScript.append("document.getElementById('recordCount').value = '");
        pageScript.append(getRecordCount());
        pageScript.append("'; ");

        pageScript.append("document.getElementById('pageSize').value = '");
        pageScript.append(getPageSize());
        pageScript.append("'; ");

        // onClickeventのスクリプトの設定
        String scriptActionVariableName = "im_action";

        // スクリプト
        StringBuilder script = new StringBuilder();
        script.append("var ");
        script.append(scriptActionVariableName);
        script.append(" = document.");
        script.append(formName);
        script.append(".action; ");

        script.append("document.");
        script.append(formName);
        script.append(".action = document.");
        script.append(formName);
        script.append(".action + '?method:doPage';");

        script.append("document.");
        script.append(formName);
        script.append(".submit(); ");
        script.append("return false;");

        // スクリプトの生成
        String realScript = "";
        realScript = "var myFunction = new Function(&quot;doClearWaterMark();" + pageScript.toString()
                        + "&quot;); var result = myFunction();" + " if (result == undefined || result == true) {"
                        + script.toString() + "};";

        return realScript;
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
     * 指定ページ番号の開始行番号的取得。
     * 
     * @param _pageNo ページ番号
     * @return 現在ページの開始行番号
     */
    public int getPageStartRowNo(int _pageNo) {

        if (NumberUtils.toInt(getRecordCount()) > 0) {
            return (NumberUtils.toInt(pageSize) * _pageNo) - NumberUtils.toInt(pageSize) + 1;
        }

        return 0;
    }

    /**
     * 現在ページの終了行番号的取得。
     * 
     * @return 現在ページの終了行番号
     */
    public int getPageEndRowNo() {

        if (NumberUtils.toInt(getRecordCount()) > 0) {
            int pageEndNo = NumberUtils.toInt(pageSize) * getCurrentPageNo();

            if (NumberUtils.toInt(getRecordCount()) < pageEndNo) {
                return NumberUtils.toInt(getRecordCount());
            }
            return pageEndNo;
        }

        return 0;
    }

    /**
     * 最終ページ番号的取得。
     * <p>
     * 最終ページ番号的取得。<br>
     * </p>
     * 
     * @return 最終ページ番号
     */
    public int getLastPageNo() {

        int lastPage = 1;

        if (NumberUtils.toInt(getRecordCount()) > 0) {
            lastPage = NumberUtils.toInt(getRecordCount()) / NumberUtils.toInt(pageSize);
            if ((NumberUtils.toInt(getRecordCount()) % NumberUtils.toInt(pageSize)) != 0) {
                lastPage++;
            }
        }

        return lastPage;
    }

    /**
     * hiddenタイプのINPUTタグを生成する。
     * 
     * @param _id フィールドID
     * @param _name フィールド名
     * @param _value 値
     * @return INPUTタグ
     */
    protected String renderHiddenElement(String _id, String _name, String _value) {

        StringBuilder handler = new StringBuilder();

        handler.append("<INPUT");
        appendAttribute(handler, "type", "hidden");
        appendAttribute(handler, "id", _id);
        appendAttribute(handler, "name", _name);
        appendAttribute(handler, "value", _value);

        handler.append("/>");

        return handler.toString();
    }
}
