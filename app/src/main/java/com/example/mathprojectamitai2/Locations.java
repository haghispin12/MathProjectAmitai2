package com.example.mathprojectamitai2;

public class Locations {
private String name;
private double latitiude;
private double longitude;

public Locations(String name, double latitiude, double longitude) {
this.name = name;
this.latitiude = latitiude;
this.longitude = longitude;
}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLatitiude() {
        return latitiude;
    }

    public void setLatitiude(double latitiude) {
        this.latitiude = latitiude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
