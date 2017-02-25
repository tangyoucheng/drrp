package cn.com.prescription.framework.view.taglib;

import java.io.Writer;
import java.util.List;

import cn.com.prescription.framework.CodeValueRecord;
import cn.com.prescription.framework.util.CheckUtils;
import cn.com.prescription.framework.util.LogUtils;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts2.components.Component;

import com.opensymphony.xwork2.util.ValueStack;

/**
 * レジュルボックスリストタグ
 * 
 * @date 2012/10/12
 * @author kaki
 */
public class RadioList extends Component {

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
    /** カラム・カウント */
    private String columnCount = "";
    /** cssErrorClass */
    private String cssErrorClass = "";

    /**
     * @param _stack スタック
     */
    public RadioList(ValueStack _stack) {
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
            @SuppressWarnings("unchecked")
            List<CodeValueRecord> dataSource_ = (List<CodeValueRecord>) findValue(this.list);

            // タグ生成バッファ
            StringBuilder sb_ = new StringBuilder("<table class='boxList'>");

            // 値の設定
            String[] actualValue = null;
            try {
                if (CheckUtils.isEmpty(key)) {
                    actualValue = ((String) getStack().findValue(this.name, String.class, throwExceptionOnELFailure))
                        .split(",");
                } else {
                    actualValue = ((String) getStack().findValue(key, String.class, throwExceptionOnELFailure))
                        .split(",");
                }
            } catch (Exception e) {
                actualValue = new String[] { "" };
            }
            // 错误リストの取得
            String fieldErrors = (String) getStack().findValue("fieldErrors", String.class, throwExceptionOnELFailure);
            // 列の設定
            int lineCount = 4;
            try {
                if (CheckUtils.isNumber(this.columnCount)) {
                    lineCount = Integer.valueOf(this.columnCount);
                } else {
                    lineCount = (Integer) findValue(this.columnCount);
                }
            } catch (Exception e) {

            }
            if (lineCount <= 0) {
                lineCount = 4;
            }
            int i = 0;
            String CheckSt = "";
            String ErrorClass = "";
            if ((fieldErrors != null) && (fieldErrors.contains(this.name))) {
                if (CheckUtils.isEmpty(cssErrorClass)) {
                    ErrorClass = "  class=\"error\"";
                } else {
                    ErrorClass = "  class=\"" + cssErrorClass + "\"";
                }
            }
            // オプションタグ生成
            for (CodeValueRecord record_ : dataSource_) {
                if (i == 0) {
                    sb_.append("<tr>");
                }
                if (i != 0 && i % lineCount == 0) {
                    sb_.append("</tr><tr>");
                }
                CheckSt = "";
                for (String actualValue_ : actualValue) {
                    if (actualValue_.trim().equals(BeanUtils.getProperty(record_, this.listKey))) {
                        CheckSt = " checked = \"true\" ";
                    }
                }

                sb_.append("<td><input type=\"radio\" name=\"");
                sb_.append(this.name);
                sb_.append("\" value=\"");
                sb_.append(BeanUtils.getProperty(record_, this.listKey));
                sb_.append("\" id=\"");
                sb_.append(this.name);
                sb_.append("\"");
                sb_.append(CheckSt);
                sb_.append(ErrorClass);
                sb_.append(" /><label for=\"");
                sb_.append(this.name);
                sb_.append((i + 1));
                sb_.append("\" class=\"radioboxLabel\">");
                sb_.append(BeanUtils.getProperty(record_, this.listValue));
                sb_.append("</label></td>");

                i++;
            }
            sb_.append("</tr></table>");
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
     * 名称的设定。
     * 
     * @param name 名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 初期選択値フィールド的设定。
     * 
     * @param key 初期選択値フィールド
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * データソース的设定。
     * 
     * @param list データソース
     */
    public void setList(String list) {
        this.list = list;
    }

    /**
     * リストキー的设定。
     * 
     * @param listKey リストキー
     */
    public void setListKey(String listKey) {
        this.listKey = listKey;
    }

    /**
     * リスト値的设定。
     * 
     * @param listValue リスト値
     */
    public void setListValue(String listValue) {
        this.listValue = listValue;
    }

    /**
     * カラム・カウント的设定。
     * 
     * @param columnCount カラム・カウント
     */
    public void setColumnCount(String columnCount) {
        this.columnCount = columnCount;
    }

    /**
     * cssErrorClass的设定。
     * 
     * @param cssErrorClass cssErrorClass
     */
    public void setCssErrorClass(String cssErrorClass) {
        this.cssErrorClass = cssErrorClass;
    }

}
