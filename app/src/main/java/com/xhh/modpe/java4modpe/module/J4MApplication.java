package com.xhh.modpe.java4modpe.module;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;

import com.xhh.modpe.java4modpe.module.manager.ModuleManager;
import com.xhh.modpe.java4modpe.module.model.AppData;
import com.xhh.modpe.java4modpe.module.window.ManagerWindow;
import com.xhh.modpe.java4modpe.util.ModuleUtil;
import com.xhh.modpe.library.Application;
import com.xhh.modpe.library.Mod;

import java.io.File;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import dalvik.system.DexClassLoader;

public class J4MApplication extends Application implements ModuleManager.OnModuleLoadListener {

    private ArrayList<AppData> appDatas = new ArrayList<>();

    public J4MApplication(Activity activity, Context context) {
        super(activity, context);
        File file = new File(ModuleUtil.MOD_PATH);
        if (file.exists()) {
            file.delete();
        }
    }

    @Override
    public void init() {
        super.init();
        showWindow(ManagerWindow.class);
        ModuleManager.getInstance(this).addOnModuleLoadListener(this);
    }

    @Override
    public void onFinished(ArrayList<AppData> appDatas) {
        this.appDatas = appDatas;
        Mod.print("加载" + appDatas.size() + "个modpe");
    }

    @Override
    public void attackHook(long attacker, long victim) {
        ModuleManager.onFunction(
                "attackHook",
                appDatas,
                new Class[]{long.class, long.class},
                new Object[]{attacker, victim});
    }

    @Override
    public void chatHook(String str) {
        ModuleManager.onFunction(
                "chatHook",
                appDatas,
                new Class[]{String.class},
                new Object[]{str});
    }

    @Override
    public void continueDestroyBlock(int x, int y, int z, int side, float progress) {
        ModuleManager.onFunction(
                "continueDestroyBlock",
                appDatas,
                new Class[]{int.class, int.class, int.class, int.class},
                new Object[]{x, y, z, side, progress});
    }

    @Override
    public void destroyBlock(int x, int y, int z, int side) {
        ModuleManager.onFunction(
                "destroyBlock",
                appDatas,
                new Class[]{int.class, int.class, int.class, int.class},
                new Object[]{x, y, z, side});
    }

    @Override
    public void projectileHitEntityHook(long projectile, long targetEntity) {
        ModuleManager.onFunction(
                "projectileHitEntityHook",
                appDatas,
                new Class[]{long.class, long.class},
                new Object[]{projectile, targetEntity});
    }

    @Override
    public void eatHook(int hearts, float saturationRatio) {
        ModuleManager.onFunction(
                "eatHook",
                appDatas,
                new Class[]{int.class, float.class},
                new Object[]{hearts, saturationRatio});
    }

    @Override
    public void entityAddedHook(long entity) {
        ModuleManager.onFunction(
                "entityAddedHook",
                appDatas,
                new Class[]{long.class},
                new Object[]{entity});
    }

    @Override
    public void entityHurtHook(long attacker, long victim, int halfhearts) {
        ModuleManager.onFunction(
                "entityHurtHook",
                appDatas,
                new Class[]{long.class, long.class, int.class},
                new Object[]{attacker, victim, halfhearts});
    }

    @Override
    public void entityRemovedHook(long entity) {
        ModuleManager.onFunction(
                "entityRemovedHook",
                appDatas,
                new Class[]{long.class},
                new Object[]{entity});
    }

    @Override
    public void explodeHook(long entity, float x, float y, float z, float power, boolean onFire) {
        ModuleManager.onFunction(
                "explodeHook",
                appDatas,
                new Class[]{long.class, float.class, float.class, float.class, float.class, boolean.class},
                new Object[]{entity, x, y, z, power, onFire});
    }

    @Override
    public void serverMessageReceiveHook(String str) {
        ModuleManager.onFunction(
                "serverMessageReceiveHook",
                appDatas,
                new Class[]{String.class},
                new Object[]{str});
    }

    @Override
    public void chatReceiveHook(String str, String sender) {
        ModuleManager.onFunction(
                "chatReceiveHook",
                appDatas,
                new Class[]{String.class, String.class},
                new Object[]{str, sender});
    }

    @Override
    public void leaveGame() {
        ModuleManager.onFunction(
                "leaveGame",
                appDatas,
                new Class[]{},
                new Object[]{});
    }

    @Override
    public void deathHook(long attacker, long victim) {
        ModuleManager.onFunction(
                "deathHook",
                appDatas,
                new Class[]{long.class, long.class},
                new Object[]{attacker, victim});
    }

    @Override
    public void playerAddExpHook(long player, int experienceAdded) {
        ModuleManager.onFunction(
                "playerAddExpHook",
                appDatas,
                new Class[]{long.class, int.class},
                new Object[]{player, experienceAdded});

    }

    @Override
    public void playerExpLevelChangeHook(long player, int levelsAdded) {
        ModuleManager.onFunction(
                "playerExpLevelChangeHook",
                appDatas,
                new Class[]{long.class, int.class},
                new Object[]{player, levelsAdded});
    }

    @Override
    public void redstoneUpdateHook(int x, int y, int z, int newCurrent, boolean worldLoading, int blockId, int blockDamage) {
        ModuleManager.onFunction(
                "redstoneUpdateHook",
                appDatas,
                new Class[]{int.class, int.class, int.class, int.class, boolean.class, int.class, int.class},
                new Object[]{x, y, z, newCurrent, worldLoading, blockId, blockDamage});
    }

    @Override
    public void screenChangeHook(String screenName) {
        ModuleManager.onFunction(
                "screenChangeHook",
                appDatas,
                new Class[]{String.class},
                new Object[]{screenName});
    }

    @Override
    public void selectLevelHook() {
        ModuleManager.onFunction(
                "selectLevelHook",
                appDatas,
                new Class[]{},
                new Object[]{});
    }

    @Override
    public void newLevel() {
        ModuleManager.onFunction(
                "newLevel",
                appDatas,
                new Class[]{},
                new Object[]{});
    }

    @Override
    public void startDestroyBlock(int x, int y, int z, int side) {
        ModuleManager.onFunction(
                "startDestroyBlock",
                appDatas,
                new Class[]{int.class, int.class, int.class},
                new Object[]{x, y, z, side});
    }

    @Override
    public void projectileHitBlockHook(long projectile, int blockX, int blockY, int blockZ, int side) {
        ModuleManager.onFunction(
                "projectileHitBlockHook",
                appDatas,
                new Class[]{long.class, int.class, int.class, int.class, int.class},
                new Object[]{projectile, blockX, blockY, blockZ, side});
    }

    @Override
    public void modTick() {
        ModuleManager.onFunction(
                "modTick",
                appDatas,
                new Class[]{},
                new Object[]{});
    }

    @Override
    public void useItem(int x, int y, int z, int itemid, int blockid, int side, int itemDamage, int blockDamage) {
        ModuleManager.onFunction(
                "useItem",
                appDatas,
                new Class[]{int.class, int.class, int.class, int.class, int.class, int.class, int.class, int.class},
                new Object[]{x, y, z, itemid, blockid, side, itemDamage, blockDamage});
    }
}
