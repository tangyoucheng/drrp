/*
 * Copyright(c) 2011 
 */
package cn.com.prescription.framework.biz;

import java.io.Serializable;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import cn.com.prescription.framework.action.AbstractAction;
import cn.com.prescription.framework.exception.SystemException;
import cn.com.prescription.framework.util.CheckUtils;
import cn.com.prescription.framework.util.StringUtils;

import org.apache.struts2.ServletActionContext;

/**
 * actionURL情報
 * 
 * @author bpchikazawa
 */
public class ActionInfo implements Serializable {

    /** 串行版本号 */
    private static final long serialVersionUID = -8598666599448946011L;

    /** ダミーaction */
    public static final ActionInfo NON_ACTION = new ActionInfo(AbstractAction.class);

    /** action */
    private Class<? extends AbstractAction> action = null;

    /** メソッド */
    private String method = null;

    /** パラメータ */
    private Map<String, String> parameters = null;

    /**
     * action未指定のコンストラクタ.
     * <p>
     * インスタンス生成後、プロパティを設定して下さい。
     * </p>
     */
    public ActionInfo() {
        this(null, "");
    }

    /**
     * actionのみ指定するコンストラクタ.
     * <p>
     * "XXXAction" 形式のURLを生成します。
     * </p>
     * 
     * @param _action actionクラス
     */
    public ActionInfo(Class<? extends AbstractAction> _action) {
        this(_action, "");
    }

    /**
     * actionとメソッドを指定するコンストラクタ.
     * <p>
     * "XXXAction!doMethod" 形式のURLを生成します。
     * </p>
     * 
     * @param _action actionクラス
     * @param _method actionメソッド（"do"プリフィックスは任意）
     */
    public ActionInfo(Class<? extends AbstractAction> _action, String _method) {
        this(_action, _method, new HashMap<String, String>());
    }

    /**
     * actionとパラメータマップを指定するコンストラクタ.
     * <p>
     * "XXXAction?mapKey=value" 形式のURLを生成します。
     * </p>
     * 
     * @param _action actionクラス
     * @param _parameters actionパラメータマップ
     */
    public ActionInfo(Class<? extends AbstractAction> _action, Map<String, String> _parameters) {
        this(_action, "", _parameters);
    }

    /**
     * action、メソッド、パラメータマップを指定するコンストラクタ
     * <p>
     * "XXXAction!doMethod?mapKey=value" 形式のURLを生成します。
     * </p>
     * 
     * @param _action actionクラス
     * @param _method actionメソッド（"do"プリフィックスは任意）
     * @param _parameters actionパラメータマップ
     */
    public ActionInfo(Class<? extends AbstractAction> _action, String _method, Map<String, String> _parameters) {
        setAction(_action);
        setMethod(_method);
        setParameters(_parameters);
    }

    /**
     * actionと一つのパラメータを指定するコンストラクタ.
     * <p>
     * "XXXAction?_parameterKey=_parameterValue" 形式のURLを生成します。
     * </p>
     * 
     * @param _action actionクラス
     * @param _parameterKey パラメータキー
     * @param _parameterValue パラメータ値
     */
    public ActionInfo(Class<? extends AbstractAction> _action, String _parameterKey, String _parameterValue) {
        this(_action, "", _parameterKey, _parameterValue);
    }

    /**
     * action、メソッド、一つのパラメータを指定するコンストラクタ.
     * <p>
     * "XXXAction!doMethod?_parameterKey=_parameterValue" 形式のURLを生成します。
     * </p>
     * 
     * @param _action actionクラス
     * @param _method actionメソッド
     * @param _parameterKey パラメータキー
     * @param _parameterValue パラメータ値
     */
    public ActionInfo(Class<? extends AbstractAction> _action, String _method, String _parameterKey,
                    String _parameterValue) {
        // コンストラクタ
        this(_action, _method);
        // パラメータ追加
        putParameter(_parameterKey, _parameterValue);
    }

    /**
     * プロパティ情報を元にURLを生成して返します.
     * 
     * @return URL
     * @throws SystemException 予期せぬ错误発生時
     */
    public String getUrl() throws SystemException {
        try {

            StringBuilder url_ = new StringBuilder();

            // クラスパスをドットで分割
            String[] classPath_ = this.getAction().getName().split("\\.");

            // ネームスペースを取得
            String nameSpace_ = classPath_[classPath_.length - 3];

            // クラス名を取得
            String className_ = classPath_[classPath_.length - 1];

            // クラス名を一旦小文字に変換
            className_ = StringUtils.lowerCase(className_);

            // サフィックスを置換
            className_ = className_.replaceAll("action$", "Action");

            // URL生成
            url_.append(ServletActionContext.getRequest().getContextPath());
            url_.append("/");
            url_.append(nameSpace_);
            url_.append("/");
            url_.append(className_);

            // メソッド名
            if (!CheckUtils.isEmpty(getMethod())) {
                url_.append("!");
                if (!getMethod().matches("do[A-Z].*")) {
                    url_.append("do");
                }
                url_.append(getMethod());
            }

            // action拡張子
            url_.append(".do");

            // パラメータ
            if (!getParameters().isEmpty()) {
                url_.append("?");
                int count = 0;
                for (Map.Entry<String, String> entrySet_ : getParameters().entrySet()) {
                    if (count > 0) {
                        url_.append("&");
                    }
                    url_.append(entrySet_.getKey());
                    url_.append("=");
                    url_.append(URLEncoder.encode(entrySet_.getValue(), ServletActionContext.getRequest()
                        .getCharacterEncoding()));
                    count++;
                }
            }

            // URLを返却
            return url_.toString();

        } catch (Exception e) {
            throw new SystemException(e.getMessage(), e);
        }
    }

    /**
     * @return action
     */
    public final Class<? extends AbstractAction> getAction() {
        return action;
    }

    /**
     * @param _action セットする action
     */
    public final void setAction(Class<? extends AbstractAction> _action) {
        action = _action;
    }

    /**
     * @return method
     */
    public final String getMethod() {
        return method;
    }

    /**
     * @param _method セットする method
     */
    public final void setMethod(String _method) {
        method = _method;
    }

    /**
     * @return parameters
     */
    public final Map<String, String> getParameters() {
        return parameters;
    }

    /**
     * @param _parameters セットする parameters
     */
    public final void setParameters(Map<String, String> _parameters) {
        parameters = _parameters == null ? new HashMap<String, String>() : _parameters;
    }

    /**
     * パラメータを追加します.
     * 
     * @param _parameterKey パラメータキー
     * @param _parameterValue パラメータ値
     */
    public final void putParameter(String _parameterKey, String _parameterValue) {
        getParameters().put(_parameterKey, _parameterValue);
    }

}
