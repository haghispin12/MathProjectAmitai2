package com.example.mathprojectamitai2;

import static androidx.core.content.ContextCompat.startActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.Firebase;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class LoginProActivity extends AppCompatActivity {
    private Button btLogin;
    private Button btConnected;
    private TextView tvNameOfGame;
    private EditText etEmail;
    private EditText etPassowrd;
    private Button btSubmit;

    private FirebaseAuth auth;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_pro);
        initview();
        //FirebaseApp.initializeApp(this);
        //FirebaseFirestore db = FirebaseFirestore.getInstance();
//        FirebaseFirestore db = FirebaseFirestore.getInstance();
//        int n=10;
         auth = FirebaseAuth.getInstance();
         if(auth.getCurrentUser()!=null){
            startGame();
         }
    }



    private void startGame(){
        Intent inn = new Intent(this, PreviewActivity.class);
        inn.putExtra("userName",auth.getCurrentUser().getEmail());
        startActivity(inn);
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