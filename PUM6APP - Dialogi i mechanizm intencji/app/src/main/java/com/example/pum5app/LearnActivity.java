package com.example.pum5app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LearnActivity extends AppCompatActivity {

    String[] polishWords, englishWords;
    TextView polishWordTv, englishWordTv;
    Button nextButton;
    List<Integer> listOfIndex = new ArrayList<>();
    int indexOfWord = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn);

        polishWordTv = findViewById(R.id.polishWordTv);
        englishWordTv = findViewById(R.id.englishWordTv);
        polishWords = getResources().getStringArray(R.array.countryPolish);
        englishWords = getResources().getStringArray(R.array.countryEnglish);
        nextButton = findViewById(R.id.nextBtn);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextWord();
            }
        });

        for (int i = 0; i < polishWords.length; i++)
        {
            listOfIndex.add(i);
        }

        prepareLearning();
    }

    private void nextWord() {
        showWord();
        if(indexOfWord == polishWords.length)
        {
            nextButton.setVisibility(View.INVISIBLE);
        }
    }

    private void prepareLearning() {
        indexOfWord = 0;
        Collections.shuffle(listOfIndex);
        showWord();
    }

    private void showWord() {
        int index = listOfIndex.get(indexOfWord);
        String polishWord = polishWords[index];
        String englishWord = englishWords[index];
        showPolishWord(polishWord);
        showEnglishWord(englishWord);
        indexOfWord++;
    }

    private void showEnglishWord(String englishWord) {
        englishWordTv.setText(englishWord);
    }

    private void showPolishWord(String polishWord) {
        polishWordTv.setText(polishWord);
    }


}
