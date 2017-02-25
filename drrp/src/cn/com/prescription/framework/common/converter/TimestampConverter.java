/*
 * Copyright(c) 2011 
 */
package cn.com.prescription.framework.common.converter;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Map;

import cn.com.prescription.framework.util.CheckUtils;

import org.apache.struts2.util.StrutsTypeConverter;

/**
 * タイムスタンプコンバータ.
 * 
 * @author nttdc
 */
public class TimestampConverter extends StrutsTypeConverter {

    /**
     * 文字列をタイムスタンプ型に変換して返す.
     * 
     * @param _context the action context
     * @param _values the String values to be converted, such as those submitted from an HTML form
     * @param _toClass the class to convert to
     * @return タイムスタンプ
     */
    @Override
    public Object convertFromString(Map _context, String[] _values, Class _toClass) {
        String[] value = _values[0].split(",");
        Timestamp ts = null;
        if (CheckUtils.isEmpty(value[0])) {
            ts = Timestamp.valueOf(value[0]);
        }
        return ts;
    }

    /**
     * タイムスタンプ型を文字列に変換して返す.
     * 
     * @param _context the action context
     * @param _o the object to be converted
     * @return 文字列
     */
    @Override
    public String convertToString(Map _context, Object _o) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String result = null;
        if (_o != null) {
            Timestamp ts = (Timestamp) _o;
            result = df.format(ts);
        }
        return result;
    }

}
