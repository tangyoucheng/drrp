<!DOCTYPE html >
<%--
/*
 * Copyright(c) 2016 
 */
/*
 * 药品信息编辑
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
<%-- <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/pages/css/schm/rpa00303.css" /> --%>
<script type="text/javascript" src="<%=request.getContextPath()%>/pages/csjs/rpa/rpa00303.js"></script>
</head>
<body>
    <s:form id="rpa00302Action" name="rpa00303Form" action="rpa00303Action" method="post" validate="true" theme="simple">
        <input type="hidden" name="rpa00303Form.drugId"  value="${rpa00303Form.drugId }">
        <input type="hidden" name="rpa00303Form.lastUpdateDate"  value="${rpa00303Form.lastUpdateDate }">
        <div id="contents">
        <div id="two_main_colum">
        
             <h:normaltitleinbtn value="rpa00303"/>
             <h:messege/>
             <table class="entry_content_table gamen_layout1">
               <col style="width: 110px;"/>
               <col style="width: 35px;"/>
               <col style="width:auto;"/>
               <tr>
                   <!-- 药品名称 -->
                   <td class="entry_name" style="width:110px;">药品名称：</td>
                   <td><h:mustentryicon/></td>
                   <td >
                       <s:textfield id="drugName" name="rpa00303Form.drugName" cssErrorClass="error" style="width:400px;" cssErrorStyle="width:400px;" maxlength="300" cssClass="mustdata" ></s:textfield>
                   </td>
               </tr>
               <tr>
                   <!-- 厂商名称 -->
                   <td class="entry_name" style="width:110px;">厂商名称：</td>
                   <td><h:mustentryicon/></td>
                   <td >
                       <s:textfield id="manufacturerName" name="rpa00303Form.manufacturerName" cssErrorClass="error" style="width:400px;" cssErrorStyle="width:400px;" maxlength="300" cssClass="mustdata" ></s:textfield>
                   </td>
               </tr>
               <tr>
                   <!-- 价格 -->
                   <td class="entry_name" style="width:110px;">价格：</td>
                   <td><h:mustentryicon/></td>
                   <td >
                       <s:textfield id="price" name="rpa00303Form.price" cssErrorClass="error" style="width:120px;" cssErrorStyle="width:120px;" maxlength="10" cssClass="mustdata" ></s:textfield>元
                   </td>
               </tr>
               <tr>
                   <!-- 规格 -->
                   <td class="entry_name" style="width:110px;">规格：</td>
                   <td><h:mustentryicon/></td>
                   <td >
                       <s:textfield id="price" name="rpa00303Form.unit" cssErrorClass="error" style="width:120px;" cssErrorStyle="width:120px;" maxlength="10" cssClass="mustdata" ></s:textfield>
                   </td>
               </tr>
               <tr>
                   <td class="entry_name" style="width:110px;">备注：</td>
                   <td ></td>
                   <td>
                       <s:textarea id="notes" name="rpa00303Form.notes" rows="2" cols="60" cssErrorClass="error" maxlength="255" ></s:textarea>
                   </td>
               </tr>
             </table>
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
                                         <td><g:submit action="rpa00302Action" method="doBack" cssClass="back_mainbtn" /></td>
                                         <td>&nbsp;</td>
                                    </tr>
                                </table>
                            </td>
                            <!-- 登録 -->
                            <td>
                                <table  style="width: 100%;">
                                    <col style="width: 50%;" />
                                    <col style="width: 50%;" />
                                    <tr>
                                        <!-- 登録 -->
                                        <td><g:submit method="doUpdate" id="touroku" cssClass="edit_mainbtn" onclick="return doUpdate($(this));" value="" /></td>
                                        <td><g:submit method="doDelete" id="delete" cssClass="delete_mainbtn" onclick="return doDelete($(this));" value="" /></td>
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