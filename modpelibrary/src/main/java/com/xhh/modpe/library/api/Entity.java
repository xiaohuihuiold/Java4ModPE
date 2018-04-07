package com.xhh.modpe.library.api;

import android.util.Log;

import com.xhh.modpe.library.Mod;

import net.zhuoweizhang.mcpelauncher.ScriptManager;
import net.zhuoweizhang.mcpelauncher.api.modpe.EntityRenderType;
import net.zhuoweizhang.mcpelauncher.api.modpe.MobEffect;
import net.zhuoweizhang.mcpelauncher.api.modpe.OldEntityTextureFilenameMapping;
import net.zhuoweizhang.mcpelauncher.api.modpe.RendererManager;

import org.mozilla.javascript.NativeJavaObject;

public class Entity {
    private final static String TAG = "Entity";

    public static void addEffect(Object entity, int id, int time, int level, boolean isShadow, boolean isParticle) {
        if (!Mod.isPro) {
            long l = Mod.getEntityId(entity);
            int i = ScriptManager.nativeGetEntityTypeId(l);
            if ((i <= 0) || (i >= 64)) {
                Log.e(TAG, "效果只能在生物上使用");
                return;
            }
            if (MobEffect.effectIds.get(id) == null) {
                Log.e(TAG, "错误的效果:" + id);
                return;
            }
            ScriptManager.nativeMobAddEffect(l, id, time, level, isShadow, isParticle);
        }
    }

    public static long[] getAll() {
        if (!Mod.isPro) {
            long[] arrayOfLong = new long[ScriptManager.allentities.size()];
            int i = 0;
            while (arrayOfLong.length > i) {
                arrayOfLong[i] = ScriptManager.allentities.get(i);
                i += 1;
            }
            return arrayOfLong;
        }
        return new long[0];
    }

    public static int getAnimalAge(Object entity) {
        getEntityTypeId(entity);
        if (!Mod.isPro) {
            return ScriptManager.nativeGetAnimalAge(Mod.getEntityId(entity));
        }
        return -1;
    }

    public static int getArmor(Object entity, int index) {
        if (!Mod.isPro) {
            if ((index < 0) || (index >= 4)) {
                Log.e(TAG, index + "不是盔甲");
                return -1;
            }
            long l = Mod.getEntityId(entity);
            ScriptManager.nativeGetEntityTypeId(l);
            return ScriptManager.nativeMobGetArmor(l, index, 0);
        }
        return -1;
    }

    public static String getArmorCustomName(Object entity, int index) {
        if (!Mod.isPro) {
            if ((index < 0) || (index >= 4)) {
                Log.e(TAG, index + "不是盔甲");
                return null;
            }
            long l = Mod.getEntityId(entity);
            int i = ScriptManager.nativeGetEntityTypeId(l);
            if ((i <= 0) || (i >= 64)) {
                Log.e(TAG, index + "只能在生物上使用");
                return null;
            }
            return ScriptManager.nativeMobGetArmorCustomName(l, index);
        }
        return null;
    }

    public static int getArmorDamage(Object entity, int index) {
        if (!Mod.isPro) {
            if ((index < 0) || (index >= 4)) {
                Log.e(TAG, index + "不是盔甲");
                return -1;
            }
            long l = Mod.getEntityId(entity);
            ScriptManager.nativeGetEntityTypeId(l);
            return ScriptManager.nativeMobGetArmor(l, index, 1);
        }
        return -1;
    }

    public static int getCarriedItem(Object entity) {
        if (!Mod.isPro) {
            return ScriptManager.nativeEntityGetCarriedItem(Mod.getEntityId(entity), 0);
        }
        return -1;
    }

    public static int getCarriedItemCount(Object entity) {
        if (!Mod.isPro) {
            return ScriptManager.nativeEntityGetCarriedItem(Mod.getEntityId(entity), 2);
        }
        return -1;
    }

    public static int getCarriedItemData(Object entity) {
        if (!Mod.isPro) {
            return ScriptManager.nativeEntityGetCarriedItem(Mod.getEntityId(entity), 1);
        }
        return -1;
    }

