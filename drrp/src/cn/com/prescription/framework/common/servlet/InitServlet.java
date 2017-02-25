/*
 * Copyright(c) 2011 
 */
package cn.com.prescription.framework.common.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

/**
 * コンテキスト情報保持用サーブレット.
 * 
 * @author NTTDC
 */
public class InitServlet extends HttpServlet {

    /** 串行版本号 */
    private static final long serialVersionUID = 1L;

    /**
     * サーブレット初期化.
     * 
     * @throws ServletException 予期せぬ错误発生時
     */
    @Override
    public void init() throws ServletException {
    }
}
