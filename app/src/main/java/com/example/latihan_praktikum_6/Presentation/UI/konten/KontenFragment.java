package com.example.latihan_praktikum_6.Presentation.UI.konten;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Criteria;
import android.location.LocationListener;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.latihan_praktikum_6.R;
import com.example.latihan_praktikum_6.Presentation.Adapter.BookAdapter;
import com.example.latihan_praktikum_6.Presentation.ViewModel.BookViewModel;
import com.example.latihan_praktikum_6.Data.model.Volume;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

//Notifikasi
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

//KontenGPS
import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import androidx.core.app.ActivityCompat;

import android.widget.TextView;
import android.widget.Toast;



public class KontenFragment extends Fragment {

    private RecyclerView recyclerView;
    private BookAdapter adapter;
    private BookViewModel viewModel;
    private SearchView searchView;
    private List<Volume> allBooks = new ArrayList<>();

    public KontenFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_konten, container, false);

        recyclerView = view.findViewById(R.id.recyclerViewKonten);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        adapter = new BookAdapter();
        recyclerView.setAdapter(adapter);

        searchView = view.findViewById(R.id.searchViewKonten);

        viewModel = new ViewModelProvider(this).get(BookViewModel.class);

        // Ambil dan tampilkan data awal
        viewModel.getBooks().observe(getViewLifecycleOwner(), books -> {
            if (books != null) {
                allBooks = books;
                adapter.setData(books);
            }
        });

        // Panggil fetchBooks seperti di HomeFragment
        viewModel.fetchBooks();

        // Tambahkan di sini untuk menampilkan lokasi pengguna
        showLocation(view);

        setupSearch();
        return view;
    }

    private void setupSearch() {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                performSearch(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                performSearch(newText);
                return true;
            }
        });
    }

    private void performSearch(String query) {
        if (query == null || query.trim().isEmpty()) {
            adapter.setData(allBooks); // Tampilkan semua jika kosong
        } else {
            List<Volume> filteredList = new ArrayList<>();
            for (Volume book : allBooks) {
                if (book.getVolumeInfo().getTitle().toLowerCase().contains(query.toLowerCase())) {
                    filteredList.add(book);
                }
            }
            adapter.setData(filteredList);
        }
        // Tampilkan notifikasi setiap pencarian dilakukan
        showNotification("Menampilkan hasil pencarian untuk: " + query);
    }

    @SuppressLint("MissingPermission")
    private void showNotification(String message) {
        String CHANNEL_ID = "book_channel_id";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    CHANNEL_ID,
                    "Book Notifications",
                    NotificationManager.IMPORTANCE_DEFAULT
            );
            NotificationManager manager = requireContext().getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(requireContext(), CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_foreground) // gunakan ikon yang ada
                .setContentTitle("Pencarian Buku")
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(requireContext());
        notificationManager.notify(1, builder.build());
    }

    @SuppressLint("MissingPermission")
    private void showLocation(View view) {
        TextView locationTextView = view.findViewById(R.id.textViewLocation);
        LocationManager locationManager = (LocationManager) requireContext().getSystemService(Context.LOCATION_SERVICE);

        if (ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(requireActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            return;
        }

        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE);

        locationManager.requestSingleUpdate(criteria, new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {
                double latitude = location.getLatitude();
                double longitude = location.getLongitude();
                locationTextView.setText("Lokasi Anda: " + latitude + ", " + longitude);
            }

            @Override
            public void onProviderDisabled(@NonNull String provider) {
                locationTextView.setText("GPS tidak aktif");
            }
        }, null);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1 && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            showLocation(getView()); // Panggil ulang jika izin diberikan
        }
    }


}
