// java
package com.danialonso.room03;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ListaAdapter extends RecyclerView.Adapter<ListaAdapter.ViewHolder> {
    private List<Nota> notas;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public ListaAdapter(List<Nota> notas) {
        this.notas = notas;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_nota, parent, false);
        return new ViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvCiudad.setText(String.format(java.util.Locale.getDefault(), "%s - %.2f", notas.get(position).getModulo(), notas.get(position).getCalificacion()));
        holder.tvCiudad.setTextSize(20);
        holder.tvCiudad.setPadding(16,16,16,16);
        holder.tvCiudad.setBackgroundResource(android.R.color.holo_green_light);
    }

    @Override
    public int getItemCount() {
        return notas != null ? notas.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvCiudad;

        public ViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            tvCiudad = itemView.findViewById(R.id.textView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}