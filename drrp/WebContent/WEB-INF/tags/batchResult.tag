<%@tag pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="h" tagdir="/WEB-INF/tags" %>
<%@attribute name="id" type="java.lang.String" required="true" %>
<%@attribute name="form" type="java.lang.String" required="true" %>
<%@attribute name="shoriId" type="java.lang.String" required="false" %>
<%@attribute name="backBtnID" type="java.lang.String" required="false" %>

<%
request.setAttribute("shoriIdFieldName",form + ".shoriId");
request.setAttribute("shoriIdFieldValue",shoriId);
request.setAttribute("shoriExecuteJokyoCdFieldName",form + ".shoriJikkoJokyo");
request.setAttribute("shoriMessageHidFieldName",form+ ".jikkoKekkaMessage");
request.setAttribute("batchKeyFieldName",form + ".batchKey");
request.setAttribute("logFileNameFieldName",form + ".logFileName");
request.setAttribute("backBtnIDFieldName","#"+backBtnID);
%>

<s:hidden id="shoriId" name="%{#request.shoriIdFieldName}" value="%{#request.shoriIdFieldValue}"/>
<s:hidden id="shoriJikkoJokyo" name="%{#request.shoriExecuteJokyoCdFieldName}"/>
<s:hidden id="shoriMessageHid" name="%{#request.shoriMessageHidFieldName}"/>
<s:hidden id="batchKey" name="%{#request.batchKeyFieldName}"/>
<s:hidden id="logFileName" name="%{#request.logFileNameFieldName}"/>
<s:hidden id="backBtnID" value="%{#request.backBtnIDFieldName}"/>

    <table id="<%=id %>" style="width: auto;" class="entry_content_table">
        <tr>
            <td class="entry_name">開始日時</td>
            <td>
                <h:calendar id="shoriStartDate"  form="<%=form%>" era="shoriKaishiNichijiGengo" year="shoriKaishiNichijiNen" month="shoriKaishiNichijiTsuki" day="shoriKaishiNichijiDate" readonlyFlg="true"></h:calendar>&nbsp;
                <h:time id="shoriStartTime" form="<%=form%>" hour="shoriKaishiNichijiToki" minute="shoriKaishiNichijiFun" readonlyFlg="true"></h:time>
            </td>
            
        </tr>
        <tr>
            <td class="entry_name">終了日時</td>
            <td>
                <h:calendar id="shoriEndDate"  form="<%=form%>" era="shoriShuuryoNichijiGengo" year="shoriShuuryoNichijiNen" month="shoriShuuryoNichijiTsuki" day="shoriShuuryoNichijiDate" readonlyFlg="true"></h:calendar>&nbsp;
                <h:time id="shoriEndTime" form="<%=form%>" hour="shoriShuuryoNichijiToki" minute="shoriShuuryoNichijiFun" readonlyFlg="true"></h:time>
            </td>
            
        </tr>
        <tr>        
            <td class="entry_name">実行者</td>
            <td>
                <h:readonlyfield id="shoriExecuteSha" name='<%=form+".shoriExecuteSha"%>' style="width:160px;"></h:readonlyfield>
            </td>
        </tr>
        <tr>
            <td class="entry_name">実行状況</td>
            <td>
                <div class="aidbtn_storage">
                    <h:readonlyfield id="jikkoJokyoMeisho" name='<%=form+".jikkoJokyoMeisho"%>' style="width:160px;"></h:readonlyfield>
                    <a id="regetStatusBtn" href="javascript:void(0);" class="aid_btn" onclick="regetStatus();"><span>状況再取得</span></a>
                </div>
            </td>
        </tr>
    </table>
    
    <div id="shoriResultDiv" style="display:none;">
        <table border="1" style="border-color: #000000;" width="96%" style="margin-left:15px">            
            <tr><td style="padding-top:15px;text-align:center" width="100%"><h3><b>実行結果</b></h3></td></tr>                            
            <tr><td style="padding-top:10px;padding-left:10px;padding-bottom:20px" width="100%">
                   <s:label id="message01"/><BR/>
                   <s:label id="message02"/><BR/>
                   <s:label id="message03"/><BR/>
                   <s:label id="message04"/><BR/>
                   <s:label id="message05"/>
                </td>
            </tr>
            <tr><td style="padding-bottom:15px" width="100%"><div style="padding-left:45%;width:100%"><a href="javascript:void(0);" class="aid_btn" onclick="getLog();"><span>ログ取得</span></a></div></td></tr>                       
        </table>
    </div>
    
    <iframe id="downloadFrame" frameborder="0" width="0" height="0"></iframe>
