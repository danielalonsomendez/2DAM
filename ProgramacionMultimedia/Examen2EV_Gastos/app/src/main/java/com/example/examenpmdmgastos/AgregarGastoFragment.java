package com.example.examenpmdmgastos;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.example.examenpmdmroom.R;

import java.util.Date;


public class AgregarGastoFragment extends Fragment {
    public AgregarGastoFragment() {
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_agregar_gasto, container, false);
       // Conseguir campos
        Button btnVolver = view.findViewById(R.id.btnVolver);
        Button btnCrear = view.findViewById(R.id.btnCrear);
        EditText txtConcepto = view.findViewById(R.id.txtConcepto);
        EditText txtImporte = view.findViewById(R.id.txtImporte);
        RadioGroup rCategoria = view.findViewById(R.id.rCategoria);
        EditText txtFecha = view.findViewById(R.id.txtFecha);

        // Settear btnVolver
        btnVolver.setOnClickListener(v -> {
            assert getActivity() != null;
            ((MainActivity) getActivity()).navegaraLista();
        });

        // Crear gasto
        btnCrear.setOnClickListener(v -> {
            // CONSEGUIR DATOS
            // Concepto
            String concepto = txtConcepto.getText().toString();
            // Importe
            double importe;
            try {
                 importe = Double.parseDouble(txtImporte.getText().toString());
            } catch (Exception e){
                // Si no es un numero o es incorrecto
                new AlertDialog.Builder(view.getContext()).setTitle("Error").setMessage("Importe tiene que ser mayor que 0").show();
                return;
            }
            // Categoria
            int seleccionadoId = rCategoria.getCheckedRadioButtonId();
            String categoria = "";
            if (seleccionadoId == R.id.rComida) {
                categoria = "Comida";
            } else if (seleccionadoId == R.id.rTransporte) {
                categoria = "Transporte";
            } else if (seleccionadoId == R.id.rOcio) {
                categoria = "Ocio";
            } else if (seleccionadoId == R.id.rOtros) {
                categoria = "Otros";
            }

            // Fecha
            long fecha;
            try {    fecha = new  Date(txtFecha.getText().toString()).getTime();
            } catch (Exception e){
                // Si la fecha esta vacia o mal escrita
                new AlertDialog.Builder(view.getContext()).setTitle("Error").setMessage("Fecha en formato no valido").show();
                return;
            }

            // VALIDACIONES
            // Concepto vacio
            if(concepto.isEmpty()){
                new AlertDialog.Builder(view.getContext()).setTitle("Error").setMessage("Concepto no puede estar vacio").show();
                return;
            }

            // Importe inferior a 0
            if(importe <= 0){
                new AlertDialog.Builder(view.getContext()).setTitle("Error").setMessage("Importe tiene que ser mayo que 0").show();
                return;
            }

            // Categoria vacia
            if(categoria == ""){
                new AlertDialog.Builder(view.getContext()).setTitle("Error").setMessage("Categoria es obligatorio").show();
                return;
            }

            // ENVIAR DATOS
            // Crear gasto en constructor
            Gasto nuevoGasto = new Gasto(concepto,importe,categoria,fecha);
            assert getActivity() != null;
            // Añadirlo a la base de datos
            ((MainActivity) getActivity()).db.gastoDAO().insertar(nuevoGasto);
            // Volver a lists
            ((MainActivity) getActivity()).navegaraLista();

        });

        return view;
    }
}