package com.example.rhp8r.final_project_final_spearow;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class TabLink extends Fragment {
    public static final int ADD_LINK = 1;
    public static final int EDIT_lINK = 2;
    RecyclerView linkList;
    ArrayList<Link> links;
    FloatingActionButton addButton;
    String lname;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.linktab, container, false);
        Intent intent = getActivity().getIntent();
        Bundle b = intent.getExtras();
        lname = b.getString("langName");
        linkList = (RecyclerView) rootView.findViewById(R.id.linkList);
        loadLanguageInfoFromDatabase(lname);
        LinkAdapter adapter = new LinkAdapter(getActivity(),links);
        addButton = (FloatingActionButton) rootView.findViewById(R.id.addLink);
        linkList.setAdapter(adapter);
        linkList.setLayoutManager(new LinearLayoutManager(getActivity()));
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addLink(v);
            }
        });
        return rootView;
    }
    public void loadLanguageInfoFromDatabase(String langname) {
        DatabaseHelper mDbHelper = new DatabaseHelper(getActivity());
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        String[] projection = {
                "url",
                "label"
        };
        String sortOrder =
                "";
        String selection = "'langname= "+langname+"'";
        //String[] selectionargs = {langname};
        Cursor cursor = db.query(
                "media",  // The table to query
                projection,                               // The columns to return
                selection,                                // The columns for the WHERE clause
                null,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                sortOrder                                 // The sort order
        );
        int count = cursor.getCount();
        links = new ArrayList();
        while (cursor.moveToNext()) {
            Link temp = new Link(cursor.getString(cursor.getColumnIndexOrThrow("url")), cursor.getString(cursor.getColumnIndexOrThrow("label")),langname);
            links.add(temp);
            //Log.i("DBData");
        }
        cursor.close();
    }
    public void saveToDatabase(String url, String label, String lang) {
        // Add code here to save to the database
        DatabaseHelper mDbHelper = new DatabaseHelper(getActivity());
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
//
//        // Create a new map of values, where column names are the keys

        ContentValues values2 = new ContentValues();;
        values2.put("url", url);
        values2.put("label", label);
        values2.put("langname", lang);
//

        long newRowId2;
//
        newRowId2 = db.insertWithOnConflict(
                "media",
                null,
                values2, SQLiteDatabase.CONFLICT_IGNORE);


    }
    public void addLink(View view) {
        Intent intent1 = new Intent(getActivity(), AddLink.class);
        Bundle b = new Bundle();
        b.putString("langname",lname);
        intent1.putExtras(b);
        startActivityForResult(intent1, ADD_LINK);
    }
    public void sendMessage(View view) {
        //Intent intent2 = new Intent();

        TextView current = (TextView) view;
        String currentname = current.getText().toString();
        Link selectedLang = new Link("null", "null", "null");
        int position;
        for (int i = 0; i < links.size(); i++) {
            Link iter = links.get(i);
            if (currentname == iter.getUrl()) {
                selectedLang = iter;
                position = i;
            }
        }
        String url = selectedLang.getUrl();
        if (!url.startsWith("http://") && !url.startsWith("https://"))
            url = "http://" + url;
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(intent);
    }
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        Bundle b = intent.getExtras();
        String url = b.getString("newLang");
        String label = b.getString("newWord");
        if (requestCode == ADD_LINK) {

            Link newEntry = new Link(url, label, lname);
            saveToDatabase(url, label, lname);
            links.add(newEntry);
            linkList.getAdapter().notifyDataSetChanged();
            LinkAdapter adapter = new LinkAdapter(getActivity(), links);
           linkList.setAdapter(adapter);
            linkList.setLayoutManager(new LinearLayoutManager(getActivity()));
        }


    }

}