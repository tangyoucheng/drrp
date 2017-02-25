<%@tag import="cn.com.prescription.framework.util.TimestampUtils"%>
<%@tag import="cn.com.prescription.framework.util.DateUtils"%>
<%@tag import="cn.com.prescription.framework.util.CheckUtils"%>
<%@tag pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@attribute name="form" type="java.lang.String" required="true"%>
<%@attribute name="name" type="java.lang.String" required="false"%>
<%@attribute name="id" type="java.lang.String" required="false"%>
<%@attribute name="subform" type="java.lang.String" required="false"%>
<%@attribute name="index" type="java.lang.Integer" required="false"%>
<%@attribute name="size" type="java.lang.Integer" required="false"%>
<%@attribute name="cssClass" type="java.lang.String" required="false"%>
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
    if (!CheckUtils.isEmpty(subform)) {
        tag_file_name = form + "." + subform + indexStr + FILE_NAME;
        tag_fileName_name = form + "." + subform + indexStr + FILE_FileName_NAME;
        tag_fileContentType_name = form + "." + subform + indexStr + FILE_ContentType_NAME;
        tag_file_text_disPath_name = form + "." + subform + indexStr+FILE_DIS_PATH_NAME;
        tag_file_text_serverPath_name = form + "." + subform + indexStr +FILE_SERVER_PATH_NAME;
    }
    request.setAttribute("tag_file_name", tag_file_name);
    request.setAttribute("tag_fileName_name", tag_fileName_name);
    request.setAttribute("tag_fileContentType_name", tag_fileContentType_name);
    request.setAttribute("tag_file_disPath_name",tag_file_text_disPath_name);
    request.setAttribute("tag_file_serverPath_name",tag_file_text_serverPath_name);
    if(size == null){
        size = 20;
    }
    request.setAttribute("tag_file_size",size);
    request.setAttribute("tag_file_cssClass",cssClass);
    request.setAttribute("tag_file_id",id);
%>
<div id="<%=tag_file_did_id%>" style="float:left;">
    <div style="float:left;">
        <s:textfield id="tag_file_disPath" name="%{#request.tag_file_disPath_name}" size="%{#request.tag_file_size}" cssClass="%{#request.tag_file_cssClass}" readonly="true" cssErrorClass="error"  onkeydown="keydownFileTag(this)"/>
    </div>
    <div id="tag_file"  style="height:24px;float:left;margin-left: 2px;">
        <a class="aid_btn" href="javascript:void(0);"  onmousemove="resetFile(this);" onfocus="resetFile(this);"><span>添加图片</span></a>
        <s:file name="%{#request.tag_file_name}" onchange="onchangeFileTag(this);" cssStyle="height:24px;width:50px;position:absolute;filter:alpha(opacity=0);opacity: 0;"/>
    </div> 
<s:hidden id="tag_file_serverPath" name="%{#request.tag_file_serverPath_name}"/>
<s:hidden id="tag_fileName" name="%{#request.tag_fileName_name}"/>
<s:hidden id="tag_fileContentType" name="%{#request.tag_fileContentType_name}"/>            
</div>