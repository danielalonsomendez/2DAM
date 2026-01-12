package com.danielalonsomendez.osmdroid2ejercicio;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ListaActivity extends AppCompatActivity {


    private ListView listView;
    private ArrayAdapter<GeoPunto> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        setContentView(R.layout.activity_lista);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.layout), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        listView = findViewById(R.id.lista);
        Button btnFormulario = findViewById(R.id.btnFormulario);

        adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                GeoRepositorio.puntos
        );

        listView.setAdapter(adapter);

        listView.setOnItemClickListener((parent, view, position, id) -> {
            GeoPunto p = GeoRepositorio.puntos.get(position);

            Intent i = new Intent(this, MapaActivity.class);
            i.putExtra("lat", p.getLatitud());
            i.putExtra("lon", p.getLongitud());
            i.putExtra("nombre", p.getNombre());
            startActivity(i);
        });

        btnFormulario.setOnClickListener(v ->
                startActivity(new Intent(this, FormularioActivity.class))
        );

    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }


}