/*
 * Copyright(c) 2011 
 */
package cn.com.prescription.framework.data.condition;

import java.io.Serializable;
import java.util.LinkedHashMap;

import cn.com.prescription.framework.StandardConstantsIF;
import cn.com.prescription.framework.util.NumberUtils;

/**
 * 標準の抽象コンディション.
 * 
 * @author nttdc
 */
public abstract class BaseCondition implements Serializable {

    /** 串行版本号 */
    private static final long serialVersionUID = 1L;

    /**
     * 学校スキーマ
     */
    private String gakkouSchema = null;

    /**
     * offset
     */
    private int offset;

    /**
     * limit
     */
    private int limit = -1;

    /**
     * ページング実行時の並び替え対象項目。
     * <p>
     * ページング実行時に指定された並び替え対象項目および昇順/降順。
     * </p>
     * 
     * @since 1.0
     */
    private LinkedHashMap<String, String> pageSortFields = new LinkedHashMap<String, String>();

    /**
     * Condition を構築します。
     */
    public BaseCondition() {
        super();

        // デフォルト設定
        setLimit(NumberUtils.toInt(StandardConstantsIF.KYOTU_PAGE_SIZE));
    }

    /**
     * 学校スキーマ的取得。
     * 
     * @return 学校スキーマ
     */
    public String getGakkouSchema() {
        return gakkouSchema;
    }

    /**
     * 学校スキーマ的设定。
     * 
     * @param _gakkouSchema 学校スキーマ
     */
    public void setGakkouSchema(String _gakkouSchema) {
        this.gakkouSchema = _gakkouSchema;
    }

    /**
     * 取得順序のSQL文を生成します。
     * 
     * @return ORDER BY句に続くsql文
     * @since 1.0
     */
    public String getSortClause() {
        StringBuilder sb = new StringBuilder();

        for (String key : pageSortFields.keySet()) {
            sb.append(key);
            sb.append(" ");
            sb.append(pageSortFields.get(key));
            sb.append(", ");
        }

        if (sb.length() > 0) {
            sb.delete((sb.length() - 2), (sb.length() - 1));
        }

        return sb.toString();
    }

    /**
     * 指定したソート順の並び替え対象項目を追加する。
     * <p>
     * ページング実行時に指定したソート順で並び替え対象項目を追加する。追加した順番でORDER BY句が生成される。<br>
     * </p>
     * 
     * @param _pageSortField 並び替え対象項目名
     * @param _pageSortOrder ソート順<br>
     *            <ul>
     *            <li>ASC</li>
     *            <li>DESC</li>
     *            </ul>
     */
    public void addPageSortField(String _pageSortField, String _pageSortOrder) {
        this.pageSortFields.put(_pageSortField, _pageSortOrder);
    }

    /**
     * 指定したソート順の並び替え対象項目的设定。
     * <p>
     * ページング実行時に指定したソート順で並び替え対象項目的设定。既存の並び替え対象項目はクリアされる。<br>
     * </p>
     * 
     * @param _pageSortField 並び替え対象項目名
     * @param _pageSortOrder ソート順<br>
     *            <ul>
     *            <li>ASC</li>
     *            <li>DESC</li>
     *            </ul>
     */
    public void setPageSortField(String _pageSortField, String _pageSortOrder) {
        this.pageSortFields.clear();
        this.pageSortFields.put(_pageSortField, _pageSortOrder);
    }

    /**
     * 並び替え対象項目リスト 的设定。
     * <p>
     * 並び替え対象項目リスト を {@link LinkedHashMap} オブジェクトで設定する。既存の並び替え対象項目はクリアされる。<br>
     * <ul>
     * <li>key: 並び替え対象項目名</li>
     * <li>value: ソート順 ( <code>ASC</code> / <code>DESC</code> )</li>
     * </ul>
     * </P>
     * 
     * @param _pageSortFields 並び替え対象項目リスト
     */
    public void setPageSortFields(LinkedHashMap<String, String> _pageSortFields) {
        pageSortFields = _pageSortFields;
    }

    /**
     * offsetを取得します。
     * 
     * @return offset
     */
    public int getOffset() {
        return offset;
    }

    /**
     * offsetを設定します。
     * 
     * @param offset offset
     */
    public void setOffset(int offset) {
        this.offset = offset;
    }

    /**
     * limitを取得します。
     * 
     * @return limit
     */
    public int getLimit() {
        return limit;
    }

    /**
     * limitを設定します。
     * 
     * @param limit limit
     */
    public void setLimit(int limit) {
        this.limit = limit;
    }

    /**
     * ページサイズを取得します。
     * 
     * @return ページサイズ
     */
    public int getPageSize() {
        return getLimit();
    }

    /**
     * ページサイズを設定します。
     * 
     * @param _pageSize ページサイズ
     */
    public void setPageSize(int _pageSize) {
        setLimit(_pageSize);
    }

    /**
     * 並び替え対象項目リスト 的取得。
     * 
     * @return 並び替え対象項目リスト
     */
    public LinkedHashMap<String, String> getPageSortFields() {
        return pageSortFields;
    }

    /**
     * 先頭の並び換え対象項目を取得します。
     * 
     * @return 先頭の並び換え対象項目
     */
    public String getPageSortField() {
        for (String key : pageSortFields.keySet()) {
            return key;
        }
        return "";
    }

    /**
     * 先頭の並び換え対象項目のソート順 を取得します。
     * 
     * @return 先頭の並び換え対象項目のソート順
     */
    public String getPageSortOrder() {
        for (String key : pageSortFields.keySet()) {
            return pageSortFields.get(key);
        }
        return "";
    }

    /**
     * 取得開始行番号的设定。
     * 
     * @param _pageStartRowNo 取得開始行番号
     */
    public void setPageStartRowNo(int _pageStartRowNo) {
        setOffset(_pageStartRowNo - 1);
    }

    /**
     * 取得開始行番号的取得。
     * 
     * @return レコード照会件数
     */
    public int getPageStartRowNo() {
        return getOffset() + 1;
    }
}
