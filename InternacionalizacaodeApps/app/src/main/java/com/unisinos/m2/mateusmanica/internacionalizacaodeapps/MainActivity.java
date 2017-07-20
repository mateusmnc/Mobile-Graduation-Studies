package com.unisinos.m2.mateusmanica.internacionalizacaodeapps;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    protected Spinner mLangList;
    private static boolean is_translated;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence>(
                this,
                R.layout.support_simple_spinner_dropdown_item,
                getResources().getStringArray(R.array.langList));

        mLangList = (Spinner)findViewById(R.id.langSpin);
        mLangList.setAdapter(adapter);
        mLangList.setOnItemSelectedListener(this);

        String language = getCurrentLanguage();

        int langPosition = adapter.getPosition(language);
        mLangList.setSelection(langPosition);
        mLangList.setOnItemSelectedListener(this);

        if (is_translated == false) {
            translateApp(language);
            is_translated = true;
        }
    }

    @NonNull
    private String getCurrentLanguage() {
        SharedPreferences sharedPref = getPreferences(MODE_PRIVATE);
        String defaultLang = getResources().getString(R.string.DEFAULT_LANGUAGE);
        return sharedPref.getString(getString(R.string.SAVED_LANGUAGE), defaultLang);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String newLang = parent.getSelectedItem().toString();
        if (newLang.equals(getCurrentLanguage())){
            return;
        }
        setLanguage(newLang);
        translateApp(newLang);
    }

    private void translateApp(String newLang) {

        Locale locale        = null;
        String[] langList    = getResources().getStringArray(R.array.langList);
        Configuration config = getBaseContext().getResources().getConfiguration();

        if (newLang.equals(langList[1])){
            locale = new Locale("en");
        }else if(newLang.equals(langList[2])){
            locale = new Locale("es");
        }else{
            locale = new Locale("pt");
        }

        Locale.setDefault(locale);
        Configuration conf = new Configuration(config);
        conf.locale = locale;
        getBaseContext().getResources().updateConfiguration(
                conf,
                getBaseContext().getResources().getDisplayMetrics());

        Intent intent = getIntent();
        finish();
        startActivity(intent);

    }

    private void setLanguage(String newLang) {
        SharedPreferences.Editor spEditor = getPreferences(Context.MODE_PRIVATE).edit();
        spEditor.putString(getString(R.string.SAVED_LANGUAGE), newLang);
        spEditor.commit();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
