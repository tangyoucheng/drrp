/*
 * Copyright(c) 2011 
 */
package cn.com.prescription.framework.event;

import java.util.ArrayList;

import cn.com.prescription.framework.action.form.StandartForm;
import cn.com.prescription.framework.event.exception.EventApplicationException;
import cn.com.prescription.framework.event.exception.EventValidateException;

import org.intra_mart.framework.base.event.Event;
import org.intra_mart.framework.base.event.EventResult;
import org.intra_mart.framework.base.event.StandardEventListener;
import org.intra_mart.framework.system.exception.ApplicationException;
import org.intra_mart.framework.system.exception.SystemException;

/**
 * 標準の抽象eventリスナー
 * 
 * @author nttdc
 * @param <P> eventクラス
 * @param <R> event処理結果クラス
 */
public abstract class AbstractStandardEventListener<P extends StandardEvent, R extends StandardEventResult> extends
                StandardEventListener {

    /** event処理結果 */
    private R eventResult = null;

    /** 错误メッセージ */
    private ArrayList<String> errorMessages = new ArrayList<String>();

    /** form */
    private StandartForm form;

    /**
     * event処理を実行します。
     * <p>
     * intra-mart のeventフレームワークより呼び出しされます。<br>
     * 
     * @param _event このeventリスナに引き渡されたevent
     * @return event処理結果
     * @throws SystemException 系统例外发生的情况にスローされる
     * @throws ApplicationException 应用程序产生异常的情况にスローされる
     */
    @Override
    protected final EventResult fire(Event _event) throws SystemException, ApplicationException {

        // event処理
        doExecute((P) _event);

        // 処理結果を返す
        return eventResult;

    }

    /**
     * event処理的调用。
     * 
     * @param _event このeventリスナに引き渡されたevent
     * @throws SystemException 系统例外发生的情况にスローされる
     * @throws ApplicationException 应用程序产生异常的情况にスローされる
     */
    public void doExecute(P _event) throws SystemException, ApplicationException {

        // event処理を実行する
        execute(_event);

        // event処理結果設定
        R result_ = getEventResult();

        if (result_ == null) {
            result_ = (R) new StandardEventResult();
        }

        // 出力情報の設定
        result_.setForm(form);

        // 処理結果を設定
        setEventResult(result_);

    }

    /**
     * event処理を実装する。
     * <p>
     * 実装eventリスナクラスは、本メソッドを実装しビジネスロジックを実装する。
     * </p>
     * 
     * @param _event このeventリスナに引き渡されたevent
     * @throws ApplicationException event処理にて应用程序产生异常的情况スローされる
     * @throws SystemException event処理にて系统例外发生的情况スローされる
     */
    protected abstract void execute(P _event) throws ApplicationException, SystemException;

    /**
     * @param _errorMessages 错误メッセージ
     */
    public void setErrorMessages(ArrayList<String> _errorMessages) {
        this.errorMessages = _errorMessages;
    }

    /**
     * 処理で错误が発生したメッセージ的设定。
     * <p>
     * 処理で错误が発生した場合に错误内容に対応する項目名とメッセージ的设定。
     * </p>
     * 
     * @param _message メッセージ
     */
    protected final void addErrorMessage(String _message) {
        errorMessages.add(_message);
    }

    /**
     * @return 错误メッセージ
     */
    public ArrayList<String> getErrorMessages() {
        return errorMessages;
    }

    /**
     * バリデーション错误が存在した場合に例外を発生させる
     * 
     * @throws EventValidateException バリデーション错误発生時
     */
    protected final void checkErrorEnd() throws EventValidateException {
        if (errorMessages.size() > 0) {
            throw new EventValidateException("event処理でチェック错误が発生しました。", errorMessages);
        }
    }

    /**
     * アプリケーション错误が存在した場合に例外を発生させる
     * 
     * @throws EventApplicationException アプリケーション错误発生時
     */
    protected final void errorEnd() throws EventApplicationException {
        if (errorMessages.size() > 0) {
            throw new EventApplicationException("event処理でアプリケーション例外が発生しました。", errorMessages);
        }
    }

    /**
     * eventResult 的取得。
     * 
     * @return eventResult
     */
    public R getEventResult() {
        return eventResult;
    }

    /**
     * event処理結果的设定。
     * 
     * @param _eventResult event処理結果
     */
    public void setEventResult(R _eventResult) {
        this.eventResult = _eventResult;
    }

    /**
     * @return form
     */
    public StandartForm getForm() {
        return form;
    }

    /**
     * @param _form form
     */
    public void setForm(StandartForm _form) {
        this.form = _form;
    }

    // /**
    // * 単一ファイルの上传処理。
    // *
    // * @param _file ファイル
    // * @param _oldFileName ファイル名
    // * @param _newFileName ファイル名
    // * @param _realPath パス
    // * @return ファイル名
    // * @throws Exception 系统例外发生的情况にスローされる
    // */
    // protected final String doUpload(List<File> _file, List<String> _oldFileName, String _newFileName, String
    // _realPath)
    // throws Exception {
    // String fileName = "default.gif";
    // if (_file != null && !_file.isEmpty()) {
    //
    // fileName = _file.get(0).getName();
    // if (!CheckUtils.isEmpty(_newFileName)) {
    // String[] fileNames = _oldFileName.get(0).split("\\.");
    // fileName = _newFileName + "." + fileNames[1];
    // }
    //
    // File destFile = new File(_realPath, fileName);
    //
    // InputStream inputStream = new FileInputStream(_file.get(0));
    // OutputStream outputStream = new FileOutputStream(destFile);
    // byte[] buffer = new byte[400];
    //
    // int length = 0;
    //
    // while ((length = inputStream.read(buffer)) > 0) {
    // outputStream.write(buffer, 0, length);
    // }
    // inputStream.close();
    // outputStream.close();
    // }
    // return fileName;
    // }

    // /**
    // * 複数ファイルの上传処理。
    // *
    // * @param _file ファイル
    // * @param _oldFileName ファイル名
    // * @param _newFileName ファイル名
    // * @param _realPath パス
    // * @throws Exception 系统例外发生的情况にスローされる
    // */
    // protected final void doUpload(List<File> _file, List<String> _oldFileName, List<String> _newFileName,
    // String _realPath) throws Exception {
    // if (_file != null && !_file.isEmpty()) {
    // if (_newFileName == null || _newFileName.isEmpty()) {
    // _newFileName = new ArrayList<String>();
    // for (int j = 0; j < _file.size(); j++) {
    // _newFileName.add("");
    // }
    // }
    //
    // for (int i = 0; i < _file.size(); ++i) {
    // if (_file.get(i) != null) {
    // String fileName = _oldFileName.get(i);
    // if (!CheckUtils.isEmpty(_newFileName.get(i))) {
    // String[] fileNames = fileName.split("\\.");
    // fileName = _newFileName.get(i) + "." + fileNames[1];
    // }
    //
    // File destFile = new File(_realPath, fileName);
    //
    // InputStream inputStream = new FileInputStream(_file.get(i));
    // OutputStream outputStream = new FileOutputStream(destFile);
    // byte[] buffer = new byte[400];
    //
    // int length = 0;
    //
    // while ((length = inputStream.read(buffer)) > 0) {
    // outputStream.write(buffer, 0, length);
    // }
    // inputStream.close();
    // outputStream.close();
    // }
    // }
    // }
    // }

    // /**
    // * ファイル削除処理
    // *
    // * @param _realPath パス
    // * @param _fileName ファイル名
    // * @throws Exception 予期せぬ错误発生時
    // */
    // protected final void doUpdateImg(String _realPath, String _fileName) throws Exception {
    // String[] fileNames = _fileName.split("\\.");
    // String fileName = fileNames[0] + "_confirm" + "." + fileNames[1];
    //
    // File inFile = new File(_realPath, fileName);
    // File destFile = new File(_realPath, _fileName);
    //
    // InputStream inputStream = new FileInputStream(inFile);
    // OutputStream outputStream = new FileOutputStream(destFile);
    // byte[] buffer = new byte[400];
    //
    // int length = 0;
    //
    // while ((length = inputStream.read(buffer)) > 0) {
    // outputStream.write(buffer, 0, length);
    // }
    // inputStream.close();
    // outputStream.close();
    //
    // inFile.delete();
    // }

    // /**
    // * Bean形式クラスのオブジェクト間でプロパティ値をコピーする。
    // * <p>
    // * Bean形式クラスからBean形式クラスへ同名のプロパティへ値をコピーする<br/>
    // * データベース取得結果の Modelオブジェクトからコントローラオブジェクトへの値コピーに利用する。<br>
    // * <ul>
    // * <li>コピー対象が存在しない場合は、コピーが行われない。</li>
    // * <li>データ型が異なる場合は、コピー対象プロパティのデータ型に併せて変換される。</li>
    // * <li>Map形式の場合もコピーが可能。その場合、キー名にてマッピングが行われる。</li>
    // * </ul>
    // * </p>
    // *
    // * @param _dest コピー先のBean形式クラス
    // * @param _origenal コピー元のBean形式クラス
    // * @throws EventSystemException 以下の場合に例外がスローされる
    // * <ul>
    // * <li>コピー先のアクセッサにアクセスできない場合</li>
    // * <li>コピー先のアクセッサメソッドが例外をスローした場合</li>
    // * </ul>
    // * @since 1.0
    // */
    // protected final void copyProperties(Object _dest, Object _origenal) throws EventSystemException {
    // try {
    // BeanUtils.copyProperties(_dest, _origenal);
    //
    // } catch (IllegalAccessException e) {
    // // アクセッサにアクセスできない場合
    // throw new EventSystemException("オブジェクト間のプロパティ値のコピーに失敗しました。", e);
    // } catch (InvocationTargetException e) {
    // // アクセッサメソッドが例外をスローした場合
    // throw new EventSystemException("オブジェクト間のプロパティ値のコピーに失敗しました。", e);
    // }
    // }

    // /**
    // * コンポーネント取得.
    // *
    // * @param <C> コンポーネント
    // * @param _componentClass 取得クラス
    // * @return コンポーネント
    // */
    // protected <C> C getComponent(Object _componentClass) {
    // return ServiceUtils.getComponent(_componentClass);
    // }

}
