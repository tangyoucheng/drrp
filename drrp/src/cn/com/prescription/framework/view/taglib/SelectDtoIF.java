/*
 * Copyright(c) 2011 
 */
package cn.com.prescription.framework.view.taglib;

import java.io.Serializable;

/**
 * スタイル指定可能な <select> タグ用データソースインターフェース.
 * 
 * @author bpchikazawa
 */
public interface SelectDtoIF extends Serializable {

    /**
     * @return このオプションの CSS クラス.
     */
    String getCssClass();

}
