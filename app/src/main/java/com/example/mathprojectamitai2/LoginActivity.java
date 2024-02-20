package com.example.mathprojectamitai2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {
    private EditText etUserName;
    private Button btSubmit;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initview();

        btSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                intent.putExtra("userKey", etUserName.getText().toString());
                startActivity(intent);
            }

        });

    }





    public void initview() {
        etUserName = findViewById(R.id.etUserName);
        btSubmit = findViewById(R.id.btSubmit);
    }

}