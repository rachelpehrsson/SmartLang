package com.example.rhp8r.final_project_final_spearow;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

public class LanguagesActivity extends AppCompatActivity {
    public static final int ADD_LANG = 1;
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
   /* public void addLanguage(View view) {
        Intent intent1 = new Intent(this, AddLanguage.class);
        startActivityForResult(intent1, ADD_lANG);
    }*/



}
