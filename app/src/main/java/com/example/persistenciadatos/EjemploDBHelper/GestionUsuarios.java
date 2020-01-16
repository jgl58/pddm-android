package com.example.persistenciadatos.EjemploDBHelper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.persistenciadatos.EjemploDBHelper.SQLiteHelper.SQliteHelper;
import com.example.persistenciadatos.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GestionUsuarios extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Button btnActualizar;
    Map<Integer,String> mapUsuarios;
    SQliteHelper dbHelper;
    SQLiteDatabase db;
    List<String> usuarios;
    Spinner spinner;
    String userId = "1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestion);

        dbHelper = new SQliteHelper(this,"DBUsuarios",null,1);
        db = dbHelper.getWritableDatabase();
        usuarios = new ArrayList<>();
        mapUsuarios = new HashMap<>();

        cargarUsuarios();
        iniciarElementos();
    }

    public void cargarUsuarios(){
        Cursor result = dbHelper.consultarUsuarios(db);
        if(result.moveToFirst()){
            do{
                mapUsuarios.put(result.getInt(0),result.getString(1));
                usuarios.add(result.getString(1));
            }while (result.moveToNext());
        }

    }


    public void iniciarElementos(){
        spinner = findViewById(R.id.usuarios_spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,android.R.layout.simple_spinner_item,usuarios);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        btnActualizar = findViewById(R.id.btnActualizar);

        btnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ActualizarActivity.class);
                intent.putExtra("ID", userId);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        /*String name = spinner.getSelectedItem().toString();
        String id = spinnerMap.get(spinner.getSelectedItemPosition());*/
        userId = String.valueOf(position + 1);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}

