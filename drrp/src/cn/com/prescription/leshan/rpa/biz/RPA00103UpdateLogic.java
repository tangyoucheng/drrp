/*
 * Copyright(c) 2016 
 */
package cn.com.prescription.leshan.rpa.biz;

import cn.com.prescription.framework.biz.ActionInfo;
import cn.com.prescription.framework.biz.StandardBiz;
import cn.com.prescription.framework.biz.StandardLogic;
import cn.com.prescription.framework.common.session.UserSessionUtils;
import cn.com.prescription.framework.event.StandardEvent;
import cn.com.prescription.framework.event.StandardEventResult;
import cn.com.prescription.framework.exception.ApplicationException;
import cn.com.prescription.framework.exception.SystemException;
import cn.com.prescription.framework.message.MessageUtils;
import cn.com.prescription.framework.util.CheckUtils;
import cn.com.prescription.framework.util.DateUtils;
import cn.com.prescription.framework.util.NumberUtils;
import cn.com.prescription.framework.util.ReflectUtils;
import cn.com.prescription.framework.util.TimestampUtils;
import cn.com.prescription.leshan.common.LeshanConstantsIF;
import cn.com.prescription.leshan.common.data.RpmPrescriptionDao;
import cn.com.prescription.leshan.common.data.RpmPrescriptionDrug01Dao;
import cn.com.prescription.leshan.common.data.RpmPrescriptionDrugDao;
import cn.com.prescription.leshan.common.data.condition.RpmPrescriptionCondition;
import cn.com.prescription.leshan.common.data.condition.RpmPrescriptionDrug01Condition;
import cn.com.prescription.leshan.common.data.model.RpmPrescriptionDrugModel;
import cn.com.prescription.leshan.common.data.model.RpmPrescriptionModel;
import cn.com.prescription.leshan.rpa.action.RPA00102Action;
import cn.com.prescription.leshan.rpa.action.form.RPA0010301Dto;
import cn.com.prescription.leshan.rpa.action.form.RPA00103Form;

/**
 * 处方编辑页面 登録処理 ロジック
 * 
 * @author fsb
 */
/*
 * 新規作成
 * DATE: 2012.11. NAME: fsb
 */
public class RPA00103UpdateLogic extends StandardBiz implements StandardLogic {

    /**
     * 用户基本テーブル DAO
     */
    private RpmPrescriptionDao rpmPrescriptionDao = null;

    /**
     * 处方药品一览基本テーブル DAO
     */
    private RpmPrescriptionDrugDao rpmPrescriptionDrugDao = null;

    /**
     * 处方药品一览基本テーブル DAO
     */
    private RpmPrescriptionDrug01Dao rpmPrescriptionDrug01Dao = null;

    /**
     * 用户情報設定ロジック的构造。
     */
    public RPA00103UpdateLogic() {
        super();
    }

    /**
     * 業務処理を行う。
     * 
     * @param _event event
     * @return event処理結果
     * @throws ApplicationException event処理里应用程序例外发生的情况
     * @throws SystemException event処理里系统例外发生的情况
     */
    public StandardEventResult service(StandardEvent _event) throws ApplicationException, SystemException {

        // event処理結果
        RPA00103Form inForm_ = (RPA00103Form) _event.getForm();

        // 排他情報：处方情報
        RpmPrescriptionModel prescriptionModel_ = prescriptionCheck(inForm_);

        // 处方テーブルの登録
        updatePrescription(inForm_, prescriptionModel_);

        // 处方药品テーブルの登録
        updatePrescriptionDrug(inForm_);

        // 完了画面を表示する。
        this.connectCompleteDialog(MessageUtils.getMessage("I00001", "登录"),
            new ActionInfo(RPA00102Action.class, "doBack"));
        // 出力情報設定
        return this.getEventResult(inForm_);

    }

