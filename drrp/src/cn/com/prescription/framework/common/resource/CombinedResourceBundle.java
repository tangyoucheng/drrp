/*
 * Copyright(c) 2011 
 */
package cn.com.prescription.framework.common.resource;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.NoSuchElementException;
import java.util.ResourceBundle;
import java.util.Set;

/**
 * メッセージリソース操作の抽象クラス（多メッセージリソースの合併をサッポートする）。
 * <p>
 * メッセージリソース操作を行う抽象クラス。メッセージリソースごとに実装クラスを生成する。<br>
 * </p>
 *
 * @author t.d.m
 */
public class CombinedResourceBundle extends ResourceBundle {

	/**
	 * メッセージリソース保存
	 */
	private Map<String, Object> lookup = null;

	/**
	 * CombinedResourceBundleクラス的构造
	 *
	 * @param bundle_1d_Incoming
	 *            　メッセージリソース
	 */
	public CombinedResourceBundle(String[] baseNames, Locale locale) {
		super();
		ResourceBundle bundle = null;

		lookup = new HashMap<String, Object>();
		if (baseNames != null && baseNames.length > 0) {
			for (String bundleName : baseNames) {
				try {
					bundle = ResourceBundle.getBundle(bundleName, locale);
				} catch (MissingResourceException e) {
					bundle = ResourceBundle.getBundle(bundleName);
				}

				Enumeration<String> emKeys = bundle.getKeys();
				while (emKeys != null && emKeys.hasMoreElements()) {
					String strKey = emKeys.nextElement();
					if (strKey != null) {
						if (!lookup.containsKey(strKey)) {
							try {
								lookup.put(strKey, bundle.getObject(strKey));
							} catch (MissingResourceException e) {
								lookup.put(strKey, null);
							}
						}
					}
				}
			}
		}
	}

	/**
	 * getKeysメッソドオーバーライド
	 *
	 * @see java.util.ResourceBundlegetKeys()
	 */
	@Override
	public Enumeration<String> getKeys() {
		return new CombinedResourceBundleEnumeration(lookup.keySet(),
				(parent != null) ? parent.getKeys() : null);
	}

	/**
	 * handleGetObjectメッソドオーバーライド
	 *
	 * @see java.util.ResourceBundlehandleGetObject(java.lang.String)
	 */
	@Override
	protected Object handleGetObject(String strKey)
			throws MissingResourceException, NullPointerException {
		if (strKey == null) {
			throw new NullPointerException();
		}
		return lookup.get(strKey);
	}
}

/**
 * Just a copy of "java.util.ResourceBundleEnumeration".<br>
 * Don't know how it works.<br>
 *
 * @author Typhoon.Free.Wolf
 * @version 2009-01-08-01
 */
class CombinedResourceBundleEnumeration implements Enumeration<String> {
	Set<String> set;
	Iterator<String> iterator;
	Enumeration<String> enumeration; // may remain null
	String next = null;

	/**
	 * Constructs a resource bundle enumeration.
	 * @param set an set providing some elements of the enumeration
	 * @param enumeration an enumeration providing more elements of the enumeration.
	 * enumeration may be null.
	 */
	CombinedResourceBundleEnumeration(Set<String> set,
			Enumeration<String> enumeration) {
		this.set = set;
		this.iterator = set.iterator();
		this.enumeration = enumeration;
	}

	public boolean hasMoreElements() {
		if (next == null) {
			if (iterator.hasNext()) {
				next = iterator.next();
			} else if (enumeration != null) {
				while (next == null && enumeration.hasMoreElements()) {
					next = enumeration.nextElement();
					if (set.contains(next)) {
						next = null;
					}
				}
			}
		}
		return next != null;
	}

	public String nextElement() {
		if (hasMoreElements()) {
			String result = next;
			next = null;
			return result;
		} else {
			throw new NoSuchElementException();
		}
	}
}
