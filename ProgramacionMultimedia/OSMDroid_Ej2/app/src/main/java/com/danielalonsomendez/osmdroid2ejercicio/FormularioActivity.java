package com.danielalonsomendez.osmdroid2ejercicio;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class FormularioActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_formulario);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.formulario), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        EditText nombre = findViewById(R.id.edtNombre);
        EditText lat = findViewById(R.id.edtLat);
        EditText lon = findViewById(R.id.edtLon);
        Button guardar = findViewById(R.id.btnGuardar);

        guardar.setOnClickListener(v -> {
            GeoPunto p = new GeoPunto(
                    nombre.getText().toString(),
                    Double.parseDouble(lat.getText().toString()),
                    Double.parseDouble(lon.getText().toString())
            );

            // Añadir al repositorio común
            GeoRepositorio.puntos.add(p);

            // Mostrar directamente en el mapa
            Intent i = new Intent(this, MapaActivity.class);
            i.putExtra("lat", p.getLatitud());
            i.putExtra("lon", p.getLongitud());
            i.putExtra("nombre", p.getNombre());
            startActivity(i);

            finish();
        });


    }
}