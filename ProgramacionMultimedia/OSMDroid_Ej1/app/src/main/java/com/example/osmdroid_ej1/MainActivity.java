package com.example.osmdroid_ej1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements CityAdapter.CityListener {

    private RecyclerView recyclerView;
    private final List<City> cities = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.cityRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new CityAdapter(cities, this));

        loadCities();
    }

    private void loadCities() {
        cities.clear();
        cities.add(new City("Madrid", 40.4168, -3.7038));
        cities.add(new City("Barcelona", 41.3874, 2.1686));
        cities.add(new City("Valencia", 39.4699, -0.3763));
        cities.add(new City("Sevilla", 37.3891, -5.9845));
        cities.add(new City("Bilbao", 43.2631, -2.9349));
        cities.add(new City(getString(R.string.manual_option_title), null, null));
        recyclerView.getAdapter().notifyDataSetChanged();
    }

    @Override
    public void onCitySelected(City city) {
        if (city.latitude == null || city.longitude == null) {
            ManualCityDialog dialog = new ManualCityDialog(this, this::openMapWithCity);
            dialog.show();
        } else {
            openMapWithCity(city);
        }
    }

    private void openMapWithCity(City city) {
        if (city == null || city.latitude == null || city.longitude == null) {
            Toast.makeText(this, R.string.manual_dialog_error, Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intent = new Intent(this, MapActivity.class);
        intent.putExtra(MapActivity.EXTRA_CITY_NAME, city.name);
        intent.putExtra(MapActivity.EXTRA_CITY_LAT, city.latitude);
        intent.putExtra(MapActivity.EXTRA_CITY_LON, city.longitude);
        startActivity(intent);
    }
}