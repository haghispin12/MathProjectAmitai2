package com.example.mathprojectamitai2;

import static androidx.core.content.ContextCompat.startActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mathprojectamitai2.MathPro.MainActivity;
import com.google.firebase.Firebase;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class LoginProActivity extends AppCompatActivity {

    private TextView tvNameOfGame;
    private EditText etEmail;
    private EditText etPassowrd;
    private Button btSubmit;
    private Spinner spLogin;

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

        tvNameOfGame = findViewById(R.id.tvNameOfGame);
        etEmail = findViewById(R.id.etEmail);
        etPassowrd = findViewById(R.id.etPassowrd);
        btSubmit = findViewById(R.id.btSubmit);
        spLogin = findViewById(R.id.spLogin);


        btSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        spLogin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                String item = adapterView.getItemAtPosition(position).toString();
                Toast.makeText(LoginProActivity.this, "sellected: " + item, Toast.LENGTH_LONG).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("Log in");
        arrayList.add("sign in");





    }





























}