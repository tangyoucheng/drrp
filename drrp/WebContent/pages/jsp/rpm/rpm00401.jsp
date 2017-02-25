<!DOCTYPE html >
<%--
/*
 * Copyright(c) 2016 
 */
/*
 * 患者信息导出
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
<%-- <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/pages/css/rpm/rpm00401.css" /> --%>
<script type="text/javascript" src="<%=request.getContextPath()%>/pages/csjs/rpm/rpm00401.js"></script>
</head>
<body>
    <s:form id="rpm00401Form" name="rpm00401Form" action="rpm00401Action" method="post" validate="true" theme="simple">
        <div id="contents">
        <div id="two_main_colum">
        
             <h:normaltitleinbtn value="rpm00401"/>
             <h:messege/>

             
             <table class="entry_content_table input_condition">
               <col style="width:80px;"/>
               <col style="width:35px;"/>
               <col style="width:186px;"/>
               <col style="width:80px;"/>
               <col style="width:35px;"/>
               <col style="width: auto;"/>
               <tr>
                   <td class="entry_name" style="width:80px;">角色：</td>
                   <td></td>
                   <td>
                       <s:select name="rpm00401Form.roleId" onchange="doSearch(rpm00401Form)" list="rpm00401Form.roleDataSource" listKey="recordCode" listValue="recordValue" cssStyle="width:160px;" cssErrorClass="error" />
                   </td>
                   <td class="entry_name" style="width:80px;">菜单名：</td>
                   <td ></td>
                   <td>
                       <s:select name="rpm00401Form.menuHierarchy1Id" onchange="doSearch(rpm00401Form)" list="rpm00401Form.menuHierarchy1DataSource" listKey="recordCode" cssStyle="width:160px;" listValue="recordValue" cssErrorClass="error"/>
                   </td>
               </tr>
             </table>

                <!-- 検索の画面 -->
	        <table style="margin-left:35px; margin-top: 20px">
	        <!-- 机能菜单一览 -->
	        <tr>
	        <td>机能菜单一览</td>
	        </tr>
	        </table>
	        <div style="overflow:hidden;float:left;">
	            <table id="tablecloth_row1" class="row_table" style="margin-bottom:0px;margin-left:35px;width:678px;">
	                <col style="padding:1px;width: 80px;"/>
	                <col style="padding:1px;width:150px;"/>
	                <col style="padding:1px;width:200px;"/>
	                <col style="padding:1px;width:200px;"/>
	                <tr> 
	                    <th class="class_cell">许可</th>
	                    <th class="class_cell">菜单</th>
	                    <th class="class_cell">子菜单</th>
	                    <th class="class_cell">机能菜单</th>
	                </tr>
	            </table>
	            <div style="width:730px;" class="scroll_div_8">
	                <table class="row_table" style="margin-top:-2px;margin-bottom:0px;margin-left:35px;width:678px;">
	                    <col style="padding:1px;width: 80px;"/>
	                    <col style="padding:1px;width:150px;"/>
	                    <col style="padding:1px;width:200px;"/>
	                    <col style="padding:1px;width:200px;"/>
	                    <s:iterator status="listData" value="rpm00401Form.subForm1" >
	                      <tr  id="meisai_<g:property value="#listData.index"/>"
	                           <s:if test="#listData.even">class="even"</s:if><s:else>class="odd"</s:else> 
                               onmouseover="setTableHover(this);" onmouseout="clearTableHover(this);"
	                      >
	                            <td style="text-align:center;">
	                                <s:checkbox id="dataSelected" name="rpm00401Form.subForm1[%{#listData.index}].check" fieldValue="true"/>
	                            </td>
	                          <td>
	                                <s:property value="menu"/>
	                                 <s:hidden name="rpm00401Form.subForm1[%{#listData.index}].menu" />
	                          </td>
	                          <td>
	                                <s:property value="subMenu"/>
	                                 <s:hidden name="rpm00401Form.subForm1[%{#listData.index}].subMenu" />
	                          </td>
	                          <td>
	                                <s:property value="kinoMenu"/>
	                                 <s:hidden name="rpm00401Form.subForm1[%{#listData.index}].kinoMenu" />
	                                <s:hidden id="hidMenuId" name="rpm00401Form.subForm1[%{#listData.index}].hidMenuId" />
<%-- 	                                <s:hidden id="hidKinoId" name="rpm00401Form.subForm1[%{#listData.index}].hidKinoId" /> --%>
	                                <s:hidden id="hidChikkuKinoId" name="rpm00401Form.subForm1[%{#listData.index}].hidChikkuKinoId" />
	                          </td>
	                      </tr>
	                   </s:iterator>
	               </table>        
	            </div>
	        </div>
           <table class="subForm1_checked_btn" style="margin-left:35px;">
               <tr>
                   <td>&nbsp;</td>
                   <td>&nbsp;</td>
               </tr>
               <s:if test="rpm00401Form.subForm1.size > 0">
	               <tr>
	                   <td><a href="javascript:void(0);" class="aid_btn" onclick="doCheckedAll('dataSelected',true)"><span>全选择</span></a></td>
	                   <td><a href="javascript:void(0);" class="aid_btn" onclick="doCheckedAll('dataSelected',false)"><span>全解除</span></a></td>
	               </tr>
               </s:if>
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
                                        <!-- 登録 -->
                                        
                                        <s:if test="rpm00401Form.subForm1.size > 0">
                                            <td><g:submit method="doEntry" id="entry" cssClass="entry_mainbtn" onclick="return doEntry($(this));" value="" /></td>
                                        </s:if>
                                        <s:else>
                                            <td>&nbsp;</td>
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