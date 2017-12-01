package com.example.rhp8r.final_project_final_spearow;

/**
 * Created by rhp8r on 11/25/2017.
 */
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**

 Assignment Notes: This code is provided as part of the SQLite feature.
 You do not need to edit this code.  Note that it provides the functionality
 to create a blank database if one does not exist.

 */

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Languages.db";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table languages (langname varchar(25))");
        db.execSQL("create table vocabulary (word varchar(100), translation varchar(100), ranking int(10), langname varchar(25))");
        db.execSQL("create table media (url varchar(100), label varchar(100), langname varchar(25))");
        db.execSQL("create table photos (filename varchar(100), langname varchar(25))");
        db.execSQL("create table audio (filename varchar(100), langname varchar(25))");
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL("delete table person");
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
