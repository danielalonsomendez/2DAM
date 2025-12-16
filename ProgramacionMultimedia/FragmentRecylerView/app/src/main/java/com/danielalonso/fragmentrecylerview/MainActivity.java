package com.danielalonso.fragmentrecylerview;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<String> ciudades;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        ciudades = new ArrayList<>();
        ciudades.add("Madrid");
        ciudades.add("Barcelona");
        ciudades.add("Valencia");
        ciudades.add("Sevilla");
        ciudades.add("Zaragoza");
        ciudades.add("Málaga");
        ciudades.add("Murcia");
        ciudades.add("Palma");
        ciudades.add("Las Palmas");
        ciudades.add("Bilbao");
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.contenedor, FragmentLista.newInstance(ciudades))
                .commit();
    }

    public void abrirDetalleCiudad(String ciudad) {
        FragmentDetalle detalle = FragmentDetalle.newInstance(ciudad);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.contenedor, detalle)
                .addToBackStack(null)
                .commit();
    }
    public void volverLista() {
        getSupportFragmentManager().popBackStack();
    }
}