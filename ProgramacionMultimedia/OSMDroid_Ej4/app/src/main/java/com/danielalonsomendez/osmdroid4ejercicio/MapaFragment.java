package com.danielalonsomendez.osmdroid4ejercicio;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import org.osmdroid.api.IMapController;
import org.osmdroid.config.Configuration;
import org.osmdroid.util.BoundingBox;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;

import java.util.ArrayList;
import java.util.List;

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
            List<String> nombres = args.getStringArrayList("nombres");
            List<Double> lats = (List<Double>) args.getSerializable("lats");
            List<Double> lons = (List<Double>) args.getSerializable("lons");

            if (nombres != null && lats != null && lons != null && nombres.size() == lats.size() && lats.size() == lons.size()) {
                List<GeoPoint> puntos = new ArrayList<>();
                for (int i = 0; i < nombres.size(); i++) {
                    GeoPoint punto = new GeoPoint(lats.get(i), lons.get(i));
                    puntos.add(punto);

                    Marker marker = new Marker(map);
                    marker.setPosition(punto);
                    marker.setTitle(nombres.get(i));
                    map.getOverlays().add(marker);
                }

                // Centrar el mapa para que se vean todos los puntos
                if (puntos.size() == 1) {
                    map.getController().setZoom(15.0);
                    map.getController().setCenter(puntos.get(0));
                } else if (puntos.size() > 1) {
                    // Calcular el centro entre los puntos
                    double latSum = 0;
                    double lonSum = 0;
                    for (GeoPoint p : puntos) {
                        latSum += p.getLatitude();
                        lonSum += p.getLongitude();
                    }
                    GeoPoint center = new GeoPoint(latSum / puntos.size(), lonSum / puntos.size());
                    map.getController().setCenter(center);
                    map.getController().setZoom(8.0); // Zoom fijo para mostrar la región
                }
            } else {
                // Compatibilidad con un solo punto
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
