package com.xhh.modpe.java4modpe;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.xhh.modpe.java4modpe.util.PathUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity {

    private Button imp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imp = findViewById(R.id.app_import);
        imp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    InputStream inputStream = getAssets().open(PathUtil.JAVA4MODPE);
                    File file = new File(PathUtil.MOD_PATH);
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
                ComponentName componentName = new ComponentName(PathUtil.PACKAGE_BLOCKLAUNCHER,
                        PathUtil.PACKAGE_BLOCKLAUNCHER_ACTIVITY);
                intent.setData(Uri.fromFile(new File(PathUtil.MOD_PATH)));
                intent.setComponent(componentName);
                startActivity(intent);
            }
        });
    }
}
