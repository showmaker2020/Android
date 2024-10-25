package com.example.ui_project2024.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ui_project2024.List.List_Bill;
import com.example.ui_project2024.List.List_Bill_Items;
import com.example.ui_project2024.R;

import java.util.ArrayList;

public class Bill_Adapter extends RecyclerView.Adapter<Bill_Adapter.ViewHolder> {
    private final ArrayList<List_Bill_Items> billList;
    private final Context context;
    public Bill_Adapter(Context context, ArrayList<List_Bill_Items> billList) {
        this.context = context;
        this.billList = billList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_stadium, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        List_Bill_Items listData = billList.get(position);
        if (listData != null) {
            holder.id.setText(listData.getId_bill());
            holder.name.setText(listData.getName());
            holder.phone.setText(listData.getPhone());
            holder.id_stadium.setText(listData.getId_studium());
            holder.date.setText(listData.getDate());
            holder.time_pick.setText(listData.getTime());
            holder.time_return.setText(listData.getReturn_time());
            holder.total.setText(String.valueOf(listData.getTotal()));
            // holder.status.setText(String.valueOf(listData.getStatus()));
        }
    }

    @Override
    public int getItemCount() {
        return billList.size();
    }public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView id, name, phone, id_stadium,date,  time_pick, time_return, total, string;
        Switch status;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.tv_list_id_bill);
            name = itemView.findViewById(R.id.tv_list_bill_name_customer);
            phone = itemView.findViewById(R.id.tv_list_bill_phone);
            id_stadium = itemView.findViewById(R.id.tv_list_bill_id_stadium);
            time_pick = itemView.findViewById(R.id.tv_list_bill_time_pick);
            date = itemView.findViewById(R.id.tv_list_bill_date);
            time_return = itemView.findViewById(R.id.tv_list_bill_time_return);
            total = itemView.findViewById(R.id.tv_list_bill_total);
            status = itemView.findViewById(R.id.switch_list_bill_status);
            string = itemView.findViewById(R.id.tv_list_bill_status_text);
//            Log.d("Bill_Adapter", "ViewHolder created: " + status.getText().toString());
//            String statusText = status.getText().toString().trim();
//            if (!statusText.isEmpty()) {
//                try {
//                    int statusValue = Integer.parseInt(statusText);
//                    if (statusValue == 0) {
//                        status.setChecked(false);
//                        string.setText("Chưa thanh toán");
//                    } else {
//                        status.setChecked(true);
//                        string.setText("Đã thanh toán");
//                    }
//                } catch (NumberFormatException e) {
//                    status.setChecked(false);
//                    string.setText("Dữ liệu không hợp lệ");
//                }
//            } else {
//                status.setChecked(false);
//                string.setText("Trạng thái không xác định");
//            }

        }


    }
}
