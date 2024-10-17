package com.example.ui_project2024.Login_Register;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.ui_project2024.R;

public class otp extends AppCompatActivity {
    EditText otp1, otp2, otp3, otp4;
    TextView resend, emailotp, otpphone;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_otp);
        otp1 = findViewById(R.id.otp1);
        otp2 = findViewById(R.id.otp2);
        otp3 = findViewById(R.id.otp3);
        otp4 = findViewById(R.id.otp4);
        resend = findViewById(R.id.resendotp);
        emailotp = findViewById(R.id.otp_email);
        otpphone = findViewById(R.id.phone_otp);
        String getemail = getIntent().getStringExtra("email");
        String getphone = getIntent().getStringExtra("phone");
        emailotp.setText(getemail);
        otpphone.setText(getphone);
        Button verity = findViewById(R.id.veritybtn);
    }
}