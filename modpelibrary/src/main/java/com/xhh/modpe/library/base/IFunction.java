package com.xhh.modpe.library.base;

public interface IFunction {

    public void attackHook(long attacker, long victim);

    public void chatHook(String str);

    public void continueDestroyBlock(int x, int y, int z, int side, float progress);

    public void destroyBlock(int x, int y, int z, int side);

    public void projectileHitEntityHook(long projectile, long targetEntity);

    public void eatHook(int hearts, float saturationRatio);

    public void entityAddedHook(long entity);

    public void entityHurtHook(long attacker, long victim, int halfhearts);

    public void entityRemovedHook(long entity);

    public void explodeHook(long entity, float x, float y, float z, float power, boolean onFire);

    public void serverMessageReceiveHook(String str);

    public void chatReceiveHook(String str, String sender);

    public void leaveGame();

    public void deathHook(long attacker, long victim);

    public void playerAddExpHook(long player, int experienceAdded);

    public void playerExpLevelChangeHook(long player, int levelsAdded);

    public void redstoneUpdateHook(int x, int y, int z, int newCurrent, boolean worldLoading, int blockId, int blockDamage);

    public void screenChangeHook(String screenName);

    public void selectLevelHook();

    public void newLevel();

    public void startDestroyBlock(int x, int y, int z, int side);

    public void projectileHitBlockHook(long projectile, int blockX, int blockY, int blockZ, int side);

    public void modTick();

    public void useItem(int x, int y, int z, int itemid, int blockid, int side, int itemDamage, int blockDamage);
}
