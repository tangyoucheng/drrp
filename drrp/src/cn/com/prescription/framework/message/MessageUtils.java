/*
 * Copyright(c) 2011 
 */
package cn.com.prescription.framework.message;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.MessageFormat;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;

import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;

/**
 * Provides support for localization in XWork.
 * <p/>
 * <!-- START SNIPPET: searchorder --> Resource bundles are searched in the following order:
 * <p/>
 * <p/>
 * <ol>
 * <li>ActionClass.properties</li>
 * <li>Interface.properties (every interface and sub-interface)</li>
 * <li>BaseClass.properties (all the way to Object.properties)</li>
 * <li>ModelDriven's model (if implements ModelDriven), for the model object repeat from 1</li>
 * <li>package.properties (of the directory where class is located and every parent directory all the way to the root
 * directory)</li>
 * <li>search up the i18n message key hierarchy itself</li>
 * <li>global resource properties</li>
 * </ol>
 * <p/>
 * <!-- END SNIPPET: searchorder -->
 * <p/>
 * <!-- START SNIPPET: packagenote --> To clarify #5, while traversing the package hierarchy, Struts 2 will look for a
 * file package.properties:
 * <p/>
 * com/<br/>
 * &nbsp; acme/<br/>
 * &nbsp; &nbsp; package.properties<br/>
 * &nbsp; &nbsp; actions/<br/>
 * &nbsp; &nbsp; &nbsp; package.properties<br/>
 * &nbsp; &nbsp; &nbsp; FooAction.java<br/>
 * &nbsp; &nbsp; &nbsp; FooAction.properties<br/>
 * <p/>
 * If FooAction.properties does not exist, com/acme/action/package.properties will be searched for, if not found
 * com/acme/package.properties, if not found com/package.properties, etc.
 * <p/>
 * <!-- END SNIPPET: packagenote -->
 * <p/>
 * <!-- START SNIPPET: globalresource --> A global resource bundle could be specified programatically, as well as the
 * locale.
 * <p/>
 * <!-- END SNIPPET: globalresource -->
 * 
 * @author Jason Carreira
 * @author Mark Woon
 * @author Rainer Hermanns
 * @author tm_jee
 * @version $Date: 2011-04-04 09:55:54 +0200 (Mon, 04 Apr 2011) $ $Id: LocalizedTextUtil.java 1088493 2011-04-04
 *          07:55:54Z lukaszlenart $
 */
public class MessageUtils {

    private static final List<String> DEFAULT_RESOURCE_BUNDLES = new CopyOnWriteArrayList<String>();
    private static final Logger LOG = LoggerFactory.getLogger(MessageUtils.class);
    private static boolean reloadBundles = false;
    private static final ResourceBundle EMPTY_BUNDLE = new EmptyResourceBundle();
    private static final ConcurrentMap<String, ResourceBundle> bundlesMap = new ConcurrentHashMap<String, ResourceBundle>();
    private static final ConcurrentMap<MessageFormatKey, MessageFormat> messageFormats = new ConcurrentHashMap<MessageFormatKey, MessageFormat>();

    private static ClassLoader delegatedClassLoader;

    static {
        // init(listFile());
        DEFAULT_RESOURCE_BUNDLES.add("messageResource");
        DEFAULT_RESOURCE_BUNDLES.add("logResource");
        DEFAULT_RESOURCE_BUNDLES.add("reports");
    }

    // 2013/01/24 未使用メッソドをコメントする
    // /**
    // * メッセージリソースのベース名取得
    // *
    // * @return メッセージリソースのベース名
    // */
    // private static List<String> listFile() {
    // String[] extensions = new String[1];
    // List<String> baseNameList = new ArrayList<String>();
    // extensions[0] = "properties";
    // String baseName = null;
    // String dir = FileUtils.getAbsolutePath("/WEB-INF/classes",
    // SysConfigHandler.create().getPattern("common_message_path"));
    // File f = new File(dir);
    //
    // Collection<File> list = org.apache.commons.io.FileUtils.listFiles(f, extensions, false);
    // for (File s : list) {
    // baseName = FilenameUtils.getBaseName(s.getName());
    // if (StringUtils.indexOf(baseName, "_") > 0) {
    // baseName = baseName.substring(0, baseName.lastIndexOf("_"));
    // }
    // baseNameList.add(FileUtils.getRelativePath(SysConfigHandler.create().getPattern("common_message_path"),
    // baseName));
    // }
    //
    // return baseNameList;
    // }

    /**
     * 初期化処理
     * 
     * @param resourceBundlesNames
     */
    public static void init(List<String> resourceBundlesNames) {
        synchronized (DEFAULT_RESOURCE_BUNDLES) {
            for (int i = 0; i < resourceBundlesNames.size(); i++) {
                DEFAULT_RESOURCE_BUNDLES.add(resourceBundlesNames.get(i));
            }
        }
    }

