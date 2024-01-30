 package com.example.mathprojectamitai2;

import androidx.appcompat.app.AppCompatActivity;

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




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initview();
    }
    private int num1;
    private int num2;

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
            setBtChallenge();
            update();
            }
        });

        btUntiltwenty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            setBtUntiltwenty();
             update();
            }
        });

        btMultiplicationTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            setBtMultiplicationTable();
            update();
            }
        });

        tvNumOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            update();
            }
        });

        tvNumTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                update();
            }
        });

        etAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (check())
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

public void setBtChallenge(){
        Random r = new Random();
        num1 = r.nextInt(8) + 1;
        num2 = r.nextInt(89) + 10;
    }

    public void setBtUntiltwenty(){
        Random r = new Random();
        num1 = r.nextInt(8) + 1;
        num2 = r.nextInt(10) + 10;
    }

    public void setBtMultiplicationTable(){
        Random r = new Random();
        num1 = r.nextInt(8) + 1;
        num2 = r.nextInt(8) + 1;
    }

    public void update(){
        tvNumOne.setText(num1+" ");
        tvNumTwo.setText(num2+" ");
    }

    public boolean check(){
       int result = num1+num2;
        String res = result + "";
        if(res.equals(etAnswer.getText().toString())) {
            return true;
        }else
        return false;
    }



}