package com.example.ramjee.myapplication;

/**
 * Created by kcngan on 12/4/16.
 */
public class ExerciseChoiceRecord {
    public int getExerciseID() {
        return exerciseID;
    }

    public void setExerciseID(int exerciseID) {
        this.exerciseID = exerciseID;
    }

    private int exerciseID;

    public ExerciseChoiceRecord(int exerciseID) {
        this.exerciseID = exerciseID;
    }
}