package com.danialonso.ejerciciorepaso;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MapaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MapaFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    // TODO: Rename and change types of parameters

    public MapaFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment MapaFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MapaFragment newInstance() {
        MapaFragment fragment = new MapaFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mapa, container, false);
        MapView map = view.findViewById(R.id.map);
        map.setTileSource(TileSourceFactory.MAPNIK);
        Configuration.getInstance().setUserAgentValue(requireContext().getPackageName());
        map.setMultiTouchControls(true);
        List<Sitio> sitios = ((MainActivity) getActivity()).db.sitioDAO().obtenerTodos();
        if (!sitios.isEmpty()) {
            Sitio primero = sitios.get(0);
            map.getController().setZoom(15.0);
            map.getController().setCenter(new GeoPoint(primero.getLatitud(), primero.getLongitud()));
        }
        for (Sitio sitio : sitios) {
            Marker marker = new Marker(map);
            marker.setOnMarkerClickListener((m, mapView) -> {
                if (getActivity() != null) {
                    new AlertDialog.Builder(getActivity())
                            .setTitle(sitio.getNombre())
                            .setMessage("¿Qué quieres hacer con este sitio?")
                            .setPositiveButton("Editar", (dialog, which) -> {
                                ((MainActivity) getActivity()).cambiarFragmentEditar(sitio);
                            })
                            .setNegativeButton("Borrar", (dialog, which) -> {
                                ((MainActivity) getActivity()).db.sitioDAO().eliminar(sitio);
                            })
                            .show();
                }

                return true; // consume el click
            });
            marker.setPosition(new GeoPoint(sitio.getLatitud(), sitio.getLongitud()));
            map.getOverlays().add(marker);

        }
        return view;
    }
}