    public static int getEntityTypeId(Object entity) {
        if (!Mod.isPro) {
            return ScriptManager.nativeGetEntityTypeId(Mod.getEntityId(entity));
        }
        return -1;
    }

    public static String getExtraData(Object entity, String name) {
        Object object = null;
        if (!Mod.isPro) {
            object = Mod.execScriptManager(
                    "NativeEntityApi",
                    "getExtraData",
                    new Class[]{Object.class, String.class},
                    new Object[]{entity, name}
            );
        }
        if (object != null && object instanceof String) {
            return (String) object;
        }
        return null;
    }

    public static int getHealth(Object entity) {
        int i = getEntityTypeId(entity);
        if ((i < 10) || (i >= 64)) {
            return 0;
        }
        if (!Mod.isPro) {
            return ScriptManager.nativeGetMobHealth(Mod.getEntityId(entity));
        }
        return -1;
    }

    public static int getItemEntityCount(Object entity) {
        long l = Mod.getEntityId(entity);
        if (!Mod.isPro) {
            if (ScriptManager.nativeGetEntityTypeId(l) != 64) {
                Log.e(TAG, "只能是掉落物品");
                return -1;
            }
            return ScriptManager.nativeGetItemEntityItem(l, 2);
        }
        return -1;
    }

    public static int getItemEntityData(Object entity) {
        long l = Mod.getEntityId(entity);
        if (!Mod.isPro) {
            if (ScriptManager.nativeGetEntityTypeId(l) != 64) {
                Log.e(TAG, "只能是掉落物品");
                return -1;
            }
            return ScriptManager.nativeGetItemEntityItem(l, 1);
        }
        return -1;
    }

    public static int getItemEntityId(Object entity) {
        long l = Mod.getEntityId(entity);
        if (!Mod.isPro) {
            int i = ScriptManager.nativeGetEntityTypeId(l);
            if (i != 64) {
                Log.e(TAG, "只能是掉落物品");
                return -1;
            }
            return ScriptManager.nativeGetItemEntityItem(l, 0);
        }
        return -1;
    }

    public static int getMaxHealth(Object entity) {
        if (!Mod.isPro) {
            return ScriptManager.nativeGetMobMaxHealth(Mod.getEntityId(entity));
        }
        return -1;
    }

    public static String getMobSkin(Object entity) {
        long l = Mod.getEntityId(entity);
        int i = getEntityTypeId(l);
        if ((i <= 0) || (i >= 64)) {
            return "";
        }
        if (!Mod.isPro) {
            return ScriptManager.nativeEntityGetMobSkin(l);
        }
        return "";
    }

    public static String getNameTag(Object entity) {
        if (!Mod.isPro) {
            return ScriptManager.nativeEntityGetNameTag(Mod.getEntityId(entity));
        }
        return null;
    }

    public static int getOffhandSlot(Object entity) {
        long l = Mod.getEntityId(entity);
        if (!Mod.isPro) {
            ScriptManager.ensureEntityIsMob(l);
            return ScriptManager.nativeEntityGetOffhandSlot(l, 0);
        }
        return -1;
    }

    public static int getOffhandSlotCount(Object entity) {
        long l = Mod.getEntityId(entity);
        if (!Mod.isPro) {
            ScriptManager.ensureEntityIsMob(l);
            return ScriptManager.nativeEntityGetOffhandSlot(l, 2);
        }
        return -1;
    }

    public static int getOffhandSlotData(Object entity) {
        long l = Mod.getEntityId(entity);
        if (!Mod.isPro) {
            ScriptManager.ensureEntityIsMob(l);
            return ScriptManager.nativeEntityGetOffhandSlot(l, 1);
        }
        return -1;
    }

    public static double getPitch(Object entity) {
        if (!Mod.isPro) {
            return ScriptManager.nativeGetPitch(Mod.getEntityId(entity));
        }
        return -1;
    }

    public static int getRenderType(Object entity) {
        if (!Mod.isPro) {
            return ScriptManager.nativeEntityGetRenderType(Mod.getEntityId(entity));
        }
        return -1;
    }

    public static int getRider(Object entity) {
        if (!Mod.isPro) {
            return ScriptManager.nativeEntityGetRider(Mod.getEntityId(entity));
        }
        return -1;
    }

