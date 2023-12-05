package com.example.ems;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.CharacterPickerDialog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class AddActivity extends AppCompatActivity implements RecyclerViewInterface {

    private ArrayList<Equipment_item> equipitems;
    private RecyclerView rv_equip;
    private FloatingActionButton fbtn_add;
    private ListAdapter listAdapter;

    private String tv_state = "대여 가능";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        getSupportActionBar().setTitle("등록");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setinit();

    }

    private void setinit() {

        rv_equip = findViewById(R.id.rv_equip);
        fbtn_add = findViewById(R.id.fbtn_add);
        equipitems = new ArrayList<>();
        listAdapter = new ListAdapter(equipitems, this, rv_equip);
        rv_equip.setAdapter(listAdapter);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(rv_equip.getContext(),new LinearLayoutManager(this).getOrientation());
        rv_equip.addItemDecoration(dividerItemDecoration);




        fbtn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //등록 팝업창 띄우기
                Dialog dialog = new Dialog(AddActivity.this, android.R.style.Theme_Material_Light_Dialog);
                dialog.setContentView(R.layout.dialog_add);
                EditText et_id = dialog.findViewById(R.id.et_id);
                EditText et_name = dialog.findViewById(R.id.et_name);
                EditText et_detail = dialog.findViewById(R.id.et_detail);
                Button btn_ok = dialog.findViewById(R.id.btn_ok);
                btn_ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Equipment_item item = new Equipment_item();
                        tv_state = "대여 가능";
                        item.setId(et_id.getText().toString());
                        item.setName(et_name.getText().toString());
                        item.setDetail(et_detail.getText().toString());
                        item.setState(tv_state);
                        equipitems.add(item);
                        listAdapter.notifyItemInserted(0);
                        listAdapter.notifyDataSetChanged();
                        dialog.dismiss();
                        Toast.makeText(AddActivity.this, "등록이 완료되었습니다.",Toast.LENGTH_SHORT).show();
                    }

                });

                dialog.show();
            }
        });

    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(AddActivity.this, DetailActivity.class);

        intent.putExtra("Id", equipitems.get(position).getId());
        intent.putExtra("Name", equipitems.get(position).getName());
        intent.putExtra("Detail", equipitems.get(position).getDetail());

        // DetailActivity로 이동 시 tv_state 값을 전달합니다.
        intent.putExtra("tv_state", tv_state);

        startActivity(intent);

    }


}