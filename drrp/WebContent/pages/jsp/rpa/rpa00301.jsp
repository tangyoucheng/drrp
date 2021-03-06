<!DOCTYPE html >
<%--
/*
 * Copyright(c) 2016 
 */
/*
 * 药品信息添加
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
<%-- <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/pages/css/schm/rpa00301.css" /> --%>
<script type="text/javascript" src="<%=request.getContextPath()%>/pages/csjs/rpa/rpa00301.js"></script>
</head>
<body>
    <s:form id="rpa00301Form" name="rpa00301Form" action="rpa00301Action" method="post" validate="true" theme="simple">
        <div id="contents">
        <div id="two_main_colum">
        
             <h:normaltitleinbtn value="rpa00301"/>
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
                       <s:textfield id="drugName" name="rpa00301Form.drugName" cssErrorClass="error" style="width:400px;" cssErrorStyle="width:400px;" maxlength="300" cssClass="mustdata" ></s:textfield>
                   </td>
               </tr>
               <tr>
                   <!-- 厂商名称 -->
                   <td class="entry_name" style="width:110px;">厂商名称：</td>
                   <td><h:mustentryicon/></td>
                   <td >
                       <s:textfield id="manufacturerName" name="rpa00301Form.manufacturerName" cssErrorClass="error" style="width:400px;" cssErrorStyle="width:400px;" maxlength="300" cssClass="mustdata" ></s:textfield>
                   </td>
               </tr>
               <tr>
                   <!-- 价格 -->
                   <td class="entry_name" style="width:110px;">价格：</td>
                   <td><h:mustentryicon/></td>
                   <td >
                       <s:textfield id="price" name="rpa00301Form.price" cssErrorClass="error" style="width:120px;" cssErrorStyle="width:120px;" maxlength="10" cssClass="mustdata" ></s:textfield>元
                   </td>
               </tr>
               <tr>
                   <!-- 规格 -->
                   <td class="entry_name" style="width:110px;">规格：</td>
                   <td><h:mustentryicon/></td>
                   <td >
                       <s:textfield id="price" name="rpa00301Form.unit" cssErrorClass="error" style="width:120px;" cssErrorStyle="width:120px;" maxlength="10" cssClass="mustdata" ></s:textfield>
                   </td>
               </tr>
               <tr>
                   <td class="entry_name" style="width:110px;">备注：</td>
                   <td ></td>
                   <td>
                       <s:textarea id="notes" name="rpa00301Form.notes" rows="2" cols="60" cssErrorClass="error" maxlength="255" ></s:textarea>
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
                                        <td>&nbsp;</td>
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
                                        <td><g:submit method="doEntry" id="touroku" cssClass="entry_mainbtn" onclick="return doEntry($(this));" value="" /></td>
                                        <td>
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