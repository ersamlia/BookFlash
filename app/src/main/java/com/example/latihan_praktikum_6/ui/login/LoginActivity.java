package com.example.latihan_praktikum_6.ui.login;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.example.latihan_praktikum_6.R;

public class LoginActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new LoginFragment())
                    .commit();
        }
    }
}

