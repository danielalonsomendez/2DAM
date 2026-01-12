package com.example.osmdroid_ej1;

import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;

public class MapActivity extends AppCompatActivity {

    public static final String EXTRA_CITY_NAME = "extra_city_name";
    public static final String EXTRA_CITY_LAT = "extra_city_lat";
    public static final String EXTRA_CITY_LON = "extra_city_lon";

    private MapView mapView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_map);

        mapView = findViewById(R.id.map);
        Configuration.getInstance().setUserAgentValue(getPackageName());
        mapView.setTileSource(TileSourceFactory.MAPNIK);
        mapView.setMultiTouchControls(true);

        String name = getIntent().getStringExtra(EXTRA_CITY_NAME);
        double lat = getIntent().getDoubleExtra(EXTRA_CITY_LAT, Double.NaN);
        double lon = getIntent().getDoubleExtra(EXTRA_CITY_LON, Double.NaN);

        if (Double.isNaN(lat) || Double.isNaN(lon)) {
            Toast.makeText(this, R.string.manual_dialog_error, Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        GeoPoint point = new GeoPoint(lat, lon);
        mapView.getController().setZoom(12.0);
        mapView.getController().setCenter(point);

        Marker marker = new Marker(mapView);
        marker.setPosition(point);
        marker.setTitle(name);
        mapView.getOverlays().add(marker);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }
}
