package com.unisinos.m2.mateusmanica.calculadoradecombustivel;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private AdapterView.OnItemClickListener itemClickHandler = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (id == 0){
            Intent typeOfFuelAct = new Intent(MainActivity.this, typeOfFuelAct.class);
            startActivity(typeOfFuelAct);
        }else if (id == 1){
            Intent calcSimpleAverage = new Intent(MainActivity.this, calcSimpleAverage.class);
            startActivity(calcSimpleAverage);
        }else if (id == 2){

        }else if (id == 3){

        }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Create the adapter with the strings from the resources(strings.xml)
        ArrayAdapter<String> adapterFeatures = new ArrayAdapter<String>(
                this,                                                           //context
                R.layout.support_simple_spinner_dropdown_item,                  //layout
                getResources().getStringArray(R.array.list_of_features));//String[]
        ListView listOfFeatures = ((ListView)findViewById(R.id.featuresList));
        listOfFeatures.setAdapter(adapterFeatures);
        listOfFeatures.setOnItemClickListener(itemClickHandler);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
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
