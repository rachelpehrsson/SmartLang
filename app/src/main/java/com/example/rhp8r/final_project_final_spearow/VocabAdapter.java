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

public class VocabAdapter extends RecyclerView.Adapter<VocabAdapter.ViewHolder> {
    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView word;
        public TextView def;


        public ViewHolder(View itemView) {
            super(itemView);

            word = (TextView) itemView.findViewById(R.id.word);
            def = (TextView) itemView.findViewById(R.id.def);

        }
    }
    private List<String> wordList;
    private List<String> defList;
    // Store the context for easy access
    private Context mContext;

    // Pass in the contact array into the constructor
    public VocabAdapter(Context context, List<String> words, List<String> defs) {
        wordList = words;
        defList = defs;
        mContext = context;
    }

    // Easy access to the context object in the recyclerview
    private Context getContext() {
        return mContext;
    }

    // Usually involves inflating a layout from XML and returning the holder
    @Override
    public VocabAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.vocab_entry, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }
    /*public void onCheckboxClicked(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();
    }*/
    @Override
    public void onBindViewHolder(VocabAdapter.ViewHolder viewHolder, int position) {

        String word = wordList.get(position);
        String def = defList.get(position);
        // Set item views based on your views and data model
        TextView wordView = viewHolder.word;
        wordView.setText(word);
        TextView defView = viewHolder.def;
        defView.setText(def);
        /*if(!item.isOnline()) {
            textView.setClickable(false);
            textView.setActivated(false);
            textView.setEnabled(false);
        }*/

    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return wordList.size();
    }
}