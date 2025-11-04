package com.poupoumpany.actividad10;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.poupoumpany.actividad10.adapters.PhotoPagerAdapter;
import com.poupoumpany.actividad10.models.Photo;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewPager2 viewPager;
    private LinearLayout indicatorLayout;
    private List<Photo> photoList;
    private int currentPosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.viewPager);
        indicatorLayout = findViewById(R.id.indicatorLayout);
        Button btnPrev = findViewById(R.id.btnPrev);
        Button btnNext = findViewById(R.id.btnNext);
        Button btnHome = findViewById(R.id.btnHome);

        photoList = new ArrayList<>();
        photoList.add(new Photo(R.drawable.sample1, "Sample 1", "Hermoso carnalito en la playa", "01/11/2025"));
        photoList.add(new Photo(R.drawable.sample2, "Sample 2", "Dios esta aquí, dios esta aqui", "02/11/2025"));
        photoList.add(new Photo(R.drawable.sample3, "Sample 3", "Dios esta muerto, dios sigue muerto y nosotros lo hemos matado", "03/11/1821"));

        PhotoPagerAdapter adapter = new PhotoPagerAdapter(photoList);
        viewPager.setAdapter(adapter);

        setupIndicators(photoList.size());
        setCurrentIndicator(0);

        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                currentPosition = position;
                setCurrentIndicator(position);
            }
        });

        btnPrev.setOnClickListener(v -> {
            if (currentPosition > 0) viewPager.setCurrentItem(currentPosition - 1);
        });

        btnNext.setOnClickListener(v -> {
            if (currentPosition < photoList.size() - 1) viewPager.setCurrentItem(currentPosition + 1);
        });

        btnHome.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ThumbnailActivity.class);
            startActivity(intent);
        });
    }

    private void setupIndicators(int count) {
        ImageView[] indicators = new ImageView[count];
        indicatorLayout.removeAllViews();

        for (int i = 0; i < count; i++) {
            indicators[i] = new ImageView(this);
            indicators[i].setImageResource(R.drawable.indicator_inactive);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            params.setMargins(8, 0, 8, 0);
            indicatorLayout.addView(indicators[i], params);
        }
    }

    private void setCurrentIndicator(int index) {
        int childCount = indicatorLayout.getChildCount();
        for (int i = 0; i < childCount; i++) {
            ImageView imageView = (ImageView) indicatorLayout.getChildAt(i);
            imageView.setImageResource(
                    i == index ? R.drawable.indicator_active : R.drawable.indicator_inactive
            );
        }
    }
}
