package com.example.ui_project2024;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.ui_project2024.Login_Register.Log_in;
import com.example.ui_project2024.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;

import java.util.Objects;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    FloatingActionButton fab;
    DrawerLayout drawer;
    BottomNavigationView bottomNavigationView;
    NavigationView nav;

    ActivityMainBinding binding;

    @SuppressLint({"CutPasteId", "MissingInflatedId", "NonConstantResourceId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        boolean isLoggedIn = getSharedPreferences("USER_PREF", MODE_PRIVATE)
                .getBoolean("isLoggedIn", false);
        if (!isLoggedIn) {
            Intent intent = new Intent(MainActivity.this, Log_in.class);
            startActivity(intent);
            finish();
            return;
        }
        // Nếu đã đăng nhập, tiếp tục xử lý trong MainActivity
        setContentView(R.layout.activity_main);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        fab = findViewById(R.id.fab);
        drawer = findViewById(R.id.drawer_layout);
        nav = findViewById(R.id.nav_view);
        nav.setNavigationItemSelectedListener((NavigationView.OnNavigationItemSelectedListener) this);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.open_nav,
                R.string.close_nav);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
//        if (savedInstanceState == null) {
//            getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,
//                    new HomeFragment()).commit();
//            nav.setCheckedItem(R.id.nav_home);
//        }
        bottomNavigationView.setOnItemSelectedListener(item -> {
            Log.d("BottomNav", "Item selected: " + item.getItemId());
            switch (item.getItemId()) {
                case R.id.home:
                    Log.d("aaa", "Home is Clicked");
                    replaceFragment(new HomeFragment());
                    break;
                case R.id.chart:
                    Log.d("aaa", "Chart is Clicked!!!!!!!");
                    replaceFragment(new ShortsFragment());
                    break;
                case R.id.bill:
                    Log.d("aaa", "Bill is Clicked");
                    String name = "";
                    String address= "";
                    int image = 0;
                    LibraryFragment libraryFragment = LibraryFragment.newInstance(name, address, image);
                    replaceFragment(libraryFragment);
                    break;
                case R.id.Exit:
                    Log.d("aaa", "Log out is Clicked");
                    getSharedPreferences("USER_PREF", MODE_PRIVATE)
                            .edit()
                            .putBoolean("isLoggedIn", false)
                            .apply();
                    Intent intent = new Intent(MainActivity.this, Log_in.class);
                    startActivity(intent);
                    finish();
                    Log.d("aaa", "Log out: thành công!!!");
            }
            return true;
        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showBottomDialog();
            }
        });
        nav.setNavigationItemSelectedListener(item -> {
            Log.d("NavigationClick", "Item selected: " + item.getItemId());
            switch (item.getItemId()) {
                case R.id.nav_home:
                    replaceFragment(new HomeFragment());
                    break;
                case R.id.nav_settings:
                    replaceFragment(new SettingsFragment());
                    break;
                case R.id.nav_share:
                    replaceFragment(new ShareFragment());
                    break;
                case R.id.nav_about:
                    replaceFragment(new AboutFragment());
                    break;
                case R.id.nav_logout:
                    Log.d("aaa", "Log out is Clicked");
                    getSharedPreferences("USER_PREF", MODE_PRIVATE)
                            .edit()
                            .putBoolean("isLoggedIn", false)
                            .apply();
                    Intent intent = new Intent(MainActivity.this, Log_in.class);
                    startActivity(intent);
                    finish();
                    Log.d("aaa", "Log out: thành công!!!");
                    break;
            }
            return false;
        });
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }
    private void showBottomDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.bottomsheetlayout);
        LinearLayout videoLayout = dialog.findViewById(R.id.layoutVideo);
        LinearLayout shortsLayout = dialog.findViewById(R.id.layoutShorts);
        LinearLayout liveLayout = dialog.findViewById(R.id.layoutLive);
        ImageView cancelButton = dialog.findViewById(R.id.cancelButton);
        videoLayout.setOnClickListener(v -> {
            dialog.dismiss();
            Log.d("aaa", "Video is Clicked");
        });
        shortsLayout.setOnClickListener(v -> {
            dialog.dismiss();
            Log.d("aaa", "Shorts is Clicked");
        });
        liveLayout.setOnClickListener(v -> {
            dialog.dismiss();
            Log.d("aaa", "Live is Clicked");
        });
        cancelButton.setOnClickListener(view -> dialog.dismiss());
        dialog.show();
        Objects.requireNonNull(dialog.getWindow()).setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.getWindow().setGravity(Gravity.BOTTOM);
    }
    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Log.d("NavigationClick", "Item selected: " + item.getItemId());
        switch (item.getItemId()) {
            case R.id.nav_home:
                replaceFragment(new HomeFragment());
                break;
            case R.id.nav_settings:
                replaceFragment(new SettingsFragment());
                break;
            case R.id.nav_share:
                replaceFragment(new ShareFragment());
                break;
            case R.id.nav_about:
                replaceFragment(new AboutFragment());
                break;
            case R.id.nav_logout:
                Log.d("aaa", "Log out is Clicked");
                getSharedPreferences("USER_PREF", MODE_PRIVATE)
                        .edit()
                        .putBoolean("isLoggedIn", false)
                        .apply();
                Intent intent = new Intent(MainActivity.this, Log_in.class);
                startActivity(intent);
                finish();
                Log.d("aaa", "Log out: thành công!!!");
                break;
        }
        return false;
    }

}
