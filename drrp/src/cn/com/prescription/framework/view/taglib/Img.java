/*
 * Copyright(c) 2011 
 */
package cn.com.prescription.framework.view.taglib;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.prescription.framework.util.CheckUtils;
import cn.com.prescription.framework.util.LogUtils;
import cn.com.prescription.framework.view.HTMLEncoder;

import com.opensymphony.xwork2.util.ValueStack;

/**
 * サブミットタグコンポーネント.
 * 
 * @author bpchikazawa
 */
public class Img extends StorageUrl {

    /**
     * タグ出力開始
     * 
     * @param _valuestack スタック
     * @param _request リクエスト
     * @param _response レスポンス
     */
    public Img(ValueStack _valuestack, HttpServletRequest _request, HttpServletResponse _response) {
        super(_valuestack, _request, _response);
    }

    /**
     * タグ出力
     * 
     * @param _writer 出力先
     * @return 処理結果
     */
    @Override
    public boolean start(Writer _writer) {
        try {

            // タグ生成バッファ
            StringBuilder sb_ = new StringBuilder();

            // URL
            String url_ = this.createUrl(this.storageKey, false);

            // キー指定の場合、SRCを書き換え
            if (!CheckUtils.isEmpty(url_)) {
                this.src = url_;
            }

            // 開始タグ
            sb_.append("<img");
            sb_.append(attr(" id", this.id));
            sb_.append(attr(" name", this.name));
            sb_.append(attr(" class", this.cssClass));
            sb_.append(attr(" style", this.cssStyle));
            sb_.append(attr(" src", findText(this.src, this.src)));
            sb_.append(attr(" alt", findText(this.alt, this.alt)));
            sb_.append(attr(" onclick", HTMLEncoder.encodeValue(this.onclick)));
            sb_.append(" />");

            // タグ出力
            _writer.write(sb_.toString());

            // 終了
            return true;

        } catch (IOException e) {
            // 予期せぬ错误
            LogUtils.error(e.getMessage(), e);
            // 错误終了
            return false;
        }
    }

    /** id属性 */
    private String id = "";
    /** name属性 */
    private String name = "";
    /** src属性 */
    private String src = "";
    /** alt属性 */
    private String alt = "";
    /** cssClass属性 */
    private String cssClass = "";
    /** cssStyle属性 */
    private String cssStyle = "";
    /** onclick属性 */
    private String onclick = "";

    /**
     * @param _id id属性
     */
    public final void setId(String _id) {
        id = _id;
    }

    /**
     * @param _name name属性
     */
    public final void setName(String _name) {
        name = _name;
    }

    /**
     * @param _src src属性
     */
    public final void setSrc(String _src) {
        src = _src;
    }

    /**
     * @param _alt alt属性
     */
    public final void setAlt(String _alt) {
        alt = _alt;
    }

    /**
     * @param _cssClass cssClass属性
     */
    public final void setCssClass(String _cssClass) {
        cssClass = _cssClass;
    }

    /**
     * @param _cssStyle cssStyle属性
     */
    public final void setCssStyle(String _cssStyle) {
        cssStyle = _cssStyle;
    }

    /**
     * @param _onclick onclick属性
     */
    public final void setOnclick(String _onclick) {
        onclick = _onclick;
    }

}
