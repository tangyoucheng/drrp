/*
 * Copyright(c) 2011 
 */
package cn.com.prescription.framework.action.form;

import java.io.File;
import java.io.Serializable;

import cn.com.prescription.framework.exception.SystemException;
import cn.com.prescription.framework.util.CheckUtils;
import cn.com.prescription.framework.util.FileUtils;

/**
 * uploadファールのDto.
 * 
 * @author NTTDC
 */
public class UploadFileDto implements Serializable {

    /**
     * 串行版本号
     */
    private static final long serialVersionUID = 1L;

    /** 上传ファイル */
    private File file;
    /** 上传ファイル名 */
    private String fileFileName;
    /** 上传ファイル種別 */
    private String fileContentType;

    /** 上传ファイルdisplay path */
    private String fileDisPath;

    /** 上传ファイルserver path */
    private String fileServerPath;

    /**
     * 上传ファイルdisplay pathを取得します。
     * 
     * @return 上传ファイルdisplay path
     */
    public String getFileDisPath() {
        return fileDisPath;
    }

    /**
     * 上传ファイルdisplay pathを設定します。
     * 
     * @param fileDisPath 上传ファイルdisplay path
     */
    public void setFileDisPath(String fileDisPath) {
        this.fileDisPath = fileDisPath;
    }

    /**
     * 上传ファイルを取得します。
     * 
     * @return 上传ファイル
     */
    public File getFile() {
        if (file == null && !CheckUtils.isEmpty(fileServerPath)) {
            file = new File(fileServerPath);
        }
        return file;
    }

    /**
     * 上传ファイルを設定します。
     * 
     * @param file 上传ファイル
     */
    public void setFile(File file) throws SystemException {
        this.file = file;
        if (file.exists()) {
            this.fileServerPath = this.file.getAbsolutePath() + ".uploadtep";
            FileUtils.copy(this.file.getAbsolutePath(), fileServerPath);
        }
    }

    /**
     * 上传ファイル名を取得します。
     * 
     * @return 上传ファイル名
     */
    public String getFileFileName() {
        return fileFileName;
    }

    /**
     * 上传ファイル名を設定します。
     * 
     * @param fileFileName 上传ファイル名
     */
    public void setFileFileName(String fileFileName) {
        this.fileFileName = fileFileName;
    }

    /**
     * 上传ファイル種別を取得します。
     * 
     * @return 上传ファイル種別
     */
    public String getFileContentType() {
        return fileContentType;
    }

    /**
     * 上传ファイル種別を設定します。
     * 
     * @param fileContentType 上传ファイル種別
     */
    public void setFileContentType(String fileContentType) {
        this.fileContentType = fileContentType;
    }

    /**
     * 上传ファイルserver pathを取得します。
     * 
     * @return 上传ファイルserver path
     */
    public String getFileServerPath() {
        return fileServerPath;
    }

    /**
     * 上传ファイルserver pathを設定します。
     * 
     * @param fileServerPath 上传ファイルserver path
     */
    public void setFileServerPath(String fileServerPath) {
        if (!CheckUtils.isEmpty(fileServerPath)) {
            this.fileServerPath = fileServerPath;
        }
    }
}
