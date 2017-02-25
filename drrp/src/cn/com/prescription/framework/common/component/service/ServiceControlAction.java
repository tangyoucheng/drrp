/*
 * Copyright(c) 2011 
 */
package cn.com.prescription.framework.common.component.service;

import java.util.Arrays;

import cn.com.prescription.framework.StandardConstantsIF;
import cn.com.prescription.framework.action.AbstractAction;
import cn.com.prescription.framework.util.CheckUtils;
import cn.com.prescription.framework.util.NumberUtils;
import cn.com.prescription.framework.util.StringUtils;

/**
 * マイ菜单action
 * 
 * @author bpchikazawa
 */
public class ServiceControlAction extends AbstractAction {

    /** 串行版本号 */
    private static final long serialVersionUID = 1L;

    /** サービス停止中标识キー */
    public static final String STOP_SERVICE = ServiceControlAction.class.getName().concat(".isStopService");

    /**
     * 初期化action.
     * 
     * @return null
     * @throws Exception 予期せぬ例外発生時
     */
    @Override
    public String doInit() throws Exception {
        // 何もしない
        return null;
    }

    /**
     * サービス開始action
     * 
     * @return 終了状態
     * @throws Exception 予期せぬ例外発生時
     */
    public String doStart() throws Exception {

        // IPチェック
        if (isEnableAddress(this.request.getRemoteAddr())) {
            // 停止标识OFF
            request.getSession().getServletContext().removeAttribute(STOP_SERVICE);
            // 開始メッセージ
            this.setMessages(Arrays.asList("サービスを開始状態にしました。"));
        }

        // 終了
        return SUCCESS;

    }

    /**
     * サービス停止action
     * 
     * @return 終了状態
     * @throws Exception 予期せぬ例外発生時
     */
    public String doStop() throws Exception {

        // IPチェック
        if (isEnableAddress(this.request.getRemoteAddr())) {
            // 停止标识ON
            request.getSession().getServletContext().setAttribute(STOP_SERVICE, true);
            // 停止メッセージ
            this.setMessages(Arrays.asList("サービスを停止状態にしました。"));
        }

        // 終了
        return SUCCESS;

    }

    /**
     * 制御許可IPチェック.
     * 
     * @param _remoteAddr リモートアドレス
     * @return true:許可アドレス
     */
    public static Boolean isEnableAddress(String _remoteAddr) {

        // 許可IP
        for (String controlAddress_ : StringUtils.split(StandardConstantsIF.KYOTU_SERVICE_CONTROL_KYOKA_IP_ADD, "|")) {
            // チェック
            if (checkIpAddress(controlAddress_, _remoteAddr)) {
                return true;
            }
        }

        // NGアドレス
        return false;

    }

    /**
     * IPアドレスチェック。
     * 
     * @param _ipAddress IPアドレス
     * @param _clientIpAddress クライアントIPアドレス
     * @return チェック結果
     */
    private static Boolean checkIpAddress(String _ipAddress, String _clientIpAddress) {

        // パラメータ不正
        if (CheckUtils.isEmpty(_ipAddress) || CheckUtils.isEmpty(_clientIpAddress)) {
            return false;
        }

        // IPアドレス分割
        String[] limitAddress_ = StringUtils.split(_ipAddress, ".");
        String[] targetAddress_ = StringUtils.split(_clientIpAddress, ".");

        // 要素数が異なる場合错误
        if (limitAddress_.length != 4 || limitAddress_.length != targetAddress_.length) {
            return false;
        }

        // 1要素ずつチェック
        for (int i = 0; i < limitAddress_.length; ++i) {

            // 1要素抽出
            String[] check_ = StringUtils.split(limitAddress_[i], "-");
            String target_ = targetAddress_[i];

            // 制限IP不正
            for (String ip_ : check_) {
                if (!CheckUtils.isNumber(ip_)) {
                    return false;
                }
            }

            // クライアントIP不正
            if (!CheckUtils.isNumber(target_)) {
                return false;
            }

            // 範囲指定不正
            if (check_.length < 1 || 2 < check_.length) {
                return false;
            }

            // 数値変換
            int targetValue_ = NumberUtils.toInt(target_);
            int beginValue_ = NumberUtils.toInt(check_[0]);
            int endValue_ = check_.length < 2 ? beginValue_ : NumberUtils.toInt(check_[1]);

            // 範囲チェック
            if (targetValue_ < beginValue_ || endValue_ < targetValue_) {
                return false;
            }

        }

        // OK
        return true;

    }

}
