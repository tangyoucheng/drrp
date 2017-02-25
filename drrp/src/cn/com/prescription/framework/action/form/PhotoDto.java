/*
 * Copyright(c) 2011 
 */
package cn.com.prescription.framework.action.form;

import java.io.Serializable;

/**
 * 写真のDto.
 * 
 * @author NTTDC
 */
public class PhotoDto implements Serializable {

    /** 串行版本号 */
    private static final long serialVersionUID = 1L;

    /** imgPath */
    private String imgPath = "";

    /** imgMaxPath */
    private String imgMaxPath = "";
    
    /** title */
    private String imgTitle = null;
    
    /** biko */
    private String imgBiko = null;
    
    /** width */
    private String imgWidth = null;
    
    /** height */
    private String imgHeight = null;

    public PhotoDto(String imgPath, String imgMaxPath, String imgTitle , String imgBiko , String imgWidth, String imgHeight) {
        this.imgPath = imgPath;
        this.imgMaxPath = imgMaxPath;
        this.imgBiko = imgBiko;
        this.imgTitle = imgTitle;
        this.imgWidth = imgWidth;
        this.imgHeight = imgHeight;
    }

    /**
     * imgPathを取得します。
     * @return imgPath
     */
    public String getImgPath() {
        return imgPath;
    }

    /**
     * imgPathを設定します。
     * @param imgPath imgPath
     */
    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    /**
     * imgMaxPathを取得します。
     * @return imgMaxPath
     */
    public String getImgMaxPath() {
        return imgMaxPath;
    }

    /**
     * imgMaxPathを設定します。
     * @param imgMaxPath imgMaxPath
     */
    public void setImgMaxPath(String imgMaxPath) {
        this.imgMaxPath = imgMaxPath;
    }

    /**
     * titleを取得します。
     * @return title
     */
    public String getImgTitle() {
        return imgTitle;
    }

    /**
     * titleを設定します。
     * @param imgTitle title
     */
    public void setImgTitle(String imgTitle) {
        this.imgTitle = imgTitle;
    }

    /**
     * bikoを取得します。
     * @return biko
     */
    public String getImgBiko() {
        return imgBiko;
    }

    /**
     * bikoを設定します。
     * @param imgBiko biko
     */
    public void setImgBiko(String imgBiko) {
        this.imgBiko = imgBiko;
    }

    /**
     * widthを取得します。
     * @return width
     */
    public String getImgWidth() {
        return imgWidth;
    }

    /**
     * widthを設定します。
     * @param imgWidth width
     */
    public void setImgWidth(String imgWidth) {
        this.imgWidth = imgWidth;
    }

    /**
     * heightを取得します。
     * @return height
     */
    public String getImgHeight() {
        return imgHeight;
    }

    /**
     * heightを設定します。
     * @param imgHeight height
     */
    public void setImgHeight(String imgHeight) {
        this.imgHeight = imgHeight;
    }
}
