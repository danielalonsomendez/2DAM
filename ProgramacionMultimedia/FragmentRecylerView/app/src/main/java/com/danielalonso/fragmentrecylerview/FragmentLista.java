// java
package com.danielalonso.fragmentrecylerview;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import java.util.ArrayList;

public class FragmentLista extends Fragment {
    private static final String ARG_CIUDADES = "ciudades";
    private ArrayList<String> ciudades = new ArrayList<>();

    // Recibe el ArrayList y lo guarda en arguments
    public static FragmentLista newInstance(ArrayList<String> ciudades) {
        FragmentLista fragment = new FragmentLista();
        Bundle args = new Bundle();
        args.putStringArrayList(ARG_CIUDADES, ciudades);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            ArrayList<String> argsList = getArguments().getStringArrayList(ARG_CIUDADES);
            if (argsList != null) {
                ciudades = argsList;
            }
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lista, container, false);
        // RecyclerView
        RecyclerView recyclerView = view.findViewById(R.id.rvLista);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        ListaAdapter adapter = new ListaAdapter(ciudades);
        adapter.setOnItemClickListener(new ListaAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                // Al hacer click se va a un metodo eb MainActivity
                ((MainActivity) requireActivity()).abrirDetalleCiudad(ciudades.get(position));

            }
        });

        recyclerView.setAdapter(adapter);

        return view;
    }


}
