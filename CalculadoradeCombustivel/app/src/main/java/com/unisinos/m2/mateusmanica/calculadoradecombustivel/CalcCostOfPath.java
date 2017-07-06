package com.unisinos.m2.mateusmanica.calculadoradecombustivel;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CalcCostOfPath extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc_cost_of_path);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.askCalcPricePathBt:
                calculate();
                return;
            case R.id.newCalcCostBt:
                clearFields();
                return;
            case R.id.backCalcCostBt:
                finish();
        }
    }

    private void calculate() {
        //Get the value for consumption, price of fuel and distance to be run
        String distance   = ((EditText)findViewById(R.id.disRunNum)).getText().toString();
        String priceLiter = ((EditText)findViewById(R.id.literPriceNum)).getText().toString();
        String consumV    = ((EditText)findViewById(R.id.consumVNum)).getText().toString();

        //Check if the fields are filled
        if (consumV.equals("") || priceLiter.equals("") || distance.equals("")){
            launchToast("Preencha: distancia a percorrer, consumo do veículo e preço do combustível");
            return;
        }

        Double consumVNum  = Double.valueOf(consumV);
        Double priceNum    = Double.valueOf(priceLiter);
        Double distanceNum = Double.valueOf(distance);

        double finalPrice = distanceNum / consumVNum * priceNum;
        ((TextView)findViewById(R.id.ansPathPriceTxt)).setText("R$" + finalPrice);
    }

    private void clearFields() {
        ((EditText)findViewById(R.id.disRunNum)).setText("");
        ((EditText)findViewById(R.id.literPriceNum)).setText("");
        ((EditText)findViewById(R.id.consumVNum)).setText("");
    }

    private void launchToast(String message){
        Context context = getApplicationContext();
        CharSequence text = message;
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }
}
