/*
 * Copyright(c) 2016 
 */
package cn.com.prescription.leshan.rpa.biz;

import java.util.ArrayList;
import java.util.List;

import cn.com.prescription.framework.CodeValueRecord;
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
import cn.com.prescription.framework.util.TimestampUtils;
import cn.com.prescription.leshan.common.LeshanConstantsIF;
import cn.com.prescription.leshan.common.data.RpmPatient01Dao;
import cn.com.prescription.leshan.common.data.RpmPrescriptionDrug01Dao;
import cn.com.prescription.leshan.common.data.condition.RpmPatient01Condition;
import cn.com.prescription.leshan.common.data.condition.RpmPrescriptionDrug01Condition;
import cn.com.prescription.leshan.common.data.model.RpmPatient01Model;
import cn.com.prescription.leshan.common.data.model.RpmPrescriptionDrug01Model;
import cn.com.prescription.leshan.rpa.action.RPA00102Action;
import cn.com.prescription.leshan.rpa.action.form.RPA0010401Dto;
import cn.com.prescription.leshan.rpa.action.form.RPA00104Form;

/**
 * 处方调配初期化处理。
 * 新規作成
 * DATE: 2016.03.24 NAME: tyc
 */
public class RPA00104InitLogic extends StandardBiz implements StandardLogic {

    /**
     * 患者DAO
     */
    RpmPatient01Dao rpmPatient01Dao = null;

    /**
     * 处方药品一览基本テーブル DAO
     */
    private RpmPrescriptionDrug01Dao rpmPrescriptionDrug01Dao = null;

