package com.example.ems;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class DetailActivity extends AppCompatActivity {
    TextView dt_id;
    TextView dt_name;
    TextView dt_detail;

    String id;
    String name;
    String detail;
    String tv_state;

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

        id = intent.getExtras().getString("Id");
        name = intent.getExtras().getString("Name");
        detail = intent.getExtras().getString("Detail");
        tv_state = intent.getExtras().getString("tv_state");

        dt_id.setText(id);
        dt_name.setText(name);
        dt_detail.setText(detail);

        Button btnReturn = findViewById(R.id.btn_return);
        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // btn_return 버튼이 클릭되었을 때 실행될 코드
                updateEquipmentState_return();

                Toast.makeText(DetailActivity.this,"반납되었습니다.", Toast.LENGTH_SHORT).show();
            }
        });

        Button btnBorrow = findViewById(R.id.btn_borrow);

        btnBorrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateEquipmentState_borrow();

                Toast.makeText(DetailActivity.this, "대여되었습니다", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateEquipmentState_return() {
        // 대여 상태를 "대여 불가능"으로 업데이트
        String newEquipmentState = "대여 불가능";
        TextView dtState = findViewById(R.id.dt_state);
        dtState.setText(newEquipmentState);
    }

    private void updateEquipmentState_borrow() {
        // 대여 상태를 "대여 불가능"으로 업데이트
        String newEquipmentState = "대여 가능";
        TextView dtState = findViewById(R.id.dt_state);
        dtState.setText(newEquipmentState);
    }
}