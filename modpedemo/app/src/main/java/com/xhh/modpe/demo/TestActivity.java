package com.xhh.modpe.demo;

import android.content.Context;
import android.support.annotation.NonNull;

import com.xhh.modpe.library.activity.BaseActivity;

public class TestActivity extends BaseActivity {

    public TestActivity(@NonNull Context context) {
        super(context);
    }

    @Override
    public void onCreate() {
        setView(R.layout.activity_test);
    }
}
