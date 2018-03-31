package com.xhh.modpe.java4modpe.module;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import com.xhh.modpe.java4modpe.util.PathUtil;
import com.xhh.modpe.library.Application;

import java.io.File;

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
        Toast.makeText(getActivity(), "测试模块", Toast.LENGTH_LONG).show();
    }
}
