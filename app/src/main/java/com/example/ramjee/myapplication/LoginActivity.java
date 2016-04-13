package com.example.ramjee.myapplication;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kcngan on 11/4/16.
 */
public class LoginActivity extends AppCompatActivity {
    private RequestJSONData reqJSON = new RequestJSONData();
    List<FoodRecord> foodRecords = new ArrayList<>();
    List<LoginRecord> loginR =new ArrayList<>();
    DataSourceManager db = new DataSourceManager(this);


    //EditText userN = (EditText) findViewById(R.id.editText2);
    //EditText pw = (EditText) findViewById(R.id.editText3);

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
    }

    protected void onStart() {
        super.onStart();
        final EditText userN = (EditText) findViewById(R.id.editText2);
        final EditText pw = (EditText) findViewById(R.id.editText3);

        Button login = (Button) findViewById(R.id.button);
        if (login != null) {
            login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Runnable run = new Runnable() {
                        @Override
                        public void run() {
                            if (isNetworkConnected()) {
                                try {
                                    if (userN.getText().toString().equals("") && pw.getText().toString().equals("")){
                                        System.out.println("No credentials");
                                    } else {
                                        retrieveLoginInfo(userN.getText().toString(), pw.getText().toString());
                                    }
                                } catch (Exception e) {

                                }
                            }
                            loginR = db.getAllLoginRecords();
                            if (loginR.size() == 1) {
                                showProfileActivity();
                            }
                        }
                    };
                    Thread th = new Thread(run);
                    th.start();

                }
            });
        }

    }


    public void retrieveLoginInfo(String un, String pw) {
        reqJSON.getResponse("http://fitman.eu5.org/MComp/Login.php?name=" + un + "&pw=" + pw);
        reqJSON.parseJSON("LI");
        loginR.clear();
        loginR.add(reqJSON.getLoginRecord());
        Log.d("XX", "" + loginR.size());
        addLoginRecords();
    }

    private void showFoodRecord() {
        //1. Create a new intent instance.
        //2. Start  a new activity with the created intent.
        //3. Stop current activity.
        Intent intent = new Intent(LoginActivity.this, FoodRecordActivity.class);
        startActivity(intent);
        this.finish();
    }

    public void onBackPressed() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        this.finish();
    }

    public void showProfileActivity() {
        Intent intent = new Intent(LoginActivity.this, ProfileActivity.class);
        startActivity(intent);
        this.finish();
    }



    public void addLoginRecords() {
        db.deleteAllLoginRecords();
        Log.d("AddingLoginRecord", "");
        for (LoginRecord f : loginR) {
            Log.d("AddLoginRecord", f.toString());
            db.addLoginRecord(f);
        }
    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null;
    }
}
