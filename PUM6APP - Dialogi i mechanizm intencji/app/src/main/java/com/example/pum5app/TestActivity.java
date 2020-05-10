package com.example.pum5app;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TestActivity extends AppCompatActivity {

    String[] polishWords, englishWords;
    List<Integer> listOfIndex = new ArrayList<>();
    LearningHelper helper;
    TextView text;
    EditText translation;
    boolean finish = false;
    String goodWord;
    int indexOfWord;
    int goodAnswers = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        Intent intent = getIntent();
        helper = (LearningHelper) intent.getSerializableExtra("Helper");
        polishWords = getResources().getStringArray(R.array.countryPolish);
        englishWords = getResources().getStringArray(R.array.countryEnglish);
        text = findViewById(R.id.wordTV);
        translation = findViewById(R.id.editText);

        findViewById(R.id.nextButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkWord();
            }
        });

        for (int i = 0; i < polishWords.length; i++)
        {
            listOfIndex.add(i);
        }

        prepareTest();
    }

    private void checkWord() {
        if(finish)
        {
            openResultActivity();
        }
        else
        {
            String word = translation.getText().toString();

            if(!"".equals(word))
            {
                word = word.toLowerCase();

                if(word.equals(goodWord))
                {
                    goodAnswers++;
                }

                translation.setText("");
                showWord();
            }
            else
            {
                Toast.makeText(getApplicationContext(), getString(R.string.writeText), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void prepareTest() {
        indexOfWord = 0;
        Collections.shuffle(listOfIndex);
        showWord();
    }

    private void showWord() {
        if(indexOfWord == listOfIndex.size())
        {
            finish = true;
            openResultActivity();
        }
        else
        {
            int index = listOfIndex.get(indexOfWord);
            if(helper.learningLanguage == LearningHelper.LanguageToLearn.POLISH)
            {
                goodWord = englishWords[index].toLowerCase();
                showWord(polishWords[index]);
            }
            else
            {
                goodWord = polishWords[index].toLowerCase();
                showWord(englishWords[index]);
            }
            indexOfWord++;
        }
    }

    private void openResultActivity() {
        helper.goodAnswer = goodAnswers;
        helper.numberOfAnswer = polishWords.length;

        Intent i = new Intent(getApplicationContext(), TestResultActivity.class);
        i.putExtra("Helper", helper);
        startActivity(i);
    }

    private void showWord(String word) {
        text.setText(word);
    }

    @Override
    public void onBackPressed() {

        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle(getString(R.string.endTest));
        dialog.setMessage(getString(R.string.doUWantFinish));
        dialog.setPositiveButton(getString(R.string.yes), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                TestActivity.super.onBackPressed();
            }
        });

        dialog.setNegativeButton(getString(R.string.no), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        dialog.show();
    }
}
