/*
 * Copyright(c) 2011 
 */
package cn.com.prescription.framework.action.validator;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.validator.Arg;
import org.apache.commons.validator.Field;
import org.apache.commons.validator.Form;
import org.apache.commons.validator.Validator;
import org.apache.commons.validator.ValidatorException;
import org.apache.commons.validator.ValidatorResources;
import org.apache.commons.validator.ValidatorResult;
import org.apache.commons.validator.ValidatorResults;
import org.apache.commons.validator.util.ValidatorUtils;
import org.xml.sax.SAXException;

import cn.com.prescription.framework.action.ActionMessages;
import cn.com.prescription.framework.action.form.AbstractDto;
import cn.com.prescription.framework.action.form.StandartForm;
import cn.com.prescription.framework.util.CheckUtils;
import cn.com.prescription.framework.util.NumberUtils;
import cn.com.prescription.framework.util.StringUtils;

/**
 * バリデータマネージャ。
 * <p>
 * Apatch commons validator を利用したバリデーション機能を提供する。
 * </p>
 * 
 * @author t.d.m
 */
public final class ValidatorManager {

    /**
     * バリデータマネージャ
     */
    private static ValidatorManager manager;

    /**
     * バリデートモード
     * <p>
     * 単一バリデーションモード true:単一
     * </p>
     */
    private String validateMode = Boolean.FALSE.toString();

    /**
     * 错误メッセージオブジェクト（内部変数）
     */
    private ActionMessages testmessages = new ActionMessages();

    /***
     * Validator生成
     */
    private ValidatorResources resources = null;

    /**
     * バリデーションマネージャ的构造。
     * 
     * @throws ValidatorManagerException バリデータマネージャの生成に失敗した場合
     */
    private ValidatorManager() throws ValidatorManagerException {
    }

    /**
     * バリデーションマネージャ的构造。
     * 
     * @return バリデータマネージャ
     * @throws ValidatorManagerException バリデータマネージャの生成に失敗した場合
     */
    public static ValidatorManager getValidatorManager() throws ValidatorManagerException {

        manager = new ValidatorManager();

        return manager;
    }

