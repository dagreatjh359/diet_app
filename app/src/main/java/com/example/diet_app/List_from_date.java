package com.example.diet_app;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class List_from_date extends AppCompatActivity {

    String date_search = "";
    String[] data = {""};
    private RecyclerView myRecyclerView;
    private RecyclerView.LayoutManager myLayoutManager;
    ArrayList<meal> mealInfo = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_view);

        myRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        myRecyclerView.setHasFixedSize(true);
        myLayoutManager = new LinearLayoutManager(this);
        myRecyclerView.setLayoutManager(myLayoutManager);

        Intent intent_from_main = getIntent();
        date_search = intent_from_main.getStringExtra("date_search");

        TextView date_title = findViewById(R.id.textView4);
        date_title.setText(date_search);

        getFoods();

        MyAdapter myAdapter = new MyAdapter(mealInfo);
        myRecyclerView.setAdapter(myAdapter);
    }
    private static String[] Add(String[] originArray, String Val) {
        String[] newArray = new String[originArray.length + 1];

        System.arraycopy(originArray, 0, newArray, 0, originArray.length);

        newArray[originArray.length] = Val;
        return newArray;
    }

    public void getFoods(){
        String[] columns = new String[]{"_id", "location", "photo1"};
        Cursor c = getContentResolver().query(MyContentProvider.CONTENT_URI, columns,
                null, null,null); //"date = "+"'"+date_search+"'"

        if(c != null){
            while(c.moveToNext()){
                String row = c.getString(0)+","+c.getString(1)+","+c.getString(2);
                data = Add(data, row);
                int i=0;
                while (i<data.length){
                    String _id = c.getString(0);
                    String place = c.getString(1);
                    mealInfo.add(new meal(_id,date_search, place, null, null,
                            null, null, null, null));
                    i++;
                }
            }
            c.close();
        }
    }
}