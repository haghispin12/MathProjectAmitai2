package com.example.mathprojectamitai2;



import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.mapbox.geojson.Point;
import com.mapbox.maps.MapView;
import com.mapbox.maps.Style;
//import com.mapbox.maps.MapView;
import com.mapbox.turf.TurfMeasurement;

import java.util.ArrayList;
import java.util.Random;

public class pro_map extends AppCompatActivity {



    MapView mapView;
    private TextView tvNameOfCity;

    private Button btmyButton;

    Locations myLocation;
    private  Point cityPoint = Point.fromLngLat(35.2137, 31.7683);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        initview();
        setContentView(R.layout.activity_pro_map);
        tvNameOfCity.setText(myLocation.getName());

//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });

        tvNameOfCity.setText(myLocation.getName());




        ArrayList<Locations> locations = new ArrayList<>();
        FirebaseFirestore.getInstance().collection("locations").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for(DocumentSnapshot documentSnapshot : queryDocumentSnapshots){
                    if(documentSnapshot.exists()){
                        String name = documentSnapshot.getString("name");
                        double latitiude = documentSnapshot.getDouble("latitude");
                        double longitude = documentSnapshot.getDouble("longitude");
                        Locations location1 = new Locations(name, latitiude, longitude);
                        locations.add(location1);
                    }
                }

                int n=getRndomIndex(locations);
                myLocation = locations.get(n);
                Log.d("random",n+"");
                cityPoint=Point.fromLngLat(locations.get(n).getLongitude(), locations.get(n).getLatitiude());
                //locations.get(n).getLatitiude();
            }
        });





                mapView.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View view, MotionEvent motionEvent) {
                        if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                            Point centerPoint = mapView.getMapboxMap().getCameraState().getCenter();

                            getCords();
                            showCoordinatesAndDistance(centerPoint);

                        }
                        //getCords();

                        //setMarker();
                        return false;
                    }
                });
        //setMarker();
//        mapView.setOnDragListener(new View.OnDragListener() {
//            @Override
//            public boolean onDrag(View view, DragEvent dragEvent) {
//                setMarker();
//                return true;
//            }
//        });

        if (mapView != null){
            mapView.getMapboxMap().loadStyleUri(Style.MAPBOX_STREETS, new Style.OnStyleLoaded() {
                @Override
                public void onStyleLoaded(@androidx.annotation.NonNull Style style) {

                }
            });
        }

    }

    public void initview(){
        mapView = findViewById(R.id.mapView);
        tvNameOfCity = findViewById(R.id.tvNameOfCity);
        btmyButton = findViewById(R.id.btmyButton);

        btmyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCoordinatesAndDistance(cityPoint);

                tvNameOfCity.setText(myLocation.getName());
            }
        });



    }
    private double calculateDistance(Point from, Point to) {
        return TurfMeasurement.distance(from, to);
    }

    private void showCoordinatesAndDistance(Point point) {
        double distance = calculateDistance(point, cityPoint);
        String coordinates = "Latitude: " + point.latitude() +
                ", Longitude: " + point.longitude() +
                "\nDistance to city: " + String.format("%.2f", distance) + " km";
        Log.d("tag", coordinates);
//        coordinatesTextView.setText(coordinates);
    }

    public  Point getCords(){
        double latitude = mapView.getMapboxMap().getCameraState().getCenter().latitude();
        double longitude = mapView.getMapboxMap().getCameraState().getCenter().longitude();
        String message = "Latitude: " + latitude + ", Longitude: " + longitude;
        Log.d("TAG", message);
        Point point = Point.fromLngLat(longitude, latitude);
        return point;
    }

    public int getRndomIndex(ArrayList<Locations> locations){
        Random random = new Random();
        return random.nextInt(locations.size());
    }

    public void setMarker(){

        double latitude = mapView.getMapboxMap().getCameraState().getCenter().latitude();
        double longitude = mapView.getMapboxMap().getCameraState().getCenter().longitude();

        String message = "Latitude: " + latitude + ", Longitude: " + longitude;

        Toast.makeText(pro_map.this, message, Toast.LENGTH_SHORT).show();
//        double mapLat = mapView.getMapboxMap().getCameraPosition().target.getLatitude()
//
//        double mapLong = mapboxMap.getCameraPosition().target.getLongitude()
//        double sourceLat = lat;
//        double sourceLng = lng;
//        mapView.setSourcePin(lat , lng);
//        map.flyTo(lat, lng);
    }

//    private void addAnnotationToMap() {
//        Bitmap bitmap = bitmapFromDrwableRes(this, R.drawable.red_marker);
//        if(bitmap != null && mapView != null){
//            AnnotationPlugin annotationApi = mapView.getAnnotations();
//            PointAnnotationManager pointAnnotationManager = annotationApi.createPointAnnotationManager();
//
//            PointAnnotationOptions pointAnnotationOptions = new PointAnnotationOptions()
//                    .withPoint(Point.fromLngLat(-90.0,39.5))
//                    .withIconImage(bitmap);
//
//            pointAnnotationManager.create(pointAnnotationOptions);
//
//            pointAnnotationManager.addClickListener(new PointAnnotationManager.OnPointAnnotationClickListener() {
//                @Override
//                public boolean onAnnotationClick(@NonNull PointAnnotation pointAnnotation) {
//                    return false;
//                }
//            }
//
//        }
//    }
//    private Bitmap bitmapFromDrwableRes(Context context, @DrawableRes int resourcId){
//        Drawable drawable = AppCompatResources.getDrawable(context, resourcId);
//        return convertDrawableToBitmap(drawable);





//         private void addAnnotationToMap() {
//            Bitmap bitmap = bitmapFromDrawableRes(this, R.drawable.marker);
//            if(bitmap != null && mapView != null){
//                AnnotationPlugin annotationApi = mapView.getAnnotations();
//                pointAnnotationManager = annotationApi.createPointAnnotationManager();
//
//                pointAnnotationManager pointAnnotationManager = new pointAnnotationOptions()
//            }
//        }
//        mapView.onCreate(savedInstanceState);
//
//        mapView.getMapAsync(new OnMapReadyCallback)

//        mapView = new MapView(this);
//        mapView.getMapboxMap().setCamera(CameraOptions.Builder()
//                .center(Point.fromLngLat(-90.0,39.5)).pitch(0.0).zoom(2.0).bearing(0.0).build());


}
