/*
 * Copyright(c) 2011 
 */
package cn.com.prescription.framework.view;

/**
 * HTML変換クラス
 * 
 * @author t.d.m
 */
public class HTMLEncoder {

    /**
     * HTMLEncoder的构造
     */
    private HTMLEncoder() {
        super();
    }

    /**
     * @param _caption エンコード対象文字列
     * @return エンコード文字列
     */
    public static String encodeCaption(String _caption) {

        // TODO: OpenIM を呼んでは駄目か・・？
        return org.intra_mart.framework.base.web.util.HTMLEncoder.encodeCaption(_caption);

        // StringBuffer srcBuffer = null;
        // String src = null;
        // StringBuffer resultBuffer = null;
        // String result = null;
        // char token = '\0';
        // int length = 0;
        // int beginIndex = 0;
        // int endIndex = 0;
        // if (_caption != null) {
        // srcBuffer = new StringBuffer();
        // beginIndex = 0;
        // endIndex = 0;
        // for (length = _caption.length(); beginIndex < length
        // && (endIndex = _caption.indexOf("\r\n", beginIndex)) != -1; beginIndex = endIndex + 2) {
        // if (beginIndex < endIndex)
        // srcBuffer.append(_caption.substring(beginIndex, endIndex));
        // srcBuffer.append("\n");
        // }
        //
        // if (beginIndex < length)
        // srcBuffer.append(_caption.substring(beginIndex));
        // src = new String(srcBuffer);
        // srcBuffer = new StringBuffer();
        // beginIndex = 0;
        // endIndex = 0;
        // for (length = src.length(); beginIndex < length && (endIndex = src.indexOf("\r", beginIndex)) != -1;
        // beginIndex = endIndex + 1) {
        // if (beginIndex < endIndex)
        // srcBuffer.append(src.substring(beginIndex, endIndex));
        // srcBuffer.append("\n");
        // }
        //
        // if (beginIndex < length)
        // srcBuffer.append(src.substring(beginIndex));
        // src = new String(srcBuffer);
        // resultBuffer = new StringBuffer();
        // length = src.length();
        // for (int i = 0; i < length; i++) {
        // token = src.charAt(i);
        // if (token == '<') {
        // resultBuffer.append("&lt;");
        // continue;
        // }
        // if (token == '>') {
        // resultBuffer.append("&gt;");
        // continue;
        // }
        // if (token == '&') {
        // resultBuffer.append("&amp;");
        // continue;
        // }
        // if (token == '"') {
        // resultBuffer.append("&quot;");
        // continue;
        // }
        // if (token == ' ') {
        // resultBuffer.append("&nbsp;");
        // continue;
        // }
        // if (token == '\n')
        // resultBuffer.append("<BR>");
        // else
        // resultBuffer.append(token);
        // }
        //
        // result = new String(resultBuffer);
        // }
        // return result;
    }

    /**
     * @param _value エンコード対象文字列
     * @return エンコード文字列
     */
    public static String encodeValue(String _value) {

        // TODO: OpenIM を呼んでは駄目か・・？
        return org.intra_mart.framework.base.web.util.HTMLEncoder.encodeValue(_value);

        // StringBuffer resultBuffer = null;
        // String result = null;
        // int length = 0;
        // char token = '\0';
        // if (value != null) {
        // resultBuffer = new StringBuffer();
        // length = value.length();
        // for (int i = 0; i < length; i++) {
        // token = value.charAt(i);
        // if (token == '<') {
        // resultBuffer.append("&lt;");
        // continue;
        // }
        // if (token == '>') {
        // resultBuffer.append("&gt;");
        // continue;
        // }
        // if (token == '&') {
        // resultBuffer.append("&amp;");
        // continue;
        // }
        // if (token == '"')
        // resultBuffer.append("&quot;");
        // else
        // resultBuffer.append(token);
        // }
        //
        // result = new String(resultBuffer);
        // }
        // return result;
    }

}