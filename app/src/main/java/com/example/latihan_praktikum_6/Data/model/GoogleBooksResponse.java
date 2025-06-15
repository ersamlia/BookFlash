package com.example.latihan_praktikum_6.Data.model;

import java.util.List;

public class GoogleBooksResponse {
    private List<Volume> items;

    public List<Volume> getItems() {
        return items;
    }

    public void setItems(List<Volume> items) {
        this.items = items;
    }
}
