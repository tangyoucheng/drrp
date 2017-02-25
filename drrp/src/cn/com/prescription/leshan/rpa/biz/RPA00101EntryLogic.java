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
import cn.com.prescription.framework.util.DateUtils;
import cn.com.prescription.framework.util.NumberUtils;
import cn.com.prescription.framework.util.ReflectUtils;
import cn.com.prescription.framework.util.StringUtils;
import cn.com.prescription.framework.util.TimestampUtils;
import cn.com.prescription.leshan.common.LeshanConstantsIF;
import cn.com.prescription.leshan.common.data.RpmPatientDao;
import cn.com.prescription.leshan.common.data.RpmPrescriptionDao;
import cn.com.prescription.leshan.common.data.RpmPrescriptionDrugDao;
import cn.com.prescription.leshan.common.data.condition.RpmPatientCondition;
import cn.com.prescription.leshan.common.data.model.RpmPatientModel;
import cn.com.prescription.leshan.common.data.model.RpmPrescriptionDrugModel;
import cn.com.prescription.leshan.common.data.model.RpmPrescriptionModel;
import cn.com.prescription.leshan.rpa.action.RPA00101Action;
import cn.com.prescription.leshan.rpa.action.form.RPA0010101Dto;
import cn.com.prescription.leshan.rpa.action.form.RPA00101Form;

/**
 * 处方添加页面 登録処理 ロジック
 * 
 * @author fsb
 */
/*
 * 新規作成
 * DATE: 2012.11. NAME: fsb
 */
public class RPA00101EntryLogic extends StandardBiz implements StandardLogic {

    /**
     * 患者基本テーブル DAO
     */
    private RpmPatientDao rpmPatientDao = null;

    /**
     * 处方基本テーブル DAO
     */
    private RpmPrescriptionDao rpmPrescriptionDao = null;

    /**
     * 处方药品一览基本テーブル DAO
     */
    private RpmPrescriptionDrugDao rpmPrescriptionDrugDao = null;

    /**
     * 患者情報設定ロジック的构造。
     */
    public RPA00101EntryLogic() {
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
        RPA00101Form inForm_ = (RPA00101Form) _event.getForm();

        // 患者信息テーブルの登録
        insertPatient(inForm_);

        String prescriptionID = StringUtils.getUniqueId();
        // 处方テーブルの登録
        insertPrescription(inForm_, prescriptionID);

        // 处方药品一览テーブルの登録
        insertPrescriptionDrug(inForm_, prescriptionID);

        // 完了画面を表示する。
        this.connectCompleteDialog(MessageUtils.getMessage("I00001", "登录"),
            new ActionInfo(RPA00101Action.class, "doInit"));
        // 出力情報設定
        return this.getEventResult(inForm_);

    }

    /**
     * 患者信息テーブルの登録を行う
     * 
     * @param _inForm SCHM00301Form
     * @throws ApplicationException event処理里应用程序例外发生的情况
     * @throws SystemException event処理里系统例外发生的情况
     */
    private void insertPatient(RPA00101Form _inForm) throws ApplicationException, SystemException {

        // 排他情報：患者プロ情報
        RpmPatientCondition patientCondition_ = new RpmPatientCondition();
        // 患者名
        patientCondition_.setUserName(_inForm.getPatientName());
        // 患者手机
        patientCondition_.setCeelNumber(_inForm.getCeelNumber());
        // 消除标识＝ [定数：消除标识．有効レコード]
        patientCondition_.setDeleteFlag(LeshanConstantsIF.DEL_FLG_YUKO_RECORD);
        // [排他情報：患者プロファイル情報]
        RpmPatientModel patientModel_ = rpmPatientDao.select(patientCondition_);
        if (patientModel_ == null) {
            rpmPatientDao.updateLockTable();
            RpmPatientModel insertPatientModel_ = new RpmPatientModel();

            // 患者ID
            insertPatientModel_.setUserId(StringUtils.getUniqueId());
            // 患者名
            insertPatientModel_.setUserName(_inForm.getPatientName());
            // 生日
            insertPatientModel_.setBirthday(DateUtils.format(_inForm.getBirthday(),
                LeshanConstantsIF.DATE_FORMAT_YYYY_YEAR_MM_MONTH_DD_DATE, LeshanConstantsIF.DATE_FORMAT_YYYYMMDD));
            // 性别ID
            insertPatientModel_.setSexId(_inForm.getSexId());
            // 手机
            insertPatientModel_.setCeelNumber(_inForm.getCeelNumber());
            // 身份证号码
            insertPatientModel_.setIdNumber(_inForm.getIdNumber());
            // 居住地
            insertPatientModel_.setAddr(_inForm.getAddr());
            // 消除标识
            insertPatientModel_.setDeleteFlag(LeshanConstantsIF.DEL_FLG_YUKO_RECORD);
            // 创建者
            insertPatientModel_.setCreateUserId(UserSessionUtils.getUserSessionInfo().getUserId());
            // 创建日
            insertPatientModel_.setCreateDate(TimestampUtils.getSysTimestamp());
            // 最終更新患者ID
            insertPatientModel_.setLastUpdateUserId(UserSessionUtils.getUserSessionInfo().getUserId());
            // 最終更新患者名
            insertPatientModel_.setLastUpdateUserName(UserSessionUtils.getUserSessionInfo().getUserName());
            // 最終更新日
            insertPatientModel_.setLastUpdateDate(TimestampUtils.getSysTimestamp());

            // オブジェクトの空の文字列を削除する。
            ReflectUtils.clearAllSpace(insertPatientModel_);

            int PatientCount_ = rpmPatientDao.insert(insertPatientModel_);
            // 件数 ＝ 0 の場合
            if (PatientCount_ == 0) {
                this.addErrorMessage(MessageUtils.getMessage("E00047", "登录"));
                this.errorEnd();
            }
            _inForm.setPatientId(insertPatientModel_.getUserId());
        } else {
            _inForm.setPatientId(patientModel_.getUserId());
        }
    }

