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
import com.xhh.modpe.java4modpe.module.manager.ModuleManager;
import com.xhh.modpe.java4modpe.module.model.AppData;
import com.xhh.modpe.library.activity.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class ManagerActivity extends BaseActivity {

    private ListView listView;

    public ManagerActivity(@NonNull Context context) {
        super(context);
    }

    @Override
    public void onCreate() {
        setView(R.layout.activity_manager);
        listView = (ListView) findViewById(R.id.list_applist);
        listView.setAdapter(new ListAppAdapter(getContextMod(), ModuleManager.getInstance(null).getAppDatas()));
    }

}
