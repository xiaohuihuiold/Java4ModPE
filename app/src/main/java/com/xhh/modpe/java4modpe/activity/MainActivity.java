package com.xhh.modpe.java4modpe.activity;

import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.xhh.modpe.java4modpe.R;
import com.xhh.modpe.java4modpe.fragment.HomeFragment;
import com.xhh.modpe.java4modpe.fragment.ModuleFragment;
import com.xhh.modpe.java4modpe.util.ModuleUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar toolbar;
    private DrawerLayout drawer;
    private ActionBarDrawerToggle toggle;
    private NavigationView navigationView;

    private Bundle savedInstanceState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setBackgroundDrawable(null);
        setContentView(R.layout.activity_main);
        this.savedInstanceState = savedInstanceState;
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /** try {
         InputStream inputStream = getAssets().open(ModuleUtil.JAVA4MODPE);
         File file = new File(ModuleUtil.MOD_PATH);
         if (!file.exists()) {
         if (file.createNewFile()) {

         }
         }
         OutputStream outputStream = new FileOutputStream(file);
         byte[] bytes = new byte[1024];
         int is = 0;
         while ((is = inputStream.read(bytes)) != -1) {
         outputStream.write(bytes, 0, is);
         }
         inputStream.close();
         outputStream.close();
         } catch (IOException e) {
         e.printStackTrace();
         }

         Intent intent = new Intent();
         ComponentName componentName = new ComponentName(ModuleUtil.PACKAGE_BLOCKLAUNCHER,
         ModuleUtil.PACKAGE_BLOCKLAUNCHER_ACTIVITY);
         intent.setData(Uri.fromFile(new File(ModuleUtil.MOD_PATH)));
         intent.setComponent(componentName);
         startActivity(intent);**/

        initView();
        initData();
    }

    private void initView() {
        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);

    }

    private void initData() {

        toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        showHome();
    }

    private void showHome() {
        showFragment(getString(R.string.app_name), "fragment_home", HomeFragment.class);
    }

    private void showModule() {
        showFragment(getString(R.string.nav_title_module), "fragment_module", ModuleFragment.class);
    }

    private void showFragment(String title, String tag, Class<?> fragmentClass) {
        try {
            toolbar.setTitle(title);
            Fragment fragment = null;
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            if (savedInstanceState == null) {
                fragment = (Fragment) fragmentClass.newInstance();
                fragmentTransaction.add(R.id.main_fragment, fragment, tag);
            } else {
                fragment = (Fragment) getSupportFragmentManager().findFragmentByTag(tag);
            }
            if (fragment == null) {
                fragment = (Fragment) fragmentClass.newInstance();
                fragmentTransaction.add(R.id.main_fragment, fragment, tag);
            }
            hideAll(fragmentTransaction);
            fragmentTransaction.show(fragment);
            fragmentTransaction.commit();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private void hideAll(FragmentTransaction fragmentTransaction) {
        HomeFragment homeFragment = (HomeFragment) getSupportFragmentManager().findFragmentByTag("fragment_home");
        ModuleFragment moduleFragment = (ModuleFragment) getSupportFragmentManager().findFragmentByTag("fragment_module");
        if (homeFragment != null) {
            fragmentTransaction.hide(homeFragment);
        }
        if (moduleFragment != null) {
            fragmentTransaction.hide(moduleFragment);
        }
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_about) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.nav_home:
                showHome();
                break;
            case R.id.nav_module:
                showModule();
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
