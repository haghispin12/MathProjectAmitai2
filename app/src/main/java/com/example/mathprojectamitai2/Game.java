package com.example.mathprojectamitai2;

import android.util.Log;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.mapbox.geojson.Point;
import com.mapbox.maps.MapView;
import com.mapbox.turf.TurfMeasurement;

import java.util.ArrayList;
import java.util.Random;

public class Game {
    private String Uid;

    MapView mapView;

    private int previousIndex = -1;

    private int range;

    Locations myLocation;

    private double distance;

    private  Point cityPoint = Point.fromLngLat(35.2137, 31.7683);

    private ArrayList<Locations> locations;





    public Game(String uid, MapView mapView, int previousIndex, int range, Locations myLocation, double distance, Point cityPoint, ArrayList<Locations> locations) {
        Uid = uid;
        this.mapView = mapView;
        this.previousIndex = previousIndex;
        this.range = range;
        this.myLocation = myLocation;
        this.distance = distance;
        this.cityPoint = cityPoint;
        this.locations = locations;
    }

    public String getUid() {
        return Uid;
    }
    public void setUid(String uid) {
        Uid = uid;
    }
    public MapView getMapView() {
        return mapView;
    }
    public void setMapView(MapView mapView) {
        this.mapView = mapView;
    }
    public int getPreviousIndex() {
        return previousIndex;
    }
    public void setPreviousIndex(int previousIndex) {
        this.previousIndex = previousIndex;
    }
    public Locations getMyLocation() {
        return myLocation;
    }
    public void setMyLocation(Locations myLocation) {
        this.myLocation = myLocation;
    }
    public double getDistance() {
        return distance;
    }
    public void setDistance(double distance) {
        this.distance = distance;
    }
    public Point getCityPoint() {
        return cityPoint;
    }
    public void setCityPoint(Point cityPoint) {
        this.cityPoint = cityPoint;
    }
    public ArrayList<Locations> getLocations() {
        return locations;
    }
    public void setLocations(ArrayList<Locations> locations) {
        this.locations = locations;
    }

    /**
     *  ממלא את מערך המקומות
     */
    public void FillingTheLocationsArray() {
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
                //מקבל עיר רנדומלית ומעדכן את הטקסט
                randomCity();
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
        cityPoint= Point.fromLngLat(locations.get(n).getLongitude(), locations.get(n).getLatitiude());
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
    public void showCoordinatesAndDistance(Point point) {
        distance = calculateDistance(point, cityPoint); //חישוב מרחק והצבה בדיסטנס
        String coordinates =
                "\nDistance to city: " + String.format("%.2f", distance) + " km";
        Log.d("tag", coordinates);

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
     *  חישוב נקודות
     * @param
     * @param score
     * @return
     */
    public int calculateScore( int score){
        int score1 = score;
        range = (int) Math.round(distance);
        if(range >=0 && range <=5)
            score1 = 50;
        else if (range >5 && range <=10)
            score1 = 45;
        else if (range >10 && range <=15)
            score1 = 40;
        else if (range >15 && range <=20)
            score1 = 35;
        else if (range >20 && range <=25)
            score1 = 30;
        else if (range >25 && range <=30)
            score1 = 25;
        else if (range >30 && range <=35)
            score1 = 20;
        else if (range >35 && range <=40)
            score1 = 15;
        else if (range >40 && range <=45)
            score1 = 10;
        else if (range >45 && range <=50)
            score1 = 5;
        else
            score1 = 0;

        return score1;

    }









}
