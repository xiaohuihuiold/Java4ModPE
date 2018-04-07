package com.xhh.modpe.library;


import org.mozilla.javascript.NativeJavaObject;
import org.mozilla.javascript.Undefined;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by nameh on 2018/3/28 0028.
 */

public class Mod{

    private String application;

    public static boolean isPro = false;

    public static Object execScriptManager(String className, String methodName, Class<?>[] classes, Object[] objects) {
        Object object = null;
        if (!isPro) {
            className = "net.zhuoweizhang.mcpelauncher.ScriptManager$" + className;
        }
        try {
            Class<?> scr = Class.forName(className);
            Method method = scr.getMethod(methodName, classes);
            object = method.invoke(null, objects);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return object;
    }

    public static Object exec(String className, String methodName, Class<?>[] classes, Object[] objects) {
        Object object = null;
        if (!isPro) {
            className =className;
        }
        try {
            Class<?> scr = Class.forName(className);
            Method method = scr.getMethod(methodName, classes);
            object = method.invoke(null, objects);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return object;
    }

    public static long getEntityId(Object paramObject) {
        if (paramObject == null) {
            return -1L;
        }
        if ((paramObject instanceof NativeJavaObject)) {
            return (Long) ((NativeJavaObject) paramObject).unwrap();
        }
        if ((paramObject instanceof Number)) {
            return ((Number) paramObject).longValue();
        }
        if ((paramObject instanceof Undefined)) {
            return 0L;
        }
        throw new RuntimeException("Not an entity: " + paramObject + " (" + paramObject.getClass().toString() + ")");
    }

}
