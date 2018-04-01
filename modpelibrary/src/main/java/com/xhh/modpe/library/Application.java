package com.xhh.modpe.library;

import android.app.Activity;
import android.content.Context;

import com.xhh.modpe.library.base.Function;
import com.xhh.modpe.library.base.IFunction;

import java.util.ArrayList;

public class Application implements IFunction {

    private Activity activity;
    private Context context;

    public Application(Activity activity, Context context) {
        this.activity = activity;
        this.context = context;
        Function.getInstance().addListener(this);
        init();
    }

    public void init() {

    }

    public void showWindow(Class clazz) {
        Mod.showWindow(activity, context, clazz);
    }

    public void startActivity(Class clazz) {
        Mod.startActivity(activity, context, clazz);
    }

    public Activity getActivity() {
        return activity;
    }

    public Context getContext() {
        return context;
    }

    @Override
    public void attackHook(long attacker, long victim) {

    }

    @Override
    public void chatHook(String str) {

    }

    @Override
    public void continueDestroyBlock(int x, int y, int z, int side, float progress) {

    }

    @Override
    public void destroyBlock(int x, int y, int z, int side) {

    }

    @Override
    public void projectileHitEntityHook(long projectile, long targetEntity) {

    }

    @Override
    public void eatHook(int hearts, float saturationRatio) {

    }

    @Override
    public void entityAddedHook(long entity) {

    }

    @Override
    public void entityHurtHook(long attacker, long victim, int halfhearts) {

    }

    @Override
    public void entityRemovedHook(long entity) {

    }

    @Override
    public void explodeHook(long entity, float x, float y, float z, float power, boolean onFire) {

    }

    @Override
    public void serverMessageReceiveHook(String str) {

    }

    @Override
    public void chatReceiveHook(String str, String sender) {

    }

    @Override
    public void leaveGame() {

    }

    @Override
    public void deathHook(long attacker, long victim) {

    }

    @Override
    public void playerAddExpHook(long player, int experienceAdded) {

    }

    @Override
    public void playerExpLevelChangeHook(long player, int levelsAdded) {

    }

    @Override
    public void redstoneUpdateHook(int x, int y, int z, int newCurrent, boolean worldLoading, int blockId, int blockDamage) {

    }

    @Override
    public void screenChangeHook(String screenName) {

    }

    @Override
    public void selectLevelHook() {

    }

    @Override
    public void newLevel() {

    }

    @Override
    public void startDestroyBlock(int x, int y, int z, int side) {

    }

    @Override
    public void projectileHitBlockHook(long projectile, int blockX, int blockY, int blockZ, int side) {

    }

    @Override
    public void modTick() {

    }

    @Override
    public void useItem(int x, int y, int z, int itemid, int blockid, int side, int itemDamage, int blockDamage) {

    }
}
