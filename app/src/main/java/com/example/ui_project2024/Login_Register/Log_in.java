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
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.ui_project2024.DB_Manager.Mydatabase_Login_register;
import com.example.ui_project2024.MainActivity;
import com.example.ui_project2024.R;
import com.example.ui_project2024.databinding.ActivityLogInBinding;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

public class Log_in extends AppCompatActivity {
    ActivityLogInBinding binding;
    Mydatabase_Login_register db;
    private boolean showpass = false;

    GoogleSignInOptions gso;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLogInBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        EditText emailname = findViewById(R.id.email_name);
        EditText passEt = findViewById(R.id.pass_et);
        ImageView passicon = findViewById(R.id.pass_icon);
        db = new Mydatabase_Login_register(this);
        passicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(showpass){
                    showpass = false;
                    passEt.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    passicon.setImageResource(R.drawable.show);
                }else{
                    showpass = true;
                    passEt.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    passicon.setImageResource(R.drawable.hide);
                }
                passEt.setSelection(passEt.getText().length());
            }
        });
        binding.signinBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String email = binding.emailName.getText().toString();
                String password = binding.passEt.getText().toString();
                getSharedPreferences("USER_PREF", MODE_PRIVATE)
                        .edit()
                        .putBoolean("isLoggedIn", true)
                        .apply();
                if(email.equals("") || password.equals("")){
                    Log.d("aaa", "Please enter all the fields");
                }
                else {
                    Boolean checkCredentials = db.checkEmailPassword(email, password);
                    if(checkCredentials == true){
                        Log.d("aaa", "Login successful");
                        Intent intent  = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                    }else{
                        Log.d("aaa", "Login failed");
                    }
                }
            }
        });
        binding.SignupBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Register.class);
                startActivity(intent);
            }
        });


    }
}