    /**
     * バリデーションを実行する。
     * 
     * @param _resources バリデータ定義リソース
     * @param _bean Beanオブジェクト
     * @param _formName バリデーション実行対象のform名
     * @return メッセージ群
     * @throws ValidatorManagerException バリデーション処理中に系统错误が発生した場合スローされる
     */
    @SuppressWarnings("unchecked")
    private boolean validate(ValidatorResources _resources, StandartForm _bean, String _formName)
                    throws ValidatorManagerException {

        boolean result = true;

        // 検査対象フィールド取得
        Form form = _resources.getForm(Locale.getDefault(), _formName);
        if (form == null) {
            return result;
        }
        List<Field> fields = form.getFields();

        Map<String, List<Field>> indexedList = new LinkedHashMap<String, List<Field>>();

        for (Field field : fields) {
            String indexedListProperty = field.getIndexedListProperty();
            if (CheckUtils.isEmpty(indexedListProperty)) {
                result = validateField(_resources, form, field, _bean);

                // 単一バリデーションmodeの場合は終了
                if (result) {
                    return result;
                }

            } else {
                // フィールドが配列またはコレクションの場合

                // 階層を分割（階層の場合は"."にて指定、2階層まで指定可）
                String[] tempProperty = StringUtils.split(indexedListProperty, ".");

                if (tempProperty.length == 1) {
                    // 1階層のみ
                    List<Field> temp = null;
                    if (indexedList.containsKey(indexedListProperty)) {
                        temp = indexedList.get(indexedListProperty);
                    } else {
                        temp = new LinkedList<Field>();
                        indexedList.put(indexedListProperty, temp);
                    }
                    temp.add(field);

                } else if (tempProperty.length == 2) {
                    // 2階層の場合、親階層のリスト取得
                    int length = 0;
                    try {
                        Object indexedPropertyObject = PropertyUtils.getProperty(_bean, tempProperty[0]);
                        if (indexedPropertyObject != null) {
                            // 配列形式のリスト長取得
                            if (indexedPropertyObject instanceof Collection) {
                                length = ((Collection<?>) indexedPropertyObject).size();
                            } else if (indexedPropertyObject.getClass().isArray()) {
                                length = Array.getLength(indexedPropertyObject);
                            }
                        }

                    } catch (IllegalAccessException e) {
                        throw new ValidatorManagerException("indexedListPropertyに指定されたオブジェクトが取得できません。" + "property="
                                        + field.getProperty() + ", indexedListProperty=" + indexedListProperty,
                            e);
                    } catch (InvocationTargetException e) {
                        throw new ValidatorManagerException("indexedListPropertyに指定されたオブジェクトが取得できません。" + "property="
                                        + field.getProperty() + ", indexedListProperty=" + indexedListProperty,
                            e);
                    } catch (NoSuchMethodException e) {
                        throw new ValidatorManagerException("indexedListPropertyに指定されたオブジェクトが取得できません。" + "property="
                                        + field.getProperty() + ", indexedListProperty=" + indexedListProperty,
                            e);
                    }

                    if (length == 0) {
                        // 親階層が配列形式でない場合
                        List<Field> temp = null;
                        if (indexedList.containsKey(indexedListProperty)) {
                            temp = indexedList.get(indexedListProperty);
                        } else {
                            temp = new LinkedList<Field>();
                            indexedList.put(indexedListProperty, temp);
                        }
                        temp.add(field);

                    } else {
                        // 親階層が配列形式の場合
                        for (int j = 0; j < length; j++) {
                            String bufProperty = StringUtils.replace(indexedListProperty, ".", "[" + j + "].");
                            List<Field> temp = null;
                            if (indexedList.containsKey(bufProperty)) {
                                temp = indexedList.get(bufProperty);
                            } else {
                                temp = new LinkedList<Field>();
                                indexedList.put(bufProperty, temp);
                            }
                            temp.add(field);
                        }
                    }

                } else {
                    // 2階層以上はNG
                    throw new ValidatorManagerException("indexedListPropertyは2階層までしか指定できません。property="
                                    + field.getProperty() + ", indexedListProperty=" + indexedListProperty);
                }
            }
        }

        // 配列形式のチェック
        for (String indexedProperty : indexedList.keySet()) {
            // indexedPropertyチェック
            result = validateIndexedProperty(_resources, form, indexedList.get(indexedProperty), _bean,
                indexedProperty);

            // 単一バリデーションmodeの場合は終了
            /*
             * if (result) {
             * return result;
             * }
             */
        }

        // debug-end
        // LogUtils.end(logger,
        // "validate(ValidatorResources, AbstractBaseControllerObject, String)",
        // result);

        return result;
    }

