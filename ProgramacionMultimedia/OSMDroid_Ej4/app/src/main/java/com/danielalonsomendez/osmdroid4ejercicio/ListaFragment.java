package com.danielalonsomendez.osmdroid4ejercicio;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ListaFragment extends Fragment {

    private PuntoAdapter adapter;
    private Set<Integer> selectedPositions = new HashSet<>();
    private Button btnVerMapa;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lista, container, false);

        ListView listView = view.findViewById(R.id.lista);
        Button btnFormulario = view.findViewById(R.id.btnFormulario);
        btnVerMapa = view.findViewById(R.id.btnVerMapa);

        adapter = new PuntoAdapter(
                requireContext(),
                GeoRepositorio.puntos,
                selectedPositions,
                this::updateVerMapaButton
        );

        listView.setAdapter(adapter);

        btnFormulario.setOnClickListener(v ->
                ((MainActivity) requireActivity()).showForm()
        );

        btnVerMapa.setOnClickListener(v -> {
            List<GeoPunto> selectedPuntos = new ArrayList<>();
            for (Integer pos : selectedPositions) {
                selectedPuntos.add(GeoRepositorio.puntos.get(pos));
            }
            ((MainActivity) requireActivity()).showMap(selectedPuntos);
        });

        return view;
    }

    private void updateVerMapaButton() {
        btnVerMapa.setEnabled(selectedPositions.size() == 2);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }
    }
}
