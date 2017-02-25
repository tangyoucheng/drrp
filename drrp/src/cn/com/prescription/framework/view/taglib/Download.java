/*
 * Copyright(c) 2011 
 */
package cn.com.prescription.framework.view.taglib;

import java.io.IOException;
import java.io.Writer;

import cn.com.prescription.framework.util.LogUtils;

import org.apache.struts2.components.Component;

import com.opensymphony.xwork2.util.ValueStack;

/**
 * ダウンロード開始タグコンポーネント.
 * 
 * @author bpchikazawa
 */
public class Download extends Component {

    /**
     * ダウンロード開始
     * 
     * @param _stack スタック
     */
    public Download(ValueStack _stack) {
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
            boolean isDownload_ = Boolean.valueOf(getStack().findString("isDownload"));

            // メッセージ無し
            if (!isDownload_) {
                return true;
            }

            // タグ生成バッファ
            StringBuilder sb_ = new StringBuilder();

            // form
            sb_.append("<iframe id=\"downloadFrame\"");
            sb_.append(" frameborder=\"0\"");
            sb_.append(" width=\"0\"");
            sb_.append(" height=\"0\">");
            sb_.append("</iframe>");

            // 起動スクリプト
            sb_.append("<script");
            sb_.append(" type=\"text/javascript\">");
            sb_.append("$(document).ready(function(){");
            sb_.append("connectDownload();");
            sb_.append("try{window.top.releaseVar();}catch(e){}");
            sb_.append("}");
            sb_.append(")");
            sb_.append("</script>");

            // タグ出力
            _writer.write(sb_.toString());

            // 終了
            return true;

        } catch (IOException e) {
            // 予期せぬ错误
            LogUtils.error(e.getMessage(), e);
            // 错误終了
            return false;
        }
    }

}
