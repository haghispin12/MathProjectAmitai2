package com.example.mathprojectamitai2;



import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.constraintlayout.helper.widget.Layer;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.mapbox.geojson.Point;
import com.mapbox.maps.CameraOptions;
import com.mapbox.maps.MapView;
import com.mapbox.maps.Style;
import com.mapbox.maps.plugin.annotation.AnnotationPlugin;
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationManager;
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationOptions;
//import com.mapbox.maps.MapView;

public class pro_map extends AppCompatActivity {



    MapView mapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pro_map);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });

        mapView = findViewById(R.id.mapView);

        mapView.setOn
        //setMarker();
        mapView.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View view, DragEvent dragEvent) {
                setMarker();
                return true;
            }
        });

        if (mapView != null){
            mapView.getMapboxMap().loadStyleUri(Style.MAPBOX_STREETS, new Style.OnStyleLoaded() {
                @Override
                public void onStyleLoaded(@androidx.annotation.NonNull Style style) {

                }
            });
        }

    }

    public  void getCords(){
        double latitude = mapView.getMapboxMap().getCameraState().getCenter().latitude();
        double longitude = mapView.getMapboxMap().getCameraState().getCenter().longitude();
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