    /**
     * フィールドに対してバリデーションを実行する。
     * <p>
     * フィールドに対してバリデーション定義に従いバリデーションを実行する。<br>
     * バリデーション結果にバリデーション错误が存在する場合は、メッセージ群に错误内容を追加し返却する。
     * </p>
     * 
     * @param _resources バリデータ定義リソース
     * @param _form form定義
     * @param _bean Beanオブジェクト
     * @param _field フィールド定義
     * @return メッセージ群
     * @throws ValidatorManagerException バリデーション実行に失敗した場合スロー
     */
    @SuppressWarnings("unchecked")
    private boolean validateField(ValidatorResources _resources, Form _form, Field _field, StandartForm _bean)
                    throws ValidatorManagerException {

        // debug-start
        // LogUtils.start(
        // logger,
        // "validateField(ValidatorResources, Form, Field, String, AbstractBaseControllerObject)", _resources,
        // _form, _field, _bean);

        boolean result = false;
        Throwable throwable = null;

        Validator validator = new Validator(_resources, _form.getName());

        validator.setParameter(Validator.FORM_PARAM, _form);
        validator.setParameter(Validator.BEAN_PARAM, _bean);
        validator.setParameter(Validator.FIELD_PARAM, _field);
        validator.setFormName(_form.getName());
        validator.setFieldName(_field.getProperty());

        // バリデーション実行
        try {
            ValidatorResults validatorResults = validator.validate();

            validatorResults.getPropertyNames().iterator();
            if (!validatorResults.isEmpty()) {

                ValidatorResult validatorResult = validatorResults.getValidatorResult(_field.getProperty());
                List<String> dependencyList = _field.getDependencyList();

                // 错误情報をメッセージリストへ追加する
                for (String dependency : dependencyList) {
                    if (validatorResult.containsAction(dependency) && !validatorResult.isValid(dependency)) {
                        testmessages.add(_field.getProperty(), convertMessage(_resources, _field, dependency));

                        if (Boolean.valueOf(validateMode)) {
                            // チェック错误かつ、単一バリデーションmodeの場合はtrueを返却して終了
                            result = true;
                            return result;
                        }
                    }
                }
            }

        } catch (ValidatorException e) {
            throwable = e;
            throw new ValidatorManagerException(
                ValidatorMessagesHandler.create().getSimpleMessage("E0000002", _form.getName(), _field.getProperty()),
                e);
        } finally {
            if (throwable == null) {
                // debug-end
                // LogUtils.end(
                // logger,
                // "validateField(ValidatorResources, Form, Field, String, AbstractBaseControllerObject)",
                // result);
            }
        }

        return result;
    }

    /**
     * フィールドに対してバリデーションを実行する。
     * <p>
     * フィールドに対してバリデーション定義に従いバリデーションを実行する。<br>
     * バリデーション結果にバリデーション错误が存在する場合は、メッセージ群に错误内容を追加し返却する。
     * </p>
     * 
     * @param _resources バリデータ定義リソース
     * @param _form form定義
     * @param _bean Beanオブジェクト
     * @param _field フィールド定義
     * @param _rowIndex 行番号
     * @return メッセージ群
     * @throws ValidatorManagerException バリデーション実行に失敗した場合スロー
     */
    @SuppressWarnings("unchecked")
    private boolean validateField(ValidatorResources _resources, Form _form, Field _field, StandartForm _bean,
                    String _rowIndex) throws ValidatorManagerException {

        // debug-start
        // LogUtils.start(
        // logger,
        // "validateField(ValidatorResources, Form, Field, String, AbstractBaseControllerObject)", _resources,
        // _form, _field, _bean);

        boolean result = false;
        Throwable throwable = null;

        Validator validator = new Validator(_resources, _form.getName());

        validator.setParameter(Validator.FORM_PARAM, _form);
        validator.setParameter(Validator.BEAN_PARAM, _bean);
        validator.setParameter(Validator.FIELD_PARAM, _field);
        validator.setFormName(_form.getName());
        validator.setFieldName(_field.getProperty());

        // バリデーション実行
        try {
            ValidatorResults validatorResults = validator.validate();

            validatorResults.getPropertyNames().iterator();
            if (!validatorResults.isEmpty()) {

                ValidatorResult validatorResult = validatorResults.getValidatorResult(_field.getProperty());
                List<String> dependencyList = _field.getDependencyList();

                // 错误情報をメッセージリストへ追加する
                for (String dependency : dependencyList) {
                    if (validatorResult.containsAction(dependency) && !validatorResult.isValid(dependency)) {
                        testmessages.add(_field.getProperty(),
                            convertMessage(_resources, _field, dependency, _rowIndex));

                        if (Boolean.valueOf(validateMode)) {
                            // チェック错误かつ、単一バリデーションmodeの場合はtrueを返却して終了
                            result = true;
                            return result;
                        }
                    }
                }
            }

        } catch (ValidatorException e) {
            throwable = e;
            throw new ValidatorManagerException(
                ValidatorMessagesHandler.create().getSimpleMessage("E0000002", _form.getName(), _field.getProperty()),
                e);
        } finally {
            if (throwable == null) {
                // debug-end
                // LogUtils.end(
                // logger,
                // "validateField(ValidatorResources, Form, Field, String, AbstractBaseControllerObject)",
                // result);
            }
        }

        return result;
    }

