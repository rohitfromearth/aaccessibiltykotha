package com.example.aaccessibiltykotha;

import static android.Manifest.permission.ACCESS_NOTIFICATION_POLICY;
import static android.Manifest.permission.FOREGROUND_SERVICE;
import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;



import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button allowPermissionn, notificationperm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        allowPermissionn = findViewById(R.id.allowPermission);
        notificationperm=findViewById(R.id.notificationPermission);
//        Intent intent = new Intent(this, AccessService.class);
//        startService(intent);

        notificationperm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ActivityCompat.requestPermissions(MainActivity.this,
//                        new String[]{POST_NOTIFICATION}, 1);



            }
        });

        allowPermissionn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS));

            }
        });
    }
}