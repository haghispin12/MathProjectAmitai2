package com.example.mathprojectamitai2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActiv extends AppCompatActivity {
    private EditText etUserName;
    private Button btSubmit;
    Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initview();
       intent = new Intent(this, MainActivity.class);
        intent.putExtra("userKey", etUserName.getText().toString());
    }



    public void initview(){
    etUserName = findViewById(R.id.etUserName);
    btSubmit = findViewById(R.id.btSubmit);
    btSubmit.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
        startActivity(intent);
        }
    });

    }

}