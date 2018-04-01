package com.xhh.modpe.library.window;

import android.content.Context;
import android.os.Vibrator;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.xhh.modpe.library.base.BaseDialog;
import com.xhh.modpe.library.base.Function;
import com.xhh.modpe.library.base.IFunction;

public abstract class BaseWindow extends BaseDialog{

    private Vibrator vibrator;

    private boolean isLongClick = false;

    public BaseWindow(@NonNull Context context) {
        super(context);
        setCanceledOnTouchOutside(false);
        setCancelable(false);

        Function.getInstance().addListener(this);
        vibrator = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);
    }

    @Override
    public void show() {
        super.show();
        Window window = getWindow();
        window.setFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE, WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);
        window.setBackgroundDrawable(null);
        window.getDecorView().setPadding(0, 0, 0, 0);
        window.setDimAmount(0);
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        window.setGravity(Gravity.LEFT | Gravity.TOP);
        layoutParams.x = 0;
        layoutParams.y = 0;
        window.setAttributes(layoutParams);
        onCreate();
    }

    public void setMoveView(View view) {
        view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                isLongClick = true;
                if (vibrator != null) {
                    vibrator.vibrate(30);
                }
                return false;
            }
        });
        view.setOnTouchListener(new View.OnTouchListener() {
            private int tx;
            private int ty;

            @Override
            public boolean onTouch(final View p1, MotionEvent p2) {
                switch (p2.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        tx = (int) p2.getX();
                        ty = (int) p2.getY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        if (isLongClick) {
                            setXY((int) p2.getRawX() - tx, (int) p2.getRawY() - ty);
                        }
                        break;
                    case MotionEvent.ACTION_UP:
                        isLongClick = false;
                        break;
                    default:
                        break;
                }
                return false;
            }
        });
    }

    public void setXY(int x, int y) {
        Window window = getWindow();
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        window.setGravity(Gravity.LEFT | Gravity.TOP);
        layoutParams.x = x;
        layoutParams.y = y;
        window.setAttributes(layoutParams);
    }

    public int[] getXY() {
        Window window = getWindow();
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        window.setGravity(Gravity.LEFT | Gravity.TOP);
        return new int[]{layoutParams.x, layoutParams.y};
    }

    public void close() {
        dismiss();
    }

}
