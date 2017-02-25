<!DOCTYPE html >
<%--
/*
 * Copyright(c) 2016 
 */
/*
 * 患者信息添加
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
<%-- <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/pages/css/schm/rpa00201.css" /> --%>
<script type="text/javascript" src="<%=request.getContextPath()%>/pages/csjs/rpa/rpa00201.js"></script>
</head>
<body>
    <s:form id="rpa00201Form" name="rpa00201Form" action="rpa00201Action" method="post" validate="true" theme="simple">
        <div id="contents">
        <div id="two_main_colum">
        
             <h:normaltitleinbtn value="rpa00201"/>
             <h:messege/>
             <table class="entry_content_table gamen_layout1">
               <col style="width: 110px;"/>
               <col style="width: 35px;"/>
               <col style="width:auto;"/>
               <tr>
                   <!-- 姓名 -->
                   <td class="entry_name" style="width:110px;">姓名：</td>
                   <td><h:mustentryicon/></td>
                   <td >
                       <s:textfield id="userName" name="rpa00201Form.userName" cssErrorClass="error" style="width:130px;" maxlength="120" cssClass="mustdata" ></s:textfield>
                   </td>
               </tr>
             </table>
             <table class="entry_content_table gamen_layout1">
               <col style="width: 110px;"/>
               <col style="width: 35px;"/>
               <col style="width: 120px;"/>
               <col style="width: 80px;"/>
               <col style="width: 170px;"/>
               <col style="width: 60px;"/>
               <col style="width: 60px;"/>
               <col style="width: auto;"/>
               <tr>
                   <!-- 性别 -->
                   <td class="entry_name" style="width:110px;">性别：</td>
                   <td>
                   </td>
                   <td>
                       <g:radioList name="rpa00201Form.sexId" list="rpa00201Form.sexDataSource" listKey="recordCode" listValue="recordValue" cssErrorClass="error"/>
                   </td>
                   <!-- 出生日期 -->
                   <td style="text-align: right;width:60px;padding-top: 5px;">出生日期：</td>
                   <td>
                       <h:calendar form="rpa00201Form" id="birthday" dateValue="birthday"></h:calendar>
                   </td>
                   <!-- 年齢 -->
                   <td style="text-align: right;padding-top: 5px;">年龄：</td>
                   <td>
                       <h:readonlyfield id="birthday_age" name="rpa00201Form.age" style="text-align: right;width:20px;"></h:readonlyfield>岁
                   </td>
                   <td>
                       <a href="javascript:void(0);" onclick="getAge('birthday')" class="aid_btn" style="width: auto;"><span>自动计算</span></a>
                   </td>
               </tr>
             </table>
             <table class="entry_content_table gamen_layout1">
              
               <tr>
                   <!-- 邮政编码 -->
                   <td class="entry_name" style="width: 110px;">邮政编码：</td>
                   <td style="width: 32px;"></td>
                   <td colspan="1">
                        <s:textfield id="postNumber" name="rpa00201Form.postNumber" style="width:65px;ime-mode:disabled;" maxlength="7" cssErrorClass="error"/>
                   </td>
               </tr>
               <tr>
                   <!-- 居住地 -->
                   <td class="entry_name" style="width:110px;">居住地：</td>
                   <td></td>
                   <td colspan="4">
                       <s:textfield id="addr" name="rpa00201Form.addr" style="width:400px;" maxlength="400" ></s:textfield>
                   </td>
                   <td></td>
               </tr>
               <tr>
                   <!-- 手机号码 -->
                   <td class="entry_name" style="width:110px;">手机号码：</td>
                   <td><h:mustentryicon/></td>
                   <td >
                       <s:textfield id="ceelNumber" name="rpa00201Form.ceelNumber" cssStyle="width:160px;" cssErrorStyle="width:160px;" maxlength="15" cssErrorClass="error" cssClass="mustdata"></s:textfield>
                   </td>
                   <!-- 座机号码 -->
                   <td class="entry_name">座机号码 ：</td>
                   <td ><s:textfield id="phoneNumber" name="rpa00201Form.phoneNumber" style="width:160px;" maxlength="11" cssErrorClass="error"></s:textfield></td>
               </tr>
               <tr>
                   <!-- 身份证号码-->
                   <td class="entry_name" style="width:110px;">身份证号码：</td>
                   <td></td>
                   <td colspan="3">
                       <s:textfield id="idNumber" name="rpa00201Form.idNumber" style="width:300px;" maxlength="50" cssErrorClass="error" ></s:textfield>
                   </td>
                   <td></td>
               </tr>
               <tr>
                   <!-- 电子邮箱-->
                   <td class="entry_name" style="width:110px;">电子邮箱：</td>
                   <td></td>
                   <td colspan="3">
                       <s:textfield id="email" name="rpa00201Form.email" style="width:300px;" maxlength="50" cssErrorClass="error" ></s:textfield>
                   </td>
                   <td></td>
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