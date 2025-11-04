package com.poupoumpany.actividad10;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.poupoumpany.actividad10.adapters.ThumbnailAdapter;
import com.poupoumpany.actividad10.models.Photo;

import java.util.ArrayList;
import java.util.List;

public class ThumbnailActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ThumbnailAdapter adapter;
    private List<Photo> photoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thumbnail);

        recyclerView = findViewById(R.id.recyclerThumbnails);
        Button btnBackToHome = findViewById(R.id.btnBackToHome);

        photoList = new ArrayList<>();
        photoList.add(new Photo(R.drawable.sample1, "Sample 1", "Hermoso carnalito", "01/11/2025"));
        photoList.add(new Photo(R.drawable.sample2, "Sample 2", "Dios esta con nosotros", "02/11/2025"));
        photoList.add(new Photo(R.drawable.sample3, "Sample 3", "Dios nos ha abandonado", "03/11/1821"));

        adapter = new ThumbnailAdapter(this, photoList);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setAdapter(adapter);

        btnBackToHome.setOnClickListener(v -> {
            Intent intent = new Intent(ThumbnailActivity.this, MainActivity.class);
            startActivity(intent);
        });

    }

}

