package com.danialonso.ejerciciorepaso;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ListaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListaFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


    public ListaFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ListaFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ListaFragment newInstance() {
        ListaFragment fragment = new ListaFragment();
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_lista, container, false);

        Button btnAnadir = view.findViewById(R.id.anadirBtn);
        btnAnadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getActivity() != null) {
                    ((MainActivity) getActivity()).cambiarFragmentAnadir();
                }
            }

            ;
        });
        Button btnMapa = view.findViewById(R.id.mapaBtn);
        btnMapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getActivity() != null) {
                    ((MainActivity) getActivity()).cambiarFragmentMapa();
                }
            }
        });
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        SitiosAdapter adapter = new SitiosAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        if (getActivity() != null) {
            adapter.actualizar(((MainActivity) getActivity()).db.sitioDAO().obtenerTodos());
        }
        recyclerView.setAdapter(adapter);
        assert getActivity() != null;
        return view;
    }
}