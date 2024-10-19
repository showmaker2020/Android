package com.example.ui_project2024.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ui_project2024.List.List_Bill;
import com.example.ui_project2024.R;

import java.util.ArrayList;

public class Bill_Adapter extends RecyclerView.Adapter<Bill_Adapter.ViewHolder> {
    private final ArrayList<List_Bill> billList;
    private final Context context;
    public Bill_Adapter(Context context, ArrayList<List_Bill> billList) {
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
        List_Bill listData = billList.get(position);
        if (listData != null) {
            holder.listImage.setImageResource(listData.getImg());
            holder.name.setText(listData.getName());
            holder.address.setText(listData.getAddress());
        }
    }

    @Override
    public int getItemCount() {
        return billList.size();
    }public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView listImage;
        TextView name;
        TextView address;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            listImage = itemView.findViewById(R.id.img_stadium);
            name = itemView.findViewById(R.id.tv_name_stadium);
            address = itemView.findViewById(R.id.tv_address);
        }
    }
}
