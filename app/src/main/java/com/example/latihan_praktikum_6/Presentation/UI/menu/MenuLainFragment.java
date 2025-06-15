package com.example.latihan_praktikum_6.Presentation.UI.menu;

import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.latihan_praktikum_6.R;

public class MenuLainFragment extends Fragment {

    public MenuLainFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu_lain, container, false);

        Button btnTentang = view.findViewById(R.id.btn_tentang);
        btnTentang.setOnClickListener(v -> {
            new AlertDialog.Builder(requireContext())
                    .setTitle("Tentang Aplikasi")
                    .setMessage("Ini adalah aplikasi pencarian buku berbasis Google Books API.")
                    .setPositiveButton("OK", null)
                    .show();
        });

        return view;
    }
}
