package com.example.rhp8r.final_project_final_spearow;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.support.v7.app.AppCompatActivity;
/**
 * Created by rhp8r on 12/1/2017.
 */

public class AddLink extends AppCompatActivity{
    EditText url;
    EditText label;

    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        setContentView(R.layout.add_link);
        url = (EditText) findViewById(R.id.url);
        label = (EditText) findViewById(R.id.label);

    }
    public void sendMessage(View view) {
        Button current = (Button) view;
        Intent intent2 = new Intent();
        Bundle b = new Bundle();
        b.putString("url", url.getText().toString());
        b.putString("label", label.getText().toString());
        intent2.putExtras(b);
        setResult(RESULT_OK, intent2);
        finish();
    }
}
