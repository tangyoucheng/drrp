<!DOCTYPE html >
<%--
/*
 * Copyright(c) 2016 
 */
/*
 * 角色一览
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
<%-- <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/pages/css/schm/rpm00302.css" /> --%>
<%-- <script type="text/javascript" src="<%=request.getContextPath()%>/pages/csjs/rpm/rpm00302.js"></script> --%>
</head>
<body>
    <s:form id="rpm00302Form" name="rpm00302Form" action="rpm00302Action" method="post" validate="true" theme="simple">
        <input type="hidden" name="rpm00302Form.pageType"  value="${rpm00302Form.pageType }">
        <div id="contents">
        <div id="two_main_colum">
        
             <h:normaltitleinbtn value="rpm00302"/>
             <h:messege/>
             <table class="entry_content_table input_condition">
               <col style="width:80px;"/>
               <col style="width:35px;"/>
               <col style="width:186px;"/>
               <col style="width:80px;"/>
               <col style="width:35px;"/>
               <col style="width: auto;"/>
               <tr>
                   <td class="entry_name" style="width:80px;">角色ID：</td>
                   <td></td>
                   <td>
                       <s:textfield id="roleId" name="rpm00302Form.roleId" cssErrorClass="error"   maxlength="50" ></s:textfield>
                   </td>
                   <td class="entry_name" style="width:80px;">角色名：</td>
                   <td ></td>
                   <td>
                       <s:textfield id="roleName"  name="rpm00302Form.roleName" cssErrorClass="error" maxlength="50"></s:textfield>
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
                 <s:if test="rpm00302Form.subForm1.size > 0">
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
                                <th>角色ＩＤ</th>
                                <th>角色名</th>
                                <th>备注</th>
                            </tr>
                            <s:iterator status="listData" value="rpm00302Form.subForm1">
                                <s:if test="rpm00302Form.pageType == 'rpm00303'">
                                    <s:url var="sentaku" action="rpm00303Action" method="doInit" namespace="/rpm" escapeAmp="false">
                                        <s:param name="rpm00303Form.roleId" value="roleId"></s:param>
                                    </s:url>
                                </s:if>
                                <tr  id="meisai_<g:property value="#listData.index"/>"
	                                onclick="goNext('<s:property value="sentaku"/>')"  
	                                <s:if test="#listData.even">class="even"</s:if><s:else>class="odd"</s:else> 
                                    onmouseover="setTableHover(this);" onmouseout="clearTableHover(this);"
                                >
                                    <!-- NO  -->
                                    <td style="text-align: center;">
                                        <g:property value="rpm00302Form.pageStartRowNo + #listData.index + 1" /></td>
                                    <!-- 角色ID  -->
                                    <td title="<s:property value="roleId" /> " class="txt_overflow">
                                            <s:property value="roleId" /> 
                                            <s:hidden name="rpm00302Form.subForm1[%{#listData.index}].roleId" />
                                    </td>
                                    <!-- 角色名  -->
                                    <td title="<s:property value="roleName" /> " class="txt_overflow">
                                            <s:property value="roleName" /> 
                                            <s:hidden name="rpm00302Form.subForm1[%{#listData.index}].roleName" />
                                    </td>
                                    <!-- 备注  -->
                                    <td title="<s:property value="notes" /> " class="txt_overflow">
                                            <s:property value="notes" /> 
                                            <s:hidden name="rpm00302Form.subForm1[%{#listData.index}].notes" />
                                    </td>
                                </tr>
                            </s:iterator>
                        </table>
                    </div>
                    <table width="100%">
                        <tr>
                            <td>
                                <div class="in_table_number">
                                     <g:pager formId="rpm00302Form" formAction="rpm/rpm00302Action!doPage.do"  uniqueFlag="true"/>
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