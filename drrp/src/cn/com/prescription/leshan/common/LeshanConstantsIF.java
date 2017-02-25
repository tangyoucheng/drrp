/*
 * Copyright(c) 2011 
 */
package cn.com.prescription.leshan.common;

import cn.com.prescription.framework.StandardConstantsIF;

public interface LeshanConstantsIF extends StandardConstantsIF {

    /** 先頭表示区分：無 */
    String SENTO_HYOJI_KUBUN_NASHI = "0";
    /** 先頭表示区分：空白 */
    String SENTO_HYOJI_KUBUN_SPACE = "1";
    /** 先頭表示区分：全て */
    String SENTO_HYOJI_KUBUN_SUBETE = "2";
    /** 先頭表示区分：指定なし */
    String SENTO_HYOJI_KUBUN_SHITEI_NASI = "3";
    /** 公開标识：公開しない */
    String KOKAI_FLG_KOKAI_SHINAI = "0";
    /** 公開标识：公開する */
    String KOKAI_FLG_KOKAI_SURU = "1";
    }
