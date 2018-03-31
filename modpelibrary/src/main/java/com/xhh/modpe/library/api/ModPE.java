package com.xhh.modpe.library.api;

import android.util.Log;

import com.xhh.modpe.library.Mod;

import net.zhuoweizhang.mcpelauncher.ScriptManager;

import java.io.File;
import java.io.InputStream;

public class ModPE {

    private final static String TAG = "ModPE";

    public static void addItemInventory(int itemId, int itemNumber, int itemDamage) {
        if (!Mod.isPro) {
            if (!ScriptManager.nativeIsValidItem(itemId)) {
                Log.e(TAG, "无效的ID:" + itemId);
                return;
            }
            ScriptManager.nativeAddItemInventory(itemId, itemNumber, itemDamage);
        }
    }

    public static void setMobSkin(long entity, String skinPath) {
        if (!Mod.isPro) {
            ScriptManager.nativeSetMobSkin(entity, skinPath);
        }
    }

    public static void clientMessage(String message) {
        if (!Mod.isPro) {
            ScriptManager.nativeClientMessage(message);
        }
    }

    public static void explode(float x, float y, float z, float r, boolean isFire) {
        if (!Mod.isPro) {
            ScriptManager.nativeExplode(x, y, z, r, isFire, true, Float.POSITIVE_INFINITY);
        }
    }

    public static int getCarriedItem() {
        if (!Mod.isPro) {
            return ScriptManager.nativeGetCarriedItem(0);
        }
        return -1;
    }

    public static long getLevel() {
        if (!Mod.isPro) {
            return ScriptManager.nativeGetLevel();
        }
        return -1;
    }

    public static float getPitch(long enyity) {
        if (!Mod.isPro) {
            return ScriptManager.nativeGetPitch(enyity);
        }
        return -1;
    }

    public static long getPlayerEnt() {
        if (!Mod.isPro) {
            return ScriptManager.nativeGetPlayerEnt();
        }
        return -1;
    }

    public static float getPlayerX() {
        if (!Mod.isPro) {
            return ScriptManager.nativeGetPlayerLoc(0);
        }
        return -1;
    }

    public static float getPlayerY() {
        if (!Mod.isPro) {
            return ScriptManager.nativeGetPlayerLoc(1);
        }
        return -1;
    }

    public static float getPlayerZ() {
        if (!Mod.isPro) {
            return ScriptManager.nativeGetPlayerLoc(2);
        }
        return -1;
    }

    public static int getTile(int x, int y, int z) {
        if (!Mod.isPro) {
            return ScriptManager.nativeGetTileWrap(x, y, z);
        }
        return -1;
    }

    public static float getYaw(long entity) {
        if (!Mod.isPro) {
            return ScriptManager.nativeGetYaw(entity);
        }
        return -1;
    }

    public static void preventDefault() {
        if (!Mod.isPro) {
            ScriptManager.nativePreventDefault();
        }
    }

    public static void rideAnimal(Object entity, Object oentity) {
        if (!Mod.isPro) {
            ScriptManager.nativeRideAnimal(Mod.getEntityId(entity), Mod.getEntityId(oentity));
        }
    }

