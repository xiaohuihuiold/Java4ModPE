package com.xhh.modpe.library.activity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.PixelFormat;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.xhh.modpe.library.base.BaseDialog;

/**
 * Created by nameh on 2018/3/28 0028.
 */

public abstract class BaseActivity extends BaseDialog {

    public BaseActivity(@NonNull Context context) {
        super(context);
    }

    @Override
    public void show() {
        super.show();
        Window window=getWindow();
        window.setBackgroundDrawable(null);
        window.setBackgroundDrawable(new Drawable() {
            @Override
            public void draw(@NonNull Canvas canvas) {
                canvas.drawColor(Color.WHITE);
            }

            @Override
            public void setAlpha(int alpha) {

            }

            @Override
            public void setColorFilter(@Nullable ColorFilter colorFilter) {

            }

            @Override
            public int getOpacity() {
                return PixelFormat.UNKNOWN;
            }
        });
        window.getDecorView().setPadding(0, 0, 0, 0);
        window.setDimAmount(0);
        WindowManager.LayoutParams layoutParams=window.getAttributes();
        window.setGravity(Gravity.LEFT| Gravity.TOP);
        layoutParams.x=0;
        layoutParams.y=0;
        Point point=new Point();
        getWindow().getWindowManager().getDefaultDisplay().getSize(point);
        layoutParams.width=point.x;
        layoutParams.height=point.y;
        window.setAttributes(layoutParams);
        onCreate();
    }

    public void finish(){
        dismiss();
    }

}
