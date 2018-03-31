package com.xhh.modpe.java4modpe.module.activity;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.widget.ListView;
import android.widget.Toast;

import com.xhh.modpe.java4modpe.R;
import com.xhh.modpe.java4modpe.module.adapter.ListAppAdapter;
import com.xhh.modpe.java4modpe.module.model.AppData;
import com.xhh.modpe.library.activity.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class ManagerActivity extends BaseActivity {

    private ListView listView;

    public ManagerActivity(@NonNull Context context, Context contextMod) {
        super(context, contextMod);
    }

    @Override
    public void onCreate() {
        setView(R.layout.activity_manager);
        listView = (ListView) findViewById(R.id.list_applist);
        listView.setAdapter(new ListAppAdapter(getContextMod(), getAppList()));
    }

    private ArrayList<AppData> getAppList() {
        ArrayList<AppData> appDatas = new ArrayList<>();
        PackageManager pm = getActivity().getPackageManager();
        List<PackageInfo> packages = pm.getInstalledPackages(0);
        for (PackageInfo packageInfo : packages) {
            if ((packageInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 0) {
                try {
                    ApplicationInfo applicationInfo=getActivity().getPackageManager().getApplicationInfo(packageInfo.packageName,PackageManager.GET_META_DATA);
                    if(applicationInfo.metaData.getString("modpe_name")!=null) {
                        AppData appData = new AppData();
                        appData.setName((String) packageInfo.applicationInfo.loadLabel(getActivity().getPackageManager()));
                        appData.setDescription(applicationInfo.metaData.getString("modpe_description"));
                        //appData.setIcon(packageInfo.applicationInfo.loadIcon(getActivity().getPackageManager()));
                        appDatas.add(appData);
                    }
                } catch (PackageManager.NameNotFoundException|NullPointerException e) {
                    e.printStackTrace();
                }
            }
        }
        Toast.makeText(getActivity(), "" + appDatas.size(), Toast.LENGTH_LONG).show();
        return appDatas;
    }

}