    /**
     * 用户プロファイルテーブルのデータを取得し、ロック（for update）を行う
     * 
     * @param _inForm RPA00103Form
     * @return PatientModel_
     * @throws ApplicationException event処理里应用程序例外发生的情况
     * @throws SystemException event処理里系统例外发生的情况
     */
    private RpmPrescriptionModel prescriptionCheck(RPA00103Form _inForm) throws ApplicationException, SystemException {

        // 排他情報：处方情報
        RpmPrescriptionCondition _condition_ = new RpmPrescriptionCondition();
        // 处方ＩＤ
        _condition_.setPrescriptionId(_inForm.getPrescriptionId());
        // 消除标识＝ [定数：消除标识．有効レコード]
        _condition_.setDeleteFlag(LeshanConstantsIF.DEL_FLG_YUKO_RECORD);
        // [排他情報：用户プロファイル情報]
        RpmPrescriptionModel returnModel_ = rpmPrescriptionDao.selectForUpdate(_condition_);
        // [排他情報：用户プロ情報]の取得件数 ＝ 0 の場合
        if (returnModel_ == null) {
            // メッセージID【E00004】（パラメータ1："削除"、パラメータ２："登录"）
            this.addErrorMessage(MessageUtils.getMessage("E00004", "删除", "登录"));
            this.errorEnd();
        }
        // [排他情報：用户プロ情報．最終更新日]≠[画面：最終更新日]の場合
        if (returnModel_ != null && !CheckUtils.isEqual(_inForm.getLastUpdateDatePrescription(),
            TimestampUtils.formatUpd(returnModel_.getLastUpdateDate()))) {
            // メッセージID【E00006】
            this.addErrorMessage(MessageUtils.getMessage("E00006", "更新", "登录", returnModel_.getLastUpdateUserName(),
                DateUtils.format(returnModel_.getLastUpdateDate(), LeshanConstantsIF.DATE_FORMAT_YYYY_MM_DD_HH_MM)));
            this.errorEnd();
        }
        return returnModel_;
    }

    /**
     * 处方テーブルの登録を行う
     * 
     * @param _inForm SCHM00301Form
     * @param prescriptionModel_ RpmPrescriptionModel
     * @throws ApplicationException event処理里应用程序例外发生的情况
     * @throws SystemException event処理里系统例外发生的情况
     */
    private void updatePrescription(RPA00103Form _inForm, RpmPrescriptionModel prescriptionModel_)
                    throws ApplicationException, SystemException {

        // 处方状态
        prescriptionModel_.setPrescriptionStatus("1");
        // 处方种类
        prescriptionModel_.setPrescriptionType(_inForm.getPrescriptionType());
        // 处方内容
        prescriptionModel_.setContents(_inForm.getPrescriptionContent());
        // 处方图片路径
        prescriptionModel_.setFileContents(_inForm.getFileContents());
        // 处方配药者
        // prescriptionModel_.setDispenseUserId(UserSessionUtils.getUserSessionInfo().getUserId());
        // 处方配药者名
        // prescriptionModel_.setDispenseUserName(UserSessionUtils.getUserSessionInfo().getUserName());
        // 处方审核者
        // 处方审核者名
        // 门诊/住院号
        prescriptionModel_.setInpatientNumber(_inForm.getInpatientNumber());
        // 科室
        prescriptionModel_.setDepartment(_inForm.getDepartment());
        // 床号
        prescriptionModel_.setBedNo(_inForm.getBedNo());
        // 过敏史
        prescriptionModel_.setAllergyHistory(_inForm.getAllergyHistory());
        // 临床诊断
        prescriptionModel_.setDiagnosis(_inForm.getDiagnosis());
        // 价格
        prescriptionModel_.setPrice(_inForm.getPrice());
        // 备注

        // 最終更新用户ID
        prescriptionModel_.setLastUpdateUserId(UserSessionUtils.getUserSessionInfo().getUserId());
        // 最終更新用户名
        prescriptionModel_.setLastUpdateUserName(UserSessionUtils.getUserSessionInfo().getUserName());
        // 最終更新日
        prescriptionModel_.setLastUpdateDate(TimestampUtils.getSysTimestamp());

        // オブジェクトの空の文字列を削除する。
        ReflectUtils.clearAllSpace(prescriptionModel_);

        int PatientCount_ = rpmPrescriptionDao.update(prescriptionModel_);
        // 件数 ＝ 0 の場合
        if (PatientCount_ == 0) {
            this.addErrorMessage(MessageUtils.getMessage("E00047", "登录"));
            this.errorEnd();
        }
    }

