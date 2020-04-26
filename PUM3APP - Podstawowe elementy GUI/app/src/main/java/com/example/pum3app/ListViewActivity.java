package com.example.pum3app;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class ListViewActivity extends ListActivity {

    String[] country = {"Polska", "Niemcy", "Francja", "Holandia", "Czechy", "Słowacja", "Białoruś", "Rosja", "Anglia"};
    String[] shortcutCountry = {"PL", "DE", "FR", "NL", "CZ", "SK", "BY", "R", "EN"};
    String[] countries;
    String[] countriesShorcuts;
    boolean a5 = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        Bundle extra = getIntent().getExtras();
        if(extra != null)
        {
            a5 = true;
            countries = getResources().getStringArray(R.array.Country_array);
            countriesShorcuts = getResources().getStringArray(R.array.Country_shortcuts);
            setListAdapter(new ArrayAdapter<String>(this, R.layout.listview_template, countries));
        }
        else
        {
            setListAdapter(new ArrayAdapter<String>(this, R.layout.listview_template, country));
        }
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id)
    {
        super.onListItemClick(l, v, position, id);
        String text;
        if(a5)
        {
            text = countriesShorcuts[position];
        }
        else
        {
            text = shortcutCountry[position];
        }

        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG).show();
    }
}
