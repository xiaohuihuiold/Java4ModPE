package com.xhh.modpe.library.base;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;

import com.xhh.modpe.library.Mod;

public abstract class BaseDialog extends Dialog implements IFunction{

    private Activity activity;
    private Context contextMod;

    public BaseDialog(Context context, Context contextMod) {
        super(context);
        this.activity = (Activity) context;
        this.contextMod = contextMod;
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        Function.getInstance().addListener(this);
    }

    public void setView(int resourcesId){
        setView(LayoutInflater.from(getContextMod()).inflate(getContextMod().getResources().getLayout(resourcesId),null,false));
    }

    public void setView(View view){
        setContentView(view);
    }

    public View findViewById(int resourceId){
        return getWindow().findViewById(resourceId);
    }

    public void showWindow(Class clazz){
        Mod.showWindow(getActivity(),getContextMod(),clazz);
    }

    public void startActivity(Class clazz){
        Mod.startActivity(getActivity(),getContextMod(),clazz);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Function.getInstance().removeListener(this);
    }

    public void onUI(Runnable runnable){
        getActivity().runOnUiThread(runnable);
    }

    public abstract void onCreate();

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

    public Activity getActivity() {
        return activity;
    }

    public Context getContextMod() {
        return contextMod;
    }

}