    public static int getRiding(Object entity) {
        if (!Mod.isPro) {
            return ScriptManager.nativeEntityGetRiding(Mod.getEntityId(entity));
        }
        return -1;
    }

    public static long getTarget(Object entity) {
        if (!Mod.isPro) {
            long l = Mod.getEntityId(entity);
            int i = ScriptManager.nativeGetEntityTypeId(l);
            if ((i <= 0) || (i >= 64)) {
                Log.e(TAG, "只能获取生物");
                return -1;
            }
            return ScriptManager.nativeEntityGetTarget(l);
        }
        return -1;
    }

    public static String getUniqueId(Object entity) {
        Object object = null;
        if (!Mod.isPro) {
            object = Mod.execScriptManager(
                    "NativeEntityApi",
                    "getUniqueId",
                    new Class[]{Object.class},
                    new Object[]{entity}
            );
        }
        if (object != null && object instanceof String) {
            return (String) object;
        }
        return null;
    }

    public static double getVelX(Object entity) {
        if (!Mod.isPro) {
            return ScriptManager.nativeGetEntityVel(Mod.getEntityId(entity), 0);
        }
        return -1;
    }

    public static double getVelY(Object entity) {
        if (!Mod.isPro) {
            return ScriptManager.nativeGetEntityVel(Mod.getEntityId(entity), 1);
        }
        return -1;
    }

    public static double getVelZ(Object entity) {
        if (!Mod.isPro) {
            return ScriptManager.nativeGetEntityVel(Mod.getEntityId(entity), 2);
        }
        return -1;
    }

    public static double getX(Object entity) {
        if (!Mod.isPro) {
            return ScriptManager.nativeGetEntityLoc(Mod.getEntityId(entity), 0);
        }
        return -1;
    }

    public static double getY(Object entity) {
        if (!Mod.isPro) {
            return ScriptManager.nativeGetEntityLoc(Mod.getEntityId(entity), 1);
        }
        return -1;
    }

    public static double getYaw(Object entity) {
        if (!Mod.isPro) {
            return ScriptManager.nativeGetYaw(Mod.getEntityId(entity));
        }
        return -1;
    }

    public static double getZ(Object entity) {
        if (!Mod.isPro) {
            return ScriptManager.nativeGetEntityLoc(Mod.getEntityId(entity), 2);
        }
        return -1;
    }

    public static boolean isSneaking(Object entity) {
        if (!Mod.isPro) {
            return ScriptManager.nativeIsSneaking(Mod.getEntityId(entity));
        }
        return false;
    }

    public static void remove(Object entity) {
        if (!Mod.isPro) {
            ScriptManager.nativeRemoveEntity(Mod.getEntityId(entity));
        }
    }

    public static void removeAllEffects(Object entity) {
        long l = Mod.getEntityId(entity);
        if (!Mod.isPro) {
            int i = ScriptManager.nativeGetEntityTypeId(l);
            if ((i <= 0) || (i >= 64)) {
                Log.e(TAG, "只能在生物使用");
                return;
            }
            ScriptManager.nativeMobRemoveAllEffects(l);
        }
    }

    public static void removeEffect(Object entity, int id) {
        long l = Mod.getEntityId(entity);
        if (!Mod.isPro) {
            int i = ScriptManager.nativeGetEntityTypeId(l);
            if ((i <= 0) || (i >= 64)) {
                Log.e(TAG, "只能在生物使用");
                return;
            }
            if (MobEffect.effectIds.get(id) == null) {
                Log.e(TAG, "错误的效果ID:" + id);
                return;
            }
            ScriptManager.nativeMobRemoveEffect(l, id);
        }
    }

    public static void rideAnimal(Object entity, Object oentity) {
        if (!Mod.isPro) {
            ScriptManager.nativeRideAnimal(Mod.getEntityId(entity), Mod.getEntityId(oentity));
        }
    }

    public static void setAnimalAge(Object entity, int age) {
        getEntityTypeId(entity);
        if (!Mod.isPro) {
            ScriptManager.nativeSetAnimalAge(Mod.getEntityId(entity), age);
        }
    }

