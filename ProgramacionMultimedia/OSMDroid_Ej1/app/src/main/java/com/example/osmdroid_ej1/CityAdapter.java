package com.example.osmdroid_ej1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CityAdapter extends RecyclerView.Adapter<CityAdapter.CityViewHolder> {

    public interface CityListener {
        void onCitySelected(City city);
    }

    private final List<City> cities;
    private final CityListener listener;

    public CityAdapter(List<City> cities, CityListener listener) {
        this.cities = cities;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_city, parent, false);
        return new CityViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CityViewHolder holder, int position) {
        City city = cities.get(position);
        holder.bind(city, listener);
    }

    @Override
    public int getItemCount() {
        return cities.size();
    }

    static class CityViewHolder extends RecyclerView.ViewHolder {
        private final TextView nameView;
        private final TextView coordsView;

        CityViewHolder(@NonNull View itemView) {
            super(itemView);
            nameView = itemView.findViewById(R.id.cityName);
            coordsView = itemView.findViewById(R.id.cityCoords);
        }

        void bind(City city, CityListener listener) {
            nameView.setText(city.name);
            if (city.hasCoordinates()) {
                coordsView.setVisibility(View.VISIBLE);
                coordsView.setText(itemView.getContext().getString(R.string.coords_format, city.latitude, city.longitude));
            } else {
                coordsView.setVisibility(View.GONE);
            }
            itemView.setOnClickListener(v -> listener.onCitySelected(city));
        }
    }
}
