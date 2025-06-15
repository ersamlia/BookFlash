package com.example.latihan_praktikum_6.Data.Remote;

import com.example.latihan_praktikum_6.Data.model.GoogleBooksResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET("volumes")
    Call<GoogleBooksResponse> getBooks(@Query("q") String query);
}