    public static void setArmor(Object entity, int index, int id, int max) {
        if ((index < 0) || (index >= 4)) {
            Log.e(TAG, "无效的盔甲");
            return;
        }
        long l = Mod.getEntityId(entity);
        if (!Mod.isPro) {
            int i = ScriptManager.nativeGetEntityTypeId(l);
            if ((i <= 0) || (i >= 64)) {
                Log.e(TAG, "只能设置在生物上");
                return;
            }
            ScriptManager.nativeMobSetArmor(l, index, id, max);
        }
    }

    public static void setArmorCustomName(Object entity, int index, String name) {
        if ((index < 0) || (index >= 4)) {
            Log.e(TAG, "无效的盔甲");
            return;
        }
        long l = Mod.getEntityId(entity);
        if (!Mod.isPro) {
            int i = ScriptManager.nativeGetEntityTypeId(l);
            if ((i <= 0) || (i >= 64)) {
                Log.e(TAG, "只能设置在生物上");
                return;
            }
            ScriptManager.nativeMobSetArmorCustomName(l, index, name);
        }
    }

    public static void setCape(Object entity, String path) {
        if (!Mod.isPro) {
            int i = ScriptManager.nativeGetEntityTypeId(Mod.getEntityId(entity));
            if ((i < 32) || (i >= 64)) {
                Log.e(TAG, "只能设置在人形生物上");
                return;
            }
            ScriptManager.nativeSetCape(Mod.getEntityId(entity), path);
        }
    }

    public static void setCarriedItem(Object entity, int id, int count, int damage) {
        if (!Mod.isPro) {
            if (!ScriptManager.nativeIsValidItem(id)) {
                Log.e(TAG, "无效的ID:" + id);
                return;
            }
            ScriptManager.nativeSetCarriedItem(Mod.getEntityId(entity), id, count, damage);
        }
    }

    public static void setCollisionSize(Object entity, float d, float h) {
        if (!Mod.isPro) {
            ScriptManager.nativeEntitySetSize(Mod.getEntityId(entity), d, h);
        }
    }

    public static boolean setExtraData(Object entity, String name, String value) {
        Object object = null;
        if (!Mod.isPro) {
            object = Mod.execScriptManager(
                    "NativeEntityApi",
                    "setExtraData",
                    new Class[]{Object.class, String.class, String.class},
                    new Object[]{entity, name, value}
            );
        }
        if (object != null && object instanceof Boolean) {
            return (boolean) object;
        }
        return false;
    }

    public static void setFireTicks(Object entity, int time) {
        if (!Mod.isPro) {
            ScriptManager.nativeSetOnFire(Mod.getEntityId(entity), time);
        }
    }

    public static void setHealth(Object entity, int health) {
        int i = getEntityTypeId(entity);
        if ((i < 10) || (i >= 64)) {
            return;
        }
        if (!Mod.isPro) {
            ScriptManager.nativeSetMobHealth(Mod.getEntityId(entity), health);
        }
    }

    public static void setImmobile(Object entity, boolean isImmobile) {
        setImmobileImpl(entity, isImmobile);
        for (String str = "1"; ; str = "0") {
            setExtraData(entity, "zhuowei.bl.im", str);
            return;
        }
    }

    public static void setImmobileImpl(Object entity, boolean isImmobile) {
        if (!Mod.isPro) {
            ScriptManager.nativeEntitySetImmobile(Mod.getEntityId(entity), isImmobile);
        }
    }

    public static void setMaxHealth(Object entity, int health) {
        int i = getEntityTypeId(entity);
        if ((i < 10) || (i >= 64)) {
            Log.e(TAG, "setMaxHealth called on non-mob: entityType=" + i);
            return;
        }
        if (!Mod.isPro) {
            ScriptManager.nativeSetMobMaxHealth(Mod.getEntityId(entity), health);
        }
    }

    public static void setMobSkin(Object entity, String path) {
        setMobSkinImpl(entity, path, true);
    }

