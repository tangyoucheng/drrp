/*
 * Copyright(c) 2011 
 */
package cn.com.prescription.framework.action.form;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 写真AlbumのDto.
 * 
 * @author NTTDC
 */
public class PhotoAlbumDto implements Serializable {

    /** 串行版本号 */
    private static final long serialVersionUID = 1L;

    /** 写真Albumのリスト */
    private List<PhotoDto> photoAlbum = new ArrayList<PhotoDto>();

    /** photoAlbumのindex */
    private int index = 0;

    /**
     * 写真Albumのリストを取得します。
     * @return 写真Albumのリスト
     */
    public List<PhotoDto> getPhotoAlbum() {
        return photoAlbum;
    }

    /**
     * 写真Albumのリストを設定します。
     * @param photoAlbum 写真Albumのリスト
     */
    public void setPhotoAlbum(List<PhotoDto> photoAlbum) {
        this.photoAlbum = photoAlbum;
    }

    /**
     * photoAlbumのindexを取得します。
     * @return photoAlbumのindex
     */
    public int getIndex() {
        return index;
    }

    /**
     * photoAlbumのindexを設定します。
     * @param index photoAlbumのindex
     */
    public void setIndex(int index) {
        this.index = index;
    }
}
