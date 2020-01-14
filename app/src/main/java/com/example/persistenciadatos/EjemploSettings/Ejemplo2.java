package com.example.persistenciadatos.EjemploSettings;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.persistenciadatos.R;

public class Ejemplo2 extends AppCompatActivity {

    TextView textoFinal;
    Button btnVisualizar, btnSalir;
    EditText texto;
    SharedPreferences preferences;
    float textSize;
    String color, background;
    Boolean negrita = false, cursiva = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejemplo2);


        texto = findViewById(R.id.textoPrevisualizar);
        textoFinal = findViewById(R.id.textoFinal);
        btnVisualizar = findViewById(R.id.btnVisualizar);
        leerPreferencias();

        btnVisualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                leerPreferencias();
                textoFinal.setText(texto.getText());
                textoFinal.setTextSize(textSize);
                textoFinal.setBackgroundColor(Color.parseColor(background));
                textoFinal.setTextColor(Color.parseColor(color));
                textoFinal.setTypeface(null,Typeface.NORMAL);
                if(negrita) {
                    textoFinal.setTypeface(null, Typeface.BOLD);
                }
                if(cursiva){
                    textoFinal.setTypeface(null,Typeface.ITALIC);
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        leerPreferencias();
    }

    public void leerPreferencias(){

        preferences= PreferenceManager.getDefaultSharedPreferences(this);
        String tamaño = preferences.getString("size","32");
        textSize = Float.parseFloat(tamaño);
        color = preferences.getString("color","#000000");
        background = preferences.getString("background","#FFFFFF");
        negrita = preferences.getBoolean("bold",false);
        cursiva = preferences.getBoolean("italic",false);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.ejemplo2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
                startActivity(intent);
                return true;



            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }
}