    public static void setNightMode(boolean isNight) {
        if (!Mod.isPro) {
            ScriptManager.nativeSetNightMode(isNight);
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

    public static void setRot(Object entity, float h, float v) {
        if (!Mod.isPro) {
            ScriptManager.nativeSetRot(Mod.getEntityId(entity), h, v);
        }
    }

    public static void setTile(int x, int y, int z, int blockId, int blockBagame) {
        if (!Mod.isPro) {
            ScriptManager.nativeSetTile(x, y, z, blockId, blockBagame);
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

    public static long spawnChicken(float x, float y, float z, String skin) {
        Object object = null;
        if (!Mod.isPro) {
            object = Mod.execScriptManager(
                    "BlockHostObject",
                    "spawnChicken",
                    new Class[]{double.class, double.class, double.class, String.class},
                    new Object[]{x, y, z, skin}
            );
        }
        if (object != null && object instanceof Long) {
            return (long) object;
        }
        return -1;
    }

    public static long spawnCow(float x, float y, float z, String skin) {
        Object object = null;
        if (!Mod.isPro) {
            object = Mod.execScriptManager(
                    "BlockHostObject",
                    "spawnCow",
                    new Class[]{double.class, double.class, double.class, String.class},
                    new Object[]{x, y, z, skin}
            );
        }
        if (object != null && object instanceof Long) {
            return (long) object;
        }
        return -1;
    }

    public static long spawnPigZombie(float x, float y, float z, int id, String skin) {
        Object object = null;
        if (!Mod.isPro) {
            object = Mod.execScriptManager(
                    "BlockHostObject",
                    "spawnPigZombie",
                    new Class[]{double.class, double.class, double.class, int.class, String.class},
                    new Object[]{x, y, z, id, skin}
            );
        }
        if (object != null && object instanceof Long) {
            return (long) object;
        }
        return -1;
    }

    public static byte[] getBytesFromTexturePack(String path) {
        Object object = null;
        if (!Mod.isPro) {
            object = Mod.execScriptManager(
                    "NativeModPEApi",
                    "getBytesFromTexturePack",
                    new Class[]{String.class},
                    new Object[]{path}
            );
        }
        if (object != null && object instanceof byte[]) {
            return (byte[]) object;
        }
        return null;
    }

    public static String getI18n(String lang) {
        if (!Mod.isPro) {
            return ScriptManager.nativeGetI18NString(lang);
        }
        return null;
    }

    public static String getLanguage() {
        if (!Mod.isPro) {
            ScriptManager.nativeGetLanguageName();
        }
        return null;
    }

    public static String getMinecraftVersion() {
        if (!Mod.isPro) {
            try {
                String str = ScriptManager.androidContext.getPackageManager().getPackageInfo("com.mojang.minecraftpe", 0).versionName;
                return str;
            } catch (Exception localException) {
                localException.printStackTrace();
            }
            return null;
        }
        return null;
    }

    public static String getLevelName(File file) {
        Object object = null;
        if (!Mod.isPro) {
            object = Mod.execScriptManager(
                    "NativeModPEApi",
                    "getLevelName",
                    new Class[]{File.class},
                    new Object[]{file}
            );
        }
        if (object != null && object instanceof String) {
            return (String) object;
        }
        return null;
    }

    public static void langEdit(String start, String str) {
        if (!Mod.isPro) {
            ScriptManager.nativeSetI18NString(start, str);
        }
    }

    public static void leaveGame() {
        if (!Mod.isPro) {
            Mod.execScriptManager("NativeModPEApi", "leaveGame", new Class[]{}, new Object[]{});
        }
    }

    public static InputStream openInputStreamFromTexturePack(String path) {
        Object object = null;
        if (!Mod.isPro) {
            object = Mod.execScriptManager(
                    "NativeModPEApi",
                    "openInputStreamFromTexturePack",
                    new Class[]{String.class},
                    new Object[]{path}
            );
        }
        if (object != null && object instanceof InputStream) {
            return (InputStream) object;
        }
        return null;
    }

    public static void overrideTexture(String old, String neww) {
        if (!Mod.isPro) {
            Mod.execScriptManager("NativeModPEApi", "overrideTexture", new Class[]{String.class, String.class}, new Object[]{old, neww});
        }
    }

    public static String readData(String key) {
        Object object = null;
        if (!Mod.isPro) {
            object = Mod.execScriptManager(
                    "NativeModPEApi",
                    "readData",
                    new Class[]{String.class},
                    new Object[]{key}
            );
        }
        if (object != null && object instanceof String) {
            return (String) object;
        }
        return null;
    }

    public static void removeData(String key) {
        if (!Mod.isPro) {
            Mod.execScriptManager("NativeModPEApi", "removeData", new Class[]{String.class}, new Object[]{key});
        }
    }

    public static void resetFov() {
        if (!Mod.isPro) {
            ScriptManager.nativeSetFov(0, false);
        }
    }

    public static void resetImages() {
        if (!Mod.isPro) {
            Mod.execScriptManager("NativeModPEApi", "resetImages", new Class[]{}, new Object[]{});
        }
    }

    public static void saveData(String key, String value) {
        if (!Mod.isPro) {
            Mod.execScriptManager("NativeModPEApi", "saveData", new Class[]{String.class, String.class}, new Object[]{key, value});
        }
    }

    public static void selectLevel(String path) {
        if (!Mod.isPro) {
            Mod.execScriptManager("NativeModPEApi", "selectLevel", new Class[]{String.class}, new Object[]{path});
        }
    }

    public static void setCamera(long entity) {
        if (!Mod.isPro) {
            ScriptManager.nativeSetCameraEntity(entity);
        }
    }

    public static void setFoodItem(int itemId, String type, int order, int hungry, String name, int max) {
        if (!Mod.isPro) {
            Mod.execScriptManager(
                    "NativeModPEApi",
                    "selectLevel",
                    new Class[]{int.class, String.class, int.class, int.class, String.class, int.class},
                    new Object[]{itemId, type, order, hungry, name, max}
            );
        }
    }

    public static void setFov(float fov) {
        if (!Mod.isPro) {
            ScriptManager.nativeSetFov(fov, true);
        }
    }

    public static void setGameSpeed(float speed) {
        if (!Mod.isPro) {
            ScriptManager.nativeSetGameSpeed(speed);
        }
    }

    public static void setGuiBlocks(String path) {
        overrideTexture("gui/gui_blocks.png", path);
    }

    public static void setItem(int itemId, String mat, int order, String name, int max) {
        if (!Mod.isPro) {
            Mod.execScriptManager(
                    "NativeModPEApi",
                    "setItem",
                    new Class[]{int.class, String.class, int.class, String.class, int.class},
                    new Object[]{itemId, mat, order, name, max}
            );
        }
    }

    public static void setItems(String path) {
        overrideTexture("images/items-opaque.png", path);
    }

    public static void setTerrain(String path) {
        overrideTexture("images/terrain-atlas.tga", path);
    }

    public static void setUiRenderDebug(boolean isDebug) {
        if (!Mod.isPro) {
            ScriptManager.nativeModPESetRenderDebug(isDebug);
        }
    }

    public static void showTipMessage(String message) {
        if (!Mod.isPro) {
            ScriptManager.nativeShowTipMessage(message);
        }
    }

    public static void takeScreenshot(String path) {
        if (!Mod.isPro) {
            ScriptManager.takeScreenshot(path);
        }
    }

}
