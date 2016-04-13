package com.example.ramjee.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kent on 14/1/2016.
 */

public class FoodRecordActivity extends Activity {
    private RequestJSONData reqJSON = new RequestJSONData();
    List<FoodRecord> foodRecords = new ArrayList<>();
    DataSourceManager db = new DataSourceManager(this);
    private static final int padding = 100;
    private ArrayAdapter<String> adapter = null;

    private Handler handler = new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        //Code Segment for retrieving data from remote server to SQLite db


        List<FoodRecord> list = db.getAllFoodRecords();
        List<String> results = new ArrayList<String>();
        if (list.size() == 0) {
            Toast.makeText(this, "Cannot find Records.",
                    Toast.LENGTH_LONG).show();
        } else {
            for (FoodRecord gr : list) {
                results.add("" + gr.getFoodName() + "\n" + gr.getfoodcalories() + ".");
            }
        }

        if (results.size() == 0) {
            TextView content = new TextView(this);
            content.setText("No Record is found.");
            content.setPadding(padding, padding, padding, padding);
            setContentView(content);
        } else {
            setContentView(R.layout.foodmenu);
            adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1,
                    results);
            ListView listView = (ListView) findViewById(R.id.foodmenu_list);
            listView.setAdapter(adapter);
            EditText inputSearch = (EditText) findViewById(R.id.editText);
            inputSearch.addTextChangedListener(new TextWatcher() {

                @Override
                public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                    // When user changed the Text
                    FoodRecordActivity.this.adapter.getFilter().filter(cs);
                }

                @Override
                public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                              int arg3) {
                    // TODO Auto-generated method stub

                }

                @Override
                public void afterTextChanged(Editable arg0) {
                    // TODO Auto-generated method stub
                }
            });

        }




    }

    public void onBackPressed() {
        Intent intent = new Intent(FoodRecordActivity.this, MainActivity.class);
        startActivity(intent);
        this.finish();
    }




}