    /**
     * 处方药品一览テーブルの登録を行う
     * 
     * @param _inForm SCHM00301Form
     * @throws ApplicationException event処理里应用程序例外发生的情况
     * @throws SystemException event処理里系统例外发生的情况
     */
    private void updatePrescriptionDrug(RPA00103Form _inForm) throws ApplicationException, SystemException {

        RpmPrescriptionDrug01Condition _rpDrug01condition = new RpmPrescriptionDrug01Condition();
        _rpDrug01condition.setPrescriptionId(_inForm.getPrescriptionId());
        rpmPrescriptionDrug01Dao.deleteList(_rpDrug01condition);

        if (_inForm.getSubForm1() != null) {
            for (int i = 0; i < _inForm.getSubForm1().size(); i++) {
                RPA0010301Dto rpa0010301Dto = _inForm.getSubForm1().get(i);

                RpmPrescriptionDrugModel insertPrescriptionDrugModel_ = new RpmPrescriptionDrugModel();

                // 处方ＩＤ
                insertPrescriptionDrugModel_.setPrescriptionId(_inForm.getPrescriptionId());
                // 药品ID
                insertPrescriptionDrugModel_.setDrugId(rpa0010301Dto.getDrugId());
                // 数量
                insertPrescriptionDrugModel_.setQuantity(rpa0010301Dto.getQuantity());
                // 排序码
                insertPrescriptionDrugModel_.setSortKey(NumberUtils.toBigDecimal(i));
                // 备注

                // 最終更新患者ID
                insertPrescriptionDrugModel_.setLastUpdateUserId(UserSessionUtils.getUserSessionInfo().getUserId());
                // 最終更新患者名
                insertPrescriptionDrugModel_.setLastUpdateUserName(UserSessionUtils.getUserSessionInfo().getUserName());
                // 最終更新日
                insertPrescriptionDrugModel_.setLastUpdateDate(TimestampUtils.getSysTimestamp());

                // オブジェクトの空の文字列を削除する。
                ReflectUtils.clearAllSpace(insertPrescriptionDrugModel_);

                int returnCount_ = rpmPrescriptionDrugDao.insert(insertPrescriptionDrugModel_);
                // 件数 ＝ 0 の場合
                if (returnCount_ == 0) {
                    this.addErrorMessage(MessageUtils.getMessage("E00047", "登录"));
                    this.errorEnd();
                }
            }
        }

    }

    /**
     * 用户基本テーブル DAO的设定
     * 
     * @param _rpmPrescriptionDao 用户基本テーブル DAO
     */
    public void setRpmPrescriptionDao(RpmPrescriptionDao _rpmPrescriptionDao) {
        this.rpmPrescriptionDao = _rpmPrescriptionDao;
    }

    /**
     * 处方药品一览基本テーブル DAO的设定
     * 
     * @param _rpmPrescriptionDrugDao 处方药品一览基本テーブル DAO
     */
    public void setRpmPrescriptionDrugDao(RpmPrescriptionDrugDao _rpmPrescriptionDrugDao) {
        this.rpmPrescriptionDrugDao = _rpmPrescriptionDrugDao;
    }

    /**
     * 处方药品一览基本テーブル DAO的设定
     * 
     * @param _rpmPrescriptionDrug01Dao 处方药品一览基本テーブル DAO
     */
    public void setRpmPrescriptionDrug01Dao(RpmPrescriptionDrug01Dao _rpmPrescriptionDrug01Dao) {
        this.rpmPrescriptionDrug01Dao = _rpmPrescriptionDrug01Dao;
    }

}