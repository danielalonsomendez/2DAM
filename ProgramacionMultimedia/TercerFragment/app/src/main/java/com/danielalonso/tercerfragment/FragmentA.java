package com.danielalonso.tercerfragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentA#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentA extends Fragment {

    private TextView textResultadoA;

    public FragmentA() {
        // Required empty public constructor
    }
    public static FragmentA newInstance(String message) {
        FragmentA fragment = new FragmentA();
        Bundle args = new Bundle();
        args.putString("message", message);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // no-op
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_a, container, false);

        Button buttonA = view.findViewById(R.id.buttonFragmentA_A);
        Button buttonB = view.findViewById(R.id.buttonFragmentB_a);
        Button buttonActivity = view.findViewById(R.id.buttonActivityA);

        final EditText editTextA = view.findViewById(R.id.editTextTextA);
        textResultadoA = view.findViewById(R.id.textResultadoA);

        if (getArguments() != null) {
            String msg = getArguments().getString("message");
            if (msg != null) {
                textResultadoA.setText(msg);
            }
        }

        buttonA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getParentFragmentManager().beginTransaction()
                        .replace(R.id.contenedor, new FragmentA())
                        .commit();
            }
        });

        buttonB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String texto = editTextA.getText() != null ? editTextA.getText().toString() : null;
                getParentFragmentManager().beginTransaction()
                        .replace(R.id.contenedor, FragmentB.newInstance(texto))
                        .commit();
            }
        });

        buttonActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String texto = editTextA.getText() != null ? editTextA.getText().toString() : null;
                if (getActivity() instanceof MainActivity) {
                    ((MainActivity) getActivity()).cambiarTexto(texto);
                }
            }
        });

        return view;
    }
}