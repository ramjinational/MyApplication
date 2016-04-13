package com.example.ramjee.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kcngan on 11/4/16.
 */
public class ProfileActivity extends Activity{
    private RequestJSONData reqJSON = new RequestJSONData();
    DataSourceManager db = new DataSourceManager(this);
    List<LoginRecord> loginR = new ArrayList<>();
    List<FoodChoiceRecord> foodChRecords=new ArrayList<>();
    List<ExerciseChoiceRecord> exerChRecords=new ArrayList<>();
    List<FoodRecord> pfoodRecords=new ArrayList<>();
    List<ExerciseRecord> pexerRecords=new ArrayList<>();
    String LoginID="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);
        TextView tv = (TextView) findViewById(R.id.textView7);
        loginR=db.getAllLoginRecords();

        LoginID=loginR.get(0).getUserid();

        tv.setText("My Name:\n" + loginR.get(0).getUsername() + "("+loginR.get(0).getUserid()+")\n" +
                        "My BMI:\n" + loginR.get(0).getBMI() + "\n" +
                        "Sex:\n" + loginR.get(0).getSex() + "\n" +
                        "Height\n" + loginR.get(0).getHeight() + "\n" +
                        "Weight\n" + loginR.get(0).getWeight()
        );



    }
    protected void onStart() {
        super.onStart();
        Button b = (Button) findViewById(R.id.button3);
        Button update = (Button) findViewById(R.id.profileUpdate);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Runnable run = new Runnable() {
                    @Override
                    public void run() {
                        if (isNetworkConnected()) {
                            //rertiveAddExerciseChRecord(LoginID);
                            rertiveAddFoodChRecord(LoginID);
                        }
                        showFoodExerActivity();
                    }
                };
                Thread th = new Thread(run);
                th.start();

            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Runnable run = new Runnable() {
                    @Override
                    public void run() {
                        showUpdateUserActivity();
                    }
                };
                Thread th = new Thread(run);
                th.start();

            }
        });

    }



    public void rertiveAddExerciseChRecord(String arg) {
        reqJSON.getResponse("http://fitman.eu5.org/MComp/ExerciseChoice.php?name=" + arg);
        reqJSON.parseJSON("EXCH");
        exerChRecords = reqJSON.getExerciseChList();
        addExerciseChRecords();
    }
    public void rertiveAddFoodChRecord(String arg) {
        reqJSON.getResponse("http://fitman.eu5.org/MComp/FoodChoice.php?name="+arg);
        reqJSON.parseJSON("FDCH");
        foodChRecords = reqJSON.getFoodChList();
        addFoodChRecords();
    }
    public void addExerciseChRecords() {
        db.deleteAllExerChRecords();
        for (ExerciseChoiceRecord f : exerChRecords) {
            Log.d("AddExerciseChRecord", f.toString());
            db.addExerChRecord(f);
        }
    }
    public void addFoodChRecords() {
        db.deleteAllFoodChRecords();
        for (FoodChoiceRecord f : foodChRecords) {
            Log.d("AddFoodChRecord", f.toString());
            db.addFoodChRecord(f);
        }
    }
    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null;
    }
    public void showFoodExerActivity() {
        Intent intent = new Intent(ProfileActivity.this, FoodExerChActivity.class);
        startActivity(intent);
        this.finish();
    }

    public void showUpdateUserActivity() {
        Intent intent = new Intent(ProfileActivity.this, UpdateActivity.class);
        startActivity(intent);
        this.finish();
    }
}
