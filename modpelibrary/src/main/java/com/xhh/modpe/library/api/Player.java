package com.xhh.modpe.library.api;

import android.util.Log;

import com.xhh.modpe.library.Mod;

import net.zhuoweizhang.mcpelauncher.ScriptManager;

public class Player {
    private final static String TAG = "Player";

    public static void addExp(int exps) {
        if (!Mod.isPro) {
            ScriptManager.nativePlayerAddExperience(exps);
        }
    }

    public static void addItemCreativeInv(int itemId, int count, int damage) {
        if (!Mod.isPro) {
            if (!ScriptManager.nativeIsValidItem(itemId)) {
                Log.e(TAG, "没有找到ID:" + itemId);
                return;
            }
            ScriptManager.nativeAddItemCreativeInv(itemId, count, damage);
        }
    }

    public static void addItemInventory(int itemId, int count, int damage) {
        if (!Mod.isPro) {
            if (!ScriptManager.nativeIsValidItem(itemId)) {
                Log.e(TAG, "没有找到ID:" + itemId);
                return;
            }
            ScriptManager.nativeAddItemInventory(itemId, count, damage);
        }
    }

    public static boolean canFly() {
        if (!Mod.isPro) {
            return ScriptManager.nativePlayerCanFly();
        }
        return false;
    }

    public static void clearInventorySlot(int grids) {
        if (!Mod.isPro) {
            ScriptManager.nativeClearSlotInventory(grids);
        }
    }

    public static void enchant(int type, int id, int level) {
        if (!Mod.isPro) {
            if ((id < 0) || (id > 24)) {
                Log.e(TAG, "没有找到ID:" + id);
                return;
            }
            ScriptManager.nativePlayerEnchant(type, id, level);
        }
    }

    public static int getArmorSlot(int index) {
        return Entity.getArmor(getEntity(), index);
    }

    public static int getArmorSlotDamage(int index) {
        return Entity.getArmorDamage(getEntity(), index);
    }

    public static int getCarriedItem() {
        if (!Mod.isPro) {
            return ScriptManager.nativeGetCarriedItem(0);
        }
        return -1;
    }

    public static int getCarriedItemCount() {
        if (!Mod.isPro) {
            return ScriptManager.nativeGetCarriedItem(2);
        }
        return -1;
    }

    public static int getCarriedItemData() {
        if (!Mod.isPro) {
            return ScriptManager.nativeGetCarriedItem(1);
        }
        return -1;
    }

    public static int getDimension() {
        if (!Mod.isPro) {
            return ScriptManager.nativePlayerGetDimension();
        }
        return -1;
    }

    public static Object[] getEnchantments(int index) {
        Object object = null;
        if (!Mod.isPro) {
            object = Mod.execScriptManager(
                    "NativePlayerApi",
                    "getEnchantments",
                    new Class[]{int.class},
                    new Object[]{index}
            );
        }
        if (object != null && object instanceof Object[]) {
            return (Object[]) object;
        }
        return null;
    }

    public static long getEntity() {
        if (!Mod.isPro) {
            return ScriptManager.nativeGetPlayerEnt();
        }
        return -1;
    }

    public static double getExhaustion() {
        if (!Mod.isPro) {
            return ScriptManager.nativePlayerGetExhaustion();
        }
        return -1;
    }

    public static double getExp() {
        if (!Mod.isPro) {
            return ScriptManager.nativePlayerGetExperience();
        }
        return -1;
    }

    public static double getHunger() {
        if (!Mod.isPro) {
            return ScriptManager.nativePlayerGetHunger(getEntity());
        }
        return -1;
    }

    public static int getInventorySlot(int index) {
        if (!Mod.isPro) {
            return ScriptManager.nativeGetSlotInventory(index, 0);
        }
        return -1;
    }


    public static int getInventorySlotCount(int index) {
        if (!Mod.isPro) {
            return ScriptManager.nativeGetSlotInventory(index, 2);
        }
        return -1;
    }

    public static int getInventorySlotData(int index) {
        if (!Mod.isPro) {
            return ScriptManager.nativeGetSlotInventory(index, 1);
        }
        return -1;
    }

    public static String getItemCustomName(int itemId) {
        if (!Mod.isPro) {
            return ScriptManager.nativePlayerGetItemCustomName(itemId);
        }
        return null;
    }

    public static int getLevel() {
        if (!Mod.isPro) {
            return ScriptManager.nativePlayerGetLevel();
        }
        return -1;
    }

    public static String getName(Object player) {
        Object object = null;
        if (!Mod.isPro) {
            object = Mod.execScriptManager(
                    "NativePlayerApi",
                    "getName",
                    new Class[]{Object.class},
                    new Object[]{player}
            );
        }
        if (object != null && object instanceof String) {
            return (String) object;
        }
        return null;
    }

    public static int getPointedBlockData() {
        if (!Mod.isPro) {
            return ScriptManager.nativePlayerGetPointedBlock(17);
        }
        return -1;
    }

