/*
 * Copyright(c) 2011 
 */
package cn.com.prescription.framework.view.taglib;

import java.io.Writer;
import java.util.Collection;

import cn.com.prescription.framework.common.component.dialog.DialogAction;
import cn.com.prescription.framework.util.CheckUtils;
import cn.com.prescription.framework.util.LogUtils;
import cn.com.prescription.framework.util.ServiceUtils;
import cn.com.prescription.framework.util.StringUtils;
import cn.com.prescription.framework.view.HTMLEncoder;

import org.apache.struts2.components.Component;

import com.opensymphony.xwork2.util.ValueStack;

/**
 * アプリケーション错误時ポップアップ表示タグ.
 * 
 * @author bpchikazawa
 */
public class PopUpError extends Component {

    /**
     * ポップアップ错误表示
     * 
     * @param _stack スタック
     */
    public PopUpError(ValueStack _stack) {
        super(_stack);
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

            // 错误标识取得
            boolean isExclamation_ = Boolean.valueOf(getStack().findString("isExclamation"));

            // 登録完了标识取得
            boolean isCompleteDialog_ = Boolean.valueOf(getStack().findString("isCompleteDialog"));

            // メッセージ無し
            if (!isExclamation_ && !isCompleteDialog_) {
                return true;
            }

            // タグ出力
            _writer.write(showPopUp(isExclamation_, isCompleteDialog_));

            // 終了
            return true;

        } catch (Exception e) {
            // 予期せぬ错误
            LogUtils.error(e.getMessage(), e);
            // 错误終了
            return false;
        }
    }

    /**
     * ダイアログメッセージ生成
     * 
     * @return メッセージタグ
     */
    private String dialogMessage() {

        // メッセージ取得
        Collection<String> messages_ = (Collection<String>) getStack().findValue("messages");

        // メッセージを繋げる
        String message_ = StringUtils.join(messages_.toArray(), '\n');

        // 改行タグに変換
        return message_.replaceAll("\n", "<br />");

    }

    /**
     * ダイアログ起動スクリプト生成
     * 
     * @param _isExclamation 例外错误标识
     * @param _isCompleteDialog 登録完了ダイアログ标识
     * @return ダイアログ起動スクリプトタグ
     */
    private String showPopUp(boolean _isExclamation, boolean _isCompleteDialog) {

        // ダイアログタイプ
        String type_ = "";

        // 错误
        if (_isExclamation) {
            type_ = "isExclamation";
        }

        // 登録完了
        if (_isCompleteDialog) {
            type_ = "isComplete";
        }

        // コールバック指定を取得
        String callBack_ = ServiceUtils.popSession(DialogAction.CALLBACK_FUNCTION_SESSION_KEY);

        // タグ生成バッファ
        StringBuilder sb_ = new StringBuilder();

        // ダイアログ起動スクリプト
        sb_.append("<script");
        sb_.append(" type=\"text/javascript\">");
        sb_.append("$(document).ready(function(){");
        sb_.append("setTimeout('window.top.showPopUpMessage(");
        sb_.append(quote(type_));
        sb_.append(",");
        sb_.append(quote(HTMLEncoder.encodeValue(dialogMessage())));
        sb_.append(",");
        sb_.append("$(\"form\").get(0)");
        sb_.append(",");
        sb_.append(CheckUtils.isEmpty(callBack_) ? "doClearWaterMark" : callBack_);
        sb_.append(")',150);}");
        sb_.append(")");
        sb_.append("</script>");

        // 错误有無判定用
        sb_.append("<input type='hidden' id='popUpMessage' value='1' />");

        // タグを返却
        return sb_.toString();

    }

    /**
     * @param _val 対象文字列
     * @return ダブルクォート文字列
     */
    private String quote(String _val) {
        return "\"".concat(StringUtils.defaultString(_val)).concat("\"");
    }

}
