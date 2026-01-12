package com.danielalonsomendez.osmdroid4ejercicio;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

public class FormularioFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_formulario, container, false);

        EditText nombre = view.findViewById(R.id.edtNombre);
        EditText lat = view.findViewById(R.id.edtLat);
        EditText lon = view.findViewById(R.id.edtLon);
        Button guardar = view.findViewById(R.id.btnGuardar);

        guardar.setOnClickListener(v -> {
            GeoPunto p = new GeoPunto(
                    nombre.getText().toString(),
                    Double.parseDouble(lat.getText().toString()),
                    Double.parseDouble(lon.getText().toString())
            );

            // Añadir al repositorio común
            GeoRepositorio.puntos.add(p);

            // Volver a la lista
            ((MainActivity) requireActivity()).replaceFragment(new ListaFragment());
        });

        return view;
    }
}
