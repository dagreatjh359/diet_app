package com.example.diet_app;

import android.net.Uri;

import java.util.ArrayList;

public class meal {
    // meal input date -> calender
    String _id;
    String date_time;
    // meal Place -> google map
    String Place;
    // meal image
    String image_uri1;
    String image_uri2;
    //food list
    ArrayList<String> food_list;
    //food calorie list
    ArrayList<Integer> calorie_list;
    ArrayList<Integer> food_qnt_list;
    // meal review
    String review;
    // Foods
    public meal(String _id, String date_time,String Place, String image_uri1, String image_uri2, String review, ArrayList<String> food_list, ArrayList<Integer> calorie_list, ArrayList<Integer> food_qnt_list) {
        this._id = _id;
        this.date_time = date_time;
        this.Place = Place;
        this.image_uri1 = image_uri1;
        this.image_uri2 = image_uri2;
        this.review = review;
        this.food_list = food_list;
        this.calorie_list = calorie_list;
        this.food_qnt_list = food_qnt_list;
    }
    public String get_id() {return _id;}
    public String getDate_time(){
        return date_time;
    }
    public String getPlace(){
        return Place;
    }
    public String getImage_uri1(){
        return image_uri1;
    }
    public String getImage_uri2(){
        return image_uri2;
    }
    public ArrayList<String> getFood_list(){
        return food_list;
    }
    public ArrayList<Integer> getCalorie_list(){
        return calorie_list;
    }
    public ArrayList<Integer> getFood_qnt_list(){
        return food_qnt_list;
    }
    public String getReview(){
        return review;
    }
}
