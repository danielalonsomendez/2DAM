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
 * Use the {@link FragmentB#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentB extends Fragment {

    private TextView textResultadob;

    public FragmentB() {
        // Required empty public constructor
    }

    public static FragmentB newInstance(String message) {
        FragmentB fragment = new FragmentB();
        Bundle args = new Bundle();
        args.putString("message", message);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_b, container, false);

        Button buttonA = view.findViewById(R.id.buttonFragmentA_B);
        Button buttonB = view.findViewById(R.id.buttonFragmentB_B);
        Button buttonActivity = view.findViewById(R.id.buttonActivityA);

        final EditText editTextB = view.findViewById(R.id.editTextTextB);
        textResultadob = view.findViewById(R.id.textResultadob);

        if (getArguments() != null) {
            String msg = getArguments().getString("message");
            if (msg != null) {
                textResultadob.setText(msg);
            }
        }

        buttonA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String texto = editTextB.getText() != null ? editTextB.getText().toString() : null;
                getParentFragmentManager().beginTransaction()
                        .replace(R.id.contenedor, FragmentA.newInstance(texto))
                        .commit();
            }
        });

        buttonB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getParentFragmentManager().beginTransaction()
                        .replace(R.id.contenedor, new FragmentB())
                        .commit();
            }
        });

        buttonActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String texto = editTextB.getText() != null ? editTextB.getText().toString() : null;
                if (getActivity() instanceof MainActivity) {
                    ((MainActivity) getActivity()).cambiarTexto(texto);
                }
            }
        });

        return view;
    }
}