package com.danielalonsomendez.osmdroid4ejercicio;

import java.util.ArrayList;
import java.util.List;

public class GeoRepositorio {

    public static final List<GeoPunto> puntos = new ArrayList<>();

    static {
        puntos.add(new GeoPunto("Bilbao", 43.2630, -2.9350));
        puntos.add(new GeoPunto("Madrid", 40.4168, -3.7038));
        puntos.add(new GeoPunto("Barcelona", 41.3874, 2.1686));
    }
}
