/*
 * Copyright(c) 2011 
 */
package cn.com.prescription.leshan.scz.biz;

import org.intra_mart.framework.system.exception.ApplicationException;
import org.intra_mart.framework.system.exception.SystemException;

import cn.com.prescription.framework.biz.StandardBiz;
import cn.com.prescription.framework.biz.StandardLogic;
import cn.com.prescription.framework.event.StandardEvent;
import cn.com.prescription.framework.event.StandardEventResult;
import cn.com.prescription.leshan.common.data.RpmAdvertisementDao;
import cn.com.prescription.leshan.common.data.condition.RpmAdvertisementCondition;
import cn.com.prescription.leshan.common.data.model.RpmAdvertisementModel;
import cn.com.prescription.leshan.scz.action.form.SCZ9901Form;

/**
 * SCZ990101 门户画面 action。
 * 
 * @author kourei
 */
/*
 * 新規作成
 * DATE: 2010.03.09 NAME: kourei
 */
public class SCZ9901InitLogic extends StandardBiz implements StandardLogic {

    /**
     * 推广信息 DAO
     */
    private RpmAdvertisementDao rpmAdvertisementDao = null;

    /**
     * 门户画面 初期化業務クラス的构造。
     */
    public SCZ9901InitLogic() {
        super();
    }

    /**
     * event処理を行う。
     * 
     * @param _event event
     * @return event処理結果
     * @throws ApplicationException event処理里应用程序例外发生的情况
     * @throws SystemException event処理里系统例外发生的情况
     */
    public StandardEventResult service(StandardEvent _event) throws ApplicationException, SystemException {

        // 初期化
        SCZ9901Form form = (SCZ9901Form) _event.getForm();

        // 1.菜单実行権限の初期化
        // 用户の登录情報からデータソースを切り替える
        // コンテナからファクトリを取得
        // DataSourceFactory dsFactory = (DataSourceFactory) SingletonS2ContainerFactory.getContainer().getComponent(
        // DataSourceFactory.class);

        // データソース名を設定
        // dsFactory.setSelectableDataSourceName(UserSessionUtils.getSchemaName());

        RpmAdvertisementCondition _condition = new RpmAdvertisementCondition();
        _condition.setAdStatus("1");
        RpmAdvertisementModel advertisementModel = rpmAdvertisementDao.select(_condition);
        if (advertisementModel != null) {
            form.setAdUrl(advertisementModel.getAdUrl());
        }
        // 画面情報的设定。
        return this.getEventResult(form);
    }

    /**
     * 推广信息 DAO的设定
     * 
     * @param _rpmAdvertisementDao 推广信息 DAO
     */
    public void setRpmAdvertisementDao(RpmAdvertisementDao _rpmAdvertisementDao) {
        this.rpmAdvertisementDao = _rpmAdvertisementDao;
    }

}
