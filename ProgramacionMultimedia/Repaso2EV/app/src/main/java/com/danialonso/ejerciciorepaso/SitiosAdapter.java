package com.danialonso.ejerciciorepaso;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SitiosAdapter extends RecyclerView.Adapter<SitiosAdapter.MiViewHolder> {

    private List<Sitio> datos;

    static class MiViewHolder extends RecyclerView.ViewHolder {
        TextView nombre;
        TextView tipo;
        TextView descripcion;
        Button btnEditar;
        Button btnBorrar;


        MiViewHolder(View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.nombre);
            tipo = itemView.findViewById(R.id.tipo);
            descripcion = itemView.findViewById(R.id.descripcion);
            btnEditar = itemView.findViewById(R.id.btnEditar);
            btnBorrar = itemView.findViewById(R.id.btnBorrar);
        }
    }

    @Override
    public MiViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_layout, parent, false);
        return new MiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MiViewHolder holder, int position) {
        Sitio item = datos.get(position);
        holder.nombre.setText(item.getNombre());
        holder.tipo.setText(item.getTipo());
        holder.descripcion.setText(item.getDescripcion());
        holder.btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ( holder.btnEditar.getContext() instanceof MainActivity) {
                    ((MainActivity)  holder.btnEditar.getContext()).cambiarFragmentEditar(item);
                }
            }
        });
        holder.btnBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ( holder.btnEditar.getContext() instanceof MainActivity) {
                    ((MainActivity)  holder.btnEditar.getContext()).db.sitioDAO().eliminar(item);
actualizar(((MainActivity)  holder.btnEditar.getContext()).db.sitioDAO().obtenerTodos());
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return datos.size();
    }

    public void actualizar(List<Sitio> nuevosDatos) {
        this.datos = nuevosDatos;
        notifyDataSetChanged();
    }
}

