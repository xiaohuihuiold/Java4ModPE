package com.xhh.modpe.library.api;

import android.util.Log;

import com.xhh.modpe.library.Mod;

import net.zhuoweizhang.mcpelauncher.ScriptManager;
import net.zhuoweizhang.mcpelauncher.api.modpe.ParticleType;

public class Level {

    private final static String TAG = "Level";

    public static void addParticle(Object type, float x, float y, float z, float xs, float ys, float zs, int size) {
        if (!Mod.isPro) {
            type = ParticleType.getTypeFromRaw(type);
            if (!ParticleType.checkValid((String) type, size)) {
                return;
            }
            ScriptManager.nativeLevelAddParticle((String) type, x, y, z, xs, ys, zs, size);
        }
    }

    public static String biomeIdToName(int biomeId) {
        if (!Mod.isPro) {
            return ScriptManager.nativeBiomeIdToName(biomeId);
        }
        return null;
    }

    public static boolean canSeeSky(int x, int y, int z) {
        if (!Mod.isPro) {
            return ScriptManager.nativeLevelCanSeeSky(x, y, z);
        }
        return false;
    }

    public static void destroyBlock(int x, int y, int z, boolean isFall) {
        if (!Mod.isPro) {
            int i;
            int j;
            do {
                i = getTile(x, y, z);
                j = getData(x, y, z);
                ScriptManager.nativeDestroyBlock(z, y, z);
            } while (!isFall);
            dropItem(z + 0.5f, y, z + 0.5f, 1.0f, i, 1, j);
        }
    }

    public static long dropItem(float x, float y, float z, float size, int itemId, int count, int demage) {
        if (!Mod.isPro) {
            if (!ScriptManager.nativeIsValidItem(itemId)) {
                Log.e(TAG, "无效的ID:" + itemId);
                return -1;
            }
            return ScriptManager.nativeDropItem(x, y, z, size, itemId, count, demage);
        }
        return -1;
    }

    public static void executeCommand(final String paramString, final boolean paramBoolean) {
        if (!Mod.isPro) {
            ScriptManager.runOnMainThread(new Runnable() {
                @Override
                public void run() {
                    ScriptManager.nativeLevelExecuteCommand(paramString, paramBoolean);
                }
            });
        }
    }

    public static void explode(float x, float y, float z, float r, boolean isFire) {
        if (!Mod.isPro) {
            ScriptManager.nativeExplode(x, y, z, r, isFire, true, Float.POSITIVE_INFINITY);
        }
    }

    public static int getBiome(int x, int z) {
        if (!Mod.isPro) {
            return ScriptManager.nativeLevelGetBiome(x, z);
        }
        return -1;
    }

    public static int getBiomeName(int x, int z) {
        if (!Mod.isPro) {
            ScriptManager.nativeLevelGetBiomeName(x, z);
        }
        return -1;
    }

    public static int getBrightness(int x, int y, int z) {
        if (!Mod.isPro) {
            return ScriptManager.nativeGetBrightness(x, y, z);
        }
        return -1;
    }

    public static int getChestSlot(int x, int y, int z, int index) {
        if (!Mod.isPro) {
            return ScriptManager.nativeGetItemChest(x, y, z, index);
        }
        return -1;
    }

    public static int getChestSlotCount(int x, int y, int z, int index) {
        if (!Mod.isPro) {
            return ScriptManager.nativeGetItemCountChest(x, y, z, index);
        }
        return -1;
    }

    public static String getChestSlotCustomName(int x, int y, int z, int index) {
        if (!Mod.isPro) {
            return ScriptManager.nativeGetItemNameChest(x, y, z, index);
        }
        return null;
    }

    public static int getChestSlotData(int x, int y, int z, int index) {
        if (!Mod.isPro) {
            return ScriptManager.nativeGetItemDataChest(x, y, z, index);
        }
        return -1;
    }

