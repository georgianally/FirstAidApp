package com.georgia.firstaid.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String TABLE_REPORT = "reports";
    public static final String COLUMN_NAME_ID = "id";
    public static final String COLUMN_NAME_DATE = "date";
    public static final String COLUMN_NAME_START = "start";
    public static final String COLUMN_NAME_FINISH = "finish";
    public static final String COLUMN_NAME_LOCATION = "location";
    public static final String COLUMN_NAME_RESPONSIVE = "responsive";
    public static final String COLUMN_NAME_BLEED = "bleed";
    public static final String COLUMN_NAME_CPR = "cpr";


    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TABLE_REPORT + " (" +
                    COLUMN_NAME_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    COLUMN_NAME_DATE + " DATETIME DEFAULT CURRENT_DATE," +
                    COLUMN_NAME_START + " DATETIME," +
                    COLUMN_NAME_FINISH + " DATETIME DEFAULT CURRENT_TIME," +
                    COLUMN_NAME_LOCATION + "TEXT," +
                    COLUMN_NAME_RESPONSIVE + "TEXT," +
                    COLUMN_NAME_BLEED + "TEXT," +
                    COLUMN_NAME_CPR + "TEXT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + TABLE_REPORT;
    //If database contract changed, increment database version
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Report.db";

    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
}