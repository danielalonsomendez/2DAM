package com.danialonso.room02;


import android.app.AlertDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import java.util.List;


public class MainActivity extends AppCompatActivity {

    private AppDatabase db;
    private EditText etModulo, etCalificacion;
    private RecyclerView listaNotas;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = Room.databaseBuilder(
                getApplicationContext(),
                AppDatabase.class,
                "notas_db")
                .allowMainThreadQueries()
                .build();

        etModulo = findViewById(R.id.etModulo);
        etCalificacion = findViewById(R.id.etCalificacion);
        listaNotas = findViewById(R.id.listaNotas);
        searchView= findViewById(R.id.searchView);
        Button btnAnadir = findViewById(R.id.btnAnadir);
        listaNotas.setLayoutManager(new LinearLayoutManager(this));

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText == null || newText.trim().isEmpty()) {
                    cargarNotas();
                    return false;
                }

                List<Nota> notasFiltradas = db.notaDao().buscarPorModulo("%" + newText + "%");
                ListaAdapter adapter = new ListaAdapter(notasFiltradas);
                adapter.setOnItemClickListener(new ListaAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        mostrarDialogoBorrar(notasFiltradas.get(position));
                    }
                });
                listaNotas.setAdapter(adapter);
                return false;
            }
        });

        cargarNotas();

        btnAnadir.setOnClickListener(v -> {
            anadirNota();
            cargarNotas();
        });

    }

    private void anadirNota(){
        String modulo = etModulo.getText().toString();
        String calificacion = etCalificacion.getText().toString();

        if(modulo.isEmpty() ||  calificacion.isEmpty()) {
            Toast.makeText(this, "Rellena los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        Nota nota = new Nota(modulo, calificacion);
        db.notaDao().insertar(nota);

        etModulo.setText("");
        etCalificacion.setText("");

        Toast.makeText(this, "Nota añadida", Toast.LENGTH_SHORT).show();
    }

    private void cargarNotas(){

        listaNotas.removeAllViews();

        List<Nota> notas = db.notaDao().obtenerTodas();

        ListaAdapter adapter = new ListaAdapter(notas);
        adapter.setOnItemClickListener(new ListaAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                mostrarDialogoBorrar(notas.get(position));
            }
        });
        listaNotas.setAdapter(adapter);

// CON LIST VIEW
      /*  for (Nota nota: notas){
            TextView tv = new TextView(this);

            // Margen entre items
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            params.setMargins(0, 0, 0, 8);
            tv.setLayoutParams(params);


            tv.setText(nota.getModulo() + " - " + nota.getCalificacion());
            tv.setTextSize(20);
            tv.setPadding(16,16,16,16);
            tv.setBackgroundResource(android.R.color.holo_green_light);
            // Listener para borrar al tocar
            tv.setOnClickListener(v -> mostrarDialogoBorrar(nota));

            listaNotas.addView(tv);

        }*/

    }

    private void mostrarDialogoBorrar(Nota nota){

        new AlertDialog.Builder(this)
                .setTitle("Borrar nota")
                .setMessage("¿Quieres borrar la nota?")
                .setPositiveButton("Si", (dialog, which) -> {
                    db.notaDao().borrar(nota);
                    cargarNotas();
                })
                .setNegativeButton("No", null)
                .show();


    }

}