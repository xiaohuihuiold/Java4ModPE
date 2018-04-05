package com.xhh.modpe.java4modpe.fragment;

import android.annotation.SuppressLint;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.xhh.modpe.java4modpe.R;
import com.xhh.modpe.java4modpe.util.ModuleUtil;

import java.util.Objects;

public class HomeFragment extends Fragment implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener {

    private View view;

    private SwipeRefreshLayout swipeRefreshLayout;
    private Switch switchStatus;
    private CardView cardStatus;
    private CardView cardInstall;
    private CardView cardInfo;
    private ImageView imageStatus;
    private TextView textStatus;
    private TextView textVersion;
    private TextView textOS;
    private TextView textDevice;
    private TextView textMcpe;
    private TextView textBl;
    private TextView textBlpro;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        initView();
        initData();
        return view;
    }

    private void initView() {
        swipeRefreshLayout = view.findViewById(R.id.home_swipe);
        switchStatus = view.findViewById(R.id.home_switch_status);
        cardStatus = view.findViewById(R.id.home_card_status);
        cardInstall = view.findViewById(R.id.home_card_install);
        cardInfo = view.findViewById(R.id.home_card_info);
        imageStatus = view.findViewById(R.id.home_image_status);
        textStatus = view.findViewById(R.id.home_text_status);
        textVersion = view.findViewById(R.id.home_text_version);
        textOS = view.findViewById(R.id.home_text_os);
        textDevice = view.findViewById(R.id.home_text_device);
        textMcpe = view.findViewById(R.id.home_text_mcpe);
        textBl = view.findViewById(R.id.home_text_bl);
        textBlpro = view.findViewById(R.id.home_text_bl_pro);
    }

    private void initData() {
        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorAccent));
        swipeRefreshLayout.setOnRefreshListener(this);
        cardStatus.setOnClickListener(this);
        cardInstall.setOnClickListener(this);
        cardInfo.setOnClickListener(this);

        switchStatus.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                ModuleUtil.setEnable(getContext(),isChecked);
            }
        });

        loadInfo();
    }

    @Override
    public void onRefresh() {
        loadInfo();
        swipeRefreshLayout.setRefreshing(false);
    }

    private void loadInfo() {
        switchStatus.setChecked(ModuleUtil.isEnable(getContext()));
        loadDeviceInfo();
    }

    @SuppressLint("SetTextI18n")
    private void loadDeviceInfo() {
        textOS.setText("\u3000\u3000");
        textOS.append("Android ");
        textOS.append(Build.VERSION.RELEASE);
        textOS.append(" (" + "API " + Build.VERSION.SDK_INT + ")");
        textDevice.setText("\u3000\u3000" + Build.BRAND + " " + Build.MODEL);
        textMcpe.setText("\u3000\u3000" + getAppName(ModuleUtil.PACKAGE_MCPE));
        textBl.setText("\u3000\u3000" + getAppName(ModuleUtil.PACKAGE_BLOCKLAUNCHER));
        textBlpro.setText("\u3000\u3000" + getAppName(ModuleUtil.PACKAGE_BLOCKLAUNCHER_PRO));
    }

    public String getAppName(String packageName) {
        StringBuilder result = new StringBuilder();
        try {
            PackageInfo packageInfo = getActivity().getPackageManager().getPackageInfo(packageName, 0);
            result.append((String) packageInfo.applicationInfo.loadLabel(getActivity().getPackageManager()));
            result.append(" ").append(packageInfo.versionName);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        if(result.toString().equals("")){
            result.append("未找到").append(packageName);
        }
        return result.toString();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.home_card_status:
                break;
            case R.id.home_card_install:
                break;
            case R.id.home_card_info:
                loadDeviceInfo();
                break;
        }
    }
}
