<!DOCTYPE html >
<%--
/*
 * Copyright(c) 2016 
 */
/*
 * 处方审核
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
<%-- <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/pages/css/schm/rpa00105.css" /> --%>
<script type="text/javascript" src="<%=request.getContextPath()%>/pages/csjs/lib/lodop/LodopFuncs.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/pages/csjs/rpa/rpa00105.js"></script>
<script type="text/javascript">
    var nonPictureUrl = "<%=request.getContextPath()%>/pages/img/defaultPicture.gif";
</script>
</head>
<body>
    <s:form id="rpa00105Action" name="rpa00105Form" action="rpa00105Action" method="post" validate="true" theme="simple">
        <s:hidden id="prescriptionId" name="rpa00105Form.prescriptionId"></s:hidden>
        <s:hidden id="patientId" name="rpa00105Form.patientId"></s:hidden>
        <s:hidden id="fileContents" name="rpa00105Form.fileContents"></s:hidden>
        <s:hidden name="rpa00105Form.lastUpdateDatePrescription"></s:hidden>
        <s:hidden name="rpa00105Form.prescriptionStatus"></s:hidden>
        <s:hidden id="firstReviewFlag" name="rpa00105Form.firstReviewFlag"></s:hidden>
        <s:hidden id="hiddenPrescriptionNumber" name="rpa00105Form.prescriptionNumber"></s:hidden>
        <div id="contents">
        <div id="two_main_colum">
        
            <h:normaltitleinbtn value="rpa00105"/>
            <h:messege/>
    
            <table class="entry_content_table ">
              <col style="width:100px;"/>
              <col style="width:35px;"/>
              <col style="width:210px;"/>
              <col style="width:100px;"/>
              <col style="width:35px;"/>
              <col style="width: auto;"/>
              <tr>
                  <td class="entry_name" style="width:100px;" >患者姓名：</td>
                  <td></td>
                  <td>
                      <h:readonlyfield id="patientName" name="rpa00105Form.patientName" ></h:readonlyfield>
                  </td>
                  <td class="entry_name" style="width:100px;">处方日期：</td>
                  <td></td>
                  <td>
                      <h:readonlyfield id="prescriptionCreateDate" name="rpa00105Form.prescriptionCreateDate" style="width:170px;"></h:readonlyfield>
                  </td>
              </tr>
            </table>

            <table class="entry_content_table gamen_layout1">
               <col style="width: 100px;"/>
               <col style="width: 35px;"/>
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
                       <h:readonlyfield id="sex" name="rpa00105Form.sex" style="width:30px;"></h:readonlyfield>
                   </td>
                   <!-- 出生日期 -->
                   <td style="text-align: right;padding-top: 5px;">出生日期：</td>
                   <td>
                       <h:readonlyfield name="rpa00105Form.birthday" id="birthday" ></h:readonlyfield>
                   </td>
                   <!-- 年齢 -->
                   <td style="text-align: right;padding-top: 5px;">年龄：</td>
                   <td>
                       <h:readonlyfield id="age" name="rpa00105Form.age" style="text-align: right;width:20px;"></h:readonlyfield>岁
                   </td>
                   <td>
                   </td>
               </tr>
            </table>
            <table class="entry_content_table ">
              <col style="width:100px;"/>
              <col style="width:35px;"/>
              <col style="width:210px;"/>
              <col style="width:100px;"/>
              <col style="width:35px;"/>
              <col style="width:auto;"/>
              <tr>
                  <td class="entry_name" style="width:100px;">门诊/住院号：</td>
                  <td></td>
                  <td>
                      <h:readonlyfield id="inpatientNumber" name="rpa00105Form.inpatientNumber" ></h:readonlyfield>
                  </td>
                  <td class="entry_name" style="width:100px;">科室：</td>
                  <td></td>
                  <td>
                      <h:readonlyfield id="department" name="rpa00105Form.department" ></h:readonlyfield>
                  </td>
              </tr>
              <tr>
                  <td class="entry_name" style="width:100px;">床号：</td>
                  <td></td>
                  <td>
                      <h:readonlyfield id="bedNo" name="rpa00105Form.bedNo" ></h:readonlyfield>
                  </td>
                  <td class="entry_name" style="width:100px;">过敏史：</td>
                  <td></td>
                  <td>
                      <h:readonlyfield id="allergyHistory" name="rpa00105Form.allergyHistory" ></h:readonlyfield>
                  </td>
              </tr>
              <tr>
                  <td class="entry_name" style="width:100px;">临床诊断：</td>
                  <td></td>
                  <td colspan="4">
                      <h:readonlyfield id="diagnosis" name="rpa00105Form.diagnosis" style="width:500px;"></h:readonlyfield>
                  </td>
              </tr>
              <tr>
                  <td class="entry_name" style="width:100px;">手机号码：</td>
                  <td></td>
                  <td>
                      <h:readonlyfield id="ceelNumber" name="rpa00105Form.ceelNumber" ></h:readonlyfield>
                  </td>
                  <td class="entry_name" style="width:100px;">身份证号码：</td>
                  <td></td>
                  <td>
                      <h:readonlyfield id="idNumber" name="rpa00105Form.idNumber" ></h:readonlyfield>
                  </td>
              </tr>
              <tr>
                  <td class="entry_name" style="width:100px;">居住地：</td>
                  <td></td>
                  <td colspan="4">
                      <h:readonlyfield id="addr" name="rpa00105Form.addr" style="width:500px;" ></h:readonlyfield>
                  </td>
              </tr>

              <s:if test="rpa00105Form.subForm1.size > 0">
              <tr>
                  <td class="entry_name" style="width:100px;">药品处方：</td>
                  <td></td>
                  <td id="addDrugInfoTr" colspan="4">
                     <table id="table_drguListInfo" class="row_table" style="width: 600px;margin-left: 0px;">
                         <col width="7%" />
                         <col width="auto" />
                         <col width="25%" />
                         <col width="100px" />
                         <col width="100px" />
                         <thead>
                         <tr>
                             <th style="font-size:1em;vertical-align:middle;">NO</th>
                             <th style="font-size:1em;vertical-align:middle;">药品名称</th>
                             <th style="font-size:1em;vertical-align:middle;">厂商名称</th>
                             <th style="font-size:1em;vertical-align:middle;">价格(元)/规格</th>
                             <th style="font-size:1em;vertical-align:middle;">数量</th>
                         </tr>
                         </thead>
                         <tbody>
                         <s:iterator status="listData" value="rpa00105Form.subForm1">
                             <tr>
                                 <!-- NO  -->
                                 <td style="font-size:1em;text-align: center;vertical-align:middle;">
                                     <g:property value="#listData.index + 1" /></td>
                                 <!-- 药品名称  -->
                                 <td title="<s:property value="#rpa0010501Dto.drugName" /> " class="txt_overflow" style="vertical-align:middle;">
                                         <s:property value="#rpa0010501Dto.drugName" /> 
                                 </td>
                                 <!-- 厂商名称  -->
                                 <td title="<s:property value="#rpa0010501Dto.manufacturerName" /> " class="txt_overflow" style="vertical-align:middle;">
                                         <s:property value="#rpa0010501Dto.manufacturerName" /> 
                                 </td>
                                 <!-- 价格/规格  -->
                                 <td title="<s:property value="#rpa0010501Dto.price" />/<s:property value="#rpa0010501Dto.unit" /> " class="txt_overflow" style="vertical-align:middle;">
                                         <s:property value="#rpa0010501Dto.price" />/<s:property value="#rpa0010501Dto.unit" />
                                 </td>
                                 <!-- 数量  -->
                                 <td>
                                         <s:property value="#rpa0010501Dto.quantity" />
                                         <s:hidden name="rpa00105Form.subForm1[%{#listData.index}].drugId" />
                                         <s:hidden name="rpa00105Form.subForm1[%{#listData.index}].drugName" />
                                         <s:hidden name="rpa00105Form.subForm1[%{#listData.index}].manufacturerName" />
                                         <s:hidden name="rpa00105Form.subForm1[%{#listData.index}].price" />
                                 </td>
                             </tr>
                         </s:iterator>
                         </tbody>
                     </table>
                  </td>
              </tr>
              </s:if>
              
              <tr>
                  <td class="entry_name" style="width:100px;">处方类型：</td>
                  <td></td>
                  <td colspan="2">
                      <g:radioList name="rpa00105Form.prescriptionType" list="rpa00105Form.prescriptionTypeDataSource" listKey="recordCode" listValue="recordValue" cssErrorClass="error"/>
                  </td>
                  <td colspan="2">
                  </td>
              </tr>
              <tr id="prescriptionContent">
                  <td class="entry_name" style="width:100px;">处方内容：</td>
                  <td></td>
                  <td colspan="4">
                      <s:textarea id="prescriptionContent" name="rpa00105Form.prescriptionContent" rows="10" cols="35" cssStyle="font-size:24px;padding-bottom:15px;" cssErrorStyle="font-size:24px;" cssClass="readonlydata" readonly="true" cssErrorClass="error" maxlength="500" ></s:textarea>
                  </td>
              </tr>
              <tr id="prescriptionFilePreview">
                  <td class="entry_name" style="width:100px;">处方图片：</td>
                  <td></td>
                  <td colspan="4" >
                    <g:img id="kaoPicture" onclick="doEnlargeImage();" cssStyle="width:400px; height: 300px;" /> 
                  </td>
              </tr>
              <tr>
                  <td class="entry_name" style="width:100px;">医师：</td>
                  <td></td>
                  <td >
                      <h:readonlyfield id="prescriptionCreateUserName" name="rpa00105Form.prescriptionCreateUserName"></h:readonlyfield>
                  </td>
                  <td class="entry_name" style="width:100px;">金额(元)：</td>
                  <td></td>
                  <td>
                      <h:readonlyfield id="diagnosis" name="rpa00105Form.price" ></h:readonlyfield>
                  </td>
              </tr>
              <tr>
                  <td class="entry_name" style="width:100px;">调配员：</td>
                  <td></td>
                  <td colspan="4">
                      <h:readonlyfield id="dispenseUserName" name="rpa00105Form.dispenseUserName"></h:readonlyfield>
                  </td>
              </tr>
              <tr>
                  <td class="entry_name" style="width:100px;">审核员：</td>
                  <td></td>
                  <td colspan="4">
                      <h:readonlyfield id="confirmUserName" name="rpa00105Form.confirmUserName"></h:readonlyfield>
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
                                        <!-- 登録 -->
                                    <s:if test="rpa00105Form.prescriptionStatus != 4 ">
                                        <td><g:submit method="doUpdate" id="touroku" cssClass="fix_mainbtn" onclick="return doUpdate($(this));" value=""/></td>
                                    </s:if>
                                    <s:if test="rpa00105Form.prescriptionStatus == 4 ">
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
                                        <!-- 登録 -->
	                                    <s:if test="rpa00105Form.prescriptionStatus != 4 ">
                                            <td>&nbsp;</td>
	                                    </s:if>
	                                    <s:if test="rpa00105Form.prescriptionStatus == 4 ">
                                            <td><a id="print" class="print_mainbtn" href="javascript:void(0);" onclick="doPrint();"></a></td>
	                                    </s:if>
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