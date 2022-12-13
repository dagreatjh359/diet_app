package com.example.diet_app;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {public class MyViewHolder extends RecyclerView.ViewHolder {
    TextView date;
    TextView address;

    MyViewHolder(View view){
        super(view);
        address = view.findViewById(R.id.address);
        date = view.findViewById(R.id.date);
    }
}
    // meal 객체들을 담을 수 있는 리스트
    private ArrayList<meal> mealList;
    MyAdapter(ArrayList<meal> meals){
        this.mealList = meals;
    }
    // 아래는 어댑터 클래스의 기본 기능이므로 오버라이드 해야 함. 리싸이클러의 각 행에 사용되는 레이아웃 xml 을 가져옴.
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, parent, false);
        return new MyViewHolder(v);
    }

    // 리싸이클러에 보이지는 이미지와 문장들을 설정함.
    @Override
    public void onBindViewHolder(MyAdapter.MyViewHolder holder, final int position) {
        MyViewHolder myViewHolder = (MyViewHolder) holder;

        String place = mealList.get(position).getPlace();
        myViewHolder.address.setText(place);
        String date = mealList.get(position).getDate_time();
        myViewHolder.date.setText(date);
        /*Uri input_uri = Uri.parse(mealList.get(position).getImage_uri1());
        myViewHolder.photo1.setImageURI(input_uri);*/
    }
    @Override
    public int getItemCount() {
        return mealList.size();
    }
}
