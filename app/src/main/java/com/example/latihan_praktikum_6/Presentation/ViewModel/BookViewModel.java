package com.example.latihan_praktikum_6.Presentation.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.latihan_praktikum_6.Data.model.GoogleBooksResponse;
import com.example.latihan_praktikum_6.Data.model.Volume;
import com.example.latihan_praktikum_6.Data.Repository.BookRepository;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookViewModel extends ViewModel {

    private final BookRepository repository = new BookRepository();

    private final MutableLiveData<List<Volume>> bookList = new MutableLiveData<>();

    public LiveData<List<Volume>> getBooks() {
        return bookList;
    }

    public void fetchBooks() {
        repository.getBooks().enqueue(new Callback<GoogleBooksResponse>() {
            @Override
            public void onResponse(Call<GoogleBooksResponse> call, Response<GoogleBooksResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    bookList.setValue(response.body().getItems());
                }
            }

            @Override
            public void onFailure(Call<GoogleBooksResponse> call, Throwable t) {
                // Optional: log error
            }
        });
    }

    public LiveData<List<Volume>> searchBooks(String query) {
        MutableLiveData<List<Volume>> searchResult = new MutableLiveData<>();
        repository.searchBooks(query).enqueue(new Callback<GoogleBooksResponse>() {
            @Override
            public void onResponse(Call<GoogleBooksResponse> call, Response<GoogleBooksResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    searchResult.setValue(response.body().getItems());
                }
            }

            @Override
            public void onFailure(Call<GoogleBooksResponse> call, Throwable t) {
                // Optional: log error
            }
        });
        return searchResult;
    }
}
