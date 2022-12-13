package com.example.diet_app;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.location.Geocoder;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Calendar;

public class ADD_FOOD extends AppCompatActivity {

    ImageView imageView1;
    ImageView imageView2;
    DatePickerDialog dateDialog;
    TextView dateText;
    String uri_photo1;
    String uri_photo2;
    TextView location;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adding_food);

        imageView1 = findViewById(R.id.img1);
        imageView2 = findViewById(R.id.img2);
        dateText = findViewById(R.id.date);
        location = findViewById(R.id.location);

        Button photo_add_Button = findViewById(R.id.photo_add);
        ImageButton add_location = findViewById(R.id.map_open);
        ImageButton add_date = findViewById(R.id.calender_open);
        Button add_confirm = findViewById(R.id.add_food);

        add_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addFoods(view);
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });

        photo_add_Button.setOnClickListener(new View.OnClickListener() {
            int btn_cnt;
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                if (btn_cnt == 0) {
                    startActivityForResult(intent, 0);
                    btn_cnt = btn_cnt + 1;
                } else {
                    startActivityForResult(intent, 1);
                    btn_cnt = 0;
                }
            }
        });
        add_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), location.class);
                startActivityForResult(intent, 2);
            }
        });
        add_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dateText.setText("");
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                dateDialog = new DatePickerDialog(ADD_FOOD.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int y, int m, int d) {
                                m = m + 1;
                                String date = y + ". " + m + ". " + d ;
                                dateText.setText(date);
                            }
                        }, year, month, day);
                dateDialog.show();
            }
        });
    }
    public void addFoods(View view) {
        ContentValues addValues = new ContentValues();
        addValues.put(MyContentProvider.date, ((TextView)findViewById(R.id.date_creation)).getText().toString());
        addValues.put(MyContentProvider.location, ((TextView)findViewById(R.id.location)).getText().toString());
        addValues.put(MyContentProvider.review, ((TextView)findViewById(R.id.review)).getText().toString());
        addValues.put(MyContentProvider.food1, ((EditText)findViewById(R.id.food1_name)).getText().toString());
        addValues.put(MyContentProvider.calorie1, ((EditText)findViewById(R.id.food1_calorie)).getText().toString());
        addValues.put(MyContentProvider.quantity1, ((EditText)findViewById(R.id.food1_quantity)).getText().toString());
        addValues.put(MyContentProvider.food2, ((EditText)findViewById(R.id.food2_name)).getText().toString());
        addValues.put(MyContentProvider.calorie2, ((EditText)findViewById(R.id.food2_calorie)).getText().toString());
        addValues.put(MyContentProvider.quantity2, ((EditText)findViewById(R.id.food2_quantity)).getText().toString());
        addValues.put(MyContentProvider.food3, ((EditText)findViewById(R.id.food3_name)).getText().toString());
        addValues.put(MyContentProvider.calorie3, ((EditText)findViewById(R.id.food3_calorie)).getText().toString());
        addValues.put(MyContentProvider.quantity3, ((EditText)findViewById(R.id.food3_quantity)).getText().toString());
        addValues.put(MyContentProvider.photo1, uri_photo1);
        addValues.put(MyContentProvider.photo2, uri_photo2);
        getContentResolver().insert(MyContentProvider.CONTENT_URI,
                addValues);
        Toast.makeText(getBaseContext(),
                "Record Added", Toast.LENGTH_LONG).show();
    }

    //Photo
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ContentValues addValues = new ContentValues();
        if (requestCode == 0) {
            if (resultCode == RESULT_OK) {
                assert data != null;
                Glide.with(getApplicationContext()).load(data.getData()).override(500, 500).into(imageView1);
                uri_photo1 = String.valueOf(data.getData());
                // 사진 uri 저장하기
            }
        } else if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                assert data != null;
                Glide.with(getApplicationContext()).load(data.getData()).override(500, 500).into(imageView2);
                uri_photo2 = String.valueOf(data.getData());
            }
        }else if(requestCode == 2){
            if (resultCode == RESULT_OK){
                assert data != null;
                location.setText(data.getStringExtra("location"));
            }
        }
    }
}





















