package com.unisinos.m2.mateusmanica.calculadoradecombustivel;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
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
        if (v.getId() == R.id.pergCombBt){
            //Get the value of Gasoline and Etanol prices and performance
            double precoGasNum = Double.valueOf(((EditText)findViewById(R.id.precoGasNum)).getText().toString());
            double precoEtaNum = Double.valueOf(((EditText)findViewById(R.id.precoEtaNum)).getText().toString());
            double consumoGas = Double.valueOf(((EditText)findViewById(R.id.consumoGasNum)).getText().toString());
            double consumoEta = Double.valueOf(((EditText)findViewById(R.id.consumoEtaNum)).getText().toString());

            //Check if the fields were filled
            if (precoEtaNum == 0.00 || precoGasNum == 0.00) {
                //Toast notification
                Context context = getApplicationContext();
                CharSequence text = "Preencha os campos";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
                return;
            }
            //Check the 70% rule or the if the price rate reaches the performance rate
            if ( (precoEtaNum / precoGasNum <= 0.7) || ( precoEtaNum / precoGasNum <= consumoEta / consumoGas)) {
                //Etanol is more expensive than 70% of gasoline price, or the rate given by the
                // performance calc. It does not worth the investment
                ((TextView) findViewById(R.id.respCombTxt)).setText("Gasolina");
            }else {
                ((TextView) findViewById(R.id.respCombTxt)).setText("Etanol");
            }

            return;
        }
    }
}
