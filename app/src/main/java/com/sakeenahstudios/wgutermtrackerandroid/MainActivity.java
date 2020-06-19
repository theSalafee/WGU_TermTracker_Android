package com.sakeenahstudios.wgutermtrackerandroid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {
    public static final int NEW_WORD_ACTIVITY_REQUEST_CODE = 1;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button myButton = findViewById(R.id.termsBtn);
        myButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick (View v) {
                startActivity(new Intent(MainActivity.this, TermsActivity.class));
            }
        });
    }
}