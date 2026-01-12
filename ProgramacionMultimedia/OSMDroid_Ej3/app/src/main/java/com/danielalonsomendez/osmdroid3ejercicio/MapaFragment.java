package com.danielalonsomendez.osmdroid3ejercicio;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import org.osmdroid.config.Configuration;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;

public class MapaFragment extends Fragment {

    private MapView map;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mapa, container, false);

        Configuration.getInstance().setUserAgentValue(requireContext().getPackageName());
        map = view.findViewById(R.id.map);
        map.setMultiTouchControls(true);

        Bundle args = getArguments();
        if (args != null) {
            double lat = args.getDouble("lat", 0);
            double lon = args.getDouble("lon", 0);
            String nombre = args.getString("nombre");

            GeoPoint punto = new GeoPoint(lat, lon);
            map.getController().setZoom(15.0);
            map.getController().setCenter(punto);

            Marker marker = new Marker(map);
            marker.setPosition(punto);
            marker.setTitle(nombre);
            map.getOverlays().add(marker);
        }

        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
        if (map != null) {
            map.onPause();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (map != null) {
            map.onResume();
        }
    }
}
