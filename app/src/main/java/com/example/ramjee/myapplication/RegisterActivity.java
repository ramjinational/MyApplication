package com.example.ramjee.myapplication;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kcngan on 11/4/16.
 */
public class RegisterActivity extends AppCompatActivity {
    private RequestJSONData reqJSON = new RequestJSONData();
    List<FoodRecord> foodRecords = new ArrayList<>();
    LoginRecord loginR;
    DataSourceManager db = new DataSourceManager(this);
//    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getApplicationContext());
//    AlertDialog alertDialog;


    //EditText userN = (EditText) findViewById(R.id.editText2);
    //EditText pw = (EditText) findViewById(R.id.editText3);

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
    }

    protected void onStart() {
        super.onStart();
        final EditText userN = (EditText) findViewById(R.id.userName);
        final EditText pw = (EditText) findViewById(R.id.password);
        final EditText email = (EditText) findViewById(R.id.email);
        final EditText height = (EditText) findViewById(R.id.height);
        final EditText weight = (EditText) findViewById(R.id.weight);
        final EditText sex = (EditText) findViewById(R.id.sex);
        final EditText DOB = (EditText) findViewById(R.id.DOB);

        Button register = (Button) findViewById(R.id.register);
        if (register != null) {
            register.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Runnable run = new Runnable() {
                        @Override
                        public void run() {
                            if (height.getText().toString().equals("")&&weight.getText().toString().equals("")) {
                                //alertDialogBuilder.setMessage("Please enter your details");
                                //alertDialog = alertDialogBuilder.create();
                                //alertDialog.show();
                                System.out.println("Input details not entered");
                            }else {
                                loginR = new LoginRecord(
                                        userN.getText().toString() + "_id",
                                        userN.getText().toString(),
                                        pw.getText().toString(),
                                        email.getText().toString(),
                                        Double.parseDouble(height.getText().toString()),
                                        Double.parseDouble(weight.getText().toString()),
                                        sex.getText().toString(),
                                        DOB.getText().toString());
                            }
                            if (loginR!=null){
                                db.addLoginRecord(loginR);
                                showLoginActivity();
                            }
                        }
                    };
                    Thread th = new Thread(run);
                    th.start();

                }
            });
        }

    }

    public void onBackPressed() {
        Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
        startActivity(intent);
        this.finish();
    }

    private void showLoginActivity() {
        //1. Create a new intent instance.
        //2. Start  a new activity with the created intent.
        //3. Stop current activity.
        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(intent);
        this.finish();
    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null;
    }
}
