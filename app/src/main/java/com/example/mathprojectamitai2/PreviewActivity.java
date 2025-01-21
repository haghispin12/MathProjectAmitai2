package com.example.mathprojectamitai2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class PreviewActivity extends AppCompatActivity {
    private Button btInvite;
    private TextView tvIdGame;
    private Button btGame;
    private EditText etIdGame;
    private Button btJoinGame;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview);
        initview();
    }

    public void initview(){
        btInvite = findViewById(R.id.btInvite);
        tvIdGame = findViewById(R.id.tvIdGame);
        btGame = findViewById(R.id.btGame);
        etIdGame = findViewById(R.id.etIdGame);
        btJoinGame = findViewById(R.id.btJoinGame);

        btInvite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btJoinGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


    }

















}