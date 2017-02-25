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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.seasar.extension.datasource.DataSourceFactory;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

import cn.com.prescription.framework.StandardConstantsIF;
import cn.com.prescription.framework.common.session.UserSessionInfo;
import cn.com.prescription.framework.common.session.UserSessionUtils;
import cn.com.prescription.framework.util.CheckUtils;

/**
 * データソース的设定ためのフィルタです。
 * 
 * @author 唐代明
 */
/*
 * 更新
 * DATE: 2010/06/15 マルチスレッドに対応しました。
 * NAME: chikazawa
 */
public class DataSourceFilter implements Filter {

    /**
     * データソースフィルタ処理.
     * <p>
     * 用户の登录情報からデータソースを切り替える。
     * </p>
     * 
     * @param _servletRequest リクエスト
     * @param _servletResponse レスポンス
     * @param _chain フィルタチェーン
     * @throws IOException 予期せぬ错误発生時
     * @throws ServletException 予期せぬ错误発生時
     */
    public void doFilter(ServletRequest _servletRequest, ServletResponse _servletResponse, FilterChain _chain)
                    throws IOException, ServletException {

        // セッション
        HttpSession session = ((HttpServletRequest) _servletRequest).getSession(false);
        // 用户登录情報
        UserSessionInfo userInfo = null;
        // データソース
        String ds = null;

        // セッションが存在する
        if (session != null) {
            // 登录用户情報を取得
            userInfo = (UserSessionInfo) session.getAttribute(UserSessionUtils.SESSION_KEY);
        }

        // 用户情報が存在する
        if (userInfo != null) {
            // 用户のスキーマ名を取得
            ds = userInfo.getSchemaName();
        }

        // データソースが未設定の場合
        if (CheckUtils.isEmpty(ds)) {
            // デフォルトスキーマに接続
            ds = StandardConstantsIF.KYOTU_KYOTU_SCHEMA;
        }

        // コンテナからファクトリを取得
        DataSourceFactory dsFactory = (DataSourceFactory) SingletonS2ContainerFactory.getContainer()
            .getComponent(DataSourceFactory.class);

        // データソース名を設定
        dsFactory.setSelectableDataSourceName(ds);

        // 次フィルタへ
        _chain.doFilter(_servletRequest, _servletResponse);

    }

    /**
     * フィルタ初期化.
     * 
     * @param _filterConfig 初期化パラメータ
     * @throws ServletException 予期せぬ错误発生時
     */
    public void init(FilterConfig _filterConfig) throws ServletException {
    }

    /**
     * フィルタ破棄.
     */
    public void destroy() {
    }

}
