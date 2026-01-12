package com.danielalonsomendez.osmdroid3ejercicio;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

public class ListaFragment extends Fragment {

    private ArrayAdapter<GeoPunto> adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lista, container, false);

        ListView listView = view.findViewById(R.id.lista);
        Button btnFormulario = view.findViewById(R.id.btnFormulario);

        adapter = new ArrayAdapter<>(
                requireContext(),
                android.R.layout.simple_list_item_1,
                GeoRepositorio.puntos
        );

        listView.setAdapter(adapter);

        listView.setOnItemClickListener((parent, view1, position, id) -> {
            GeoPunto p = GeoRepositorio.puntos.get(position);
            ((MainActivity) requireActivity()).showMap(p.getLatitud(), p.getLongitud(), p.getNombre());
        });

        btnFormulario.setOnClickListener(v ->
                ((MainActivity) requireActivity()).showForm()
        );

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }
    }
}
