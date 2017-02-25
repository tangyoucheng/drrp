
<%--
/*
 * Copyright(c) 2011 
 */
/*
 *画像イメージ拡大表示
 *
 * @author wjx
 * @since 1.0
 *
 * 新規作成
 * DATE: 2012/02/13 NAME: wjx
 */
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/pages/jsp/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<h:head></h:head>
	<script type="text/javascript" src="<%=request.getContextPath()%>/pages/csjs/scz/scz0201.js"></script>
</head>
<body>
<s:form id="scz0201Action" name="scz0201Form" action="scz0201Action" method="post" validate="true"  theme="simple">
<!-- コンテンツ -->
<div id="contents">
	<!-- メインカラム -->
	<div id="two_main_colum">
	
		<h:normaltitle value="scz0201"/>
	
		<h:messege/>
		<div style="height: 500px;overflow: auto;">
    	<table style="width: auto;margin-left: 0px;height: 400px;" class="entry_content_table input_condition" >
    		<!-- 画像イメージ  -->
		    <tr>
			  <td>
   				<s:if test="scz0201Form.imagePath != null && scz0201Form.imagePath != ''" >
					<img src='../..<g:property value="scz0201Form.imagePath"/>?temp="<%=Math.random()%>"' style='width: <g:property value="scz0201Form.imageWidth"/>px;'/>
				</s:if>
			  </td>
		    </tr>
	     </table>
         </div>
  
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
		                       <td><g:submit cssClass="close_mainbtn" value="" title="閉じる" onclick="return doCloseScz0201Dialog();"/></td>						
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
		                        <td>&nbsp;</td>								
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