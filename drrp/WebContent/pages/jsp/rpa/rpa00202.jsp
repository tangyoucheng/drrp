<!DOCTYPE html >
<%--
/*
 * Copyright(c) 2016 
 */
/*
 * 患者信息一览
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
<%-- <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/pages/css/schm/rpa00202.css" /> --%>
<script type="text/javascript" src="<%=request.getContextPath()%>/pages/csjs/rpa/rpa00202.js"></script>
</head>
<body>
    <s:form id="rpa00202Form" name="rpa00202Form" action="rpa00202Action" method="post" validate="true" theme="simple">
        <input type="hidden" name="rpa00202Form.pageType"  value="${rpa00202Form.pageType }">
        <div id="contents">
        <div id="two_main_colum">
        
             <h:normaltitleinbtn value="rpa00202"/>
             <h:messege/>
             <table class="entry_content_table input_condition">
               <col style="width:80px;"/>
               <col style="width:35px;"/>
               <col style="width:186px;"/>
               <col style="width:80px;"/>
               <col style="width:35px;"/>
               <col style="width: auto;"/>
               <tr>
                   <td class="entry_name" style="width:80px;">姓名：</td>
                   <td></td>
                   <td>
                       <s:textfield id="userName" name="rpa00202Form.userName" cssErrorClass="error" maxlength="50"></s:textfield>
                   </td>
                   <td class="entry_name" style="width:80px;">手机号码：</td>
                   <td ></td>
                   <td>
                       <s:textfield id="ceelNumber"  name="rpa00202Form.ceelNumber" cssErrorClass="error" maxlength="50"></s:textfield>
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
                 <s:if test="rpa00202Form.subForm1.size > 0">
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
                            <col width="58px" />
                            <col width="58px" />
                            <col width="20%" />
                            <col width="30%" />
                            <col width="auto" />
                            <tr>
                                <th>NO</th>
                                <th>&nbsp;</th>
                                <th>&nbsp;</th>
                                <th>姓名</th>
                                <th>手机号码</th>
                                <th>座机号码</th>
                            </tr>
                            <s:iterator status="listData" value="rpa00202Form.subForm1">
                                <s:if test="rpa00202Form.pageType == 'rpa00203'">
                                    <s:url var="sentaku" action="rpa00203Action" method="doInit" namespace="/rpa" escapeAmp="false">
                                        <s:param name="rpa00203Form.userId" value='userId'></s:param>
                                    </s:url>
                                </s:if>
                                    <s:url var="print" action="rpa00206Action" method="doInit" namespace="/rpa" escapeAmp="false">
                                        <s:param name="rpa00206Form.userId" value="userId"></s:param>
                                    </s:url>
                                <tr   id="meisai_<g:property value="#listData.index"/>"
                                    <s:if test="#listData.even">class="even"</s:if><s:else>class="odd"</s:else> 
                                    onmouseover="setTableHover(this);" onmouseout="clearTableHover(this);"
                                >
                                    <!-- NO  -->
                                    <td onclick="goNext('<s:property value="sentaku"/>')" style="text-align: center;">
                                        <g:property value="rpa00202Form.pageStartRowNo + #listData.index + 1" /></td>
                                    <!-- 编辑  -->
                                    <td style="text-align: center;">
                                        <a href="javascript:void(0);" class="aid_btn" style="margin-right: 0px;margin-left: 4px;" onclick="goNext('<s:property value="sentaku"/>')"><span>编辑</span></a>
                                    </td>
                                    <!-- 印刷  -->
                                    <td style="text-align: center;">
                                        <a href="javascript:void(0);" class="aid_btn" style="margin-right: 0px;margin-left: 4px;" onclick="doPrint('<s:property value="print"/>')"><span>印刷</span></a>
                                    </td>
                                    <!-- 用户名  -->
                                    <td title="<s:property value="userName" /> " class="txt_overflow">
                                            <s:property value="userName" /> 
                                            <s:hidden name="rpa00202Form.subForm1[%{#listData.index}].userName" />
                                    </td>
                                    <!-- 手机号码  -->
                                    <td title="<s:property value="ceelNumber" /> " class="txt_overflow">
                                            <s:property value="ceelNumber" /> 
                                            <s:hidden name="rpa00202Form.subForm1[%{#listData.index}].ceelNumber" />
                                    </td>
                                    <!-- 座机号码  -->
                                    <td title="<s:property value="phoneNumber" /> " class="txt_overflow">
                                            <s:property value="phoneNumber" /> 
                                            <s:hidden name="rpa00202Form.subForm1[%{#listData.index}].phoneNumber" />
                                    </td>
                                </tr>
                            </s:iterator>
                        </table>
                    </div>
                    <table width="100%">
                        <tr>
                            <td>
                                <div class="in_table_number">
                                     <g:pager formId="rpa00202Form" formAction="rpa/rpa00202Action!doPage.do"  uniqueFlag="true"/>
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