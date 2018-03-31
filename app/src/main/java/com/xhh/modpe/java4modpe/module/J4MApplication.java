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
import android.widget.Toast;

import com.xhh.modpe.java4modpe.module.model.AppData;
import com.xhh.modpe.java4modpe.module.window.ManagerWindow;
import com.xhh.modpe.java4modpe.util.PathUtil;
import com.xhh.modpe.library.Application;

import java.io.File;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.List;

import dalvik.system.DexClassLoader;

public class J4MApplication extends Application {
    public J4MApplication(Activity activity, Context context) {
        super(activity, context);
        File file = new File(PathUtil.MOD_PATH);
        if (file.exists()) {
            file.delete();
        }
    }

    @Override
    public void init() {
        super.init();
        showWindow(ManagerWindow.class);
        new Thread(new Runnable() {
            @Override
            public void run() {
                loadModPE();
            }
        }).start();
    }

    private void loadModPE(){
        PackageManager pm = getActivity().getPackageManager();
        List<PackageInfo> packages = pm.getInstalledPackages(0);
        for (PackageInfo packageInfo : packages) {
            if ((packageInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 0) {
                try {
                    ApplicationInfo applicationInfo=getActivity().getPackageManager().getApplicationInfo(packageInfo.packageName,PackageManager.GET_META_DATA);
                    if(applicationInfo.metaData.getString("modpe_name")!=null) {
                        String moduleApplication=applicationInfo.metaData.getString("modpe_application");
                        String modpePackage=packageInfo.packageName;
                        String clazzPackage="com.xhh.modpe.library.Mod";

                        Object object;

                        try{
                            Intent intent=new Intent();
                            intent.setPackage(modpePackage);

                            PackageManager packageManager=getActivity().getPackageManager();
                            List<ResolveInfo> activitys = packageManager.queryIntentActivities(intent, 0);
                            ResolveInfo resolveInfo=activitys.get(0);
                            ActivityInfo activityInfo=resolveInfo.activityInfo;

                            Resources resources=packageManager.getResourcesForApplication(intent.getPackage());
                            String div = System.getProperty("path.separator");
                            String packageName=activityInfo.packageName;
                            String dexPath=activityInfo.applicationInfo.sourceDir;
                            String dexOutputDir=getActivity().getApplicationInfo().dataDir;
                            String libPath=activityInfo.applicationInfo.nativeLibraryDir;
                            Context ctxMod = getActivity().createPackageContext(modpePackage,  Context.CONTEXT_IGNORE_SECURITY | Context.CONTEXT_INCLUDE_CODE) ;

                            DexClassLoader dcLoader=new DexClassLoader(dexPath, dexOutputDir, libPath, getActivity().getClassLoader());
                            Class clazz=dcLoader.loadClass(clazzPackage);
                            InvocationHandler invocationHandler=new InvocationHandler(){
                                @Override
                                public Object invoke(Object proxy, Method method, Object[] args) {
                                    return null;
                                }
                            };
                            object = clazz.newInstance();
                            Method method=clazz.getMethod("init",String.class,Activity.class,Context.class,InvocationHandler.class);
                            method.invoke(object,moduleApplication,getActivity(), ctxMod,invocationHandler);
                        }catch(Exception e){
                            e.printStackTrace();
                        }
                    }
                } catch (PackageManager.NameNotFoundException|NullPointerException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