    public static void setMobSkinImpl(Object entity, String path, boolean paramBoolean) {
        if (!Mod.isPro) {
            String str = (String) OldEntityTextureFilenameMapping.m.get(path);
            if (str != null) {
                path = str;
            }
            if (!path.endsWith(".png")) {
                str = path;
                if (!path.endsWith(".tga")) {
                }
            } else {
                str = path.substring(0, path.length() - 4);
            }
            ScriptManager.nativeSetMobSkin(Mod.getEntityId(entity), str);
            if (paramBoolean) {
                setExtraData(entity, "zhuowei.bl.s", str);
            }
        }
    }

    public static void setNameTag(Object entity, String name) {
        if (!Mod.isPro) {
            if (ScriptManager.nativeGetEntityTypeId(Mod.getEntityId(entity)) >= 64) {
                Log.e(TAG, "只能设置在生物");
                return;
            }
            ScriptManager.nativeEntitySetNameTag(Mod.getEntityId(entity), name);
        }
    }

    public static void setOffhandSlot(Object entity, int itemId, int p2, int p3) {
        if (!Mod.isPro) {
            if (!ScriptManager.nativeIsValidItem(itemId)) {
                Log.e(TAG, "无效的物品ID:" + itemId);
                return;
            }
            long l = Mod.getEntityId(entity);
            ScriptManager.ensureEntityIsMob(l);
            ScriptManager.nativeEntitySetOffhandSlot(l, itemId, p2, p3);
        }
    }

    public static void setPosition(Object entity, float x, float y, float z) {
        if (!Mod.isPro) {
            ScriptManager.nativeSetPosition(Mod.getEntityId(entity), x, y, z);
        }
    }

    public static void setPositionRelative(Object entity, float x, float y, float z) {
        if (!Mod.isPro) {
            ScriptManager.nativeSetPositionRelative(Mod.getEntityId(entity), x, y, z);
        }
    }

    public static void setRenderType(Object entity, Object id) {
        Mod.execScriptManager(
                "NativeEntityApi",
                "setRenderType",
                new Class[]{Object.class, Object.class},
                new Object[]{entity, id}
        );
    }

    public static void setRot(Object entity, float x, float y) {
        if (!Mod.isPro) {
            ScriptManager.nativeSetRot(Mod.getEntityId(entity), x, y);
        }
    }

    public static void setSneaking(Object entity, boolean isSneaking) {
        if (!Mod.isPro) {
            ScriptManager.nativeSetSneaking(Mod.getEntityId(entity), isSneaking);
        }
    }

    public static void setTarget(Object entity, Object oentity) {
        long l2 = Mod.getEntityId(entity);
        if (!Mod.isPro) {
            int i = ScriptManager.nativeGetEntityTypeId(l2);
            if ((i <= 0) || (i >= 64)) {
                Log.e(TAG, "只能设置在生物");
                return;
            }
            if (oentity == null) {
            }
            for (long l1 = -1L; l1 != -1L; l1 = Mod.getEntityId(oentity)) {
                i = ScriptManager.nativeGetEntityTypeId(l1);
                if ((i > 0) && (i < 64)) {
                    break;
                }
                Log.e(TAG, "只能设置在生物");
                return;
            }
            ScriptManager.nativeEntitySetTarget(l2, i);
        }
    }

    public static void setVelX(Object entity, float speed) {
        if (!Mod.isPro) {
            ScriptManager.nativeSetVel(Mod.getEntityId(entity), speed, 0);
        }
    }

    public static void setVelY(Object entity, float speed) {
        if (!Mod.isPro) {
            ScriptManager.nativeSetVel(Mod.getEntityId(entity), speed, 1);
        }
    }

    public static void setVelZ(Object entity, float speed) {
        if (!Mod.isPro) {
            ScriptManager.nativeSetVel(Mod.getEntityId(entity), speed, 2);
        }
    }

    public static long spawnMob(float x, float y, float z, int id, String skin) {
        Object object = null;
        if (!Mod.isPro) {
            object = Mod.execScriptManager(
                    "NativeEntityApi",
                    "spawnMob",
                    new Class[]{double.class, double.class, double.class, int.class, String.class},
                    new Object[]{x, y, z, id, skin}
            );
        }
        if (object != null && object instanceof Long) {
            return (long) object;
        }
        return -1;
    }

}
