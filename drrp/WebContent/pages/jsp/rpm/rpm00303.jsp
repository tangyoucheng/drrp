<!DOCTYPE html >
<%--
/*
 * Copyright(c) 2016 
 */
/*
 * 角色编辑
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
<%-- <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/pages/css/schm/rpm00303.css" /> --%>
<script type="text/javascript" src="<%=request.getContextPath()%>/pages/csjs/rpm/rpm00303.js"></script>
</head>
<body>
    <s:form id="rpm00303Action" name="rpm00303Form" action="rpm00303Action" method="post" validate="true" theme="simple">
        <input type="hidden" name="rpm00303Form.lastUpdateDate"  value="${rpm00303Form.lastUpdateDate }">
        <div id="contents">
        <div id="two_main_colum">
        
             <h:normaltitleinbtn value="rpm00303"/>
             <h:messege/>
             <table class="entry_content_table input_condition">
               <col style="width:130px;"/>
               <col style="width:35px;"/>
               <col style="width:186px;"/>
               <col style="width: auto;"/>
               <tr>
                   <td class="entry_name" style="width:130px;">角色ID：</td>
                   <td></td>
                   <td>
                       <h:readonlyfield id="roleId" name="rpm00303Form.roleId" ></h:readonlyfield>
                   </td>
                   <td></td>
               </tr>
               <tr>
                   <td class="entry_name" style="width:130px;">角色名：</td>
                   <td style="width:35px;" class="padding_0"><h:mustentryicon /></td>
                   <td>
                           <s:textfield id="roleName"  name="rpm00303Form.roleName" cssErrorClass="error" size="50" maxlength="50" showPassword="true" cssClass="mustdata" ></s:textfield>
                   </td>
               </tr>
               <tr>
                   <td class="entry_name" style="width:130px;">备注：</td>
                   <td ></td>
                   <td>
                       <s:textarea id="notes" name="rpm00303Form.notes" rows="2" cols="60" cssErrorClass="error" maxlength="255" ></s:textarea>
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
                                         <td><g:submit action="rpm00302Action" method="doBack" cssClass="back_mainbtn" /></td>
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
                                        <td><g:submit method="doUpdate" id="touroku" cssClass="edit_mainbtn" onclick="return doUpdate($(this));" value="" /></td>
                                        <s:if test="rpm00303Form.roleId == 'admin' ">
                                        <td>&nbsp;</td>
                                        </s:if>
                                        <s:if test="rpm00303Form.roleId != 'admin' ">
                                        <td><g:submit method="doDelete" id="delete" cssClass="delete_mainbtn" onclick="return doDelete($(this));" value="" /></td>
                                        </s:if>
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