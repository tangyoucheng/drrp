<%@tag import="cn.com.prescription.framework.util.TimestampUtils"%>
<%@tag import="cn.com.prescription.framework.util.DateUtils"%>
<%@tag import="cn.com.prescription.framework.util.CheckUtils"%>
<%@tag pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@attribute name="form" type="java.lang.String" required="true"%>
<%@attribute name="name" type="java.lang.String" required="false"%>
<%@attribute name="id" type="java.lang.String" required="false"%>
<%@attribute name="sbuform" type="java.lang.String" required="false"%>
<%@attribute name="index" type="java.lang.Integer" required="false"%>
<%@attribute name="rows" type="java.lang.Integer" required="false"%>
<%@attribute name="cols" type="java.lang.Integer" required="false"%>
<%@attribute name="cssClass" type="java.lang.String" required="false"%>
<%@attribute name="margin_top" type="java.lang.Integer" required="false"%>
<%
    // tag id
    String tag_file_did_id = id;
    if(CheckUtils.isEmpty(tag_file_did_id)){
        String tag_file_id = form + "_uploadFile_file_"    + DateUtils.format(TimestampUtils.getSysTimestamp(),"yyyyMMddHHmmssSSS") + "_"    + Math.random();
        tag_file_id = tag_file_id.replaceAll("\\.", "");
        tag_file_did_id = "file_div_" + tag_file_id;
    }

    // tag tagName
    String tagName = ".uploadFile";
    if(!CheckUtils.isEmpty(name)){
        tagName = "."+name;
    }

    final String FILE_NAME = tagName+".file";
    final String FILE_FileName_NAME = tagName+".fileFileName";
    /** 上传ファイル種別 */
    final String FILE_ContentType_NAME = tagName+".fileContentType";
    final String FILE_DIS_PATH_NAME = tagName+".fileDisPath";
    final String FILE_SERVER_PATH_NAME = tagName+".fileServerPath";
    
    String tag_file_name = form + FILE_NAME;
    String tag_fileName_name = form + FILE_FileName_NAME;
    String tag_fileContentType_name = form + FILE_ContentType_NAME;
    String tag_file_text_disPath_name = form+FILE_DIS_PATH_NAME;
    String tag_file_text_serverPath_name = form+FILE_SERVER_PATH_NAME;
    String indexStr = "";
    if(index != null){
        indexStr = "[" + index + "]";
    }
    if (!CheckUtils.isEmpty(sbuform)) {
        tag_file_name = form + "." + sbuform + indexStr + FILE_NAME;
        tag_fileName_name = form + "." + sbuform + indexStr + FILE_FileName_NAME;
        tag_fileContentType_name = form + "." + sbuform + indexStr + FILE_ContentType_NAME;
        tag_file_text_disPath_name = form + "." + sbuform + indexStr+FILE_DIS_PATH_NAME;
        tag_file_text_serverPath_name = form + "." + sbuform + indexStr +FILE_SERVER_PATH_NAME;
        //tag_file_name = form+"."+sbuform+".uploadFile.file";
    }
    request.setAttribute("tag_file_name", tag_file_name);
    request.setAttribute("tag_fileName_name", tag_fileName_name);
    request.setAttribute("tag_fileContentType_name", tag_fileContentType_name);
    request.setAttribute("tag_file_disPath_name",tag_file_text_disPath_name);
    request.setAttribute("tag_file_serverPath_name",tag_file_text_serverPath_name);
    if(rows == null){
        rows = 3;
    }
    if(rows == null){
        cols = 10;
    }
    String style_margin_top = "";
    if(margin_top != null){
        style_margin_top = "margin-top:"+margin_top+"px;";
    }
    request.setAttribute("tag_file_rows",rows);
    request.setAttribute("tag_file_cols",cols);
    request.setAttribute("tag_file_cssClass",cssClass);
%>
<div id="<%=tag_file_did_id%>" style="text-align:center;">
    <div style="float:left;">
        <s:textarea id="tag_file_disPath" name="%{#request.tag_file_disPath_name}" rows="%{#request.tag_file_rows}" cols="%{#request.tag_file_cols}"  cssClass="%{#request.tag_file_cssClass}"  cssErrorClass="error" readonly="true" onkeydown="keydownFileTag(this)"/>
    </div>
    <div id="tag_file" style="height:24px;overflow:hidden;float:left;margin-left: 2px;<%=style_margin_top %>">
        <a class="aid_btn" title="参照"  href="javascript:void(0);"  onmousemove="resetFile(this);" onfocus="resetFile(this);"><span>参照</span></a>
        <s:file name="%{#request.tag_file_name}"  cssStyle="height:24px;width:50px;position:absolute;filter:alpha(opacity=0);" onchange="onchangeFileTag(this);"/>
    </div> 
    <s:hidden id="tag_file_serverPath" name="%{#request.tag_file_serverPath_name}"/>
    <s:hidden id="tag_fileName" name="%{#request.tag_fileName_name}"/>
    <s:hidden id="tag_fileContentType" name="%{#request.tag_fileContentType_name}"/>
</div>
