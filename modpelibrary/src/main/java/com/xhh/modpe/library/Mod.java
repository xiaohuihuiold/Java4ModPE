package com.xhh.modpe.library;

import android.app.Activity;
import android.content.Context;
import android.os.Looper;
import android.widget.Toast;

import com.xhh.modpe.library.activity.BaseActivity;
import com.xhh.modpe.library.base.Function;
import com.xhh.modpe.library.base.IFunction;
import com.xhh.modpe.library.window.BaseWindow;

import org.mozilla.javascript.NativeJavaObject;
import org.mozilla.javascript.Undefined;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by nameh on 2018/3/28 0028.
 */

public class Mod implements Runnable, IFunction {

    private String application;
    private static Activity activity;
    private static Context context;

    public static boolean isPro = false;

    public void init(String application, Activity act, Context con) {
        this.application = application;
        activity = act;
        context = con;


        if (activity.getPackageName().equals("net.zhuoweizhang.mcpelauncher")) {
            isPro = false;
        } else {
            isPro = true;
        }

        activity.runOnUiThread(this);

    }

    @Override
    public void run() {
        if (application == null) {
            return;
        }
        try {
            Class<?> clazz = Class.forName(application);
            Constructor constructor = clazz.getConstructor(Activity.class, Context.class);
            constructor.newInstance(activity, context);
        } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException | ClassNotFoundException e) {
            e.printStackTrace();
            print("Mod加载错误:" + e.getMessage());
        }
    }

    public static void showWindow(Activity activity, Context context, Class clazz) {
        try {
            Constructor<?> constructor = clazz.getConstructor(Context.class);
            BaseWindow baseActivity = (BaseWindow) constructor.newInstance(activity);
            baseActivity.setContextMod(context);
            baseActivity.show();
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
            e.printStackTrace();
        }
    }

    public static void startActivity(Activity activity, Context context, Class clazz) {
        try {
            Constructor<?> constructor = clazz.getConstructor(Context.class);
            BaseActivity baseActivity = (BaseActivity) constructor.newInstance(activity);
            baseActivity.setContextMod(context);
            baseActivity.show();
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
            e.printStackTrace();
        }
    }

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
            return ((Long) ((NativeJavaObject) paramObject).unwrap()).longValue();
        }
        if ((paramObject instanceof Number)) {
            return ((Number) paramObject).longValue();
        }
        if ((paramObject instanceof Undefined)) {
            return 0L;
        }
        throw new RuntimeException("Not an entity: " + paramObject + " (" + paramObject.getClass().toString() + ")");
    }

    public static void print(final String text) {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(activity, text, Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void attackHook(long attacker, long victim) {
        Function.getInstance().attackHook(attacker, victim);
    }

    @Override
    public void chatHook(String str) {
        Function.getInstance().chatHook(str);
    }

    @Override
    public void continueDestroyBlock(int x, int y, int z, int side, float progress) {
        Function.getInstance().continueDestroyBlock(x, y, z, side, progress);
    }

    @Override
    public void destroyBlock(int x, int y, int z, int side) {
        Function.getInstance().destroyBlock(x, y, z, side);
    }

    @Override
    public void projectileHitEntityHook(long projectile, long targetEntity) {
        Function.getInstance().projectileHitEntityHook(projectile, targetEntity);
    }

    @Override
    public void eatHook(int hearts, float saturationRatio) {
        Function.getInstance().eatHook(hearts, saturationRatio);
    }

    @Override
    public void entityAddedHook(long entity) {
        Function.getInstance().entityAddedHook(entity);
    }

    @Override
    public void entityHurtHook(long attacker, long victim, int halfhearts) {
        Function.getInstance().entityHurtHook(attacker, victim, halfhearts);
    }

    @Override
    public void entityRemovedHook(long entity) {
        Function.getInstance().entityRemovedHook(entity);
    }

    @Override
    public void explodeHook(long entity, float x, float y, float z, float power, boolean onFire) {
        Function.getInstance().explodeHook(entity, x, y, z, power, onFire);
    }

    @Override
    public void serverMessageReceiveHook(String str) {
        Function.getInstance().serverMessageReceiveHook(str);
    }

    @Override
    public void chatReceiveHook(String str, String sender) {
        Function.getInstance().chatReceiveHook(str, sender);
    }

    @Override
    public void leaveGame() {
        Function.getInstance().leaveGame();
    }

    @Override
    public void deathHook(long attacker, long victim) {
        Function.getInstance().deathHook(attacker, victim);
    }

    @Override
    public void playerAddExpHook(long player, int experienceAdded) {
        Function.getInstance().playerAddExpHook(player, experienceAdded);
    }

    @Override
    public void playerExpLevelChangeHook(long player, int levelsAdded) {
        Function.getInstance().playerExpLevelChangeHook(player, levelsAdded);
    }

    @Override
    public void redstoneUpdateHook(int x, int y, int z, int newCurrent, boolean worldLoading, int blockId, int blockDamage) {
        Function.getInstance().redstoneUpdateHook(x, y, z, newCurrent, worldLoading, blockId, blockDamage);
    }

    @Override
    public void screenChangeHook(String screenName) {
        Function.getInstance().screenChangeHook(screenName);
    }

    @Override
    public void selectLevelHook() {
        Function.getInstance().selectLevelHook();
    }

    @Override
    public void newLevel() {
        Function.getInstance().newLevel();
    }

    @Override
    public void startDestroyBlock(int x, int y, int z, int side) {
        Function.getInstance().startDestroyBlock(x, y, z, side);
    }

    @Override
    public void projectileHitBlockHook(long projectile, int blockX, int blockY, int blockZ, int side) {
        Function.getInstance().projectileHitBlockHook(projectile, blockX, blockY, blockZ, side);
    }

    @Override
    public void modTick() {
        Function.getInstance().modTick();
    }

    @Override
    public void useItem(int x, int y, int z, int itemid, int blockid, int side, int itemDamage, int blockDamage) {
        Function.getInstance().useItem(x, y, z, itemid, blockid, side, itemDamage, blockDamage);
    }
}
