package com.example.latihan_praktikum_6.Presentation.UI.setting;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Switch;

import com.example.latihan_praktikum_6.R;
import com.example.latihan_praktikum_6.ui.login.LoginActivity;

public class SettingsFragment extends Fragment {

    private static final String PREFS_NAME = "settings_prefs";
    private static final String NIGHT_MODE_KEY = "night_mode";

    public SettingsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setting, container, false);

        Switch switchTheme = view.findViewById(R.id.switch_theme);
        Button btnLogout = view.findViewById(R.id.btn_logout);

        // Ambil preferensi saat ini
        SharedPreferences prefs = requireActivity().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        boolean isNightMode = prefs.getBoolean(NIGHT_MODE_KEY, false);
        switchTheme.setChecked(isNightMode);

        // Terapkan mode berdasarkan preferensi
        AppCompatDelegate.setDefaultNightMode(
                isNightMode ? AppCompatDelegate.MODE_NIGHT_YES : AppCompatDelegate.MODE_NIGHT_NO
        );

        // Saat switch ditekan, simpan preferensi dan ubah mode
        switchTheme.setOnCheckedChangeListener((buttonView, isChecked) -> {
            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean(NIGHT_MODE_KEY, isChecked);
            editor.apply();

            AppCompatDelegate.setDefaultNightMode(
                    isChecked ? AppCompatDelegate.MODE_NIGHT_YES : AppCompatDelegate.MODE_NIGHT_NO
            );

            requireActivity().recreate(); // Restart activity agar mode berubah
        });

        btnLogout.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        });

        return view;
    }
}
