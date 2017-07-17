package com.unisinos.m2.mateusmanica.internacionalizacaodeapps;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class MainActivity extends LocalizationActivity implements AdapterView.OnItemSelectedListener{
    protected Spinner mLangList;

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


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (position == 1) {
            setLanguage("en");
            return;
        }
        if (position == 2){
            setLanguage("es");
            return;
        }
        setLanguage("pt");

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
