/*
 * Copyright(c) 2011 
 */
package cn.com.prescription.framework.common.filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.com.prescription.framework.common.session.UserSessionInfo;
import cn.com.prescription.framework.common.session.UserSessionUtils;
import cn.com.prescription.framework.util.CheckUtils;

/**
 * セッションの認証情報をチェックするフィルタです.
 * 
 * @author nttdc
 */
public class SecurityFilter implements Filter {

    /** 認証错误時リダイレクトURL：初期化パラメータキー */
    public static final String CONFIG_REDIRECTURL = "redirectURL";

    /** 認証スキップ：初期化パラメータキー */
    public static final String CONFIG_UN_RESTRICTED_RESOURCES = "unRestrictedResources";

    /** 認証スキップ対象 */
    private Set<String> unRestrictedResources = new HashSet<String>();

    /** リダイレクトURL */
    private String redirectURL = null;

    /**
     * 認証チェックフィルタ.
     * 
     * @param _servletRequest リクエスト
     * @param _servletResponse レスポンス
     * @param _chain フィルタチェーン
     * @throws IOException 予期せぬ错误発生時
     * @throws ServletException 予期せぬ错误発生時
     */
    public void doFilter(ServletRequest _servletRequest, ServletResponse _servletResponse, FilterChain _chain)
                    throws IOException, ServletException {

        // リクエスト
        HttpServletRequest request = (HttpServletRequest) _servletRequest;
        // レスポンス
        HttpServletResponse response = (HttpServletResponse) _servletResponse;

        // URLを取得
        String currentURL = request.getRequestURI();
        // URLからチェック範囲を抽出
        String targetURL = currentURL.substring(currentURL.indexOf("/", 1), currentURL.length());

        // 認証対象 かつ セッション情報不足の場合
        if (!this.contains(targetURL)) {
            if (!this.authorize(request)) {
                // セッションタイムアウトページへリダイレクト
                response.sendRedirect(request.getContextPath() + redirectURL);
            } else {
                // 次フィルタ
                _chain.doFilter(request, response);
            }
        } else {
            try {
                Thread.currentThread();
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new ServletException(e);
            }
            // 次フィルタ
            _chain.doFilter(request, response);
        }

    }

    /**
     * フィルタ初期化.
     * 
     * @param _config 初期化パラメータ
     * @throws ServletException 予期せぬ例外発生時
     */
    public void init(FilterConfig _config) throws ServletException {

        // リダイレクトURL
        this.redirectURL = _config.getInitParameter(CONFIG_REDIRECTURL);

        // 認証スキップ対象
        String unRestrictedRes = _config.getInitParameter(CONFIG_UN_RESTRICTED_RESOURCES);

        // Setに変換して保持
        this.unRestrictedResources.addAll(Arrays.asList(unRestrictedRes.split(",")));

    }

    /**
     * フィルタ破棄.
     */
    public void destroy() {
    }

    /**
     * URLが認証対象であるかチェックして返す.
     * 
     * @param _value チェック対象URL
     * @return true:対象外
     */
    private boolean contains(String _value) {

        // 認証対象外の場合 true を返す
        for (String target_ : this.unRestrictedResources) {
            // if (target_.equalsIgnoreCase(_value)) {
            if (_value.matches(target_)) {
                return true;
            }
        }

        // 認証スキップ対象の場合 false を返す
        return false;

    }

    /**
     * 認証チェック.
     * 
     * @param _request リクエスト情報
     * @return true:認証成功
     */
    private boolean authorize(HttpServletRequest _request) {

        // セッションを取得
        HttpSession session = _request.getSession(false);

        // セッションが存在する場合
        if (session != null) {

            // 用户情報を取得
            UserSessionInfo userInfo = (UserSessionInfo) session.getAttribute(UserSessionUtils.SESSION_KEY);

            // 用户情報が存在する場合
            if (userInfo != null && !CheckUtils.isEmpty(userInfo.getUserId())) {

                try {
                    // TODO クライアントIPアドレス が　ない
                    // JktUserKihonDao dao_ = (JktUserKihonDao) ServiceUtils.getDAO(JktUserKihonDao.class);
                    //
                    // JktUserKihonCondition _userKihonCondition = new JktUserKihonCondition();
                    // //　_userKihonCondition.setIpAddr(_request.getRemoteAddr()); TODO クライアントIPアドレス が　ない
                    // _userKihonCondition.setSakujoFlg(StandardConstantsIF.DEL_FLG_YUKO_RECORD);
                    // JktUserKihonModel record = dao_.select(_userKihonCondition);
                    // if (record == null) {
                    // return false;
                    // }
                } catch (Exception e) {
                    return false;
                }

                // 認証OK
                return true;
            }

        }

        // 認証されていない
        return false;

    }

}
