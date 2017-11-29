package com.example.rhp8r.final_project_final_spearow;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.Collections;

public class TabVocab extends Fragment {
    RecyclerView vocabList;
    ArrayList<Vocab> vocab;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.vocabtab, container, false);
        Intent intent = getActivity().getIntent();
        Bundle b = intent.getExtras();
        String name = b.getString("langname");
        vocabList = (RecyclerView) rootView.findViewById(R.id.vocabList);
        loadLanguageInfoFromDatabase(name);
        VocabAdapter adapter = new VocabAdapter(getActivity(), vocab);
        vocabList.setAdapter(adapter);
        vocabList.setLayoutManager(new LinearLayoutManager(getActivity()));
        return rootView;
    }
    public void sort(){
        Collections.sort(vocab, Collections.reverseOrder());
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
        String selection = "langname = "+langname;
        Cursor cursor = db.query(
                "vocabulary",  // The table to query
                projection,                               // The columns to return
                selection,                                // The columns for the WHERE clause
                null,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                sortOrder                                 // The sort order
        );
         vocab = new ArrayList();
        while (cursor.moveToNext()) {
            Vocab temp = new Vocab(cursor.getString(cursor.getColumnIndexOrThrow("word")), cursor.getString(cursor.getColumnIndexOrThrow("def")),
                    cursor.getInt(cursor.getColumnIndexOrThrow("rank")),langname);
            vocab.add(temp);
            //Log.i("DBData");
        }
        cursor.close();
    }
}