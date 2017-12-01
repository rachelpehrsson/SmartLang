package com.example.rhp8r.final_project_final_spearow;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


import static android.app.Activity.RESULT_OK;

public class TabPic extends Fragment {

    static final int TAKE_PHOTO_PERMISSION = 1;
    static final int REQUEST_TAKE_PHOTO = 2;
    static final int PICK_IMAGE_REQUEST = 3;
    static String langname;
    ImageView imageView;
    Button takePictureButton, imageButton;
    ArrayList<String> images;

    Uri file;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        //getActivity().setContentView(R.layout.pictab);
        final View rootView = inflater.inflate(R.layout.pictab, container, false);
        takePictureButton = (Button) rootView.findViewById(R.id.takePictureButton);
        imageButton = (Button) rootView.findViewById(R.id.imageLibraryButton);
        imageView = (ImageView) rootView.findViewById(R.id.imageView);
        //android.hardware.Camera camera = android.hardware.Camera.open();
        Intent intent = getActivity().getIntent();
        Bundle b = intent.getExtras();
        langname = b.getString("langName");
        // We are giving you the code that checks for permissions
        if (ContextCompat.checkSelfPermission(rootView.getContext(), android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            takePictureButton.setEnabled(false);
            ActivityCompat.requestPermissions(this.getActivity(), new String[] { android.Manifest.permission.CAMERA, android.Manifest.permission.WRITE_EXTERNAL_STORAGE }, TAKE_PHOTO_PERMISSION);
        }
//        takePictureButton.setEnabled(true);
        //Button button = (Button) view.findViewById(R.id.button1);
        takePictureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takePicture(v);
            }
        });

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getImageFromLibrary(v);
            }
        });



        return rootView;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        // This is called when permissions are either granted or not for the app
        // You do not need to edit this code.

        if (requestCode == TAKE_PHOTO_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED
                    && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                takePictureButton.setEnabled(true);
            }
        }
    }

//
public void takePicture(View view) {
    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
    file = Uri.fromFile(getOutputMediaFile());
    intent.putExtra(MediaStore.EXTRA_OUTPUT, file);

    startActivityForResult(intent, REQUEST_TAKE_PHOTO);


}
//
public void getImageFromLibrary(View view) {
    // Add code here to start the process of getting a picture from the library
    Intent intent = new Intent();
// Show only images, no videos or anything else
    intent.setType("image/*");
    intent.setAction(Intent.ACTION_GET_CONTENT);
// Always show the chooser (if there are multiple options available)
    startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
}
//
public void loadLanguageInfoFromDatabase(String langname) {
    DatabaseHelper mDbHelper = new DatabaseHelper(getActivity());
    SQLiteDatabase db = mDbHelper.getWritableDatabase();

    String[] projection = {
            "filename"
    };
    String sortOrder =
            "";
    String selection = "'langname= "+langname+"'";
    //String[] selectionargs = {langname};
    Cursor cursor = db.query(
            "photos",  // The table to query
            projection,                               // The columns to return
            selection,                                // The columns for the WHERE clause
            null,                            // The values for the WHERE clause
            null,                                     // don't group the rows
            null,                                     // don't filter by row groups
            sortOrder                                 // The sort order
    );
    int count = cursor.getCount();
    images = new ArrayList();
    while (cursor.moveToNext()) {
        images.add(cursor.getString(cursor.getColumnIndexOrThrow("filename")));
        //Log.i("DBData");
    }
    cursor.close();
}
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Add code here to handle results from both taking a picture or pulling
        // from the image gallery.

        if (requestCode == REQUEST_TAKE_PHOTO) {
            if (resultCode == RESULT_OK) {
                saveToDatabase(file.getEncodedPath(), langname);
                imageView.setImageURI(file);
            }

        }
        else if (requestCode == PICK_IMAGE_REQUEST) {

            if (resultCode == RESULT_OK && data != null && data.getData() != null) {

                file = data.getData();

                saveToDatabase(file.getEncodedPath(), langname);

                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), file);
                    // Log.d(TAG, String.valueOf(bitmap));
                    imageView.setImageBitmap(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }
    public void saveToDatabase(String filename, String lang) {
        // Add code here to save to the database
        DatabaseHelper mDbHelper = new DatabaseHelper(getActivity());
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
//
//        // Create a new map of values, where column names are the keys

        ContentValues values2 = new ContentValues();;
        values2.put("filename", filename);
        values2.put("langname", lang);
//

        long newRowId2;
//
        newRowId2 = db.insertWithOnConflict(
                "photos",
                null,
                values2, SQLiteDatabase.CONFLICT_IGNORE);


    }


    private static File getOutputMediaFile(){
        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), "CameraDemo");

        if (!mediaStorageDir.exists()){
            if (!mediaStorageDir.mkdirs()){
                return null;
            }
        }

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        return new File(mediaStorageDir.getPath() + File.separator +
                "IMG_"+ timeStamp + ".jpg");
    }
}
