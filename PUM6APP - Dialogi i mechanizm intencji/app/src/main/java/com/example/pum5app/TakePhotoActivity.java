package com.example.pum5app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;

public class TakePhotoActivity extends AppCompatActivity {

    ImageView camera, gallery;
    static final int REQUEST_IMAGE_CAPTURE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_takephoto);

        camera = findViewById(R.id.takePhotoIV);
        gallery = findViewById(R.id.galleryIV);

        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               takePhotoIntent();
            }
        });

        gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPhotoGallery();
            }
        });
    }

    private void openPhotoGallery() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setType("image/*");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    private void takePhotoIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

}
