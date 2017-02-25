<!DOCTYPE html >
<%--
/*
 * Copyright(c) 2016 
 */
/*
 * 添加处方
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
<%-- <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/pages/css/rpa/rpa00101.css" /> --%>
<script type="text/javascript" src="<%=request.getContextPath()%>/pages/csjs/rpa/rpa00101.js"></script>
<%-- <script type="text/javascript" src="<%=request.getContextPath()%>/pages/csjs/common/tagFile.js"></script> --%>
<%-- <script type="text/javascript" src="<%=request.getContextPath()%>/pages/csjs/common/jquery.form.js"></script> --%>
<%-- <script type="text/javascript" src="<%=request.getContextPath()%>/pages/csjs/lib/jSignature/flashcanvas.js"></script> --%>
<%-- <script type="text/javascript" src="<%=request.getContextPath()%>/pages/csjs/lib/jSignature/jSignature.min.js"></script> --%>
<%-- <script type="text/javascript" src="<%=request.getContextPath()%>/pages/csjs/lib/jSignature/jSignature.min.noconflict.js"></script> --%>
<script type="text/javascript">
    var nonPictureUrl = "<%=request.getContextPath()%>/pages/img/defaultPicture.gif";
</script>
</head>
<body>
    <s:form id="rpa00101Action" name="rpa00101Form" action="rpa00101Action" method="post" validate="true" theme="simple" enctype="multipart/form-data">
        <input type="hidden" id="patientId" name="rpa00101Form.patientId"  value="${rpa00101Form.patientId }">
        <s:hidden id="fileContents" name="rpa00101Form.fileContents"></s:hidden>
        <div id="contents">
        <div id="two_main_colum">
        
            <h:normaltitleinbtn value="rpa00101"/>
            <h:messege/>
    
            <table class="entry_content_table ">
              <col style="width:100px;"/>
              <col style="width:40px;"/>
              <col style="width:210px;"/>
              <col style="width:100px;"/>
              <col style="width:35px;"/>
              <col style="width: auto;"/>
              <tr>
                  <td class="entry_name" style="width:100px;" >患者姓名：</td>
                  <td><h:mustentryicon /></td>
                  <td>
                      <s:textfield id="patientName" name="rpa00101Form.patientName" cssStyle="width:173px;" cssErrorStyle="width:173px;" maxlength="15" cssErrorClass="error" cssClass="mustdata"></s:textfield>
                      <img onclick="doSearchPatient();" src="<%=request.getContextPath()%>/pages/img/icon_search.png" class="searchImg"/>
                  </td>
                  <td class="entry_name" style="width:100px;">当前日期：</td>
                  <td></td>
                  <td>
                      <h:readonlyfield id="prescriptionCreateDate" name="rpa00101Form.prescriptionCreateDate"></h:readonlyfield>
                  </td>
              </tr>
            </table>

            <table class="entry_content_table gamen_layout1">
               <col style="width: 100px;"/>
               <col style="width: 40px;"/>
               <col style="width: 120px;"/>
               <col style="width: 100px;"/>
               <col style="width: 170px;"/>
               <col style="width: 60px;"/>
               <col style="width: 60px;"/>
               <col style="width: auto;"/>
               <tr>
                   <!-- 性别 -->
                   <td class="entry_name" style="width:100px;">性别：</td>
                   <td>
                   </td>
                   <td>
                       <g:radioList name="rpa00101Form.sexId" list="rpa00101Form.sexDataSource" listKey="recordCode" listValue="recordValue" cssErrorClass="error"/>
                   </td>
                   <!-- 出生日期 -->
                   <td style="text-align: right;padding-top: 5px;">出生日期：</td>
                   <td>
                       <h:calendar form="rpa00101Form" id="birthday" dateValue="birthday"></h:calendar>
                   </td>
                   <!-- 年齢 -->
                   <td style="text-align: right;padding-top: 5px;">年龄：</td>
                   <td>
                       <h:readonlyfield id="birthday_age" name="rpa00101Form.age" style="text-align: right;width:20px;"></h:readonlyfield>岁
                   </td>
                   <td>
                       <a href="javascript:void(0);" onclick="getAge('birthday')" class="aid_btn" style="width: auto;"><span>自动计算</span></a>
                   </td>
               </tr>
            </table>
            <table class="entry_content_table ">
              <col style="width:100px;"/>
              <col style="width:40px;"/>
              <col style="width:210px;"/>
              <col style="width:100px;"/>
              <col style="width:35px;"/>
              <col style="width:auto;"/>
              <tr>
                  <td class="entry_name" style="width:100px;">门诊/住院号：</td>
                  <td></td>
                  <td>
                      <s:textfield id="inpatientNumber" name="rpa00101Form.inpatientNumber" cssErrorClass="error"   maxlength="50" ></s:textfield>
                  </td>
                  <td class="entry_name" style="width:100px;">科室：</td>
                  <td></td>
                  <td>
                      <s:textfield id="department" name="rpa00101Form.department" cssErrorClass="error"   maxlength="50" ></s:textfield>
                  </td>
              </tr>
              <tr>
                  <td class="entry_name" style="width:100px;">床号：</td>
                  <td></td>
                  <td>
                      <s:textfield id="bedNo" name="rpa00101Form.bedNo" cssErrorClass="error"   maxlength="50" ></s:textfield>
                  </td>
                  <td class="entry_name" style="width:100px;">过敏史：</td>
                  <td></td>
                  <td>
                      <s:textfield id="allergyHistory" name="rpa00101Form.allergyHistory" cssErrorClass="error"   maxlength="50"></s:textfield>
                  </td>
              </tr>
              <tr>
                  <td class="entry_name" style="width:100px;">临床诊断：</td>
                  <td></td>
                  <td colspan="4">
                      <s:textfield id="diagnosis" name="rpa00101Form.diagnosis" cssErrorClass="error" style="width:500px;" maxlength="50"></s:textfield>
                  </td>
              </tr>
              <tr>
                  <td class="entry_name" style="width:100px;">手机号码：</td>
                  <td><h:mustentryicon /></td>
                  <td>
                       <s:textfield id="ceelNumber" name="rpa00101Form.ceelNumber" cssStyle="width:173px;" cssErrorStyle="width:173px;" maxlength="15" cssErrorClass="error" cssClass="mustdata"></s:textfield>
                   </td>
                  <td class="entry_name" style="width:100px;">身份证号码：</td>
                  <td></td>
                  <td>
                      <s:textfield id="idNumber" name="rpa00101Form.idNumber" cssErrorClass="error" maxlength="20"></s:textfield>
                  </td>
              </tr>
              <tr>
                  <td class="entry_name" style="width:100px;">居住地：</td>
                  <td></td>
                  <td colspan="4">
                      <s:textfield id="addr" name="rpa00101Form.addr" style="width:500px;" cssErrorClass="error"></s:textfield>
                  </td>
              </tr>
              <tr>
                  <td class="entry_name" style="width:100px;">药品处方：</td>
                  <td></td>
                  <td id="addDrugInfoTr" colspan="4">
                     <a id="addDrugInfoButton" class="aid_btn" href="javascript:void(0);" onclick="doAddDrug();"><span>添加药品</span></a>
                     <br>
                     <s:if test="rpa00101Form.subForm1.size > 0">
                     <table id="table_drguListInfo" class="row_table" style="width: 600px;margin-left: 0px;">
                         <col width="7%" />
                         <col width="auto" />
                         <col width="25%" />
                         <col width="100px" />
                         <col width="100px" />
                         <col width="60px" />
                         <thead>
                         <tr>
                             <th style="font-size:1em;vertical-align:middle;">NO</th>
                             <th style="font-size:1em;vertical-align:middle;">药品名称</th>
                             <th style="font-size:1em;vertical-align:middle;">厂商名称</th>
                             <th style="font-size:1em;vertical-align:middle;">价格(元)</th>
                             <th style="font-size:1em;vertical-align:middle;">数量<h:mustentryicon style="margin-top:2px;" /></th>
                             <th style="font-size:1em;"></th>
                         </tr>
                         </thead>
                         <tbody>
                         <s:iterator status="listData" value="rpa00101Form.subForm1">
                             <tr>
                                 <!-- NO  -->
                                 <td style="font-size:1em;text-align: center;vertical-align:middle;">
                                     <g:property value="#listData.index + 1" /></td>
                                 <!-- 药品名称  -->
                                 <td title="<s:property value="#rpa0010101Dto.drugName" /> " class="txt_overflow" style="vertical-align:middle;">
                                         <s:property value="#rpa0010101Dto.drugName" /> 
                                 </td>
                                 <!-- 厂商名称  -->
                                 <td title="<s:property value="#rpa0010101Dto.manufacturerName" /> " class="txt_overflow" style="vertical-align:middle;">
                                         <s:property value="#rpa0010101Dto.manufacturerName" /> 
                                 </td>
                                 <!-- 价格  -->
                                 <td title="<s:property value="#rpa0010101Dto.price" /> " class="txt_overflow" style="vertical-align:middle;">
                                         <s:property value="#rpa0010101Dto.price" />
                                 </td>
                                 <!-- 数量  -->
                                 <td>
                                         <s:textfield name="rpa00101Form.subForm1[%{#listData.index}].quantity" cssClass="mustdata" cssErrorClass="error"  cssStyle="width:85px;" cssErrorStyle="width:85px;" maxlength="10"/>
                                 </td>
                                 <!-- 删除和隐藏变量  -->
                                 <td style="text-align: center;">
                                     <s:hidden name="rpa00101Form.subForm1[%{#listData.index}].drugId" />
                                     <s:hidden name="rpa00101Form.subForm1[%{#listData.index}].drugName" />
                                     <s:hidden name="rpa00101Form.subForm1[%{#listData.index}].manufacturerName" />
                                     <s:hidden name="rpa00101Form.subForm1[%{#listData.index}].price" />
                                     <a class="aid_btn_r" href="javascript:void(0);" onclick="doRemoveDrug(this.parentNode.parentNode);"><span>删除</span></a>
                                 </td>
                             </tr>
                         </s:iterator>
                         </tbody>
                     </table>
                     </s:if>
                  </td>
              </tr>
              <tr>
                  <td class="entry_name" style="width:100px;">处方类型：</td>
                  <td></td>
                  <td >
                      <g:radioList name="rpa00101Form.prescriptionType" list="rpa00101Form.prescriptionTypeDataSource" listKey="recordCode" listValue="recordValue" cssErrorClass="error"/>
                  </td>
                  <td colspan="3">
                      <a id="addPrescriptionImageButton" class="aid_btn" href="javascript:void(0);" onclick="doWritePrescription();"><span>添加图片</span></a>
                      <a id="clearPrescriptionContentButton" class="aid_btn" href="javascript:void(0);" onclick="return window.top.showAjaxConfirm(null, 'Q00001,operation_reset', null, clearPrescriptionContent);"><span>清空处方内容</span></a>
                  </td>
              </tr>
              <tr id="prescriptionContentTr">
                  <td class="entry_name" style="width:100px;">处方内容：</td>
                  <td><h:mustentryicon /></td>
                  <td colspan="4">
                      <s:textarea id="prescriptionContent" name="rpa00101Form.prescriptionContent" rows="10" cols="35" cssStyle="font-size:24px;" cssErrorStyle="font-size:24px;" cssErrorClass="error" maxlength="500"  cssClass="mustdata"></s:textarea>
                  </td>
              </tr>
              <tr id="prescriptionFilePreviewTr">
                  <td class="entry_name" style="width:100px;">处方图片：</td>
                  <td><h:mustentryicon /></td>
                  <td colspan="4" >
                    <g:img id="kaoPicture" onclick="doEnlargeImage();" cssStyle="width:400px; height: 300px;" /> 
                  </td>
              </tr>
            </table>
            <table class="entry_content_table gamen_layout1">
               <col style="width: 100px;"/>
               <col style="width: 40px;"/>
               <col style="width: 200px;"/>
               <col style="width: 60px;"/>
               <col style="width: 40px;"/>
               <col style="width: 210px;"/>
               <col style="width: auto;"/>
               <tr>
                  <td class="entry_name" style="width:100px;">医师：</td>
                  <td></td>
                  <td>
                      <h:readonlyfield id="prescriptionCreateUserName" name="rpa00101Form.prescriptionCreateUserName"></h:readonlyfield>
                  </td>
                  <td class="entry_name" style="width:60px;">金额(元)：</td>
                  <td><h:mustentryicon /></td>
                  <td>
                      <s:textfield id="sumPrice" name="rpa00101Form.price" cssErrorClass="error" cssStyle="width:173px;" cssErrorStyle="width:173px;" maxlength="50" cssClass="mustdata"></s:textfield>
                  </td>
                   <td>
                       
                       <a id="drugPriceAutoCalc" href="javascript:void(0);" onclick="doCalcDrugPrice()" class="aid_btn" 
                       style="width: auto;<s:if test="rpa00101Form.subForm1.size == 0">display:none;</s:if>"><span>自动计算</span></a>
                       
                   </td>
               </tr>
            </table>
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
                                        <td>&nbsp;</td>
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