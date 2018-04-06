package com.xhh.modpe.java4modpe.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.xhh.modpe.java4modpe.R;
import com.xhh.modpe.java4modpe.module.model.AppData;
import com.xhh.modpe.java4modpe.util.ModuleUtil;

import java.util.ArrayList;

public class ModuleAdapter extends RecyclerView.Adapter<ModuleAdapter.ModuleViewHolder> {

    private Context context;

    private ArrayList<AppData> appDatas = new ArrayList<>();

    private ArrayList<String> enables;

    public ModuleAdapter(Context context, ArrayList<AppData> appDatas) {
        this.context = context;
        this.appDatas = appDatas;
        enables = ModuleUtil.getEnableModules(context);
    }

    @NonNull
    @Override
    public ModuleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ModuleViewHolder(LayoutInflater.from(context).inflate(R.layout.recycler_module, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ModuleViewHolder holder, int position) {
        AppData appData = appDatas.get(position);
        holder.name.setText(appData.getName());
        holder.enable.setText(appData.getVersion());
        holder.desc.setText(appData.getDescription());
        holder.enable.setChecked(enables.contains(appData.getPackageName()));
        holder.icon.setImageDrawable(appData.getIcon());
    }

    @Override
    public int getItemCount() {
        return appDatas.size();
    }

    class ModuleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener, CompoundButton.OnCheckedChangeListener {
        public ImageView icon;
        public TextView name;
        public Switch enable;
        public TextView desc;
        public CardView cardView;

        public ModuleViewHolder(View itemView) {
            super(itemView);
            icon = itemView.findViewById(R.id.recycler_module_icon);
            name = itemView.findViewById(R.id.recycler_module_name);
            enable = itemView.findViewById(R.id.recycler_module_enable);
            desc = itemView.findViewById(R.id.recycler_module_description);
            cardView = itemView.findViewById(R.id.recycler_module_card);
            cardView.setOnClickListener(this);
            cardView.setOnLongClickListener(this);
            enable.setOnCheckedChangeListener(this);
        }

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            ModuleUtil.setModuleEnable(context, appDatas.get(getAdapterPosition()).getPackageName(), isChecked);
        }

        @Override
        public void onClick(View v) {
            enable.setChecked(!enable.isChecked());
        }

        @Override
        public boolean onLongClick(View v) {
            PopupMenu pop = new PopupMenu(context, v);
            pop.getMenuInflater().inflate(R.menu.module_menu, pop.getMenu());
            pop.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.module_menu_info:
                            Intent intent = new Intent()
                                    .setAction("android.settings.APPLICATION_DETAILS_SETTINGS")
                                    .setData(Uri.fromParts("package", appDatas.get(getAdapterPosition()).getPackageName(), null));
                            context.startActivity(intent);
                            break;
                        case R.id.module_menu_un:
                            Uri uri = Uri.fromParts("package", appDatas.get(getAdapterPosition()).getPackageName(), null);
                            Intent intentdel = new Intent(Intent.ACTION_DELETE, uri);
                            context.startActivity(intentdel);
                            break;
                    }
                    return false;
                }
            });
            pop.show();
            return false;
        }
    }
}
