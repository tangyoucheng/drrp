/*
 * Copyright(c) 2011 
 */
package cn.com.prescription.framework.data.s2dao;

import java.util.HashMap;
import java.util.Map;

import cn.com.prescription.framework.exception.SystemException;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

/**
 * S2daoContainerManager
 * <p>
 * S2コンテナマネージャ<br>
 * </p>
 * 
 * @author t.d.m
 */
/*
 * 新規作成
 * DATE: 2010/01/15 NAME: 唐代明
 * 更新
 * DATE: $Date:: 2010-01-15 21:34:01#$ NAME: $Author: 唐代明 $
 */
public class S2daoContainerManager {

    /** インスタンス */
    private static S2daoContainerManager instance = new S2daoContainerManager();

    /** コンテナマップ */
    private Map<String, S2Container> containerMap = new HashMap<String, S2Container>();

    /** コンストラクタ */
    private S2daoContainerManager() {
    }

    /**
     * インスタンス取得
     * 
     * @return YnsContainerManager コンテナマネージャ
     */
    public static S2daoContainerManager getInstance() {
        return instance;
    }

    /**
     * コンテナー生成
     * 
     * @param name コンポーネント名
     * @return S2Container コンテナー
     * @throws SystemException 系统例外
     */
    public S2Container create(String name) throws SystemException {

        S2Container container = null;

        if (containerMap.containsKey(name)) {
            container = containerMap.get(name);
        } else {
            SingletonS2ContainerFactory.setConfigPath(name);
            SingletonS2ContainerFactory.init();
            container = SingletonS2ContainerFactory.getContainer();

            // container = S2ContainerFactory.create(name);
            containerMap.put(name, container);
        }

        return container;
    }
}
