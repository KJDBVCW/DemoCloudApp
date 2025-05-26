package com.example.democloudapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import java.util.ArrayList;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import androidx.annotation.NonNull;
public class ShowActivity extends AppCompatActivity {

    ListView listViewSinhVien;
    ArrayList<String> listSinhVien;
    ArrayAdapter<String> adapter;
    FirebaseDatabase database;
    DatabaseReference myRef;
    Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_show);

        btnBack = findViewById(R.id.btnBack);
        listViewSinhVien = findViewById(R.id.listViewSinhVien);
        listSinhVien = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listSinhVien);
        listViewSinhVien.setAdapter(adapter);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("sinhvien");

        myRef.addValueEventListener(new ValueEventListener(){
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listSinhVien.clear();
                for(DataSnapshot ds : snapshot.getChildren()){
                    SinhVien sv = ds.getValue(SinhVien.class);
                    if(sv != null){
                        String info = sv.maSV + " - " + sv.hoTen + " - " + sv.lop;

                        listSinhVien.add(info);
                    }
                }
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ShowActivity.this, "Lỗi kết nối", Toast.LENGTH_SHORT).show();

            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}