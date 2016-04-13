package com.example.ramjee.myapplication;

/**
 * Created by Kent on 8/1/2016.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class MySQLiteHelper extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 4;
    // Database Name
    private static final String DATABASE_NAME = "FitnessManager.db";
    // Name of table
    private static final String FOODTABLE_NAME = "foodcalories";
    private static final String EXERTABLE_NAME = "exercalories";
    private static final String FOODCHTABLE_NAME = "foodchoice";
    private static final String EXCHTABLE_NAME = "exerchoice";
    private static final String LOGIN_NAME = "user";
    //  Columns names in table
    //Columns in Food
    private static final String KEY_ID = "foodID";
    private static final String KEY_FdNme = "foodName";
    private static final String KEY_calories = "calories";
    //Columns in Exercise
    private static final String KEY_EID = "exerciseID";
    private static final String KEY_ExerNme = "exerciseName";
    private static final String KEY_expcal = "calories";
    //Columns in Exercise Choice
    private static final String KEY_ECID = "exercisechoiceID";
    //Columns in Exercise Choice
    private static final String KEY_FCID = "foodchoiceID";
    //Columns in Users
    private static final String KEY_UID = "userID";
    private static final String KEY_UserN = "userName";
    private static final String KEY_PW = "password";
    private static final String KEY_eM = "email";
    private static final String KEY_height = "height";
    private static final String KEY_wT = "weight";
    private static final String KEY_BMI = "BMI";
    private static final String KEY_BMIs = "BMI_status";
    private static final String KEY_Sex = "sex";
    private static final String KEY_dob = "DOB";

    public static String getFoodtableName() {
        return FOODTABLE_NAME;
    }

    public static String getExertableName() {
        return EXERTABLE_NAME;
    }

    public static String getKEY_FdNme() {
        return KEY_FdNme;
    }

    public static String getKEY_calories() {
        return KEY_calories;
    }

    public static String getFoodchtableName() {
        return FOODCHTABLE_NAME;
    }

    public static String getExchtableName() {
        return EXCHTABLE_NAME;
    }

    public static String getKeyId() {
        return KEY_ID;
    }

    public static String getKeyEid() {
        return KEY_EID;
    }

    public static String getKEY_ExerNme() {
        return KEY_ExerNme;
    }

    public static String getKEY_expcal() {
        return KEY_expcal;
    }

    public static String getKeyEcid() {
        return KEY_ECID;
    }

    public static String getKeyFcid() {
        return KEY_FCID;
    }

    public static String getLoginName() {
        return LOGIN_NAME;
    }

    public static String getKeyUid() {
        return KEY_UID;
    }

    public static String getKeyBmi() {
        return KEY_BMI;
    }

    public static String getKEY_UserN() {
        return KEY_UserN;
    }

    public static String getKeyPw() {
        return KEY_PW;
    }

    public static String getKeyem() {
        return KEY_eM;
    }

    public static String getKEY_height() {
        return KEY_height;
    }

    public static String getKeywt() {
        return KEY_wT;
    }

    public static String getKEY_BMIs() {
        return KEY_BMIs;
    }

    public static String getKEY_Sex() {
        return KEY_Sex;
    }

    public static String getKEY_dob() {
        return KEY_dob;
    }

    public static String getKeyFdNme() {
        return KEY_FdNme;
    }

    public static String getFdTableName() {
        return FOODTABLE_NAME;
    }

    public static String getKeyCalories() {
        return KEY_calories;
    }

    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // 1. SQL statement for creating table for records.
        String CREATE_TABLE_FOODRECORD_COMMAND = "CREATE TABLE "+FOODTABLE_NAME+" ( " +
                KEY_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, " +
                KEY_FdNme+" TEXT NOT NULL, "+
                KEY_calories+" INTEGER )";

        String CREATE_TABLE_LOGIN_COMMAND = "CREATE TABLE "+LOGIN_NAME+" ( " +
                KEY_UID+" TEXT NOT NULL PRIMARY KEY, " +
                KEY_UserN+" TEXT NOT NULL, "+
                KEY_PW+" TEXT NOT NULL, "+
                KEY_eM+" TEXT NOT NULL, "+
                KEY_height+" FLOAT, "+
                KEY_wT+" FLOAT, "+
                KEY_BMI+" FLOAT, "+
                KEY_BMIs+" TEXT NOT NULL, "+
                KEY_Sex+" TEXT NOT NULL, "+
                KEY_dob+" TEXT NOT NULL )";
        String CREATE_TABLE_EXERRECORD_COMMAND = "CREATE TABLE "+EXERTABLE_NAME+" ( " +
                KEY_EID+" INTEGER PRIMARY KEY AUTOINCREMENT, " +
                KEY_ExerNme+" TEXT NOT NULL, "+
                KEY_expcal+" FLOAT )";
        String CREATE_TABLE_FOODCHRECORD_COMMAND = "CREATE TABLE "+FOODCHTABLE_NAME+" ( " +
                KEY_FCID+" INTEGER PRIMARY KEY AUTOINCREMENT, " +
                KEY_ID+" INTEGER )";
        String CREATE_TABLE_EXERCHRECORD_COMMAND = "CREATE TABLE "+EXCHTABLE_NAME+" ( " +
                KEY_ECID+" INTEGER PRIMARY KEY AUTOINCREMENT, " +
                KEY_EID+" INTEGER )";

        //2.  execute SQL commands
        db.execSQL(CREATE_TABLE_FOODRECORD_COMMAND);
        db.execSQL(CREATE_TABLE_LOGIN_COMMAND);
        db.execSQL(CREATE_TABLE_EXERRECORD_COMMAND);
        db.execSQL(CREATE_TABLE_FOODCHRECORD_COMMAND);
        db.execSQL(CREATE_TABLE_EXERCHRECORD_COMMAND);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //1. Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS "+FOODTABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+LOGIN_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+EXERTABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+FOODCHTABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+EXCHTABLE_NAME);
        //2. Create a new table
        this.onCreate(db);
    }


}