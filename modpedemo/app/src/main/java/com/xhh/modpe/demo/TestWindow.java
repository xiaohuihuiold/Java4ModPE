package com.xhh.modpe.demo;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;

import com.xhh.modpe.library.window.BaseWindow;

public class TestWindow extends BaseWindow {
    public TestWindow(@NonNull Context context) {
        super(context);
    }

    @Override
    public void onCreate() {
        setView(R.layout.window_main);
        Button button= (Button) findViewById(R.id.open);
        setMoveView(button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(TestActivity.class);
            }
        });
    }

    @Override
    public void useItem(int x, int y, int z, int itemid, int blockid, int side, int itemDamage, int blockDamage) {
        super.useItem(x, y, z, itemid, blockid, side, itemDamage, blockDamage);
    }
}
