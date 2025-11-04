package com.poupoumpany.actividad10.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.poupoumpany.actividad10.R;
import com.poupoumpany.actividad10.models.Photo;

import java.util.List;

public class PhotoPagerAdapter extends RecyclerView.Adapter<PhotoPagerAdapter.PhotoViewHolder> {

    private List<Photo> photoList;

    public PhotoPagerAdapter(List<Photo> photoList) {
        this.photoList = photoList;
    }

    @NonNull
    @Override
    public PhotoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_photo_card, parent, false);
        return new PhotoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PhotoViewHolder holder, int position) {
        Photo photo = photoList.get(position);
        holder.imageView.setImageResource(photo.getImageResId());
        holder.titleText.setText(photo.getTitle());
        holder.descText.setText(photo.getDescription());
        holder.dateText.setText(photo.getDate());
    }

    @Override
    public int getItemCount() {
        return photoList.size();
    }

    static class PhotoViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView titleText, descText, dateText;

        public PhotoViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.photoImage);
            titleText = itemView.findViewById(R.id.photoTitle);
            descText = itemView.findViewById(R.id.photoDesc);
            dateText = itemView.findViewById(R.id.photoDate);
        }
    }
}

