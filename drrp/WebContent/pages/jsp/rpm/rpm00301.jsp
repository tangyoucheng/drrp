<!DOCTYPE html >
<%--
/*
 * Copyright(c) 2016 
 */
/*
 * 角色添加
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
<%-- <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/pages/css/schm/rpm00301.css" /> --%>
<script type="text/javascript" src="<%=request.getContextPath()%>/pages/csjs/rpm/rpm00301.js"></script>
</head>
<body>
    <s:form id="rpm00301Action" name="rpm00301Form" action="rpm00301Action" method="post" validate="true" theme="simple">
        <div id="contents">
        <div id="two_main_colum">
        
             <h:normaltitleinbtn value="rpm00301"/>
             <h:messege/>
             <table class="entry_content_table input_condition">
               <col style="width:130px;"/>
               <col style="width:35px;"/>
               <col style="width:186px;"/>
               <col style="width: auto;"/>
               <tr>
                   <td class="entry_name" style="width:130px;">角色ID：</td>
                   <td><h:mustentryicon /></td>
                   <td>
                       <s:textfield id="roleId" name="rpm00301Form.roleId" cssErrorClass="error"   maxlength="50" cssClass="mustdata" ></s:textfield>
                   </td>
                   <td></td>
               </tr>
             </table>
             <table class="entry_content_table gamen_layout1" >
               <tr>
                   <td class="entry_name" style="width:130px;">角色名：</td>
                   <td style="width:35px;" class="padding_0"><h:mustentryicon /></td>
                   <td>
                           <s:textfield id="roleName"  name="rpm00301Form.roleName" cssErrorClass="error" size="50" maxlength="50" showPassword="true" cssClass="mustdata" ></s:textfield>
                   </td>
               </tr>
               <tr>
                   <td class="entry_name" style="width:130px;">备注：</td>
                   <td ></td>
                   <td>
                       <s:textarea id="notes" name="rpm00301Form.notes" rows="2" cols="60" cssErrorClass="error" maxlength="255" ></s:textarea>
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