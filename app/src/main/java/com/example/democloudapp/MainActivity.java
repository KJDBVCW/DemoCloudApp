package com.example.democloudapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Button;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;


import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;
public class MainActivity extends AppCompatActivity {

    EditText edtMaSV, edtHoTen, edtLop;
    Button btnSave, btnShow;
    FirebaseDatabase database;
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        edtMaSV = findViewById(R.id.edtMaSV);
        edtHoTen = findViewById(R.id.edtHoTen);
        edtLop = findViewById(R.id.edtLop);
        btnSave = findViewById(R.id.btnSave);
        btnShow = findViewById(R.id.btnShow);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("sinhvien");

        btnSave.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String maSV = edtMaSV.getText().toString().trim();
                String hoTen = edtHoTen.getText().toString().trim();
                String lop = edtLop.getText().toString().trim();

                if(!maSV.isEmpty() && !hoTen.isEmpty() && !lop.isEmpty()){
                    SinhVien sv = new SinhVien(maSV, hoTen, lop);
                    myRef.child(maSV).setValue(sv);

                    Toast.makeText(MainActivity.this, "Lưu thành công", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(MainActivity.this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ShowActivity.class);
                startActivity(intent);
            }
        });


    }
}