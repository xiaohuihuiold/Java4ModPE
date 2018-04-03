package com.xhh.modpe.java4modpe.util;

import android.os.Environment;

public class ModuleUtil {
    public static final String SDCARD = Environment.getExternalStorageDirectory().getPath() + "/";
    public static final String JAVA4MODPE = "Java4ModPE.js";
    public static final String MOD_PATH = SDCARD + JAVA4MODPE;

    public static final String PACKAGE_BLOCKLAUNCHER="net.zhuoweizhang.mcpelauncher";
    public static final String PACKAGE_BLOCKLAUNCHER_ACTIVITY="net.zhuoweizhang.mcpelauncher.api.ImportScriptActivity";
    public static final String PACKAGE_MODULE="com.xhh.modpe.library.Mod";

    public static final String META_DATA_MODPE_NAME="modpe_name";
    public static final String META_DATA_MODPE_APPLICATION="modpe_application";
    public static final String META_DATA_MODPE_DESCRIPTION="modpe_description";
}