    /**
     * indexed フィールドに対してバリデーションを実行する。
     * 
     * @param _resources バリデータ定義リソース
     * @param _form form定義
     * @param _fields フィールド定義
     * @param _bean Beanオブジェクト
     * @param _indexedProperty 配列プロパティ名
     * @return メッセージ群
     * @throws ValidatorManagerException バリデーション実行に失敗した場合スロー
     */
    @SuppressWarnings("unchecked")
    private boolean validateIndexedProperty(ValidatorResources _resources, Form _form, List<Field> _fields,
                    StandartForm _bean, String _indexedProperty) throws ValidatorManagerException {
        // debug-start
        // LogUtils.start(
        // logger,
        // "validateIndexedProperty(ValidatorResources, Form, List<Field>, AbstractBaseControllerObject, String)",
        // _resources, _form, _fields, _bean, _indexedProperty);
        // start time-stamp

        boolean result = true;
        Throwable throwable = null;

        try {
            Object indexedPropertyObject = PropertyUtils.getProperty(_bean, _indexedProperty);

            if (indexedPropertyObject != null) {

                // 配列形式のリスト取得
                Object[] indexedObject = null;

                if (indexedPropertyObject instanceof Collection) {
                    indexedObject = ((Collection<?>) indexedPropertyObject).toArray();

                } else if (indexedPropertyObject.getClass().isArray()) {
                    indexedObject = new Object[Array.getLength(indexedPropertyObject)];
                    for (int i = 0; i < indexedObject.length; i++) {
                        indexedObject[i] = Array.get(indexedObject, i);
                    }

                } else {
                    // 配列形式でない場合错误
                    throw new ValidatorManagerException(
                        ValidatorMessagesHandler.create().getSimpleMessage("E0000006", _indexedProperty));
                }

                // 有効行の判定項目設定
                Map<String, Map<String, Object>> validSettingMap = new LinkedHashMap<String, Map<String, Object>>();

                // 明細行ごとに処理を行う
                for (int i = 0; i < indexedObject.length; i++) {
                    AbstractDto abstractDto = (AbstractDto) indexedObject[i];

                    String indexedPropertyName = new StringBuilder().append(_indexedProperty).append("[").append(i)
                        .append("]").toString();

                    // 明細行の検査対象フィールドに対して処理を行う
                    for (Field field : _fields) {

                        // 判定項目の設定値取得
                        if (!validSettingMap.containsKey(field.getProperty())) {
                            Map<String, Object> temp = new LinkedHashMap<String, Object>();

                            // validProperty取得
                            String[] validProperties = StringUtils.split(field.getVarValue("validProperty"), ",",
                                false);
                            temp.put("validProperty", validProperties);

                            if (validProperties != null) {
                                // モード設定（validPropertyMode）取得
                                boolean validPropertyMode = Boolean.valueOf(field.getVarValue("validPropertyMode"));
                                temp.put("validPropertyMode", Boolean.toString(validPropertyMode));

                                // ZERO設定（validZeroProperty）取得
                                Collection<String> validZeroProperties = new LinkedList<String>();
                                String[] tempValidZeroProperties = StringUtils
                                    .split(field.getVarValue("validZeroProperty"), ",", false);
                                if (tempValidZeroProperties != null) {
                                    CollectionUtils.addAll(validZeroProperties, tempValidZeroProperties);
                                }
                                temp.put("validZeroProperty", validZeroProperties);
                            }

                            validSettingMap.put(field.getProperty(), temp);
                        }

                        // 有効行の判定を行う
                        boolean validflg = true;
                        // 明細行check start row
                        String strStart = field.getVarValue("validPropertyStart");
                        if (CheckUtils.isDigits(strStart)) {
                            if (Integer.valueOf(strStart) > i) {
                                validflg = false;
                                break;
                            }
                        }

                        Map<String, Object> setting = validSettingMap.get(field.getProperty());

                        if (setting.get("validProperty") != null) {
                            String[] validProperties = (String[]) setting.get("validProperty");
                            boolean validPropertyMode = Boolean.valueOf((String) setting.get("validPropertyMode"));
                            Collection<String> validZeroProperties = (Collection<String>) setting
                                .get("validZeroProperty");

                            for (String validProperty : validProperties) {
                                validflg = !validPropertyMode;

                                String propertyName = new StringBuilder().append(indexedPropertyName).append(".")
                                    .append(validProperty).toString();

                                String propertyeValue = ValidatorUtils.getValueAsString(_bean, propertyName);
                                if (!CheckUtils.isEmpty(propertyeValue)
                                                && validZeroProperties.contains(validProperty)) {
                                    // 判定対象プロパティがZEROの場合に空白と見なす場合、値をクリア
                                    if (NumberUtils.toBigDecimal(propertyeValue).compareTo(BigDecimal.ZERO) == 0) {
                                        propertyeValue = null;
                                    }
                                }

                                if ("true".equals(propertyeValue) || "false".equals(propertyeValue)) {
                                    if (validPropertyMode) {
                                        // validPropertyMode=trueの場合、1つでも値ありだったら有効行
                                        if (!Boolean.valueOf(validPropertyMode)) {
                                            validflg = true;
                                            break;
                                        }

                                    } else {
                                        // validPropertyMode=falseの場合、1つでも空だったら無効行
                                        if (Boolean.valueOf(propertyeValue)) {
                                            validflg = false;
                                            break;
                                        }
                                    }
                                } else {
                                    if (validPropertyMode) {
                                        // validPropertyMode=trueの場合、1つでも値ありだったら有効行
                                        if (!CheckUtils.isEmpty(propertyeValue)) {
                                            validflg = true;
                                            break;
                                        }

                                    } else {
                                        // validPropertyMode=falseの場合、1つでも空だったら無効行
                                        if (CheckUtils.isEmpty(propertyeValue)) {
                                            validflg = false;
                                            break;
                                        }
                                    }
                                }
                            }
                        }

                        // 有効行のみバリデート実行
                        if (validflg) {
                            /*
                             * {_indexedProperty}[i].{propertyNme}
                             * {_indexedProperty}[i]
                             */
                            StringBuilder propertyName = new StringBuilder(indexedPropertyName);

                            if (!CheckUtils.isEmpty(field.getProperty())) {
                                propertyName.append(".").append(field.getProperty());
                            }

                            // formに対象フィールドを設定
                            // 退避
                            String bufIndexedListProperty = field.getIndexedListProperty();
                            String bufKey = field.getKey();
                            String bufProperty = field.getProperty();

                            // 設定
                            field.setIndexedListProperty("");
                            field.setKey(propertyName.toString());
                            field.setProperty(propertyName.toString());

                            _form.addField(field);

                            // バリデート実行
                            // result = validateField(_resources, _form, field, _bean, abstractDto.getValidDataIndex());
                            String rowIndex = abstractDto.getValidDataIndex();
                            if (CheckUtils.isEmpty(rowIndex)) {
                                rowIndex = abstractDto.getNo();
                                if ("0".equals(rowIndex)) {
                                    rowIndex = null;
                                }
                            }
                            result = validateField(_resources, _form, field, _bean, rowIndex);

                            // 復帰
                            field.setIndexedListProperty(bufIndexedListProperty);
                            field.setKey(bufKey);
                            field.setProperty(bufProperty);

                            // // 単一バリデーションmodeの場合は終了
                            if (result) {
                                return result;
                            }
                        }
                    }
                }
            }

        } catch (IllegalAccessException e) {
            // 配列形式のオブジェクト取得に失敗
            throwable = e;
            throw new ValidatorManagerException(
                ValidatorMessagesHandler.create().getSimpleMessage("E0000007", _indexedProperty), e);
        } catch (InvocationTargetException e) {
            // 配列形式のオブジェクト取得に失敗
            throwable = e;
            throw new ValidatorManagerException(
                ValidatorMessagesHandler.create().getSimpleMessage("E0000007", _indexedProperty), e);
        } catch (NoSuchMethodException e) {
            // 配列形式のオブジェクト取得に失敗
            throwable = e;
            throw new ValidatorManagerException(
                ValidatorMessagesHandler.create().getSimpleMessage("E0000007", _indexedProperty), e);
        } finally {
            if (throwable == null) {
                // time-stamp
                // LogUtils.process(
                // logger,
                // "validateIndexedProperty(ValidatorResources, Form, List<Field>, AbstractBaseControllerObject,
                // String)",
                // startMillis, System.currentTimeMillis());
                // // debug-end
                // LogUtils.end(
                // logger,
                // "validateIndexedProperty(ValidatorResources, Form, List<Field>, AbstractBaseControllerObject,
                // String)",
                // result);
            }
        }

        return result;
    }

