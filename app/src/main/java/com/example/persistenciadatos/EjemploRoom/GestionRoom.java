package com.example.persistenciadatos.EjemploRoom;

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

import com.example.persistenciadatos.EjemploDBHelper.ActualizarActivity;
import com.example.persistenciadatos.EjemploDBHelper.NuevoUsuario;
import com.example.persistenciadatos.EjemploDBHelper.SQLiteHelper.SQliteHelper;
import com.example.persistenciadatos.EjemploRoom.RoomUtilities.DatabaseRoom;
import com.example.persistenciadatos.EjemploRoom.RoomUtilities.UsuarioRoom;
import com.example.persistenciadatos.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GestionRoom extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Button btnActualizar, btnNuevo, btnEliminar;
    List<UsuarioRoom> mapUsuarios;
    DatabaseRoom db;
    List<String> usuarios;
    ArrayAdapter<String> adapter;
    Spinner spinner;
    int userId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestion_room);

        db = DatabaseRoom.getInstance(getApplicationContext());

        usuarios = new ArrayList<>();
        mapUsuarios = new ArrayList<>();

        cargarUsuarios();
        iniciarElementos();


        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                db.usuarioDAO().delete(mapUsuarios.get(userId));
                mapUsuarios.remove(userId);
                adapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        mapUsuarios.clear();
        usuarios.clear();
        cargarUsuarios();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        db.close();
    }

    public void cargarUsuarios(){
        List<UsuarioRoom> result = db.usuarioDAO().getAll();

        for (int i=0; i<result.size();i++){
            usuarios.add(result.get(i).getNombre());
            mapUsuarios.add(result.get(i));

        }
    }



    public void iniciarElementos(){
        spinner = findViewById(R.id.usuarios_spinner_room);
        adapter = new ArrayAdapter<>(
                this,android.R.layout.simple_spinner_item,usuarios);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        btnActualizar = findViewById(R.id.btnActualizarRoom);

        btnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), ActualizarRoom.class);
                intent.putExtra("ID", mapUsuarios.get(userId).getId());
                startActivity(intent);
            }
        });

        btnNuevo = findViewById(R.id.btnNuevoUsuarioRoom);

        btnNuevo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), NuevoUsuarioRoom.class);
                startActivity(intent);
            }
        });

        btnEliminar = findViewById(R.id.btnEliminarRoom);



    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        userId = position;
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
