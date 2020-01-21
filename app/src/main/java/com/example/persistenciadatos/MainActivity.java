package com.example.persistenciadatos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.persistenciadatos.Ejemplo1.Ejemplo1;
import com.example.persistenciadatos.EjemploDBHelper.GestionUsuarios;
import com.example.persistenciadatos.EjemploDBHelper.LoginUsuarios;
import com.example.persistenciadatos.EjemploFicheros.Ficheros;
import com.example.persistenciadatos.EjemploRoom.LoginRoom;
import com.example.persistenciadatos.EjemploSettings.Ejemplo2;

public class MainActivity extends AppCompatActivity {

    Button btnEjemplo1, btnEjemplo2, btnEjemplo3, btnSqlite,btnRoom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnEjemplo1 = findViewById(R.id.btnEjemplo1);
        btnEjemplo2 = findViewById(R.id.btnEjemplo2);
        btnEjemplo3 = findViewById(R.id.btnEjemplo3);
        btnSqlite = findViewById(R.id.btnSqlite);
        btnRoom = findViewById(R.id.btnRoom);


        btnEjemplo1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Ejemplo1.class);
                startActivity(intent);
            }
        });
        btnEjemplo2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Ejemplo2.class);
                startActivity(intent);
            }
        });
        btnEjemplo3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Ficheros.class);
                startActivity(intent);
            }
        });

        btnSqlite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginUsuarios.class);
                startActivity(intent);
            }
        });
        btnRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginRoom.class);
                startActivity(intent);
            }
        });

    }
}
