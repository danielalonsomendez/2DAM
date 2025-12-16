package com.danielalonso.fragmentrecylerview;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentDetalle#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentDetalle extends Fragment {
    private static final String ARG_CIUDAD = "ciudad";
    private String ciudad ="";
    // Recibe el string y lo guarda en arguments
    public static FragmentDetalle newInstance(String ciudad) {
        FragmentDetalle fragment = new FragmentDetalle();
        Bundle args = new Bundle();
        args.putString(ARG_CIUDAD, ciudad);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            String argsList = getArguments().getString(ARG_CIUDAD);
            if (argsList != null) {
                ciudad = argsList;
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detalle, container, false);

        TextView txtCiudad = view.findViewById(R.id.txt);
        if (txtCiudad != null) {
            txtCiudad.setText(ciudad);
        }
        Button button = view.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Volver al fragment lista desde mainactivity
                ((MainActivity) requireActivity()).volverLista();
            }
        });
        return view;
    }
}