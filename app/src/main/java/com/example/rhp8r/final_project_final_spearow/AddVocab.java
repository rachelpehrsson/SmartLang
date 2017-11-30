package com.example.rhp8r.final_project_final_spearow;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;

/**
 * Created by rhp8r on 11/28/2017.
 */



public class AddVocab extends AppCompatActivity {
    EditText newWord;
    EditText newDef;
    RadioGroup ranks;
    int rank;
    String langname;
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        Bundle b = intent.getExtras();
        langname = b.getString("langname");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        setContentView(R.layout.add_vocab);
        newWord = (EditText) findViewById(R.id.word);
        newDef = (EditText) findViewById(R.id.def);
        ranks = (RadioGroup) findViewById(R.id.ranks);
    }
    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.rank0:
                if (checked)
                    rank = 0;
                    break;
            case R.id.rank1:
                if (checked)
                    rank = 1;
                    // Ninjas rule
                    break;
            case R.id.rank2:
                if (checked)
                    rank = 3;
                    // Ninjas rule
                    break;
        }
    }

    public void saveToDatabase(String lang, String word, String def, int rank) {
        // Add code here to save to the database
        DatabaseHelper mDbHelper = new DatabaseHelper(this);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values2 = new ContentValues();
        values2.put("word", word);
        values2.put("translation", def);
        values2.put("ranking", rank);
        values2.put("langname", lang);

        long newRowId2;

        newRowId2 = db.insertWithOnConflict(
                "vocabulary",
                null,
                values2,  SQLiteDatabase.CONFLICT_IGNORE);



    }
    public void sendMessage(View view) {

        saveToDatabase(langname,newWord.getText().toString(),newDef.getText().toString(), rank);
        Intent intent2 = new Intent();
        Bundle b = new Bundle();
        b.putString("newLang", langname);
        b.putString("newWord", newWord.getText().toString());
        b.putString("newDef", newDef.getText().toString());
        b.putInt("rank", rank);
        intent2.putExtras(b);
        setResult(RESULT_OK, intent2);
        finish();
    }

}
