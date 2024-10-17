package com.example.ui_project2024;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ui_project2024.Adapter.Bill_Adapter; // Sử dụng BillAdapter mới
import com.example.ui_project2024.List.List_Bill;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class LibraryFragment extends Fragment {

    private ArrayList<List_Bill> billList;

    public LibraryFragment() {
    }

    public static LibraryFragment newInstance(String name, String address, int image) {
        LibraryFragment fragment = new LibraryFragment();
        Bundle args = new Bundle();
        args.putString("name", name);
        args.putString("address", address);
        args.putInt("image", image);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        billList = new ArrayList<>();
        billList.add(new List_Bill("Hóa đơn 1", "Địa chỉ 1", R.drawable.aklogo_1));
        billList.add(new List_Bill("Hóa đơn 2", "Địa chỉ 2", R.drawable.aklogo_1));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_library, container, false);

        TextView textView = view.findViewById(R.id.text_view);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerViews);

        textView.setText("Danh sách hóa đơn");
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        Bill_Adapter billAdapter = new Bill_Adapter(getContext(), billList);
        recyclerView.setAdapter(billAdapter);
        return view;
    }
}
