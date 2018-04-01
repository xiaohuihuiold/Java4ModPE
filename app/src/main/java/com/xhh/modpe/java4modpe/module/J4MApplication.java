package com.xhh.modpe.java4modpe.module;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;

import com.xhh.modpe.java4modpe.module.manager.ModuleManager;
import com.xhh.modpe.java4modpe.module.model.AppData;
import com.xhh.modpe.java4modpe.module.window.ManagerWindow;
import com.xhh.modpe.java4modpe.util.ModuleUtil;
import com.xhh.modpe.library.Application;
import com.xhh.modpe.library.Mod;

import java.io.File;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import dalvik.system.DexClassLoader;

public class J4MApplication extends Application implements ModuleManager.OnModuleLoadListener {
    public J4MApplication(Activity activity, Context context) {
        super(activity, context);
        File file = new File(ModuleUtil.MOD_PATH);
        if (file.exists()) {
            file.delete();
        }
    }

    @Override
    public void init() {
        super.init();
        showWindow(ManagerWindow.class);
        ModuleManager.getInstance(this).addOnModuleLoadListener(this);
    }

    @Override
    public void onFinished(ArrayList<AppData> appDatas) {
        Mod.print(getActivity(), "加载" + appDatas.size() + "个modpe");
    }
}
