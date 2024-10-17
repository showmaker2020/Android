package com.example.ui_project2024.List;

import java.util.List;

public class List_Bill {
    String name, address;
    int img;
    public List_Bill(String name, String address, int img){
        this.name = name;
        this.address = address;
        this.img = img;
    }
    public String getName(){
        return name;
    }
    public String getAddress(){
        return address;
    }
    public int getImg(){
        return img;
    }
}
