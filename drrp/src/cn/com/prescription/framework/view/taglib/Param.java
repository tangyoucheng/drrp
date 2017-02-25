/*
 * Copyright(c) 2011 
 */
package cn.com.prescription.framework.view.taglib;

import java.io.Writer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.prescription.framework.util.CheckUtils;

import org.apache.struts2.components.Component;
import org.apache.struts2.util.TextProviderHelper;

import com.opensymphony.xwork2.util.ValueStack;

/**
 * パラメータコンポーネント.
 * 
 * @author bpchikazawa
 */
public class Param extends Component {

    /**
     * パラメータ判定用インターフェース.
     */
    public static interface Apply {
        /**
         * @param _key パラメータ名
         * @param _value パラメータ値
         */
        void addParameter(String _key, Object _value);
    }

    /**
     * コンストラクタ
     * 
     * @param _valuestack スタック
     * @param _request リクエスト
     * @param _response レスポンス
     */
    public Param(ValueStack _valuestack, HttpServletRequest _request, HttpServletResponse _response) {
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
     * パラメータ設定
     * 
     * @param _writer 出力先
     * @param _body ボディ部
     * @return 処理結果
     */
    @Override
    public boolean end(Writer _writer, String _body) {

        // パラメータ適用先を検索
        Apply target_ = (Apply) this.findAncestor(Apply.class);

        // 適用先が見つかった場合
        if (target_ != null) {

            // メッセージをスタックから検索
            String actualName_ = findString(this.value);

            // Body部が存在すればそちらを優先
            String defaultMessage_ = CheckUtils.isEmpty(_body) ? actualName_ : _body;

            // メッセージを取得
            String message_ = TextProviderHelper.getText(actualName_, defaultMessage_, null, getStack(), true);

            // パラメータを設定
            target_.addParameter(this.name, message_);

        }

        // 終了
        return true;

    }

    /** リクエスト */
    private HttpServletRequest request = null;
    /** レスポンス */
    private HttpServletResponse response = null;

    /** name属性 */
    private String name = "";
    /** value属性 */
    private String value = "";

    /**
     * @param _name name属性
     */
    public final void setName(String _name) {
        name = _name;
    }

    /**
     * @param _value value属性
     */
    public final void setValue(String _value) {
        value = _value;
    }

}
