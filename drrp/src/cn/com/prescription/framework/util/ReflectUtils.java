/*
 * Copyright(c) 2011 
 */
package cn.com.prescription.framework.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import cn.com.prescription.framework.CodeValueRecord;
import cn.com.prescription.framework.exception.SystemException;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;

import sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl;

/**
 * リフレクション用ユーティリティ.
 */
public class ReflectUtils {

    /**
     * インスタンス生成.
     * 
     * @param <T> インスタンス型
     * @param _class 生成クラス
     * @return インスタンス
     * @throws SystemException 予期せぬ错误発生時
     */
    public static <T> T newInstance(Class<?> _class) throws SystemException {
        try {

            // インスタンス生成
            return (T) _class.newInstance();

        } catch (InstantiationException e) {
            throw new SystemException(e.getMessage(), e);
        } catch (IllegalAccessException e) {
            throw new SystemException(e.getMessage(), e);
        }
    }

    /**
     * インスタンス生成.
     * 
     * @param <T> インスタンス型
     * @param _class 生成クラス
     * @return インスタンス
     * @throws SystemException 予期せぬ错误発生時
     */
    public static <T> T newInstance(String _class) throws SystemException {
        return newInstance(newClass(_class));
    }

    /**
     * クラスインスタンス生成.
     * 
     * @param <T> インスタンス型
     * @param _classPath 生成クラスパス
     * @return クラスインスタンス
     * @throws SystemException 予期せぬ错误発生時
     */
    public static <T> Class<T> newClass(String _classPath) throws SystemException {
        try {

            // インスタンス生成
            return (Class<T>) Class.forName(_classPath);

        } catch (Exception e) {
            throw new SystemException(e.getMessage(), e);
        }
    }

    /**
     * メソッド取得.
     * 
     * @param _instance インスタンス
     * @param _methodName メソッド名
     * @param _parameterTypes メソッドパラメータ型
     * @return メソッド
     * @throws SystemException 予期せぬ错误発生時
     */
    public static Method getMethod(Object _instance, String _methodName, Class<?>... _parameterTypes)
                    throws SystemException {
        try {
            // オブジェクトからメソッドを取得
            return _instance.getClass().getMethod(_methodName, _parameterTypes);
        } catch (Exception e) {
            // 予期せぬ例外
            throw new SystemException(e);
        }
    }

    /**
     * メソッド取得（メソッド名にて）
     * 
     * @param _instance インスタンス
     * @param _methodName メソッド名
     * @return メソッドリスト
     * @throws SystemException 予期せぬ错误発生時
     */
    public static List<Method> getMethods(Object _instance, String _methodName) throws SystemException {

        // 結果リスト
        List<Method> result_ = new ArrayList<Method>();

        // メソッド取得
        for (Method method_ : _instance.getClass().getMethods()) {
            // メソッド名チェック
            if (method_.getName().equals(_methodName)) {
                result_.add(method_);
            }
        }

        // メソッドリストを返す
        return result_;

    }

    /**
     * クラスインスタンス生成.
     * 
     * @param <T> 戻り値型
     * @param _instance 実行対象インスタンス
     * @param _method メソッド
     * @param _parameters 引数
     * @return 実行結果
     * @throws SystemException 予期せぬ错误発生時
     */
    public static <T> T invokeMethod(Object _instance, Method _method, Object... _parameters) throws SystemException {
        try {
            // 実行
            return (T) _method.invoke(_instance, _parameters);
        } catch (Exception e) {
            // 予期せぬ例外
            throw new SystemException(e);
        }
    }

    /**
     * Beanプロパティ取得
     * 
     * @param <T> Beanクラス
     * @param _bean Beanオブジェクト
     * @param _name プロパティ名
     * @return 取得値
     * @throws SystemException 予期せぬ错误発生時
     */
    public static <T> T getProperty(Object _bean, String _name) throws SystemException {
        try {
            // プロパティ取得
            return (T) PropertyUtils.getProperty(_bean, _name);
        } catch (Exception e) {
            // 系统例外
            throw new SystemException(e.getMessage(), e);
        }
    }

