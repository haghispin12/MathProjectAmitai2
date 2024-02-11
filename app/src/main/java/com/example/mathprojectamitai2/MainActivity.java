 package com.example.mathprojectamitai2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private Button btChallenge;

    private Button btUntiltwenty;

    private Button btMultiplicationTable;

    private TextView tvNumOne;

    private TextView tvNumTwo;

    private EditText etAnswer;

    private Button btCheck;

    private Button btsave;

    private Button btShowAllUsers;

     private ViewModel viewModel;

            Exercise exercise;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        exercise =new Exercise();
        initview();



    }


    public void initview(){
        btChallenge = findViewById(R.id.btChallenge);
        btUntiltwenty = findViewById(R.id.btUntilTwenty);
        btMultiplicationTable = findViewById(R.id.btMultiplicationTable);
        tvNumOne = findViewById(R.id.tvNumOne);
        tvNumTwo = findViewById(R.id.tvNumTwo);
        etAnswer = findViewById(R.id.etAnswer);
        btCheck = findViewById(R.id.btCheck);
        btsave = findViewById(R.id.btSave);
        btShowAllUsers = findViewById(R.id.btShowAllUsers);

        btChallenge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            exercise.setBtChallenge();
            update();
            //hag
            }
        });

        btUntiltwenty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            exercise.setBtUntiltwenty();
             update();
            }
        });

        btMultiplicationTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            exercise.setBtMultiplicationTable();
            update();
            }
        });





        btCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean b=exercise.check(etAnswer.getText().toString());
                if (b)
                    Toast.makeText(MainActivity.this, "success", Toast.LENGTH_LONG).show();

            else
                    Toast.makeText(MainActivity.this, "error", Toast.LENGTH_LONG).show();
            }
        });

        btsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btShowAllUsers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }



    public void update(){
        tvNumOne.setText(exercise.getNum1()+" ");
        tvNumTwo.setText(exercise.getNum2()+" ");
    }










}