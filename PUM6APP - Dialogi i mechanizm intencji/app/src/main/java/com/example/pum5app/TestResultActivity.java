package com.example.pum5app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class TestResultActivity extends AppCompatActivity {

    LearningHelper helper;
    int questionNumber, goodAnswer = 0;
    TextView numberQ, numberOfA;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_result);
        numberQ = findViewById(R.id.questionNumberTV);
        numberOfA = findViewById(R.id.goodAnswerTV);

        Intent i = getIntent();
        helper = (LearningHelper) i.getSerializableExtra("Helper");
        questionNumber = helper.numberOfAnswer;
        goodAnswer = helper.goodAnswer;
        numberQ.setText(Integer.toString(questionNumber));
        numberOfA.setText(Integer.toString(goodAnswer));
    }
}