    /**
     * メッセージIDに対するメッセージ的取得。
     * 
     * @param messageId メッセージID
     * @return String 取得したメッセージ
     */
    public static String getMessage(String messageId) {
        return getMessage(messageId, "");
    }

    /**
     * メッセージIDに対するメッセージ的取得。
     * 
     * @param messageId メッセージID
     * @param params 埋め込みパラメータ
     * @return String 取得したメッセージ
     */
    public static String getMessage(String messageId, String... params) {
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        builder.append(messageId);
        builder.append("]");
        builder.append(getSimpleMessage(messageId, params));
        String message = builder.toString();

        return message;
    }

    /**
     * メッセージIDに対するメッセージ的取得。
     * 
     * @param messageId メッセージID
     * @param params 埋め込みパラメータ
     * @return String 取得したメッセージ
     */
    public static String getSimpleMessage(String messageId, String... params) {
        String message = findDefaultText(messageId, params);
        return message;
    }

    /**
     * Should resorce bundles be reloaded.
     * 
     * @param reloadBundles reload bundles?
     */
    public static void setReloadBundles(boolean reloadBundles) {
        MessageUtils.reloadBundles = reloadBundles;
    }

    /**
     * Add's the bundle to the internal list of default bundles.
     * <p/>
     * If the bundle already exists in the list it will be readded.
     * 
     * @param resourceBundleName the name of the bundle to add.
     */
    public static void addDefaultResourceBundle(String resourceBundleName) {
        // make sure this doesn't get added more than once
        synchronized (DEFAULT_RESOURCE_BUNDLES) {
            DEFAULT_RESOURCE_BUNDLES.remove(resourceBundleName);
            DEFAULT_RESOURCE_BUNDLES.add(0, resourceBundleName);
        }

        if (LOG.isDebugEnabled()) {
            LOG.debug("Added default resource bundle '" + resourceBundleName + "' to default resource bundles = "
                            + DEFAULT_RESOURCE_BUNDLES);
        }
    }

    /**
     * Returns a localized message for the specified key, aTextName. Neither the key nor the
     * message is evaluated.
     * 
     * @param aTextName the message key
     * @param locale the locale the message should be for
     * @return a localized message based on the specified key, or null if no localized message can be found for it
     */
    private static String findDefaultText(String aTextName, Locale locale) {
        List<String> localList = DEFAULT_RESOURCE_BUNDLES;

        for (String bundleName : localList) {
            ResourceBundle bundle = findResourceBundle(bundleName, locale);
            if (bundle != null) {
                reloadBundles();
                try {
                    return bundle.getString(aTextName);
                } catch (MissingResourceException e) {
                    // ignore and try others
                }
            }
        }

        return null;
    }

    // 2013/01/24 未使用メッソドをコメントする
    // /**
    // * Returns a localized message for the specified key, aTextName. Neither the key nor the
    // * message is evaluated.
    // *
    // * @param aTextName the message key
    // * @return a localized message based on the specified key, or null if no localized message can be found for it
    // */
    // private static String findDefaultText(String aTextName) {
    // return findDefaultText(aTextName, Locale.getDefault());
    // }

    /**
     * Returns a localized message for the specified key, aTextName, substituting variables from the
     * array of params into the message. Neither the key nor the message is evaluated.
     * 
     * @param aTextName the message key
     * @param locale the locale the message should be for
     * @param params an array of objects to be substituted into the message text
     * @return A formatted message based on the specified key, or null if no localized message can be found for it
     */
    private static String findDefaultText(String aTextName, Object[] params) {
        return findDefaultText(aTextName, Locale.getDefault(), params);
    }

    /**
     * Returns a localized message for the specified key, aTextName, substituting variables from the
     * array of params into the message. Neither the key nor the message is evaluated.
     * 
     * @param aTextName the message key
     * @param locale the locale the message should be for
     * @param params an array of objects to be substituted into the message text
     * @return A formatted message based on the specified key, or null if no localized message can be found for it
     */
    private static String findDefaultText(String aTextName, Locale locale, Object[] params) {
        String defaultText = findDefaultText(aTextName, locale);
        if (defaultText != null) {
            MessageFormat mf = buildMessageFormat(defaultText, locale);
            return formatWithNullDetection(mf, params);
        }
        return null;
    }

