<%@tag pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="h" tagdir="/WEB-INF/tags" %>
<%@attribute name="id" type="java.lang.String" required="true" %>
<%@attribute name="name" type="java.lang.String" required="true" %>
<%@attribute name="width" type="java.lang.String" required="false" %>
<%@attribute name="integerSize" type="java.lang.Integer" required="true" %>
<%@attribute name="decimalSize" type="java.lang.Integer" required="false" %>
<%@attribute name="align" type="java.lang.String" required="false" %>
<%@attribute name="onblur" type="java.lang.String" required="false" %>
<%@attribute name="commaFlg" type="java.lang.Boolean" required="false" %>
<%@attribute name="rpadFlg" type="java.lang.Boolean" required="false" %>
<%@attribute name="mustFlg" type="java.lang.Boolean" required="false" %>
<%@attribute name="minusFlg" type="java.lang.Boolean" required="false" %>
<%@attribute name="noTabindexFlg" type="java.lang.Boolean" required="false" %>
<%

    if (mustFlg == null) {
        mustFlg = Boolean.FALSE;
    }
    if (rpadFlg == null) {
        rpadFlg = Boolean.FALSE;
    }
    if (commaFlg == null) {
        commaFlg = Boolean.FALSE;
    }
    if(align == null){
        align = "right";
    }
    if (minusFlg == null) {
        minusFlg = Boolean.FALSE;
    }
    if (noTabindexFlg == null) {
        noTabindexFlg = Boolean.FALSE;
    }
    request.setAttribute("tag_numberField_id", id);
    request.setAttribute("tag_numberField_name", name);
    
    if(mustFlg){
        request.setAttribute("tag_numberField_mustStr", "mustdata");
    }else{
        request.setAttribute("tag_numberField_mustStr", "");
    }

    if(onblur==null){
        request.setAttribute("tag_numberField_onblurMethod", "numberFormat($(this),"+commaFlg+","+rpadFlg+","+decimalSize+")");
    }else{
        request.setAttribute("tag_numberField_onblurMethod", "numberFormat($(this),"+commaFlg+","+rpadFlg+","+decimalSize+");"+onblur);
    }
    
    if(commaFlg){
         request.setAttribute("tag_numberField_onfocusMethod", "getCursurPosition(this.id);deleteComma($(this),"+commaFlg+");setCursurPosition(this.id)");
      }else{
        request.setAttribute("tag_numberField_onfocusMethod", "deleteComma($(this),"+commaFlg+")");
    }
    
    if(decimalSize!=null){
         if (minusFlg) {
             request.setAttribute("tag_numberField_maxlength", integerSize + 2 + decimalSize);
         }else{
             request.setAttribute("tag_numberField_maxlength", integerSize + 1 + decimalSize);
         }
        
    }else{
        if (minusFlg) {
            request.setAttribute("tag_numberField_maxlength", integerSize+1);
        }else{
            request.setAttribute("tag_numberField_maxlength", integerSize);
        }
        
    }
    if(noTabindexFlg){
        request.setAttribute("tag_numberField_tabindex", -1);
    }else {
        request.setAttribute("tag_numberField_tabindex", "");
    }
    
    String onpropertychangeMethod = "inputLimit(this,"+integerSize+","+decimalSize+","+commaFlg+","+minusFlg+")";
    String onpasteMethod = "return inputValidate(clipboardData.getData('text'),"+integerSize+","+decimalSize+","+commaFlg+","+minusFlg+")";
%>

<s:textfield id="%{#request.tag_numberField_id}" name="%{#request.tag_numberField_name}" onpropertychange='<%=onpropertychangeMethod %>' 
onpaste="<%=onpasteMethod %>" onblur="%{#request.tag_numberField_onblurMethod}" onfocus="%{#request.tag_numberField_onfocusMethod}" style='<%="ime-mode: disabled;text-align:"+align+";width:"+width %>' maxlength="%{#request.tag_numberField_maxlength}" cssErrorClass="error" cssClass="%{#request.tag_numberField_mustStr}" tabindex="%{#request.tag_numberField_tabindex}"/>
