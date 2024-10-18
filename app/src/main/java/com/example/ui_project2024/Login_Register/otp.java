package com.example.ui_project2024.Login_Register;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.inputmethodservice.InputMethodService;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.ui_project2024.DB_Manager.Database;
import com.example.ui_project2024.R;
import com.google.android.material.internal.TextWatcherAdapter;

public class otp extends AppCompatActivity {
    EditText otp1, otp2, otp3, otp4;
    TextView resend, emailotp, otpphone;
    Database database;
    private boolean resendEnable = false;
    private int resendTime = 60;
    private int selectotppos = 0;
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
        otp1.addTextChangedListener(textWatcher);
        otp2.addTextChangedListener(textWatcher);
        otp3.addTextChangedListener(textWatcher);
        otp4.addTextChangedListener(textWatcher);

        startcounttime();
        resend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(resendEnable){
                    startcounttime();
                }
            }
        });
        verity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String genOtp = otp1.getText().toString() + otp2.getText().toString()
                        + otp3.getText().toString() + otp4.getText().toString();
                Log.d("aaa", genOtp);
                if(genOtp.length() == 4){
                    database = new Database(otp.this);
                    if(database.onSubmitOtpClicked(String.valueOf(emailotp), genOtp)){
                        database.updateCheckColumn(String.valueOf(emailotp));
                        Log.d("aaa", "Ma otp hop le");
                        Intent intent = new Intent(getApplicationContext(), Log_in.class);
                        startActivity(intent);
                    } else {
                        Log.d("aaa", "Ma otp khong hop le");
                    }
                }
            }
        });

    }

    private void showkeyboard(EditText otp_edittext){
        otp_edittext.requestFocus();
        InputMethodManager ipmethod = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        ipmethod.showSoftInput(otp_edittext, InputMethodManager.SHOW_IMPLICIT);
    }

    private void startcounttime(){
        resendEnable = false;
        resend.setTextColor(Color.parseColor("#990000"));
        new CountDownTimer(resendTime * 1000, 1000){

            @Override
            public void onTick(long millisUntilFinished) {
                resend.setText("Resend code (" + (millisUntilFinished/1000) + ")");

            }

            @Override
            public void onFinish() {
                resendEnable = true;
                resend.setText("Resend code");
                resend.setTextColor(getResources().getColor(R.color.primary));
            }
        }.start();

    }
    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if(s.length() > 0){
                if(selectotppos == 0){
                    selectotppos = 1;
                    showkeyboard(otp2);
                } else if(selectotppos == 1){
                    selectotppos = 2;
                    showkeyboard(otp3);
                } else if(selectotppos == 2){
                    selectotppos = 3;
                    showkeyboard(otp4);
                }
            }
        }
    };

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_DEL){
            if(selectotppos == 3){
                selectotppos = 2;
                showkeyboard(otp3);
            } else if(selectotppos == 2){
                selectotppos = 1;
                showkeyboard(otp2);
            } else if(selectotppos == 1){
                selectotppos = 0;
                showkeyboard(otp1);
            }
            return true;
        }
        else {
            return super.onKeyUp(keyCode, event);
        }
    }
}