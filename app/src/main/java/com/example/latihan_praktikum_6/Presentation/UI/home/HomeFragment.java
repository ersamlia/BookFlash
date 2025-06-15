package com.example.latihan_praktikum_6.Presentation.UI.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.latihan_praktikum_6.Presentation.Adapter.BookAdapter;
import com.example.latihan_praktikum_6.Presentation.ViewModel.BookViewModel;
import com.example.latihan_praktikum_6.R;

public class HomeFragment extends Fragment {
    private BookViewModel viewModel;
    private BookAdapter adapter;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        RecyclerView recyclerView = root.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new BookAdapter();
        recyclerView.setAdapter(adapter);

        viewModel = new ViewModelProvider(this).get(BookViewModel.class);
        viewModel.getBooks().observe(getViewLifecycleOwner(), books -> adapter.setData(books));
        viewModel.fetchBooks();

        return root;
    }
}
