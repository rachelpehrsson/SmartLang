package com.example.rhp8r.final_project_final_spearow;

/**
 * Created by rhp8r on 11/13/2017.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Button;

import java.util.List;
/**
 * Created by rhp8r on 9/4/2017.
 */

public class LanguagesAdapter extends RecyclerView.Adapter<LanguagesAdapter.ViewHolder> {
    public static class ViewHolder extends RecyclerView.ViewHolder {

        public Button languageSelect;

        public ViewHolder(View itemView) {
            super(itemView);

            languageSelect = (Button) itemView.findViewById(R.id.langSelect);

        }
    }
    private List<Language> langList;
    // Store the context for easy access
    private Context mContext;

    // Pass in the contact array into the constructor
    public LanguagesAdapter(Context context, List<Language> items) {
        langList = items;
        mContext = context;
    }

    // Easy access to the context object in the recyclerview
    private Context getContext() {
        return mContext;
    }

    // Usually involves inflating a layout from XML and returning the holder
    @Override
    public LanguagesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.language_entry, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }
    /*public void onCheckboxClicked(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();


    }*/
    @Override
    public void onBindViewHolder(LanguagesAdapter.ViewHolder viewHolder, int position) {

        Language lang = langList.get(position);

        // Set item views based on your views and data model
        Button langButton = viewHolder.languageSelect;
        langButton.setText(lang.getLName());
        /*if(!item.isOnline()) {
            textView.setClickable(false);
            textView.setActivated(false);
            textView.setEnabled(false);

        }*/

    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return langList.size();
    }
}
