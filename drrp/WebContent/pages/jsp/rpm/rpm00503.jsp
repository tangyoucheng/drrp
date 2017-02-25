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
<script type="text/javascript" src="<%=request.getContextPath()%>/pages/csjs/rpm/rpm00503.js"></script>
</head>
<body>
    <s:form id="rpm00503Action" name="rpm00503Form" action="rpm00503Action" method="post" validate="true" theme="simple">
    <input type="hidden" name="rpm00503Form.lastUpdateDate"  value="${rpm00503Form.lastUpdateDate }">
        <div id="contents">
        <div id="two_main_colum">
        
             <h:normaltitleinbtn value="rpm00503"/>
             <h:messege/>
             <table class="entry_content_table input_condition">
               <col style="width:130px;"/>
               <col style="width:35px;"/>
               <col style="width:186px;"/>
               <col style="width: auto;"/>
               <tr>
                   <td class="entry_name" style="width:130px;">推广信息ID：</td>
                   <td></td>
                   <td>
                       <h:readonlyfield id="adId" name="rpm00503Form.adId" ></h:readonlyfield>
                   </td>
                   <td></td>
               </tr>
               <tr>
                   <td class="entry_name" style="width:130px;">推广信息名：</td>
                   <td style="width:35px;" class="padding_0"><h:mustentryicon /></td>
                   <td>
                      <s:textfield id="adName"  name="rpm00503Form.adName" cssErrorClass="error" cssStyle="width:400px;" cssErrorStyle="width:400px;" maxlength="50" showPassword="true" cssClass="mustdata" ></s:textfield>
                   </td>
               </tr>
               <tr>
                   <td class="entry_name" style="width:130px;">推广信息URL：</td>
                   <td style="width:35px;" class="padding_0"><h:mustentryicon /></td>
                   <td>
                       <s:textfield id="adUrl" name="rpm00503Form.adUrl" cssErrorClass="error" cssStyle="width:500px;" cssErrorStyle="width:500px;"  maxlength="300" showPassword="true" cssClass="mustdata" ></s:textfield>
                   </td>
               </tr>
               <tr>
                   <td class="entry_name" style="width:130px;">推广信息状态：</td>
                   <td ></td>
                   <td>
                       <s:if test="rpm00503Form.checkedFlag">
                       <s:checkbox id="dataSelected" name="rpm00503Form.checkedFlag"  value="true" ></s:checkbox>推广
                       </s:if>
                       <s:else>
                       <s:checkbox id="dataSelected" name="rpm00503Form.checkedFlag" ></s:checkbox>推广
                       </s:else>
                   </td>
               </tr>
               <tr>
                   <td class="entry_name" style="width:130px;">备注：</td>
                   <td ></td>
                   <td>
                       <s:textarea id="notes" name="rpm00503Form.notes" rows="2" cols="60" cssErrorClass="error" maxlength="255" ></s:textarea>
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
                                         <td><g:submit action="rpm00502Action" method="doBack" cssClass="back_mainbtn" /></td>
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