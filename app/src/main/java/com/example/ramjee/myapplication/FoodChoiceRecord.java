package com.example.ramjee.myapplication;

/**
 * Created by kcngan on 12/4/16.
 */
public class FoodChoiceRecord {
    public int getFoodID() {
        return foodID;
    }

    public FoodChoiceRecord(int foodID) {
        this.foodID = foodID;
    }

    public void setFoodID(int foodID) {
        this.foodID = foodID;
    }

    private int foodID;

}