    public static int getData(int x, int y, int z) {
        if (!Mod.isPro) {
            return ScriptManager.nativeGetData(x, y, z);
        }
        return -1;
    }

    public static int getDifficulty() {
        if (!Mod.isPro) {
            return ScriptManager.nativeLevelGetDifficulty();
        }
        return -1;
    }

    public static int getFurnaceSlot(int x, int y, int z, int index) {
        if (!Mod.isPro) {
            return ScriptManager.nativeGetItemFurnace(x, y, z, index);
        }
        return -1;
    }

    public static int getFurnaceSlotCount(int x, int y, int z, int index) {
        if (!Mod.isPro) {
            return ScriptManager.nativeGetItemCountFurnace(x, y, z, index);
        }
        return -1;
    }

    public static int getFurnaceSlotData(int x, int y, int z, int index) {
        if (!Mod.isPro) {
            return ScriptManager.nativeGetItemDataFurnace(x, y, z, index);
        }
        return -1;
    }

    public static int getGameMode() {
        if (!Mod.isPro) {
            return ScriptManager.nativeGetGameType();
        }
        return -1;
    }

    public static int getGrassColor(int x, int z) {
        if (!Mod.isPro) {
            return ScriptManager.nativeLevelGetGrassColor(x, z);
        }
        return -1;
    }

    public static double getLightningLevel() {
        if (!Mod.isPro) {
            return ScriptManager.nativeLevelGetLightningLevel();
        }
        return -1;
    }

    public static double getRainLevel() {
        if (!Mod.isPro) {
            return ScriptManager.nativeLevelGetRainLevel();
        }
        return -1;
    }

    public static String getSignText(int x, int y, int z, int line) {
        Object object = null;
        if (!Mod.isPro) {
            object = Mod.execScriptManager(
                    "NativeLevelApi",
                    "getSignText",
                    new Class[]{int.class, int.class, int.class, int.class},
                    new Object[]{x, y, z, line}
            );
        }
        if (object != null && object instanceof String) {
            return (String) object;
        }
        return null;
    }

    public static int getSpawnerEntityType(int x, int y, int z) {
        if (!Mod.isPro) {
            if (getTile(x, y, z) != 52) {
                Log.e(TAG, "此坐标方块不是刷怪笼");
                return -1;
            }
            return ScriptManager.nativeSpawnerGetEntityType(x, y, z);
        }
        return -1;
    }

    public static int getTile(int x, int y, int z) {
        if (!Mod.isPro) {
            return ScriptManager.nativeGetTileWrap(x, y, z);
        }
        return -1;
    }

    public static long getTime() {
        if (!Mod.isPro) {
            return ScriptManager.nativeGetTime();
        }
        return -1;
    }

    public static String getWorldDir() {
        if (!Mod.isPro) {
            return ScriptManager.worldDir;
        }
        return null;
    }

    public static String getWorldName() {
        if (!Mod.isPro) {
            return ScriptManager.worldName;
        }
        return null;
    }

    public static boolean isRemote() {
        if (!Mod.isPro) {
            return ScriptManager.nativeLevelIsRemote();
        }
        return false;
    }

    public static void playSound(float x, float y, float z, String name, float vol, float tone) {
        if (!Mod.isPro) {
            Mod.execScriptManager(
                    "NativeLevelApi",
                    "playSound",
                    new Class[]{double.class, double.class, double.class, String.class, double.class, double.class},
                    new Object[]{x, y, z, name, vol, tone}
            );
        }
    }

    public static void playSoundEnt(Object entity, String name, float vol, float tone) {
        if (!Mod.isPro) {
            Mod.execScriptManager(
                    "NativeLevelApi",
                    "playSoundEnt",
                    new Class[]{Object.class, String.class, double.class, double.class},
                    new Object[]{entity, name, vol, tone}
            );
        }
    }

    public static void setBlockExtraData(int p1, int p2, int p3, int p4) {
        if (!Mod.isPro) {
            ScriptManager.nativeLevelSetExtraData(p1, p2, p3, p4);
        }
    }

