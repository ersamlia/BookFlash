package com.example.latihan_praktikum_6.Data.Repository;

import com.example.latihan_praktikum_6.Data.Remote.ApiClient;
import com.example.latihan_praktikum_6.Data.Remote.ApiService;
import com.example.latihan_praktikum_6.Data.model.GoogleBooksResponse;

import retrofit2.Call;

public class BookRepository {

    private final ApiService apiService;

    public BookRepository() {
        apiService = ApiClient.getApiService();
    }

    public Call<GoogleBooksResponse> getBooks() {
        return apiService.getBooks("novel");
    }

    public Call<GoogleBooksResponse> searchBooks(String query) {
        return apiService.getBooks(query);
    }
}
