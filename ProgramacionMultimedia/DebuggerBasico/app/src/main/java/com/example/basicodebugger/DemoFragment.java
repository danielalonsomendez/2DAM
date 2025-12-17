package com.example.basicodebugger;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


public class DemoFragment extends Fragment {

    private Button btnClick;
    private TextView tvMensaje;

    public DemoFragment() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_demo, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view,
                              @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Corregir el ID del boton R.id.btnClick -> R.id.btnPresionar
        btnClick = getActivity().findViewById(R.id.btnPresionar);

        tvMensaje = view.findViewById(R.id.tvMensaje);

        btnClick.setOnClickListener(v -> {
            tvMensaje.setText("Botón pulsado");
        });
    }
}
