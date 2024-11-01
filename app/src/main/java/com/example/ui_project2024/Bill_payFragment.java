package com.example.ui_project2024;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;

import com.example.ui_project2024.Adapter.Bill_Adapter;
import com.example.ui_project2024.DB_Manager.Data_Stadium;
import com.example.ui_project2024.List.List_Bill_Items;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Bill_payFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Bill_payFragment extends Fragment {

    private ArrayList<List_Bill_Items> billList;

    public static Bill_payFragment newInstance(String name, String phone, String sevrice, String timePick, String timeReturn, int total, int status) {
        Bill_payFragment fragment = new Bill_payFragment();
        Bundle args = new Bundle();
        args.putString("name", name);
        args.putString("phone", phone);
        args.putString("service", sevrice);
        args.putString("timePick", timePick);
        args.putString("timeReturn", timeReturn);
        args.putInt("total", total);
        args.putInt("status", status);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Data_Stadium dataStadium = new Data_Stadium(getContext());
        billList = dataStadium.getBillsPayment();
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bill_pay, container, false);
//        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
//        TextView textView = view.findViewById(R.id.text_view_billpay);
//        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
//        RecyclerView recyclerView = view.findViewById(R.id.recyclerViews_billpay);
//        textView.setText("Danh sách hóa đơn da thanh toan");
//        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//        Bill_Adapter billAdapter = new Bill_Adapter(getContext(), billList);
//        recyclerView.setAdapter(billAdapter);
        return view;
    }
}