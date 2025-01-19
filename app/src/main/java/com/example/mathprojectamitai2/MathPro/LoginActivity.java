package com.example.mathprojectamitai2.MathPro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.mathprojectamitai2.R;

public class LoginActivity extends AppCompatActivity {
    private EditText etUserName;
    private Button btSubmit;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initview();
        SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        String s1 = sh.getString("name", "");
        etUserName.setText(s1);

        btSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                intent.putExtra("userKey", etUserName.getText().toString());
                startActivity(intent);

                SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref",MODE_PRIVATE);
                SharedPreferences.Editor myEdit = sharedPreferences.edit();
                myEdit.putString("name", etUserName.getText().toString());
                myEdit.apply();
            }

        });

    }





    public void initview() {
        etUserName = findViewById(R.id.etUserName);
        btSubmit = findViewById(R.id.btSubmit);

    }

}