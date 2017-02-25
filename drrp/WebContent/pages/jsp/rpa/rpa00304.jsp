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
<%-- <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/pages/css/schm/rpa00304.css" /> --%>
<script type="text/javascript" src="<%=request.getContextPath()%>/pages/csjs/rpa/rpa00304.js"></script>
</head>
<body>
    <s:form id="rpa00304Form" name="rpa00304Form" action="rpa00304Action" method="post" validate="true" theme="simple">
        <input type="hidden" name="rpa00304Form.pageType"  value="${rpa00304Form.pageType }">
        <div id="contents">
        <div id="two_main_colum">
        
             <h:normaltitleinbtn value="rpa00304"/>
             <h:messege/>
             <table class="entry_content_table input_condition">
               <col style="width:80px;"/>
               <col style="width:186px;"/>
               <col style="width:80px;"/>
               <col style="width:186px;"/>
               <col style="width: auto;"/>
               <tr>
                   <td class="entry_name" style="width:80px;">药品名称：</td>
                   <td>
                       <s:textfield id="drugName" name="rpa00302Form.drugName" cssErrorClass="error" maxlength="50"></s:textfield>
                   </td>
                   <td class="entry_name" style="width:80px;">厂商名称：</td>
                   <td>
                       <s:textfield id="manufacturerName"  name="rpa00302Form.manufacturerName" cssErrorClass="error" maxlength="50"></s:textfield>
                   </td>
                   <td><g:submit method="doSearch" cssClass="aid_btn" value="" ><span>呼出</span></g:submit></td>
               </tr>
             </table>

                 <s:if test="rpa00304Form.subForm1.size > 0">
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
                            <col width="5%" />
                            <col width="20%" />
                            <col width="20%" />
                            <col width="20%" />
                            <col width="auto" />
                            <tr>
                                <th>NO</th>
                                <th>药品名称</th>
                                <th>厂商名称</th>
                                <th>价格(元)/规格</th>
                                <th>备注</th>
                            </tr>
                            <s:iterator status="listData" value="rpa00304Form.subForm1">
                                <tr id="drugTr_<g:property value="#listData.index"/>" onclick="setSelectedTrColor(this);">
                                    <!-- NO  -->
                                    <td style="text-align: center;">
                                        <g:property value="rpa00304Form.pageStartRowNo + #listData.index + 1" />
                                        <s:hidden id="hidDrugId_%{#listData.index}" name="rpa00304Form.subForm1[%{#listData.index}].drugId"/>
                                        <s:hidden id="hidDrugName_%{#listData.index}" name="rpa00304Form.subForm1[%{#listData.index}].drugName"/>
                                        <s:hidden id="hidManufacturerName_%{#listData.index}" name="rpa00304Form.subForm1[%{#listData.index}].manufacturerName"/>
                                        <s:hidden id="hidPrice_%{#listData.index}" name="rpa00304Form.subForm1[%{#listData.index}].price"/>
                                        <s:hidden id="hidUnit_%{#listData.index}" name="rpa00304Form.subForm1[%{#listData.index}].unit"/>
                                        <s:hidden id="hidNotes_%{#listData.index}" name="rpa00304Form.subForm1[%{#listData.index}].notes"/>
                                    </td>
                                    <!-- 药品名称  -->
                                    <td title="<s:property value="#rpa0030401Dto.drugName" /> " class="txt_overflow">
                                            <s:property value="#rpa0030401Dto.drugName" /> 
                                    </td>
                                    <!-- 厂商名称  -->
                                    <td title="<s:property value="#rpa0030401Dto.manufacturerName" /> " class="txt_overflow">
                                            <s:property value="#rpa0030401Dto.manufacturerName" /> 
                                    </td>
                                    <!-- 价格/规格  -->
                                    <td title="<s:property value="#rpa0030401Dto.price" />/<s:property value="#rpa0030401Dto.unit" /> " class="txt_overflow">
                                            <s:property value="#rpa0030401Dto.price" />/<s:property value="#rpa0030401Dto.unit" /> 
                                    </td>
                                    <!-- 备注  -->
                                    <td title="<s:property value="#rpa0030401Dto.notes" /> " class="txt_overflow">
                                            <s:property value="#rpa0030401Dto.notes" /> 
                                    </td>
                                </tr>
                            </s:iterator>
                        </table>
                    </div>
                    <table width="100%">
                        <tr>
                            <td>
                                <div class="in_table_number">
                                     <g:pager formId="rpa00304Form" formAction="rpa/rpa00304Action!doPage.do"  uniqueFlag="true"/>
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
                        <table  style="width: 100%;">
                              <col style="width: 50%;" />
                              <col style="width: 50%;" />
                            <tr>
                                <td style="text-align: left;">&nbsp;</td>
                                <td>&nbsp;</td>
                            </tr> 
                        </table>
                    </td>
               
                    <td>
                        <table  style="width: 100%;">
                            <tr>
                              <td>
                                  <s:if test="rpa00304Form.subForm1.size > 0" >
                                      <g:submit cssClass="select_mainbtn" value="" title="選択" onclick="return setSelectedData();" />
                                  </s:if>
                              </td>
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
</body>
</html>