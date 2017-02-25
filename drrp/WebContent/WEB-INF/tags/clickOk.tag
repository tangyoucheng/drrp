<%@tag pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@attribute name="form" type="java.lang.String" required="true"%>
<%
    request.setAttribute("_isClickOkFgName", form + ".isClickOkFg");
    request.setAttribute("_isClickOkKeyName", form + ".isClickOkKey");
%>
<s:hidden id="isClickOkFg" name="%{#request._isClickOkFgName}"></s:hidden>
<s:hidden id="isClickOkKey" name="%{#request._isClickOkKeyName}"></s:hidden>
<script type="text/javascript">
    function _clickOkEvent(isOK) {
        if (isOK) {
            var _isClickOkKey = $("#isClickOkKey").val();
            $("#isClickOkFg").val(_isClickOkKey);
            doClearWaterMark();
        } else {
            $("#isClickOkKey").val(0);
            $("#isClickOkFg").val(0);
            doShowWaterMark();
        }
    }
</script>

