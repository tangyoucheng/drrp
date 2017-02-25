<!DOCTYPE html PUBLIC "-//W3C//DTD Xhtml 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%--
/*
 * Copyright(c) 2016 
 */
/*
 * 用户一览
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
<%-- <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/pages/css/schm/rpm00202.css" /> --%>
<%-- <script type="text/javascript" src="<%=request.getContextPath()%>/pages/csjs/schm/rpm00202.js"></script> --%>
</head>
<body>
    <s:form id="rpm00202Action" name="rpm00202Form" action="rpm00202Action" method="post" validate="true" theme="simple">
        <input type="hidden" name="rpm00202Form.pageType"  value="${rpm00202Form.pageType }">
        <div id="contents">
        <div id="two_main_colum">
        
             <h:normaltitleinbtn value="rpm00202"/>
             <h:messege/>
             <table class="entry_content_table input_condition">
               <col style="width:80px;"/>
               <col style="width:35px;"/>
               <col style="width:186px;"/>
               <col style="width:80px;"/>
               <col style="width:35px;"/>
               <col style="width: auto;"/>
               <tr>
                   <td class="entry_name" style="width:80px;">用户ID：</td>
                   <td></td>
                   <td>
                       <s:textfield id="userId" name="rpm00202Form.userId" cssErrorClass="error"   maxlength="50"></s:textfield>
                   </td>
                   <td class="entry_name" style="width:80px;">用户名：</td>
                   <td ></td>
                   <td>
                       <s:textfield id="userName"  name="rpm00202Form.userName" cssErrorClass="error" maxlength="50" ></s:textfield>
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
                 <s:if test="rpm00202Form.subForm1.size > 0">
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
                            <col width="auto" />
                            <tr>
                                <th>NO</th>
                                <th>用户ＩＤ</th>
                                <th>用户名</th>
                                <th>备注</th>
                            </tr>
                            <s:iterator status="listData" value="rpm00202Form.subForm1">
                                <s:if test="rpm00202Form.pageType == 'rpm00203'">
                                    <s:url var="sentaku" action="rpm00203Action" method="doInit" namespace="/rpm" escapeAmp="false">
                                        <s:param name="rpm00203Form.userId" value="userId"></s:param>
                                    </s:url>
                                </s:if>
                                <tr  id="meisai_<g:property value="#listData.index"/>"
                                    onclick="goNext('<s:property value="sentaku"/>')" 
                                    <s:if test="#listData.even">class="even"</s:if><s:else>class="odd"</s:else>
                                    onmouseover="setTableHover(this);" onmouseout="clearTableHover(this);"
                                 >
                                    <!-- NO  -->
                                    <td style="text-align: center;">
                                        <g:property value="rpm00202Form.pageStartRowNo + #listData.index + 1" /></td>
                                    <!-- 角色ID  -->
                                    <td title="<s:property value="userId" /> " class="txt_overflow">
                                            <s:property value="userId" /> 
                                            <s:hidden name="rpm00202Form.subForm1[%{#listData.index}].userId" />
                                    </td>
                                    <!-- 角色名  -->
                                    <td title="<s:property value="userName" /> " class="txt_overflow">
                                            <s:property value="userName" /> 
                                            <s:hidden name="rpm00202Form.subForm1[%{#listData.index}].userName" />
                                    </td>
                                    <!-- 备注  -->
                                    <td title="<s:property value="notes" /> " class="txt_overflow">
                                            <s:property value="notes" /> 
                                            <s:hidden name="rpm00202Form.subForm1[%{#listData.index}].notes" />
                                    </td>
                                </tr>
                            </s:iterator>
                        </table>
                    </div>
                    <table width="100%">
                        <tr>
                            <td>
                                <div class="in_table_number">
                                     <g:pager formId="rpm00202Form" formAction="rpm/rpm00202Action!doPage.do"  uniqueFlag="true"/>
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