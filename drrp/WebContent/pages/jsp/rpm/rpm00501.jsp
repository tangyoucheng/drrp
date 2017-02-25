<!DOCTYPE html >
<%--
/*
 * Copyright(c) 2016 
 */
/*
 * 推广信息添加
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
    <s:form id="rpm00501Action" name="rpm00501Form" action="rpm00501Action" method="post" validate="true" theme="simple">
        <div id="contents">
        <div id="two_main_colum">
        
             <h:normaltitleinbtn value="rpm00501"/>
             <h:messege/>
             <table class="entry_content_table input_condition">
               <col style="width:130px;"/>
               <col style="width:35px;"/>
               <col style="width:186px;"/>
               <col style="width: auto;"/>
               <tr>
                   <td class="entry_name" style="width:130px;">推广信息ID：</td>
                   <td><h:mustentryicon /></td>
                   <td>
                       <s:textfield id="adId" name="rpm00501Form.adId" cssErrorClass="error"   maxlength="50" cssClass="mustdata" ></s:textfield>
                   </td>
                   <td></td>
               </tr>
             </table>
             <table class="entry_content_table gamen_layout1" >
               <tr>
                   <td class="entry_name" style="width:130px;">推广信息名：</td>
                   <td style="width:35px;" class="padding_0"><h:mustentryicon /></td>
                   <td>
                      <s:textfield id="adName"  name="rpm00501Form.adName" cssErrorClass="error" cssStyle="width:400px;" cssErrorStyle="width:400px;"  maxlength="50" showPassword="true" cssClass="mustdata" ></s:textfield>
                   </td>
               </tr>
               <tr>
                   <td class="entry_name" style="width:130px;">推广信息URL：</td>
                   <td style="width:35px;" class="padding_0"><h:mustentryicon /></td>
                   <td>
                       <s:textfield id="adUrl" name="rpm00501Form.adUrl" cssErrorClass="error" cssStyle="width:500px;" cssErrorStyle="width:500px;" maxlength="50" showPassword="true" cssClass="mustdata" ></s:textfield>
                   </td>
               </tr>
               <tr>
                   <td class="entry_name" style="width:130px;">推广信息状态：</td>
                   <td ></td>
                   <td>
                       <s:checkbox id="dataSelected" name="rpm00501Form.checkedFlag"></s:checkbox>推广
                   </td>
               </tr>
               <tr>
                   <td class="entry_name" style="width:130px;">备注：</td>
                   <td ></td>
                   <td>
                       <s:textarea id="notes" name="rpm00501Form.notes" rows="2" cols="60" cssErrorClass="error" maxlength="255" ></s:textarea>
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