package com.example.rhp8r.final_project_final_spearow;

import android.content.Intent;
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
    ArrayList<Language> languageList;
    RecyclerView rvItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_languages);
        rvItems = (RecyclerView) findViewById(R.id.rvItems);
        languageList = Language.createInitialLanguageList();
        LanguagesAdapter adapter = new LanguagesAdapter(this, languageList);
        rvItems.setAdapter(adapter);
        rvItems.setLayoutManager(new LinearLayoutManager(this));
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