    /**
     * バリデーション错误時のメッセージ内容的取得。
     * <p>
     * フィールド定義に個別メッセージが設定されている場合は個別メッセージから错误メッセージを生成する。 個別メッセージが設定されていない場合はバリデーションルールに定義されたメッセージから生成する。<br>
     * 置き換えパラメータ取得にてarg/resource=trueの場合はメッセージプロパティファイルより取得することができる。
     * </p>
     * 
     * @param _resources バリデータ定義リソース
     * @param _field フィールド定義
     * @param _dependency dependency
     * @return メッセージ
     */
    private String convertMessage(ValidatorResources _resources, Field _field, String _dependency) {
        // メッセージキー取得
        String messageKey = _field.getMsg(_dependency);
        if (CheckUtils.isEmpty(messageKey)) {
            messageKey = _resources.getValidatorAction(_dependency).getMsg();
        }

        // メッセージ置換パラメータ取得
        Arg[] args = _field.getArgs(_dependency);

        String[] params = new String[args.length];
        for (int i = 0; i < args.length; i++) {
            if (args[i] != null) {
                if (args[i].isResource()) {
                    // 置換パラメータにメッセージプロパティファイルを使用する場合
                    // params[i] = ImMessageUtils
                    // .getSimpleMessage(args[i].getKey());
                } else {
                    params[i] = args[i].getKey();
                }
            } else {
                params[i] = "";
            }
        }

        // 行番号取得
        StringBuilder bufDetailNo = new StringBuilder();
        String bufName = _field.getProperty();
        int index = 0;
        while ((index = StringUtils.indexOf(bufName, "]", index)) > -1) {

            String no = StringUtils.substringBetween(bufName, "[", "]");
            if (!CheckUtils.isEmpty(no) && CheckUtils.isNumber(no)) {
                if (bufDetailNo.length() > 0) {
                    bufDetailNo.append("-");
                }
                bufDetailNo.append(NumberUtils.toInt(no) + 1);
            }
            bufName = StringUtils.substring(_field.getProperty(), index++);
        }

        // メッセージ変換
        StringBuilder result = new StringBuilder();
        result.append(ValidatorResourceHandler.create().getSimpleMessage(messageKey, params));

        if (bufDetailNo.length() > 0) {
            // 行番号付与
            result.append(
                ValidatorResourceHandler.create().getSimpleMessage("validator.error.detailNo", bufDetailNo.toString()));
        }

        return result.toString();
    }

