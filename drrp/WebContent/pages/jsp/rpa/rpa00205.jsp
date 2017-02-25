<!DOCTYPE html >
<%--
/*
 * Copyright(c) 2016 
 */
/*
 * 患者信息导出
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
<%-- <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/pages/css/schm/rpa00205.css" /> --%>
<script type="text/javascript" src="<%=request.getContextPath()%>/pages/csjs/rpa/rpa00205.js"></script>
</head>
<body>
    <s:form id="rpa00205Form" name="rpa00205Form" action="rpa00205Action" method="post" validate="true" theme="simple">
        <div id="contents">
        <div id="two_main_colum">
        
             <h:normaltitleinbtn value="rpa00205"/>
             <h:messege/>

             <div style="width:730px;">
                 <table id="tablecloth_row1" class="row_table" style="width: 720px;margin-left: 0px">
                     <col width="10%" />
                     <col width="auto" />
                     <tr>
                         <th>选择</th>
                         <th>导出项目</th>
                     </tr>
                     <s:iterator status="listData" value="rpa00205Form.subForm1">
                         <tr   id="meisai_<g:property value="#listData.index"/>"
                            <s:if test="#listData.even">class="even"</s:if><s:else>class="odd"</s:else>"
                                    onmouseover="setTableHover(this);" onmouseout="clearTableHover(this);"
                         >
                             <td style="text-align: center;">
                                <s:checkbox id="dataSelected" name="rpa00205Form.subForm1[%{#listData.index}].checkedFlag"></s:checkbox>
                             <td title="<s:property value="ItemName" /> " class="txt_overflow">
                                     <s:property value="ItemName" /> 
                                     <s:hidden name="rpa00205Form.subForm1[%{#listData.index}].ItemId" />
                                     <s:hidden name="rpa00205Form.subForm1[%{#listData.index}].ItemName" />
                             </td>
                         </tr>
                     </s:iterator>
                 </table>
                <table class="subForm1_checked_btn">
                    <tr>
                        <td><a href="javascript:void(0);" class="aid_btn" onclick="doCheckedAll('dataSelected',true)"><span>全选择</span></a></td>
                        <td><a href="javascript:void(0);" class="aid_btn" onclick="doCheckedAll('dataSelected',false)"><span>全解除</span></a></td>
                        <td></td>
                        <td></td>
                        <td></td>
                    </tr>
                </table>
             </div>
             
           <div  class="guide_btn_area">
                 <table class="guide_btn_table" style="width: 100%;">
                      <col style="width: 25%;" />
                      <col style="width: 50%;" />
                      <col style="width: 25%;" />
                        <tr>
                            <td>
                                <table  style="width: 100%;">
                                      <col style="width: 50%;" />
                                      <col style="width: 50%;" />
                                    <tr>
                                        <td>&nbsp;</td>
                                        <td>&nbsp;</td>
                                    </tr>
                                </table>
                            </td>
                            <!-- 抽出 -->
                            <td>
                                <table  style="width: 100%;">
                                    <col style="width: 50%;" />
                                    <col style="width: 50%;" />
                                    <tr>
                                        <!-- 抽出 -->
                                        <td><g:submit method="doExport" id="chushutsu" cssClass="extraction_mainbtn" onclick="return doChushutsu($(this));" value="" /></td>
                                    </tr>
                                </table>
                            </td>
                
                            <td>
                                <table  style="width: 100%;">
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
    <g:download />
</body>
</html>