    public static void setChestSlot(int x, int y, int z, int grid, int itemId, int itemDamage, int count) {
        if (!Mod.isPro) {
            if (!ScriptManager.nativeIsValidItem(itemId)) {
                Log.e(TAG, "没有找到ID:" + itemId);
                return;
            }
            ScriptManager.nativeAddItemChest(x, y, z, grid, itemId, itemDamage, count);
        }
    }

    public static void setChestSlotCustomName(int x, int y, int z, int grid, String name) {
        if (!Mod.isPro) {
            ScriptManager.nativeSetItemNameChest(x, y, z, grid, name);
        }
    }

    public static void setDifficulty(int difficulty) {
        if (!Mod.isPro) {
            ScriptManager.nativeLevelSetDifficulty(difficulty);
        }
    }

    public static void setFurnaceSlot(int x, int y, int z, int grid, int id, int data, int count) {
        if (!Mod.isPro) {
            if (!ScriptManager.nativeIsValidItem(id)) {
                Log.e(TAG, "没有找到ID:" + id);
                return;
            }
            ScriptManager.nativeAddItemFurnace(x, y, z, grid, id, data, count);
        }
    }

    public static void setGameMode(int mode) {
        if (!Mod.isPro) {
            ScriptManager.nativeSetGameType(mode);
        }
    }

    public static void setGrassColor(int r, int g, int b) {
        if (!Mod.isPro) {
            ScriptManager.nativeLevelSetGrassColor(r, g, b);
        }
    }

    public static void setLightningLevel(float level) {
        if (!Mod.isPro) {
            ScriptManager.nativeLevelSetLightningLevel(level);
        }
    }

    public static void setNightMode(boolean mode) {
        if (!Mod.isPro) {
            ScriptManager.nativeSetNightMode(mode);
        }
    }

    public static void setRainLevel(float level) {
        if (!Mod.isPro) {
            ScriptManager.nativeLevelSetRainLevel(level);
        }
    }

    public static void setSignText(int x, int y, int z, int line, String text) {
        if (!Mod.isPro) {
            Mod.execScriptManager(
                    "NativeLevelApi",
                    "setSignText",
                    new Class[]{int.class, int.class, int.class, int.class, String.class},
                    new Object[]{x, y, z, line, text}
            );
        }
    }

    public static void setSpawn(int x, int y, int z) {
        if (!Mod.isPro) {
            ScriptManager.nativeSetSpawn(x, y, z);
        }
    }

    public static void setSpawnerEntityType(int x, int y, int z, int id) {
        if (!Mod.isPro) {
            if (getTile(x, y, z) != 52) {
                Log.e(TAG, "没有找到ID:" + id);
                return;
            }
            ScriptManager.nativeSpawnerSetEntityType(x, y, z, id);
        }
    }

    public static void setTile(int x, int y, int z, int blockId, int damage) {
        if (!Mod.isPro) {
            if (blockId >= 256) {
                ScriptManager.nativeSetTile(x, y, z, 0, 0);
                ScriptManager.nativeSetTile(x, y, z, 245, damage);
                ScriptManager.nativeLevelSetExtraData(x, y, z, blockId);
                return;
            }
            ScriptManager.nativeSetTile(x, y, z, blockId, damage);
        }
    }

    public static void setTime(int time) {
        if (!Mod.isPro) {
            ScriptManager.nativeSetTime(time);
        }
    }

    public static long spawnChicken(float x, float y, float z, String skin) {
        return ModPE.spawnChicken(x, y, z, skin);
    }

    public static long spawnCow(float x, float y, float z, String skin) {
        return ModPE.spawnCow(x, y, z, skin);
    }

    public static long spawnMob(float x, float y, float z, int entityId, String skin) {
        return Entity.spawnMob(x, y, z, entityId, skin);
    }

}
