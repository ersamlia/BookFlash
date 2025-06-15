package com.example.latihan_praktikum_6.Presentation.UI.setting;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.latihan_praktikum_6.R;

public class SettingsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.settings_container, new SettingsFragment())
                .commit();
    }
}

