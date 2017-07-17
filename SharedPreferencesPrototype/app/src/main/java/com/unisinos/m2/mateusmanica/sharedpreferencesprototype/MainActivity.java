package com.unisinos.m2.mateusmanica.sharedpreferencesprototype;

import android.app.PendingIntent;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner spinner = (Spinner) findViewById(R.id.countrySpin);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.list_of_lang, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        //Read sharedPreferences
        SharedPreferences sp = getPreferences(Context.MODE_PRIVATE);
        String defaultLang = getResources().getString(R.string.DEFAULT_LANGUAGE);
        String language = sp.getString(getString(R.string.LANGUAGE), defaultLang);

        int langPos = adapter.getPosition(language);
        spinner.setSelection(langPos);
        spinner.setOnItemSelectedListener(this);
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String newLang = parent.getSelectedItem().toString();
        SharedPreferences.Editor spEditor = getPreferences(Context.MODE_PRIVATE).edit();
        spEditor.putString(getString(R.string.LANGUAGE), newLang);
        spEditor.commit();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
