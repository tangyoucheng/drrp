/*
 * Copyright(c) 2011 
 */
package cn.com.prescription.framework.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

import org.seasar.framework.util.InputStreamReaderUtil;
import org.seasar.framework.util.ReaderUtil;

import cn.com.prescription.framework.action.ActionMessages;
import cn.com.prescription.framework.action.form.StandartForm;
import cn.com.prescription.framework.action.validator.ValidatorManager;
import cn.com.prescription.framework.action.validator.ValidatorManagerException;
import cn.com.prescription.framework.exception.SystemException;

/**
 * フィールドチェッククラス。
 * <p>
 * 本クラスで提供するバリデーション機能は以下に定義する。
 * </p>
 * 
 * @author t.d.m
 * @since 1.0
 */
public class ValidateCheckUtils {

    /** バリデータマネージャ */
    private ValidatorManager validatorManager = null;

    /** form名 */
    String formName = null;

    /** コンストラクタ */
    private ValidateCheckUtils() {
    }

    /**
     * バリデーションマネージャ的构造。
     * 
     * @return バリデータマネージャ
     * @throws ValidatorManagerException バリデータマネージャの生成に失敗した場合
     */
    public static ValidateCheckUtils getValidatorManager(String _validationPath, String _formName)
                    throws ValidatorManagerException {

        ValidateCheckUtils utils = new ValidateCheckUtils();
        utils.validatorManager = ValidatorManager.getValidatorManager();
        utils.validatorManager.load(_validationPath);
        utils.formName = _formName;
        return utils;
    }

    /**
     * @param _validationPath バリデーション設定ファイルパス
     * @param _formName form名
     * @param _form form
     * @return メッセージ
     * @throws Exception 予期せぬ例外発生時
     */
    public static ActionMessages check(String _validationPath, String _formName, StandartForm _form)
                    throws SystemException {
        ActionMessages errorMessages = new ActionMessages();

        // セッションでバリデータファイルのキー
        String fileSessionKey = _validationPath;
        // ファイル内容
        String fileContext = System.getProperty(fileSessionKey);
        // セッションに該当ファイルが存在しない場合
        if (CheckUtils.isEmpty(fileContext)) {

            // バリデータファイル内容を読み込む
            InputStream inputStream = ValidateCheckUtils.class.getClassLoader().getResourceAsStream(_validationPath);
            // バリデータファイルが存在しない場合
            if (inputStream != null) {
                // reader的取得
                Reader reader = InputStreamReaderUtil.create(inputStream, "UTF-8");

                // ファイル内容的取得
                fileContext = ReaderUtil.readText(reader);
                // セッションにバリデータファイルを保存する
                System.setProperty(fileSessionKey, fileContext);

                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                        throw new SystemException(e);
                    }
                }
            }
        }
        // バリデータチェックがあるの場合
        if (!CheckUtils.isEmpty(fileContext) && fileContext.contains(_formName)) {
            errorMessages.add(ValidatorManager.getValidatorManager().validate(_validationPath, _formName, _form));
        }
        return errorMessages;
    }

    /**
     * @param _validationPath バリデーション設定ファイルパス
     * @param _formName form名
     * @param _form form
     * @return メッセージ
     * @throws Exception 予期せぬ例外発生時
     */
    public ActionMessages check(String _formName, StandartForm _form) throws SystemException {
        ActionMessages errorMessages = new ActionMessages();
        errorMessages.add(this.validatorManager.validate(_formName, _form));
        return errorMessages;
    }

    /**
     * @param _form form
     * @return メッセージ
     * @throws Exception 予期せぬ例外発生時
     */
    public ActionMessages check(StandartForm _form) throws SystemException {
        ActionMessages errorMessages = new ActionMessages();
        errorMessages.add(this.validatorManager.validate(this.formName, _form));
        return errorMessages;
    }

    /**
     * form名的设定
     * 
     * @param _formName form名
     */
    public void setFormName(String _formName) {
        this.formName = _formName;
    }
}
