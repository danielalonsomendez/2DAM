package com.danialonso.ejerciciorepaso;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CrearEditarSitio#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CrearEditarSitio extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private Sitio sitio;

    public CrearEditarSitio() {

    }

    public CrearEditarSitio(Sitio sitio) {
        this.sitio = sitio;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment CrearEditarSitio.
     */
    // TODO: Rename and change types and number of parameters
    public static CrearEditarSitio newInstance() {
        CrearEditarSitio fragment = new CrearEditarSitio();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_crear_editar_sitio, container, false);
        // rellenar opciones spinner
        Spinner spinner = view.findViewById(R.id.txtTipoSitio);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.tipos_sitio, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        TextView txtTitulo = view.findViewById(R.id.tituloTxt);
        TextView txtNombre = view.findViewById(R.id.txtNombre);
        TextView txtDescripcion = view.findViewById(R.id.txtDescripcion);
        TextView txtLatitud = view.findViewById(R.id.txtLatitud);
        TextView txtLongitud = view.findViewById(R.id.txtLongitud);
        Spinner txtTipoSitio = view.findViewById(R.id.txtTipoSitio);

        if (sitio != null) {
            txtTitulo.setText(R.string.editar_sitio);
            txtNombre.setText(sitio.getNombre());
            txtDescripcion.setText(sitio.getDescripcion());
            txtLatitud.setText(String.valueOf(sitio.getLatitud()));
            txtLongitud.setText(String.valueOf(sitio.getLongitud()));
            txtTipoSitio.setSelection(adapter.getPosition(sitio.getTipo()));
        } else {
            txtTitulo.setText(R.string.crear_sitio);
        }
        Button btnVolver = view.findViewById(R.id.btnVolver);
        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getActivity() != null) {
                    ((MainActivity) getActivity()).cambiarFragmentLista();
                }
            }

            ;
        });
        Button btnGuardar = view.findViewById(R.id.txtGuardar);
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nombre = txtNombre.getText().toString();
                String descripcion = txtDescripcion.getText().toString();
                String tipoSitio = txtTipoSitio.getSelectedItem().toString();
                double latitud = Double.parseDouble(txtLatitud.getText().toString());
                double longitud = Double.parseDouble(txtLongitud.getText().toString());
                assert getActivity() != null;
                if (sitio == null) {
                    ((MainActivity) getActivity()).db.sitioDAO().insertar(new Sitio(latitud, longitud, nombre, tipoSitio, descripcion));
                } else {
                    sitio.setLatitud(latitud);
                    sitio.setLongitud(longitud);
                    sitio.setNombre(nombre);
                    sitio.setTipo(tipoSitio);
                    sitio.setDescripcion(descripcion);
                    ((MainActivity) getActivity()).db.sitioDAO().actualizar(sitio);
                }
                assert getActivity() != null;
                ((MainActivity) getActivity()).cambiarFragmentLista();

            }
        });
        return view;
    }
}