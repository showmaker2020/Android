package com.example.ui_project2024;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.example.ui_project2024.DB_Manager.Data_Stadium;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ShortsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ShortsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    public static ShortsFragment newInstance(String param1, String param2) {
        ShortsFragment fragment = new ShortsFragment();
        Bundle args = new Bundle();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_shorts, container, false);
        PieChart pieChart = view.findViewById(R.id.pieChart);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        EditText time1 = view.findViewById(R.id.time1);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        EditText time2 = view.findViewById(R.id.time2);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        ImageButton imgbtn = view.findViewById(R.id.search_btn);
        Spinner spinner = view.findViewById(R.id.spinner_selection);
        // Log.d("SpinnerCheck", "Selected item: " + spinner.getSelectedItem().toString());
        if(spinner.getSelectedItem().toString().equalsIgnoreCase("Stadium")){
            imgbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String date1 = time1.getText().toString();
                    String date2 = time2.getText().toString();
                    Data_Stadium db = new Data_Stadium(getActivity());
                    ArrayList<PieEntry> entries = db.getchart(date1, date2);
                    PieDataSet dataSet = new PieDataSet(entries, "Các sân vận động");
                    dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
                    pieChart.setCenterText("Biểu đồ phần trăm tu Staidum\n " + date1 + " to " + date2);
                    PieData data = new PieData(dataSet);
                    data.setValueFormatter(new PercentFormatter(pieChart));
                    pieChart.setUsePercentValues(true);
                    pieChart.setData(data);
                    pieChart.invalidate();
                    time1.setText("");
                    time2.setText("");
                }
            });
        } else {
            imgbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String date1 = time1.getText().toString();
                    String date2 = time2.getText().toString();
                    Data_Stadium db = new Data_Stadium(getActivity());
                    ArrayList<PieEntry> entries_service = db.getServiceDetails(date1, date2);
                    PieDataSet dataSet = new PieDataSet(entries_service, "Các dich vu");
                    dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
                    pieChart.setCenterText("Biểu đồ phần trăm tu Service\n " + date1 + " to " + date2);
                    PieData data = new PieData(dataSet);
                    data.setValueFormatter(new PercentFormatter(pieChart));
                    pieChart.setUsePercentValues(true);
                    pieChart.setData(data);
                    pieChart.invalidate();
                    time1.setText("");
                    time2.setText("");
                }
            });
        }
        time1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        getContext(),
                        (view1, selectedYear, selectedMonth, selectedDay) -> {
                            String selectedDate = selectedDay + "/" + (selectedMonth + 1) + "/" + selectedYear;
                            time1.setText(selectedDate);
                        },
                        year, month, day);
                datePickerDialog.show();
            }
        });
        time2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        getContext(),
                        (view1, selectedYear, selectedMonth, selectedDay) -> {
                            String selectedDate = selectedDay + "/" + (selectedMonth + 1) + "/" + selectedYear;
                            time2.setText(selectedDate);
                        },
                        year, month, day);
                datePickerDialog.show();
            }
        });
        return view;
    }
}