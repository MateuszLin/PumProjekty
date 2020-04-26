package com.example.pum4app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btnTts).setOnClickListener(this);
        findViewById(R.id.btnPhoto).setOnClickListener(this);
    }

    private void openActivity(Class acti)
    {
        Intent i = new Intent(getApplicationContext(), acti);
        startActivity(i);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btnTts)
        {
            openActivity(TextToSpeechActivity.class);
        }
        else if(v.getId() == R.id.btnPhoto)
        {
            openActivity(PhotoViewerActivity.class);
        }
    }
}
