package com.example.mathprojectamitai2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.Firebase;
import com.google.firebase.firestore.FirebaseFirestore;

public class LoginProActivity extends AppCompatActivity {
    private Button btLogin;
    private Button btConnected;
    private TextView tvNameOfGame;
    private EditText etEmail;
    private EditText etPassowrd;
    private Button btSubmit;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_pro);
        initview();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        int n=10;
    }


    public void initview(){
        btLogin = findViewById(R.id.btLogin);
        btConnected = findViewById(R.id.btConnected);
        tvNameOfGame = findViewById(R.id.tvNameOfGame);
        etEmail = findViewById(R.id.etEmail);
        etPassowrd = findViewById(R.id.etPassowrd);
        btSubmit = findViewById(R.id.btSubmit);

        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btConnected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }





























}