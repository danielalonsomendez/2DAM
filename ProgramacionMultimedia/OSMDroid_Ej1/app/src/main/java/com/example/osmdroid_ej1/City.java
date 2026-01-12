package com.example.osmdroid_ej1;

import androidx.annotation.Nullable;

public class City {
    public final String name;
    @Nullable
    public final Double latitude;
    @Nullable
    public final Double longitude;

    public City(String name, @Nullable Double latitude, @Nullable Double longitude) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public boolean hasCoordinates() {
        return latitude != null && longitude != null;
    }
}
