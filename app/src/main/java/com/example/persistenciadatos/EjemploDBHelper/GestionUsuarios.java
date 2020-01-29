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

import static com.example.persistenciadatos.EjemploDBHelper.BackupUtlilty.BackupUtility.DB_VERSION;

public class GestionUsuarios extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Button btnActualizar, btnNuevo, btnEliminar;
    List<Integer> mapUsuariosIds;
    SQliteHelper dbHelper;
    SQLiteDatabase db;
    List<String> usuarios;
    ArrayAdapter<String> adapter;
    Spinner spinner;
    int userId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestion);

        dbHelper = new SQliteHelper(this,"DBUsuarios",null,DB_VERSION);
        db = dbHelper.getWritableDatabase();
        usuarios = new ArrayList<>();
        mapUsuariosIds = new ArrayList<>();

        cargarUsuarios();
        iniciarElementos();


        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper.deleteUsuario(String.valueOf(mapUsuariosIds.get(userId)),db);
                mapUsuariosIds.remove(userId);
                usuarios.remove(userId);
                adapter.notifyDataSetChanged();

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("Debug","OnResume");
        mapUsuariosIds.clear();
        usuarios.clear();
        cargarUsuarios();
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        mapUsuariosIds.clear();
        usuarios.clear();
        cargarUsuarios();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        db.close();
    }

    public void cargarUsuarios(){
        Cursor result = dbHelper.consultarUsuarios(db);
        if(result.moveToFirst()){
            do{
                mapUsuariosIds.add(result.getInt(0));
                usuarios.add(result.getString(1));
            }while (result.moveToNext());

        }

    }


    public void iniciarElementos(){
        spinner = findViewById(R.id.usuarios_spinner);
        adapter = new ArrayAdapter<>(
                this,android.R.layout.simple_spinner_item,usuarios);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        btnActualizar = findViewById(R.id.btnActualizar);

        btnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ActualizarActivity.class);
                intent.putExtra("ID", mapUsuariosIds.get(userId));
                startActivity(intent);
            }
        });

        btnNuevo = findViewById(R.id.btnNuevoUsuario);

        btnNuevo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), NuevoUsuario.class);
                startActivity(intent);
            }
        });

        btnEliminar = findViewById(R.id.btnEliminar);



    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        /*String name = spinner.getSelectedItem().toString();
        String id = spinnerMap.get(spinner.getSelectedItemPosition());*/
        userId = position;
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}

