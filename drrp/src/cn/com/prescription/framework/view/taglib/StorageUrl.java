/*
 * Copyright(c) 2011 
 */
package cn.com.prescription.framework.view.taglib;

import java.io.IOException;
import java.io.Writer;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.prescription.framework.util.CheckUtils;
import cn.com.prescription.framework.util.LogUtils;
import cn.com.prescription.framework.util.StringUtils;

import org.apache.struts2.components.Component;
import org.apache.struts2.util.TextProviderHelper;

import com.opensymphony.xwork2.util.ValueStack;

/**
 * サブミットタグコンポーネント.
 * 
 * @author bpchikazawa
 */
public class StorageUrl extends Component {

    /**
     * タグ出力開始
     * 
     * @param _valuestack スタック
     * @param _request リクエスト
     * @param _response レスポンス
     */
    public StorageUrl(ValueStack _valuestack, HttpServletRequest _request, HttpServletResponse _response) {
        super(_valuestack);
        this.request = _request;
        this.response = _response;
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

            // タグ出力
            _writer.write(createUrl(this.storageKey, true));

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
     * URL生成
     * 
     * @param _storageKey キー
     * @param _isAlwaysUrl true:キーが見つからなくてもURLを出力する
     * @return URL文字列
     * @throws IOException 予期せぬ例外発生時
     */
    protected String createUrl(String _storageKey, Boolean _isAlwaysUrl) throws IOException {

        // 結果URL
        String url_ = "";

        // ストレージキー
        this.storageKey = StringUtils.defaultString(findValue(this.storageKey));

        // 終了チェック
        if (!_isAlwaysUrl && CheckUtils.isEmpty(this.storageKey)) {
            return url_;
        }

        // キーチェック
        this.storageKey = CheckUtils.isEmpty(this.storageKey) ? _storageKey : this.storageKey;

        // URL生成
        url_ = request.getContextPath().concat("/pages/jsp/common/storageFile.jsp?k=")
            .concat(URLEncoder.encode(storageKey, request.getCharacterEncoding()));

        // URL返却
        return url_;

    }

    /**
     * @param _value メッセージ検索キー
     * @param _defaultValue メッセージ無し時の初期値
     * @return メッセージ
     */
    protected String findText(String _value, String _defaultValue) {
        // メッセージを取得
        return TextProviderHelper.getText(_value, _defaultValue, null, getStack(), true);
    }

    /**
     * @param _attr 属性名
     * @param _val 属性値
     * @return 属性文字列 _attr="_val"
     */
    protected String attr(String _attr, String _val) {
        return CheckUtils.isEmpty(_val) ? "" : _attr.concat("=").concat(quote(_val));
    }

    /**
     * @param _val 対象文字列
     * @return ダブルクォート文字列
     */
    protected String quote(String _val) {
        return "\"".concat(StringUtils.defaultString(_val)).concat("\"");
    }

    /** リクエスト */
    protected HttpServletRequest request = null;
    /** レスポンス */
    protected HttpServletResponse response = null;

    /** storageKey属性 */
    protected String storageKey = "";

    /**
     * @param _storageKey storageKey属性
     */
    public final void setStorageKey(String _storageKey) {
        storageKey = _storageKey;
    }

}
