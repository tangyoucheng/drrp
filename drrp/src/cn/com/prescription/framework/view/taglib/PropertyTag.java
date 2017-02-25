/*
 * Copyright(c) 2011 
 */
package cn.com.prescription.framework.view.taglib;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.components.Component;
import org.apache.struts2.views.jsp.ComponentTagSupport;

import com.opensymphony.xwork2.util.ValueStack;

/**
 * @see Property
 * @author sk
 */
public class PropertyTag extends ComponentTagSupport {

    private static final long serialVersionUID = 435308349113743852L;

    private String defaultValue;
    private String value;
    private boolean escape = true;
    private boolean escapeJavaScript = false;
    private boolean encode = false;

    @Override
    public Component getBean(ValueStack stack, HttpServletRequest req, HttpServletResponse res) {
        return new Property(stack);
    }

    @Override
    protected void populateParams() {
        super.populateParams();

        Property tag = (Property) component;
        tag.setDefault(defaultValue);
        tag.setValue(value);
        tag.setEscape(escape);
        tag.setEncode(encode);
        tag.setEscapeJavaScript(escapeJavaScript);
    }

    public void setDefault(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public void setEscape(boolean escape) {
        this.escape = escape;
    }

    public void setEscapeJavaScript(boolean escapeJavaScript) {
        this.escapeJavaScript = escapeJavaScript;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setEncode(boolean encode) {
        this.encode = encode;
    }
}
