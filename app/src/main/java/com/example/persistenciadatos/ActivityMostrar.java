package com.example.persistenciadatos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import static com.example.persistenciadatos.Ejemplo1.DEFAULT_TAM;
import static com.example.persistenciadatos.Ejemplo1.DEFAULT_TEXTO;
import static com.example.persistenciadatos.Ejemplo1.PREFERENCES;
import static com.example.persistenciadatos.Ejemplo1.PREF_TAM;
import static com.example.persistenciadatos.Ejemplo1.PREF_TEXTO;
import static com.example.persistenciadatos.Encriptacion.*;


public class ActivityMostrar extends AppCompatActivity {

    SharedPreferences preferences;
    TextView texto;
    Button volver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar);

        preferences = getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE);
        texto = findViewById(R.id.textoMostrar);

        String tam = decrypt(preferences.getString(encrypt(PREF_TAM), encrypt(DEFAULT_TAM)));
        texto.setText(decrypt(preferences.getString(encrypt(PREF_TEXTO),encrypt(DEFAULT_TEXTO))));
        texto.setTextSize(Float.parseFloat(tam));


        volver = findViewById(R.id.btnVolver);
        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}
