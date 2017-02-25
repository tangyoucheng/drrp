<!DOCTYPE html >
<%--
/*
 * Copyright(c) 2016 
 */
/*
 * 添加用户
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
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/pages/css/rpm/rpm00203_bootstrap.css" />

<link rel="stylesheet"  type="text/css" href="<%=request.getContextPath()%>/pages/csjs/lib/jquery_fileupload/css/jquery.fileupload.css" />
<link rel="stylesheet"  type="text/css" href="<%=request.getContextPath()%>/pages/csjs/lib/jquery_fileupload/css/jquery.fileupload-ui.css" />

    
<%-- <script type="text/javascript" src="<%=request.getContextPath()%>/pages/csjs/lib/blueimp/tmpl.min.js"></script> --%>
<%-- <script type="text/javascript" src="<%=request.getContextPath()%>/pages/csjs/lib/blueimp/load-image.all.min.js"></script> --%>
<%-- <script type="text/javascript" src="<%=request.getContextPath()%>/pages/csjs/lib/blueimp/jquery.blueimp-gallery.min.js"></script> --%>

<script type="text/javascript" src="<%=request.getContextPath()%>/pages/csjs/lib/jquery_fileupload/vendor/jquery.ui.widget.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/pages/csjs/lib/jquery_fileupload/jquery.iframe-transport.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/pages/csjs/lib/jquery_fileupload/jquery.fileupload.js"></script>

<script type="text/javascript" src="<%=request.getContextPath()%>/pages/csjs/lib/jquery_fileupload/jquery.fileupload-process.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/pages/csjs/lib/jquery_fileupload/jquery.fileupload-validate.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/pages/csjs/lib/jquery_fileupload/jquery.fileupload-ui.js"></script>
<%-- <script type="text/javascript" src="<%=request.getContextPath()%>/pages/csjs/lib/jquery_fileupload/jquery.fileupload-image.js"></script> --%>
<%-- <script type="text/javascript" src="<%=request.getContextPath()%>/pages/csjs/lib/jquery_fileupload/jquery.fileupload-audio.js"></script> --%>
<%-- <script type="text/javascript" src="<%=request.getContextPath()%>/pages/csjs/lib/jquery_fileupload/jquery.fileupload-video.js"></script> --%>



<%-- <script type="text/javascript" src="<%=request.getContextPath()%>/pages/csjs/lib/jquery_fileupload/jquery.fileupload-angular.js"></script> --%>
<%-- <script type="text/javascript" src="<%=request.getContextPath()%>/pages/csjs/lib/jquery_fileupload/cors/jquery.postmessage-transport.js"></script> --%>
<%-- <script type="text/javascript" src="<%=request.getContextPath()%>/pages/csjs/lib/jquery_fileupload/cors/jquery.xdr-transport.js"></script> --%>
<script type="text/javascript" src="<%=request.getContextPath()%>/pages/csjs/lib/jquery_fileupload/locale_zh_CN.js"></script>
<%-- <script type="text/javascript" src="<%=request.getContextPath()%>/pages/csjs/common/jquery.form.js"></script> --%>
<script type="text/javascript" src="<%=request.getContextPath()%>/pages/csjs/rpm/rpm00203.js"></script>

<script type="text/javascript">
    var nonPictureUrl = "<%=request.getContextPath()%>/pages/img/defaultPicture.gif";
</script>
</head>
<body>
    <s:form id="rpm00203Form" name="rpm00203Form" action="rpm00203Action" method="post" enctype="multipart/form-data" >
        <input type="hidden" name="rpm00203Form.lastUpdateDateUser"  value="${rpm00203Form.lastUpdateDateUser }">
        <input type="hidden" name="rpm00203Form.lastUpdateDateUserProfile"  value="${rpm00203Form.lastUpdateDateUserProfile }">
        <input type="hidden" name="rpm00203Form.lastUpdateDateUserRole"  value="${rpm00203Form.lastUpdateDateUserRole }">
        <input type="hidden" id="hiddenOldIdentityImage" name="rpm00203Form.oldIdentityImage"  value="${rpm00203Form.oldIdentityImage }">
        <input type="hidden" id="hiddenNewIdentityImage" name="rpm00203Form.newIdentityImage"  value="${rpm00203Form.newIdentityImage }">
        
        <div id="contents">
        <div id="two_main_colum">
        
             <h:normaltitleinbtn value="rpm00203"/>
             <h:messege/>
    
             <table class="entry_content_table gamen_layout1 input_condition">
               <col style="width:130px;"/>
               <col style="width:35px;"/>
               <col style="width:186px;"/>
               <col style="width: auto;"/>
               <tr>
                   <td colspan="3">＜用户基本信息＞</td>
               </tr>
               <tr>
                   <!-- 用户ID -->
                   <td class="entry_name" style="width:130px;">用户ID：</td>
                   <td><h:mustentryicon /></td>
                   <td>
                       <h:readonlyfield id="userId" name="rpm00203Form.userId" ></h:readonlyfield>
                   </td>
                   <td></td>
               </tr>
             </table>
             <table class="entry_content_table gamen_layout1" >
               <tr>
                   <!-- 密码-->
                   <td class="entry_name" style="width:130px;">密码：</td>
                   <td style="width:35px;" class="padding_0"><h:mustentryicon /></td>
                   <td>
                           <s:password id="password"  name="rpm00203Form.password" cssErrorClass="error" style="width:240px;" maxlength="50" showPassword="true" cssClass="mustdata" />
                   </td>
               </tr>
               <tr>
                   <!-- 有效期间 -->
                   <s:if test="rpm00203Form.roleId == 'admin' ">
	                   <td class="entry_name"  >有效期间：</td>
	                   <td></td>
	                   <td>
	                       <h:readonlyfield id="startDate" name="rpm00203Form.startDate" ></h:readonlyfield>
	                       <h:readonlyfield id="endDate" name="rpm00203Form.endDate" ></h:readonlyfield>
	                   </td>
                   </s:if>
                   <s:if test="rpm00203Form.roleId != 'admin' ">
	                   <td class="entry_name"  >有效期间：</td>
	                   <td><h:mustentryicon /></td>
	                   <td>
	                       <h:calendar form="rpm00203Form" id="startDate" dateValue="startDate"></h:calendar>至
	                       <h:calendar form="rpm00203Form" id="endDate" dateValue="endDate"></h:calendar>
	                   </td>
                   </s:if>
               </tr>
             </table>
             <table class="entry_content_table gamen_layout1">
               <col style="width: 130px;"/>
               <col style="width: 40px;"/>
               <col style="width: auto;"/>
               <tr>
                   <!-- 备注 -->
                   <td class="entry_name" style="width:130px;">备注：</td>
                   <td ></td>
                   <td>
                       <s:textarea id="notes" name="rpm00203Form.notes" rows="2" cols="60" cssErrorClass="error"  maxlength="255" ></s:textarea>
                   </td>
               </tr>
             </table>
             <table class="entry_content_table gamen_layout2">
               <tr>
                   <!--用户补充信息 -->
                   <td >＜用户补充信息＞</td>
               </tr>
             </table>
             <table class="entry_content_table gamen_layout1">
               <col style="width: 130px;"/>
               <col style="width: 35px;"/>
               <col style="width:160px;"/>
               <col style="width: 80px;"/>
               <col style="width:auto;"/>
               <tr>
                   <!-- 姓名 -->
                   <td class="entry_name" style="width:130px;">姓名：</td>
                   <td><h:mustentryicon/></td>
                   <td >
                       <s:textfield id="userName" name="rpm00203Form.userName" cssErrorClass="error"  cssErrorStyle="width:160px;" style="width:160px;"  maxlength="120" cssClass="mustdata" ></s:textfield>
                   </td>
                   <td class="entry_name" style="width:130px;">角色：</td>
                   <td >
                       <s:select name="rpm00203Form.roleId" list="rpm00203Form.roleDataSource" listKey="recordCode" listValue="recordValue" cssStyle="width:160px;" cssErrorClass="error" />
                       
                   </td>
               </tr>
             </table>
             <table class="entry_content_table gamen_layout1">
               <col style="width: 130px;"/>
               <col style="width: 35px;"/>
               <col style="width:160px;"/>
               <col style="width: 80px;"/>
               <col style="width:auto;"/>
               <tr>
                   <td class="entry_name" style="width:130px;">证件图片：</td>
                   <td><h:mustentryicon/></td>
                   <td colspan="3">
	                    <!-- The fileupload-buttonbar contains buttons to add/delete files and start/cancel the upload -->
	                    <div class="row fileupload-buttonbar">
	                        <div class="col-lg-7">
	                            <!-- The fileinput-button span is used to style the file input field as button -->
	                            <span class="btn btn-success fileinput-button">
	                                <i class="glyphicon glyphicon-plus"></i>
	                                <span>添加图片...</span>
	                                <input type="file" name="rpm00203Form.file" multiple>
	                            </span>
	                            <button type="reset" class="btn btn-warning cancel">
	                                <i class="glyphicon glyphicon-ban-circle"></i>
	                                <span>取消上传</span>
	                            </button>
	                        </div>
	                    </div>
	                    <!-- The table listing the files available for upload/download -->
	                    <table role="presentation" class="table table-striped"><tbody class="files"></tbody></table>
                   </td>
               </tr>
               <tr>
                   <td class="entry_name" style="width:130px;">证件变更前：</td>
                   <td></td>
                   <td>
                       <g:img id="oldidentityImage" cssStyle="width:160px;"/>
                   </td>
                   <td class="entry_name" style="width:130px;">证件变更后：</td>
                   <td>
                       <g:img id="newidentityImage" cssStyle="width:160px;"/>
                   </td>
               </tr>
             </table>
             <table class="entry_content_table gamen_layout1">
               <col style="width: 130px;"/>
               <col style="width: 35px;"/>
               <col style="width: 120px;"/>
               <col style="width: 80px;"/>
               <col style="width: 170px;"/>
               <col style="width: 60px;"/>
               <col style="width: 60px;"/>
               <col style="width: auto;"/>
               <tr>
                   <!-- 性别 -->
                   <td class="entry_name" style="width:130px;">性别：</td>
                   <td>
                   </td>
                   <td>
                       <g:radioList name="rpm00203Form.sexId" list="rpm00203Form.sexDataSource" listKey="recordCode" listValue="recordValue" cssErrorClass="error"/>
                   </td>
                   <!-- 出生日期 -->
                   <td style="text-align: right;width:60px;padding-top: 5px;">出生日期：</td>
                   <td>
                       <h:calendar form="rpm00203Form" id="birthday" dateValue="birthday"></h:calendar>
                   </td>
                   <!-- 年齢 -->
                   <td style="text-align: right;padding-top: 5px;">年龄：</td>
                   <td>
                       <h:readonlyfield id="birthday_age" name="rpm00203Form.age" style="text-align: right;width:20px;"></h:readonlyfield>岁
                   </td>
                   <td>
                       <a href="javascript:void(0);" onclick="getAge('birthday')" class="aid_btn" style="width: auto;"><span>自动计算</span></a>
                   </td>
               </tr>
             </table>
             <table class="entry_content_table gamen_layout1">
              
               <tr>
                   <!-- 邮政编码 -->
                   <td class="entry_name" style="width: 130px;">邮政编码：</td>
                   <td style="width: 32px;"></td>
                   <td colspan="3">
                        <s:textfield id="postNumber" name="rpm00203Form.postNumber" 
                                        style="width:65px;ime-mode:disabled;" maxlength="7" 
                                        cssErrorClass="error"/>
                   </td>
               </tr>
               <tr>
                   <!-- 居住地 -->
                   <td class="entry_name" style="width:130px;">居住地：</td>
                   <td></td>
                   <td colspan="3">
                       <s:textfield id="addr" name="rpm00203Form.addr" style="width:400px;" maxlength="400" ></s:textfield>
                   </td>
               </tr>
               <tr>
                   <!-- 手机号码 -->
                   <td class="entry_name" style="width:130px;">手机号码：</td>
                   <td></td>
                   <td style="width: 160px;" >
                       <s:textfield id="ceelNumber" name="rpm00203Form.ceelNumber" style="width:160px;" maxlength="15" cssErrorClass="error"></s:textfield>
                   </td>
                   <!-- 座机号码 -->
                   <td class="entry_name" style="width:135px;">座机号码：</td>
                   <td ><s:textfield id="phoneNumber" name="rpm00203Form.phoneNumber" style="width:160px;" maxlength="11" cssErrorClass="error"></s:textfield></td>
               </tr>
               <tr>
                   <!-- 电子邮箱-->
                   <td class="entry_name" style="width:130px;">电子邮箱：</td>
                   <td></td>
                   <td colspan="3">
                       <s:textfield id="email" name="rpm00203Form.email" style="width:300px;" maxlength="50" cssErrorClass="error" ></s:textfield>
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
                                         <td><g:submit action="rpm00202Action" method="doBack" cssClass="back_mainbtn" /></td>
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
                                        <td><g:submit method="doUpdate" id="update" cssClass="edit_mainbtn" onclick="return doUpdate($(this));" value="" /></td>
                                        <!-- 削除 -->
                                        <s:if test="rpm00203Form.roleId == 'admin' ">
                                        <td>&nbsp;</td>
                                        </s:if>
                                        <s:if test="rpm00203Form.roleId != 'admin' ">
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