package com.example.latihan_praktikum_6.ui.login;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.annotation.NonNull;

import com.example.latihan_praktikum_6.Data.LoginRepository;
import com.example.latihan_praktikum_6.Data.LoginDataSource;
//import com.example.latihan_praktikum_6.data.LoginRepository;

/**
 * ViewModel provider factory to instantiate LoginViewModel.
 * Required given LoginViewModel has a non-empty constructor
 */
public class LoginViewModelFactory implements ViewModelProvider.Factory {

    private com.example.latihan_praktikum_6.Data.LoginRepository LoginRepository;

    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(LoginViewModel.class)) {
            return (T) new LoginViewModel(LoginRepository.getInstance(new LoginDataSource()));
        } else {
            throw new IllegalArgumentException("Unknown ViewModel class");
        }
    }

    private class LoginDataSource extends com.example.latihan_praktikum_6.Data.LoginDataSource {
    }
}