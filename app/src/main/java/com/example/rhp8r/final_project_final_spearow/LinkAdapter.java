package com.example.rhp8r.final_project_final_spearow;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

/**
 * Created by rhp8r on 12/1/2017.
 */

public class LinkAdapter extends RecyclerView.Adapter<LinkAdapter.ViewHolder> {
    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView link;

        public ViewHolder(View itemView) {
            super(itemView);

            link = (TextView) itemView.findViewById(R.id.linktitle);

        }
    }
    private List<Link> linkList;
    // Store the context for easy access
    private Context mContext;

    // Pass in the contact array into the constructor
    public LinkAdapter(Context context, List<Link> items) {
        linkList = items;
        mContext = context;
    }

    // Easy access to the context object in the recyclerview
    private Context getContext() {
        return mContext;
    }

    // Usually involves inflating a layout from XML and returning the holder
    @Override
    public LinkAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.link_entry, parent, false);

        // Return a new holder instance
        LinkAdapter.ViewHolder viewHolder = new LinkAdapter.ViewHolder(contactView);
        return viewHolder;
    }
    /*public void onCheckboxClicked(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();
    }*/
    @Override
    public void onBindViewHolder(LinkAdapter.ViewHolder viewHolder, int position) {

        Link lin = linkList.get(position);

        // Set item views based on your views and data model
        TextView linkView = viewHolder.link;
        linkView.setText(lin.getLabel());
        /*if(!item.isOnline()) {
            textView.setClickable(false);
            textView.setActivated(false);
            textView.setEnabled(false);
        }*/

    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return linkList.size();
    }
}
