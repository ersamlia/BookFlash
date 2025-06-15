package com.example.latihan_praktikum_6.Presentation.UI;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

import com.example.latihan_praktikum_6.Presentation.Adapter.BookAdapter;
import com.example.latihan_praktikum_6.Presentation.UI.home.HomeFragment;
import com.example.latihan_praktikum_6.Presentation.UI.konten.KontenFragment;
import com.example.latihan_praktikum_6.Presentation.UI.menu.MenuLainFragment;
import com.example.latihan_praktikum_6.Presentation.UI.setting.SettingsFragment;
import com.example.latihan_praktikum_6.Presentation.UI.setting.SettingsActivity;
import com.example.latihan_praktikum_6.Presentation.ViewModel.BookViewModel;
import com.example.latihan_praktikum_6.R;
import com.example.latihan_praktikum_6.ui.login.LoginFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private BookAdapter adapter;
    private BookViewModel viewModel;

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser == null) {
            // Belum login, arahkan ke Login
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.nav_host_fragment, new LoginFragment()) // <- diganti jadi nav_host_fragment
                    .commit();
        } else {
            // Sudah login, langsung ke Home
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.nav_host_fragment, new HomeFragment())
                    .commit();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set up BottomNavigationView with new listener method
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);

        // Use the newer setOnItemSelectedListener
        bottomNavigationView.setOnItemSelectedListener(item -> {
            Fragment fragment = null;

            // Ganti switch dengan if-else
            if (item.getItemId() == R.id.home) {
                fragment = new HomeFragment();
            } else if (item.getItemId() == R.id.konten) {
                fragment = new KontenFragment();
            } else if (item.getItemId() == R.id.menu_lain) {
                fragment = new MenuLainFragment();
            } else if (item.getItemId() == R.id.setting) {
                fragment = new SettingsFragment();
            }

            // Replace the fragment when a menu item is selected
            if (fragment != null) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.nav_host_fragment, fragment)
                        .commit();
            }

            return true;
        });

        // Set HomeFragment as default when activity is first created
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.nav_host_fragment, new HomeFragment())
                    .commit();
        }

        SharedPreferences prefs = getSharedPreferences("settings_prefs", MODE_PRIVATE);
        boolean isNightMode = prefs.getBoolean("night_mode", false);
        AppCompatDelegate.setDefaultNightMode(
                isNightMode ? AppCompatDelegate.MODE_NIGHT_YES : AppCompatDelegate.MODE_NIGHT_NO
        );


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            startActivity(new Intent(this, SettingsActivity.class));
            return true;
        } else if (id == R.id.action_about) {
            showAboutDialog();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }


    private void showAboutDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Tentang Aplikasi")
                .setMessage("Aplikasi ini dikembangkan untuk latihan praktikum.\nVersi 1.0")
                .setPositiveButton("OK", null)
                .show();
    }

}
