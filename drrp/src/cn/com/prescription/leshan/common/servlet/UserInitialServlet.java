/*
 * Copyright(c) 2010 
 */
package cn.com.prescription.leshan.common.servlet;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserInitialServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /** リダイレクトURL */
    private String loginUrl = null;
    /** サーブレットコンテキスト */
    private static ServletContext context = null;
    /** コンテキストパス */
    private static String contextPath = null;

    /**
     * UserInitialServlet的构造
     */
    public UserInitialServlet() {
        super();
    }

    /**
     * 
     */
    @Override
    public void init(ServletConfig cfg) throws ServletException {
        super.init(cfg);
        // サーブレットコンテキスト
        context = cfg.getServletContext();
        // コンテキストパス
        contextPath = context.getRealPath("");
        // リダイレクトURL
        loginUrl = context.getContextPath() + cfg.getInitParameter("loginUrl");
    }

    /**
     * 
     */
    @Override
    public void destroy() {
        super.destroy();
    }

    /**
     * セッションを破棄して指定のURLへのリダイレクトレスポンスを返します
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        doPost(req, res);
    }

    /**
     * セッションを破棄して指定のURLへのリダイレクトレスポンスを返します
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        // req.getSession().invalidate();
        res.sendRedirect(loginUrl);
    }

    /**
     * @return サーブレットコンテキスト
     */
    public static ServletContext getContext() {
        return context;
    }

    /**
     * @return コンテキストパス
     */
    public static String getContextPath() {
        return contextPath;
    }
}
