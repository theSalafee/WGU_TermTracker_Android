package com.sakeenahstudios.wgutermtrackerandroid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class TermsActivity extends AppCompatActivity {
    public  static final  int NEW_WORD_ACTIVITY_REQUEST_CODE = 1;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowCustomEnabled(true);

        final ListView termList = findViewById(R.id.termList);
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("Term One : 01/01/21 - 06/30/2021");
        arrayList.add("Term Two : 01/01/21 - 06/30/2021");
        arrayList.add("Term Three : 01/01/21 - 06/30/2021");
        arrayList.add("Term Four : 01/01/21 - 06/30/2021");
        arrayList.add("Term Five : 01/01/21 - 06/30/2021");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayList);

        termList.setAdapter(arrayAdapter);

        termList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick (AdapterView<?> parent, View view, int position, long id) {
                String clickedItem = (String) termList.getItemAtPosition(position);
                Toast.makeText(TermsActivity.this, clickedItem, Toast.LENGTH_LONG).show();
            }
        });
        
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view) {
                Intent intent = new Intent(TermsActivity.this, AddTermActivity.class);
                startActivityForResult(intent, NEW_WORD_ACTIVITY_REQUEST_CODE);
            }
        });
    }
    @Override
    public  boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
