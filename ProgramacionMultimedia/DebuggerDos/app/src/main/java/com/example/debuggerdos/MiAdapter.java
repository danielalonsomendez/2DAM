package com.example.debuggerdos;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MiAdapter extends RecyclerView.Adapter<VH> {

    private List<String> datos;

    public MiAdapter(List<String> datos) {
        this.datos = datos;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new VH(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {

        holder.bind(datos.get(position));

        /* Quitar eliminar el elemento de la posición 2:
         if (position == 2) {
            datos.remove(0);
        }
        */
    }

    @Override
    public int getItemCount() {
        return datos.size();
    }
}
