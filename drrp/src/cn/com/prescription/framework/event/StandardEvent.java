/*
 * Copyright(c) 2011 
 */
package cn.com.prescription.framework.event;

import cn.com.prescription.framework.action.form.StandartForm;

import org.intra_mart.framework.base.event.Event;

/**
 * 標準event.
 * 
 * @author nttdc
 */
public class StandardEvent extends Event {

    /** 串行版本号 */
    private static final long serialVersionUID = -5569737965295650043L;

    /** form */
    private StandartForm form = null;

    /** 任意オブジェクト */
    private Object object = null;

    /**
     * StandardEvent 的构造。
     */
    public StandardEvent() {
        super();
    }

    /**
     * @return form
     */
    public final StandartForm getForm() {
        return form;
    }

    /**
     * @param _form form
     */
    public final void setForm(StandartForm _form) {
        form = _form;
    }

    /**
     * @return 任意オブジェクト
     */
    public final Object getObject() {
        return object;
    }

    /**
     * @param _object 任意オブジェクト
     */
    public final void setObject(Object _object) {
        object = _object;
    }

}
