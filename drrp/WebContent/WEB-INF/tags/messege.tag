<%@tag pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<div id="messege" style="margin-bottom: 10px;">
    <s:if test="hasFieldErrors() || hasActionMessages() || hasActionErrors()">
        <span class="error_message">
            <s:fielderror cssClass="errorMessage" escape="false" />
            <s:actionmessage cssClass="errorMessage" escape="false" />
            <s:actionerror cssClass="errorMessage" escape="false" />
            
        </span>
    </s:if>
</div>
