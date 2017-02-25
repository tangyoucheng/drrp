/*
 * Copyright(c) 2016 
 */
package cn.com.prescription.leshan.rpm.biz;

import cn.com.prescription.framework.biz.StandardBiz;
import cn.com.prescription.framework.biz.StandardLogic;
import cn.com.prescription.framework.event.StandardEvent;
import cn.com.prescription.framework.event.StandardEventResult;
import cn.com.prescription.framework.exception.ApplicationException;
import cn.com.prescription.framework.exception.SystemException;
import cn.com.prescription.framework.util.StringUtils;
import cn.com.prescription.leshan.common.data.RpmAdvertisementDao;
import cn.com.prescription.leshan.rpm.action.form.RPM00501Form;

/**
 * 推广信息添加初期化处理。
 * 新規作成
 * DATE: 2016.03.24 NAME: tyc
 */
public class RPM00501InitLogic extends StandardBiz implements StandardLogic {

    /**
     * 役割マスタDAO
     */
    RpmAdvertisementDao rpmAdvertisementDao = null;

    /**
     * 用户情報設定画面（一般用户用） 初期化業務クラス的构造。
     */
    public RPM00501InitLogic() {
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
        RPM00501Form inForm_ = (RPM00501Form) _event.getForm();

        // // (2)役割リストを取得する
        // RpmRoleCondition roleCondition = new RpmRoleCondition();
        // // 消除标识
        // roleCondition.setDeleteFlag(LeshanConstantsIF.DEL_FLG_YUKO_RECORD);
        // // ソートキー ASC
        // // roleCondition.addPageSortField("RPM_ROLE.SORT_KEY", LeshanConstantsIF.SORT_SEQ_ASC);
        // List<RpmRoleModel> roleModelList_ = rpmRoleDao.selectBySort(roleCondition);
        // // [取得情報：役割リスト]の取得件数=0件の場合、错误とする。以降の処理を行わない
        // if (roleModelList_.size() == 0) {
        // // メッセージID【E00033】(パラメータ１："役割リスト取得")
        // this.addErrorMessage(MessageUtils.getMessage("E00033", "役割リスト取得"));
        // this.errorEnd();
        // } else {
        // // 役割リストを取得する
        // CodeValueRecord record_ = null;
        // List<CodeValueRecord> roleList_ = new ArrayList<CodeValueRecord>();
        // for (RpmRoleModel roleModel : roleModelList_) {
        // record_ = new CodeValueRecord();
        // record_.setRecordCode(roleModel.getRoleId());
        // record_.setRecordValue(roleModel.getRoleName());
        // roleList_.add(record_);
        // }
        // // inForm_.setYakuwariDataSource(roleList_);
        // }

        inForm_.setAdId(StringUtils.getUniqueId());

        // 出力情報設定
        return this.getEventResult(inForm_);
    }

    /**
     * 役割マスタDAOを設定する。
     * 
     * @param _rpmRoleDao 役割マスタDAO
     */
    public void setRpmAdvertisementDao(RpmAdvertisementDao _rpmAdvertisementDao) {
        this.rpmAdvertisementDao = _rpmAdvertisementDao;
    }

}
