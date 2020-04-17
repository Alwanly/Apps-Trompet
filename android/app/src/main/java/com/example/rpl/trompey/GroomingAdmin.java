package com.example.rpl.trompey;

public class GroomingAdmin {
    String name,status;
    int Image;

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }

    public int getImage() {
        return Image;
    }

    public GroomingAdmin(String name, String status, int image) {
        this.name = name;
        this.status = status;
        Image = image;
    }
}
