package com.example.mathprojectamitai2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class PreviewActivity extends AppCompatActivity {
    private Button btJoinGame;
    private RecyclerView rcShowProUsers;

    private ArrayList<User_pro>users;





    /**
     *  כניסה למסך
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview);
        initview();
        FillingUsersArray();

    }







    public void initview(){
        btJoinGame = findViewById(R.id.btJoinGame);
        rcShowProUsers = findViewById(R.id.rcShowProUsers);
        btJoinGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkMap();
            }
        });
    }


    /**
     *  התחלת המשחק
     */
    private void checkMap(){
        Intent inn = new Intent(this, pro_map.class);
        startActivity(inn);

    }

    /**
     * ממלא את מערך users
     */
    public void FillingUsersArray(){
        FirebaseFirestore.getInstance().collection("users").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                users = new ArrayList<>();
                for (DocumentSnapshot documentSnapshot : queryDocumentSnapshots){
                    if (documentSnapshot.exists()){
                        String uid = documentSnapshot.getString("uid");
                        int score = documentSnapshot.getLong("score").intValue();
                        User_pro user_pro1 = new User_pro(uid, score);
                        users.add(user_pro1);
                    }
                }
                createRecycleView(users);

            }

        });


    }


    /**
     * יצירת RecycleView
     * @param users
     */
    public void createRecycleView(ArrayList<User_pro>users) {
        UserProAdapter userProAdapter1 = new UserProAdapter(users, new UserProAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(User_pro item) {
                Toast.makeText(PreviewActivity.this, item.getUID(), Toast.LENGTH_SHORT).show();
            }
        });
        rcShowProUsers.setLayoutManager(new LinearLayoutManager(this));
        rcShowProUsers.setAdapter(userProAdapter1);
        rcShowProUsers.setHasFixedSize(true);
    }
















}