package com.example.rhp8r.final_project_final_spearow;
import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by rhp8r on 11/14/2017.
 */

public class AddLanguage extends AppCompatActivity {

    EditText addLanguage;
    EditText newWord;
    EditText newDef;
    boolean addedVocab = false;

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
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        setContentView(R.layout.add_language);
        addLanguage = (EditText) findViewById(R.id.newLang);
        newWord = (EditText) findViewById(R.id.word);
        newDef = (EditText) findViewById(R.id.def);
        if (newWord.equals("First Vocab Definition")==false){
            addedVocab = true;
        }
    }

    public void sendMessage(View view) {
        Intent intent2 = new Intent();
        Bundle b = new Bundle();
        b.putString("newLang", addLanguage.getText().toString());
        b.putString("newWord", newWord.getText().toString());
        b.putString("newDef", newDef.getText().toString());
        b.putBoolean("addedVocab", addedVocab);
        intent2.putExtras(b);
        setResult(RESULT_OK, intent2);
        finish();
    }
}