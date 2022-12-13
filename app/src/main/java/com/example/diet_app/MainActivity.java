package com.example.diet_app;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.lang.reflect.Array;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    Spinner spinner1;
    Spinner spinner2;
    Spinner spinner3;
    String v="";
    String v1="";
    String v2="";
    String date_search = "";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final String[] data1 = getResources().getStringArray(R.array.year);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, data1);
        Spinner spinner1 = (Spinner) findViewById(R.id.spinner1);
        spinner1.setAdapter(adapter1);
        String text = spinner1.getSelectedItem().toString();
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                v = adapter1.getItem(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {textView.setText("선택: ");}
        });

        final String[] data2 = getResources().getStringArray(R.array.month);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, data2);
        Spinner spinner2 = (Spinner) findViewById(R.id.spinner2);
        spinner2.setAdapter(adapter2);
        String text2 = spinner2.getSelectedItem().toString();
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                v1 = adapter2.getItem(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                textView.setText("선택: ");
            }
        });

        final String[] data3 = getResources().getStringArray(R.array.day);
        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, data3);
        Spinner spinner3 = (Spinner) findViewById(R.id.spinner3);
        spinner3.setAdapter(adapter3);
        String text3 = spinner3.getSelectedItem().toString();
        spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                v2 = adapter3.getItem(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                textView.setText("선택: ");
            }
        });

        date_search = text +". "+ text2 +". "+ text3;
        Button find = (Button) findViewById(R.id.button); //조회하기
        find.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(),List_from_date.class);
                intent.putExtra("date_search",date_search);
                startActivity(intent);
            }
        });

        Button button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(),ADD_FOOD.class);
                startActivity(intent);
            }
        });

    }
}