package com.example.mathprojectamitai2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

public class RateActivity extends AppCompatActivity {

    private SeekBar sbRate;
    private Button btSaveRate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate);
        initview();
    }



    public void initview(){
        sbRate = findViewById(R.id.sbRate);
        btSaveRate = findViewById(R.id.btSaveRate);

        btSaveRate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inn = new Intent();
                inn.putExtra("Rate Key", sbRate.getProgress());
                setResult(RESULT_OK, inn);
                finish();
            }
        });

    }
}