package com.unisinos.m2.mateusmanica.calculadoradecombustivel;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CalcCompAverage extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc_comp_average);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.askCCABt:
                calculate();
                return;
            case R.id.newCalcCABt:
                clearFields();
                return;
            case R.id.backCCABt:
                finish();
        }
    }

    public void calculate(){
        //Get the value for liters in tank, initial and final kilometers
        String initKm      = ((EditText)findViewById(R.id.initRunKmNum)).getText().toString();
        String finalKm     = ((EditText)findViewById(R.id.finalKmRunNum)).getText().toString();
        String filledTank2 = ((EditText)findViewById(R.id.filledTankNum2)).getText().toString();

        //Check if the fields are filled
        if (initKm.equals("") || finalKm.equals("") || filledTank2.equals("")){
            launchToast("Preencha: Km inicial, Km Final e Litros abastecidos");
            return;
        }

        double initialKmNum   = Double.valueOf(initKm);
        double finalKmNum     = Double.valueOf(finalKm);
        double filledTankNum2 = Double.valueOf(filledTank2);

        double completeAverage = (finalKmNum - initialKmNum) / filledTankNum2;

        ((TextView)findViewById(R.id.ansCCATxt)).setText(completeAverage + " km/l");
    }

    public void clearFields(){
        ((EditText)findViewById(R.id.initRunKmNum)).setText("");
        ((EditText)findViewById(R.id.finalKmRunNum)).setText("");
        ((EditText)findViewById(R.id.filledTankNum2)).setText("");
        ((TextView)findViewById(R.id.ansCCATxt)).setText("?");
    }

    private void launchToast(String message){
        Context context = getApplicationContext();
        CharSequence text = message;
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

}