    /**
     * Beanプロパティ設定
     * 
     * @param <T> Beanクラス
     * @param _bean Beanオブジェクト
     * @param _name プロパティ名
     * @param _value 設定値
     * @return Beanオブジェクト
     * @throws SystemException 予期せぬ错误発生時
     */
    public static <T> T setProperty(T _bean, String _name, Object _value) throws SystemException {
        try {
            // インスタンス生成
            BeanUtils.setProperty(_bean, _name, _value);
            // Beanオブジェクトを返す
            return _bean;
        } catch (Exception e) {
            // 系统例外
            throw new SystemException(e.getMessage(), e);
        }
    }

    /**
     * Beanプロパティ複写
     * 
     * @param <T> Beanクラス
     * @param _srcBean コピー元Bean
     * @param _dstBean コピー先Bean
     * @return コピー処理後のBean
     * @throws SystemException 予期せぬ错误発生時
     */
    public static <T> T copyProperties(Object _srcBean, T _dstBean) throws SystemException {
        try {
            // パラメータチェック
            if (_srcBean == null) {
                return null;
            }
            // プロパティを複写
            PropertyUtils.copyProperties(_dstBean, _srcBean);
            // インスタンスを返す
            return _dstBean;
        } catch (Exception e) {
            // 系统例外
            throw new SystemException(e.getMessage(), e);
        }
    }

    /**
     * オブジェクトのフィールド値的取得
     * 
     * @param object 取得する対象となるオブジェクト
     * @param fieldName フィールド名
     * @return 返回结果
     * @throws SystemException 例外が発生する場合、
     */
    public static Object getFieldValueByName(Object object, String fieldName) throws SystemException {

        if (object == null) {
            return null;
        }
        Object objValue = null;

        try {
            Class<?> cls = object.getClass();
            // フィールドのgetメソッド名を取得
            char firstChar = fieldName.charAt(0);
            char uFirstChar = Character.toUpperCase(firstChar);
            String getMethod = "get" + fieldName.replaceFirst(String.valueOf(firstChar), String.valueOf(uFirstChar));
            // getメソッドを取得
            Method method = cls.getMethod(getMethod, new Class[] {});
            // getメソッドを呼び出し、戻り値はフィールドの値になります
            objValue = method.invoke(object, new Object[] {});
        } catch (Exception e) {
            // 発生した例外を捕捉します
            throw new SystemException(e);
        }

        return objValue;
    }

    /**
     * オブジェクトのgetメッソド的取得
     * 
     * @param object 取得する対象となるオブジェクト
     * @param fieldName フィールド名
     * @return 返回结果
     * @throws SystemException 例外が発生する場合、
     */
    public static Method getFieldMethod(Object object, String fieldName) throws SystemException {

        if (object == null) {
            return null;
        }
        // 2013/01/24 未使用変数をコメントする。
        // Object objValue = null;
        Method method = null;

        try {
            Class<?> cls = object.getClass();
            // フィールドのgetメソッド名を取得
            char firstChar = fieldName.charAt(0);
            char uFirstChar = Character.toUpperCase(firstChar);
            String getMethod = "get" + fieldName.replaceFirst(String.valueOf(firstChar), String.valueOf(uFirstChar));
            // getメソッドを取得
            method = cls.getMethod(getMethod, new Class[] {});
            // getメソッドを呼び出し、戻り値はフィールドの値になります
            // 2013/01/24 未使用変数をコメントする。
            // objValue = method.invoke(object, new Object[] {});
        } catch (Exception e) {
            // 発生した例外を捕捉します
            throw new SystemException(e);
        }

        return method;
    }

