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

public class MainActivity extends AppCompatActivity {

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
            Intent calcCompAverage = new Intent(MainActivity.this, CalcCompAverage.class);
            startActivity(calcCompAverage);
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
}
