<%--
/*
 * Copyright(c) 2011 
 */
/*
 *画像イメージ拡大表示
 *
 * @author l.z.q
 * @since 1.0
 *
 * 新規作成
 * DATE: 2012/04/26 NAME: l.z.q
 */
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/pages/jsp/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<h:head></h:head>
    <!--[if IE 9]> <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/pages/css/scz/scz0301_ie9.css" /><![endif]-->
    <!--[if lt IE 9]> <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/pages/css/scz/scz0301.css" /><![endif]-->
	<script type="text/javascript" src="<%=request.getContextPath()%>/pages/csjs/scz/scz0301.js"></script>
</head>
<body>
<s:form id="scz0301Action" name="scz0301Form" action="scz0301Action" method="post" validate="true"  theme="simple">
<s:hidden id="nextUrl" name="scz0301Form.nextUrl" />
<s:hidden id="printkinoid" name="scz0301Form.printkinoid" />
<s:hidden id="nextFlg" name="scz0301Form.nextFlg" />
<!-- コンテンツ -->
<div id="contents">
	<!-- メインカラム -->
	<div id="two_main_colum">
	
		<h:normaltitleinbtn value="scz0301"/>
	
		<h:messege/>
		
		<table style="margin-bottom: 0px; margin-left: 40px; margin-top: 0px; width: auto;">
			<tr>
				<td style="text-align: left;">帳票一覧</td>
			</tr>
			<tr>
				<td>
					<div style="overflow: hidden; float: left;width: auto;height: 440px; " >
						<table id="tablecloth_row1" class="row_table" style="margin-bottom: 0px; margin-left: 0px; width: auto;">
							<col style="width: 65px;" />
							<col style="width: 495px;" />
							<col style="width: 79px;" />
							<tr>
					            <th>選択<h:mustentryicon></h:mustentryicon></th>
					            <th>帳票名</th>
					            <th>用紙</th>
				            </tr>
						</table>
						<div class="div_class" style="overflow-x: hidden; overflow-y: auto;">
							<table id="tablecloth_row2" class="row_table" style="margin-top: -2px; margin-bottom: 0px; margin-left: 0px; width: auto;">
								<col style="width: 65px;" />
								<col style="width: 495px;" />
								<col style="width: 79px;" />
						        <s:iterator status="listData" value="scz0301Form.subForm" >
						            <tr <s:if test="#scz0301Dto.classFlg">class="even"</s:if><s:else>class="odd"</s:else> style="height: 20px;">
						            <s:url action="scz0301Action" method="doNext" namespace="/scz"></s:url>
										<%-- 選択 --%>
										<s:if test="#scz0301Dto.printUsageKeyFlg">
										    <s:if test="#scz0301Dto.printOrderUrl == scz0301Form.nextUrl">
												<td style="text-align: center;" rowspan="${scz0301Dto.count}">
													<input name="chohyoId_%{#listData.index}" id="chohyoId_${listData.index}" type="radio" value="<s:property value='#scz0301Dto.printOrderUrl' />" onclick="ischecked('<s:property value="#listData.index"/>');" checked="checked" style="cursor: pointer;" />
												</td>
											</s:if>
											<s:else>
											     <td style="text-align: center;" rowspan="${scz0301Dto.count}">
                                                    <input name="chohyoId_%{#listData.index}" id="chohyoId_${listData.index}" type="radio" value="<s:property value='#scz0301Dto.printOrderUrl' />" onclick="ischecked('<s:property value="#listData.index"/>');" style="cursor: pointer;" />
                                                </td>
											</s:else>
										</s:if>
										<%-- 帳票名 --%>
										<td style="text-align: left;" >
											<s:property value="#scz0301Dto.chohyoName" />
										</td>
										<%-- 用紙 --%>
										<td style="text-align: left;" >
											<s:property value="#scz0301Dto.paperSize" />
											<s:hidden id="printOrderUrl_%{#listData.index}" name="scz0301Form.subForm[%{#listData.index}].printOrderUrl" />
										</td>
									</tr>
						        </s:iterator>
							</table>
						</div>
					</div></td>
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
								
            <td>								
                <table  style="width: 100%;">								
                    <tr>								
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
                     <s:if test='scz0301Form.hidFlg !="1"'>
						<td>
						   <a href="javascript:void(0);" onclick="goNext()" class="next_mainbtn" title="次へ"></a>
						</td>
					</s:if>
					<s:else>	<td>&nbsp;</td></s:else>								
                    </tr>								
                </table>								
            </td>								
        </tr>								
    </table>								
</div>								
	</div>
</div>
<!-- コンテンツ終わり -->
</s:form>
</body>
</html>
