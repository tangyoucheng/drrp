/*
 * Copyright(c) 2016 
 */
package cn.com.prescription.leshan.rpa.biz;

import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import cn.com.prescription.framework.CodeValueRecord;
import cn.com.prescription.framework.StandardConstantsIF;
import cn.com.prescription.framework.biz.StandardBiz;
import cn.com.prescription.framework.biz.StandardLogic;
import cn.com.prescription.framework.event.StandardEvent;
import cn.com.prescription.framework.event.StandardEventResult;
import cn.com.prescription.framework.exception.ApplicationException;
import cn.com.prescription.framework.exception.SystemException;
import cn.com.prescription.framework.message.MessageUtils;
import cn.com.prescription.framework.util.CheckUtils;
import cn.com.prescription.framework.util.DateUtils;
import cn.com.prescription.framework.util.FileUtils;
import cn.com.prescription.framework.util.ServiceUtils;
import cn.com.prescription.framework.util.StringUtils;
import cn.com.prescription.framework.util.TimestampUtils;
import cn.com.prescription.leshan.common.LeshanConstantsIF;
import cn.com.prescription.leshan.rpa.action.form.RPA0010901Dto;
import cn.com.prescription.leshan.rpa.action.form.RPA00109Form;
import cn.com.prescription.leshan.rpa.data.RPA00109Dao;
import cn.com.prescription.leshan.rpa.data.condition.RPA00109Condition;
import cn.com.prescription.leshan.rpa.data.model.RPA00109Model;

/**
 * 处方统计信息导出初期化处理。
 * 新規作成
 * DATE: 2016.03.24 NAME: tyc
 */
public class RPA00109ExportLogic extends StandardBiz implements StandardLogic {

    /**
     * 处方统计信息导出DAO
     */
    RPA00109Dao rpa00109Dao = null;

