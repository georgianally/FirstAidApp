package com.georgia.firstaid.ui;

import android.content.ContentValues;
import android.content.Intent;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.georgia.firstaid.R;
import com.georgia.firstaid.model.DatabaseHelper;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button startButton = (Button) findViewById(R.id.startButton);
        Button viewReportsButton = (Button) findViewById(R.id.viewReportsButton);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startFirstAid();
            }
        });
    }

    private void startFirstAid() {
        Intent intent = new Intent(this, FirstAidActivity.class);
        startActivity(intent);
    }
}
