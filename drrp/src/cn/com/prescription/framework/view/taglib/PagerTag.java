package cn.com.prescription.framework.view.taglib;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

public class PagerTag extends TagSupport {

    private static final long serialVersionUID = 5729832874890369508L;

    private String formId;
    private String formAction;
    private String pageSize;
    private String pageStartRowNo = "0";
    private String recordCount = "-1";
    /** unique标识 */
    private boolean uniqueFlag;
    /* ajax分页处理方法 */
    private String ajaxPageMethod;

    @Override
    public int doStartTag() throws JspException {

        try {
            HttpServletRequest request = (HttpServletRequest) this.pageContext.getRequest();
            this.pageStartRowNo = request.getAttribute(formId + ".pageStartRowNo").toString();
            this.recordCount = request.getAttribute(formId + ".recordCount").toString();
            this.pageSize = request.getAttribute(formId + ".pageSize").toString();
            PagerForm pagerForm = new PagerForm();
            pagerForm.setFormId(formId);
            pagerForm.setFormAction(formAction);
            pagerForm.setPageSize(pageSize);
            pagerForm.setPageStartRowNo(pageStartRowNo);
            pagerForm.setRecordCount(recordCount);
            pagerForm.setUniqueFlag(uniqueFlag);
            pagerForm.setAjaxPageMethod(ajaxPageMethod);
            PagerCommon pagerCommon = new PagerCommon();
            String[] _result = pagerCommon.doEditPageInfo(pagerForm);
            String resultPageInfo = _result[0] + _result[1];

            this.pageContext.getOut().println(resultPageInfo);
        } catch (IOException e) {
            throw new JspException(e);
        }
        return 0;
    }

    public String getFormId() {
        return formId;
    }

    public void setFormId(String formId) {
        this.formId = formId;
    }

    public String getFormAction() {
        return formAction;
    }

    public void setFormAction(String formAction) {
        this.formAction = formAction;
    }

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }

    public String getPageStartRowNo() {
        return pageStartRowNo;
    }

    public void setPageStartRowNo(String pageStartRowNo) {
        this.pageStartRowNo = pageStartRowNo;
    }

    public String getRecordCount() {
        return recordCount;
    }

    public void setRecordCount(String recordCount) {
        this.recordCount = recordCount;
    }

    public boolean isUniqueFlag() {
        return uniqueFlag;
    }

    public void setUniqueFlag(boolean uniqueFlag) {
        this.uniqueFlag = uniqueFlag;
    }

    public String getAjaxPageMethod() {
        return ajaxPageMethod;
    }

    public void setAjaxPageMethod(String ajaxPageMethod) {
        this.ajaxPageMethod = ajaxPageMethod;
    }

}
