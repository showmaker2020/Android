package com.example.ui_project2024.Login_Register;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.ui_project2024.DB_Manager.Data_Stadium;
import com.example.ui_project2024.R;

public class Forget extends AppCompatActivity {
    EditText email_or_phone;
    AppCompatButton btn;
    Data_Stadium db = new Data_Stadium(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_forget);
        email_or_phone = findViewById(R.id.email_et_forget);
        btn = findViewById(R.id.send_btn_forget);
        String emailcheck = email_or_phone.getText().toString();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(email_or_phone.getText().toString().isEmpty()){
                    Log.d("aaa", "onCreate: Vui long nhap email");
                } else if(db.forget_pass(emailcheck)){
                    Log.d("aaa", "onCreate: Gui thanh cong");
                    String phone = db.get_phone(emailcheck);
                    Intent intent = new Intent(Forget.this, otp.class);
                    intent.putExtra("email", emailcheck);
                    intent.putExtra("phone", phone);
                    startActivity(intent);
                } else {
                    Log.d("aaa", "onCreate: Gui that bai or email khong hop le");
                }
            }
        });


    }
}