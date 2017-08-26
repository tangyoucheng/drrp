/*
 * Copyright(c) 2016 
 */
package cn.com.prescription.leshan.rpa.biz;

import cn.com.prescription.framework.biz.StandardBiz;
import cn.com.prescription.framework.biz.StandardLogic;
import cn.com.prescription.framework.event.StandardEvent;
import cn.com.prescription.framework.event.StandardEventResult;
import cn.com.prescription.framework.exception.ApplicationException;
import cn.com.prescription.framework.exception.SystemException;
import cn.com.prescription.leshan.rpa.action.form.RPA0020501Dto;
import cn.com.prescription.leshan.rpa.action.form.RPA00205Form;

/**
 * 患者信息导出初期化处理。
 * 新規作成
 * DATE: 2016.03.24 NAME: tyc
 */
public class RPA00205InitLogic extends StandardBiz implements StandardLogic {

    /**
     * 患者信息导出画面 初期化業務クラス的构造。
     */
    public RPA00205InitLogic() {
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
        RPA00205Form inForm_ = (RPA00205Form) _event.getForm();

        // 前回検索結果クリア
        inForm_.getSubForm1().clear();

        RPA0020501Dto rpa0020501Dto = new RPA0020501Dto();
        rpa0020501Dto.setItemId("user_name");
        rpa0020501Dto.setItemName("患者姓名");
        inForm_.getSubForm1().add(rpa0020501Dto);
        rpa0020501Dto = new RPA0020501Dto();
        rpa0020501Dto.setItemId("sex_id");
        rpa0020501Dto.setItemName("性别");
        inForm_.getSubForm1().add(rpa0020501Dto);
        rpa0020501Dto = new RPA0020501Dto();
        rpa0020501Dto.setItemId("birthday");
        rpa0020501Dto.setItemName("出生日期");
        inForm_.getSubForm1().add(rpa0020501Dto);
        rpa0020501Dto = new RPA0020501Dto();
        rpa0020501Dto.setItemId("ceel_number");
        rpa0020501Dto.setItemName("手机号码");
        inForm_.getSubForm1().add(rpa0020501Dto);
        rpa0020501Dto = new RPA0020501Dto();
        rpa0020501Dto.setItemId("phone_number");
        rpa0020501Dto.setItemName("座机号码");
        inForm_.getSubForm1().add(rpa0020501Dto);
        rpa0020501Dto = new RPA0020501Dto();
        rpa0020501Dto.setItemId("email");
        rpa0020501Dto.setItemName("电子邮箱");
        inForm_.getSubForm1().add(rpa0020501Dto);
        rpa0020501Dto = new RPA0020501Dto();
        rpa0020501Dto.setItemId("post_number");
        rpa0020501Dto.setItemName("邮政编码");
        inForm_.getSubForm1().add(rpa0020501Dto);
        rpa0020501Dto = new RPA0020501Dto();
        rpa0020501Dto.setItemId("addr");
        rpa0020501Dto.setItemName("住址");
        inForm_.getSubForm1().add(rpa0020501Dto);
        

        rpa0020501Dto = new RPA0020501Dto();
        rpa0020501Dto.setItemId("nation");
        rpa0020501Dto.setItemName("民族");
        inForm_.getSubForm1().add(rpa0020501Dto);
        rpa0020501Dto = new RPA0020501Dto();
        rpa0020501Dto.setItemId("place_of_birth");
        rpa0020501Dto.setItemName("出生地");
        inForm_.getSubForm1().add(rpa0020501Dto);
        rpa0020501Dto = new RPA0020501Dto();
        rpa0020501Dto.setItemId("marital_status");
        rpa0020501Dto.setItemName("婚况");
        inForm_.getSubForm1().add(rpa0020501Dto);
        rpa0020501Dto = new RPA0020501Dto();
        rpa0020501Dto.setItemId("lunar_birthday");
        rpa0020501Dto.setItemName("农历生日");
        inForm_.getSubForm1().add(rpa0020501Dto);
        rpa0020501Dto = new RPA0020501Dto();
        rpa0020501Dto.setItemId("time_of_birth");
        rpa0020501Dto.setItemName("出生时间");
        inForm_.getSubForm1().add(rpa0020501Dto);
        rpa0020501Dto = new RPA0020501Dto();
        rpa0020501Dto.setItemId("zodiac");
        rpa0020501Dto.setItemName("属相");
        inForm_.getSubForm1().add(rpa0020501Dto);
        rpa0020501Dto = new RPA0020501Dto();
        rpa0020501Dto.setItemId("company");
        rpa0020501Dto.setItemName("单位");
        inForm_.getSubForm1().add(rpa0020501Dto);
        rpa0020501Dto = new RPA0020501Dto();
        rpa0020501Dto.setItemId("profession");
        rpa0020501Dto.setItemName("职业");
        inForm_.getSubForm1().add(rpa0020501Dto);

        // 出力情報設定
        return this.getEventResult(inForm_);
    }

}
