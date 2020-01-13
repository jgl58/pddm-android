package com.example.persistenciadatos.EjemploFicheros;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.persistenciadatos.R;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import static com.example.persistenciadatos.Ejemplo1.Ejemplo1.PREFERENCES;
import static com.example.persistenciadatos.EjemploFicheros.FilesAccess.leerFicheroExterna;
import static com.example.persistenciadatos.EjemploFicheros.FilesAccess.leerFicheroInterna;
import static com.example.persistenciadatos.Encriptacion.decode;
import static com.example.persistenciadatos.Encriptacion.encode;

public class Fichero extends AppCompatActivity {

    public String PREF_FICHERO = "PREF_FICHERO";
    SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fichero);

        preferences = getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE);

        TextView texto = findViewById(R.id.contenidoFichero);

        String storage = decode(preferences.getString(encode(PREF_FICHERO),""));
        Log.d("Debug",storage);
        if(storage.equals("interna")){
           setTitle("Memoria interna");
           Log.d("Debug", leerFicheroInterna(getApplicationContext()));
            texto.setText(leerFicheroInterna(getApplicationContext()));
        }else{
           setTitle("Memoria externa");
            Log.d("Debug", leerFicheroExterna(getApplicationContext()));
            texto.setText(leerFicheroExterna(getApplicationContext()));
        }


        findViewById(R.id.btnVolverFichero).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}
