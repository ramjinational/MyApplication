package com.example.ramjee.myapplication;

/**
 * Created by Kent on 8/1/2016.
 */
public class ExerciseRecord {
    private double exercisecalories;
    private String exerciseName;

    public ExerciseRecord(String time, double exercisecalories) {
        this.exerciseName = time;
        this.exercisecalories = exercisecalories;
    }

    public double getExercisecalories() {
        return exercisecalories;
    }

    public String getExerciseName() {
        return exerciseName;
    }

}