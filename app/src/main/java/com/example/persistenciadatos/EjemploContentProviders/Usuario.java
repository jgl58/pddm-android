package com.example.persistenciadatos.EjemploContentProviders;

import android.provider.BaseColumns;

public final class Usuario implements BaseColumns {

    private Usuario() {

    }
    public static String COL_NOMBRE = "nombre";
    public static String COL_NOMBRE_COMPLETO = "nombre_completo";
    public static String COL_PASSWORD = "password";
    public static String COL_TELEFONO = "telefono";
    public static String COL_EMAIL = "email";

}
