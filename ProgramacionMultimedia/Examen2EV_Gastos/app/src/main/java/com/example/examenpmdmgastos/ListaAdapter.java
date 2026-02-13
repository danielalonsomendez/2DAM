package com.example.examenpmdmgastos;

import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.examenpmdmroom.R;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ListaAdapter extends RecyclerView.Adapter<ListaAdapter.MiViewHolder> {

    private List<Gasto> datos;


    // ViewHolder - contiene las referencias a las vistas de cada item
    static class MiViewHolder extends RecyclerView.ViewHolder {

        TextView txtConcepto;
        TextView txtImporte;
        TextView txtCategoria;
        TextView txtFecha;
        Button btnBorrar;

        MiViewHolder(View itemView) {
            super(itemView);
            txtCategoria = itemView.findViewById(R.id.categoria);
            txtImporte = itemView.findViewById(R.id.importe);
            txtFecha = itemView.findViewById(R.id.fecha);
            txtConcepto = itemView.findViewById(R.id.concepto);
            btnBorrar = itemView.findViewById(R.id.btnBorrar);


        }
    }

    @Override
    public MiViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_layout, parent, false);
        return new MiViewHolder(view);
    }
    // Crea nuevas vistas (invocado por el LayoutManager)

    @Override
    public void onBindViewHolder(MiViewHolder holder, int position) {
        Gasto gasto = datos.get(position);

        // Conseguir importe
        DecimalFormat formato = new DecimalFormat("#,##0.00");
        String importeFormateado = formato.format(gasto.getImporte());

        // Conseguir fecha
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy HH:mm",
                Locale.getDefault());
        Date fecha = new Date(gasto.getFecha());  // timestamp es long en milisegundos
        String fechaFormateada = formatoFecha.format(fecha);  // "19/12/2024 17:30

        // Settear campos del layout
        holder.txtConcepto.setText(gasto.getConcepto());
        holder.txtCategoria.setText(gasto.getCategoria());
        holder.txtImporte.setText(String.format("%s€", importeFormateado));
        holder.txtFecha.setText(fechaFormateada);

        // AlertDialog de confirmacion de accion
        holder.btnBorrar.setOnClickListener(v -> {
            new AlertDialog.Builder(v.getContext())
                    .setTitle("¿Estas seguro?")
                    .setMessage("Estas a punto de borrar " + gasto.getConcepto())
                    .setPositiveButton("Sí", (dialog, which) -> {
                        // Borrar y actualizar fragment
                        AppDatabase db = AppDatabase.getInstance(v.getContext());
                        db.gastoDAO().eliminar(gasto);
                        actualizar(db.gastoDAO().consultaSelect());
                    })
                    .setNegativeButton("No", null)
                    .show();


        });

    }
    // Reemplaza el contenido de una vista (invocado por el LayoutManager)

    @Override
    public int getItemCount() {
        return datos.size();
    }
    // Devuelve el tamaño del dataset

    public void actualizar(List<Gasto> nuevosDatos) {
        this.datos = nuevosDatos;
        notifyDataSetChanged();
    }
    // Actualiza los datos y refresca la vista
}

