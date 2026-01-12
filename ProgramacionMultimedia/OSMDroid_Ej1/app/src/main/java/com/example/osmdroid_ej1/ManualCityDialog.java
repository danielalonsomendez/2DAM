package com.example.osmdroid_ej1;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;

public class ManualCityDialog extends Dialog {

    public interface ManualCityListener {
        void onManualCityFilled(City city);
    }

    private final ManualCityListener listener;

    public ManualCityDialog(@NonNull Context context, ManualCityListener listener) {
        super(context);
        this.listener = listener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_manual_city);

        EditText nameInput = findViewById(R.id.inputName);
        EditText latInput = findViewById(R.id.inputLat);
        EditText lonInput = findViewById(R.id.inputLon);
        Button confirmButton = findViewById(R.id.confirmButton);
        Button cancelButton = findViewById(R.id.cancelButton);

        confirmButton.setOnClickListener(v -> {
            String name = nameInput.getText().toString().trim();
            String latText = latInput.getText().toString().trim();
            String lonText = lonInput.getText().toString().trim();

            if (TextUtils.isEmpty(name) || TextUtils.isEmpty(latText) || TextUtils.isEmpty(lonText)) {
                Toast.makeText(getContext(), R.string.manual_dialog_error, Toast.LENGTH_SHORT).show();
                return;
            }

            try {
                double lat = Double.parseDouble(latText);
                double lon = Double.parseDouble(lonText);
                listener.onManualCityFilled(new City(name, lat, lon));
                dismiss();
            } catch (NumberFormatException e) {
                Toast.makeText(getContext(), R.string.manual_dialog_error, Toast.LENGTH_SHORT).show();
            }
        });

        cancelButton.setOnClickListener(v -> dismiss());
    }
}
