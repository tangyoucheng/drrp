<!DOCTYPE html >
<%--
/*
 * Copyright(c) 2016 
 */
/*
 * 处方手写录入
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
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/pages/css/rpa/rpa00150.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/pages/csjs/lib/jSignature/flashcanvas.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/pages/csjs/lib/jSignature/jSignature.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/pages/csjs/lib/jSignature/jSignature.min.noconflict.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/pages/csjs/rpa/rpa00150.js"></script>
</head>
<body>
    <s:form id="rpa00150Action" name="rpa00150Form" action="rpa00150Action" method="post" validate="true" theme="simple">
        <input type="hidden" id="rpa00150PageType" name="rpa00150Form.pageType"  value="${rpa00150Form.pageType }">
        <div id="contents">
        <div id="two_main_colum" style="padding-bottom: 0px;">
        
            <s:if test="rpa00150Form.pageType == 'readonly'">
                <g:img id="enlargePicture"  cssStyle="height:1120px;width:850px;" /> 
            </s:if>
            <s:else>
	            <h:normaltitleinbtn value="rpa00150"/>
	            <h:messege/>
	            <div id="signatureparent">
	                <div id="signature"></div>
	            </div>
	            <div class="shrinkwidth_parent"><div id="signaturetools" class="shrinkwidth_subject"></div></div>
	            
	                    
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
	                                        <td>
	                                            <g:submit id="fix_button" cssClass="fix_mainbtn" cssStyle="display: none;" value="" onclick="return setSelectedData();" />
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
             </s:else>
        </div>
    </div>
    </s:form>
    <g:popuperror />
</body>
</html>