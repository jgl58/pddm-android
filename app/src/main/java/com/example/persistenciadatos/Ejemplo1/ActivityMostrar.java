package com.example.persistenciadatos.Ejemplo1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import com.example.persistenciadatos.R;

import static com.example.persistenciadatos.Ejemplo1.Ejemplo1.DEFAULT_TAM;
import static com.example.persistenciadatos.Ejemplo1.Ejemplo1.DEFAULT_TEXTO;
import static com.example.persistenciadatos.Ejemplo1.Ejemplo1.PREFERENCES;
import static com.example.persistenciadatos.Ejemplo1.Ejemplo1.PREF_TAM;
import static com.example.persistenciadatos.Ejemplo1.Ejemplo1.PREF_TEXTO;
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

        String tam = decode(preferences.getString(encode(PREF_TAM), encode(DEFAULT_TAM)));
        texto.setText(decode(preferences.getString(encode(PREF_TEXTO), encode(DEFAULT_TEXTO))));
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
