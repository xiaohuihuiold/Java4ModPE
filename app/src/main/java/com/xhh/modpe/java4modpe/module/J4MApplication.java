package com.xhh.modpe.java4modpe.module;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import com.xhh.modpe.java4modpe.manager.ModuleManager;
import com.xhh.modpe.java4modpe.model.AppData;
import com.xhh.modpe.java4modpe.module.base.Function;

import java.util.ArrayList;

public class J4MApplication implements ModuleManager.OnModuleLoadListener{

    public static Activity activity;
    public static Context context;

    public void init(Activity act, Context con) {
        activity = act;
        context = con;
        ModuleManager.getInstance(act,con).addOnModuleLoadListener(this);
    }

    public static void print(final String text) {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(activity, text, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onFinished(ArrayList<AppData> appDatas) {
        print("模块加载完成");
    }

    public void attackHook(long attacker, long victim) {
        Function.getInstance().attackHook(attacker, victim);
    }

    public void chatHook(String str) {
        Function.getInstance().chatHook(str);
    }

    public void continueDestroyBlock(int x, int y, int z, int side, float progress) {
        Function.getInstance().continueDestroyBlock(x, y, z, side, progress);
    }

    public void destroyBlock(int x, int y, int z, int side) {
        Function.getInstance().destroyBlock(x, y, z, side);
    }

    public void projectileHitEntityHook(long projectile, long targetEntity) {
        Function.getInstance().projectileHitEntityHook(projectile, targetEntity);
    }

    public void eatHook(int hearts, float saturationRatio) {
        Function.getInstance().eatHook(hearts, saturationRatio);
    }

    public void entityAddedHook(long entity) {
        Function.getInstance().entityAddedHook(entity);
    }

    public void entityHurtHook(long attacker, long victim, int halfhearts) {
        Function.getInstance().entityHurtHook(attacker, victim, halfhearts);
    }

    public void entityRemovedHook(long entity) {
        Function.getInstance().entityRemovedHook(entity);
    }

    public void explodeHook(long entity, float x, float y, float z, float power, boolean onFire) {
        Function.getInstance().explodeHook(entity, x, y, z, power, onFire);
    }

    public void serverMessageReceiveHook(String str) {
        Function.getInstance().serverMessageReceiveHook(str);
    }

    public void chatReceiveHook(String str, String sender) {
        Function.getInstance().chatReceiveHook(str, sender);
    }

    public void leaveGame() {
        Function.getInstance().leaveGame();
    }

    public void deathHook(long attacker, long victim) {
        Function.getInstance().deathHook(attacker, victim);
    }

    public void playerAddExpHook(long player, int experienceAdded) {
        Function.getInstance().playerAddExpHook(player, experienceAdded);
    }

    public void playerExpLevelChangeHook(long player, int levelsAdded) {
        Function.getInstance().playerExpLevelChangeHook(player, levelsAdded);
    }

    public void redstoneUpdateHook(int x, int y, int z, int newCurrent, boolean worldLoading, int blockId, int blockDamage) {
        Function.getInstance().redstoneUpdateHook(x, y, z, newCurrent, worldLoading, blockId, blockDamage);
    }

    public void screenChangeHook(String screenName) {
        Function.getInstance().screenChangeHook(screenName);
    }

    public void selectLevelHook() {
        Function.getInstance().selectLevelHook();
    }

    public void newLevel() {
        Function.getInstance().newLevel();
    }

    public void startDestroyBlock(int x, int y, int z, int side) {
        Function.getInstance().startDestroyBlock(x, y, z, side);
    }

    public void projectileHitBlockHook(long projectile, int blockX, int blockY, int blockZ, int side) {
        Function.getInstance().projectileHitBlockHook(projectile, blockX, blockY, blockZ, side);
    }

    public void modTick() {
        Function.getInstance().modTick();
    }

    public void useItem(int x, int y, int z, int itemid, int blockid, int side, int itemDamage, int blockDamage) {
        Function.getInstance().useItem(x, y, z, itemid, blockid, side, itemDamage, blockDamage);
    }

}
