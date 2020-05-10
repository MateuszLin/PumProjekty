package com.example.pum5app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btnLearn).setOnClickListener(this);
        findViewById(R.id.btnNotes).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btnLearn)
        {
            openActivity(LearnEnglishActivity.class);
        }
        else if(v.getId() == R.id.btnNotes)
        {
            openActivity(TakePhotoActivity.class);
        }
    }

    private void openActivity(Class activi)
    {
        Intent i = new Intent(getApplicationContext(), activi);
        startActivity(i);
    }



}
