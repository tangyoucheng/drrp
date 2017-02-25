<!DOCTYPE html >
<%--
/*
 * Copyright(c) 2016 
 */
/*
 * 打印患者条形码
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
<%-- <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/pages/css/schm/rpa00206.css" /> --%>
<script type="text/javascript" src="<%=request.getContextPath()%>/pages/csjs/lib/jquery_qrcode/jquery.qrcode.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/pages/csjs/lib/jquery_qrcode/qrcode.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/pages/csjs/lib/jquery_barcode/jquery-barcode.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/pages/csjs/lib/lodop/LodopFuncs.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/pages/csjs/rpa/rpa00206.js"></script>
<style type="text/css" media="print">
<!--
.guide_btn_area{display:none;}
-->
</style>
</head>
<body>
    <s:form id="rpa00206Form" name="rpa00206Form" action="rpa00206Action" method="post" validate="true" theme="simple">
        <s:hidden id="hiddenPrintUserId" name="rpa00206Form.userId"></s:hidden>
        <s:hidden name="rpa00206Form.userName"></s:hidden>
        <s:hidden name="rpa00206Form.ceelNumber"></s:hidden>
        <s:hidden id="barcodeFilePath" name="rpa00206Form.barcodeFilePath"></s:hidden>
        <s:hidden id="qrcodeFilePath" name="rpa00206Form.qrcodeFilePath"></s:hidden>
        <div id="contents">
        <div id="two_main_colum">
        
            <table style="width: 300px;">
            <tr >
                <td style="width: 80px;text-align: right;">姓名：</td>
                <td style="width: auto;"><s:property value="rpa00206Form.userName" /></td>
                <td style="width: 35%;" rowspan="2">
	                <s:if test="rpa00206Form.qrcodeFilePath!= null && rpa00206Form.qrcodeFilePath !=''">
	                    <g:img id="qrPicture" cssStyle="width:80px; height: 80px;" />
	                </s:if>
                </td>
            </tr>
            <tr>
                <td >&nbsp;</td>
                <td >&nbsp;</td>
            </tr>
            <tr>
                <td style="text-align: right;">手机号码：</td>
                <td ><s:property value="rpa00206Form.ceelNumber" /></td>
            </tr>
            </table>
            <table style="margin-top: 20px;">
            <tr>
                <td style="" id="barcodeTarget">
            </tr>
            </table>
            <br>
             
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
                                        <td>
                                        <a id="print" class="print_mainbtn" href="javascript:void(0);" onclick="doPrint();"></a>
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
    <g:download />
</body>
</html>