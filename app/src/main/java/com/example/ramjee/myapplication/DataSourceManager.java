package com.example.ramjee.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Kent on 15/1/2016.
 */
public class DataSourceManager {
    SQLiteOpenHelper dbHelper;
    SQLiteDatabase db;
    private Context context;

    public DataSourceManager(Context c) {
        context = c;
        dbHelper = new MySQLiteHelper(context);
    }

    public void open() throws SQLException{
        db = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public void addRecord(FoodRecord record) {
        // 1. Open a database.
        open();
        // 2. create ContentValues to add key (column) and value
        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.getKeyFdNme(), record.getFoodName());
        values.put(MySQLiteHelper.getKeyCalories(), record.getfoodcalories());
        // 3. insert the record into database
        db.insert(MySQLiteHelper.getFdTableName(), null, values);
        // 4. close a database.
        close();
    }

    public void addExerRecord(ExerciseRecord record) {
        // 1. Open a database.
        open();
        // 2. create ContentValues to add key (column) and value
        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.getKEY_ExerNme(), record.getExerciseName());
        values.put(MySQLiteHelper.getKEY_expcal(), record.getExercisecalories());
        // 3. insert the record into database
        db.insert(MySQLiteHelper.getExertableName(), null, values);
        // 4. close a database.
        close();
    }

    public void addExerChRecord(ExerciseChoiceRecord record) {
        // 1. Open a database.
        open();
        // 2. create ContentValues to add key (column) and value
        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.getKeyEid(), record.getExerciseID());
        // 3. insert the record into database
        db.insert(MySQLiteHelper.getExchtableName(), null, values);
        // 4. close a database.
        close();
    }

    public void addFoodChRecord(FoodChoiceRecord record) {
        // 1. Open a database.
        open();
        // 2. create ContentValues to add key (column) and value
        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.getKeyId(), record.getFoodID());
        // 3. insert the record into database
        db.insert(MySQLiteHelper.getFoodchtableName(), null, values);
        // 4. close a database.
        close();
    }

    public void addLoginRecord(LoginRecord record) {
        // 1. Open a database.
        open();
        // 2. create ContentValues to add key (column) and value
        ContentValues values = new ContentValues();
        /*values.put(MySQLiteHelper.getKeyUid(), record.getUserid());
        values.put(MySQLiteHelper.getKEY_UserN(), record.getUsername());
        values.put(MySQLiteHelper.getKeyPw(), record.getPassword());
        values.put(MySQLiteHelper.getKeyem(), record.getEmail());
        values.put(MySQLiteHelper.getKEY_height(), (float) record.getHeight());
        values.put(MySQLiteHelper.getKeywt(), (float) record.getWeight());
        values.put(MySQLiteHelper.getKeyBmi(), (float) record.getBMI());
        values.put(MySQLiteHelper.getKEY_BMIs(), record.getBMIstatus());
        values.put(MySQLiteHelper.getKEY_Sex(), record.getSex());
        values.put(MySQLiteHelper.getKEY_BMIs(), record.getBMIstatus());
        values.put(MySQLiteHelper.getKEY_dob(), record.getDob());*/
        values.put("userID", record.getUserid());
        values.put("userName", record.getUsername());
        values.put("password", record.getPassword());
        values.put("email", record.getEmail());
        values.put("height", (float) record.getHeight());
        values.put("weight", (float) record.getWeight());
        values.put("BMI", (float) record.getBMI());
        values.put("BMI_Status", record.getBMIstatus());
        values.put("sex", record.getSex());
        values.put("DOB", record.getDob());

        // 3. insert the record into database
        System.out.println(""+MySQLiteHelper.getLoginName()+"____"+values.toString());
        //System.out.println("\""+MySQLiteHelper.getKEY_UserN().toString()+"\"");
        System.out.println(""+record.getUserid());
        System.out.println(""+record.getUsername());
        System.out.println(""+record.getPassword());
        System.out.println(""+record.getEmail());
        System.out.println(""+(float)record.getHeight());
        System.out.println(""+(float)record.getWeight());
        System.out.println(""+(float)record.getBMI());
        System.out.println(""+record.getBMIstatus());
        System.out.println(""+record.getSex());
        System.out.println(""+record.getDob());
        //db.insert(MySQLiteHelper.getLoginName(), null, values);
        if (db.isOpen())
        db.insert("user", null, values);
        else
        System.out.println("DB not available");
        // 4. close a database.
        close();
    }

    public void updateLoginRecord(LoginRecord record) {
        // 1. Open a database.
        open();
        // 2. create ContentValues to add key (column) and value
        ContentValues values = new ContentValues();
        /*values.put(MySQLiteHelper.getKeyUid(), record.getUserid());
        values.put(MySQLiteHelper.getKEY_UserN(), record.getUsername());
        values.put(MySQLiteHelper.getKeyPw(), record.getPassword());
        values.put(MySQLiteHelper.getKeyem(), record.getEmail());
        values.put(MySQLiteHelper.getKEY_height(), (float) record.getHeight());
        values.put(MySQLiteHelper.getKeywt(), (float) record.getWeight());
        values.put(MySQLiteHelper.getKeyBmi(), (float) record.getBMI());
        values.put(MySQLiteHelper.getKEY_BMIs(), record.getBMIstatus());
        values.put(MySQLiteHelper.getKEY_Sex(), record.getSex());
        values.put(MySQLiteHelper.getKEY_BMIs(), record.getBMIstatus());
        values.put(MySQLiteHelper.getKEY_dob(), record.getDob());*/
        values.put("userID", record.getUserid());
        values.put("userName", record.getUsername());
        values.put("password", record.getPassword());
        values.put("email", record.getEmail());
        values.put("height", (float) record.getHeight());
        values.put("weight", (float) record.getWeight());
        values.put("BMI", (float) record.getBMI());
        values.put("BMI_Status", record.getBMIstatus());
        values.put("sex", record.getSex());
        values.put("DOB", record.getDob());

        // 3. insert the record into database
        System.out.println(""+MySQLiteHelper.getLoginName()+"____"+values.toString());
        //System.out.println("\""+MySQLiteHelper.getKEY_UserN().toString()+"\"");
        System.out.println(""+record.getUserid());
        System.out.println(""+record.getUsername());
        System.out.println(""+record.getPassword());
        System.out.println(""+record.getEmail());
        System.out.println(""+(float)record.getHeight());
        System.out.println(""+(float)record.getWeight());
        System.out.println(""+(float)record.getBMI());
        System.out.println(""+record.getBMIstatus());
        System.out.println(""+record.getSex());
        System.out.println(""+record.getDob());
        //db.insert(MySQLiteHelper.getLoginName(), null, values);
        if (db.isOpen())
            db.update("user", values, "userID=userID",null);
        else
            System.out.println("DB not available");
        // 4. close a database.
        close();
    }

    public void deleteAllFoodRecords() {
        // 1. Open a database.
        open();
        db.execSQL("DELETE FROM " + MySQLiteHelper.getFdTableName());
        // 3. close a database.
        close();
    }

    public void deleteAllFoodChRecords() {
        // 1. Open a database.
        open();
        db.execSQL("DELETE FROM " + MySQLiteHelper.getFoodchtableName());
        // 3. close a database.
        close();
    }

    public void deleteAllExerRecords() {
        // 1. Open a database.
        open();
        db.execSQL("DELETE FROM " + MySQLiteHelper.getExertableName());
        // 3. close a database.
        close();
    }

    public void deleteAllExerChRecords() {
        // 1. Open a database.
        open();
        db.execSQL("DELETE FROM " + MySQLiteHelper.getExchtableName());
        // 3. close a database.
        close();
    }

    public void deleteAllLoginRecords() {
        // 1. Open a database.
        open();
        db.execSQL("DELETE FROM " + MySQLiteHelper.getLoginName());
        // 3. close a database.
        close();
    }

    public List<FoodRecord> getAllFoodRecords() {
        //1. Open a database.
        open();
        List<FoodRecord> FoodRecord = new LinkedList<FoodRecord>();
        //2. Build the query
        String query = "SELECT  * FROM " + MySQLiteHelper.getFdTableName();
        Cursor cursor = db.rawQuery(query, null);
        //3. Build records with the help of “cursor” to create a new instance of FoodRecordrecord.
        FoodRecord gr = null;
        if (cursor.moveToFirst()) {
            do {
                gr = new FoodRecord(cursor.getString(1), Integer.parseInt(cursor.getString(2)));
                FoodRecord.add(gr);
            } while (cursor.moveToNext());
        }

        //4. Close the database.
        close();
        //5. Return FoodRecord records.
        return FoodRecord;
    }

    public List<ExerciseRecord> getAllExerRecords() {
        //1. Open a database.
        open();
        List<ExerciseRecord> ExerciseRecord = new LinkedList<ExerciseRecord>();
        //2. Build the query
        String query = "SELECT  * FROM " + MySQLiteHelper.getExertableName();
        Cursor cursor = db.rawQuery(query, null);
        //3. Build records with the help of “cursor” to create a new instance of ExerciseRecordrecord.
        ExerciseRecord gr = null;
        if (cursor.moveToFirst()) {
            do {
                gr = new ExerciseRecord(cursor.getString(1), Integer.parseInt(cursor.getString(2)));
                ExerciseRecord.add(gr);
            } while (cursor.moveToNext());
        }

        //4. Close the database.
        close();
        //5. Return ExerciseRecord records.
        return ExerciseRecord;
    }

    public FoodRecord getOneFoodRecord(String cond) {
        //1. Open a database.
        open();
        List<FoodRecord> FoodRecord = new LinkedList<FoodRecord>();
        //2. Build the query
        String query = "SELECT * FROM " + MySQLiteHelper.getFdTableName() +
                " WHERE " + MySQLiteHelper.getKeyId() + " = " + cond + "";
        Log.d("query", query);

        Cursor cursor = db.rawQuery(query, null);
        //3. Build records with the help of “cursor” to create a new instance of FoodRecordrecord.
        FoodRecord gr = null;
        if (cursor.moveToFirst()) {
            Log.d("Moving cursor","d");
            do {
                gr = new FoodRecord(cursor.getString(1), Integer.parseInt(cursor.getString(2)));
                FoodRecord.add(gr);
            } while (cursor.moveToNext());
        }

        //4. Close the database.
        close();
        //5. Return FoodRecord records.
        return gr;
    }

    public ExerciseRecord getOneExerRecord(String cond) {
        //1. Open a database.
        open();
        List<ExerciseRecord> ExerciseRecord = new LinkedList<ExerciseRecord>();
        //2. Build the query
        String query = "SELECT * FROM " + MySQLiteHelper.getExertableName();
                //+" WHERE " + MySQLiteHelper.getKeyEid() + " = " + cond + "";
        Cursor cursor = db.rawQuery(query, null);
        //3. Build records with the help of “cursor” to create a new instance of ExerciseRecordrecord.
        ExerciseRecord gr = null;
        if (cursor.moveToFirst()) {
            do {
                gr = new ExerciseRecord(cursor.getString(1), Integer.parseInt(cursor.getString(2)));
                ExerciseRecord.add(gr);
            } while (cursor.moveToNext());
        }

        //4. Close the database.
        close();
        //5. Return ExerciseRecord records.
        return gr;
    }

    public List<FoodChoiceRecord> getAllFoodChRecords() {
        //1. Open a database.
        open();
        List<FoodChoiceRecord> FoodChoiceRecord = new LinkedList<FoodChoiceRecord>();
        //2. Build the query
        String query = "SELECT  * FROM " + MySQLiteHelper.getFoodchtableName();
        Cursor cursor = db.rawQuery(query, null);
        //3. Build records with the help of “cursor” to create a new instance of FoodChoiceRecordrecord.
        FoodChoiceRecord gr = null;
        if (cursor.moveToFirst()) {
            do {
                gr = new FoodChoiceRecord(cursor.getInt(1));
                FoodChoiceRecord.add(gr);
            } while (cursor.moveToNext());
        }

        //4. Close the database.
        close();
        //5. Return FoodChoiceRecord records.
        return FoodChoiceRecord;
    }

    public List<ExerciseChoiceRecord> getAllExerChRecords() {
        //1. Open a database.
        open();
        List<ExerciseChoiceRecord> ExerciseChoiceRecord = new LinkedList<ExerciseChoiceRecord>();
        //2. Build the query
        String query = "SELECT  * FROM " + MySQLiteHelper.getExchtableName();
        Cursor cursor = db.rawQuery(query, null);
        //3. Build records with the help of “cursor” to create a new instance of ExerciseChoiceRecordrecord.
        ExerciseChoiceRecord gr = null;
        if (cursor.moveToFirst()) {
            do {
                gr = new ExerciseChoiceRecord(cursor.getInt(1));
                ExerciseChoiceRecord.add(gr);
            } while (cursor.moveToNext());
        }

        //4. Close the database.
        close();
        //5. Return ExerciseChoiceRecord records.
        return ExerciseChoiceRecord;
    }

    public List<LoginRecord> getAllLoginRecords() {
        //1. Open a database.
        open();
        List<LoginRecord> LoginRecord = new LinkedList<LoginRecord>();
        //2. Build the query
        String query = "SELECT * FROM " + MySQLiteHelper.getLoginName();
        Cursor cursor = db.rawQuery(query, null);
        //3. Build records with the help of “cursor” to create a new instance of LoginRecordrecord.
        LoginRecord lr = null;
        if (cursor.moveToFirst()) {
            do {
                lr = new LoginRecord(cursor.getString(0),cursor.getString(1), cursor.getString(2)
                        , cursor.getString(3)
                        , Double.parseDouble(cursor.getString(4))
                        , Double.parseDouble(cursor.getString(5))
                        , cursor.getString(6)
                        , cursor.getString(7)
                );
                LoginRecord.add(lr);
            } while (cursor.moveToNext());
        }

        //4. Close the database.
        close();
        //5. Return LoginRecord records.
        return LoginRecord;
    }

}