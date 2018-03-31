package com.xhh.modpe.library.api;

import com.xhh.modpe.library.Mod;

public class Entity {
    private final static String TAG = "Entity";

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
