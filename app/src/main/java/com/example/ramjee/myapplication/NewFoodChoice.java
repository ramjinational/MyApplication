package com.example.kcngan.fitnessassistant;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kcngan on 12/4/16.
 */
public class NewFoodChoice extends Activity {
    private RequestJSONData reqJSON = new RequestJSONData();
    List<FoodRecord> foodRecords = new ArrayList<>();
    DataSourceManager db = new DataSourceManager(this);

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newfood);


    }

    protected void onStart() {
        super.onStart();
        final EditText foodN = (EditText) findViewById(R.id.foodnf);
        final EditText cal = (EditText) findViewById(R.id.calf);

        Button login = (Button) findViewById(R.id.sharebtn);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Runnable run = new Runnable() {
                    @Override
                    public void run() {
                        if (isNetworkConnected()) {
                            try {
                                addFoodInfo(foodN.getText().toString(), cal.getText().toString());

                            } catch (Exception e) {

                            }
                        } else {
                            new Thread() {
                                public void run() {
                                    Log.i("log", "run");
                                    Looper.prepare();
                                    Toast.makeText(getApplicationContext(), "Connection fail", Toast.LENGTH_SHORT).show();
                                    Looper.loop();
                                };
                            }.start();

                        }

                        showProfileActivity();
                    }
                };
                Thread th = new Thread(run);
                th.start();


            }
        });

    }

    public void showProfileActivity() {
        Intent intent = new Intent(NewFoodChoice.this, ProfileActivity.class);
        startActivity(intent);
        this.finish();
    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null;
    }

    public void addFoodInfo(String fn, String ca) {
        reqJSON.getResponse("http://fitman.eu5.org/MComp/AddFood.php?name=" + fn + "&cal=" + ca);
    }
    public void rertiveAddFoodRecord() {
        reqJSON.getResponse("http://fitman.eu5.org/MComp/FoodList.php");
        reqJSON.parseJSON("FT");
        foodRecords = reqJSON.getFoodList();
        addFoodRecords();
    }
    public void addFoodRecords() {
        db.deleteAllFoodRecords();
        for (FoodRecord f : foodRecords) {
            Log.d("AddFoodRecord", f.toString());
            db.addRecord(f);
        }
    }
}
