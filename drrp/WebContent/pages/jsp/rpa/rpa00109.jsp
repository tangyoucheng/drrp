<!DOCTYPE html >
<%--
/*
 * Copyright(c) 2016 
 */
/*
 * 处方信息一览
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
<%-- <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/pages/css/schm/rpa00109.css" /> --%>
<script type="text/javascript" src="<%=request.getContextPath()%>/pages/csjs/rpa/rpa00109.js"></script>
</head>
<body>
    <s:form id="rpa00109Form" name="rpa00109Form" action="rpa00109Action" method="post" validate="true" theme="simple">
        <input type="hidden" name="rpa00109Form.pageType"  value="${rpa00109Form.pageType }">
        <div id="contents">
        <div id="two_main_colum">
        
             <h:normaltitleinbtn value="rpa00109"/>
             <h:messege/>
             <table class="entry_content_table input_condition">
               <col style="width:80px;"/>
               <col style="width:35px;"/>
               <col style="width: auto;"/>
               <tr>
                   <td class="entry_name" style="width:80px;">处方年度：</td>
                   <td></td>
                   <td>
                       <s:select name="rpa00109Form.prescriptionYear" list="rpa00109Form.prescriptionYearDataSource" listKey="recordCode" listValue="recordValue" cssStyle="width:160px;" cssErrorStyle="width:160px;" cssErrorClass="error" />
                   </td>
               </tr>
             </table>

                <!-- 検索の画面 -->
                <div class="guide_btn_area">
                    <table class="guide_btn_table" style="width:100%;">
                        <col style="width: 25%;" />
                        <col style="width: 50%;" />
                        <col style="width: 25%;" />
                        <tr>
                            <td>&nbsp;</td>
                            <td>
                                <table style="width: 100%;">
                                    <tr>
                                        <td><g:submit method="doSearch" cssClass="search_mainbtn" value="" /></td>
                                        <td><g:submit method="doExport" id="chushutsu" cssClass="extraction_mainbtn" onclick="return doChushutsu($(this));" value="" /></td>
                                    </tr>
                                </table>
                            </td>
                            <td>
                                &nbsp;
                            </td>
                        </tr>
                    </table>
                </div>
                    
                 <s:if test="rpa00109Form.subForm1.size > 0">
                     <table style="width: 100%;">
                        <tr>
                            <td>&nbsp;</td>
                        </tr>
                        <tr>
                            <td class="font_bold">检索结果一览</td>
                        </tr>
                    </table>
                        
                    <div style="width:auto;">
                        <table id="tablecloth_row1" class="row_table" style="width: auto;margin-left: 0px">
                            <tr>
                                <th rowspan="2">年度</th>
                                <th colspan="4">第一季度</th>
                                <th colspan="4">第二季度</th>
                                <th colspan="4">第三季度</th>
                                <th colspan="4">第四季度</th>
                                <th rowspan="2">年度<br>总计</th>
                            </tr>
                            <tr>
                                <th>一月</th>
                                <th>二月</th>
                                <th>三月</th>
                                <th>小计</th>
                                <th>四月</th>
                                <th>五月</th>
                                <th>六月</th>
                                <th>小计</th>
                                <th>七月</th>
                                <th>八月</th>
                                <th>九月</th>
                                <th>小计</th>
                                <th>十月</th>
                                <th>十一月</th>
                                <th>十二月</th>
                                <th>小计</th>
                            </tr>
                            <s:iterator status="listData" value="rpa00109Form.subForm1">
                                <tr   id="meisai_<g:property value="#listData.index"/>"
	                                <s:if test="#listData.even">class="even"</s:if><s:else>class="odd"</s:else> 
                                    onmouseover="setTableHover(this);" onmouseout="clearTableHover(this);"
                                >
                                    <td title="<s:property value="prescriptionYear" /> " class="txt_overflow">
                                            <s:property value="prescriptionYear" /> 
                                            <s:hidden name="rpa00109Form.subForm1[%{#listData.index}].prescriptionYear" />
                                    </td>
                                    <td style="text-align: right;" title="<s:property value="janCount" /> " class="txt_overflow">
                                            <s:property value="janCount" /> 
                                            <s:hidden name="rpa00109Form.subForm1[%{#listData.index}].janCount" />
                                    </td>
                                    <td style="text-align: right;" title="<s:property value="febCount" /> " class="txt_overflow">
                                            <s:property value="febCount" /> 
                                            <s:hidden name="rpa00109Form.subForm1[%{#listData.index}].febCount" />
                                    </td>
                                    <td style="text-align: right;" title="<s:property value="marCount" /> " class="txt_overflow">
                                            <s:property value="marCount" /> 
                                            <s:hidden name="rpa00109Form.subForm1[%{#listData.index}].marCount" />
                                    </td>
                                    <td style="text-align: right;" title="<s:property value="firstQuarterCount" /> " class="txt_overflow">
                                            <s:property value="firstQuarterCount" /> 
                                            <s:hidden name="rpa00109Form.subForm1[%{#listData.index}].firstQuarterCount" />
                                    </td>
                                    <td style="text-align: right;" title="<s:property value="aprCount" /> " class="txt_overflow">
                                            <s:property value="aprCount" /> 
                                            <s:hidden name="rpa00109Form.subForm1[%{#listData.index}].aprCount" />
                                    </td>
                                    <td style="text-align: right;" title="<s:property value="mayCount" /> " class="txt_overflow">
                                            <s:property value="mayCount" /> 
                                            <s:hidden name="rpa00109Form.subForm1[%{#listData.index}].mayCount" />
                                    </td>
                                    <td style="text-align: right;" title="<s:property value="juneCount" /> " class="txt_overflow">
                                            <s:property value="juneCount" /> 
                                            <s:hidden name="rpa00109Form.subForm1[%{#listData.index}].juneCount" />
                                    </td>
                                    <td style="text-align: right;" title="<s:property value="secondQuarterCount" /> " class="txt_overflow">
                                            <s:property value="secondQuarterCount" /> 
                                            <s:hidden name="rpa00109Form.subForm1[%{#listData.index}].secondQuarterCount" />
                                    </td>
                                    <td style="text-align: right;" title="<s:property value="julyCount" /> " class="txt_overflow">
                                            <s:property value="julyCount" /> 
                                            <s:hidden name="rpa00109Form.subForm1[%{#listData.index}].julyCount" />
                                    </td>
                                    <td style="text-align: right;" title="<s:property value="augCount" /> " class="txt_overflow">
                                            <s:property value="augCount" /> 
                                            <s:hidden name="rpa00109Form.subForm1[%{#listData.index}].augCount" />
                                    </td>
                                    <td style="text-align: right;" title="<s:property value="sepCount" /> " class="txt_overflow">
                                            <s:property value="sepCount" /> 
                                            <s:hidden name="rpa00109Form.subForm1[%{#listData.index}].sepCount" />
                                    </td>
                                    <td style="text-align: right;" title="<s:property value="thirdQuarterCount" /> " class="txt_overflow">
                                            <s:property value="thirdQuarterCount" /> 
                                            <s:hidden name="rpa00109Form.subForm1[%{#listData.index}].thirdQuarterCount" />
                                    </td>
                                    <td style="text-align: right;" title="<s:property value="octCount" /> " class="txt_overflow">
                                            <s:property value="octCount" /> 
                                            <s:hidden name="rpa00109Form.subForm1[%{#listData.index}].octCount" />
                                    </td>
                                    <td style="text-align: right;" title="<s:property value="novCount" /> " class="txt_overflow">
                                            <s:property value="novCount" /> 
                                            <s:hidden name="rpa00109Form.subForm1[%{#listData.index}].novCount" />
                                    </td>
                                    <td style="text-align: right;" title="<s:property value="decCount" /> " class="txt_overflow">
                                            <s:property value="decCount" /> 
                                            <s:hidden name="rpa00109Form.subForm1[%{#listData.index}].decCount" />
                                    </td>
                                    <td style="text-align: right;" title="<s:property value="fourthQuarterCount" /> " class="txt_overflow">
                                            <s:property value="fourthQuarterCount" /> 
                                            <s:hidden name="rpa00109Form.subForm1[%{#listData.index}].fourthQuarterCount" />
                                    </td>
                                    <td style="text-align: right;" title="<s:property value="yearCount" /> " class="txt_overflow">
                                            <s:property value="yearCount" /> 
                                            <s:hidden name="rpa00109Form.subForm1[%{#listData.index}].yearCount" />
                                    </td>
                                </tr>
                            </s:iterator>
                        </table>
                    </div>
                 </s:if>
             
        </div>
    </div>
    </s:form>
    <g:popuperror />
    <g:download />
</body>
</html>