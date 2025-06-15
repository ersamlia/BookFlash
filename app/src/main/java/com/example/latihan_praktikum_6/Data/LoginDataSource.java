package com.example.latihan_praktikum_6.Data;

import com.example.latihan_praktikum_6.Data.Result;
import com.example.latihan_praktikum_6.Data.model.LoggedInUser;

import java.io.IOException;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource {

    public Result login(String username, String password) {

        try {
            // TODO: handle loggedInUser authentication
            LoggedInUser fakeUser =
                    new LoggedInUser(
                            java.util.UUID.randomUUID().toString(),
                            "Jane Doe");
            return new com.example.latihan_praktikum_6.Data.Result.Success<>(fakeUser);
        } catch (Exception e) {
            return new com.example.latihan_praktikum_6.Data.Result.Error(new IOException("Error logging in", e));
        }
    }

    public void logout() {
        // TODO: revoke authentication
    }
}