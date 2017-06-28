package com.unisinos.m2.mateusmanica.personaldataform_dpa_1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        TextView allContentTxt = (TextView) findViewById(R.id.allContentText);
        //String var = ((EditText)findViewById(R.id.nomeText)).getText().toString();
        Bundle bundle = getIntent().getExtras();
        //Retrieve the content
        String name     = bundle.getString("name");
        String dOfBirth = bundle.getString("dateofbirth");
        String gender   = bundle.getString("gender");
        String profess  = bundle.getString("profession");
        String mStatus  = bundle.getString("maritalStatus");

        allContentTxt.append("nome: " + name);
        allContentTxt.append("\nData de Nascimento: " + dOfBirth);
        allContentTxt.append("\nSexo: " + gender);
        allContentTxt.append("\nProfissão: " + profess);
        allContentTxt.append("\nEstado Civil: " + mStatus);

    }

    @Override
    public void onClick(View v) {
        //Intent intent = new Intent(Main2Activity.this, MainActivity.class);
        //startActivity(intent);
        finish();
    }
}
