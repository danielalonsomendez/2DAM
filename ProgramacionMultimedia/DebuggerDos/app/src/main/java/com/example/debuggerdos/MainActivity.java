package com.example.debuggerdos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText etA, etB, etCadena;
    private Button btnSumar, btnInvertir, btnMedia, btnUsuario;
    private TextView tvResultado;
    private RecyclerView rv;
    // Rellenar usuario con datos de ejemplo
    private Usuario usuario = new Usuario("Juana");

    private List<String> datosRecycler = new ArrayList<>(
            Arrays.asList("Uno", "Dos", "Tres", "Cuatro", "Cinco", "Seis")
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etA = findViewById(R.id.etA);
        etB = findViewById(R.id.etB);
        etCadena = findViewById(R.id.etCadena);

        btnSumar = findViewById(R.id.btnSumar);
        btnInvertir = findViewById(R.id.btnInvertir);
        btnMedia = findViewById(R.id.btnMedia);
        btnUsuario = findViewById(R.id.btnUsuario);

        tvResultado = findViewById(R.id.tvResultado);

        rv = findViewById(R.id.rvMain);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(new MiAdapter(datosRecycler));

        btnSumar.setOnClickListener(v -> {
            int a = Integer.parseInt(etA.getText().toString());
            int b = Integer.parseInt(etB.getText().toString());
            int r = Utils.sumar(a, b);
            tvResultado.setText("Suma = " + r);
        });

        btnInvertir.setOnClickListener(v -> {
            String r = Utils.invertir(etCadena.getText().toString());
            tvResultado.setText("Invertido = " + r);
        });

        btnMedia.setOnClickListener(v -> {
            List<Integer> lista = Arrays.asList(1, 2, 3, 4, 5);
            double r = Utils.media(lista);
            tvResultado.setText("Media = " + r);
        });

        btnUsuario.setOnClickListener(v -> {
            String nombre = usuario.nombre.toUpperCase();
            tvResultado.setText("Usuario: " + nombre);
        });
    }
}