    public static int getPointedBlockId() {
        if (!Mod.isPro) {
            return ScriptManager.nativePlayerGetPointedBlock(16);
        }
        return -1;
    }

    public static int getPointedBlockSide() {
        if (!Mod.isPro) {
            return ScriptManager.nativePlayerGetPointedBlock(18);
        }
        return -1;
    }

    public static int getPointedBlockX() {
        if (!Mod.isPro) {
            return ScriptManager.nativePlayerGetPointedBlock(0);
        }
        return -1;
    }

    public static int getPointedBlockY() {
        if (!Mod.isPro) {
            return ScriptManager.nativePlayerGetPointedBlock(1);
        }
        return -1;
    }

    public static int getPointedBlockZ() {
        if (!Mod.isPro) {
            return ScriptManager.nativePlayerGetPointedBlock(2);
        }
        return -1;
    }

    public static long getPointedEntity() {
        if (!Mod.isPro) {
            return ScriptManager.nativePlayerGetPointedEntity();
        }
        return -1;
    }

    public static double getPointedVecX() {
        if (!Mod.isPro) {
            return ScriptManager.nativePlayerGetPointedVec(0);
        }
        return -1;
    }

    public static double getPointedVecY() {
        if (!Mod.isPro) {
            return ScriptManager.nativePlayerGetPointedVec(1);
        }
        return -1;
    }

    public static double getPointedVecZ() {
        if (!Mod.isPro) {
            return ScriptManager.nativePlayerGetPointedVec(2);
        }
        return -1;
    }

    public static double getSaturation() {
        if (!Mod.isPro) {
            return ScriptManager.nativePlayerGetSaturation();
        }
        return -1;
    }

    public static int getScore() {
        if (!Mod.isPro) {
            return ScriptManager.nativePlayerGetScore();
        }
        return -1;
    }

    public static int getSelectedSlotId() {
        if (!Mod.isPro) {
            return ScriptManager.nativeGetSelectedSlotId();
        }
        return -1;
    }

    public static double getX() {
        if (!Mod.isPro) {
            return ScriptManager.nativeGetPlayerLoc(0);
        }
        return -1;
    }

    public static double getY() {
        if (!Mod.isPro) {
            return ScriptManager.nativeGetPlayerLoc(1);
        }
        return -1;
    }

    public static double getZ() {
        if (!Mod.isPro) {
            return ScriptManager.nativeGetPlayerLoc(2);
        }
        return -1;
    }

    public static boolean isFlying() {
        if (!Mod.isPro) {
            return ScriptManager.nativePlayerIsFlying();
        }
        return false;
    }

    public static boolean isPlayer(Object entity) {
        if (!Mod.isPro) {
            return Entity.getEntityTypeId(Mod.getEntityId(entity)) == 63;
        }
        return false;
    }

    public static void setArmorSlot(int index, int id, int damage) {
        if (!Mod.isPro) {
            if (!ScriptManager.nativeIsValidItem(id)) {
                Log.e(TAG, "错误的ID:" + id);
                return;
            }
            Entity.setArmor(getEntity(), index, id, damage);
        }
    }

    public static void setCanFly(boolean isCanFly) {
        if (!Mod.isPro) {
            ScriptManager.nativePlayerSetCanFly(isCanFly);
        }
    }

    public static void setExhaustion(float exhaustion) {
        if (!Mod.isPro) {
            ScriptManager.nativePlayerSetExhaustion(exhaustion);
        }
    }

    public static void setExp(float exp) {
        if (!Mod.isPro) {
            ScriptManager.nativePlayerSetExperience(exp);
        }
    }

    public static void setFlying(boolean isFlying) {
        if (!Mod.isPro) {
            ScriptManager.nativePlayerSetFlying(isFlying);
        }
    }

    public static void setHealth(int health) {
        if (!Mod.isPro) {
            ScriptManager.nativeSetMobHealth(ScriptManager.nativeGetPlayerEnt(), health);
        }
    }

    public static void setHunger(float hunger) {
        if (!Mod.isPro) {
            ScriptManager.nativePlayerSetHunger(getEntity(), hunger);
        }
    }

    public static void setInventorySlot(int index, int itemId, int count,
                                        int itemDamage) {
        if (!Mod.isPro) {
            ScriptManager.nativeSetInventorySlot(index, itemId, count, itemDamage);
        }
    }

    public static void setItemCustomName(int itemId, String name) {
        if (!Mod.isPro) {
            ScriptManager.nativePlayerSetItemCustomName(itemId, name);
        }
    }

    public static void setLevel(int level) {
        if (!Mod.isPro) {
            ScriptManager.nativePlayerSetLevel(level);
        }
    }


    public static void setSaturation(float saturation) {
        if (!Mod.isPro) {
            ScriptManager.nativePlayerSetSaturation(saturation);
        }
    }

    public static void setSelectedSlotId(int index) {
        if (!Mod.isPro) {
            ScriptManager.nativeSetSelectedSlotId(index);
        }
    }

}
