package com.danialonso.room03;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class EstadisticasActivity extends AppCompatActivity {

    private AppDatabase db;
    private TextView tvResultados;
    private Button btnCalcular;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estadisticas);

        db = Room.databaseBuilder(
                getApplicationContext(),
                AppDatabase.class,
                "notas_db2")
                .allowMainThreadQueries()
                .build();

        tvResultados = findViewById(R.id.tvResultados);
        btnCalcular = findViewById(R.id.btnCalcular);

        btnCalcular.setOnClickListener(v -> calcularEstadisticas());

        calcularEstadisticas();
    }

    private void calcularEstadisticas() {
        List<Nota> notas = db.notaDao().obtenerTodas();
        Map<String, List<Double>> grouped = new HashMap<>();

        for (Nota n : notas) {
            List<Double> list = grouped.get(n.getModulo());
            if (list == null) {
                list = new ArrayList<>();
                grouped.put(n.getModulo(), list);
            }
            list.add(n.getCalificacion());
        }

        StringBuilder sb = new StringBuilder();

        if (grouped.isEmpty()) {
            sb.append("No hay notas almacenadas.\n");
        } else {
            for (Map.Entry<String, List<Double>> e : grouped.entrySet()) {
                List<Double> values = e.getValue();
                Collections.sort(values);
                int n = values.size();
                double sum = 0;
                for (double v : values) sum += v;
                double mean = sum / n;

                double median;
                if (n % 2 == 1) {
                    median = values.get(n / 2);
                } else {
                    median = (values.get(n / 2 - 1) + values.get(n / 2)) / 2.0;
                }

                double min = values.get(0);
                double max = values.get(n - 1);

                double var = 0;
                for (double v : values) var += (v - mean) * (v - mean);
                double stddev = Math.sqrt(var / n);

                sb.append(String.format(Locale.getDefault(), "Módulo: %s\n  Recuento: %d\n  Media: %.2f\n  Mediana: %.2f\n  Mínimo: %.2f\n  Máximo: %.2f\n  Desviación estándar: %.2f\n\n",
                        e.getKey(), n, mean, median, min, max, stddev));
            }
        }

        tvResultados.setText(sb.toString());
    }

}