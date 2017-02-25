package cn.com.prescription.leshan.common.action.form;

import java.io.Serializable;

/**
 * APIリザルトDTOクラス.
 * 
 * @version 1.0
 * @since 1.0
 */
public class COMFileUploadResultDto implements Serializable {

    /** デフォルトシリアルーバージョンID */
    private static final long serialVersionUID = 1L;

    /** ファイル名 */
    private String name;

    /** サイズ */
    private int size;

    /** 缩略图URL */
    private String thumbnailUrl;

    /** 削除URL */
    private String deleteUrl;

    /** 削除メソッドタイプ */
    private String deleteType;

    /** 错误 */
    private String error;

    /** ファイルパス */
    private String filePath;

    /**
     * ファイル名的取得
     * @return ファイル名
     */
    public String getName() {
        return name;
    }

    /**
     * ファイル名的设定
     * @param _name ファイル名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * サイズ的取得
     * @return サイズ
     */
    public int getSize() {
        return size;
    }

    /**
     * サイズ的设定
     * @param _size サイズ
     */
    public void setSize(int size) {
        this.size = size;
    }

    /**
     * 缩略图URL的取得
     * @return 缩略图URL
     */
    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    /**
     * 缩略图URL的设定
     * @param _thumbnailUrl 缩略图URL
     */
    public void setThumbnailUrl(String _thumbnailUrl) {
        this.thumbnailUrl = _thumbnailUrl;
    }

    /**
     * 削除URL的取得
     * @return 削除URL
     */
    public String getDeleteUrl() {
        return deleteUrl;
    }

    /**
     * 削除URL的设定
     * @param _deleteUrl 削除URL
     */
    public void setDeleteUrl(String deleteUrl) {
        this.deleteUrl = deleteUrl;
    }

    /**
     * 削除メソッドタイプ的取得
     * @return 削除メソッドタイプ
     */
    public String getDeleteType() {
        return deleteType;
    }

    /**
     * 削除メソッドタイプ的设定
     * @param _deleteType 削除メソッドタイプ
     */
    public void setDeleteType(String deleteType) {
        this.deleteType = deleteType;
    }

    /**
     * 错误的取得
     * @return 错误
     */
    public String getError() {
        return error;
    }

    /**
     * 错误的设定
     * @param _error 错误
     */
    public void setError(String error) {
        this.error = error;
    }

    /**
     * ファイルパス的取得
     * @return ファイルパス
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     * ファイルパス的设定
     * @param _filePath ファイルパス
     */
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

}
