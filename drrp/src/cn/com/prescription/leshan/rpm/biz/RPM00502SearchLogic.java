/*
 * Copyright(c) 2016 
 */
package cn.com.prescription.leshan.rpm.biz;

import java.util.List;

import cn.com.prescription.framework.biz.StandardBiz;
import cn.com.prescription.framework.biz.StandardLogic;
import cn.com.prescription.framework.event.StandardEvent;
import cn.com.prescription.framework.event.StandardEventResult;
import cn.com.prescription.framework.exception.ApplicationException;
import cn.com.prescription.framework.exception.SystemException;
import cn.com.prescription.framework.util.StringUtils;
import cn.com.prescription.leshan.common.LeshanConstantsIF;
import cn.com.prescription.leshan.common.data.RpmAdvertisement01Dao;
import cn.com.prescription.leshan.common.data.condition.RpmAdvertisementCondition;
import cn.com.prescription.leshan.common.data.model.RpmAdvertisementModel;
import cn.com.prescription.leshan.rpm.action.form.RPM0050201Dto;
import cn.com.prescription.leshan.rpm.action.form.RPM00502Form;

/**
 * 角色一览检索处理。
 * 新規作成
 * DATE: 2016.03.24 NAME: tyc
 */
public class RPM00502SearchLogic extends StandardBiz implements StandardLogic {

    /**
     * 角色DAO
     */
    RpmAdvertisement01Dao rpmAdvertisement01Dao = null;

    /**
     * 用户情報設定画面（一般用户用） 初期化業務クラス的构造。
     */
    public RPM00502SearchLogic() {
        super();
    }
    
    

    /**
     * event処理を行う。
     * 
     * @return event処理結果
     * @param _event
     *            event
     * @throws ApplicationException
     *             event処理里应用程序例外发生的情况
     * @throws SystemException
     *             event処理里系统例外发生的情况
     */
    public StandardEventResult service(StandardEvent _event) throws ApplicationException, SystemException {

        // event処理結果
        RPM00502Form inForm_ = (RPM00502Form) _event.getForm();

        // (2)役割リストを取得する
        RpmAdvertisementCondition adCondition = new RpmAdvertisementCondition();
        // 角色ID
        adCondition.setAdId(StringUtils.getLikeParameter(inForm_.getAdId()));
        // 角色名
        adCondition.setAdName(StringUtils.getLikeParameter(inForm_.getAdName()));
        // 消除标识
        adCondition.setDeleteFlag(LeshanConstantsIF.DEL_FLG_YUKO_RECORD);

        long rolecount_ = rpmAdvertisement01Dao.selectAdListCount(adCondition);
        // 前回検索結果クリア
        inForm_.getSubForm1().clear();
        // 件数チェック処理
        this.checkCount(inForm_, rolecount_);

        // 前回検索結果クリア
        // ソートキー ASC
        // roleCondition.addPageSortField("RPM_ROLE.SORT_KEY", LeshanConstantsIF.SORT_SEQ_ASC);
        adCondition.setPageStartRowNo(inForm_.getPageStartRowNo() + 1);
        List<RpmAdvertisementModel> adModelList_ =  rpmAdvertisement01Dao.selectAdList(adCondition);
        for (RpmAdvertisementModel adModel : adModelList_) {
            RPM0050201Dto rpm00502Dto = new RPM0050201Dto();
            rpm00502Dto.setAdId(adModel.getAdId());
            rpm00502Dto.setAdName(adModel.getAdName());
            rpm00502Dto.setAdUrl(adModel.getAdUrl());
            if("1".equals(adModel.getAdStatus())){
                rpm00502Dto.setAdStatus("推广中");
            }
            
            if("2".equals(adModel.getAdStatus())){
                rpm00502Dto.setAdStatus("未使用");
            }
            rpm00502Dto.setNotes(adModel.getNotes());
            inForm_.getSubForm1().add(rpm00502Dto);
        }

        // 出力情報設定
        return this.getEventResult(inForm_);
    }

    /**
     * 角色DAOを取得します。
     * @return 角色DAO
     */
    public RpmAdvertisement01Dao getRpmAdvertisement01Dao() {
        return rpmAdvertisement01Dao;
    }

    /**
     * 角色DAOを設定します。
     * @param _rpmAdvertisement01Dao 角色DAO
     */
    public void setRpmAdvertisement01Dao(RpmAdvertisement01Dao _rpmAdvertisement01Dao) {
        this.rpmAdvertisement01Dao = _rpmAdvertisement01Dao;
    }

}
