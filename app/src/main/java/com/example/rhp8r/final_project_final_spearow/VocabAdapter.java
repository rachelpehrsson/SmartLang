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
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Button;
import android.graphics.Color;

import java.util.Collections;
import java.util.List;
/**
 * Created by rhp8r on 9/4/2017.
 */

public class VocabAdapter extends RecyclerView.Adapter<VocabAdapter.ViewHolder> {
    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView word;
        public TextView def;
        public RadioGroup ranks;


        public ViewHolder(View itemView) {
            super(itemView);

            word = (TextView) itemView.findViewById(R.id.word);
            def = (TextView) itemView.findViewById(R.id.def);
            ranks = (RadioGroup)itemView.findViewById(R.id.ranks);
        }
    }
    private List<Vocab> wordList;
    // Store the context for easy access
    private Context mContext;
    private TabVocab mFragment;
    private int rank;
    private RadioButton lastCheckedRB = null;

    // Pass in the contact array into the constructor
    public VocabAdapter(Context context, List<Vocab> words, TabVocab fragment) {
        wordList = words;
        mContext = context;
        mFragment = fragment;
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
    public void onBindViewHolder(final VocabAdapter.ViewHolder viewHolder, final int position) {
        viewHolder.ranks.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton checked_rb = (RadioButton) group.findViewById(checkedId);
                if (checked_rb.getId() == R.id.rank0) {
                    wordList.get(position).setRank(0);
                    viewHolder.itemView.setBackgroundColor(Color.parseColor("#CD6155"));
                }
                if (checked_rb.getId() == R.id.rank1) {
                    wordList.get(position).setRank(1);
                    viewHolder.itemView.setBackgroundColor(Color.parseColor("#F4D03F"));
                }
                if (checked_rb.getId() == R.id.rank2) {
                    wordList.get(position).setRank(2);
                    viewHolder.itemView.setBackgroundColor(Color.parseColor("#196F3D"));
                }
                mFragment.sort();
                notifyDataSetChanged();
                //viewHolder.ranks.clearCheck(); //fix the clear check
                /*if (lastCheckedRB != null) {
                    lastCheckedRB.setChecked(false);
                }*/
                //store the clicked radiobutton
        }
        });

        String word = wordList.get(position).getWord();
        String def = wordList.get(position).getDef();
        // Set item views based on your views and data model
        TextView wordView = viewHolder.word;
        wordView.setText(word);
        TextView defView = viewHolder.def;
        defView.setText(def);

        if(wordList.get(position).getRank() == 0){
            viewHolder.itemView.setBackgroundColor(Color.parseColor("#CD6155"));
        }
        if(wordList.get(position).getRank() == 1){
            viewHolder.itemView.setBackgroundColor(Color.parseColor("#F4D03F"));
        }
        if(wordList.get(position).getRank() == 2){
            viewHolder.itemView.setBackgroundColor(Color.parseColor("#196F3D"));
        }

        /*if(!item.isOnline()) {
            textView.setClickable(false);
            textView.setActivated(false);
            textView.setEnabled(false);
        }*/

    }

    /*void update(){
        //Collections.sort(wordList);
        notifyDataSetChanged();
    }*/
    /*public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();
        lastCheckedRB = (RadioButton)view;
        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.rank0:
                if (checked)
                    rank = 0;
            case R.id.rank1:
                if (checked)
                    rank = 1;
                // Ninjas rule
            case R.id.rank2:
                if (checked)
                    rank = 2;
                // Ninjas rule
        }
        notifyDataSetChanged();
    }*/

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return wordList.size();
    }
}