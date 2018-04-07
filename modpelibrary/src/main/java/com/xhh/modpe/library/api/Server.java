package com.xhh.modpe.library.api;

import com.xhh.modpe.library.Mod;

import net.zhuoweizhang.mcpelauncher.ScriptManager;

public class Server {
    public static final String TAG = "Server";

    public static String[] getAllPlayerNames() {
        if (!Mod.isPro) {
            long[] arrayOfLong = getAllPlayers();
            String[] arrayOfString = new String[arrayOfLong.length];
            int i = 0;
            while (i < arrayOfString.length) {
                arrayOfString[i] = ScriptManager.nativeGetPlayerName(arrayOfLong[i]);
                i += 1;
            }
            return arrayOfString;
        }
        return null;
    }

    public static long[] getAllPlayers() {
        if (!Mod.isPro) {
            return ScriptManager.nativeServerGetPlayers();
        }
        return null;
    }

    public static void sendChat(String message) {
        if (!Mod.isPro) {
            ScriptManager.nativeSendChat(message);
        }
    }
}
