 package com.example.mathprojectamitai2;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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

    private MainViewModel viewModel;

    private Button btrate;

    private FragmentTransaction trans;
    private TextView tvWhatIsScore;

     ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
             new ActivityResultContracts.StartActivityForResult(),
             new ActivityResultCallback<ActivityResult>() {
                 @Override
                 public void onActivityResult(ActivityResult result) {
                     int myRate = result.getData().getIntExtra("RateKey", -1);
                     viewModel.user.setRate(myRate);
                     Toast.makeText(MainActivity.this, myRate+"", Toast.LENGTH_LONG).show();

                 }
             });



            //Exercise exercise;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initview();
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        trans = getSupportFragmentManager().beginTransaction();
        viewModel.vNum1.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                tvNumOne.setText(integer + "");
            }
        });

        viewModel.vNum2.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                tvNumTwo.setText(integer + "");
            }
        });
        Intent intent = getIntent();
        String userName=intent.getStringExtra("userKey");
        Toast.makeText(MainActivity.this,userName+"", Toast.LENGTH_LONG).show();
        viewModel.updateName(userName);
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
        btrate = findViewById(R.id.btRate);
        tvWhatIsScore = findViewById(R.id.tvWhatIsScore);

        btChallenge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            viewModel.vChallenge();
            update();

            }
        });

        btUntiltwenty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            viewModel.vUntiltwenty();
             update();

            }
        });

        btMultiplicationTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            viewModel.vMultiplicationTable();
            update();
            }
        });

        btCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean b=viewModel.vCheck(etAnswer.getText().toString());
                if (b) {
                    Toast.makeText(MainActivity.this, "success", Toast.LENGTH_LONG).show();
                    viewModel.setScore(viewModel.Type);
                    tvWhatIsScore.setText("Score:" + viewModel.getScore());
                } else
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


                trans.add(R.id.frameLayout, new ShowUsersFragment());

                trans.commit();
            }
        });

        btrate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RateActivity.class);
                activityResultLauncher.launch(intent);
            }
        });


    }



    public void update(){
        tvNumOne.setText(viewModel.vNum1.getValue() + "");
        tvNumTwo.setText(viewModel.vNum2.getValue() +" ");
    }










}