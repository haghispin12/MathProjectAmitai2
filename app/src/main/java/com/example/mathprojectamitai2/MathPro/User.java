package com.example.mathprojectamitai2.MathPro;

import android.graphics.Bitmap;
import android.net.Uri;
import android.widget.ImageView;
import android.widget.TextView;

public class User {
    private String name;
    private int score;
    private int rate;
    private long id;
    private Bitmap bitmap;
    private Uri uri;
    private ImageView ivUserPic;
    private TextView tvUserName;
    private TextView tvUserRate;




    public User(String name, int rating, long id, Bitmap bitmap, int score) {
        this.name=name;
        this.rate=rating;
        this.id=id;
        this.bitmap=bitmap;
        this.score=score;
    }

    public User(){

    }

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


}