    /**
     * 处方テーブルの登録を行う
     * 
     * @param _inForm SCHM00301Form
     * @param _prescriptionID 处方ID
     * @throws ApplicationException event処理里应用程序例外发生的情况
     * @throws SystemException event処理里系统例外发生的情况
     */
    private void insertPrescription(RPA00101Form _inForm, String _prescriptionID)
                    throws ApplicationException, SystemException {

        rpmPrescriptionDao.updateLockTable();
        RpmPrescriptionModel insertPrescriptionModel_ = new RpmPrescriptionModel();

        // 处方ＩＤ
        insertPrescriptionModel_.setPrescriptionId(_prescriptionID);
        // 患者ID
        insertPrescriptionModel_.setPatientId(_inForm.getPatientId());
        // 处方状态
        insertPrescriptionModel_.setPrescriptionStatus("1");
        // 处方种类
        insertPrescriptionModel_.setPrescriptionType(_inForm.getPrescriptionType());
        // 处方内容
        insertPrescriptionModel_.setContents(_inForm.getPrescriptionContent());
        // 处方图片路径
        insertPrescriptionModel_.setFileContents(_inForm.getFileContents());
        // 处方做成者
        insertPrescriptionModel_.setPrescriptionCreateUserId(UserSessionUtils.getUserSessionInfo().getUserId());
        // 处方做成者名
        insertPrescriptionModel_.setPrescriptionCreateUserName(UserSessionUtils.getUserSessionInfo().getUserName());
        // 处方配药者
        // 处方配药者名
        // 处方审核者
        // 处方审核者名
        // 门诊/住院号
        insertPrescriptionModel_.setInpatientNumber(_inForm.getInpatientNumber());
        // 科室
        insertPrescriptionModel_.setDepartment(_inForm.getDepartment());
        // 床号
        insertPrescriptionModel_.setBedNo(_inForm.getBedNo());
        // 过敏史
        insertPrescriptionModel_.setAllergyHistory(_inForm.getAllergyHistory());
        // 临床诊断
        insertPrescriptionModel_.setDiagnosis(_inForm.getDiagnosis());
        // 价格
        insertPrescriptionModel_.setPrice(_inForm.getPrice());
        // 备注

        // 消除标识
        insertPrescriptionModel_.setDeleteFlag(LeshanConstantsIF.DEL_FLG_YUKO_RECORD);
        // 创建者
        insertPrescriptionModel_.setCreateUserId(UserSessionUtils.getUserSessionInfo().getUserId());
        // 创建日
        insertPrescriptionModel_.setCreateDate(TimestampUtils.getSysTimestamp());
        // 最終更新患者ID
        insertPrescriptionModel_.setLastUpdateUserId(UserSessionUtils.getUserSessionInfo().getUserId());
        // 最終更新患者名
        insertPrescriptionModel_.setLastUpdateUserName(UserSessionUtils.getUserSessionInfo().getUserName());
        // 最終更新日
        insertPrescriptionModel_.setLastUpdateDate(TimestampUtils.getSysTimestamp());

        // オブジェクトの空の文字列を削除する。
        ReflectUtils.clearAllSpace(insertPrescriptionModel_);

        int PatientCount_ = rpmPrescriptionDao.insert(insertPrescriptionModel_);
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
     * @param _prescriptionID 处方ID
     * @throws ApplicationException event処理里应用程序例外发生的情况
     * @throws SystemException event処理里系统例外发生的情况
     */
    private void insertPrescriptionDrug(RPA00101Form _inForm, String _prescriptionID)
                    throws ApplicationException, SystemException {

        rpmPrescriptionDrugDao.updateLockTable();
        if (_inForm.getSubForm1() != null) {
            for (int i = 0; i < _inForm.getSubForm1().size(); i++) {
                RPA0010101Dto rpa0010101Dto = _inForm.getSubForm1().get(i);

                RpmPrescriptionDrugModel insertPrescriptionDrugModel_ = new RpmPrescriptionDrugModel();

                // 处方ＩＤ
                insertPrescriptionDrugModel_.setPrescriptionId(_prescriptionID);
                // 药品ID
                insertPrescriptionDrugModel_.setDrugId(rpa0010101Dto.getDrugId());
                // 数量
                insertPrescriptionDrugModel_.setQuantity(rpa0010101Dto.getQuantity());
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
     * 患者基本テーブル DAO的设定
     * 
     * @param _rpmPatientDao 患者基本テーブル DAO
     */
    public void setRpmPatientDao(RpmPatientDao _rpmPatientDao) {
        this.rpmPatientDao = _rpmPatientDao;
    }

    /**
     * 处方基本テーブル DAO的设定
     * 
     * @param _rpmPrescriptionDao 处方基本テーブル DAO
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

}