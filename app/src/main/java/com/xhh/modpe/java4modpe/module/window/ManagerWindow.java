package com.xhh.modpe.java4modpe.module.window;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.xhh.modpe.java4modpe.R;
import com.xhh.modpe.java4modpe.module.activity.ManagerActivity;
import com.xhh.modpe.library.window.BaseWindow;

public class ManagerWindow extends BaseWindow implements View.OnClickListener {

    private ImageView imageView;

    public ManagerWindow(@NonNull Context context) {
        super(context);
    }

    @Override
    public void onCreate() {
        setView(R.layout.window_manager);
        imageView = (ImageView) findViewById(R.id.window_manager_open);
        setMoveView(imageView);
        imageView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.window_manager_open:
                startActivity(ManagerActivity.class);
                break;
            default:
                break;
        }
    }
}
