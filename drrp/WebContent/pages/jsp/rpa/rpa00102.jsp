<!DOCTYPE html >
<%--
/*
 * Copyright(c) 2016 
 */
/*
 * 处方信息一览
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
<%-- <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/pages/css/schm/rpa00102.css" /> --%>
<script type="text/javascript" src="<%=request.getContextPath()%>/pages/csjs/rpa/rpa00102.js"></script>
</head>
<body>
    <s:form id="rpa00102Form" name="rpa00102Form" action="rpa00102Action" method="post" validate="true" theme="simple">
        <input id="hiddenPageType" type="hidden" name="rpa00102Form.pageType"  value="${rpa00102Form.pageType }">
        <div id="contents">
        <div id="two_main_colum">
        
             <h:normaltitleinbtn value="rpa00102"/>
             <h:messege/>
             <table class="entry_content_table input_condition">
               <col style="width:80px;"/>
               <col style="width:35px;"/>
               <col style="width:186px;"/>
               <col style="width:80px;"/>
               <col style="width:35px;"/>
               <col style="width: auto;"/>
               <tr>
                   <td class="entry_name" style="width:80px;">患者姓名：</td>
                   <td></td>
                   <td>
                       <s:textfield id="userName" name="rpa00102Form.userName" cssErrorClass="error" maxlength="50"></s:textfield>
                   </td>
                   <td class="entry_name" style="width:80px;">手机号码：</td>
                   <td ></td>
                   <td>
                       <s:textfield id="ceelNumber"  name="rpa00102Form.ceelNumber" cssErrorClass="error" maxlength="50"></s:textfield>
                   </td>
               </tr>
             </table>

                <!-- 検索の画面 -->
                <div class="guide_btn_area">
                    <table class="guide_btn_table" style="width:100%;">
                        <col style="width: 25%;" />
                        <col style="width: 50%;" />
                        <col style="width: 25%;" />
                        <tr>
                            <td>&nbsp;</td>
                            <td>
                                <table style="width: 100%;">
                                    <tr>
                                        <td><g:submit method="doSearch" cssClass="search_mainbtn" value="" /></td>
                                    </tr>
                                </table>
                            </td>
                            <td>
                                &nbsp;
                            </td>
                        </tr>
                    </table>
                </div>
                 <s:if test="rpa00102Form.subForm1.size > 0">
                     <table style="width: 100%;">
                        <tr>
                            <td>&nbsp;</td>
                        </tr>
                        <tr>
                            <td class="font_bold">检索结果一览</td>
                        </tr>
                    </table>
                    <div style="width:730px;">
                        <table id="tablecloth_row1" class="row_table" style="width: 720px;margin-left: 0px">
                            <col width="7%" />
                            <col width="20%" />
                            <col width="30%" />
                            <col width="15%" />
                            <col width="auto" />
                            <tr>
                                <th>NO</th>
                                <th>患者姓名</th>
                                <th>手机号码</th>
                                <th>处方状态</th>
                                <th>处方日期</th>
                            </tr>
                            <s:iterator status="listData" value="rpa00102Form.subForm1">
                                <s:if test="rpa00102Form.pageType == 'rpa00103'">
                                    <s:url var="sentaku" action="rpa00103Action" method="doInit" namespace="/rpa" escapeAmp="false">
                                        <s:param name="rpa00103Form.patientId" value='userId'></s:param>
                                        <s:param name="rpa00103Form.prescriptionId" value='prescriptionId'></s:param>
                                    </s:url>
                                </s:if>
                                <s:if test="rpa00102Form.pageType == 'rpa00104'">
                                    <s:url var="sentaku" action="rpa00104Action" method="doInit" namespace="/rpa" escapeAmp="false">
                                        <s:param name="rpa00104Form.patientId" value='userId'></s:param>
                                        <s:param name="rpa00104Form.prescriptionId" value='prescriptionId'></s:param>
                                    </s:url>
                                </s:if>
                                <s:if test="rpa00102Form.pageType == 'rpa00105'">
                                    <s:url var="sentaku" action="rpa00105Action" method="doInit" namespace="/rpa" escapeAmp="false">
                                        <s:param name="rpa00105Form.patientId" value='userId'></s:param>
                                        <s:param name="rpa00105Form.prescriptionId" value='prescriptionId'></s:param>
                                    </s:url>
                                </s:if>
                                <tr id="meisai_<g:property value="#listData.index"/>"
	                                onclick="goNext('<s:property value="sentaku"/>')" 
	                                <s:if test="#listData.even">class="even"</s:if><s:else>class="odd"</s:else> 
                                    onmouseover="setTableHover(this);" onmouseout="clearTableHover(this);"
                                >
                                    <!-- NO  -->
                                    <td style="text-align: center;">
                                        <g:property value="rpa00102Form.pageStartRowNo + #listData.index + 1" /></td>
                                    <!-- 用户名  -->
                                    <td title="<s:property value="userName" /> " class="txt_overflow">
                                            <s:property value="userName" /> 
                                            <s:hidden name="rpa00102Form.subForm1[%{#listData.index}].userName" />
                                    </td>
                                    <!-- 手机号码  -->
                                    <td title="<s:property value="ceelNumber" /> " class="txt_overflow">
                                            <s:property value="ceelNumber" /> 
                                            <s:hidden name="rpa00102Form.subForm1[%{#listData.index}].ceelNumber" />
                                    </td>
                                    <!-- 处方状态  -->
                                    <td id="prescriptionStatus_<g:property value="#listData.index"/>" title="<s:property value="prescriptionStatusName" /> " class="txt_overflow">
                                            <s:property value="prescriptionStatusName" /> 
                                            <s:hidden name="rpa00102Form.subForm1[%{#listData.index}].prescriptionStatus" />
                                            <s:hidden name="rpa00102Form.subForm1[%{#listData.index}].prescriptionStatusName" />
                                    </td>
                                    <!-- 处方日期  -->
                                    <td title="<s:property value="prescriptionCreateDate" /> " class="txt_overflow">
                                            <s:property value="prescriptionCreateDate" /> 
                                            <s:hidden name="rpa00102Form.subForm1[%{#listData.index}].prescriptionCreateDate" />
                                    </td>
                                </tr>
                            </s:iterator>
                        </table>
                    </div>
                    <table width="100%">
                        <tr>
                            <td>
                                <div class="in_table_number">
                                     <g:pager formId="rpa00102Form" formAction="rpa/rpa00102Action!doPage.do"  uniqueFlag="true"/>
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