    /**
     * バリデーション错误時のメッセージ内容的取得。
     * <p>
     * フィールド定義に個別メッセージが設定されている場合は個別メッセージから错误メッセージを生成する。 個別メッセージが設定されていない場合はバリデーションルールに定義されたメッセージから生成する。<br>
     * 置き換えパラメータ取得にてarg/resource=trueの場合はメッセージプロパティファイルより取得することができる。
     * </p>
     * 
     * @param _resources バリデータ定義リソース
     * @param _field フィールド定義
     * @param _dependency dependency
     * @param _rowIndex 行番号
     * @return メッセージ
     */
    private String convertMessage(ValidatorResources _resources, Field _field, String _dependency, String _rowIndex) {
        // メッセージキー取得
        String messageKey = _field.getMsg(_dependency);
        if (CheckUtils.isEmpty(messageKey)) {
            messageKey = _resources.getValidatorAction(_dependency).getMsg();
        }

        // メッセージ置換パラメータ取得
        Arg[] args = _field.getArgs(_dependency);

        String[] params = new String[args.length];
        for (int i = 0; i < args.length; i++) {
            if (args[i] != null) {
                if (args[i].isResource()) {
                    // 置換パラメータにメッセージプロパティファイルを使用する場合
                    // params[i] = ImMessageUtils
                    // .getSimpleMessage(args[i].getKey());
                } else {
                    params[i] = args[i].getKey();
                }
            } else {
                params[i] = "";
            }
        }

        // 行番号取得
        StringBuilder bufDetailNo = new StringBuilder();
        String bufName = _field.getProperty();
        int index = 0;
        while ((index = StringUtils.indexOf(bufName, "]", index)) > -1) {

            String no = StringUtils.substringBetween(bufName, "[", "]");
            if (!CheckUtils.isEmpty(no) && CheckUtils.isNumber(no)) {
                if (bufDetailNo.length() > 0) {
                    bufDetailNo.append("-");
                }
                bufDetailNo.append(NumberUtils.toInt(no) + 1);
            }
            bufName = StringUtils.substring(_field.getProperty(), index++);
        }

        // メッセージ変換
        StringBuilder result = new StringBuilder();
        result.append(ValidatorResourceHandler.create().getSimpleMessage(messageKey, params));

        if (!CheckUtils.isEmpty(_rowIndex)) {
            bufDetailNo = new StringBuilder();
            bufDetailNo.append(_rowIndex);
        }

        if (bufDetailNo.length() > 0) {
            // 行番号付与
            result.append(
                ValidatorResourceHandler.create().getSimpleMessage("validator.error.detailNo", bufDetailNo.toString()));
        }

        return result.toString();
    }

