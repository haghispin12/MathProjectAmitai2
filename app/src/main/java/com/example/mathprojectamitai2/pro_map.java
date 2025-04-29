package com.example.mathprojectamitai2;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.mapbox.geojson.Point;
import com.mapbox.maps.CameraOptions;
import com.mapbox.maps.MapView;
//import com.mapbox.maps.MapView;

public class pro_map extends AppCompatActivity {

    MapView mapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pro_map);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        mapView = findViewById(R.id.mapView);
//        mapView.onCreate(savedInstanceState);
//
//        mapView.getMapAsync(new OnMapReadyCallback)

//        mapView = new MapView(this);
//        mapView.getMapboxMap().setCamera(CameraOptions.Builder()
//                .center(Point.fromLngLat(-90.0,39.5)).pitch(0.0).zoom(2.0).bearing(0.0).build());

    }
}
