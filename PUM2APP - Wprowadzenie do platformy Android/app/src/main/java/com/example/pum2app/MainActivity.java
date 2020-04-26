package com.example.pum2app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btnQuessNumber).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StartNewActivity(FindNumber.class);
            }
        });

        findViewById(R.id.btnFlashLight).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StartNewActivity(FlashLight.class);
            }
        });

        findViewById(R.id.btnRoots).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StartNewActivity(CountRoots.class);
            }
        });
    }

    private void StartNewActivity(Class klasa)
    {
        Intent i = new Intent(getApplicationContext(), klasa);
        startActivity(i);
    }
}