    /**
     * RPA00104InitLogic的构造。
     */
    public RPA00104InitLogic() {
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
        RPA00104Form inForm_ = (RPA00104Form) _event.getForm();

        RpmPatient01Condition patientCondition = new RpmPatient01Condition();
        // 用户ID
        patientCondition.setUserId(inForm_.getPatientId());
        // 处方ID
        patientCondition.setPrescriptionId(inForm_.getPrescriptionId());
        // 消除标识
        patientCondition.setDeleteFlag(LeshanConstantsIF.DEL_FLG_YUKO_RECORD);

        RpmPatient01Model rpmPatient01Model = rpmPatient01Dao.selectPrescriptionInfo(patientCondition);

        if (rpmPatient01Model != null) {

            // 处方ＩＤ
            inForm_.setPrescriptionId(rpmPatient01Model.getPrescriptionId());
            // 患者ID
            inForm_.setPatientId(rpmPatient01Model.getUserId());
            // 患者姓名
            inForm_.setPatientName(rpmPatient01Model.getUserName());
            // 患者性别
            if (CheckUtils.isEqual("1", rpmPatient01Model.getSexId())) {
                inForm_.setSex("男");
            }
            if (CheckUtils.isEqual("2", rpmPatient01Model.getSexId())) {
                inForm_.setSex("女");
            }
            // 患者出生日期
            inForm_.setBirthday(DateUtils.format(rpmPatient01Model.getBirthday(),
                LeshanConstantsIF.DATE_FORMAT_YYYYMMDD, LeshanConstantsIF.DATE_FORMAT_YYYY_YEAR_MM_MONTH_DD_DATE));
            // 身份证号码
            inForm_.setIdNumbe(rpmPatient01Model.getIdNumber());
            // 手机号码
            inForm_.setCeelNumber(rpmPatient01Model.getCeelNumber());
            // 患者居住地
            inForm_.setAddr(rpmPatient01Model.getAddr());
            // TODO 定数的设定再讨论
            // 处方状态
            inForm_.setPrescriptionStatus(rpmPatient01Model.getPrescriptionStatus());
            // 处方种类
            inForm_.setPrescriptionType(rpmPatient01Model.getPrescriptionType());
            // 处方内容
            inForm_.setPrescriptionContent(rpmPatient01Model.getContents());
            // 处方图片内容
            inForm_.setFileContents(rpmPatient01Model.getFileContents());
            // 处方做成日
            inForm_.setPrescriptionCreateDate(TimestampUtils.format(rpmPatient01Model.getCreateDatePrescription(),
                LeshanConstantsIF.DATE_FORMAT_YYYY_YEAR_MM_MONTH_DD_DATE + " HH点mm分"));
            // 处方做成者
            inForm_.setPrescriptionCreateUserId(rpmPatient01Model.getPrescriptionCreateUserId());
            // 处方做成者名
            inForm_.setPrescriptionCreateUserName(rpmPatient01Model.getPrescriptionCreateUserName());
            // 处方配药者
            // 处方配药者名
            inForm_.setDispenseUserName(UserSessionUtils.getUserSessionInfo().getUserName());
            // 处方审核者
            // 处方审核者名
            // 门诊/住院号
            inForm_.setInpatientNumber(rpmPatient01Model.getInpatientNumber());
            // 科室
            inForm_.setDepartment(rpmPatient01Model.getDepartment());
            // 床号
            inForm_.setBedNo(rpmPatient01Model.getBedNo());
            // 过敏史
            inForm_.setAllergyHistory(rpmPatient01Model.getAllergyHistory());
            // 临床诊断
            inForm_.setDiagnosis(rpmPatient01Model.getDiagnosis());
            // 价格
            inForm_.setPrice(rpmPatient01Model.getPrice());
            // 备注
            // 最終更新日
            inForm_.setLastUpdateDatePrescription(
                TimestampUtils.formatUpd(rpmPatient01Model.getLastUpdateDatePrescription()));
        } else {
            // this.connectCompleteDialog(MessageUtils.getMessage("E00009"),
            // new ActionInfo(RPA00202Action.class, "doInit"));
            this.addErrorMessage(MessageUtils.getMessage("E00004", "削除", "表示"));
            this.errorEnd(new ActionInfo(RPA00102Action.class, "doBack"));
        }

        RpmPrescriptionDrug01Condition _rpDrug01condition = new RpmPrescriptionDrug01Condition();
        _rpDrug01condition.setPrescriptionId(rpmPatient01Model.getPrescriptionId());
        // ソートキー ASC
        _rpDrug01condition.addPageSortField("prescription_drug.SORT_KEY", LeshanConstantsIF.SORT_SEQ_ASC);
        List<RpmPrescriptionDrug01Model> returnList = rpmPrescriptionDrug01Dao.selectList(_rpDrug01condition);
        if (returnList != null && !returnList.isEmpty()) {
            List<RPA0010401Dto> _subForm1 = new ArrayList<RPA0010401Dto>();
            for (RpmPrescriptionDrug01Model rpmPrescriptionDrug01Model : returnList) {
                RPA0010401Dto rpa0010401Dto = new RPA0010401Dto();
                rpa0010401Dto.setDrugId(rpmPrescriptionDrug01Model.getDrugId());
                rpa0010401Dto.setDrugName(rpmPrescriptionDrug01Model.getDrugName());
                rpa0010401Dto.setManufacturerName(rpmPrescriptionDrug01Model.getManufacturerName());
                rpa0010401Dto.setPrice(rpmPrescriptionDrug01Model.getPrice());
                rpa0010401Dto.setUnit(rpmPrescriptionDrug01Model.getUnit());
                rpa0010401Dto.setQuantity(rpmPrescriptionDrug01Model.getQuantity());
                _subForm1.add(rpa0010401Dto);
            }
            inForm_.setSubForm1(_subForm1);
        }

        // inForm_.setPrescriptionCreateDate(DateUtils.format(DateUtils.getSysDateFromApServer(),
        // LeshanConstantsIF.DATE_FORMAT_YYYY_YEAR_MM_MONTH_DD_DATE));
        // inForm_.setPrescriptionCreateUserName(UserSessionUtils.getUserName()); // 处方リストを取得する
        CodeValueRecord record_ = null;
        List<CodeValueRecord> prescriptionType = new ArrayList<CodeValueRecord>();
        record_ = new CodeValueRecord();
        record_.setRecordCode("0");
        record_.setRecordValue("无");
        prescriptionType.add(record_);
        record_ = new CodeValueRecord();
        record_.setRecordCode("1");
        record_.setRecordValue("图片");
        prescriptionType.add(record_);
        record_ = new CodeValueRecord();
        record_.setRecordCode("2");
        record_.setRecordValue("文字");
        prescriptionType.add(record_);

        inForm_.setPrescriptionTypeDataSource(prescriptionType);

        // 出力情報設定
        return this.getEventResult(inForm_);
    }

    /**
     * 患者DAO的设定
     * 
     * @param _rpmPatient01Dao 患者DAO
     */
    public void setRpmPatient01Dao(RpmPatient01Dao _rpmPatient01Dao) {
        this.rpmPatient01Dao = _rpmPatient01Dao;
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
