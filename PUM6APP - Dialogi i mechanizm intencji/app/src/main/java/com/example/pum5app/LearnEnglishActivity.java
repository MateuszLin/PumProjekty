package com.example.pum5app;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.io.Serializable;

public class LearnEnglishActivity extends AppCompatActivity {

    RadioGroup mode, languageGroup;
    RadioButton testMode, learnMode, polish, english;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn_english);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mode = findViewById(R.id.modeRadioGroup);
        languageGroup = findViewById(R.id.radioGroupLang);
        testMode = findViewById(R.id.radioTest);
        learnMode = findViewById(R.id.radioLearn);
        polish = findViewById(R.id.radioPolish);
        english = findViewById(R.id.radioEnglish);

        findViewById(R.id.startBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startNewActivity();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.about:
                AlertDialog dialog = new AlertDialog.Builder(this).create();
                dialog.setTitle(getString(R.string.about));
                dialog.setMessage(getString(R.string.pumapp));
                dialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //pass
                    }
                });

                dialog.show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    private void startNewActivity() {
        LearningHelper helper = new LearningHelper();

        helper.modeOfApp = setModeOfApp();
        helper.learningLanguage = setLanguageToLearn();
        Class classPass;

        if(helper.modeOfApp == LearningHelper.ModeApp.LEARN)
        {
            classPass = LearnActivity.class;
        }
        else
        {
            classPass = TestActivity.class;
        }

        Intent i = new Intent(getApplicationContext(), classPass);
        i.putExtra("Helper", helper);
        startActivity(i);
    }

    private LearningHelper.LanguageToLearn setLanguageToLearn() {
        int idRadio = languageGroup.getCheckedRadioButtonId();

        if(idRadio == polish.getId())
        {
            return LearningHelper.LanguageToLearn.POLISH;
        }
        else
        {
            return LearningHelper.LanguageToLearn.ENGLISH;
        }
    }

    private LearningHelper.ModeApp setModeOfApp() {
        int idRadio = mode.getCheckedRadioButtonId();

        if(idRadio == testMode.getId())
        {
            return LearningHelper.ModeApp.TEST;
        }
        else {
            return LearningHelper.ModeApp.LEARN;
        }
    }


}
