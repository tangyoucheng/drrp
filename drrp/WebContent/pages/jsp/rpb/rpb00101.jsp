<!DOCTYPE html >
<%--
/*
 * Copyright(c) 2016 
 */
/*
 * 门店基本信息编辑
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
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/pages/css/rpb/rpb00101.css" />

<link rel="stylesheet"  type="text/css" href="<%=request.getContextPath()%>/pages/csjs/lib/jquery_fileupload/css/jquery.fileupload.css" />
<link rel="stylesheet"  type="text/css" href="<%=request.getContextPath()%>/pages/csjs/lib/jquery_fileupload/css/jquery.fileupload-ui.css" />

<script type="text/javascript" src="<%=request.getContextPath()%>/pages/csjs/lib/jquery_fileupload/vendor/jquery.ui.widget.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/pages/csjs/lib/jquery_fileupload/jquery.fileupload.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/pages/csjs/lib/jquery_fileupload/jquery.iframe-transport.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/pages/csjs/lib/jquery_fileupload/cors/jquery.xdr-transport.js"></script>

<script type="text/javascript" src="<%=request.getContextPath()%>/pages/csjs/lib/jquery_fileupload/jquery.fileupload-process.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/pages/csjs/lib/jquery_fileupload/jquery.fileupload-validate.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/pages/csjs/lib/jquery_fileupload/jquery.fileupload-ui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/pages/csjs/lib/jquery_fileupload/locale_zh_CN.js"></script>

<script type="text/javascript" src="<%=request.getContextPath()%>/pages/csjs/rpb/rpb00101.js"></script>

<script type="text/javascript">
    var nonPictureUrl = "<%=request.getContextPath()%>/pages/img/defaultPicture.gif";
</script>

</head>
<body>
    <s:form id="rpb00101Form" name="rpb00101Form" action="rpb00101Action" method="post" validate="true" theme="simple"  enctype="multipart/form-data" >
        <s:hidden name="rpb00101Form.storeId"></s:hidden>
        <s:hidden name="rpb00101Form.lastUpdateDate"></s:hidden>
        <s:hidden id="hiddenNewQrCodeImage" name="rpb00101Form.newQrCodeImage"></s:hidden>
        <s:hidden id="hiddenOldQrCodeImage" name="rpb00101Form.oldQrCodeImage"></s:hidden>

        <div id="contents">
        <div id="two_main_colum">
        
             <h:normaltitleinbtn value="rpb00101"/>
             <h:messege/>
             <table class="entry_content_table gamen_layout1" >
               <tr>
                   <td class="entry_name" style="width:130px;">门店名：</td>
                   <td style="width:35px;" class="padding_0"><h:mustentryicon /></td>
                   <td>
                      <s:textfield id="storeName"  name="rpb00101Form.storeName" cssErrorClass="error" cssStyle="width:300px;" cssErrorStyle="width:300px;"  maxlength="50" showPassword="true" cssClass="mustdata" ></s:textfield>
                   </td>
               </tr>
               <tr>
                   <td class="entry_name" style="width:130px;">处方单前缀：</td>
                   <td style="width:35px;" class="padding_0"><h:mustentryicon /></td>
                   <td>
                       <s:textfield id="rpCodePrefix" name="rpb00101Form.rpCodePrefix" cssErrorClass="error" maxlength="50" showPassword="true" cssClass="mustdata" ></s:textfield>
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
                   <td class="entry_name" style="width:130px;">门店二维码：</td>
                   <td><h:mustentryicon/></td>
                   <td colspan="3">
                        <!-- The fileupload-buttonbar contains buttons to add/delete files and start/cancel the upload -->
                        <div class="row fileupload-buttonbar">
                            <div class="col-lg-7">
                                <!-- The fileinput-button span is used to style the file input field as button -->
                                <span class="btn btn-success fileinput-button">
                                    <i class="glyphicon glyphicon-plus"></i>
                                    <span>添加图片...</span>
                                    <input type="file" name="rpb00101Form.file" multiple>
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
                   <td class="entry_name" style="width:130px;">二维码变更前：</td>
                   <td></td>
                   <td>
                       <g:img id="oldQrCodeImage" cssStyle="width:160px;height:160px;"/>
                   </td>
                   <td class="entry_name" style="width:130px;">二维码变更后：</td>
                   <td>
                       <g:img id="newQrCodeImage" cssStyle="width:160px;height:160px;"/>
                   </td>
               </tr>
             </table>
             <table class="entry_content_table gamen_layout1" >
               <tr>
                   <td class="entry_name" style="width:130px;">备注：</td>
                   <td ></td>
                   <td>
                       <s:textarea id="notes" name="rpb00101Form.notes" rows="2" cols="60" cssErrorClass="error" maxlength="255" ></s:textarea>
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
                                        <td><g:submit method="doUpdate" id="touroku" cssClass="edit_mainbtn" onclick="return doUpdate($(this));" value="" /></td>
                                        <s:if test="rpb00101Form.storeId != null && rpb00101Form.storeId != '' ">
                                            <td><g:submit method="doDelete" id="delete" cssClass="delete_mainbtn" onclick="return doDelete($(this));" value="" /></td>
                                        </s:if>
                                        <s:else>
                                            <td></td>
                                        </s:else>
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