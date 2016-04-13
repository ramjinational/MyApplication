package com.example.ramjee.myapplication;

/**
 * Created by Kent on 8/1/2016.
 */
public class FoodRecord {
    private int foodid;
    private int foodcalories;
    private String foodName;

    public FoodRecord(String time, int foodcalories) {
        this.foodName = time;
        this.foodcalories = foodcalories;
    }

    public int getfoodcalories() {
        return foodcalories;
    }

    public String getFoodName() {
        return foodName;
    }

}
