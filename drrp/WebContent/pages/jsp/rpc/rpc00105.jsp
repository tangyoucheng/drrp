<!DOCTYPE html >
<%--
/*
 * Copyright(c) 2016 
 */
/*
 * 复诊信息添加
 *
 *
 * 新規作成
 * DATE: 2016.11.07 NAME: tyc
 */
--%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/pages/jsp/common/taglibs.jsp"%>
<html>
<head>
<h:head></h:head>
<%-- <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/pages/css/schm/rpc00105.css" /> --%>
<script type="text/javascript" src="<%=request.getContextPath()%>/pages/csjs/rpc/rpc00105.js"></script>
</head>
<body>
    <s:form id="rpc00105Form" name="rpc00105Form" action="rpc00105Action" method="post" validate="true" theme="simple">
        <input type="hidden" name="rpc00105Form.ownershipFlag" value="${rpc00105Form.ownershipFlag }">
        <input type="hidden" name="rpc00105Form.recordId" value="${rpc00105Form.recordId }">
        <input type="hidden" name="rpc00105Form.lastUpdateDate" value="${rpc00105Form.lastUpdateDate }">
        <input type="hidden" id="birthday" name="rpc00105Form.birthday" value="${rpc00105Form.birthday }">
        <div id="contents">
            <div id="two_main_colum">

                <h:normaltitleinbtn value="rpc00105" />
                <h:messege />
                <table class="entry_content_table ">
                    <col style="width: 100px;" />
                    <col style="width: 35px;" />
                    <col style="width: 210px;" />
                    <col style="width: 100px;" />
                    <col style="width: auto;" />
                    <tr>
                        <td class="entry_name" style="width: 100px;">病例号码：</td>
                        <td style="width: 35px;"></td>
                        <td>
                            <h:readonlyfield id="caseNumber" name="rpc00105Form.caseNumber"></h:readonlyfield>
                        </td>
                        <td style="text-align: right;">就诊日期：</td>
                        <td>
                            <h:readonlyfield id="medicalDate" name="rpc00105Form.medicalDate"></h:readonlyfield>
                        </td>
                    </tr>
                </table>
                <table class="entry_content_table gamen_layout1">
                    <col style="width: 100px;" />
                    <col style="width: 35px;" />
                    <col style="width: 210px;" />
                    <col style="width: 60px;" />
                    <col style="width: 60px;" />
                    <col style="width: 60px;" />
                    <col style="width: auto;" />
                    <tr>
                        <!-- 姓名 -->
                        <td class="entry_name" style="width: 100px;">姓名：</td>
                        <td style="width: 35px;">
                            <h:mustentryicon />
                        </td>
                        <td>
                            <h:readonlyfield id="patientName" name="rpc00105Form.patientName" />
                        </td>
                        <!-- 性别 -->
                        <td style="text-align: right; padding-top: 5px;">性别：</td>
                        <td>
                            <h:readonlyfield id="sex" name="rpc00105Form.sex" style="width:30px;"></h:readonlyfield>
                        </td>
                        <!-- 年齢 -->
                        <td style="text-align: right; padding-top: 5px;">年龄：</td>
                        <td>
                            <h:readonlyfield id="birthday_age" name="rpc00105Form.age" style="text-align: right;width:20px;" />
                            岁
                        </td>
                    </tr>
                </table>

                <table class="entry_content_table gamen_layout1">
                    <tr>
                        <!-- 主诉 -->
                        <td class="entry_name" style="width: 100px;">主诉：</td>
                        <td style="width: 35px;"></td>
                        <td>
                            <s:textfield id="chiefComplaint" name="rpc00105Form.chiefComplaint" style="width:500px;" maxlength="400"></s:textfield>
                        </td>
                    </tr>
                    <tr>
                        <!-- 回访记录 -->
                        <td class="entry_name" style="width: 100px;">回访记录：</td>
                        <td style="width: 35px;"></td>
                        <td>
                            <s:textfield id="returnVisit" name="rpc00105Form.returnVisit" style="width:500px;" maxlength="400"></s:textfield>
                        </td>
                    </tr>
                    <tr>
                        <!-- 疗效 -->
                        <td class="entry_name" style="width: 100px;">疗效：</td>
                        <td style="width: 35px;"></td>
                        <td>
                            <s:textarea name="rpc00105Form.effect" rows="5" cols="35" cssStyle="font-size:24px;" cssErrorStyle="font-size:24px;" cssErrorClass="error" maxlength="500"></s:textarea>
                        </td>
                    </tr>
                </table>

                <table class="entry_content_table gamen_layout1">
                    <tr>
                        <!-- 四诊摘要 -->
                        <td class="entry_name" style="width: 100px;" rowspan="2">四诊摘要：</td>
                        <td style="width: 35px;" rowspan="2"></td>
                        <td colspan="4">
                            <s:textfield name="rpc00105Form.fourDiagnosis" cssStyle="width:500px;" cssErrorStyle="width:500px;" maxlength="120" cssErrorClass="error"></s:textfield>
                        </td>
                    </tr>
                    <tr>
                        <!-- 脉诊 -->
                        <td>舌诊：</td>
                        <td>
                            <s:textfield name="rpc00105Form.tongueInspection" cssStyle="width:200px;" cssErrorStyle="width:200px;" maxlength="120" cssErrorClass="error"></s:textfield>
                        </td>
                        <!-- 脉诊 -->
                        <td>脉诊：</td>
                        <td>
                            <s:textfield name="rpc00105Form.pulseTaking" cssStyle="width:200px;" cssErrorStyle="width:200px;" maxlength="120" cssErrorClass="error"></s:textfield>
                        </td>
                    </tr>
                </table>

                <table class="entry_content_table gamen_layout1">
                    <tr>
                        <!-- 体格检查 -->
                        <td class="entry_name" style="width: 100px;">体格检查：</td>
                        <td style="width: 35px;"></td>
                        <td style="width: 60px;">T℃：</td>
                        <td style="width: 50px;">
                            <s:textfield name="rpc00105Form.temperature" cssStyle="width:50px;" cssErrorStyle="width:50px;" maxlength="5" cssErrorClass="error"></s:textfield>
                        </td>
                        <td style="width: 60px;">R次/分：</td>
                        <td style="width: 50px;">
                            <s:textfield name="rpc00105Form.breath" cssStyle="width:50px;" cssErrorStyle="width:50px;" maxlength="5" cssErrorClass="error"></s:textfield>
                        </td>
                        <td style="width: 60px;">P次/分：</td>
                        <td style="width: 50px;">
                            <s:textfield name="rpc00105Form.pulse" cssStyle="width:50px;" cssErrorStyle="width:50px;" maxlength="5" cssErrorClass="error"></s:textfield>
                        </td>
                        <td style="width: 60px;">BP/mmHg：</td>
                        <td>
                            <s:textfield name="rpc00105Form.bloodPressure" cssStyle="width:100px;" cssErrorStyle="width:100px;" maxlength="10" cssErrorClass="error"></s:textfield>
                        </td>
                    </tr>
                </table>

                <table class="entry_content_table gamen_layout1">
                    <tr>
                        <!-- 诊断 -->
                        <td class="entry_name" style="width: 100px;">诊断：</td>
                        <td style="width: 35px;"></td>
                        <td>
                            <s:textfield name="rpc00105Form.diagnosis" style="width:500px;"></s:textfield>
                        </td>
                    </tr>
                </table>

                <table class="entry_content_table gamen_layout1">
                    <tr>
                        <!-- 处置 -->
                        <td class="entry_name" style="width: 100px;" rowspan="2">处置：</td>
                        <td style="width: 35px;" rowspan="2"></td>
                        <td colspan="2">
                            <s:textarea name="rpc00105Form.disposition" rows="5" cols="35" cssStyle="font-size:24px;" cssErrorStyle="font-size:24px;" cssErrorClass="error" maxlength="500"></s:textarea>
                        </td>
                    </tr>
                    <tr>
                        <td style="width: 350px; text-align: right;">医师：</td>
                        <td>
                            <h:readonlyfield id="physician" name="rpc00105Form.physician" style="width: 100px;"></h:readonlyfield>
                        </td>
                    </tr>
                </table>

                <table class="entry_content_table gamen_layout1">
                    <tr>
                        <!-- 备注 -->
                        <td class="entry_name" style="width: 100px;" rowspan="2">备注：</td>
                        <td style="width: 35px;" rowspan="2"></td>
                        <td colspan="4">
                            <s:textfield name="rpc00105Form.notes" style="width:500px;" maxlength="400"></s:textfield>
                        </td>
                    </tr>
                    <tr>
                        <!-- 病例记录人 -->
                        <td style="width: 90px; text-align: right;">病例记录人：</td>
                        <td>
                            <s:textfield name="rpc00105Form.caseRecorder" cssStyle="width:100px;" cssErrorStyle="width:100px;" maxlength="20" cssErrorClass="error"></s:textfield>
                        </td>
                        <!-- 主诊助理 -->
                        <td style="width: 90px; text-align: right;">主诊助理：</td>
                        <td>
                            <s:textfield name="rpc00105Form.attendingAssistant" cssStyle="width:100px;" cssErrorStyle="width:100px;" maxlength="20" cssErrorClass="error"></s:textfield>
                        </td>
                    </tr>
                </table>

                <div class="guide_btn_area">
                    <table class="guide_btn_table" style="width: 100%;">
                        <col style="width: 25%;" />
                        <col style="width: 50%;" />
                        <col style="width: 25%;" />
                        <tr>
                            <td>
                                <table style="width: 100%;">
                                    <col style="width: 50%;" />
                                    <col style="width: 50%;" />
                                    <tr>
                                        <td>
                                            <g:submit action="rpc00102Action" method="doBack" cssClass="back_mainbtn" />
                                        </td>
                                        <td>&nbsp;</td>
                                    </tr>
                                </table>
                            </td>
                            <!-- 登録 -->
                            <td>
                                <table style="width: 100%;">
                                    <col style="width: 50%;" />
                                    <col style="width: 50%;" />
                                    <tr>
                                        <c:if test="${ rpc00105Form.ownershipFlag == 'T' }">
                                            <!-- 登録 -->
                                            <td>
                                                <g:submit method="doUpdate" id="touroku" cssClass="edit_mainbtn" onclick="return doUpdate($(this));" value="" />
                                            </td>
                                            <td>
                                                <g:submit method="doDelete" id="delete" cssClass="delete_mainbtn" onclick="return doDelete($(this));" value="" />
                                            </td>
                                        </c:if>
                                        <c:if test="${ rpc00105Form.ownershipFlag != 'T' }">
                                            <td>&nbsp;</td>
                                            <td>&nbsp;</td>
                                        </c:if>
                                    </tr>
                                </table>
                            </td>

                            <td>
                                <table style="width: 100%;">
                                    <col style="width: 50%;" />
                                    <col style="width: 50%;" />
                                    <tr>
                                        <td>&nbsp;</td>
                                        <td>&nbsp;</td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                    </table>
                </div>
            </div>
        </div>
    </s:form>
    <g:popuperror />
</body>
</html>