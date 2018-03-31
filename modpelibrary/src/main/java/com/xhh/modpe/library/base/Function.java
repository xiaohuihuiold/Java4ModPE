package com.xhh.modpe.library.base;

import java.util.ArrayList;

public class Function implements IFunction {

    private static Function function;

    private ArrayList<IFunction> iFunctions=new ArrayList<>();

    private Function(){}

    public synchronized static Function getInstance(){
        if(function==null)function=new Function();
        return function;
    }

    public void addListener(IFunction iFunction){
        iFunctions.add(iFunction);
        check();
    }

    public void removeListener(IFunction iFunction){
        iFunctions.remove(iFunction);
        check();
    }

    public void check(){
        int size=iFunctions.size();
        for(int i=0;i<size;i++){
            if(iFunctions.get(i)==null){
                iFunctions.remove(i);
                size--;
                i--;
            }
        }
    }

    @Override
    public void attackHook(long attacker, long victim) {
        for (IFunction function:iFunctions){
            function.attackHook(attacker,victim);
        }
    }

    @Override
    public void chatHook(String str) {
        for (IFunction function:iFunctions){
            function.chatHook(str);
        }
    }

    @Override
    public void continueDestroyBlock(int x, int y, int z, int side, float progress) {
        for (IFunction function:iFunctions){
            function.continueDestroyBlock(x,y,z,side,progress);
        }
    }

    @Override
    public void destroyBlock(int x, int y, int z, int side) {
        for (IFunction function:iFunctions){
            function.destroyBlock(x,y,z,side);
        }
    }

    @Override
    public void projectileHitEntityHook(long projectile, long targetEntity) {
        for (IFunction function:iFunctions){
            function.projectileHitEntityHook(projectile,targetEntity);
        }
    }

    @Override
    public void eatHook(int hearts, float saturationRatio) {
        for (IFunction function:iFunctions){
            function.eatHook(hearts,saturationRatio);
        }
    }

    @Override
    public void entityAddedHook(long entity) {
        for (IFunction function:iFunctions){
            function.entityAddedHook(entity);
        }
    }

    @Override
    public void entityHurtHook(long attacker, long victim, int halfhearts) {
        for (IFunction function:iFunctions){
            function.entityHurtHook(attacker,victim,halfhearts);
        }
    }

    @Override
    public void entityRemovedHook(long entity) {
        for (IFunction function:iFunctions){
            function.entityRemovedHook(entity);
        }
    }

    @Override
    public void explodeHook(long entity, float x, float y, float z, float power, boolean onFire) {
        for (IFunction function:iFunctions){
            function.explodeHook(entity,x,y,z,power,onFire);
        }
    }

    @Override
    public void serverMessageReceiveHook(String str) {
        for (IFunction function:iFunctions){
            function.serverMessageReceiveHook(str);
        }
    }

    @Override
    public void chatReceiveHook(String str, String sender) {
        for (IFunction function:iFunctions){
            function.chatReceiveHook(str,sender);
        }
    }

    @Override
    public void leaveGame() {
        for (IFunction function:iFunctions){
            function.leaveGame();
        }
    }

    @Override
    public void deathHook(long attacker, long victim) {
        for (IFunction function:iFunctions){
            function.deathHook(attacker,victim);
        }
    }

    @Override
    public void playerAddExpHook(long player, int experienceAdded) {
        for (IFunction function:iFunctions){
            function.playerAddExpHook(player,experienceAdded);
        }
    }

    @Override
    public void playerExpLevelChangeHook(long player, int levelsAdded) {
        for (IFunction function:iFunctions){
            function.playerExpLevelChangeHook(player,levelsAdded);
        }
    }

    @Override
    public void redstoneUpdateHook(int x, int y, int z, int newCurrent, boolean worldLoading, int blockId, int blockDamage) {
        for (IFunction function:iFunctions){
            function.redstoneUpdateHook(x,y,z,newCurrent,worldLoading,blockId,blockDamage);
        }
    }

    @Override
    public void screenChangeHook(String screenName) {
        for (IFunction function:iFunctions){
            function.screenChangeHook(screenName);
        }
    }

    @Override
    public void selectLevelHook() {
        for (IFunction function:iFunctions){
            function.selectLevelHook();
        }
    }

    @Override
    public void newLevel() {
        for (IFunction function:iFunctions){
            function.newLevel();
        }
    }

    @Override
    public void startDestroyBlock(int x, int y, int z, int side) {
        for (IFunction function:iFunctions){
            function.startDestroyBlock(x,y,z,side);
        }
    }

    @Override
    public void projectileHitBlockHook(long projectile, int blockX, int blockY, int blockZ, int side) {
        for (IFunction function:iFunctions){
            function.projectileHitBlockHook(projectile,blockX,blockY,blockZ,side);
        }
    }

    @Override
    public void modTick() {
        for (IFunction function:iFunctions){
            function.modTick();
        }
    }

    @Override
    public void useItem(int x, int y, int z, int itemid, int blockid, int side, int itemDamage, int blockDamage) {
        for (IFunction function:iFunctions){
            function.useItem(x,y,z,itemid,blockid,side,itemDamage,blockDamage);
        }
    }
}
