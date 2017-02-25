/*
 * Copyright(c) 2011 
 */
package cn.com.prescription.framework.data.s2dao;

import cn.com.prescription.framework.exception.SystemException;

import org.intra_mart.framework.base.data.DAO;
import org.seasar.framework.container.S2Container;

/**
 * S2daoCommonProcessor
 * <p>
 * 基底Processor<br>
 * </p>
 * 
 * @author t.d.m
 */
/*
 * 新規作成
 * DATE: 2010/01/15 NAME: t.d.m
 * 更新
 * DATE: $Date:: 2010-01-15 21:34:01#$ NAME: $Author: t.d.m $
 */
public class S2daoCommonProcessor {

    /**
     * DAO取得.
     * 
     * @param name コンポーネント名
     * @param clazz DAOインターフェイス
     * @return DAO 生成されたDAO
     * @throws SystemException 例外
     */
    public static DAO getDAO(String name, Class<?> clazz) throws SystemException {

        // コンテナの生成
        S2Container container = S2daoContainerManager.getInstance().create(name);

        // DAOの取得
        DAO dao = (DAO) container.getComponent(clazz);

        return dao;
    }
}
