package com.example.mathprojectamitai2;

import android.graphics.Bitmap;
import android.net.Uri;

public class User {
    private String name;
    private int score;
    private int rate;
    private long id;
    private Bitmap bitmap;
    private Uri uri;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Uri getUri() {
        return uri;
    }

    public void setUri(Uri uri) {
        this.uri = uri;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public User(String name, int score, int rate, long id, Bitmap bitmap) {
        this.name = name;
        this.score = score;
        this.rate = rate;
        this.id = id;
        this.bitmap = bitmap;
    }
}
