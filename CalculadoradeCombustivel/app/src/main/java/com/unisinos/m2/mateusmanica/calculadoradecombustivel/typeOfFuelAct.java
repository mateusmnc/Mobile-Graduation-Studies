package com.unisinos.m2.mateusmanica.calculadoradecombustivel;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class typeOfFuelAct extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type_of_fuel);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.calculateBt:
                calculate();
                return;
            case R.id.newCalcBt:
                clearFields();
                return;
            case R.id.backBt:
                finish();
        }
    }

    private void calculate(){
        //Get the value of Gasoline and Alcohol prices and car's performance
        String priceGas = ((EditText)findViewById(R.id.priceGasNum)).getText().toString();
        String priceAlc = ((EditText)findViewById(R.id.priceAlcNum)).getText().toString();
        String consGas  = ((EditText)findViewById(R.id.consumpGasNum)).getText().toString();
        String consAlc  = ((EditText)findViewById(R.id.consumpGasNum)).getText().toString();

        //In case one field is initial send a toast message/notification to the user
        if (priceGas.equals("") || priceAlc.equals("") || consGas.equals("") || consAlc.equals("")) {
            launchToast("Preencha todos os campos acima");
            return;
        }

        //convert to double
        double priceGasNum  = Double.valueOf(priceGas);
        double priceAlcNum  = Double.valueOf(priceAlc);
        double consumGasNum = Double.valueOf(consGas);
        double consumAlcNum = Double.valueOf(consAlc);

        //Check the 70% rule or the if the price rate reaches the performance rate
        if ( (priceAlcNum / priceGasNum <= 0.7) || ( priceAlcNum / priceGasNum <= consumAlcNum / consumGasNum)) {
            //Alcohol is more expensive than 70% of gasoline price, or the rate given by the
            // performance calc. It does not worth the investment
            ((TextView) findViewById(R.id.ansTypeOfFuelTxt)).setText("Etanol");
        }else {
            ((TextView) findViewById(R.id.ansTypeOfFuelTxt)).setText("Gasolina");
        }

    }

    private void clearFields() {
        ((EditText)findViewById(R.id.priceGasNum)).setText("");
        ((EditText)findViewById(R.id.priceAlcNum)).setText("");
        ((EditText)findViewById(R.id.consumpGasNum)).setText("");
        ((EditText)findViewById(R.id.consumpAlcNum)).setText("");
        ((TextView) findViewById(R.id.ansTypeOfFuelTxt)).setText("?");
    }

    private void launchToast(String message){
        Context context = getApplicationContext();
        CharSequence text = message;
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }
}