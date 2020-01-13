package com.example.persistenciadatos.EjemploFicheros;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.persistenciadatos.R;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import static com.example.persistenciadatos.Ejemplo1.Ejemplo1.PREFERENCES;
import static com.example.persistenciadatos.EjemploFicheros.FilesAccess.copiarAExterna;
import static com.example.persistenciadatos.EjemploFicheros.FilesAccess.copiarAInterna;
import static com.example.persistenciadatos.EjemploFicheros.FilesAccess.escribirFicheroExterna;
import static com.example.persistenciadatos.EjemploFicheros.FilesAccess.escribirFicheroInterna;
import static com.example.persistenciadatos.EjemploFicheros.FilesAccess.leerFicheroExterna;
import static com.example.persistenciadatos.EjemploFicheros.FilesAccess.leerFicheroInterna;
import static com.example.persistenciadatos.Encriptacion.decode;
import static com.example.persistenciadatos.Encriptacion.encode;

public class Ficheros extends AppCompatActivity {

    public String PREF_FICHERO = "PREF_FICHERO";
    Button btnComprobarExterno, btnGuardar, btnVerFichero, btnMoverSD, btnMoverInterna;
    SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ficheros);

        preferences = getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE);

        if(decode(preferences.getString(encode(PREF_FICHERO),"")).equals("")){
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString(encode(PREF_FICHERO), encode("interna"));
            editor.commit();
        }

        btnComprobarExterno = findViewById(R.id.btnEstadoExterno);
        btnGuardar = findViewById(R.id.btnGuardarFichero);

        btnVerFichero = findViewById(R.id.btnVerFichero);

        btnMoverInterna = findViewById(R.id.btnMoverInterna);
        btnMoverSD = findViewById(R.id.btnMoverSD);

        listenersBotones();
        habilitarBotones();



    }

    public void habilitarBotones(){

        String storage = decode(preferences.getString(encode(PREF_FICHERO),""));
        if(storage.equals("interna")){
            TextView textView = findViewById(R.id.tvStorage);
            textView.setText("Texto a guardar en memoria interna");
            btnMoverInterna.setEnabled(false);
            btnMoverSD.setEnabled(true);
        }else{
            TextView textView = findViewById(R.id.tvStorage);
            textView.setText("Texto a guardar en memoria externa");
            btnMoverInterna.setEnabled(true);
            btnMoverSD.setEnabled(false);
        }

    }

    public void listenersBotones(){

        btnComprobarExterno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),ExternalStorage.class));
            }
        });

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String storage = decode(preferences.getString(encode(PREF_FICHERO),""));
                TextView texto = findViewById(R.id.et_texto_guardar);
                String cadena = String.valueOf(texto.getText());
                Log.d("Debug",cadena);
                if(storage.equals("interna")){
                    Log.d("Debug","Guardando en interna");
                    escribirFicheroInterna(cadena,getApplicationContext());
                }else {
                    Log.d("Debug","Guardando en externa");
                    escribirFicheroExterna(cadena, getApplicationContext());
                }
                texto.setText("");
            }
        });

        btnVerFichero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Fichero.class));
            }
        });

        btnMoverInterna.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString(encode(PREF_FICHERO), encode("interna"));
                editor.commit();
                moverFichero();
                habilitarBotones();
            }
        });

        btnMoverSD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString(encode(PREF_FICHERO), encode("sd"));
                editor.commit();

                moverFichero();
                habilitarBotones();
            }
        });
    }

    public void moverFichero(){
        preferences = getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE);
        String storage = decode(preferences.getString(encode(PREF_FICHERO),""));

        if(storage.equals("interna")){
            copiarAInterna(getApplicationContext());
        }else{
            copiarAExterna(getApplicationContext());
        }
    }

}
