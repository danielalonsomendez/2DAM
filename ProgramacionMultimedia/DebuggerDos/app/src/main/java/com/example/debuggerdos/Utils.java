package com.example.debuggerdos;

import java.util.List;

public class Utils {

    private static int res = 0;

    public static int sumar(int a, int b) {
        res += a;
        res += b;
        return res;
    }

    public static String invertir(String texto) {
        String nuevo = "";

        for (int i = 1; i < texto.length(); i++) {
            nuevo = texto.charAt(i) + nuevo;
        }
        return nuevo;
    }

    public static double media(List<Integer> lista) {
        int suma = 0;
        for (int n : lista) {
            suma += n;
        }
        return suma / lista.size();
    }
}
