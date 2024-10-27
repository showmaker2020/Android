package com.example.ui_project2024.Login_Register;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

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
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailcheck = email_or_phone.getText().toString().trim(); // Lấy giá trị email hoặc số điện thoại mỗi khi nhấn nút

                if (emailcheck.isEmpty()) {
                    Log.d("aaa", "Vui lòng nhập email hoặc số điện thoại");
                } else if (db.forget_pass(emailcheck)) {
                    Log.d("aaa", "Gửi thành công");
                    String phone = db.get_phone(emailcheck);
                    if (phone != null && !phone.isEmpty()) {
                        Intent intent = new Intent(Forget.this, otp.class);
                        intent.putExtra("email", emailcheck);
                        intent.putExtra("phone", phone);
                        startActivity(intent);
                    } else {
                        Log.d("aaa", "Số điện thoại không hợp lệ hoặc không tìm thấy");
                    }
                } else {
                    Log.d("aaa", "Gửi thất bại hoặc email không hợp lệ");
                }
            }
        });
    }
}
