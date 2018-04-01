package com.xhh.modpe.java4modpe.module.manager;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;

import com.xhh.modpe.java4modpe.module.model.AppData;
import com.xhh.modpe.java4modpe.util.ModuleUtil;
import com.xhh.modpe.library.Application;
import com.xhh.modpe.library.Mod;
import com.xhh.modpe.library.base.IFunction;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import dalvik.system.DexClassLoader;

public class ModuleManager implements Runnable {

    private static ModuleManager INSTANCE;

    private Application application;
    private ArrayList<AppData> appDatas = new ArrayList<>();

    private ArrayList<OnModuleLoadListener> onModuleLoadListeners = new ArrayList<>();

    private ModuleManager() {

    }

    private ModuleManager(Application application) {
        this.application = application;
        new Thread(this).start();
    }

    public static synchronized ModuleManager getInstance(Application application) {
        if (INSTANCE == null) {
            INSTANCE = new ModuleManager(application);
        }
        return INSTANCE;
    }

    @Override
    public void run() {
        appDatas = getModules(getUserApp(application.getActivity()));
        for (OnModuleLoadListener onModuleLoadListener : onModuleLoadListeners) {
            onModuleLoadListener.onFinished(appDatas);
        }
    }

    private ArrayList<AppData> getModules(ArrayList<PackageInfo> packageInfos) {
        ArrayList<AppData> appDatas = new ArrayList<>();
        for (PackageInfo packageInfo : packageInfos) {
            AppData appData = getAppData(packageInfo);
            if (appData != null) {
                appDatas.add(appData);
            }
        }
        return appDatas;
    }

    private AppData getAppData(PackageInfo packageInfo) {
        if (packageInfo == null) return null;
        AppData appData = null;
        try {
            ApplicationInfo applicationInfo = application.getActivity().getPackageManager().getApplicationInfo(packageInfo.packageName, PackageManager.GET_META_DATA);
            if (applicationInfo.metaData.getString(ModuleUtil.META_DATA_MODPE_NAME) == null) {
                return null;
            }
            appData = new AppData();
            appData.setName((String) applicationInfo.loadLabel(application.getActivity().getPackageManager()));
            appData.setVersion(packageInfo.versionName);
            appData.setVersionCode(packageInfo.versionCode);
            appData.setPackageName(packageInfo.packageName);
            appData.setApplication(applicationInfo.metaData.getString(ModuleUtil.META_DATA_MODPE_APPLICATION));
            appData.setDescription(applicationInfo.metaData.getString(ModuleUtil.META_DATA_MODPE_DESCRIPTION));
            appData.setObject(loadModule(appData));
            if (appData.getObject() == null) return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return appData;
    }

    private Object loadModule(AppData appData) {
        Object object = null;
        String moduleApplication = appData.getApplication();
        String modpePackage = appData.getPackageName();

        try {
            Intent intent = new Intent();
            intent.setPackage(modpePackage);

            PackageManager packageManager = application.getActivity().getPackageManager();
            List<ResolveInfo> activitys = packageManager.queryIntentActivities(intent, 0);
            ResolveInfo resolveInfo = activitys.get(0);
            ActivityInfo activityInfo = resolveInfo.activityInfo;

            Resources resources = packageManager.getResourcesForApplication(intent.getPackage());
            String div = System.getProperty("path.separator");
            String packageName = activityInfo.packageName;
            String dexPath = activityInfo.applicationInfo.sourceDir;
            String dexOutputDir = application.getActivity().getApplicationInfo().dataDir;
            String libPath = activityInfo.applicationInfo.nativeLibraryDir;
            Context ctxMod = application.getActivity().createPackageContext(modpePackage, Context.CONTEXT_IGNORE_SECURITY | Context.CONTEXT_INCLUDE_CODE);

            DexClassLoader dcLoader = new DexClassLoader(dexPath, dexOutputDir, libPath, application.getActivity().getClassLoader());
            Class clazz = dcLoader.loadClass(ModuleUtil.PACKAGE_MODULE);
            object = clazz.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return object;
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

    public void addOnModuleLoadListener(OnModuleLoadListener onModuleLoadListener) {
        onModuleLoadListeners.add(onModuleLoadListener);
        check();
    }

    public void removeOnModuleLoadListener(OnModuleLoadListener onModuleLoadListener) {
        onModuleLoadListeners.remove(onModuleLoadListener);
        check();
    }

    private void check() {
        int size = onModuleLoadListeners.size();
        for (int i = 0; i < size; i++) {
            if (onModuleLoadListeners.get(i) == null) {
                onModuleLoadListeners.remove(i);
                size--;
                i--;
            }
        }
    }

    public ArrayList<AppData> getAppDatas() {
        return appDatas;
    }

    public interface OnModuleLoadListener {
        public void onFinished(ArrayList<AppData> appDatas);
    }

}
