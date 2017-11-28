package com.example.rhp8r.final_project_final_spearow;

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

public class TabVocab extends Fragment {
    RecyclerView vocabList;
    ArrayList<String> words;
    ArrayList<String> defs;
    ArrayList<Integer> ranks;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.vocabtab, container, false);
        /*vocabList = (RecyclerView) rootView.findViewById(R.id.vocabList);
        VocabAdapter adapter = new VocabAdapter(getActivity(), words, defs);
        vocabList.setAdapter(adapter);
        vocabList.setLayoutManager(new LinearLayoutManager(getActivity()));*/
        return rootView;
    }
   /* public void loadLanguageInfoFromDatabase(String langname) {
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
         words = new ArrayList();
        while (cursor.moveToNext()) {
            words.add(cursor.getString(
                    cursor.getColumnIndexOrThrow("langname")
            ));
            //Log.i("DBData");
        }
        cursor.close();
    }*/
}