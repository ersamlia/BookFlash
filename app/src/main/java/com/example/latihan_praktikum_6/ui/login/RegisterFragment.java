package com.example.latihan_praktikum_6.ui.login;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.fragment.app.Fragment;

import com.example.latihan_praktikum_6.R;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterFragment extends Fragment {
    private FirebaseAuth mAuth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        mAuth = FirebaseAuth.getInstance();

        Button btnRegister = view.findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(v -> {
            String email = ((EditText) view.findViewById(R.id.email)).getText().toString();
            String password = ((EditText) view.findViewById(R.id.password)).getText().toString();

            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            Toast.makeText(getActivity(), "Register berhasil, silakan login", Toast.LENGTH_SHORT).show();
                            getActivity().getSupportFragmentManager().popBackStack();
                        } else {
                            Toast.makeText(getActivity(), "Register gagal", Toast.LENGTH_SHORT).show();
                        }
                    });
        });

        return view;
    }
}
