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
import cn.com.prescription.framework.util.ServiceUtils;
import cn.com.prescription.framework.util.StringUtils;
import cn.com.prescription.framework.view.HTMLEncoder;

import org.apache.struts2.components.Component;
import org.apache.struts2.components.Form;
import org.apache.struts2.util.TextProviderHelper;

import com.opensymphony.xwork2.util.ValueStack;

/**
 * サブミットタグコンポーネント.
 * 
 * @author bpchikazawa
 */
public class Submit extends Component implements Param.Apply {

    /**
     * ダウンロード開始
     * 
     * @param _valuestack スタック
     * @param _request リクエスト
     * @param _response レスポンス
     */
    public Submit(ValueStack _valuestack, HttpServletRequest _request, HttpServletResponse _response) {
        super(_valuestack);
        this.request = _request;
        this.response = _response;
    }

    /**
     * @return Body部をend()で受け取るよう true を返す
     */
    @Override
    public boolean usesBody() {
        return true;
    }

    /**
     * タグ出力
     * 
     * @param _writer 出力先
     * @param _body ボディ部
     * @return 処理結果
     */
    @Override
    public boolean end(Writer _writer, String _body) {
        try {

            // actionが未指定
            if (CheckUtils.isEmpty(this.action)) {
                // formを探す
                Form form_ = (Form) findAncestor(Form.class);
                // formのactionを取得
                if (form_ != null) {
                    // formのactionを取得
                    this.action = StringUtils.defaultString(form_.getParameters().get("actionName"));
                    // ネームスペースも合わせる
                    if (!CheckUtils.isEmpty(this.action)) {
                        this.namespace = StringUtils.defaultString(form_.getParameters().get("namespace"));
                    }
                }
                // まだ空の場合
                if (CheckUtils.isEmpty(this.action)) {
                    this.action = ServiceUtils.getActionUrl();
                }
            }

            // actionURL生成
            String url_ = determineActionURL(this.action, this.namespace, this.method, this.request, this.response,
                this.getParameters(), this.request.getScheme(), true, true, false, false);

            // 空の ID を許可しない
            if (CheckUtils.isEmpty(this.id)) {
                this.id = StringUtils.join(new String[] { this.action, "_", this.method, "_", this.name });
            }

            // タグ生成バッファ
            StringBuilder sb_ = new StringBuilder();

            // 開始タグ
            sb_.append("<a");
            sb_.append(attr(" id", this.id));
            sb_.append(attr(" name", this.name));
            sb_.append(attr(" class", this.cssClass));
            sb_.append(attr(" style", this.cssStyle));
            sb_.append(attr(" tabindex", this.tabindex));
            sb_.append(attr(" title", findStack(this.title, this.title)));
            sb_.append(attr(" href", url_));
            sb_.append(attr(" onclick", getOnClick()));
            // 無効チェック
            if (Boolean.parseBoolean(findStack(this.disabled, this.disabled))) {
                sb_.append(attr(" disabled", "disabled"));
            }
            sb_.append(">");

            // BODY
            sb_.append(findStack(this.value, _body));

            // 終了タグ
            sb_.append("</a>");

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

    /**
     * @return OnClickスクリプトを生成して返します
     */
    private String getOnClick() {

        // OnClickスクリプト
        String onClick_ = StringUtils.defaultString(this.onclick);

        // $(this) を置き換える・・・tricky
        onClick_ = onClick_.replaceAll("\\$\\(this\\)", "\\$\\(\\\"\\#" + this.id + "\\\"\\)");

        // タグバッファ
        StringBuilder sb_ = new StringBuilder();

        // スクリプト生成
        sb_.append("return submitUrl(");
        sb_.append("function() {");
        sb_.append(onClick_);
        sb_.append("}");
        sb_.append(",$(this)");
        sb_.append(");");

        // 生成したスクリプトを返す
        return HTMLEncoder.encodeValue(sb_.toString());

    }

    /**
     * @param _value メッセージ検索キー
     * @param _defaultValue メッセージ無し時の初期値
     * @return メッセージ
     */
    private String findStack(String _value, String _defaultValue) {

        // メッセージをスタックから検索
        String message_ = findString(_value);

        // メッセージを取得
        return TextProviderHelper.getText(message_, _defaultValue, null, getStack(), true);

    }

    /**
     * @param _attr 属性名
     * @param _val 属性値
     * @return 属性文字列 _attr="_val"
     */
    private String attr(String _attr, String _val) {
        return CheckUtils.isEmpty(_val) ? "" : _attr.concat("=").concat(quote(_val));
    }

    /**
     * @param _val 対象文字列
     * @return ダブルクォート文字列
     */
    private String quote(String _val) {
        return "\"".concat(StringUtils.defaultString(_val)).concat("\"");
    }

    /** リクエスト */
    private HttpServletRequest request = null;
    /** レスポンス */
    private HttpServletResponse response = null;

    /** id属性 */
    private String id = "";
    /** name属性 */
    private String name = "";
    /** title属性 */
    private String title = "";
    /** value属性 */
    private String value = "";
    /** cssClass属性 */
    private String cssClass = "";
    /** cssStyle属性 */
    private String cssStyle = "";
    /** tabindex属性 */
    private String tabindex = "";
    /** disabled属性 */
    private String disabled = "";
    /** onclick属性 */
    private String onclick = "";

    /** namespace属性 */
    private String namespace = "";
    /** action属性 */
    private String action = "";
    /** method属性 */
    private String method = "";

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
     * @param _title title属性
     */
    public final void setTitle(String _title) {
        title = _title;
    }

    /**
     * @param _value value属性
     */
    public final void setValue(String _value) {
        this.value = _value;
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
     * @param _tabindex tabindex属性
     */
    public final void setTabindex(String _tabindex) {
        tabindex = _tabindex;
    }

    /**
     * @param _disabled _disabled属性
     */
    public final void setDisabled(String _disabled) {
        disabled = _disabled;
    }

    /**
     * @param _onclick onclick属性
     */
    public final void setOnclick(String _onclick) {
        onclick = _onclick;
    }

    /**
     * @param _namespace namespace属性
     */
    public final void setNamespace(String _namespace) {
        namespace = _namespace;
    }

    /**
     * @param _action action属性
     */
    public final void setAction(String _action) {
        action = _action;
    }

    /**
     * @param _method method属性
     */
    public final void setMethod(String _method) {
        method = _method;
    }

}
