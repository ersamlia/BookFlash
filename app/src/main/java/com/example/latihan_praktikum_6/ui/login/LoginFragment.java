package com.example.latihan_praktikum_6.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.latihan_praktikum_6.Presentation.UI.MainActivity;
import com.example.latihan_praktikum_6.Presentation.UI.home.HomeFragment;
import com.example.latihan_praktikum_6.R;
import com.google.android.gms.auth.api.signin.*;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.*;

public class LoginFragment extends Fragment {
    private FirebaseAuth mAuth;
    private static final int RC_SIGN_IN = 9001;
    private GoogleSignInClient mGoogleSignInClient;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        mAuth = FirebaseAuth.getInstance();

        // Konfigurasi Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id)) // bisa juga pakai getString(R.string.default_web_client_id)
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(getActivity(), gso);

        // Email Login
        Button btnLogin = view.findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(v -> {
            String email = ((EditText) view.findViewById(R.id.email)).getText().toString().trim();
            String password = ((EditText) view.findViewById(R.id.password)).getText().toString().trim();

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(getActivity(), "Isi email dan password", Toast.LENGTH_SHORT).show();
                return;
            }

            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            startActivity(new Intent(getActivity(), MainActivity.class));
                            getActivity().finish(); // tutup LoginActivity
                        } else {
                            Toast.makeText(getActivity(), "Login gagal: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        });

        // Google Login
        Button btnGoogle = view.findViewById(R.id.btnGoogle);
        btnGoogle.setOnClickListener(v -> {
            Intent signInIntent = mGoogleSignInClient.getSignInIntent();
            startActivityForResult(signInIntent, RC_SIGN_IN);
        });

        // Ke halaman register
        TextView linkRegister = view.findViewById(R.id.linkRegister);
        linkRegister.setOnClickListener(v ->
                getParentFragmentManager().beginTransaction()
                        .replace(R.id.nav_host_fragment, new RegisterFragment())
                        .addToBackStack(null)
                        .commit());

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
                mAuth.signInWithCredential(credential)
                        .addOnCompleteListener(task2 -> {
                            if (task2.isSuccessful()) {
                                startActivity(new Intent(getActivity(), com.example.latihan_praktikum_6.Presentation.UI.MainActivity.class));
                                getActivity().finish();
                            } else {
                                Toast.makeText(getActivity(), "Google Login gagal", Toast.LENGTH_SHORT).show();
                            }
                        });
            } catch (ApiException e) {
                Toast.makeText(getActivity(), "Google sign in failed: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

}
