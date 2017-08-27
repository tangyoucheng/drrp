<!DOCTYPE html >
<%--
/*
 * Copyright(c) 2016 
 */
/*
 * 门诊信息一览
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
<%-- <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/pages/css/schm/rpc00102.css" /> --%>
<script type="text/javascript" src="<%=request.getContextPath()%>/pages/csjs/rpc/rpc00102.js"></script>
</head>
<body>
    <s:form id="rpc00102Form" name="rpc00102Form" action="rpc00102Action" method="post" validate="true" theme="simple">
        <input type="hidden" name="rpc00102Form.pageType" value="${rpc00102Form.pageType }">
        <div id="contents">
            <div id="two_main_colum">

                <h:normaltitleinbtn value="rpc00102" />
                <h:messege />
                <table class="entry_content_table input_condition">
                    <col style="width: 80px;" />
                    <col style="width: 35px;" />
                    <col style="width: 186px;" />
                    <col style="width: 120px;" />
                    <col style="width: 35px;" />
                    <col style="width: auto;" />
                    <tr>
                        <td class="entry_name" style="width: 80px;">姓名：</td>
                        <td></td>
                        <td>
                            <s:textfield id="patientName" name="rpc00102Form.patientName" cssErrorClass="error" maxlength="50"></s:textfield>
                        </td>
                        <td class="entry_name" style="width: 80px;">门诊类型：</td>
                        <td></td>
                        <td>
                            <g:radioList name="rpc00102Form.outpatientType" list="rpc00102Form.outpatientDataSource" listKey="recordCode" listValue="recordValue" cssErrorClass="error" />
                        </td>
                    </tr>
                </table>

                <!-- 検索の画面 -->
                <div class="guide_btn_area">
                    <table class="guide_btn_table" style="width: 100%;">
                        <col style="width: 25%;" />
                        <col style="width: 50%;" />
                        <col style="width: 25%;" />
                        <tr>
                            <td>&nbsp;</td>
                            <td>
                                <table style="width: 100%;">
                                    <tr>
                                        <td>
                                            <g:submit method="doSearch" cssClass="search_mainbtn" value="" />
                                        </td>
                                    </tr>
                                </table>
                            </td>
                            <td>&nbsp;</td>
                        </tr>
                    </table>
                </div>
                <s:if test="rpc00102Form.subForm1.size > 0">
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
                            <col width="7%" />
                            <col width="58px" />
                            <col width="20%" />
                            <col width="30%" />
                            <col width="20%" />
                            <col width="auto" />
                            <tr>
                                <th>NO</th>
                                <th>&nbsp;</th>
                                <th>姓名</th>
                                <th>手机号码</th>
                                <th>就诊时间</th>
                                <th>门诊类型</th>
                            </tr>
                            <s:iterator status="listData" value="rpc00102Form.subForm1">
                                <s:if test="rpc00102Form.pageType == 'rpc00103'">
                                    <s:url var="rpc00103Href" action="rpc00103Action" method="doInit" namespace="/rpc" escapeAmp="false">
                                        <s:param name="rpc00103Form.recordId" value='recordId'></s:param>
                                    </s:url>
                                    <s:url var="rpc00105Href" action="rpc00105Action" method="doInit" namespace="/rpc" escapeAmp="false">
                                        <s:param name="rpc00105Form.recordId" value='recordId'></s:param>
                                    </s:url>
                                </s:if>
                                <tr id="meisai_<g:property value="#listData.index"/>" <s:if test="#listData.even">class="even"</s:if> <s:else>class="odd"</s:else> onmouseover="setTableHover(this);"
                                    onmouseout="clearTableHover(this);">
                                    <!-- NO  -->
                                    <td
                                        <c:if test="${firstVisitFlag == '1' }">
                                            onclick="goNext('<s:property value="rpc00103Href"/>')" 
                                        </c:if>
                                        <c:if test="${firstVisitFlag == '0'}">
                                            onclick="goNext('<s:property value="rpc00105Href"/>')" 
                                        </c:if>
                                        style="text-align: center;">
                                        <g:property value="rpc00102Form.pageStartRowNo + #listData.index + 1" />
                                    </td>
                                    <!-- 编辑  -->
                                    <td style="text-align: center;">
                                        <a href="javascript:void(0);" class="aid_btn" style="margin-right: 0px; margin-left: 4px;"
                                            <c:if test="${firstVisitFlag == '1' }">
                                                onclick="goNext('<s:property value="rpc00103Href"/>')" 
                                            </c:if>
                                            <c:if test="${firstVisitFlag == '0'}">
                                                onclick="goNext('<s:property value="rpc00105Href"/>')" 
                                            </c:if>>
                                            <span>编辑</span>
                                        </a>
                                    </td>
                                    <!-- 用户名  -->
                                    <td title="<s:property value="userName" /> " class="txt_overflow">
                                        <s:property value="userName" />
                                        <s:hidden name="rpc00102Form.subForm1[%{#listData.index}].userName" />
                                    </td>
                                    <!-- 手机号码  -->
                                    <td title="<s:property value="ceelNumber" /> " class="txt_overflow">
                                        <s:property value="ceelNumber" />
                                        <s:hidden name="rpc00102Form.subForm1[%{#listData.index}].ceelNumber" />
                                    </td>
                                    <!-- 就诊时间  -->
                                    <td title="<s:property value="medicalDate" /> " class="txt_overflow">
                                        <s:property value="medicalDate" />
                                        <s:hidden name="rpc00102Form.subForm1[%{#listData.index}].medicalDate" />
                                    </td>
                                    <!-- 门诊类型  -->
                                    <td title="<s:property value="outpatientTypeName" /> " class="txt_overflow">
                                        <s:property value="outpatientTypeName" />
                                        <s:hidden name="rpc00102Form.subForm1[%{#listData.index}].firstVisitFlag" />
                                        <s:hidden name="rpc00102Form.subForm1[%{#listData.index}].outpatientTypeName" />
                                        <s:hidden name="rpc00102Form.subForm1[%{#listData.index}].recordId" />
                                    </td>
                                </tr>
                            </s:iterator>
                        </table>
                    </div>
                    <table width="100%">
                        <tr>
                            <td>
                                <div class="in_table_number">
                                    <g:pager formId="rpc00102Form" formAction="rpc/rpc00102Action!doPage.do" uniqueFlag="true" />
                                </div>
                            </td>
                        </tr>
                    </table>
                </s:if>

            </div>
        </div>
    </s:form>
    <g:popuperror />
</body>
</html>