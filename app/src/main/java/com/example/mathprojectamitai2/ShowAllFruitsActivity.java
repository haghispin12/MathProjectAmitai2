package com.example.mathprojectamitai2;

import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ShowAllFruitsActivity extends AppCompatActivity {

    private RecyclerView rcShowUsers;
    private RecyclerView rcShowFruits;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_show_users);
        initview();
        showList();

    }
    public void initview(){
       rcShowUsers = findViewById(R.id.rcShowUsers);
       rcShowFruits = findViewById(R.id.rcShowFruits);
    }



    public void showList(){
        ArrayList<Fruit> fruits = new ArrayList<>();
        fruits.add(new Fruit("banana", R.drawable.banana));
        fruits.add(new Fruit("apple", R.drawable.apple));
        fruits.add(new Fruit("ornage", R.drawable.orange));
        fruits.add(new Fruit("grapes", R.drawable.grapes));
        fruits.add(new Fruit("lemon", R.drawable.lemon));

        MyFruitsAdapter myFruitsAdapter = new MyFruitsAdapter(fruits, new MyFruitsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Fruit item) {
                Toast.makeText(ShowAllFruitsActivity.this,item.getName(),Toast.LENGTH_SHORT).show();
            }
        });
        rcShowFruits.setLayoutManager(new LinearLayoutManager(this));
        rcShowFruits.setAdapter(myFruitsAdapter);
        rcShowFruits.setHasFixedSize(true);

    }


}