    /**
     * サービスコントローラからバリデーションを実行する。
     * 
     * @param _path バリデーション設定ファイルパース
     * @param _formName form名
     * @param _bean Beanオブジェクト
     * @return メッセージ群
     * @throws ValidatorManagerException バリデーション実行時に系统例外发生的情况スローされる
     */
    public ActionMessages validate(String _path, String _formName, StandartForm _bean)
                    throws ValidatorManagerException {

        // debug-start
        // LogUtils.start(
        // logger,
        // "validate(String, String, AbstractBaseControllerObject)",
        // _application, _service, _bean);
        // start time-stamp
        // long startMillis = System.currentTimeMillis();

        // バリデートモード的设定
        // validateMode = _bean.getValidateMode();

        List<InputStream> listStream = new ArrayList<InputStream>();

        // validator-rules取得
        listStream.add(ValidatorManager.class.getResourceAsStream("validator-rules.xml"));

        // 設定ファイルパース
        if (CheckUtils.isEmpty(_path)) {
            throw new ValidatorManagerException("validation設定ファイルが取得できませんでした");
        }
        InputStream is = null;
        try {
            is = ValidatorManager.class.getClassLoader().getResourceAsStream(_path.toString());
            if (is != null) {
                listStream.add(is);

                InputStream[] configures = listStream.toArray(new InputStream[listStream.size()]);

                // Validator生成
                ValidatorResources resources = new ValidatorResources(configures);

                if (!CheckUtils.isEmpty(_formName)) {
                    validate(resources, _bean, _formName);
                }
            } else {
                // validation-configがない場合
                // LogUtils.warn(
                // logger,
                // ValidatorMessagesHandler.create().getSimpleMessage(
                // "W0000005", _application, _service));
            }

        } catch (IOException e) {
            e.printStackTrace();
            throw new ValidatorManagerException(e);
        } catch (SAXException e) {
            e.printStackTrace();
            throw new ValidatorManagerException(e);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ValidatorManagerException(e);
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // time-stamp
        // LogUtils.process(
        // logger, "validate(String, String, AbstractBaseControllerObject)",
        // startMillis, System.currentTimeMillis());
        // // debug-end
        // LogUtils.end(
        // logger,
        // "validate(String, String, AbstractBaseControllerObject)",
        // testmessages);

        return testmessages;
    }

    /**
     * サービスコントローラからバリデーションを実行する。
     * 
     * @param _formName form名
     * @param _bean Beanオブジェクト
     * @return メッセージ群
     * @throws ValidatorManagerException バリデーション実行時に系统例外发生的情况スローされる
     */
    public ActionMessages validate(String _formName, StandartForm _bean) throws ValidatorManagerException {

        // debug-start
        // LogUtils.start(
        // logger,
        // "validate(String, String, AbstractBaseControllerObject)",
        // _application, _service, _bean);
        // start time-stamp
        // long startMillis = System.currentTimeMillis();

        // 設定ファイルパース
        if (resources == null) {
            throw new ValidatorManagerException("validation設定ファイルが取得できませんでした");
        }

        if (!CheckUtils.isEmpty(_formName)) {
            validate(resources, _bean, _formName);
        }

        // time-stamp
        // LogUtils.process(
        // logger, "validate(String, String, AbstractBaseControllerObject)",
        // startMillis, System.currentTimeMillis());
        // // debug-end
        // LogUtils.end(
        // logger,
        // "validate(String, String, AbstractBaseControllerObject)",
        // testmessages);

        return testmessages;
    }

    /**
     * Validatorファイル内容を読み込む。
     * 
     * @param _path バリデーション設定ファイルパース
     * @throws ValidatorManagerException バリデーション実行時に系统例外发生的情况スローされる
     */
    public void load(String _path) throws ValidatorManagerException {

        List<InputStream> listStream = new ArrayList<InputStream>();

        // validator-rules取得
        listStream.add(ValidatorManager.class.getResourceAsStream("validator-rules.xml"));

        // 設定ファイルパース
        if (CheckUtils.isEmpty(_path)) {
            throw new ValidatorManagerException("validation設定ファイルが取得できませんでした");
        }
        InputStream is = null;
        try {
            is = ValidatorManager.class.getClassLoader().getResourceAsStream(_path.toString());
            if (is != null) {
                listStream.add(is);

                InputStream[] configures = listStream.toArray(new InputStream[listStream.size()]);

                // Validator生成
                resources = new ValidatorResources(configures);

            } else {
                // validation-configがない場合
                // LogUtils.warn(
                // logger,
                // ValidatorMessagesHandler.create().getSimpleMessage(
                // "W0000005", _application, _service));
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new ValidatorManagerException(e);
        } catch (SAXException e) {
            e.printStackTrace();
            throw new ValidatorManagerException(e);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ValidatorManagerException(e);
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // time-stamp
        // LogUtils.process(
        // logger, "validate(String, String, AbstractBaseControllerObject)",
        // startMillis, System.currentTimeMillis());
        // // debug-end
        // LogUtils.end(
        // logger,
        // "validate(String, String, AbstractBaseControllerObject)",
        // testmessages);

    }
}
