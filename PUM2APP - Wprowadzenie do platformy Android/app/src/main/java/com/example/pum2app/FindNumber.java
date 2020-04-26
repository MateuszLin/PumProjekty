package com.example.pum2app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class FindNumber extends AppCompatActivity {

    EditText checkNumber;
    Button checkButton;
    Button resetButton;
    TextView counter;
    TextView tinfo;
    int intCounter = 0;
    int numberToFind = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_number);

        checkNumber = findViewById(R.id.eTNumber);
        checkButton = findViewById(R.id.btnCheck);
        counter = findViewById(R.id.tVCounter);
        tinfo = findViewById(R.id.tVInfo);
        resetButton = findViewById(R.id.btnReset);

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newGame();
            }
        });

        checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkIfNumberIsCorrect();
            }
        });
        newGame();
    }

    private void checkIfNumberIsCorrect() {
       String temp = checkNumber.getText().toString();

       if(!"".equals(temp))
       {
           int value = Integer.parseInt(temp);

           if(value > numberToFind)
           {
                tooLargeNumber();
           }
           else if(value < numberToFind)
           {
                tooLowNumber();
           }
           else
           {
               correctAnswer();
           }
       }
    }

    private void tooLargeNumber() {
        AddToCounter();
        showToastAndSetText("Za dużo");
    }

    private void tooLowNumber() {
        AddToCounter();
        showToastAndSetText("Za mało");
    }

    private void showToastAndSetText(String text) {
        setTextOnTextView(text);
        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
    }

    private void setTextOnTextView(String text) {
        tinfo.setText(text);
    }

    private void correctAnswer() {
        showToastAndSetText("Zgadłeś!");
    }

    private void newGame()
    {
        Random rand = new Random();
        //losowanie liczby z przedziału 1 - 100
        numberToFind = rand.nextInt(100 - 1) + 1;
        intCounter = 0;
        setCounter(0);
        setTextOnTextView("");
        checkNumber.setText("");
    }

    private void AddToCounter() {
        setCounter(++intCounter);
    }

    private void setCounter(int i) {
        String value = Integer.toString(i);
        counter.setText(value);
    }
}
