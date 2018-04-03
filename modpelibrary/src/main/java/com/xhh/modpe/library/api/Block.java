package com.xhh.modpe.library.api;

import android.util.Log;

import com.xhh.modpe.library.Mod;

import net.zhuoweizhang.mcpelauncher.ScriptManager;

import org.mozilla.javascript.Scriptable;

public class Block {
    public static final String TAG = "Block";

    public static void defineBlock(int blockId, String blockName, Object matName, Object id, Object tran, Object mod) {
        Mod.execScriptManager(
                "NativeBlockApi",
                "defineBlock",
                new Class[]{int.class, String.class, Object.class, Object.class, Object.class, Object.class},
                new Object[]{blockId, blockName, matName, id, tran, mod}
        );
    }

    public static int defineLiquidBlock(int blockId, String name, Object metaName, Object type) {
        Object object = null;
        if (!Mod.isPro) {
            object = Mod.execScriptManager(
                    "NativeBlockApi",
                    "defineLiquidBlock",
                    new Class[]{int.class, String.class, Object.class, Object.class},
                    new Object[]{blockId, name, metaName, type}
            );
        }
        if (object != null && object instanceof Integer) {
            return (Integer) object;
        }
        return -1;
    }

    public static int[] getAllBlockIds() {
        Object object = null;
        if (!Mod.isPro) {
            object = Mod.execScriptManager(
                    "NativeBlockApi",
                    "getAllBlockIds",
                    new Class[]{},
                    new Object[]{}
            );
        }
        if (object != null && object instanceof int[]) {
            return (int[]) object;
        }
        return new int[0];
    }

    public static double getDestroyTime(int blockId, int blockDamage) {
        if (!Mod.isPro) {
            return ScriptManager.nativeBlockGetDestroyTime(blockId, blockDamage);
        }
        return -1;
    }

    public static double getFriction(int blockId, int blockDamage) {
        if (!Mod.isPro) {
            return ScriptManager.nativeBlockGetFriction(blockId);
        }
        return -1;
    }

    public static int getRenderLayer(int p1) {
        if (!Mod.isPro) {
            return ScriptManager.nativeBlockGetRenderLayer(p1);
        }
        return -1;
    }

    public static int getRenderType(int blockId) {
        if (!Mod.isPro) {
            return ScriptManager.nativeGetBlockRenderShape(blockId);
        }
        return -1;
    }

    public static int[] getTextureCoords(int paramInt1, int paramInt2, int paramInt3) {
        float[] arrayOfFloat = new float[6];
        if (!Mod.isPro) {
            if (!ScriptManager.nativeGetTextureCoordinatesForBlock(paramInt1, paramInt2, paramInt3, arrayOfFloat)) {
                Log.e(TAG, "不能获取方块贴图");
                return new int[0];
            }
            return new int[]{(int) (arrayOfFloat[0] * arrayOfFloat[4] + 0.5D), (int) (arrayOfFloat[1] * arrayOfFloat[5] + 0.5D), (int) (arrayOfFloat[2] * arrayOfFloat[4] + 0.5D), (int) (arrayOfFloat[3] * arrayOfFloat[5] + 0.5D), (int) (arrayOfFloat[4] + 0.5D), (int) (arrayOfFloat[5] + 0.5D)};
        }
        return new int[0];
    }

    public static void setColor(int blockId, Scriptable html) {
        if (!Mod.isPro) {
            ScriptManager.nativeBlockSetColor(blockId, ScriptManager.expandColorsArray(html));
        }
    }

    public static void setDestroyTime(int blockId, float time) {
        if (!Mod.isPro) {
            ScriptManager.nativeBlockSetDestroyTime(blockId, time);
        }
    }

    public static void setExplosionResistance(int blockId, float level) {
        if (!Mod.isPro) {
            ScriptManager.nativeBlockSetExplosionResistance(blockId, level);
        }
    }

    public static void setFriction(int blockId, float level) {
        float d = level;
        if (level < 0.1f) {
            d = 0.1f;
        }
        if (!Mod.isPro) {
            ScriptManager.nativeBlockSetFriction(blockId, d);
        }
    }

    public static void setLightLevel(int blockId, int light) {
        if (!Mod.isPro) {
            ScriptManager.nativeBlockSetLightLevel(blockId, light);
        }
    }

    public static void setLightOpacity(int blockId, int alpha) {
        if (!Mod.isPro) {
            ScriptManager.nativeBlockSetLightOpacity(blockId, alpha);
        }
    }

    public static void setRedstoneConsumer(int blockId, boolean isStong) {
        if (!Mod.isPro) {
            ScriptManager.nativeBlockSetRedstoneConsumer(blockId, isStong);
        }
    }

    public static void setRenderLayer(int blockId, int modId) {
        Mod.execScriptManager(
                "NativeBlockApi",
                "setRenderLayer",
                new Class[]{int.class, int.class},
                new Object[]{blockId, modId}
        );
    }

    public static void setRenderType(int blockId, int modId) {
        if (!Mod.isPro) {
            ScriptManager.nativeSetBlockRenderShape(blockId, modId);
        }
    }

    public static void setShape(int blockId, float sx, float sy, float sz, float ex, float ey, float ez, int blockDamage) {
        if (!Mod.isPro) {
            ScriptManager.nativeBlockSetShape(blockId, sx, sy, sz, ex, ey, ez, blockDamage);
        }
    }
}
