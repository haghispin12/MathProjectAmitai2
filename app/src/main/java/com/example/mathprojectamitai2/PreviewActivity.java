package com.example.mathprojectamitai2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.google.firebase.firestore.QuerySnapshot;

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

    private void checkMap(){
        Intent inn = new Intent(this, pro_map.class);
        startActivity(inn);
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
//                public void GameMode(game games) {
//
//                }
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
                checkMap();
            }
        });


    }

















}