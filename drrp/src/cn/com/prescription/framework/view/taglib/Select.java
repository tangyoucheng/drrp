/*
 * Copyright(c) 2011 
 */
package cn.com.prescription.framework.view.taglib;

import java.io.Writer;
import java.util.List;

import cn.com.prescription.framework.util.CheckUtils;
import cn.com.prescription.framework.util.LogUtils;
import cn.com.prescription.framework.util.StringUtils;
import cn.com.prescription.framework.view.HTMLEncoder;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts2.components.Component;

import com.opensymphony.xwork2.util.ValueStack;

/**
 * スタイル指定可能な <select> タグ.
 * 
 * @author bpchikazawa
 */
public class Select extends Component {

    /** 名称 */
    private String name = "";
    /** 初期選択値フィールド */
    private String key = "";
    /** データソース */
    private String list = "";
    /** リストキー */
    private String listKey = "";
    /** リスト値 */
    private String listValue = "";
    /** OnChangeスクリプト */
    private String onChange = "";

    /**
     * @param _stack スタック
     */
    public Select(ValueStack _stack) {
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

            // データソースを取得
            List<SelectDtoIF> dataSource_ = (List<SelectDtoIF>) findValue(this.list);

            // 選択値を取得
            String selected_ = StringUtils.defaultString(findString(this.key));

            // タグ生成バッファ
            StringBuilder sb_ = new StringBuilder();

            // <SELECT>開始
            sb_.append("<select");
            sb_.append(attr(" id", this.name));
            sb_.append(attr(" name", this.name));
            sb_.append(attr(" onchange", this.onChange));
            sb_.append(">");

            // オプションタグ生成
            for (SelectDtoIF dto_ : dataSource_) {
                // VALUEを取得
                String value_ = BeanUtils.getProperty(dto_, this.listKey);
                // <option>生成
                sb_.append("<option");
                sb_.append(attrEnc(" value", value_));
                sb_.append(attr(" class", dto_.getCssClass()));
                // 初期選択
                if (selected_.equals(value_)) {
                    sb_.append(" selected");
                }
                sb_.append(">");
                sb_.append(HTMLEncoder.encodeCaption(BeanUtils.getProperty(dto_, this.listValue)));
                sb_.append("</option>");
            }

            // <SELECT>終了
            sb_.append("</select>");

            // タグ出力
            _writer.write(sb_.toString());

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
     * @param _attr 属性名
     * @param _val 属性値
     * @return 属性文字列 _attr="_val"
     */
    private String attr(final String _attr, String _val) {
        return CheckUtils.isEmpty(_val) ? "" : _attr.concat("=").concat("\"").concat(StringUtils.defaultString(_val))
            .concat("\"");
    }

    /**
     * @param _attr 属性名
     * @param _val 属性値
     * @return 属性値がエンコードされた属性文字列 _attr="_val"
     */
    private String attrEnc(final String _attr, String _val) {
        return attr(_attr, HTMLEncoder.encodeValue(_val));
    }

    /**
     * @param _name 名称
     */
    public final void setName(String _name) {
        name = _name;
    }

    /**
     * @param _key 初期選択値フィールド
     */
    public final void setKey(String _key) {
        key = _key;
    }

    /**
     * @param _list データソース
     */
    public final void setList(String _list) {
        list = _list;
    }

    /**
     * @param _listKey リストキー
     */
    public final void setListKey(String _listKey) {
        listKey = _listKey;
    }

    /**
     * @param _listValue リスト値
     */
    public final void setListValue(String _listValue) {
        listValue = _listValue;
    }

    /**
     * @param _onChange OnChangeスクリプト
     */
    public final void setOnChange(String _onChange) {
        onChange = _onChange;
    }

}
