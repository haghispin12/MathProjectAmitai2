package com.example.mathprojectamitai2;

import static androidx.core.content.ContextCompat.startActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mathprojectamitai2.MathPro.MainActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Firebase;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
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


    /**
     *  כניסה למסך
     * @param savedInstanceState
     */
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


    /**
     *  התחלת המשחק
     */
    private void startGame(){
        Intent inn = new Intent(this, PreviewActivity.class);
        inn.putExtra("userName",auth.getCurrentUser().getEmail().toString());
        startActivity(inn);

    }


    boolean isRegister = true;


    public void initview(){

        tvNameOfGame = findViewById(R.id.tvNameOfGame);
        etEmail = findViewById(R.id.etEmail);
        etPassowrd = findViewById(R.id.etPassowrd);
        btSubmit = findViewById(R.id.btSubmit);
        spLogin = findViewById(R.id.spLogin);


        btSubmit.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                //אימות והרשמה
                if(isRegister == true){

                    auth.createUserWithEmailAndPassword(etEmail.getText().toString(),etPassowrd.getText().toString()).addOnCompleteListener(LoginProActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(LoginProActivity.this, "Registion success.", Toast.LENGTH_SHORT).show();
                                startGame();
                            }else{
                                Toast.makeText(LoginProActivity.this, "Registion failed.", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
                }
                if(isRegister == false){
                    auth.signInWithEmailAndPassword(etEmail.getText().toString(), etPassowrd.getText().toString()).addOnCompleteListener(LoginProActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(LoginProActivity.this, "Authentication success.", Toast.LENGTH_SHORT).show();
                                startGame();
                            }else{
                                Toast.makeText(LoginProActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

        spLogin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                String item = adapterView.getItemAtPosition(position).toString();
                Toast.makeText(LoginProActivity.this, "sellected: " + item, Toast.LENGTH_LONG).show();

                //שינוי טקסט
                if(item.equals("sign up")) {
                    isRegister=true;
                    btSubmit.setText("הרשמה");
                }if (item.equals("Log in")) {
                    isRegister = false;
                    btSubmit.setText("כניסה");
                }


            }

            /**
             *  אם לא נבחרה כלום
             * @param adapterView
             */
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("sign up");
        arrayList.add("Log in");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, arrayList);
        adapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        spLogin.setAdapter(adapter);

    }





























}