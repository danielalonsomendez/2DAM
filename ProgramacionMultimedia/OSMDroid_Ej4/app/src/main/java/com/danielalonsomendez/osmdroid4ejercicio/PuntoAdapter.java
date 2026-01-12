package com.danielalonsomendez.osmdroid4ejercicio;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;
import java.util.Set;

public class PuntoAdapter extends ArrayAdapter<GeoPunto> {

    private Set<Integer> selectedPositions;
    private Runnable onSelectionChanged;

    public PuntoAdapter(@NonNull Context context, @NonNull List<GeoPunto> objects, Set<Integer> selectedPositions, Runnable onSelectionChanged) {
        super(context, 0, objects);
        this.selectedPositions = selectedPositions;
        this.onSelectionChanged = onSelectionChanged;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        TextView textNombre = convertView.findViewById(R.id.textNombre);
        CheckBox checkBox = convertView.findViewById(R.id.checkBox);

        GeoPunto punto = getItem(position);
        textNombre.setText(punto.getNombre());

        checkBox.setChecked(selectedPositions.contains(position));

        checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                if (selectedPositions.size() < 2) {
                    selectedPositions.add(position);
                } else {
                    // No permitir más de 2
                    checkBox.setChecked(false);
                }
            } else {
                selectedPositions.remove(position);
            }
            onSelectionChanged.run();
        });

        return convertView;
    }
}
