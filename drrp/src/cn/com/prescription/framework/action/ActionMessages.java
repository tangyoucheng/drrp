/*
 * Copyright(c) 2011 
 */
package cn.com.prescription.framework.action;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.LinkedList;

import cn.com.prescription.framework.util.CheckUtils;

/**
 * サービスメッセージ。
 * <p>
 * </p>
 * 
 * @author t.d.m
 */
public class ActionMessages implements Serializable {

    /**
     * 串行版本号
     */
    private static final long serialVersionUID = -2136063650144551564L;

    /**
     * 画面のコントロール部品に対する名称を指定しないメッセージ。
     * <p>
     * 画面のコントロール部品に対する名称を指定しないメッセージが追加された場合はマップキーにこれを指定する。
     * </p>
     */
    public static final String NO_NAME_MESSAGE = "none";

    /**
     * メッセージマップ。
     * 
     * @since 1.0
     */
    private LinkedHashMap<String, LinkedList<String>> actionMessages;

    /**
     * 追加されたメッセージ件数。
     */
    private int count = 0;

    /**
     * ActionMessages を構築します。
     * <p>
     * </p>
     */
    public ActionMessages() {
        super();

        // 名称指定なしのメッセージリストを追加する。メッセージ件数はカウントアップしない。
        LinkedHashMap<String, LinkedList<String>> map = new LinkedHashMap<String, LinkedList<String>>();
        map.put(NO_NAME_MESSAGE, new LinkedList<String>());

        this.actionMessages = map;
    }

    /**
     * 指定された名称とメッセージでメッセージリストを追加する。
     * 
     * @param _name 画面のコントロール部品に対応する名称
     * @param _message メッセージ
     */
    public void add(String _name, String _message) {
        if (_message == null) {
            return;
        }

        String name = _name;
        if (CheckUtils.isEmpty(name)) {
            name = NO_NAME_MESSAGE;
        }

        LinkedList<String> list = null;

        if (actionMessages.containsKey(name)) {
            list = actionMessages.get(name);
        } else {
            list = new LinkedList<String>();
            actionMessages.put(name, list);
        }

        list.add(_message);
        count++;
    }

    /**
     * 指定された名称でメッセージリストを追加する。
     * 
     * @param _name 画面のコントロール部品に対応する名称
     * @param _messageList メッセージリスト
     */
    private void add(String _name, LinkedList<String> _messageList) {
        if (_messageList == null || _messageList.isEmpty()) {
            return;
        }

        String name = _name;
        if (CheckUtils.isEmpty(name)) {
            name = NO_NAME_MESSAGE;
        }

        LinkedList<String> list = null;

        if (actionMessages.containsKey(name)) {
            list = actionMessages.get(name);
        } else {
            list = new LinkedList<String>();
            actionMessages.put(name, list);
        }

        list.addAll(_messageList);
        count = count + _messageList.size();
    }

    /**
     * 指定されたメッセージでメッセージリストを追加する。
     * <p>
     * 名称指定なしのメッセージリストに追加する。
     * </p>
     * 
     * @param _message メッセージ
     */
    public void add(String _message) {
        if (_message == null) {
            return;
        }
        add(NO_NAME_MESSAGE, _message);
    }

    /**
     * 指定されたメッセージを追加する。
     * <p>
     * </p>
     * 
     * @param _messages メッセージ
     */
    public void add(ActionMessages _messages) {
        LinkedHashMap<String, LinkedList<String>> messageList = _messages.getMessages();

        for (String name : messageList.keySet()) {
            this.add(name, messageList.get(name));
        }
    }

    /**
     * メッセージリスト的取得。
     * 
     * @return メッセージリスト
     */
    public LinkedHashMap<String, LinkedList<String>> getMessages() {
        return actionMessages;
    }

    /**
     * メッセージ存在判定<br>
     * 
     * @param _name キー
     * @return boolean 判定結果
     */
    public boolean containsName(String _name) {
        return actionMessages.containsKey(_name);
    }

    /**
     * 追加されたメッセージ件数的取得。
     * 
     * @return count メッセージ件数
     */
    public int getCount() {
        return count;
    }

    /**
     * メッセージ内容表す文字列を生成する。
     * 
     * @return メッセージ内容
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();

        sb.append("actionMessages:\n");
        sb.append("\tCount = ");
        sb.append(this.count);
        sb.append("\n");

        for (String key : actionMessages.keySet()) {
            LinkedList<String> messages = actionMessages.get(key);
            sb.append("\tField: ");
            sb.append(key);
            sb.append("\n");

            for (String message : messages) {
                sb.append("\t\t");
                sb.append(message);
                sb.append("\n");
            }
        }

        return sb.toString();
    }

}