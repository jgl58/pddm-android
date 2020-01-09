package com.example.persistenciadatos.Ejemplo1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.persistenciadatos.R;

import static com.example.persistenciadatos.Encriptacion.*;


public class Ejemplo1 extends AppCompatActivity {

    public static String PREFERENCES = "Preferencias";
    public static String PREF_TEXTO = "texto";
    public static String PREF_TAM = "tam";
    public static String DEFAULT_TEXTO = "texto por defecto";
    public static String DEFAULT_TAM = "32";

    SharedPreferences preferences;
    TextView textoGuardado;
    TextView tamGuardado;
    Button guardar;
    Button salir;
    SeekBar tamano;
    TextView texto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejemplo1);

        preferences = getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE);

        guardar = findViewById(R.id.btnAplicar);
        salir = findViewById(R.id.btnSalir);
        tamano = findViewById(R.id.seekBarTam);
        texto = findViewById(R.id.etTexto);
        textoGuardado = findViewById(R.id.textoGuardado);
        tamGuardado = findViewById(R.id.tamGuardado);


        textoGuardado.setText(decrypt(preferences.getString(encrypt(PREF_TEXTO),encrypt(DEFAULT_TEXTO))));
        tamGuardado.setText(decrypt(preferences.getString(encrypt(PREF_TAM), encrypt(DEFAULT_TAM))));


        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString(encrypt(PREF_TEXTO), encrypt(String.valueOf(texto.getText())));
                editor.putString(encrypt(PREF_TAM), encrypt(String.valueOf(tamano.getProgress())));
                editor.commit();

                Intent intent = new Intent(getApplicationContext(),ActivityMostrar.class);
                startActivity(intent);
            }
        });

        salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        preferences = getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE);
        textoGuardado.setText(decrypt(preferences.getString(encrypt(PREF_TEXTO),encrypt(DEFAULT_TEXTO))));
        tamGuardado.setText(decrypt(preferences.getString(encrypt(PREF_TAM), encrypt(DEFAULT_TAM))));

    }
}
