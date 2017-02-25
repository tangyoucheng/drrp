/*
 * Copyright(c) 2011 
 */
package cn.com.prescription.framework.common.component.dialog;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.com.prescription.framework.action.AbstractAction;
import cn.com.prescription.framework.message.MessageUtils;
import cn.com.prescription.framework.util.CheckUtils;

/**
 * ダイアログ表示action
 * 
 * @author bpchikazawa
 */
public class DialogAction extends AbstractAction {

    /** 串行版本号 */
    private static final long serialVersionUID = 1L;

    /** form */
    private DialogForm form = new DialogForm();

    /** コールバックファンクション */
    public static final String CALLBACK_FUNCTION_SESSION_KEY = "CALLBACK_FUNCTION_SESSION_KEY";

    /** 「OK」「はい」action */
    public static final String OK_ACTION_SESSION_KEY = "OK_ACTION";

    /** 「いいえ」action */
    public static final String CANCEL_ACTION_SESSION_KEY = "CANCEL_ACTION";

    /**
     * @return form
     */
    public final DialogForm getForm() {
        return form;
    }

    /**
     * @param _form form
     */
    public final void setForm(DialogForm _form) {
        form = _form;
    }

    /**
     * ダイアログ表示
     */
    @Override
    public String doInit() throws Exception {

        // メッセージデコード
        form.setMessage(htmlDecode(form.getMessage()));

        // 確認メッセージの場合は、メッセージIDからメッセージを取得
        if (form.getIsConfirm() || form.getIsInfoId()) {

            // メッセージを分割
            String[] messageIDs_ = form.getMessage().split(",");
            String[] messageArgs_ = null;

            // パラメータがある場合
            if (messageIDs_.length > 1) {
                // パラメータ配列生成
                messageArgs_ = new String[messageIDs_.length - 1];
                // パラメータをコピー
                System.arraycopy(messageIDs_, 1, messageArgs_, 0, messageIDs_.length - 1);
                // パラメータ文字列を取得
                for (int i = 0; i < messageArgs_.length; ++i) {
                    String simpleMessage = MessageUtils.getSimpleMessage(messageArgs_[i].trim());
                    if (CheckUtils.isEmpty(simpleMessage)) {
                        simpleMessage = messageArgs_[i].trim();
                    }
                    messageArgs_[i] = simpleMessage;
                }
            }

            // メッセージを取得
            form.setMessage(MessageUtils.getMessage(messageIDs_[0].trim(), messageArgs_));

        }
        
        Pattern PATTERN_FILE_NAME = Pattern.compile("(\\[[A-Z0-9]{6}\\])(.*)");
        Matcher matcher_ = PATTERN_FILE_NAME.matcher(form.getMessage());
        if (matcher_.matches()) {
            form.setMessage(htmlDecode(matcher_.group(1) + "<br>" + matcher_.group(2)));
        }

        // 常に正常終了
        return SUCCESS;

    }

    /**
     * HTMLデコード.
     * 
     * @param _message デコード対象メッセージ
     * @return デコード後のメッセージ
     */
    private String htmlDecode(String _message) {
        _message = _message.replaceAll("\\&gt\\;", ">");
        _message = _message.replaceAll("\\&lt\\;", "<");
        _message = _message.replaceAll("\\&amp\\;", "&");
        _message = _message.replaceAll("\\&quot\\;", "\"");
        return _message;
    }

}
