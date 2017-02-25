/*
 * Copyright(c) 2011 
 */
package cn.com.prescription.framework.common.component.download;

import java.io.File;
import java.io.InputStream;

import org.apache.commons.io.FileUtils;

import cn.com.prescription.framework.action.AbstractAction;
import cn.com.prescription.framework.event.StandardEventResult;

/**
 * ダウンロード処理
 * 
 * @author bpchikazawa
 */
public class DownloadAction extends AbstractAction {

    /** 串行版本号 */
    private static final long serialVersionUID = 1L;

    /** ダウンロードファイル情報（セッション格納キー） */
    public static final String SESSION_KEY_DOWNLOAD_INFO = AbstractAction.class.getName()
        .concat(".SESSION_KEY_DOWNLOAD_INFO");

    /** ストリーム */
    private InputStream inputStream = null;
    /** ファイル名等 */
    private String contentDisposition = "";
    /** ファイルサイズ */
    private Long contentLength = 0L;
    /** ファイル種別 */
    private String contentType = "";
    /** バッファサイズ */
    private Long bufferSize = 1024L;

    /**
     * ダイアログ表示
     */
    @Override
    public String doInit() throws Exception {

        // セッションからパス情報を取得
        StandardEventResult result_ = (StandardEventResult) this.session.get(SESSION_KEY_DOWNLOAD_INFO);

        // レスポンス出力
        File file_ = new File(result_.getDownloadPath());

        // ストリーム設定
        setInputStream(FileUtils.openInputStream(file_));

        // ファイル名設定
        String fileName = result_.getDownloadFile();
        String userAgent = request.getHeader("User-Agent");

        // 针对IE或者以IE为内核的浏览器：
        if (userAgent.contains("MSIE") || userAgent.contains("Trident")) {
            fileName = java.net.URLEncoder.encode(fileName, "UTF-8");
        } else {
            // 非IE浏览器的处理：
            fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
        }
        setContentDisposition("attachment; filename=\"".concat(fileName).concat("\""));

        // コンテントタイプ
        setContentType("application/octet-stream");

        // ファイルサイズ
        setContentLength(file_.length());

        // セッションクリア
        this.request.getSession().removeAttribute(SESSION_KEY_DOWNLOAD_INFO);

        // 常に正常終了
        return SUCCESS;

    }

    /**
     * @return ストリーム
     */
    public final InputStream getInputStream() {
        return inputStream;
    }

    /**
     * @param _inputStream ストリーム
     */
    public final void setInputStream(InputStream _inputStream) {
        inputStream = _inputStream;
    }

    /**
     * @return ファイル名等
     */
    public final String getContentDisposition() {
        return contentDisposition;
    }

    /**
     * @param _contentDisposition ファイル名等
     */
    public final void setContentDisposition(String _contentDisposition) {
        contentDisposition = _contentDisposition;
    }

    /**
     * @return ファイルサイズ
     */
    public final Long getContentLength() {
        return contentLength;
    }

    /**
     * @param _contentLength ファイルサイズ
     */
    public final void setContentLength(Long _contentLength) {
        contentLength = _contentLength;
    }

    /**
     * @return ファイル種別
     */
    public final String getContentType() {
        return contentType;
    }

    /**
     * @param _contentType ファイル種別
     */
    public final void setContentType(String _contentType) {
        contentType = _contentType;
    }

    /**
     * @return バッファサイズ
     */
    public final Long getBufferSize() {
        return bufferSize;
    }

    /**
     * @param _bufferSize バッファサイズ
     */
    public final void setBufferSize(Long _bufferSize) {
        bufferSize = _bufferSize;
    }

}
