/*
 * Copyright(c) 2011 
 */
package cn.com.prescription.framework.view.taglib;

import java.io.IOException;
import java.io.Writer;

import cn.com.prescription.framework.common.session.UserSessionUtils;
import cn.com.prescription.framework.util.LogUtils;
import cn.com.prescription.framework.view.HTMLEncoder;

import org.apache.struts2.components.Component;

import com.opensymphony.xwork2.util.ValueStack;

/**
 * パンくずリストタグコンポーネント.
 * 
 * @author bpchikazawa
 */
public class MenuNavigation extends Component {

    /** 画面名 */
    private String gamenName = ""; // TODO 今のところ使わない事に。

    /**
     * コンストラクタ
     * 
     * @param _stack スタック
     */
    public MenuNavigation(ValueStack _stack) {
        super(_stack);
    }

    /**
     * @param _gamenName 画面名
     */
    public final void setGamenName(String _gamenName) {
        gamenName = _gamenName;
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

            // ぱんくず用バッファ
            StringBuilder bread_ = new StringBuilder();

            // ぱんくず生成
            for (int i = 0; i < UserSessionUtils.getMenuTree().size(); ++i) {
                if (i > 0) {
                    bread_.append(" > ");
                }
                bread_.append(UserSessionUtils.getMenuTree().get(i).getMenuName());
            }

            // 文字列バッファ
            StringBuilder sb_ = new StringBuilder();

            // 菜单
            sb_.append("<ol class=\"topic_path\">");
            sb_.append("<li>");
            sb_.append(HTMLEncoder.encodeCaption(bread_.toString()));
            sb_.append("</li>");
            sb_.append("</ol>");

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

    // 2013/01/24 未使用メッソドをコメントする
    // /**
    // * テキストプロバイダ検索.<s:i18n>
    // *
    // * @return テキストプロバイダ
    // */
    // private TextProvider findProvider() {
    //
    // // スタックを検索
    // for (Object element_ : getStack().getRoot()) {
    // if (element_ instanceof TextProvider) {
    // return (TextProvider) element_;
    // }
    // }
    // return null;
    //
    // }

}
