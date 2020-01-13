package com.example.persistenciadatos;


import android.util.Base64;

public class Encriptacion {

    public static String encode(String input){
        return Base64.encodeToString(input.getBytes(), Base64.DEFAULT);
    }

    public static String decode(String input){
        return new String(Base64.decode(input, Base64.DEFAULT));
    }
}
