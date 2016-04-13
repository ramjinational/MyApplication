package com.example.ramjee.myapplication;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RequestJSONData reqJSON = new RequestJSONData();
    List<LoginRecord> loginRecords = new ArrayList<>();
    List<FoodRecord> foodRecords = new ArrayList<>();
    List<ExerciseRecord> exerRecords = new ArrayList<>();
    DataSourceManager db = new DataSourceManager(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);


    }

    protected void onStart() {
        super.onStart();
        Button mainLogin = (Button) findViewById(R.id.mainLogin);
        Button mainRegister = (Button) findViewById(R.id.mainRegister);

        if (mainLogin != null) {
            mainLogin.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    loginRecords = db.getAllLoginRecords();
                    if (loginRecords.size() == 1) {
                        Runnable run = new Runnable() {
                            @Override
                            public void run() {
                                if (isNetworkConnected()) {
                                    rertiveLoginInfo(loginRecords.get(0).getUsername(), loginRecords.get(0).getPassword());
                                }
                            }
                        };
                        Thread th = new Thread(run);
                        th.start();
                        showProfileActivity();
                    } else {
                        showLoginActivity();
                    }
                }
            });
        }

        if (mainRegister != null) {
            mainRegister.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                        showRegisterActivity();
                }
            });
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        if (fab != null) {
            fab.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    Snackbar.make(view, "Browse Food Record", Snackbar.LENGTH_LONG)
                            .setAction("Go", new OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Runnable run = new Runnable() {
                                        @Override
                                        public void run() {
                                            if (isNetworkConnected()) {
                                                rertiveAddFoodRecord();
                                                //rertiveAddExerciseRecord();
                                            }
                                            showFoodRecord();
                                        }
                                    };
                                    Thread th = new Thread(run);
                                    th.start();

                                }
                            }).show();
                }
            });
        }


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatementFoodRecord
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void rertiveLoginInfo(String un, String pw) {
        reqJSON.getResponse("http://fitman.eu5.org/MComp/Login.php?name=" + un + "&pw=" + pw);
        reqJSON.parseJSON("LI");
        loginRecords.add(reqJSON.getLoginRecord());
        addLoginRecords();
    }

    public void addLoginRecords() {
        db.deleteAllLoginRecords();
        for (LoginRecord f : loginRecords) {
            Log.d("AddLoginRecord", f.toString());
            db.addLoginRecord(f);
        }
    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null;
    }

    private void showLoginActivity() {
        //1. Create a new intent instance.
        //2. Start  a new activity with the created intent.
        //3. Stop current activity.
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
        this.finish();
    }

    private void showRegisterActivity() {
        //1. Create a new intent instance.
        //2. Start  a new activity with the created intent.
        //3. Stop current activity.
        Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
        startActivity(intent);
        this.finish();
    }

    private void showFoodRecord() {
        //1. Create a new intent instance.
        //2. Start  a new activity with the created intent.
        //3. Stop current activity.
        Intent intent = new Intent(MainActivity.this, FoodRecordActivity.class);
        startActivity(intent);
        this.finish();
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
    public void rertiveAddExerciseRecord() {
        reqJSON.getResponse("http://fitman.eu5.org/MComp/ExerciseList.php");
        reqJSON.parseJSON("EX");
        exerRecords = reqJSON.getExerciseList();
        addExerciseRecords();
    }
    public void addExerciseRecords() {
        db.deleteAllExerRecords();
        for (ExerciseRecord f : exerRecords) {
            Log.d("AddExerciseRecord", f.toString());
            db.addExerRecord(f);
        }
    }
    public void showProfileActivity() {
        Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
        startActivity(intent);
        this.finish();
    }
}
