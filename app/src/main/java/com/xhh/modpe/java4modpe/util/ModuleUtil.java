package com.xhh.modpe.java4modpe.util;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.util.Log;


import com.xhh.modpe.java4modpe.model.AppData;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ModuleUtil {
    public static final String SDCARD = Environment.getExternalStorageDirectory().getPath() + "/";
    public static final String JAVA4MODPE = "Java4ModPE.js";
    public static final String MOD_PATH = SDCARD + JAVA4MODPE;
    public static final String APP_FILES = SDCARD + "/Android/data/com.xhh.modpe.java4modpe/files/";
    public static final String APP_FILES_MODULES = APP_FILES + "modules/";

    public static final String PACKAGE_MCPE = "com.mojang.minecraftpe";
    public static final String PACKAGE_BLOCKLAUNCHER = "net.zhuoweizhang.mcpelauncher";
    public static final String PACKAGE_BLOCKLAUNCHER_ACTIVITY = "net.zhuoweizhang.mcpelauncher.api.ImportScriptActivity";
    public static final String PACKAGE_BLOCKLAUNCHER_PRO = "net.zhuoweizhang.mcpelauncher.pro";
    public static final String PACKAGE_BLOCKLAUNCHER_PRO_ACTIVITY = "net.zhuoweizhang.mcpelauncher.pro.api.ImportScriptActivity";
    public static final String PACKAGE_MODULE = "com.xhh.modpe.library.Mod";

    public static final String META_DATA_MODPE_NAME = "modpe_name";
    public static final String META_DATA_MODPE_APPLICATION = "modpe_application";
    public static final String META_DATA_MODPE_DESCRIPTION = "modpe_description";

    public static boolean isEnable(Context context) {
        File file = context.getExternalFilesDir(null);
        if (file == null) {
            file = new File(APP_FILES);
            if (!file.exists()) file.exists();
        }
        if (!new File(file.getPath() + "/enable").exists()) {
            return false;
        }
        return true;
    }

    public static boolean setEnable(Context context, boolean isEnable) {
        File file = context.getExternalFilesDir(null);
        if (file == null) {
            file = new File(APP_FILES);
            if (!file.exists()) file.exists();
        }
        File enable = new File(file.getPath() + "/enable");
        if (isEnable) {
            if (!enable.exists()) {
                try {
                    enable.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                    return false;
                }
            }
        } else {
            if (enable.exists()) {
                enable.delete();
            }
        }
        return true;
    }

    public static ArrayList<String> getEnableModules(Context context) {
        ArrayList<String> modules = new ArrayList<>();
        File file = context.getExternalFilesDir("modules");
        if (file == null) {
            file = new File(APP_FILES_MODULES);
            if (!file.exists()) file.exists();
        }
        File[] files = file.listFiles();
        for (File f : files) {
            modules.add(f.getName());
        }
        return modules;
    }

    public static boolean setModuleEnable(Context context,String packageName, boolean isEnable) {
        File file = context.getExternalFilesDir("modules");
        if (file == null) {
            file = new File(APP_FILES_MODULES);
            if (!file.exists()) file.exists();
        }
        File enable = new File(file.getPath() + "/"+packageName);
        if (isEnable) {
            if (!enable.exists()) {
                try {
                    enable.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                    return false;
                }
            }
        } else {
            if (enable.exists()) {
                enable.delete();
            }
        }
        return true;
    }

    public static ArrayList<PackageInfo> getUserApp(Context context) {
        ArrayList<PackageInfo> packageInfos = new ArrayList<>();
        PackageManager packageManager = context.getPackageManager();
        List<PackageInfo> packageInfoList = packageManager.getInstalledPackages(0);
        for (PackageInfo packageInfo : packageInfoList) {
            if ((packageInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 0) {
                packageInfos.add(packageInfo);
            }
        }
        return packageInfos;
    }

    public static ArrayList<AppData> getModules(ArrayList<PackageInfo> packageInfos, Context context) {
        ArrayList<AppData> appDatas = new ArrayList<>();
        for (PackageInfo packageInfo : packageInfos) {
            AppData appData = getAppData(packageInfo,context);
            if (appData != null) {
                appDatas.add(appData);
            }
        }
        return appDatas;
    }

    public static AppData getAppData(PackageInfo packageInfo,Context context) {
        if (packageInfo == null) return null;
        AppData appData = null;
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(packageInfo.packageName, PackageManager.GET_META_DATA);
            if (applicationInfo.metaData.getString(ModuleUtil.META_DATA_MODPE_NAME) == null) {
                return null;
            }
            appData = new AppData();
            appData.setName((String) applicationInfo.loadLabel(context.getPackageManager()));
            appData.setVersion(packageInfo.versionName);
            appData.setVersionCode(packageInfo.versionCode);
            appData.setPackageName(packageInfo.packageName);
            appData.setApplication(applicationInfo.metaData.getString(ModuleUtil.META_DATA_MODPE_APPLICATION));
            appData.setDescription(applicationInfo.metaData.getString(ModuleUtil.META_DATA_MODPE_DESCRIPTION));
            appData.setIcon(applicationInfo.loadIcon(context.getPackageManager()));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return appData;
    }

}
