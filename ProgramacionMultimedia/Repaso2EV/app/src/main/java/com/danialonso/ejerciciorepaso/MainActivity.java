package com.danialonso.ejerciciorepaso;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentContainerView;

public class MainActivity extends AppCompatActivity {
    public AppDatabase db;

    public FragmentContainerView fragmentContainerView;

    public ListaFragment listafrag;
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
        db = AppDatabase.getInstance(this);
        listafrag = new ListaFragment();
        fragmentContainerView = findViewById(R.id.fragmentContainer);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainer, listafrag)
        .commit();
    }

    public void cambiarFragmentAnadir(){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainer, new CrearEditarSitio())             .addToBackStack(null)     .commit();
    }

    public void cambiarFragmentLista(){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainer, listafrag)            .addToBackStack(null)      .commit();
    }
    public void cambiarFragmentEditar(Sitio sitio){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainer, new CrearEditarSitio(sitio))            .addToBackStack(null)      .commit();
    }

    public void cambiarFragmentMapa(){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainer, new MapaFragment())            .addToBackStack(null)      .commit();

    }

}