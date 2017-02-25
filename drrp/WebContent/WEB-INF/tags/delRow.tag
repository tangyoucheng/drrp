<%@tag import="cn.com.prescription.framework.util.TimestampUtils"%>
<%@tag import="cn.com.prescription.framework.util.DateUtils"%>
<%@tag import="cn.com.prescription.framework.util.CheckUtils"%>
<%@tag import="cn.com.prescription.framework.util.ReflectUtils"%>
<%@tag import="cn.com.prescription.framework.action.form.PhotoAlbumDto"%>
<%@tag import="cn.com.prescription.framework.action.form.PhotoDto"%>
<%@tag import="java.util.List"%>
<%@tag pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@attribute name="id" type="java.lang.String" required="false"%>
<%@attribute name="subForm" type="java.lang.String" required="false"%>
<%@attribute name="delFgId" type="java.lang.String" required="false"%>
<%@attribute name="noId" type="java.lang.String" required="false"%>
<%@attribute name="cellStartBake" type="java.lang.String" required="false"%>
<%@attribute name="cellReplaceHtml" type="java.lang.String" required="false"%>
<%@attribute name="cellEndBake" type="java.lang.String" required="false"%>
<%@attribute name="cellDelPrev" type="java.lang.String" required="false"%>
<%@attribute name="cellDelAfte" type="java.lang.String" required="false"%>
<%@attribute name="xaRow" type="java.lang.String" required="false"%>
<%@attribute name="yaRow" type="java.lang.String" required="false"%>
<%@attribute name="parentLv" type="java.lang.String" required="false"%>
<%@attribute name="resetLineColor" type="java.lang.String" required="false"%>
<%@attribute name="resetNo" type="java.lang.String" required="false"%>
<%
    // tag id
    String tag_id = id;
    if(CheckUtils.isEmpty(tag_id)){
        tag_id = "delRow_"+ DateUtils.format(TimestampUtils.getSysTimestamp(),"yyyyMMddHHmmssSSS") + "_"    + Math.random();
        tag_id = tag_id.replaceAll("\\.", "");
    }
    if(CheckUtils.isEmpty(subForm)){
        subForm = "subForm1";
    }
    if(CheckUtils.isEmpty(delFgId)){
        delFgId = "delete";
    }
    if(CheckUtils.isEmpty(noId)){
        noId = "no";
    }
    if(CheckUtils.isEmpty(cellStartBake)){
        cellStartBake = "_cellStartBake";
    }
    if(CheckUtils.isEmpty(cellReplaceHtml)){
        cellReplaceHtml = "_cellReplaceHtml";
    }
    if(CheckUtils.isEmpty(cellEndBake)){
        cellEndBake = "_cellEndBake";
    }
    if(CheckUtils.isEmpty(cellDelPrev)){
        cellDelPrev = "_cellDelPrev";
    }
    if(CheckUtils.isEmpty(cellDelAfte)){
        cellDelAfte = "_cellDelPrev";
    }
    if(CheckUtils.isEmpty(xaRow)){
        xaRow = "0";
    }
    if(CheckUtils.isEmpty(yaRow)){
        yaRow = "0";
    }
    if(CheckUtils.isEmpty(parentLv)){
        parentLv = "0";
    }
    if(CheckUtils.isEmpty(resetLineColor)){
        resetLineColor = "false";
    }
    if(CheckUtils.isEmpty(resetNo)){
        resetNo = "false";
    }
%>
<a id=<%=tag_id %> href="javascript:void(0);" class="aid_btn"  style="margin-right: 0px;padding-left: 0px;"
 onclick="_clickDelRow(this,'<%=delFgId%>','<%=noId%>','<%=subForm%>',<%=cellStartBake%>,<%=cellReplaceHtml%>,<%=cellEndBake%>,<%=cellDelPrev%>,<%=cellDelAfte%>,'<%=xaRow%>','<%=yaRow%>','<%=parentLv%>','<%=resetLineColor%>','<%=resetNo%>')"
 ><span>行削除</span></a>