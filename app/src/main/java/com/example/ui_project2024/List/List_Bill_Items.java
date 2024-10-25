package com.example.ui_project2024.List;

public class List_Bill_Items {
    String id_bill, name, phone , id_studium, date, time, return_time;

    int total, status;
    public List_Bill_Items(String id_bill, String name,String phone, String id_studium, String date, String time, String return_time, int total, int status) {
        this.id_bill = id_bill;
        this.name = name;
        this.phone = phone;
        this.id_studium = id_studium;
        this.date = date;
        this.time = time;
        this.return_time = return_time;
        this.total = total;
        this.status = status;
    }

    public String getId_bill() {
        return id_bill;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getId_studium() {
        return id_studium;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getReturn_time() {
        return return_time;
    }

    public int getTotal() {
        return total;
    }

    public int getStatus() {
        return status;
    }
}
