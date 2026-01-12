package com.danielalonsomendez.osmdroid2ejercicio;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.osmdroid.config.Configuration;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;

public class MapaActivity extends AppCompatActivity {

    private MapView map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        Configuration.getInstance().setUserAgentValue(getPackageName());
        setContentView(R.layout.activity_mapa);


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.map), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        map = findViewById(R.id.map);
        map.setMultiTouchControls(true);

        double lat = getIntent().getDoubleExtra("lat", 0);
        double lon = getIntent().getDoubleExtra("lon", 0);
        String nombre = getIntent().getStringExtra("nombre");

        GeoPoint punto = new GeoPoint(lat, lon);
        map.getController().setZoom(15.0);
        map.getController().setCenter(punto);

        Marker marker = new Marker(map);
        marker.setPosition(punto);
        marker.setTitle(nombre);
        map.getOverlays().add(marker);


    }

    @Override
    protected void onPause(){
        super.onPause();
        map.onPause();
    }

    @Override
    protected void onResume(){
        super.onResume();
        map.onResume();
    }

}