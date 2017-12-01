package com.example.rhp8r.final_project_final_spearow;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.io.File;
import android.widget.ImageView;

/**
 * Created by rhp8r on 12/1/2017.
 */
/*
public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.ViewHolder> {
    private String[] itemsData;
    private Context mContext;
    public PhotoAdapter(Context context, String[] itemsData) {
        this.itemsData = itemsData;
        mContext = context;
    }

    @Override
    public PhotoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.photo_entry, null);

        ViewHolder viewHolder = new ViewHolder(itemLayoutView);
        return viewHolder;
    }
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        Uri temp = new Uri(itemsData[position]);
        Bitmap bitmap = MediaStore.Images.Media.getBitmap(mContext.getContentResolver(), temp);
        viewHolder.imgViewIcon.setImageBitmap(bitmap);
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView imgViewIcon;

        public ViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            imgViewIcon = (ImageView) itemLayoutView.findViewById(R.id.imageView);
        }
    }


    @Override
    public int getItemCount() {
        return itemsData.length;
    }
}
*/