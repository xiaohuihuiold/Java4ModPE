package com.xhh.modpe.demo;

import android.app.Activity;
import android.content.Context;

import com.xhh.modpe.library.Application;
import com.xhh.modpe.library.api.ModPE;
import com.xhh.modpe.library.base.IFunction;

public class Main extends Application {

    public Main(Activity activity, Context context) {
        super(activity, context);
    }

    @Override
    public void run() {
        super.run();
        showWindow(getActivity(), getContext(), TestWindow.class);
    }

    @Override
    public void useItem(int x, int y, int z, int itemid, int blockid, int side, int itemDamage, int blockDamage) {
        super.useItem(x, y, z, itemid, blockid, side, itemDamage, blockDamage);
        ModPE.clientMessage("点击:x:" + x + ",y:" + y + ",z:" + z);
    }
}
