package com.unisinos.m2.mateusmanica.calculadoradecombustivel;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button mPergCombBt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPergCombBt = (Button)findViewById(R.id.pergCombBt);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.pergCombBt:
                //Get the value of Gasoline and Etanol prices and performance
                String precoGasNum   = ((EditText)findViewById(R.id.precoGasNum)).getText().toString();
                String precoEtaNum   = ((EditText)findViewById(R.id.precoEtaNum)).getText().toString();
                String consumoGasNum = ((EditText)findViewById(R.id.consumoGasNum)).getText().toString();
                String consumoEtaNum = ((EditText)findViewById(R.id.consumoEtaNum)).getText().toString();

                //In case the fields are initial an exception is raised, so just let's notify the user
                //Toast notification
                if (precoEtaNum.equals("") || precoGasNum.equals("") ||
                        consumoEtaNum.equals("") || consumoGasNum.equals("")) {
                    launchToast("Preencha os campos acima");
                    return;
                }
                //convert to double
                double precoGas = Double.valueOf(precoGasNum);
                double precoEta = Double.valueOf(precoEtaNum);
                double consumoGas = Double.valueOf(consumoEtaNum);
                double consumoEta = Double.valueOf(consumoGasNum);
                //Check the 70% rule or the if the price rate reaches the performance rate
                if ( (precoEta / precoGas <= 0.7) || ( precoEta / precoGas <= consumoEta / consumoGas)) {
                    //Etanol is more expensive than 70% of gasoline price, or the rate given by the
                    // performance calc. It does not worth the investment
                    ((TextView) findViewById(R.id.respCombTxt)).setText("Etanol");
                }else {
                    ((TextView) findViewById(R.id.respCombTxt)).setText("Gasolina");
                }

                return;
            case R.id.pergMedSimBt:
                //Get the value for run kilometers and liters filled up in tank
                String kmRodadoStr = ((EditText)findViewById(R.id.kmRodadoNum)).getText().toString();
                String litrosStr   = ((EditText)findViewById(R.id.litroAbastNum)).getText().toString();

                //In case the fields are initial an exception is raised, so just let's notify the user
                //Toast notification
                if (kmRodadoStr.equals("") || litrosStr.equals("")) {
                    launchToast("Preencha os campos Km rodados e Litros abastecidos");
                    return;
                }
                double kmRodado  = Integer.valueOf(kmRodadoStr);
                double litros = Double.valueOf(litrosStr);

                //Calculate the simple average
                double simpleAverage = kmRodado / litros;

                ((TextView)findViewById(R.id.respMedSimNum)).setText(simpleAverage + " km/l");

                return;
            case R.id.pergMedCompBt:
                //Get the value for liters in tank, initial and final kilometers
                String kmInitialStr = ((EditText)findViewById(R.id.kmInicialNum)).getText().toString();
                String kmFinalStr   = ((EditText)findViewById(R.id.kmFinalNum)).getText().toString();
                String liters2Str   = ((EditText)findViewById(R.id.litrosAbastNum2)).getText().toString();

                //Check if the fields are filled
                if (kmInitialStr.equals("") || kmFinalStr.equals("") || liters2Str.equals("")){
                    launchToast("Preencha: Km inicial, Km Final e Litros");
                    return;
                }
                double kmInitial = Double.valueOf(kmInitialStr);
                double kmFinal   = Double.valueOf(kmFinalStr);
                double liters2   = Double.valueOf(liters2Str);

                double completeAverage = (kmFinal - kmInitial) / liters2;

                ((TextView)findViewById(R.id.respCompMedTxt)).setText(completeAverage + " km/l");

                return;
            case R.id.pergPrecoTrechoBt:
                //Get the value for consumption, price of fuel and distance to be run
                String consumStr   = ((EditText)findViewById(R.id.consumoVeiNum)).getText().toString();
                String priceStr    = ((EditText)findViewById(R.id.precoLitro)).getText().toString();
                String distanceStr = ((EditText)findViewById(R.id.distanciaAlvoNum)).getText().toString();

                //Check if the fields are filled
                if (consumStr.equals("") || priceStr.equals("") || distanceStr.equals("")){
                    launchToast("Preencha: distancia a percorrer, consumo do veículo e preço do combustível");
                    return;
                }
                Double consumNum   = Double.valueOf(consumStr);
                Double priceNum    = Double.valueOf(priceStr);
                Double distanceNum = Double.valueOf(distanceStr);

                double finalPrice = distanceNum / consumNum * priceNum;
                ((TextView)findViewById(R.id.distanciaAlvoNum)).setText("R$" + finalPrice);
        }

    }

    private void launchToast(String message){
        Context context = getApplicationContext();
        CharSequence text = message;
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }
}
