package com.unisinos.m2.mateusmanica.listviewprototype;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class QualCombustivel extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qual_combustivel);
    }

    @Override
    public void onClick(View v) {
        finish();
    }
}
