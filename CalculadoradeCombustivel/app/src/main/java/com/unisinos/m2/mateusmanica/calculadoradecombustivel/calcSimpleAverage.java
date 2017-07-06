package com.unisinos.m2.mateusmanica.calculadoradecombustivel;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class calcSimpleAverage extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc_simple_average);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.calcSimAvgBt:
                calculate();
                return;
            case R.id.newCalcSABt:
                clearFields();
                return;
            case R.id.backCalcSABt:
                finish();
        }
    }

    private void calculate() {
        //Get the value for run kilometers and liters filled up in tank
        String runKm      = ((EditText)findViewById(R.id.runKmNum)).getText().toString();
        String filledTank = ((EditText)findViewById(R.id.filledTankNum)).getText().toString();

        //In case the fields are initial an exception is raised, so just let's notify the user
        //Toast notification
        if (runKm.equals("") || filledTank.equals("")) {
            launchToast("Preencha os campos Km rodados e Litros abastecidos");
            return;
        }
        double runKmNum      = Double.valueOf(runKm);
        double filledTankNum = Double.valueOf(filledTank);

        //Calculate the simple average
        double simpleAverage = runKmNum / filledTankNum;

        ((TextView)findViewById(R.id.ansCSATxt)).setText(simpleAverage + " km/l");
    }

    private void clearFields() {
        ((EditText)findViewById(R.id.runKmNum)).setText("");
        ((EditText)findViewById(R.id.filledTankNum)).setText("");
    }

    private void launchToast(String message){
        Context context = getApplicationContext();
        CharSequence text = message;
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }
}