    /**
     * Finds the given resorce bundle by it's name.
     * <p/>
     * Will use <code>Thread.currentThread().getContextClassLoader()</code> as the classloader. If
     * {@link #delegatedClassLoader} is defined and the bundle cannot be found the current classloader it will delegate
     * to that.
     * 
     * @param aBundleName the name of the bundle (usually it's FQN classname).
     * @param locale the locale.
     * @return the bundle, <tt>null</tt> if not found.
     */
    public static ResourceBundle findResourceBundle(String aBundleName, Locale locale) {
        String key = createMissesKey(aBundleName, locale);

        ResourceBundle bundle = null;

        try {
            if (!bundlesMap.containsKey(key)) {
                bundle = ResourceBundle.getBundle(aBundleName, locale, Thread.currentThread().getContextClassLoader());
                bundlesMap.put(key, bundle);
            }

            bundle = bundlesMap.get(key);
        } catch (MissingResourceException ex) {
            if (delegatedClassLoader != null) {
                try {
                    if (!bundlesMap.containsKey(key)) {
                        bundle = ResourceBundle.getBundle(aBundleName, locale, delegatedClassLoader);
                        bundlesMap.put(key, bundle);
                    }

                    bundle = bundlesMap.get(key);

                } catch (MissingResourceException e) {
                    bundle = EMPTY_BUNDLE;
                    bundlesMap.put(key, bundle);
                }
            } else {
                bundle = EMPTY_BUNDLE;
                bundlesMap.put(key, bundle);
            }
        }
        return (bundle == EMPTY_BUNDLE) ? null : bundle;
    }

    /**
     * Sets a {@link ClassLoader} to look up the bundle from if none can be found on the current thread's classloader
     * 
     * @param classLoader
     */
    public static void setDelegatedClassLoader(final ClassLoader classLoader) {
        synchronized (bundlesMap) {
            delegatedClassLoader = classLoader;
        }
    }

    /**
     * Removes the bundle from any cached "misses"
     * 
     * @param bundleName
     */
    public static void clearBundle(final String bundleName) {
        bundlesMap.remove(bundleName);
    }

    /**
     * Creates a key to used for lookup/storing in the bundle misses cache.
     * 
     * @param aBundleName the name of the bundle (usually it's FQN classname).
     * @param locale the locale.
     * @return the key to use for lookup/storing in the bundle misses cache.
     */
    private static String createMissesKey(String aBundleName, Locale locale) {
        return aBundleName + "_" + locale.toString();
    }

    private static String formatWithNullDetection(MessageFormat mf, Object[] args) {
        String message = mf.format(args);
        if ("null".equals(message)) {
            return null;
        } else {
            return message;
        }
    }

    private static MessageFormat buildMessageFormat(String pattern, Locale locale) {
        MessageFormatKey key = new MessageFormatKey(pattern, locale);
        MessageFormat format = messageFormats.get(key);
        if (format == null) {
            format = new MessageFormat(pattern);
            format.setLocale(locale);
            format.applyPattern(pattern);
            messageFormats.put(key, format);
        }

        return format;
    }

    private static void reloadBundles() {
        if (reloadBundles) {
            try {
                bundlesMap.clear();
                clearMap(ResourceBundle.class, null, "cacheList");
                // now, for the true and utter hack, if we're running in tomcat, clear
                // it's class loader resource cache as well.
                clearTomcatCache();
            } catch (Exception e) {
                LOG.error("Could not reload resource bundles", e);
            }
        }
    }

    private static void clearTomcatCache() {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        // no need for compilation here.
        Class cl = loader.getClass();

        try {
            if ("org.apache.catalina.loader.WebappClassLoader".equals(cl.getName())) {
                clearMap(cl, loader, "resourceEntries");
            } else {
                if (LOG.isDebugEnabled()) {
                    LOG.debug("class loader " + cl.getName() + " is not tomcat loader.");
                }
            }
        } catch (Exception e) {
            LOG.warn("couldn't clear tomcat cache", e);
        }
    }

    private static void clearMap(Class cl, Object obj, String name) throws NoSuchFieldException,
                    IllegalAccessException, NoSuchMethodException, InvocationTargetException {

        Field field = cl.getDeclaredField(name);
        field.setAccessible(true);

        Object cache = field.get(obj);

        synchronized (cache) {
            Class ccl = cache.getClass();
            Method clearMethod = ccl.getMethod("clear");
            clearMethod.invoke(cache);
        }
    }

    static class MessageFormatKey {
        String pattern;
        Locale locale;

        MessageFormatKey(String pattern, Locale locale) {
            this.pattern = pattern;
            this.locale = locale;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof MessageFormatKey)) {
                return false;
            }
            final MessageFormatKey messageFormatKey = (MessageFormatKey) o;

            if (locale != null ? !locale.equals(messageFormatKey.locale) : messageFormatKey.locale != null) {
                return false;
            }
            if (pattern != null ? !pattern.equals(messageFormatKey.pattern) : messageFormatKey.pattern != null) {
                return false;
            }

            return true;
        }

        @Override
        public int hashCode() {
            int result = 0;
            result = (pattern != null ? pattern.hashCode() : 0);
            result = 29 * result + (locale != null ? locale.hashCode() : 0);
            return result;
        }
    }

    static class GetDefaultMessageReturnArg {
        String message;
        boolean foundInBundle;

        public GetDefaultMessageReturnArg(String message, boolean foundInBundle) {
            this.message = message;
            this.foundInBundle = foundInBundle;
        }
    }

    private static class EmptyResourceBundle extends ResourceBundle {
        @Override
        public Enumeration<String> getKeys() {
            return null; // dummy
        }

        @Override
        protected Object handleGetObject(String key) {
            return null; // dummy
        }
    }

}
