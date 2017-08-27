<!DOCTYPE html >
<%--
/*
 * Copyright(c) 2016 
 */
/*
 * 初诊信息添加
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
<%-- <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/pages/css/schm/rpc00101.css" /> --%>
<script type="text/javascript" src="<%=request.getContextPath()%>/pages/csjs/rpc/rpc00101.js"></script>
</head>
<body>
    <s:form id="rpc00101Form" name="rpc00101Form" action="rpc00101Action" method="post" validate="true" theme="simple">
        <div id="contents">
            <div id="two_main_colum">

                <h:normaltitleinbtn value="rpc00101" />
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
                            <h:readonlyfield id="caseNumber" name="rpc00101Form.caseNumber"></h:readonlyfield>
                        </td>
                        <td style="text-align: right;">就诊日期：</td>
                        <td>
                            <h:readonlyfield id="medicalDate" name="rpc00101Form.medicalDate"></h:readonlyfield>
                        </td>
                    </tr>
                </table>
                <table class="entry_content_table gamen_layout1">
                    <col style="width: 100px;" />
                    <col style="width: 35px;" />
                    <col style="width: 210px;" />
                    <col style="width: 60px;" />
                    <col style="width: 75px;" />
                    <col style="width: 60px;" />
                    <col style="width: auto;" />
                    <tr>
                        <!-- 姓名 -->
                        <td class="entry_name" style="width: 100px;">姓名：</td>
                        <td style="width: 35px;">
                            <h:mustentryicon />
                        </td>
                        <td>
                            <s:textfield id="patientName" name="rpc00101Form.patientName" cssStyle="width:173px;" cssErrorStyle="width:173px;" maxlength="15" cssErrorClass="error" cssClass="mustdata"></s:textfield>
                            <img onclick="doSearchPatient();" src="<%=request.getContextPath()%>/pages/img/icon_search.png" class="searchImg" />
                        </td>
                        <!-- 民族 -->
                        <td style="text-align: right; padding-top: 5px;">民族：</td>
                        <td>
                            <s:textfield id="nation" name="rpc00101Form.nation" style="width:75px;" maxlength="5" cssErrorClass="error" />
                        </td>
                        <!-- 婚况 -->
                        <td style="text-align: right; padding-top: 5px;">婚况：</td>
                        <td>
                            <s:textfield id="maritalStatus" name="rpc00101Form.maritalStatus" style="width:35px;" maxlength="2" cssErrorClass="error" />
                        </td>
                    </tr>
                </table>
                <table class="entry_content_table gamen_layout1">
                    <col style="width: 100px;" />
                    <col style="width: 35px;" />
                    <col style="width: 120px;" />
                    <col style="width: 80px;" />
                    <col style="width: 170px;" />
                    <col style="width: 60px;" />
                    <col style="width: 60px;" />
                    <col style="width: auto;" />
                    <tr>
                        <!-- 性别 -->
                        <td class="entry_name" style="width: 100px;">性别：</td>
                        <td style="width: 35px;"></td>
                        <td>
                            <g:radioList name="rpc00101Form.sexId" list="rpc00101Form.sexDataSource" listKey="recordCode" listValue="recordValue" cssErrorClass="error" />
                        </td>
                        <!-- 出生日期 -->
                        <td style="text-align: right; width: 60px; padding-top: 5px;">出生日期：</td>
                        <td>
                            <h:calendar form="rpc00101Form" id="birthday" dateValue="birthday"></h:calendar>
                        </td>
                        <!-- 年齢 -->
                        <td style="text-align: right; padding-top: 5px;">年龄：</td>
                        <td>
                            <h:readonlyfield id="birthday_age" name="rpc00101Form.age" style="text-align: right;width:20px;" />
                            岁
                        </td>
                        <td>
                            <a href="javascript:void(0);" onclick="getAge('birthday')" class="aid_btn" style="width: auto;">
                                <span>自动计算</span>
                            </a>
                        </td>
                    </tr>
                </table>
                <table class="entry_content_table gamen_layout1">
                    <tr>
                        <!-- 出生时间 -->
                        <td class="entry_name" style="width: 100px;">出生时间：</td>
                        <td style="width: 35px;"></td>
                        <td style="width: 130px;">
                            <h:time form="rpc00101Form" id="timeOfBirth" timeValue="timeOfBirth"></h:time>
                        </td>
                        <!-- 阴历生日 -->
                        <td style="text-align: right; width: 70px; padding-top: 5px;">阴历生日：</td>
                        <td style="width: 170px;">
                            <h:calendar form="rpc00101Form" id="lunarBirthday" dateValue="lunarBirthday"></h:calendar>
                        </td>
                        <!-- 属相 -->
                        <td style="text-align: right; width: 60px; padding-top: 5px;">属相：</td>
                        <td>
                            <s:textfield id="zodiac" name="rpc00101Form.zodiac" style="width:35px;" maxlength="1" cssErrorClass="error" />
                        </td>
                    </tr>
                </table>
                <table class="entry_content_table gamen_layout1">
                    <tr>
                        <!-- 出生地 -->
                        <td class="entry_name" style="width: 100px;">出生地：</td>
                        <td style="width: 35px;"></td>
                        <td>
                            <s:textfield id="placeOfBirth" name="rpc00101Form.placeOfBirth" style="width:400px;" maxlength="400"></s:textfield>
                        </td>
                    </tr>
                    <tr>
                        <!-- 居住地 -->
                        <td class="entry_name" style="width: 100px;">居住地：</td>
                        <td style="width: 35px;"></td>
                        <td>
                            <s:textfield id="addr" name="rpc00101Form.addr" style="width:400px;" maxlength="400"></s:textfield>
                        </td>
                    </tr>
                    <tr>
                        <!-- 手机号码 -->
                        <td class="entry_name" style="width: 100px;">手机号码：</td>
                        <td style="width: 35px;">
                            <h:mustentryicon />
                        </td>
                        <td>
                            <s:textfield id="ceelNumber" name="rpc00101Form.ceelNumber" cssStyle="width:160px;" cssErrorStyle="width:160px;" maxlength="11" cssErrorClass="error" cssClass="mustdata"></s:textfield>
                        </td>
                    </tr>
                    <tr>
                        <!-- 单位 -->
                        <td class="entry_name" style="width: 100px;">单位：</td>
                        <td style="width: 35px;"></td>
                        <td>
                            <s:textfield id="company" name="rpc00101Form.company" style="width:400px;" maxlength="400"></s:textfield>
                        </td>
                    </tr>
                    <tr>
                        <!-- 职业 -->
                        <td class="entry_name" style="width: 100px;">职业：</td>
                        <td style="width: 35px;"></td>
                        <td>
                            <s:textfield id="profession" name="rpc00101Form.profession" style="width:400px;" maxlength="100"></s:textfield>
                        </td>
                    </tr>
                </table>

                <table class="entry_content_table gamen_layout1">
                    <tr>
                        <!-- 主诉 -->
                        <td class="entry_name" style="width: 100px;">主诉：</td>
                        <td style="width: 35px;"></td>
                        <td>
                            <s:textfield id="chiefComplaint" name="rpc00101Form.chiefComplaint" style="width:400px;" maxlength="400"></s:textfield>
                        </td>
                        <!-- 发病节气 -->
                        <td>发病节气：</td>
                        <td>
                            <s:textfield id="onsetSolarTerms" name="rpc00101Form.onsetSolarTerms" cssStyle="width:100px;" cssErrorStyle="width:100px;" maxlength="10" cssErrorClass="error"></s:textfield>
                        </td>
                    </tr>
                </table>

                <table class="entry_content_table gamen_layout1">
                    <tr>
                        <!-- 现病史 -->
                        <td class="entry_name" style="width: 100px;">现病史：</td>
                        <td style="width: 35px;"></td>
                        <td>
                            <s:textfield name="rpc00101Form.presentHistory" style="width:500px;" maxlength="120"></s:textfield>
                        </td>
                    </tr>
                    <tr>
                        <!-- 既往史 -->
                        <td class="entry_name" style="width: 100px;">既往史：</td>
                        <td style="width: 35px;"></td>
                        <td>
                            <s:textfield name="rpc00101Form.previousHistory" style="width:500px;" maxlength="120"></s:textfield>
                        </td>
                    </tr>
                </table>

                <table class="entry_content_table gamen_layout1">
                    <tr>
                        <!-- 家族史 -->
                        <td class="entry_name" style="width: 100px;">家族史：</td>
                        <td style="width: 35px;"></td>
                        <td>
                            <s:textfield name="rpc00101Form.familyHistory" cssStyle="width:200px;" cssErrorStyle="width:200px;" maxlength="120" cssErrorClass="error"></s:textfield>
                        </td>
                        <!-- 过敏史 -->
                        <td>过敏史：</td>
                        <td>
                            <s:textfield name="rpc00101Form.allergyHistory" cssStyle="width:200px;" cssErrorStyle="width:200px;" maxlength="120" cssErrorClass="error"></s:textfield>
                        </td>
                    </tr>
                </table>

                <table class="entry_content_table gamen_layout1">
                    <tr>
                        <!-- 其他情况 -->
                        <td class="entry_name" style="width: 100px;">其他情况：</td>
                        <td style="width: 35px;"></td>
                        <td>
                            <s:textfield name="rpc00101Form.otherCases" style="width:500px;" maxlength="120"></s:textfield>
                        </td>
                    </tr>
                </table>

                <table class="entry_content_table gamen_layout1">
                    <tr>
                        <!-- 四诊摘要 -->
                        <td class="entry_name" style="width: 100px;" rowspan="2">四诊摘要：</td>
                        <td style="width: 35px;" rowspan="2"></td>
                        <td colspan="4">
                            <s:textfield name="rpc00101Form.fourDiagnosis" cssStyle="width:500px;" cssErrorStyle="width:500px;" maxlength="120" cssErrorClass="error"></s:textfield>
                        </td>
                    </tr>
                    <tr>
                        <!-- 脉诊 -->
                        <td>舌诊：</td>
                        <td>
                            <s:textfield name="rpc00101Form.tongueInspection" cssStyle="width:200px;" cssErrorStyle="width:200px;" maxlength="120" cssErrorClass="error"></s:textfield>
                        </td>
                        <!-- 脉诊 -->
                        <td>脉诊：</td>
                        <td>
                            <s:textfield name="rpc00101Form.pulseTaking" cssStyle="width:200px;" cssErrorStyle="width:200px;" maxlength="120" cssErrorClass="error"></s:textfield>
                        </td>
                    </tr>
                </table>

                <table class="entry_content_table gamen_layout1">
                    <tr>
                        <!-- 体格检查 -->
                        <td class="entry_name" style="width: 100px;" rowspan="2">体格检查：</td>
                        <td style="width: 35px;" rowspan="2"></td>
                        <td style="width: 60px;">T℃：</td>
                        <td style="width: 50px;">
                            <s:textfield name="rpc00101Form.temperature" cssStyle="width:50px;" cssErrorStyle="width:50px;" maxlength="5" cssErrorClass="error"></s:textfield>
                        </td>
                        <td style="width: 60px;">R次/分：</td>
                        <td style="width: 50px;">
                            <s:textfield name="rpc00101Form.breath" cssStyle="width:50px;" cssErrorStyle="width:50px;" maxlength="5" cssErrorClass="error"></s:textfield>
                        </td>
                        <td style="width: 60px;">P次/分：</td>
                        <td style="width: 50px;">
                            <s:textfield name="rpc00101Form.pulse" cssStyle="width:50px;" cssErrorStyle="width:50px;" maxlength="5" cssErrorClass="error"></s:textfield>
                        </td>
                        <td style="width: 60px;">BP/mmHg：</td>
                        <td>
                            <s:textfield name="rpc00101Form.bloodPressure" cssStyle="width:100px;" cssErrorStyle="width:100px;" maxlength="10" cssErrorClass="error"></s:textfield>
                        </td>
                    </tr>
                    <tr>
                        <td style="width: 60px;">身高cm：</td>
                        <td style="width: 50px;">
                            <s:textfield name="rpc00101Form.height" cssStyle="width:50px;" cssErrorStyle="width:50px;" maxlength="5" cssErrorClass="error"></s:textfield>
                        </td>
                        <td style="width: 60px;">体重kg：</td>
                        <td style="width: 50px;">
                            <s:textfield name="rpc00101Form.weight" cssStyle="width:50px;" cssErrorStyle="width:50px;" maxlength="5" cssErrorClass="error"></s:textfield>
                        </td>
                    </tr>
                </table>

                <table class="entry_content_table gamen_layout1">
                    <tr>
                        <!-- 辅助检查 -->
                        <td class="entry_name" style="width: 100px;">辅助检查：</td>
                        <td style="width: 35px;"></td>
                        <td>
                            <s:textfield name="rpc00101Form.assistantExamination" style="width:500px;" maxlength="120"></s:textfield>
                        </td>
                    </tr>
                    <tr>
                        <!-- 诊断 -->
                        <td class="entry_name" style="width: 100px;">诊断：</td>
                        <td style="width: 35px;"></td>
                        <td>
                            <s:textfield name="rpc00101Form.diagnosis" style="width:500px;"></s:textfield>
                        </td>
                    </tr>
                </table>

                <table class="entry_content_table gamen_layout1">
                    <tr>
                        <!-- 处置 -->
                        <td class="entry_name" style="width: 100px;" rowspan="2">处置：</td>
                        <td style="width: 35px;" rowspan="2"></td>
                        <td colspan="2">
                            <s:textarea name="rpc00101Form.disposition" rows="5" cols="35" cssStyle="font-size:24px;" cssErrorStyle="font-size:24px;" cssErrorClass="error" maxlength="500"></s:textarea>
                        </td>
                    </tr>
                    <tr>
                        <td style="width: 350px; text-align: right;">医师：</td>
                        <td>
                            <h:readonlyfield id="physician" name="rpc00101Form.physician" style="width: 100px;"></h:readonlyfield>
                        </td>
                    </tr>
                </table>

                <table class="entry_content_table gamen_layout1">
                    <tr>
                        <!-- 备注 -->
                        <td class="entry_name" style="width: 100px;" rowspan="2">备注：</td>
                        <td style="width: 35px;" rowspan="2"></td>
                        <td colspan="4">
                            <s:textfield name="rpc00101Form.notes" style="width:500px;" maxlength="400"></s:textfield>
                        </td>
                    </tr>
                    <tr>
                        <!-- 病例记录人 -->
                        <td style="width: 90px; text-align: right;">病例记录人：</td>
                        <td>
                            <s:textfield name="rpc00101Form.caseRecorder" cssStyle="width:100px;" cssErrorStyle="width:100px;" maxlength="20" cssErrorClass="error"></s:textfield>
                        </td>
                        <!-- 主诊助理 -->
                        <td style="width: 90px; text-align: right;">主诊助理：</td>
                        <td>
                            <s:textfield name="rpc00101Form.attendingAssistant" cssStyle="width:100px;" cssErrorStyle="width:100px;" maxlength="20" cssErrorClass="error"></s:textfield>
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
                                        <td>&nbsp;</td>
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
                                        <!-- 登録 -->
                                        <td>
                                            <g:submit method="doEntry" id="touroku" cssClass="entry_mainbtn" onclick="return doEntry($(this));" value="" />
                                        </td>
                                        <td></td>
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