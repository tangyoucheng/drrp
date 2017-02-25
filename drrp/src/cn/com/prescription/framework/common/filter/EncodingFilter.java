/*
 * Copyright(c) 2011 
 */
package cn.com.prescription.framework.common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import cn.com.prescription.framework.util.CheckUtils;

/**
 * リクエストのエンコーディング的设定ためのフィルタです。
 * 
 * @author 唐代明
 */
public class EncodingFilter implements Filter {

    /** エンコーディング初期化パラメータキー */
    public static final String CONFIG_ENCODING_KEY = "encoding";

    /** デフォルトのエンコーディング */
    public static final String DEFAULT_ENCODING = "UTF-8"; // "Windows-31j";

    /** エンコーディング */
    private String encoding = null;

    /**
     * エンコーディング設定処理.
     * 
     * @param _servletRequest リクエスト
     * @param _servletResponse レスポンス
     * @param _chain フィルタチェーン
     * @throws IOException 予期せぬ错误発生時
     * @throws ServletException 予期せぬ错误発生時
     */
    public void doFilter(ServletRequest _servletRequest, ServletResponse _servletResponse, FilterChain _chain)
                    throws IOException, ServletException {

        // リクエストにエンコード設定が存在しない場合のみ設定
        if (CheckUtils.isEmpty(_servletRequest.getCharacterEncoding())) {
            _servletRequest.setCharacterEncoding(encoding);
        }

        // 次フィルタ
        _chain.doFilter(_servletRequest, _servletResponse);

    }

    /**
     * フィルタ初期化.
     * 
     * @param _config 初期化パラメータ
     * @throws ServletException 予期せぬ例外発生時
     */
    public void init(FilterConfig _config) throws ServletException {

        // 初期化パラメータを取得
        encoding = _config.getInitParameter(CONFIG_ENCODING_KEY);

        // 初期化パラメータ未設定の場合、デフォルトエンコードを指定
        if (CheckUtils.isEmpty(encoding)) {
            encoding = DEFAULT_ENCODING;
        }

    }

    /**
     * フィルタ破棄.
     */
    public void destroy() {
    }

}
