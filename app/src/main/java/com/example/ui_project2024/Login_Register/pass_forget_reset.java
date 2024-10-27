package com.example.ui_project2024.Login_Register;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.ui_project2024.DB_Manager.Data_Stadium;
import com.example.ui_project2024.R;
import com.example.ui_project2024.databinding.ActivityRegisterBinding;

public class pass_forget_reset extends AppCompatActivity {
    ActivityRegisterBinding binding;
    private boolean passshow_2 = false;
    private boolean passconfirmShow_2 = false;
    ImageView passicon_2, passiconconfirm_2;
    AppCompatButton singupBtn_2;
    EditText passEt_2, passEtConfirm_2;
    Data_Stadium db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pass_forget_reset);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        db = new Data_Stadium(this);
        passEt_2 = findViewById(R.id.pass_et_2);
        passEtConfirm_2 = findViewById(R.id.pass_et_confirm_2);
        passicon_2 = findViewById(R.id.pass_icon_2);
        passiconconfirm_2 = findViewById(R.id.pass_icon_confirm_2);
        singupBtn_2 = findViewById(R.id.btn_reset_pass_2);
        String getemail = getIntent().getStringExtra("email");
        passicon_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(passshow_2){
                    passshow_2 = false;
                    passEt_2.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    passicon_2.setImageResource(R.drawable.show);
                }else{
                    passshow_2 = true;
                    passEt_2.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    passicon_2.setImageResource(R.drawable.hide);
                }
                passEt_2.setSelection(passEt_2.getText().length());
            }
        });

        passiconconfirm_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(passconfirmShow_2){
                    passconfirmShow_2 = false;
                    passEtConfirm_2.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    passiconconfirm_2.setImageResource(R.drawable.show);
                }else{
                    passconfirmShow_2 = true;
                    passEtConfirm_2.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    passiconconfirm_2.setImageResource(R.drawable.hide);
                }
                passEtConfirm_2.setSelection(passEtConfirm_2.getText().length());
            }
        });
        singupBtn_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String password = passEt_2.getText().toString();
                String confirmPassword = passEtConfirm_2.getText().toString();
                if(password.equals("")||confirmPassword.equals("")){
                    Log.d("aaa", "onClick: Enter full fields!!!");
                }
                else {
                    if(password.equals(confirmPassword)){
                        Log.d("aaa", "Password is update successfully");
                        db.updatepass( getemail, password);
                        Intent intent = new Intent(pass_forget_reset.this, Log_in.class);
                        startActivity(intent);
                    }
                    else {
                        Log.d("aaa", "Password is not matching");
                    }

                }
            }
        });

    }
}