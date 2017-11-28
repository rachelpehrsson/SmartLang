package com.example.rhp8r.final_project_final_spearow;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class LanguagesActivity extends AppCompatActivity {
    public static final int ADD_LANG = 1;
    public static final int MEDIA_RET = 2;
    ArrayList<String> langIDs;
    ArrayList<Language> languageList;
    RecyclerView rvItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_languages);
        rvItems = (RecyclerView) findViewById(R.id.rvItems);
        langIDs = new ArrayList<String>();
        loadLanguagesFromDatabase();
        if (langIDs == null){
            saveToDatabase("French");
            saveToDatabase("Spanish");
            saveToDatabase("Hindi");
            loadLanguagesFromDatabase();
        }
        languageList = Language.createInitialLanguageList(langIDs);
        LanguagesAdapter adapter = new LanguagesAdapter(this, languageList);
        rvItems.setAdapter(adapter);
        rvItems.setLayoutManager(new LinearLayoutManager(this));
    }

    public void saveToDatabase(String lang) {
        // Add code here to save to the database
        DatabaseHelper mDbHelper = new DatabaseHelper(this);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put("langname", lang);

        long newRowId;
        newRowId = db.insertWithOnConflict(
                "languages",
                null,
                values, SQLiteDatabase.CONFLICT_IGNORE);


    }
    public void saveToDatabase(String lang, String word, String def) {
        // Add code here to save to the database
        DatabaseHelper mDbHelper = new DatabaseHelper(this);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values1 = new ContentValues();
        ContentValues values2 = new ContentValues();
        values1.put("langname", lang);
        values2.put("word", word);
        values2.put("translation", def);
        values2.put("ranking", 0);
        values2.put("langname", lang);

        long newRowId1;
        long newRowId2;
        newRowId1 = db.insertWithOnConflict(
                "languages",
                null,
                values1, SQLiteDatabase.CONFLICT_IGNORE);

        newRowId2 = db.insertWithOnConflict(
                "vocabulary",
                null,
                values2, SQLiteDatabase.CONFLICT_IGNORE);



    }

    public void loadLanguagesFromDatabase() {
        DatabaseHelper mDbHelper = new DatabaseHelper(this);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        String[] projection = {
                "langname"
        };
        String sortOrder =
                "";
        Cursor cursor = db.query(
                "languages",  // The table to query
                projection,                               // The columns to return
                null,                                // The columns for the WHERE clause
                null,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                sortOrder                                 // The sort order
        );
        String name = "";
        while (cursor.moveToNext()) {
            langIDs.add(cursor.getString(
                    cursor.getColumnIndexOrThrow("langname")
            ));
            //Log.i("DBData");
        }
        cursor.close();
    }
    public void loadLanguageInfoFromDatabase(String langname) {
        DatabaseHelper mDbHelper = new DatabaseHelper(this);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        String[] projection = {
                ""
        };
        String sortOrder =
                "";
        Cursor cursor = db.query(
                "languages",  // The table to query
                projection,                               // The columns to return
                null,                                // The columns for the WHERE clause
                null,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                sortOrder                                 // The sort order
        );
        String name = "";
        while (cursor.moveToNext()) {
            langIDs.add(cursor.getString(
                    cursor.getColumnIndexOrThrow("langname")
            ));
            //Log.i("DBData");
        }
        cursor.close();
    }

    public void langSelect(View view) {
        Intent intent = new Intent(this, ChoicesActivity.class);
        startActivity(intent);
    }

    public void sendMessage(View view) {
        //Intent intent2 = new Intent();
        Intent intent = new Intent(this, ChoicesActivity.class);
        startActivity(intent);
        Button current = (Button) view;
        String currentname = current.getText().toString();
        Language selectedLang = new Language();
        int position;
        for (int i = 0; i < languageList.size(); i++) {
            Language iter = languageList.get(i);
            if (currentname == iter.getLname()) {
                selectedLang = iter;
                position = i;
            }
        }
        Bundle b = new Bundle();
        b.putString("langName", selectedLang.getLname());
        b.putStringArrayList("langWords", selectedLang.getWordList());
        b.putStringArrayList("langDefs", selectedLang.getDefList());


    }

    public void addLanguage(View view) {
        Intent intent1 = new Intent(this, AddLanguage.class);
        startActivityForResult(intent1, ADD_LANG);
    }
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Save the user's current state
        Log.d("RotationExample", "Rotating!");

        super.onSaveInstanceState(savedInstanceState);
    }
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        // Always call the superclass so it can restore the view hierarchy
        Log.d("RotationExample", "Rebuilding the View!");
        super.onRestoreInstanceState(savedInstanceState);

        // Restore state members from saved instance

    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        Bundle b = intent.getExtras();
        String newLang = b.getString("newLang");
        String firstWord = b.getString("newWord");
        String firstDef = b.getString("newDef");
        boolean hasAdded = b.getBoolean("addedVocab");
        if (requestCode == ADD_LANG) {

            Language newLanguage = new Language(newLang);
            if (hasAdded == true) {
                newLanguage.getWordList().add(firstWord);
                newLanguage.getDefList().add(firstDef);
            }
            languageList.add(newLanguage);
            rvItems.getAdapter().notifyDataSetChanged();
            LanguagesAdapter adapter = new LanguagesAdapter(this, languageList);
            rvItems.setAdapter(adapter);
            rvItems.setLayoutManager(new LinearLayoutManager(this));
        }


    }
}