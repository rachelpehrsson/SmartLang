package com.example.rhp8r.final_project_final_spearow;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import java.util.ArrayList;

import android.util.Log;
import android.view.View;
import android.widget.CheckBox;

public class Language extends AppCompatActivity {
    String lname;
    ArrayList <String> words;
    ArrayList <String> defs;
    ArrayList <Integer> ranks;
    public Language() {
        this.lname = "";
        this.words = new ArrayList <String>();
        this.defs = new ArrayList <String>();
        this.ranks = new ArrayList <String>();
    }
    public Language (String langname){
        this.lname = langname;
        this.words = new ArrayList <String>();
        this.defs = new ArrayList <String>();
    }
    public String getLname(){
        return this.lname;
    }
    public ArrayList <String> getWordList(){
        return this.words;
    }
    public ArrayList <String> getDefList(){
        return this.defs;
    }

    public static ArrayList<Language> createInitialLanguageList(ArrayList<String> names){ ///????? idk how this is actually supposed to work
        //ArrayList<String> names = loadLanguagesFromDatabase();

        ArrayList<Language> ls = new ArrayList<Language>();
        for (int i = 0; i<names.size(); i++) {
            Language newLang = new Language(names.get(i));
            ls.add(newLang);
        }

        return ls;
    }


    //   This class should have a static method called createInitialBucketList() that creates the initial, hard-coded bucket list ArrayList and returns it. This will be called in the onCreate() in the BucketListActivity.
}
