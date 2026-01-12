package com.danielalonsomendez.osmdroid4ejercicio;

import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

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

        if (savedInstanceState == null) {
            replaceFragment(new ListaFragment());
        }
    }

    public void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    public void showMap(List<GeoPunto> puntos) {
        MapaFragment mapaFragment = new MapaFragment();
        Bundle args = new Bundle();
        // Pasar la lista de puntos
        ArrayList<String> nombres = new ArrayList<>();
        ArrayList<Double> lats = new ArrayList<>();
        ArrayList<Double> lons = new ArrayList<>();
        for (GeoPunto p : puntos) {
            nombres.add(p.getNombre());
            lats.add(p.getLatitud());
            lons.add(p.getLongitud());
        }
        args.putStringArrayList("nombres", nombres);
        args.putSerializable("lats", lats);
        args.putSerializable("lons", lons);
        mapaFragment.setArguments(args);
        replaceFragment(mapaFragment);
    }

    public void showForm() {
        replaceFragment(new FormularioFragment());
    }
}
