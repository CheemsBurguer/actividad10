package com.poupoumpany.actividad10.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.poupoumpany.actividad10.R;
import com.poupoumpany.actividad10.models.Photo;

import java.util.ArrayList;
import java.util.List;

public class ThumbnailAdapter extends RecyclerView.Adapter<ThumbnailAdapter.ThumbnailViewHolder> {

    private Context context;
    private List<Photo> photoList;
    private List<Photo> selectedPhotos = new ArrayList<>();
    private boolean selectionMode = false;

    public ThumbnailAdapter(Context context, List<Photo> photoList) {
        this.context = context;
        this.photoList = photoList;
    }

    @NonNull
    @Override
    public ThumbnailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_thumbnail, parent, false);
        return new ThumbnailViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ThumbnailViewHolder holder, int position) {
        Photo photo = photoList.get(position);
        holder.imageView.setImageResource(photo.getImageResId());
        holder.titleText.setText(photo.getTitle());

        holder.checkBox.setVisibility(selectionMode ? View.VISIBLE : View.GONE);
        holder.checkBox.setChecked(selectedPhotos.contains(photo));

        holder.itemView.setOnClickListener(v -> {
            if (selectionMode) {
                toggleSelection(photo);
                notifyItemChanged(position);
            }
        });

        holder.itemView.setOnLongClickListener(v -> {
            if (!selectionMode) {
                selectionMode = true;
                toggleSelection(photo);
                notifyDataSetChanged();
            }
            return true;
        });
    }

    private void toggleSelection(Photo photo) {
        if (selectedPhotos.contains(photo)) {
            selectedPhotos.remove(photo);
        } else {
            selectedPhotos.add(photo);
        }
    }

    public void clearSelection() {
        selectedPhotos.clear();
        selectionMode = false;
        notifyDataSetChanged();
    }

    public List<Photo> getSelectedPhotos() {
        return selectedPhotos;
    }

    public boolean isSelectionMode() {
        return selectionMode;
    }

    @Override
    public int getItemCount() {
        return photoList.size();
    }

    static class ThumbnailViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView titleText;
        CheckBox checkBox;

        public ThumbnailViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.thumbnailImage);
            titleText = itemView.findViewById(R.id.thumbnailTitle);
            checkBox = itemView.findViewById(R.id.thumbnailCheckbox);
        }
    }
}
