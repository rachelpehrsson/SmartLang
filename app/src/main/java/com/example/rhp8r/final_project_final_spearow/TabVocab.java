package com.example.rhp8r.final_project_final_spearow;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;

import java.util.ArrayList;
import java.util.Collections;

public class TabVocab extends Fragment {
    public static final int ADD_WORD = 1;
    public static final int EDIT_WORD = 2;
    RecyclerView vocabList;
    ArrayList<Vocab> vocab;
    FloatingActionButton addButton;
    String lname;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.vocabtab, container, false);

        Intent intent = getActivity().getIntent();
        Bundle b = intent.getExtras();
        lname = b.getString("langName");
        vocabList = (RecyclerView) rootView.findViewById(R.id.vocabList);
        loadLanguageInfoFromDatabase(lname);
        VocabAdapter adapter = new VocabAdapter(getActivity(), vocab);
        addButton = (FloatingActionButton) rootView.findViewById(R.id.addVocab);
        vocabList.setAdapter(adapter);
        vocabList.setLayoutManager(new LinearLayoutManager(getActivity()));
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addVocab(v);
            }
        });
        sort();
        return rootView;
    }
    public void sort(){
        Collections.sort(vocab);
        for(int i = 0;i<vocab.size();i++){
            saveToDatabase(vocab.get(i).getWord(),vocab.get(i).getDef(), vocab.get(i).getRank(),lname);
        }
    }
    public void loadLanguageInfoFromDatabase(String langname) {
        DatabaseHelper mDbHelper = new DatabaseHelper(getActivity());
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        String[] projection = {
                "word",
                "translation",
                "ranking"
        };
        String sortOrder =
                "";
        String selection = "'langname= "+langname+"'";
        //String[] selectionargs = {langname};
        Cursor cursor = db.query(
                "vocabulary",  // The table to query
                projection,                               // The columns to return
                selection,                                // The columns for the WHERE clause
                null,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                sortOrder                                 // The sort order
        );
        int count = cursor.getCount();
         vocab = new ArrayList();
        while (cursor.moveToNext()) {
            Vocab temp = new Vocab(cursor.getString(cursor.getColumnIndexOrThrow("word")), cursor.getString(cursor.getColumnIndexOrThrow("def")),
                    cursor.getInt(cursor.getColumnIndexOrThrow("rank")),langname);
            vocab.add(temp);
            //Log.i("DBData");
        }
        cursor.close();
    }

    public void reshuffle(View view){


    }

    public void saveToDatabase(String word, String def, int rank, String lang) {
        // Add code here to save to the database
        DatabaseHelper mDbHelper = new DatabaseHelper(getActivity());
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
//
//        // Create a new map of values, where column names are the keys

        ContentValues values2 = new ContentValues();;
        values2.put("word", word);
        values2.put("translation", def);
        values2.put("ranking", rank);
        values2.put("langname", lang);
//

        long newRowId2;
//
        newRowId2 = db.insertWithOnConflict(
                "vocabulary",
                null,
                values2, SQLiteDatabase.CONFLICT_IGNORE);


    }

    public void addVocab(View view) {
        Intent intent1 = new Intent(getActivity(), AddVocab.class);
        Bundle b = new Bundle();
        b.putString("langname",lname);
        intent1.putExtras(b);
        startActivityForResult(intent1, ADD_WORD);
    }
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        Bundle b = intent.getExtras();
        String lang = b.getString("newLang");
        String word = b.getString("newWord");
        String def = b.getString("newDef");
        int rank = b.getInt("rank");
        if (requestCode == ADD_WORD) {

            Vocab newEntry = new Vocab(word, def, rank, lang);
            vocab.add(newEntry);
            sort();
            vocabList.getAdapter().notifyDataSetChanged();
            VocabAdapter adapter = new VocabAdapter(getActivity(), vocab);
            vocabList.setAdapter(adapter);
            vocabList.setLayoutManager(new LinearLayoutManager(getActivity()));
        }


    }

}