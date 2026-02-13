package com.example.examenpmdmgastos;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentContainerView;

import com.example.examenpmdmroom.R;

public class MainActivity extends AppCompatActivity {

    public AppDatabase db;
    FragmentContainerView fragment;
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
        // Conseguir base de datos
        db = AppDatabase.getInstance(this);
        // Conseguir el contenedor del fragment
        fragment  = findViewById(R.id.fragmentContainerView);
        // Mostrar la lista al iniciar el activity
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView,new ListaFragment()).addToBackStack(null).commit();

    }

    public void navegaraAnadir(){
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView,new AgregarGastoFragment()).addToBackStack(null).commit();

    }
    public void navegaraLista(){
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView,new ListaFragment()).addToBackStack(null).commit();

    }

}