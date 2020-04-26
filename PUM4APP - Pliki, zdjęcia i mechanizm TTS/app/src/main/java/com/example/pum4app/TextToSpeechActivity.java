package com.example.pum4app;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Locale;
import java.util.UUID;

public class TextToSpeechActivity extends AppCompatActivity implements TextToSpeech.OnInitListener {

    private TextToSpeech tts;
    EditText text;
    String idSpeech;
    SeekBar speechRateBar;
    Intent fileIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_to_speech);
        text = findViewById(R.id.editText);
        tts = new TextToSpeech(this, this);
        speechRateBar = findViewById(R.id.seekBar);
        speechRateBar.setMax(30);
        speechRateBar.setMin(1);
        speechRateBar.setProgress(10);
        speechRateBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                float convertedValue = getConvertedValue(progress);
                tts.setSpeechRate(convertedValue);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        findViewById(R.id.btnSpeech).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speakOut();
            }
        });

        findViewById(R.id.buttonPick).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseFile();
            }
        });

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
        {
            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1000);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == 1000)
        {
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                Toast.makeText(this, "Permission Granted", Toast.LENGTH_LONG).show();
            }
            else
            {
                finish();
            }
        }
    }

    private void chooseFile() {
        fileIntent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        fileIntent.setType("text/*");
        fileIntent.addCategory(Intent.CATEGORY_OPENABLE);
        startActivityForResult(fileIntent, 10);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case 10:
                if (resultCode == RESULT_OK) {
                    try {
                        String path = data.getData().getPath();
                        path = path.substring(path.indexOf(":") + 1);
                        readContentOfFile(path);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                break;
        }
    }

    private void readContentOfFile(String path) throws Exception {
        File fl = new File(path);
        if(fl.exists())
        {
            FileInputStream fis = new FileInputStream(fl);
            String content = convertStreamToString(fis);
            Toast.makeText(getApplicationContext(), path, Toast.LENGTH_LONG).show();
            fis.close();

            if(!content.equals(""))
            {
                speakOut(content);
            }
        }
    }

    private  String convertStreamToString(InputStream is) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line = null;
        while ((line = reader.readLine()) != null) {
            sb.append(line).append("\n");
        }
        reader.close();
        return sb.toString();
    }

    private float getConvertedValue(int progress) {
        float floatVal = (float) 0.0;
        floatVal = .1f * progress;
        return floatVal;
    }

    private void speakOut() {
        String textToSpeech = text.getText().toString();
        speakOut(textToSpeech);
    }

    private void speakOut(String textToSpeech)
    {
        idSpeech = UUID.randomUUID().toString();
        if(tts.speak(textToSpeech, TextToSpeech.QUEUE_FLUSH, null, idSpeech) == TextToSpeech.SUCCESS)
        {
            //Toast.makeText(getApplicationContext(), "Speech on", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onInit(int status) {
        if(status == TextToSpeech.SUCCESS)
        {
            int result = tts.setLanguage(Locale.UK);

            if(result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED)
            {
                //Log.e("TTS", "Language not supported");
            }
            else
            {
                speakOut();
            }
        }
        else
        {
            //Log.e("TTS", "Initilization Failed");
        }
    }

    @Override
    public void onDestroy() {
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }
}
