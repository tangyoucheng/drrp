/*
 * Copyright(c) 2011 
 */
package cn.com.prescription.framework.view.taglib;

import java.io.IOException;
import java.io.Writer;

import cn.com.prescription.framework.view.HTMLEncoder;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.struts2.components.Component;
import org.apache.struts2.views.annotations.StrutsTag;
import org.apache.struts2.views.annotations.StrutsTagAttribute;

import com.opensymphony.xwork2.util.ValueStack;
import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;

/**
 * <!-- START SNIPPET: javadoc --> Used to get the property of a <i>value</i>, which will default to the top of the
 * stack if none is specified. <!-- END SNIPPET: javadoc -->
 * <p/>
 * <!-- START SNIPPET: params -->
 * <ul>
 * <li>default (String) - The default value to be used if <u>value</u> attribute is null</li>
 * <li>escape (Boolean) - Escape HTML. Default to true</li>
 * <li>value (Object) - value to be displayed</li>
 * </ul>
 * <!-- END SNIPPET: params -->
 * 
 * <pre>
 * <!-- START SNIPPET: example -->
 * <s:push value="myBean">
 *     <!-- Example 1: -->
 *     <s:property value="myBeanProperty" />
 *     <!-- Example 2: -->TextUtils
 *     <s:property value="myBeanProperty" default="a default value" />
 * </s:push>
 * <!-- END SNIPPET: example -->
 * </pre>
 * 
 * <pre>
 * <!-- START SNIPPET: exampledescription -->
 * Example 1 prints the result of myBean's getMyBeanProperty() method.
 * Example 2 prints the result of myBean's getMyBeanProperty() method and if it is null, print 'a default value' instead.
 * <!-- END SNIPPET: exampledescription -->
 * </pre>
 * 
 * <pre>
 * <!-- START SNIPPET: i18nExample -->
 * &lt;s:property value="getText('some.key')" /&gt;
 * <!-- END SNIPPET: i18nExample -->
 * </pre>
 * 
 * @author sk
 */
@StrutsTag(name = "property", tldBodyContent = "empty", tldTagClass = "org.apache.struts2.views.jsp.PropertyTag", description = "Print out expression which evaluates against the stack")
public class Property extends Component {
    private static final Logger LOG = LoggerFactory.getLogger(Property.class);

    public Property(ValueStack stack) {
        super(stack);
    }

    private String defaultValue;
    private String value;
    private boolean escape = true;
    private boolean escapeJavaScript = false;
    private boolean encode = false;

    @StrutsTagAttribute(description = "The default value to be used if <u>value</u> attribute is null")
    public void setDefault(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    @StrutsTagAttribute(description = " Whether to escape HTML", type = "Boolean", defaultValue = "true")
    public void setEscape(boolean escape) {
        this.escape = escape;
    }

    @StrutsTagAttribute(description = "Whether to escape Javascript", type = "Boolean", defaultValue = "false")
    public void setEscapeJavaScript(boolean escapeJavaScript) {
        this.escapeJavaScript = escapeJavaScript;
    }

    @StrutsTagAttribute(description = "Value to be displayed", type = "Object", defaultValue = "&lt;top of stack&gt;")
    public void setValue(String value) {
        this.value = value;
    }

    @StrutsTagAttribute(description = "Whether to escape Javascript", type = "Boolean", defaultValue = "false")
    public void setEncode(boolean encode) {
        this.encode = encode;
    }

    @Override
    public boolean start(Writer writer) {
        boolean result = super.start(writer);

        String actualValue = null;

        if (value == null) {
            value = "top";
        } else {
            value = stripExpressionIfAltSyntax(value);
        }

        // exception: don't call findString(), since we don't want the
        // expression parsed in this one case. it really
        // doesn't make sense, in fact.
        actualValue = (String) getStack().findValue(value, String.class, throwExceptionOnELFailure);

        try {
            if (actualValue != null) {
                writer.write(prepare(actualValue));
            } else if (defaultValue != null) {
                writer.write(prepare(defaultValue));
            }
        } catch (IOException e) {
            LOG.info("Could not print out value '" + value + "'", e);
        }

        return result;
    }

    private String prepare(String value) {
        String result = value;
        if (escape) {
            result = StringEscapeUtils.escapeHtml(result);
        }
        if (escapeJavaScript) {
            result = StringEscapeUtils.escapeJavaScript(result);
        }
        if (encode) {
            result = HTMLEncoder.encodeCaption(value);
        }
        return result;
    }
}
