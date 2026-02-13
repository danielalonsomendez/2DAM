package com.example.examenpmdmgastos;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.examenpmdmroom.R;

import java.text.DecimalFormat;

public class ListaFragment extends Fragment {
    public ListaFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lista, container, false);
        TextView txt = view.findViewById(R.id.textView6);

        // Mostrar total de gastos
        DecimalFormat formato = new DecimalFormat("#,##0.00");
        assert getActivity() != null;
        String importeFormateado = formato.format(((MainActivity) getActivity()).db.gastoDAO().sumatotal());
        txt.setText("Total gastos: " + importeFormateado + "€");

        // Configurar btn añadir
        Button btnAnadir = view.findViewById(R.id.btnAnadir);
        btnAnadir.setOnClickListener(v -> {
            assert getActivity() != null;
            ((MainActivity) getActivity()).navegaraAnadir();
        });

        // Settear adapter del recyler y actualizar datos
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        ListaAdapter adapter = new ListaAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
        adapter.actualizar(((MainActivity) getActivity()).db.gastoDAO().consultaSelect());
        return view;
    }
}