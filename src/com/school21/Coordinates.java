package com.school21;

public class Coordinates {
    private int longitude;
    private int latitude;
    private int height;

    public Coordinates(int longitude, int latitude, int height) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.height = height;
    }

    public int getLongitude() {
        return longitude;
    }

    public int getLatitude() {
        return latitude;
    }

    public int getHeight() {
        return height;
    }

    public void setLongitude(int longitude) {
        this.longitude = longitude;
        if (this.longitude < 1)
            this.longitude = 1;
    }

    public void setLatitude(int latitude) {
        this.latitude = latitude;
        if (this.latitude < 1)
            this.latitude = 1;
    }

    public void setHeight(int height) {
        this.height = height;
        if (height < 0)
            height = 0;
        if (height > 100)
            height = 100;
    }
}
