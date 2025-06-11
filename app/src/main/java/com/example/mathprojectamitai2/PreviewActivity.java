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
    private Button btJoinGame;
    /**
     *  כניסה למסך
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview);
        initview();
    }
    /**
     *  התחלת המשחק
     */
    private void checkMap(){
        Intent inn = new Intent(this, pro_map.class);
        startActivity(inn);
    }
    public void initview(){
        btJoinGame = findViewById(R.id.btJoinGame);
        btJoinGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkMap();
            }
        });
    }

















}