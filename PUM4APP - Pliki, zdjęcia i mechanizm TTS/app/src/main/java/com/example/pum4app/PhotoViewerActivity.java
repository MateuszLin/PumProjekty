package com.example.pum4app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

public class PhotoViewerActivity extends AppCompatActivity {

    ViewGroup scrollViewgroup;
    ImageView icon;
    ImageView imageSelected;
    TextView caption;
    TextView txtMsg;
    File[] files;
    int index;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_viewer);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
        {
            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1000);
        }
        else
        {
            loadContent();
        }

    }

    private void loadContent()
    {
        txtMsg = findViewById(R.id.txtMsg);

        scrollViewgroup = findViewById(R.id.viewgroup);
        imageSelected  =findViewById(R.id.imageSelected);

        try {
            String absolutePathSd = Environment.getExternalStorageDirectory().getAbsolutePath();

            String path2Pictures = absolutePathSd + "/Pictures/thumbnails";

            File sdPicturesFiles = new File(path2Pictures);
            files = sdPicturesFiles.listFiles();

            txtMsg.append("\nNum files: " + files.length );

            File file;
            for(index = 0; index < files.length; index++)
            {
                file = files[index];

                final View frame = getLayoutInflater().inflate(R.layout.frame_icon_caption, null);

                TextView caption = frame.findViewById(R.id.caption);
                ImageView icon = frame.findViewById(R.id.iconView);

                BitmapFactory.Options bmOptions = new BitmapFactory.Options();

                bmOptions.inSampleSize = 1;
                Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath(), bmOptions);
                icon.setImageBitmap(bitmap);

                caption.setText("File- " + index);
                scrollViewgroup.addView(frame);

                frame.setId(index);

                frame.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String text = "Selected position " + frame.getId();
                        txtMsg.setText(text);
                        showLargeImage(frame.getId());
                    }
                });
            }

        }catch (Exception e)
        {
            txtMsg.append("\nError " + e.getMessage());
        }
    }

    private void showLargeImage(int id) {
        File image = files[id];
        imageSelected.setImageDrawable(Drawable.createFromPath(image.getAbsolutePath()));
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == 1000)
        {
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                Toast.makeText(this, "Permission Granted", Toast.LENGTH_LONG).show();
                loadContent();
            }
            else
            {
                finish();
            }
        }
    }
}
