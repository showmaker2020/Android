package com.example.ui_project2024.Login_Register;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.BoringLayout;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.ui_project2024.DB_Manager.Data_Stadium;
import com.example.ui_project2024.DB_Manager.Database;
import com.example.ui_project2024.DB_Manager.Mydatabase_Login_register;
import com.example.ui_project2024.R;
import com.example.ui_project2024.databinding.ActivityLogInBinding;
import com.example.ui_project2024.databinding.ActivityRegisterBinding;

public class Register extends AppCompatActivity {
    ActivityRegisterBinding binding;
    Data_Stadium database;
    private boolean passshow = false;
    private boolean passconfirmShow = false;
    EditText emailEt, passEt, passEtConfirm, fullnameEt, mobliePhone;
    ImageView passicon, passiconconfirm;
    AppCompatButton singupBtn;
    TextView signinBtn;
    private static final String TAG = "Register";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        fullnameEt = findViewById(R.id.fullname_et);
        emailEt = findViewById(R.id.email_et);
        passEt = findViewById(R.id.pass_et);
        passEtConfirm = findViewById(R.id.pass_et_confirm);
        mobliePhone = findViewById(R.id.moblie_phone);
        passicon = findViewById(R.id.pass_icon);
        passiconconfirm = findViewById(R.id.pass_icon_confirm);
        passicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(passshow){
                    passshow = false;
                    passEt.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    passicon.setImageResource(R.drawable.show);
                }else{
                    passshow = true;
                    passEt.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    passicon.setImageResource(R.drawable.hide);
                }
                passEt.setSelection(passEt.getText().length());
            }
        });
        passiconconfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(passconfirmShow){
                    passconfirmShow = false;
                    passEtConfirm.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    passiconconfirm.setImageResource(R.drawable.show);
                }else{
                    passconfirmShow = true;
                    passEtConfirm.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    passiconconfirm.setImageResource(R.drawable.hide);
                }
                passEtConfirm.setSelection(passEtConfirm.getText().length());
            }
        });
        database = new Data_Stadium(this);
        binding.singupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = binding.emailEt.getText().toString();
                String password = binding.passEt.getText().toString();
                String confirmPassword = binding.passEtConfirm.getText().toString();
                String phone = binding.mobliePhone.getText().toString();
                String name = binding.fullnameEt.getText().toString();
                if(email.equals("")||password.equals("")||confirmPassword.equals("")||name.equals("")){
                    Log.d("aaa", "onClick: Enter full fields!!!");
                }
                else {
                    // credentials: thong tin xac thuc
                    if(password.equals(confirmPassword)){
                        Boolean checkUserEmail = database.checkEmail(email);
                        if(checkUserEmail == false){
                            Boolean insert = database.insertdata_user(name, phone , email , password);
                            if(insert == true){
                                Log.d("aaa", "Sign up successfully!!!");
                                Intent i = new Intent(Register.this, otp.class);
                                i.putExtra("email", email);
                                i.putExtra("phone", phone);
                                startActivity(i);
                            }
                            else{
                                Log.d("aaa", "Sign up failed!!!");
                            }
                        }
                        else {
                            Log.d("aaa", "User already exists! Please login");
                        }
                    }
                    else {
                        Log.d("aaa", "Password is not matching");
                    }

                }
            }
        });
        binding.SigninBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Log_in.class);
                startActivity(i);
            }
        });




    }
}