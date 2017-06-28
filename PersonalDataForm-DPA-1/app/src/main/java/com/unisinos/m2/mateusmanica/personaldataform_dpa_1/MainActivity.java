package com.unisinos.m2.mateusmanica.personaldataform_dpa_1;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    protected static String[] Profs = new String[] {
            "Developer", "Programador", "Cozinheiro", "Analista","Carpinteiro",
            "Auxiliar de Serviços gerais", "Desenvolvedor", "Designer", "Escritor", "Engenheiro",
            "Arquiteto", "Enfermeiro", "Médico", "Professor", "Quiroprata", "Psicólogo", "Advogado",
            "Taxista", "Motorista", "Pesquisador", "Auditor"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, Profs);
        final AutoCompleteTextView textView = (AutoCompleteTextView)
                findViewById(R.id.autoProfTextView);
        textView.setAdapter(adapter);
        textView.setThreshold(1);
        textView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    String typedText = textView.getText().toString();
                    ArrayAdapter<String> profsToUpdate = (ArrayAdapter<String>)textView.getAdapter();
                    profsToUpdate.add(typedText);
                    textView.setAdapter(profsToUpdate);
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        /*String password = ((TextView)findViewById(R.id.pwdText)).getText().toString();
        String pwdCheck = ((TextView)findViewById(R.id.pwdChkText)).getText().toString();
        //Caso as senhas não sejam as mesmas, notifica o usuário.
        if (!password.equals(pwdCheck)) {
            Context context = getApplicationContext();
            CharSequence text = "As senhas não conferem, redigite";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();

            return;
        }*/
        TextView password = (TextView)findViewById(R.id.pwdText);
        TextView pwdCheck = (TextView)findViewById(R.id.pwdChkText);

        //Caso as senhas não sejam as mesmas, notifica o usuário.
        if (!password.getText().toString().equals(pwdCheck.getText().toString())) {
            Context context = getApplicationContext();
            CharSequence text = "As senhas não conferem, redigite";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();

            password.setText("");
            pwdCheck.setText("");
            return;
        }

        Intent intent = new Intent(MainActivity.this, Main2Activity.class);

        //Gender processing
        String gender = "indefinido";
        if (((RadioButton)findViewById(R.id.radioMasc)).isChecked()){
            gender = "masculino";
        }else if(((RadioButton)findViewById(R.id.radioFemi)).isChecked()){
            gender = "feminino";
        }

        //Marital Status processing
        String maritalStatus = "indefinido";
        if(((RadioButton)findViewById(R.id.solteiroRadio)).isChecked()){
            maritalStatus = "Solteiro";
        }else if(((RadioButton)findViewById(R.id.casadoRadio)).isChecked()){
            maritalStatus = "Casado";
        }else if(((RadioButton)findViewById(R.id.divorRadio)).isChecked()){
            maritalStatus = "Divorciado";
        }

        //Send all relevant info to the next activity
        intent.putExtra("name",((TextView) findViewById(R.id.nomeText)).getText().toString());
        intent.putExtra("dateofbirth",((TextView) findViewById(R.id.dataNascText)).getText().toString());
        intent.putExtra("gender",gender);
        intent.putExtra("profession",((TextView) findViewById(R.id.autoProfTextView)).getText().toString());
        intent.putExtra("maritalStatus", maritalStatus);

        startActivity(intent);
    }
}
