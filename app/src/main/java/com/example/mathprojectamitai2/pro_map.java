package com.example.mathprojectamitai2;



import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

//import com.google.android.gms.maps.MapView;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.mapbox.geojson.Point;
import com.mapbox.maps.MapView;
import com.mapbox.maps.Style;
//import com.mapbox.geojson.Point;
//import com.mapbox.maps.MapView;
//import com.mapbox.maps.Style;
//import com.mapbox.maps.MapView;
//import com.mapbox.turf.TurfMeasurement;

import java.util.ArrayList;
import java.util.Random;

public class pro_map extends AppCompatActivity {

    MapView mapView;
    private TextView tvNameOfCity;
    private Button btmyButton;
    Locations myLocation;
    private int score;
    private int counter;
    private TextView tvCityScore;
    private double distance;
    private int range;
    private User_pro user_pro;
    private Game game;
    private int previousIndex = -1;
    private ArrayList<Locations> locations = new ArrayList<>();
    private Point cityPoint = Point.fromLngLat(35.2137, 31.7683);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pro_map);
        initview();
        String email = FirebaseAuth.getInstance().getCurrentUser().getEmail();
        user_pro = new User_pro(email);
        user_pro.checkIfUserExist();

        game = new Game(user_pro.getUID(), mapView, previousIndex,range, myLocation, distance, cityPoint, locations);


        //ממלא את מערך המקומות
        game.FillingTheLocationsArray();

        mapView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    Point centerPoint = mapView.getMapboxMap().getCameraState().getCenter();
                    game.getCords();//קבלת מיקום המשתמש
                    game.showCoordinatesAndDistance(centerPoint); //הצגת מיקום המשתמש והמרחק מהעיר
                }
                return false;
            }
        });
        if (mapView != null) {
            mapView.getMapboxMap().loadStyleUri(Style.MAPBOX_STREETS, new Style.OnStyleLoaded() {
                @Override
                public void onStyleLoaded(@NonNull Style style) {
                }
            });
        }
    }
    public void initview() {
        mapView = findViewById(R.id.mapView);
        tvNameOfCity = findViewById(R.id.tvNameOfCity);
        btmyButton = findViewById(R.id.btmyButton);
        tvCityScore = findViewById(R.id.tvCityScore);
        btmyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                  range = (int) Math.round(distance);
                Toast.makeText(pro_map.this, "range is:" + game.calculateScore(range), Toast.LENGTH_SHORT).show();
                game.randomCity();
                tvNameOfCity.setText("העיר היא: " + game.getMyLocation().getName());
                int x = game.calculateScore( score); //חישוב נקודות
                score += x;
                tvCityScore.setText("הניקוד שלך הוא: " + score);
                counter++;
                if (counter > 2) {
                    if (user_pro.getDocumentId() != null) {
                        user_pro.getDocumentId().update("score", score)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Log.d("Firestore", "Score updated successfully");
                                        Toast.makeText(pro_map.this, "your score is:" + score, Toast.LENGTH_SHORT).show();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Log.e("Firestore", "Failed to update score", e);
                                    }
                                });
                    }
                    Intent inn = new Intent(pro_map.this, PreviewActivity.class);
                    startActivity(inn);
                }
            }
        });
    }
}