package com.example.ems;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {
    TextView dt_id;
    TextView dt_name;
    TextView dt_detail;

    String id;
    String name;
    String detail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        getSupportActionBar().setTitle("등록");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        dt_id = findViewById(R.id.dt_id);
        dt_name = findViewById(R.id.dt_name);
        dt_detail = findViewById(R.id.dt_detail);

        Intent intent = getIntent();

        id = intent.getExtras().getString("id");
        name = intent.getExtras().getString("name");
        detail = intent.getExtras().getString("detail");

        dt_id.setText(id);
        dt_name.setText(name);
        dt_detail.setText(detail);


    }
}