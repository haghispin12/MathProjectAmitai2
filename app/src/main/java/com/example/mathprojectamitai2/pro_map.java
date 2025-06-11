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
//import com.mapbox.maps.MapView;
import com.mapbox.turf.TurfMeasurement;

import java.util.ArrayList;
import java.util.Random;

public class pro_map extends AppCompatActivity {



    MapView mapView;
    private TextView tvNameOfCity;

    private Button btmyButton;


    Locations myLocation;

//    private double range;

    private int score;

    private int counter;


    private TextView tvCityScore;

    private double distance;

    private User_pro myUser;

    private int previousIndex = -1;

    private  ArrayList<Locations> locations = new ArrayList<>();
    private  Point cityPoint = Point.fromLngLat(35.2137, 31.7683);




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pro_map);
        initview();







        String email= FirebaseAuth.getInstance().getCurrentUser().getEmail();
        //Firebase.

        myUser = new User_pro(email);


         //  בודק אם המשתמש קיים בפיירסטור ואם הוא לא קיים הוא מוסיף אותו
         checkIfUserExist();

//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });






        //ממלא את מערך המקומות
        FillingTheLocationsArray();






                mapView.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View view, MotionEvent motionEvent) {
                        if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                            Point centerPoint = mapView.getMapboxMap().getCameraState().getCenter();

                            getCords(); //קבלת מיקום המשתמש
                            showCoordinatesAndDistance(centerPoint); //הצגת מיקום המשתמש והמרחק מהעיר

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
                public void onStyleLoaded(@NonNull Style style) {

                }
            });
        }

    }

    public void initview(){
        mapView = findViewById(R.id.mapView);
        tvNameOfCity = findViewById(R.id.tvNameOfCity);
        btmyButton = findViewById(R.id.btmyButton);
        tvCityScore = findViewById(R.id.tvCityScore);



        btmyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int range = (int) Math.round(distance);
                Toast.makeText(pro_map.this, "range is:" + range, Toast.LENGTH_SHORT).show();
                randomCity();
                tvNameOfCity.setText("העיר היא: " + myLocation.getName());
                int x = calculateScore(distance, score); //חישוב נקודות
                score += x;
                tvCityScore.setText("הניקוד שלך הוא: " + score);
                counter ++;

                if (counter>2) {
                    if (myUser.getDocumentId() != null) {
                        myUser.getDocumentId().update("score", score)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Log.d("Firestore", "Score updated successfully");
                                        Toast.makeText(pro_map.this, "your score is:" + score, Toast.LENGTH_SHORT).show();
                                        Intent inn = new Intent(pro_map.this, PreviewActivity.class);
                                        startActivity(inn);

                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Log.e("Firestore", "Failed to update score", e);
                                    }
                                });
                    }
                }
            }
        });
    }



    /**
     *  ממלא את מערך המקומות
     */
    private void FillingTheLocationsArray() {
        FirebaseFirestore.getInstance().collection("locations").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for(DocumentSnapshot documentSnapshot : queryDocumentSnapshots){
                    if(documentSnapshot.exists()){
                        String name = documentSnapshot.getString("name");
                        double latitiude = documentSnapshot.getDouble("latitude");
                        double longitude = documentSnapshot.getDouble("longitude");
                        //DocumentReference myDocument = documentSnapshot.getReference();
                        Locations location1 = new Locations(name, latitiude, longitude);
                        locations.add(location1);
                    }
                }
                //מקבל עיר רנדומלית ומעדכן את הטקסט
                randomCity();
            }
        });
    }

    /**
     *  בודק אם המשתמש קיים בפיירסטור ואם הוא לא קיים הוא מוסיף אותו
     */
    private void checkIfUserExist() {

        //FirebaseFirestore.getInstance().collection("locations").whereEqualTo("name", "תל אביב").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>()
        FirebaseFirestore.getInstance().collection("users").whereEqualTo("uid", myUser.getUID()).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                if(queryDocumentSnapshots.isEmpty()) {
                    FirebaseFirestore.getInstance().collection("users").add(myUser).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            int n=0;
                        }

                    });
                    //Toast.makeText(pro_map.this, "add user has been success", Toast.LENGTH_SHORT).show();
                    //create object
                    //FirebaseFirestore.getInstance().collection("users").document().set(myUser); q
                    //update documentid in the global object
                }else
                /**
                 *   אם המשתמש מקודם כן קיים, הוא מעדכן את הdocument שלו בפיירסטור
                 */
                    for(DocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                        if (documentSnapshot.exists()) {
                            int score = documentSnapshot.getLong("score").intValue();
                            myUser.setScore(score);
                            myUser.setDocumentId(documentSnapshot.getReference());



                        }
                    }

                //Toast.makeText(pro_map.this, "user already exist", Toast.LENGTH_SHORT).show();

            }
        });
    }

    /**
     *  מקבל עיר רנדומלית ומעדכן את הטקסט
     */
    public void randomCity(){
        int n=getRndomIndex(locations, previousIndex); //קבלת עיר רנדומלית והצבה ב-n
        previousIndex = n;//שמירה של האינדקס שיצא כדי שלא יוגרל פעמיים אותה העיר
        myLocation = locations.get(n);
        Log.d("random",n+"");
        cityPoint=Point.fromLngLat(locations.get(n).getLongitude(), locations.get(n).getLatitiude());
        //locations.get(n).getLatitiude();
        tvNameOfCity.setText("העיר היא: " + myLocation.getName());

    }




    /**
     * חישוב המרחק
     * @param from
     * @param to
     * @return
     */
    private double calculateDistance(Point from, Point to) {
        return TurfMeasurement.distance(from, to);
    }

    /**
     *  הצגת המרחק מהעיר
     * @param point
     */
    private void showCoordinatesAndDistance(Point point) {
        distance = calculateDistance(point, cityPoint); //חישוב מרחק והצבה בדיסטנס
        String coordinates =
                "\nDistance to city: " + String.format("%.2f", distance) + " km";
        Log.d("tag", coordinates);
        //Toast.makeText(pro_map.this, coordinates, Toast.LENGTH_SHORT).show();
//        coordinatesTextView.setText(coordinates);
    }

    /**
     *  הצגת המרחק מהעיר
     */
    private void showDistance(){
        String coordinates =
                "\nDistance to city: " + String.format("%.2f", distance) + " km";
        Log.d("tag", coordinates);
        Toast.makeText(pro_map.this, coordinates, Toast.LENGTH_SHORT).show();
    }

    /**
     *  קבלת מיקום המשתמש
     * @return
     */
    public  Point getCords(){
        double latitude = mapView.getMapboxMap().getCameraState().getCenter().latitude();
        double longitude = mapView.getMapboxMap().getCameraState().getCenter().longitude();
        String message = "Latitude: " + latitude + ", Longitude: " + longitude;
        Log.d("TAG", message);
        Point point = Point.fromLngLat(longitude, latitude);
        return point;
    }

    /**
     * קבלת עיר רנדומלית ובודק שלא תוגרל אותה העיר פעמיים
     *
     * @param locations
     * @param previousIndex
     * @return
     */
    public int getRndomIndex(ArrayList<Locations> locations, int previousIndex){
        Random random = new Random();
        int newIndex;

        if (locations.size() <= 1)
                return 0;
        do {
            newIndex = random.nextInt(locations.size());
        } while (newIndex == previousIndex);

        return newIndex;



    }

    /**
     *  הצגת מיקום המשתמש
     */
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

    /**
     *  חישוב נקודות
     * @param distance
     * @param score
     * @return
     */
    public int calculateScore(double distance, int score){
        int score1 = score;
        int range = (int) Math.round(distance);
        if(range>=0 && range<=5)
            score1 = 50;
        else if (range>5 && range<=10)
            score1 = 45;
        else if (range>10 && range<=15)
            score1 = 40;
        else if (range>15 && range<=20)
            score1 = 35;
        else if (range>20 && range<=25)
            score1 = 30;
        else if (range>25 && range<=30)
            score1 = 25;
        else if (range>30 && range<=35)
            score1 = 20;
        else if (range>35 && range<=40)
            score1 = 15;
        else if (range>40 && range<=45)
            score1 = 10;
        else if (range>45 && range<=50)
            score1 = 5;
        else
            score1 = 0;

        return score1;

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
