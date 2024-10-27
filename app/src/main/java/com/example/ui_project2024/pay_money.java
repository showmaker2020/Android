package com.example.ui_project2024;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.ui_project2024.DB_Manager.Data_Stadium;

public class pay_money extends AppCompatActivity {
    Button btn;
    private Data_Stadium db;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pay_money);
        String id = getIntent().getStringExtra("id");
        btn = findViewById(R.id.btn_xacnhan);
        db = new Data_Stadium(this);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(pay_money.this, MainActivity.class);
                db.update_check_bill(id);
                startActivity(intent);
            }
        });
    }
}