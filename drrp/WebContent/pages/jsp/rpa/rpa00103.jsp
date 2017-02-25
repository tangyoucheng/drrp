<!DOCTYPE html >
<%--
/*
 * Copyright(c) 2016 
 */
/*
 * 处方编辑
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
<%-- <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/pages/css/schm/rpa00103.css" /> --%>
<script type="text/javascript" src="<%=request.getContextPath()%>/pages/csjs/rpa/rpa00103.js"></script>
<script type="text/javascript">
    var nonPictureUrl = "<%=request.getContextPath()%>/pages/img/defaultPicture.gif";
</script>
</head>
<body>
    <s:form id="rpa00103Action" name="rpa00103Form" action="rpa00103Action" method="post" validate="true" theme="simple">
        <s:hidden id="prescriptionId" name="rpa00103Form.prescriptionId"></s:hidden>
        <s:hidden id="patientId" name="rpa00103Form.patientId"></s:hidden>
        <s:hidden id="fileContents" name="rpa00103Form.fileContents"></s:hidden>
        <s:hidden name="rpa00103Form.lastUpdateDatePrescription"></s:hidden>
        <s:hidden id="hiddenPrescriptionStatus" name="rpa00103Form.prescriptionStatus"></s:hidden>
        <div id="contents">
        <div id="two_main_colum">
        
            <h:normaltitleinbtn value="rpa00103"/>
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
                  <td></td>
                  <td>
                      <h:readonlyfield id="patientName" name="rpa00103Form.patientName" ></h:readonlyfield>
                  </td>
                  <td class="entry_name" style="width:100px;">处方日期：</td>
                  <td></td>
                  <td>
                      <h:readonlyfield id="prescriptionCreateDate" name="rpa00103Form.prescriptionCreateDate" style="width:170px;"></h:readonlyfield>
                  </td>
              </tr>
            </table>

            <table class="entry_content_table gamen_layout1">
               <col style="width: 100px;"/>
               <col style="width: 40px;"/>
               <col style="width: 120px;"/>
               <col style="width: 100px;"/>
               <col style="width: 160px;"/>
               <col style="width: 60px;"/>
               <col style="width: 60px;"/>
               <col style="width: auto;"/>
               <tr>
                   <!-- 性别 -->
                   <td class="entry_name" style="width:100px;">性别：</td>
                   <td>
                   </td>
                   <td>
                       <h:readonlyfield id="sex" name="rpa00103Form.sex" style="width:30px;"></h:readonlyfield>
                   </td>
                   <!-- 出生日期 -->
                   <td style="text-align: right;padding-top: 5px;">出生日期：</td>
                   <td>
                       <h:readonlyfield name="rpa00103Form.birthday" id="birthday" ></h:readonlyfield>
                   </td>
                   <!-- 年齢 -->
                   <td style="text-align: right;padding-top: 5px;">年龄：</td>
                   <td>
                       <h:readonlyfield id="age" name="rpa00103Form.age" style="text-align: right;width:20px;"></h:readonlyfield>岁
                   </td>
                   <td>
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
                      <s:textfield id="inpatientNumber" name="rpa00103Form.inpatientNumber" cssErrorClass="error"   maxlength="50" ></s:textfield>
                  </td>
                  <td class="entry_name" style="width:100px;">科室：</td>
                  <td></td>
                  <td>
                      <s:textfield id="department" name="rpa00103Form.department" cssErrorClass="error"   maxlength="50" ></s:textfield>
                  </td>
              </tr>
              <tr>
                  <td class="entry_name" style="width:100px;">床号：</td>
                  <td></td>
                  <td>
                      <s:textfield id="bedNo" name="rpa00103Form.bedNo" cssErrorClass="error"   maxlength="50" ></s:textfield>
                  </td>
                  <td class="entry_name" style="width:100px;">过敏史：</td>
                  <td></td>
                  <td>
                      <s:textfield id="allergyHistory" name="rpa00103Form.allergyHistory" cssErrorClass="error"   maxlength="50"></s:textfield>
                  </td>
              </tr>
              <tr>
                  <td class="entry_name" style="width:100px;">临床诊断：</td>
                  <td></td>
                  <td colspan="4">
                      <s:textfield id="diagnosis" name="rpa00103Form.diagnosis" cssErrorClass="error" style="width:500px;" maxlength="50"></s:textfield>
                  </td>
              </tr>
              <tr>
                  <td class="entry_name" style="width:100px;">手机号码：</td>
                  <td></td>
                  <td>
                      <h:readonlyfield id="ceelNumber" name="rpa00103Form.ceelNumber" ></h:readonlyfield>
                  </td>
                  <td class="entry_name" style="width:100px;">身份证号码：</td>
                  <td></td>
                  <td>
                      <h:readonlyfield id="idNumber" name="rpa00103Form.idNumber" ></h:readonlyfield>
                  </td>
              </tr>
              <tr>
                  <td class="entry_name" style="width:100px;">居住地：</td>
                  <td></td>
                  <td colspan="4">
                      <h:readonlyfield id="addr" name="rpa00103Form.addr" style="width:500px;" ></h:readonlyfield>
                  </td>
              </tr>
              <tr>
                  <td class="entry_name" style="width:100px;">药品处方：</td>
                  <td></td>
                  <td id="addDrugInfoTr" colspan="4">
                     <s:if test="rpa00103Form.prescriptionStatus != 4 ">
                     <a id="addDrugInfoButton" class="aid_btn" href="javascript:void(0);" onclick="doAddDrug();"><span>添加药品</span></a>
                     </s:if>
                     <br>
                     <s:if test="rpa00103Form.subForm1.size > 0">
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
                             <th style="font-size:1em;vertical-align:middle;">价格(元)/规格</th>
                             <th style="font-size:1em;vertical-align:middle;">数量<h:mustentryicon style="margin-top:2px;" /></th>
                             <th style="font-size:1em;"></th>
                         </tr>
                         </thead>
                         <tbody>
                         <s:iterator status="listData" value="rpa00103Form.subForm1">
                             <tr>
                                 <!-- NO  -->
                                 <td style="font-size:1em;text-align: center;vertical-align:middle;">
                                     <g:property value="#listData.index + 1" /></td>
                                 <!-- 药品名称  -->
                                 <td title="<s:property value="drugName" /> " class="txt_overflow" style="vertical-align:middle;">
                                         <s:property value="drugName" /> 
                                 </td>
                                 <!-- 厂商名称  -->
                                 <td title="<s:property value="manufacturerName" /> " class="txt_overflow" style="vertical-align:middle;">
                                         <s:property value="manufacturerName" /> 
                                 </td>
                                 <!-- 价格/规格  -->
                                 <td title="<s:property value="price" />/<s:property value="unit" /> " class="txt_overflow" style="vertical-align:middle;">
                                         <s:property value="price" />/<s:property value="unit" />
                                 </td>
                                 <!-- 数量  -->
                                 <td>
                                         <s:textfield name="rpa00103Form.subForm1[%{#listData.index}].quantity" cssClass="mustdata" cssErrorClass="error"  cssStyle="width:85px;" cssErrorStyle="width:85px;" maxlength="10"/>
                                 </td>
                                 <!-- 删除和隐藏变量  -->
                                 <td style="text-align: center;">
                                     <s:hidden name="rpa00103Form.subForm1[%{#listData.index}].drugId" />
                                     <s:hidden name="rpa00103Form.subForm1[%{#listData.index}].drugName" />
                                     <s:hidden name="rpa00103Form.subForm1[%{#listData.index}].manufacturerName" />
                                     <s:hidden name="rpa00103Form.subForm1[%{#listData.index}].price" />
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
                      <g:radioList name="rpa00103Form.prescriptionType" list="rpa00103Form.prescriptionTypeDataSource" listKey="recordCode" listValue="recordValue" cssErrorClass="error"/>
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
                      <s:textarea id="prescriptionContent" name="rpa00103Form.prescriptionContent" rows="10" cols="35" cssStyle="font-size:24px;" cssErrorStyle="font-size:24px;" cssErrorClass="error" maxlength="500"  cssClass="mustdata"></s:textarea>
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
                      <h:readonlyfield id="prescriptionCreateUserName" name="rpa00103Form.prescriptionCreateUserName"></h:readonlyfield>
                  </td>
                  <td class="entry_name" style="width:60px;">金额(元)：</td>
                  <td><h:mustentryicon /></td>
                  <td>
                      <s:textfield id="sumPrice" name="rpa00103Form.price" cssErrorClass="error" cssStyle="width:173px;" cssErrorStyle="width:173px;" maxlength="50" cssClass="mustdata"></s:textfield>
                  </td>
                   <td>
                       
                       <a id="drugPriceAutoCalc" href="javascript:void(0);" onclick="doCalcDrugPrice();" class="aid_btn" 
                       style="width: auto;<s:if test="rpa00103Form.subForm1.size == 0">display:none;</s:if>"><span>自动计算</span></a>
                       
                   </td>
               </tr>
            </table>
            <s:if test="rpa00103Form.prescriptionStatus == 4 ">
            <table class="entry_content_table gamen_layout1">
              <col style="width:100px;"/>
              <col style="width:40px;"/>
              <col style="width:210px;"/>
              <col style="width:100px;"/>
              <col style="width:35px;"/>
              <col style="width:auto;"/>
              <tr>
                  <td class="entry_name" style="width:100px;">调配员：</td>
                  <td></td>
                  <td colspan="4">
                      <h:readonlyfield id="dispenseUserName" name="rpa00103Form.dispenseUserName"></h:readonlyfield>
                  </td>
              </tr>
              <tr>
                  <td class="entry_name" style="width:100px;">审核员：</td>
                  <td></td>
                  <td colspan="4">
                      <h:readonlyfield id="confirmUserName" name="rpa00103Form.confirmUserName"></h:readonlyfield>
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
                                        <td><g:submit action="rpa00102Action" method="doBack" cssClass="back_mainbtn" /></td>
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
                                    <s:if test="rpa00103Form.prescriptionStatus != 4 ">
                                        <!-- 登録 -->
                                        <td><g:submit method="doUpdate" id="touroku" cssClass="edit_mainbtn" onclick="return doUpdate($(this));" value=""/></td>
                                        <!-- 削除 -->
                                        <td>
                                            <g:submit method="doDelete" id="sakujo" cssClass="delete_mainbtn" onclick="return doDelete($(this));" value="" />
                                        </td>
                                    </s:if>
                                    <s:if test="rpa00103Form.prescriptionStatus == 4 ">
                                        <td>&nbsp;</td>
                                        <td>&nbsp;</td>
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