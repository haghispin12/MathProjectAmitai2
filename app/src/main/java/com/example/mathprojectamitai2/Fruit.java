package com.example.mathprojectamitai2;

import android.widget.ImageView;
import android.widget.TextView;

public class Fruit {

private ImageView ivfruitlmg;

private TextView tvFruitName;

private String name;

private int drawable;

public Fruit(String name, int drawable){
    this.name = name;
    this.drawable = drawable;
}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDrawable() {
        return drawable;
    }

    public void setDrawable(int drawable) {
        this.drawable = drawable;
    }
}
