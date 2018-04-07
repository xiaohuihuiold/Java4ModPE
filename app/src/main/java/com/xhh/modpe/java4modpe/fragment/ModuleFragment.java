package com.xhh.modpe.java4modpe.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xhh.modpe.java4modpe.R;
import com.xhh.modpe.java4modpe.adapter.ModuleAdapter;
import com.xhh.modpe.java4modpe.util.ModuleUtil;

public class ModuleFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private View view;

    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_module, container, false);
        initView();
        initData();
        return view;
    }

    private void initView() {
        swipeRefreshLayout = view.findViewById(R.id.module_swipe);
        recyclerView = view.findViewById(R.id.module_recycler);
    }

    private void initData() {
        swipeRefreshLayout.setOnRefreshListener(this);
        loadModule();
    }

    private void loadModule() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(new ModuleAdapter(getContext(), ModuleUtil.getModules(ModuleUtil.getUserApp(getContext()), getContext())));
    }

    @Override
    public void onRefresh() {
        loadModule();
        swipeRefreshLayout.setRefreshing(false);
    }
}
