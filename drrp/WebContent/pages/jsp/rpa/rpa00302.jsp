<!DOCTYPE html >
<%--
/*
 * Copyright(c) 2016 
 */
/*
 * 药品信息一览
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
<%-- <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/pages/css/schm/rpa00302.css" /> --%>
<script type="text/javascript" src="<%=request.getContextPath()%>/pages/csjs/rpa/rpa00302.js"></script>
</head>
<body>
    <s:form id="rpa00302Form" name="rpa00302Form" action="rpa00302Action" method="post" validate="true" theme="simple">
        <input type="hidden" name="rpa00302Form.pageType"  value="${rpa00302Form.pageType }">
        <div id="contents">
        <div id="two_main_colum">
        
             <h:normaltitleinbtn value="rpa00302"/>
             <h:messege/>
             <table class="entry_content_table input_condition">
               <col style="width:80px;"/>
               <col style="width:35px;"/>
               <col style="width:186px;"/>
               <col style="width:80px;"/>
               <col style="width:35px;"/>
               <col style="width: auto;"/>
               <tr>
                   <td class="entry_name" style="width:80px;">药品名称：</td>
                   <td></td>
                   <td>
                       <s:textfield id="drugName" name="rpa00302Form.drugName" cssErrorClass="error" maxlength="50"></s:textfield>
                   </td>
                   <td class="entry_name" style="width:80px;">厂商名称：</td>
                   <td ></td>
                   <td>
                       <s:textfield id="manufacturerName"  name="rpa00302Form.manufacturerName" cssErrorClass="error" maxlength="50"></s:textfield>
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
                 <s:if test="rpa00302Form.subForm1.size > 0">
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
                            <col width="20%" />
                            <col width="30%" />
                            <col width="auto" />
                            <tr>
                                <th>NO</th>
                                <th>&nbsp;</th>
                                <th>药品名称</th>
                                <th>厂商名称</th>
                                <th>价格(元)/规格</th>
                            </tr>
                            <s:iterator status="listData" value="rpa00302Form.subForm1">
                                <s:if test="rpa00302Form.pageType == 'rpa00303'">
                                    <s:url var="sentaku" action="rpa00303Action" method="doInit" namespace="/rpa" escapeAmp="false">
                                        <s:param name="rpa00303Form.drugId" value='drugId'></s:param>
                                    </s:url>
                                </s:if>
                                <tr   id="meisai_<g:property value="#listData.index"/>"
                                    <s:if test="#listData.even">class="even"</s:if><s:else>class="odd"</s:else> 
                                    onmouseover="setTableHover(this);" onmouseout="clearTableHover(this);"
                                >
                                    <!-- NO  -->
                                    <td onclick="goNext('<s:property value="sentaku"/>')" style="text-align: center;">
                                        <g:property value="rpa00302Form.pageStartRowNo + #listData.index + 1" /></td>
                                    <!-- 编辑  -->
                                    <td style="text-align: center;">
                                        <a href="javascript:void(0);" class="aid_btn" style="margin-right: 0px;margin-left: 4px;" onclick="goNext('<s:property value="sentaku"/>')"><span>编辑</span></a>
                                    </td>
                                    <!-- 药品名称  -->
                                    <td title="<s:property value="drugName" /> " class="txt_overflow">
                                            <s:property value="drugName" /> 
                                            <s:hidden name="rpa00302Form.subForm1[%{#listData.index}].drugName" />
                                    </td>
                                    <!-- 厂商名称  -->
                                    <td title="<s:property value="manufacturerName" /> " class="txt_overflow">
                                            <s:property value="manufacturerName" /> 
                                            <s:hidden name="rpa00302Form.subForm1[%{#listData.index}].manufacturerName" />
                                    </td>
                                    <!-- 价格/规格  -->
                                    <td title="<s:property value="price" />/<s:property value="unit" /> " class="txt_overflow">
                                            <s:property value="price" />/<s:property value="unit" />
                                            <s:hidden name="rpa00302Form.subForm1[%{#listData.index}].price" />
                                            <s:hidden name="rpa00302Form.subForm1[%{#listData.index}].unit" />
                                    </td>
                                </tr>
                            </s:iterator>
                        </table>
                    </div>
                    <table width="100%">
                        <tr>
                            <td>
                                <div class="in_table_number">
                                     <g:pager formId="rpa00302Form" formAction="rpa/rpa00302Action!doPage.do"  uniqueFlag="true"/>
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