    /**
     * 处方统计信息导出画面 初期化業務クラス的构造。
     */
    public RPA00109ExportLogic() {
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
        RPA00109Form inForm_ = (RPA00109Form) _event.getForm();

        // 处方リストを取得する
        RPA00109Condition rpa00109Condition = new RPA00109Condition();
        // 处方状态 审核的场合
        rpa00109Condition.setPrescriptionStatus("4");
        // 处方年度
        rpa00109Condition.setPrescriptionYear(inForm_.getPrescriptionYear());
        // // 消除标识
        // rpa00109Condition.setDeleteFlag(LeshanConstantsIF.DEL_FLG_YUKO_RECORD);

        long prescriptionInfoCount_ = rpa00109Dao.selectPrescriptionInfoCount(rpa00109Condition);
        // 件数チェック処理
        if (prescriptionInfoCount_ == 0) {
            this.addErrorMessage(MessageUtils.getMessage("E00002", "处方统计信息"));
            this.errorEnd();
        }

        // 前回検索結果クリア
        inForm_.getSubForm1().clear();

        if (!CheckUtils.isEmpty(inForm_.getPrescriptionYear())) {
            List<RPA00109Model> rpa00109ModellList_ = rpa00109Dao.selectPrescriptionCount(rpa00109Condition);
            if (rpa00109ModellList_ != null) {
                for (RPA00109Model rpa00109ModelModel : rpa00109ModellList_) {
                    RPA0010901Dto rpa0010901Dto = new RPA0010901Dto();
                    rpa0010901Dto.setPrescriptionYear(inForm_.getPrescriptionYear());
                    rpa0010901Dto.setJanCount(rpa00109ModelModel.getJanCount());
                    rpa0010901Dto.setFebCount(rpa00109ModelModel.getFebCount());
                    rpa0010901Dto.setMarCount(rpa00109ModelModel.getMarCount());
                    rpa0010901Dto.setFirstQuarterCount(rpa00109ModelModel.getFirstQuarterCount());
                    rpa0010901Dto.setAprCount(rpa00109ModelModel.getAprCount());
                    rpa0010901Dto.setMayCount(rpa00109ModelModel.getMayCount());
                    rpa0010901Dto.setJuneCount(rpa00109ModelModel.getJuneCount());
                    rpa0010901Dto.setSecondQuarterCount(rpa00109ModelModel.getSecondQuarterCount());
                    rpa0010901Dto.setJulyCount(rpa00109ModelModel.getJulyCount());
                    rpa0010901Dto.setAugCount(rpa00109ModelModel.getAugCount());
                    rpa0010901Dto.setSepCount(rpa00109ModelModel.getSepCount());
                    rpa0010901Dto.setThirdQuarterCount(rpa00109ModelModel.getThirdQuarterCount());
                    rpa0010901Dto.setOctCount(rpa00109ModelModel.getOctCount());
                    rpa0010901Dto.setNovCount(rpa00109ModelModel.getNovCount());
                    rpa0010901Dto.setDecCount(rpa00109ModelModel.getDecCount());
                    rpa0010901Dto.setFourthQuarterCount(rpa00109ModelModel.getFourthQuarterCount());
                    rpa0010901Dto.setYearCount(rpa00109ModelModel.getYearCount());
                    inForm_.getSubForm1().add(rpa0010901Dto);
                }
            }
        } else {
            for (CodeValueRecord record : inForm_.getPrescriptionYearDataSource()) {
                if (!CheckUtils.isEmpty(record.getRecordCode())) {
                    // 处方年度
                    rpa00109Condition.setPrescriptionYear(record.getRecordCode());
                    List<RPA00109Model> rpa00109ModellList_ = rpa00109Dao.selectPrescriptionCount(rpa00109Condition);
                    if (rpa00109ModellList_ != null) {
                        for (RPA00109Model rpa00109ModelModel : rpa00109ModellList_) {
                            RPA0010901Dto rpa0010901Dto = new RPA0010901Dto();
                            rpa0010901Dto.setPrescriptionYear(record.getRecordCode());
                            rpa0010901Dto.setJanCount(rpa00109ModelModel.getJanCount());
                            rpa0010901Dto.setFebCount(rpa00109ModelModel.getFebCount());
                            rpa0010901Dto.setMarCount(rpa00109ModelModel.getMarCount());
                            rpa0010901Dto.setFirstQuarterCount(rpa00109ModelModel.getFirstQuarterCount());
                            rpa0010901Dto.setAprCount(rpa00109ModelModel.getAprCount());
                            rpa0010901Dto.setMayCount(rpa00109ModelModel.getMayCount());
                            rpa0010901Dto.setJuneCount(rpa00109ModelModel.getJuneCount());
                            rpa0010901Dto.setSecondQuarterCount(rpa00109ModelModel.getSecondQuarterCount());
                            rpa0010901Dto.setJulyCount(rpa00109ModelModel.getJulyCount());
                            rpa0010901Dto.setAugCount(rpa00109ModelModel.getAugCount());
                            rpa0010901Dto.setSepCount(rpa00109ModelModel.getSepCount());
                            rpa0010901Dto.setThirdQuarterCount(rpa00109ModelModel.getThirdQuarterCount());
                            rpa0010901Dto.setOctCount(rpa00109ModelModel.getOctCount());
                            rpa0010901Dto.setNovCount(rpa00109ModelModel.getNovCount());
                            rpa0010901Dto.setDecCount(rpa00109ModelModel.getDecCount());
                            rpa0010901Dto.setFourthQuarterCount(rpa00109ModelModel.getFourthQuarterCount());
                            rpa0010901Dto.setYearCount(rpa00109ModelModel.getYearCount());
                            inForm_.getSubForm1().add(rpa0010901Dto);
                        }
                    }
                }
            }
        }

        // Excel文件创建以下
        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet = wb.createSheet("sheet");
        CellStyle titleCellStyle = wb.createCellStyle();
        titleCellStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
        titleCellStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
        titleCellStyle.setBorderBottom(CellStyle.BORDER_THIN);
        titleCellStyle.setBorderLeft(CellStyle.BORDER_THIN);
        titleCellStyle.setBorderRight(CellStyle.BORDER_THIN);
        titleCellStyle.setBorderTop(CellStyle.BORDER_THIN);
        // 设置背景色
        titleCellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        titleCellStyle.setFillForegroundColor(IndexedColors.YELLOW.getIndex());

        XSSFCell cell;
        CellRangeAddress region;
        final short borderMediumDashed = CellStyle.BORDER_THIN;
        // TITLE行
        // 第二行
        XSSFRow row = sheet.createRow(2);
        cell = row.createCell(2);
        cell.setCellValue("一月");
        cell.setCellStyle(titleCellStyle);
        cell = row.createCell(3);
        cell.setCellValue("二月");
        cell.setCellStyle(titleCellStyle);
        cell = row.createCell(4);
        cell.setCellValue("三月");
        cell.setCellStyle(titleCellStyle);
        cell = row.createCell(5);
        cell.setCellValue("小计");
        cell.setCellStyle(titleCellStyle);
        cell = row.createCell(6);
        cell.setCellValue("四月");
        cell.setCellStyle(titleCellStyle);
        cell = row.createCell(7);
        cell.setCellValue("五月");
        cell.setCellStyle(titleCellStyle);
        cell = row.createCell(8);
        cell.setCellValue("六月");
        cell.setCellStyle(titleCellStyle);
        cell = row.createCell(9);
        cell.setCellValue("小计");
        cell.setCellStyle(titleCellStyle);
        cell = row.createCell(10);
        cell.setCellValue("七月");
        cell.setCellStyle(titleCellStyle);
        cell = row.createCell(11);
        cell.setCellValue("八月");
        cell.setCellStyle(titleCellStyle);
        cell = row.createCell(12);
        cell.setCellValue("九月");
        cell.setCellStyle(titleCellStyle);
        cell = row.createCell(13);
        cell.setCellValue("小计");
        cell.setCellStyle(titleCellStyle);
        cell = row.createCell(14);
        cell.setCellValue("十月");
        cell.setCellStyle(titleCellStyle);
        cell = row.createCell(15);
        cell.setCellValue("十一月");
        cell.setCellStyle(titleCellStyle);
        cell = row.createCell(16);
        cell.setCellValue("十二月");
        cell.setCellStyle(titleCellStyle);
        cell = row.createCell(17);
        cell.setCellValue("小计");
        cell.setCellStyle(titleCellStyle);

        // 第一行
        row = sheet.createRow(1);
        cell = row.createCell(1);
        cell.setCellValue("年度");
        cell.setCellStyle(titleCellStyle);
        region = new CellRangeAddress(1, 2, 1, 1);
        sheet.addMergedRegion(region);
        // Set the border and border colors.
        RegionUtil.setBorderBottom(borderMediumDashed, region, sheet, wb);
        RegionUtil.setBorderTop(borderMediumDashed, region, sheet, wb);
        RegionUtil.setBorderLeft(borderMediumDashed, region, sheet, wb);
        RegionUtil.setBorderRight(borderMediumDashed, region, sheet, wb);

        cell = row.createCell(2);
        cell.setCellValue("第一季度");
        cell.setCellStyle(titleCellStyle);
        region = new CellRangeAddress(1, 1, 2, 5);
        sheet.addMergedRegion(region);
        // Set the border and border colors.
        RegionUtil.setBorderBottom(borderMediumDashed, region, sheet, wb);
        RegionUtil.setBorderTop(borderMediumDashed, region, sheet, wb);
        RegionUtil.setBorderLeft(borderMediumDashed, region, sheet, wb);
        RegionUtil.setBorderRight(borderMediumDashed, region, sheet, wb);

        cell = row.createCell(6);
        cell.setCellValue("第二季度");
        cell.setCellStyle(titleCellStyle);
        region = new CellRangeAddress(1, 1, 6, 9);
        sheet.addMergedRegion(region);
        // Set the border and border colors.
        RegionUtil.setBorderBottom(borderMediumDashed, region, sheet, wb);
        RegionUtil.setBorderTop(borderMediumDashed, region, sheet, wb);
        RegionUtil.setBorderLeft(borderMediumDashed, region, sheet, wb);
        RegionUtil.setBorderRight(borderMediumDashed, region, sheet, wb);

        cell = row.createCell(10);
        cell.setCellValue("第三季度");
        cell.setCellStyle(titleCellStyle);
        region = new CellRangeAddress(1, 1, 10, 13);
        sheet.addMergedRegion(region);
        // Set the border and border colors.
        RegionUtil.setBorderBottom(borderMediumDashed, region, sheet, wb);
        RegionUtil.setBorderTop(borderMediumDashed, region, sheet, wb);
        RegionUtil.setBorderLeft(borderMediumDashed, region, sheet, wb);
        RegionUtil.setBorderRight(borderMediumDashed, region, sheet, wb);

        cell = row.createCell(14);
        cell.setCellValue("第四季度");
        cell.setCellStyle(titleCellStyle);
        region = new CellRangeAddress(1, 1, 14, 17);
        sheet.addMergedRegion(region);
        // Set the border and border colors.
        RegionUtil.setBorderBottom(borderMediumDashed, region, sheet, wb);
        RegionUtil.setBorderTop(borderMediumDashed, region, sheet, wb);
        RegionUtil.setBorderLeft(borderMediumDashed, region, sheet, wb);
        RegionUtil.setBorderRight(borderMediumDashed, region, sheet, wb);

        cell = row.createCell(18);
        cell.setCellValue("年度总计");
        cell.setCellStyle(titleCellStyle);
        region = new CellRangeAddress(1, 2, 18, 18);
        sheet.addMergedRegion(region);
        // Set the border and border colors.
        RegionUtil.setBorderBottom(borderMediumDashed, region, sheet, wb);
        RegionUtil.setBorderTop(borderMediumDashed, region, sheet, wb);
        RegionUtil.setBorderLeft(borderMediumDashed, region, sheet, wb);
        RegionUtil.setBorderRight(borderMediumDashed, region, sheet, wb);

        // 数据单元格样式
        CellStyle yearCellStyle = wb.createCellStyle();
        yearCellStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
        yearCellStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
        yearCellStyle.setBorderBottom(CellStyle.BORDER_THIN);
        yearCellStyle.setBorderLeft(CellStyle.BORDER_THIN);
        yearCellStyle.setBorderRight(CellStyle.BORDER_THIN);
        yearCellStyle.setBorderTop(CellStyle.BORDER_THIN);

        CellStyle dataCellStyle = wb.createCellStyle();
        dataCellStyle.setAlignment(XSSFCellStyle.ALIGN_RIGHT);
        dataCellStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
        dataCellStyle.setBorderBottom(CellStyle.BORDER_THIN);
        dataCellStyle.setBorderLeft(CellStyle.BORDER_THIN);
        dataCellStyle.setBorderRight(CellStyle.BORDER_THIN);
        dataCellStyle.setBorderTop(CellStyle.BORDER_THIN);
        // 数据编辑
        for (int index_ = 0; index_ < inForm_.getSubForm1().size(); index_++) {
            row = sheet.createRow(3 + index_);
            RPA0010901Dto dataRecord_ = inForm_.getSubForm1().get(index_);

            cell = row.createCell(1);
            cell.setCellValue(StringUtils.defaultString(dataRecord_.getPrescriptionYear()));
            cell.setCellStyle(yearCellStyle);
            cell = row.createCell(2);
            cell.setCellValue(StringUtils.defaultString(dataRecord_.getJanCount()));
            cell.setCellStyle(dataCellStyle);
            cell = row.createCell(3);
            cell.setCellValue(StringUtils.defaultString(dataRecord_.getFebCount()));
            cell.setCellStyle(dataCellStyle);
            cell = row.createCell(4);
            cell.setCellValue(StringUtils.defaultString(dataRecord_.getMarCount()));
            cell.setCellStyle(dataCellStyle);
            cell = row.createCell(5);
            cell.setCellValue(StringUtils.defaultString(dataRecord_.getFirstQuarterCount()));
            cell.setCellStyle(dataCellStyle);
            cell = row.createCell(6);
            cell.setCellValue(StringUtils.defaultString(dataRecord_.getAprCount()));
            cell.setCellStyle(dataCellStyle);
            cell = row.createCell(7);
            cell.setCellValue(StringUtils.defaultString(dataRecord_.getMayCount()));
            cell.setCellStyle(dataCellStyle);
            cell = row.createCell(8);
            cell.setCellValue(StringUtils.defaultString(dataRecord_.getJuneCount()));
            cell.setCellStyle(dataCellStyle);
            cell = row.createCell(9);
            cell.setCellValue(StringUtils.defaultString(dataRecord_.getSecondQuarterCount()));
            cell.setCellStyle(dataCellStyle);
            cell = row.createCell(10);
            cell.setCellValue(StringUtils.defaultString(dataRecord_.getJulyCount()));
            cell.setCellStyle(dataCellStyle);
            cell = row.createCell(11);
            cell.setCellValue(StringUtils.defaultString(dataRecord_.getAugCount()));
            cell.setCellStyle(dataCellStyle);
            cell = row.createCell(12);
            cell.setCellValue(StringUtils.defaultString(dataRecord_.getSepCount()));
            cell.setCellStyle(dataCellStyle);
            cell = row.createCell(13);
            cell.setCellValue(StringUtils.defaultString(dataRecord_.getThirdQuarterCount()));
            cell.setCellStyle(dataCellStyle);
            cell = row.createCell(14);
            cell.setCellValue(StringUtils.defaultString(dataRecord_.getOctCount()));
            cell.setCellStyle(dataCellStyle);
            cell = row.createCell(15);
            cell.setCellValue(StringUtils.defaultString(dataRecord_.getNovCount()));
            cell.setCellStyle(dataCellStyle);
            cell = row.createCell(16);
            cell.setCellValue(StringUtils.defaultString(dataRecord_.getDecCount()));
            cell.setCellStyle(dataCellStyle);
            cell = row.createCell(17);
            cell.setCellValue(StringUtils.defaultString(dataRecord_.getFourthQuarterCount()));
            cell.setCellStyle(dataCellStyle);
            cell = row.createCell(18);
            cell.setCellValue(StringUtils.defaultString(dataRecord_.getYearCount()));
            cell.setCellStyle(dataCellStyle);

        }

        // 下载ファイル名
        StringBuffer fileName_ = new StringBuffer();
        fileName_ = fileName_.append("处方统计信息");
        fileName_.append("_");
        fileName_.append(
            DateUtils.format(TimestampUtils.getSysTimestamp(), LeshanConstantsIF.DATE_FORMAT_YYYYMMDDHHMMSSSSS));
        fileName_.append(".xlsx");
        // 一時のファイル保存
        FileUtils.writeExelFile(wb, fileName_.toString(),
            ServiceUtils.getAbsolutePath(StandardConstantsIF.KYOTU_EXCEL_FILE_TMP_SAVE_FOLDER));
        this.connectDownload(StandardConstantsIF.KYOTU_EXCEL_FILE_TMP_SAVE_FOLDER, fileName_.toString(),
            fileName_.toString());

        // 出力情報設定
        return this.getEventResult(inForm_);
    }

    /**
     * 处方统计信息导出DAO的设定
     * 
     * @param _rpa00109Dao 处方统计信息导出DAO
     */
    public void setRpa00109Dao(RPA00109Dao _rpa00109Dao) {
        this.rpa00109Dao = _rpa00109Dao;
    }

}
