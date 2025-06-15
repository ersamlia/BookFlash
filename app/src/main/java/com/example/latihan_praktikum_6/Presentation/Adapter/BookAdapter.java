package com.example.latihan_praktikum_6.Presentation.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.latihan_praktikum_6.R;
import com.example.latihan_praktikum_6.Data.model.Volume;
import com.example.latihan_praktikum_6.Data.model.VolumeInfo;

import java.util.ArrayList;
import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder> {

    private List<Volume> bookList = new ArrayList<>();

    public void setData(List<Volume> books) {
        this.bookList = books;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_book, parent, false);
        return new BookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        Volume volume = bookList.get(position);
        VolumeInfo info = volume.getVolumeInfo();

        holder.titleText.setText(info.getTitle());

        if (info.getAuthors() != null && !info.getAuthors().isEmpty()) {
            holder.authorText.setText(info.getAuthors().get(0));  // tampilkan penulis pertama
        } else {
            holder.authorText.setText("Unknown Author");
        }

        String imageUrl;
        if (info.getImageLinks() != null && info.getImageLinks().getThumbnail() != null) {
            imageUrl = info.getImageLinks().getThumbnail();
            // Tambahkan ini untuk mengubah HTTP → HTTPS jika perlu
            imageUrl = imageUrl.replace("http://", "https://");
        } else {
            imageUrl = "https://via.placeholder.com/100x150?text=No+Image";
        }

        Glide.with(holder.itemView.getContext())
                .load(imageUrl)
                .into(holder.coverImage);
    }

    @Override
    public int getItemCount() {
        return bookList.size();
    }

    public static class BookViewHolder extends RecyclerView.ViewHolder {
        TextView titleText, authorText;
        ImageView coverImage;

        public BookViewHolder(@NonNull View itemView) {
            super(itemView);
            titleText = itemView.findViewById(R.id.textBookTitle);
            authorText = itemView.findViewById(R.id.textBookAuthor);
            coverImage = itemView.findViewById(R.id.imageBookCover);
        }
    }
}
