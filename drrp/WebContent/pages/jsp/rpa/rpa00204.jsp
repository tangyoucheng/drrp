<!DOCTYPE html >
<%--
/*
 * Copyright(c) 2016 
 */
/*
 * 患者信息一览弹出画面
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
<%-- <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/pages/css/schm/rpa00204.css" /> --%>
<script type="text/javascript" src="<%=request.getContextPath()%>/pages/csjs/rpa/rpa00204.js"></script>
</head>
<body>
    <s:form id="rpa00204Form" name="rpa00204Form" action="rpa00204Action" method="post" validate="true" theme="simple">
        <input type="hidden" name="rpa00204Form.pageType" value="${rpa00204Form.pageType }">
        <div id="contents">
            <div id="two_main_colum">

                <h:normaltitleinbtn value="rpa00204" />
                <h:messege />
                <table class="entry_content_table input_condition">
                    <col style="width: 80px;" />
                    <col style="width: 90px;" />
                    <col style="width: 80px;" />
                    <col style="width: 110px;" />
                    <col style="width: 80px;" />
                    <col style="width: auto;" />
                    <tr>
                        <td class="entry_name" style="width: 80px;">用户名：</td>
                        <td>
                            <s:textfield id="userName" name="rpa00204Form.userName" cssErrorClass="error" cssStyle="width:80px;" maxlength="50"></s:textfield>
                        </td>
                        <td class="entry_name" style="width: 80px;">手机号码：</td>
                        <td>
                            <s:textfield id="ceelNumber" name="rpa00204Form.ceelNumber" cssErrorClass="error" cssStyle="width:100px;" maxlength="50" showPassword="true"></s:textfield>
                        </td>
                        <td class="entry_name" style="width: 80px;">用户ID：</td>
                        <td>
                            <s:textfield id="ceelNumber" name="rpa00204Form.userId" cssErrorClass="error" cssStyle="width:130px;" maxlength="50" showPassword="true"></s:textfield>
                        </td>
                        <td>
                            <A class="aid_btn" id=rpa00204Action_doSearch_ onclick="return submitUrl(function() {doClearWaterMark();},$(this));"
                                href="<%=request.getContextPath()%>/rpa00204Action!doSearch.do">
                                <span>呼出</span>
                            </A>
                        </td>
                    </tr>
                </table>

                <s:if test="rpa00204Form.subForm1.size > 0">
                    <table style="width: 100%;">
                        <tr>
                            <td>&nbsp;</td>
                        </tr>
                        <tr>
                            <td class="font_bold">检索结果一览</td>
                        </tr>
                    </table>
                    <div style="width: 730px;">
                        <table id="tablecloth_row1" class="row_table" style="width: 720px; margin-left: 0px">
                            <col width="5%" />
                            <col width="20%" />
                            <col width="20%" />
                            <col width="20%" />
                            <col width="auto" />
                            <tr>
                                <th>NO</th>
                                <th>用户名</th>
                                <th>手机号码</th>
                                <th>座机号码</th>
                                <th>上次处方时间</th>
                            </tr>
                            <s:iterator status="listData" value="rpa00204Form.subForm1">
                                <tr id="patientTr_<g:property value="#listData.index"/>" onclick="setSelectedTrColor(this);">
                                    <!-- NO  -->
                                    <td style="text-align: center;">
                                        <g:property value="rpa00204Form.pageStartRowNo + #listData.index + 1" />
                                        <s:hidden id="hidUserId_%{#listData.index}" name="rpa00204Form.subForm1[%{#listData.index}].userId" />
                                        <s:hidden id="hidUserName_%{#listData.index}" name="rpa00204Form.subForm1[%{#listData.index}].userName" />
                                        <s:hidden id="hidSexId_%{#listData.index}" name="rpa00204Form.subForm1[%{#listData.index}].sexId" />
                                        <s:hidden id="hidBirthday_%{#listData.index}" name="rpa00204Form.subForm1[%{#listData.index}].birthday" />
                                        <s:hidden id="hidPhoneNumber_%{#listData.index}" name="rpa00204Form.subForm1[%{#listData.index}].phoneNumber" />
                                        <s:hidden id="hidCeelNumber_%{#listData.index}" name="rpa00204Form.subForm1[%{#listData.index}].ceelNumber" />
                                        <s:hidden id="hidIdNumber_%{#listData.index}" name="rpa00204Form.subForm1[%{#listData.index}].idNumber" />
                                        <s:hidden id="hidAddr_%{#listData.index}" name="rpa00204Form.subForm1[%{#listData.index}].addr" />
                                        <s:hidden id="hidPrescriptionType_%{#listData.index}" name="rpa00204Form.subForm1[%{#listData.index}].prescriptionType" />
                                        <s:hidden id="hidContents_%{#listData.index}" name="rpa00204Form.subForm1[%{#listData.index}].contents" />
                                        <s:hidden id="hidFileContents_%{#listData.index}" name="rpa00204Form.subForm1[%{#listData.index}].fileContents" />
                                        <s:hidden id="hidPrice_%{#listData.index}" name="rpa00204Form.subForm1[%{#listData.index}].price" />
                                    </td>
                                    <!-- 用户名  -->
                                    <td title="<s:property value="userName" /> " class="txt_overflow">
                                        <s:property value="userName" />
                                        <s:hidden name="rpa00204Form.subForm1[%{#listData.index}].userName" />
                                    </td>
                                    <!-- 手机号码  -->
                                    <td title="<s:property value="ceelNumber" /> " class="txt_overflow">
                                        <s:property value="ceelNumber" />
                                        <s:hidden name="rpa00204Form.subForm1[%{#listData.index}].ceelNumber" />
                                    </td>
                                    <!-- 座机号码  -->
                                    <td title="<s:property value="phoneNumber" /> " class="txt_overflow">
                                        <s:property value="phoneNumber" />
                                        <s:hidden name="rpa00204Form.subForm1[%{#listData.index}].phoneNumber" />
                                    </td>
                                    <!-- 上次处方时间  -->
                                    <td title="<s:property value="prescriptionCreateDate" /> " class="txt_overflow">
                                        <s:property value="prescriptionCreateDate" />
                                        <s:hidden name="rpa00204Form.subForm1[%{#listData.index}].prescriptionCreateDate" />
                                    </td>
                                </tr>
                            </s:iterator>
                        </table>
                    </div>
                    <table width="100%">
                        <tr>
                            <td>
                                <div class="in_table_number">
                                    <g:pager formId="rpa00204Form" formAction="rpa/rpa00204Action!doPage.do" uniqueFlag="true" />
                                </div>
                            </td>
                        </tr>
                    </table>
                </s:if>

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
                                        <td style="text-align: left;">&nbsp;</td>
                                        <td>&nbsp;</td>
                                    </tr>
                                </table>
                            </td>

                            <td>
                                <table style="width: 100%;">
                                    <tr>
                                        <td>
                                            <s:if test="rpa00204Form.subForm1.size > 0">
                                                <g:submit cssClass="select_mainbtn" value="" title="選択" onclick="return setSelectedData();" />
                                            </s:if>
                                        </td>
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