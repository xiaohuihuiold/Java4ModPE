package com.xhh.modpe.library.api;

import com.xhh.modpe.library.Mod;

import net.zhuoweizhang.mcpelauncher.api.modpe.RendererManager;

public class Renderer {
    public static final String TAG = "Renderer";

    public static RendererManager.NativeRenderer createHumanoidRenderer() {
        Object object = null;
        object = Mod.exec(
                "net.zhuoweizhang.mcpelauncher.api.modpe.RendererManager.NativeRendererApi",
                "createHumanoidRenderer",
                new Class[]{},
                new Object[]{}
        );
        if (object != null && object instanceof RendererManager.NativeRenderer) {
            return (RendererManager.NativeRenderer) object;
        }
        return null;
    }

    public static RendererManager.NativeRenderer get(String id) {
        Object object = null;
        object = Mod.exec(
                "net.zhuoweizhang.mcpelauncher.api.modpe.RendererManager.NativeRendererApi",
                "get",
                new Class[]{String.class},
                new Object[]{id}
        );
        if (object != null && object instanceof RendererManager.NativeRenderer) {
            return (RendererManager.NativeRenderer) object;
        }
        return null;
    }
}
