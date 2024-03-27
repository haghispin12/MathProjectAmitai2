package com.example.mathprojectamitai2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class RateActivity extends AppCompatActivity {

    private SeekBar sbRate;
    private Button btSaveRate;
    private TextView tvNumOfRate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate);
        initview();
    }



    public void initview(){
        sbRate = findViewById(R.id.sbRate);
        btSaveRate = findViewById(R.id.btSaveRate);
        tvNumOfRate = findViewById(R.id.tvNumOfRate);

        sbRate.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                tvNumOfRate.setText(seekBar.getProgress()+"");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        btSaveRate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inn = new Intent();
                inn.putExtra("RateKey", sbRate.getProgress());
                setResult(RESULT_OK, inn);
                finish();
            }
        });

    }
}