    /**
     * 回復するかどうかチェック
     * 
     * @param object 取得する対象となるオブジェクト
     * @param fieldName フィールド名
     * @return 返回结果（true:回復；false:回復しない）
     * @throws SystemException 例外が発生する場合、
     */
    public static boolean isRestore(Object object, String fieldName) throws SystemException {

        if (object == null) {
            return false;
        }

        try {
            Class<?> cls = object.getClass();
            // フィールドのgetメソッド名を取得
            char firstChar = fieldName.charAt(0);
            char uFirstChar = Character.toUpperCase(firstChar);
            String getMethod = "get" + fieldName.replaceFirst(String.valueOf(firstChar), String.valueOf(uFirstChar));
            // getメソッドを取得
            Method method = cls.getMethod(getMethod, new Class[] {});
            method.invoke(object, new Object[] {}).getClass().getGenericSuperclass();
            Type type = method.getGenericReturnType();
            if (type instanceof ParameterizedTypeImpl) {

                ParameterizedTypeImpl parameterizedTypeImpl = (ParameterizedTypeImpl) type;
                Type[] types = parameterizedTypeImpl.getActualTypeArguments();
                if (types != null && types.length != 0 && types[0].equals(CodeValueRecord.class)) {
                    return true;
                }
            }
        } catch (Exception e) {
            // 発生した例外を捕捉します
            throw new SystemException(e);
        }
        return false;
    }

    /**
     * オブジェクトのフィールド値的设定
     * 
     * @param object 対象となるオブジェクト
     * @param fieldName フィールド名
     * @param fieldValue フィールド値
     * @throws SystemException 例外を発生する場合
     */
    public static void setFieldValueByName(Object object, String fieldName, Object fieldValue) throws SystemException {

        if (object == null) {
            return;
        }

        try {
            // オブジェクトのClass取得
            Class<?> cls = object.getClass();
            // 文字列（フィールド名）からFieldオブジェクト取得
            Field field = null;
            try {
                field = cls.getDeclaredField(fieldName);
            } catch (NoSuchFieldException e) {
                if (field == null) {
                    field = cls.getSuperclass().getDeclaredField(fieldName);
                }
            }
            // フィールドの型取得
            Class<?> fieldType = field.getType();
            Class<?>[] argTypes = { fieldType };
            Object[] argValues = { fieldValue };

            setFieldValueByName(cls, object, fieldName, argTypes, argValues);
        } catch (Exception e) {
            // 発生した例外を捕捉します
            throw new SystemException(e);
        }
    }

    /**
     * オブジェクトのフィールド値的设定
     * 
     * @param cls 対象となるクラス
     * @param object 対象となるオブジェクト
     * @param fieldName フィールド名
     * @param argTypes フィールドタイプ
     * @param argValues フィールド値
     * @throws SystemException 例外を発生する場合
     */
    public static void setFieldValueByName(Class<?> cls, Object object, String fieldName, Class<?>[] argTypes,
                    Object[] argValues) throws SystemException {

        if (object == null) {
            return;
        }

        try {
            // setメソッド名
            char firstChar = fieldName.charAt(0);
            char uFirstChar = Character.toUpperCase(firstChar);
            String setterMethod = "set" + fieldName.replaceFirst(String.valueOf(firstChar), String.valueOf(uFirstChar));
            // setメソッド
            Method method = cls.getMethod(setterMethod, argTypes);
            // setメソッド実行（オブジェクト、パラメータ）
            method.invoke(object, argValues);
        } catch (Exception e) {
            // 発生した例外を捕捉します
            throw new SystemException(e);
        }
    }

    /**
     * オブジェクトの空の文字列を削除する
     * 
     * @param object 対象となるオブジェクト
     * @throws SystemException 例外を発生する場合
     */
    public static void clearAllSpace(Object _bean) throws SystemException {

        if (_bean == null) {
            return;
        }

        try {
            Class<?> cls = _bean.getClass();

            for (Method method : cls.getMethods()) {
                if (method.getReturnType() == String.class && method.getName().contains("get")) {
                    Object obj = method.invoke(_bean);
                    if ("".equals(obj)) {
                        String setterMethod = "set" + method.getName().substring(3);
                        // setメソッド
                        Method setMethod = cls.getMethod(setterMethod, String.class);
                        // setメソッド実行（オブジェクト、パラメータ）
                        setMethod.invoke(_bean, (String) null);
                    }
                }
            }
        } catch (Exception e) {
            // 発生した例外を捕捉します
            throw new SystemException(e);
        }
    }
}
