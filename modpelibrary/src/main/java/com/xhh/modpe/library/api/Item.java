package com.xhh.modpe.library.api;

import android.util.Log;

import com.xhh.modpe.library.Mod;

import net.zhuoweizhang.mcpelauncher.ScriptManager;

import org.mozilla.javascript.NativeJSON;
import org.mozilla.javascript.ScriptRuntime;
import org.mozilla.javascript.Scriptable;

public class Item {
    public static final String TAG = "Item";

    public static void addCraftRecipe(int outItemId, int outCount, int outDamage, Scriptable formula) {
        Mod.execScriptManager(
                "NativeItemApi",
                "addCraftRecipe",
                new Class[]{int.class, int.class, int.class, Scriptable.class},
                new Object[]{outItemId, outCount, outDamage, formula}
        );
    }

    public static void addFurnaceRecipe(int itemId, int outItemId, int outDamage) {
        Mod.execScriptManager(
                "NativeItemApi",
                "addFurnaceRecipe",
                new Class[]{int.class, int.class, int.class},
                new Object[]{itemId, outItemId, outDamage}
        );
    }

    public static void addShapedRecipe(int outItemId, int outCount, int outDamage, Scriptable table, Scriptable tableDefine) {
        Mod.execScriptManager(
                "NativeItemApi",
                "addShapedRecipe",
                new Class[]{int.class, int.class, int.class, Scriptable.class, Scriptable.class},
                new Object[]{outItemId, outCount, outDamage, table, tableDefine}
        );
    }

    public static void defineArmor(int armorId, String matMeta, int offset, String name, String matPath, int def, int max, int type) {
        ModPE.setItem(armorId, matMeta, offset, name, 1);
    }

    public static void defineThrowable(int itemId, String mat, int order, String name, int max) {
        Mod.execScriptManager(
                "NativeItemApi",
                "defineThrowable",
                new Class[]{int.class, String.class, int.class, String.class, int.class},
                new Object[]{itemId, mat, order, name, max}
        );
    }

    public static int getCustomThrowableRenderType(int itemId) {
        Object object = null;
        object = Mod.execScriptManager(
                "NativeItemApi",
                "getCustomThrowableRenderType",
                new Class[]{int.class},
                new Object[]{itemId}
        );
        if (object != null && object instanceof Integer) {
            return (Integer) object;
        }
        return -1;
    }

    public static int getMaxDamage(int itemId) {
        if (!Mod.isPro) {
            return ScriptManager.nativeGetItemMaxDamage(itemId);
        }
        return -1;
    }


    public static int getMaxStackSize(int itemId) {
        if (!Mod.isPro) {
            return ScriptManager.nativeItemGetMaxStackSize(itemId);
        }
        return -1;
    }

    public static String getName(int itemId, int itemDamage, boolean isInName) {
        if (!Mod.isPro) {
            if (!ScriptManager.nativeIsValidItem(itemId)) {
                Log.e(TAG, "无效的物品ID:" + itemId);
                return null;
            }
            if (itemId == 358) {
                return "Map";
            }
            return ScriptManager.nativeGetItemName(itemId, itemDamage, isInName);
        }
        return null;
    }

    public static int[] getTextureCoords(int itemId, int p2) {
        float[] arrayOfFloat = new float[6];
        if (!Mod.isPro) {
            if (!ScriptManager.nativeGetTextureCoordinatesForItem(itemId, p2, arrayOfFloat)) {
                Log.e(TAG, "不能获取贴图坐标");
                return new int[0];
            }
            return new int[]{(int) (arrayOfFloat[0] * arrayOfFloat[4] + 0.5D), (int) (arrayOfFloat[1] * arrayOfFloat[5] + 0.5D), (int) (arrayOfFloat[2] * arrayOfFloat[4] + 0.5D), (int) (arrayOfFloat[3] * arrayOfFloat[5] + 0.5D), (int) (arrayOfFloat[4] + 0.5D), (int) (arrayOfFloat[5] + 0.5D)};
        }
        return new int[0];
    }

    public static int getUseAnimation(int itemId) {
        if (!Mod.isPro) {
            return ScriptManager.nativeItemGetUseAnimation(itemId);
        }
        return -1;
    }

    public static int internalNameToId(String name) {
        Object object = null;
        object = Mod.execScriptManager(
                "NativeItemApi",
                "internalNameToId",
                new Class[]{String.class},
                new Object[]{name}
        );
        if (object != null && object instanceof Integer) {
            return (Integer) object;
        }
        return -1;
    }

    public static boolean isValidItem(int itemId) {
        if (!Mod.isPro) {
            return ScriptManager.nativeIsValidItem(itemId);
        }
        return false;
    }

    public static void setAllowOffhand(int itemId, boolean p2) {
        if (!Mod.isPro) {
            ScriptManager.nativeItemSetAllowOffhand(itemId, p2);
        }
    }

    public static void setCategory(int itemId, int itemDamage) {
        if (!Mod.isPro) {
            if ((itemDamage < 0) || (itemDamage > 4)) {
                Log.e(TAG, "物品类型不正确");
                return;
            }
            ScriptManager.nativeSetItemCategory(itemId, itemDamage, 0);
        }
    }

    public static void setEnchantType(int itemId, int type, int value) {
        if (!Mod.isPro) {
            ScriptManager.nativeSetAllowEnchantments(itemId, type, value);
        }
    }

    public static void setHandEquipped(int itemId, boolean p2) {
        if (!Mod.isPro) {
            ScriptManager.nativeSetHandEquipped(itemId, p2);
        }
    }

    public static void setMaxDamage(int itemId, int max) {
        if (!Mod.isPro) {
            ScriptManager.nativeSetItemMaxDamage(itemId, max);
        }
    }

    public static void setProperties(int itemId, Object spe) {
        Mod.execScriptManager(
                "NativeItemApi",
                "setProperties",
                new Class[]{int.class, Object.class},
                new Object[]{itemId, spe}
        );
    }

    public static void setStackedByData(int itemId, boolean p2) {
        if (!Mod.isPro) {
            ScriptManager.nativeItemSetStackedByData(itemId, p2);
        }
    }

    public static void setUseAnimation(int itemId, int aniId) {
        if (!Mod.isPro) {
            ScriptManager.nativeItemSetUseAnimation(itemId, aniId);
        }
    }

    public static int translatedNameToId(String name) {
        Object object = null;
        object = Mod.execScriptManager(
                "NativeItemApi",
                "translatedNameToId",
                new Class[]{String.class},
                new Object[]{name}
        );
        if (object != null && object instanceof Integer) {
            return (Integer) object;
        }
        return -1;
    }
}
