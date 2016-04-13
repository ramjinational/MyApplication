package com.example.ramjee.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kcngan on 12/4/16.
 */
public class FoodExerChActivity extends Activity {
    DataSourceManager db = new DataSourceManager(this);
    List<FoodChoiceRecord> foodChRecords = new ArrayList<>();
    List<ExerciseChoiceRecord> exerChRecords = new ArrayList<>();
    List<FoodRecord> pfoodRecords = new ArrayList<>();
    List<ExerciseRecord> pexerRecords = new ArrayList<>();
    List<String> result1 = new ArrayList<>();
    List<String> result2 = new ArrayList<>();

    private ArrayAdapter<String> adapter = null;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.foodexer);

        exerChRecords = db.getAllExerChRecords();
        foodChRecords = db.getAllFoodChRecords();
        //pfoodRecords = db.getAllFoodRecords();
        //pexerRecords = db.getAllExerRecords();

        for (FoodChoiceRecord f : foodChRecords) {
            Log.d("f.getFoodID()",f.getFoodID() + "");
            pfoodRecords.add(db.getOneFoodRecord(f.getFoodID() + ""));
        }

        /*for (ExerciseChoiceRecord f : exerChRecords) {
            Log.d("f.getExerciseID() ", f.getExerciseID() + "");
            ExerciseRecord e = db.getOneExerRecord(f.getExerciseID() + "");
            Log.d("e",e.getExerciseName()+" and "+e.getExercisecalories());
            pexerRecords.add(db.getOneExerRecord(f.getExerciseID() + ""));
        }*/
        Log.d("size",pfoodRecords.size()+" ");

        for(FoodRecord f:pfoodRecords) {
            Log.d("pfoodRecords",f.getFoodName()+"\n"+f.getfoodcalories()+"");
            result1.add(f.getFoodName()+"\n"+f.getfoodcalories());
        }
        /*for(ExerciseRecord f:pexerRecords) {
            Log.d("pexerRecords", f.getExerciseName() + "\n" + f.getExercisecalories() + "");
            result2.add(f.getExerciseName() + "\n" + f.getExercisecalories());
        }*/


        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                result1);
        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);
        /*adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                result2);
        ListView listView2 = (ListView) findViewById(R.id.listView2);
        listView2.setAdapter(adapter);*/
    }

}
