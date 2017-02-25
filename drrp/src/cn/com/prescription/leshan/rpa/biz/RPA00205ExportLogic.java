/*
 * Copyright(c) 2016 
 */
package cn.com.prescription.leshan.rpa.biz;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import cn.com.prescription.framework.StandardConstantsIF;
import cn.com.prescription.framework.biz.StandardBiz;
import cn.com.prescription.framework.biz.StandardLogic;
import cn.com.prescription.framework.event.StandardEvent;
import cn.com.prescription.framework.event.StandardEventResult;
import cn.com.prescription.framework.exception.ApplicationException;
import cn.com.prescription.framework.exception.SystemException;
import cn.com.prescription.framework.message.MessageUtils;
import cn.com.prescription.framework.util.DateUtils;
import cn.com.prescription.framework.util.FileUtils;
import cn.com.prescription.framework.util.ReflectUtils;
import cn.com.prescription.framework.util.ServiceUtils;
import cn.com.prescription.framework.util.StringUtils;
import cn.com.prescription.framework.util.TimestampUtils;
import cn.com.prescription.leshan.common.LeshanConstantsIF;
import cn.com.prescription.leshan.rpa.action.form.RPA0020501Dto;
import cn.com.prescription.leshan.rpa.action.form.RPA00205Form;
import cn.com.prescription.leshan.rpa.data.RPA00205Dao;
import cn.com.prescription.leshan.rpa.data.condition.RPA00205Condition;
import cn.com.prescription.leshan.rpa.data.model.RPA00205Model;

/**
 * 患者信息导出初期化处理。
 * 新規作成
 * DATE: 2016.03.24 NAME: tyc
 */
public class RPA00205ExportLogic extends StandardBiz implements StandardLogic {

    /**
     * 患者信息导出DAO
     */
    RPA00205Dao rpa00205Dao = null;

    /**
     * 患者信息导出画面 初期化業務クラス的构造。
     */
    public RPA00205ExportLogic() {
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
        List<RPA0020501Dto> subForm1 = inForm_.getSubForm1();

        List<String> exportItemIdList = new ArrayList<String>();
        List<String> exportItemNameList = new ArrayList<String>();
        for (int i = 0; i < subForm1.size(); i++) {
            RPA0020501Dto rpa0020501Dto = subForm1.get(i);
            if (rpa0020501Dto.isCheckedFlag()) {
                String[] ItemIdArray = rpa0020501Dto.getItemId().split("_");
                String editedItemId = ItemIdArray[0];
                if (ItemIdArray.length > 1) {
                    for (int j = 1; j < ItemIdArray.length; j++) {
                        char firstChar = ItemIdArray[j].charAt(0);
                        char uFirstChar = Character.toUpperCase(firstChar);
                        editedItemId = editedItemId + ItemIdArray[j].replaceFirst(String.valueOf(firstChar),
                            String.valueOf(uFirstChar));
                    }
                }
                exportItemIdList.add(editedItemId);
                exportItemNameList.add(rpa0020501Dto.getItemName());
            }
        }
        RPA00205Condition patientCondition = new RPA00205Condition();
        // // 抽出项目
        // patientCondition.setExportItems(exportItems.toString());
        // 消除标识
        // patientCondition.setDeleteFlag(LeshanConstantsIF.DEL_FLG_YUKO_RECORD);
        patientCondition.addPageSortField("user_name", LeshanConstantsIF.SORT_SEQ_ASC);
        List<RPA00205Model> resultList = rpa00205Dao.selectPatientList(patientCondition);
        if (resultList.isEmpty() || resultList.size() == 0) {
            this.addErrorMessage(MessageUtils.getMessage("E00002", "患者信息"));
            this.errorEnd();
        }

        // Excel文件创建以下
        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet = wb.createSheet("sheet");
        // TITLE行
        XSSFRow row = sheet.createRow(1);
        XSSFCell cell;
        for (int i = 0; i < exportItemNameList.size(); i++) {
            String itemName = exportItemNameList.get(i);
            cell = row.createCell(i + 1);
            cell.setCellValue(itemName);
        }

        // 数据编辑
        for (int index_ = 0; index_ < resultList.size(); index_++) {
            row = sheet.createRow(2 + index_);
            RPA00205Model dataRecord_ = resultList.get(index_);
            for (int m = 0; m < exportItemIdList.size(); m++) {
                String exportItem = exportItemIdList.get(m);
                cell = row.createCell(m + 1);
                cell.setCellValue(StringUtils.defaultString(ReflectUtils.getFieldValueByName(dataRecord_, exportItem)));
            }

        }

        // CSVファイル名
        StringBuffer fileName_ = new StringBuffer();
        fileName_ = fileName_.append("患者信息");
        fileName_.append("_");
        fileName_.append(
            DateUtils.format(TimestampUtils.getSysTimestamp(), LeshanConstantsIF.DATE_FORMAT_YYYYMMDDHHMMSSSSS));
        fileName_.append(".xlsx");
        // 一時のCSVファイル保存
        FileUtils.writeExelFile(wb, fileName_.toString(),
            ServiceUtils.getAbsolutePath(StandardConstantsIF.KYOTU_EXCEL_FILE_TMP_SAVE_FOLDER));
        this.connectDownload(StandardConstantsIF.KYOTU_EXCEL_FILE_TMP_SAVE_FOLDER, fileName_.toString(),
            fileName_.toString());

        // 出力情報設定
        return this.getEventResult(inForm_);
    }

    /**
     * 患者信息导出DAO的设定
     * 
     * @param _rpa00205Dao 患者信息导出DAO
     */
    public void setRpa00205Dao(RPA00205Dao _rpa00205Dao) {
        this.rpa00205Dao = _rpa00205Dao;
    }

}
