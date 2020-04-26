package com.example.pum3app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MenuActivity extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        findViewById(R.id.btnTrafficLights).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity(MainActivity.class);
            }
        });

        findViewById(R.id.btnTicTacToe).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity(TicTacToeActivity.class);
            }
        });

        findViewById(R.id.btnListView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity(ListViewActivity.class);
            }
        });

        findViewById(R.id.btnListView2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity(ListViewActivity.class, "5A");
            }
        });

        findViewById(R.id.btnOrderPizza).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity(OrderPizzaActivity.class);
            }
        });

        findViewById(R.id.btnQuiz).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity(QuizActivity.class);
            }
        });


    }

    private void openActivity(Class acti)
    {
        openActivity(acti, "");
    }

    private void openActivity(Class acti, String extra)
    {
        Intent i = new Intent(getApplicationContext(), acti);
        if(!extra.equals(""))
        {
            i.putExtra("5A", 1);
        }

        startActivity(i);
    }


}
