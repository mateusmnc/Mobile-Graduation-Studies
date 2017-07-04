package com.unisinos.m2.mateusmanica.listviewprototype;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] arrayOfOptions = getResources().getStringArray(R.array.list_of_func);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, arrayOfOptions);

        ListView listOfFunctionalities = ((ListView)findViewById(R.id.listAppFunctionalities));
        listOfFunctionalities.setAdapter(adapter);
        listOfFunctionalities.setOnItemClickListener(clickHandler);
    }

    private AdapterView.OnItemClickListener clickHandler = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent appFunctionality = new Intent(MainActivity.this, QualCombustivel.class);
            startActivity(appFunctionality);
        }
    };
}
