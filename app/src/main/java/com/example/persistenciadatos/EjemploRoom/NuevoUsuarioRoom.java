package com.example.persistenciadatos.EjemploRoom;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.persistenciadatos.EjemploDBHelper.SQLiteHelper.SQliteHelper;
import com.example.persistenciadatos.EjemploRoom.RoomUtilities.DatabaseRoom;
import com.example.persistenciadatos.EjemploRoom.RoomUtilities.UsuarioRoom;
import com.example.persistenciadatos.R;

public class NuevoUsuarioRoom extends AppCompatActivity {


    DatabaseRoom db;
    Button btnGuardar;
    EditText nombre, password, nombreCompleto, email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_usuario_room);
        inicializarElementos();
        db = DatabaseRoom.getInstance(getApplicationContext());

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UsuarioRoom nuevo = new UsuarioRoom();
                nuevo.setNombre(String.valueOf(nombre.getText()));
                nuevo.setPassword(String.valueOf(password.getText()));
                nuevo.setEmail(String.valueOf(email.getText()));
                nuevo.setNombreCompleto(String.valueOf(nombreCompleto.getText()));
                db.usuarioDAO().insert(nuevo);
                finish();

            }
        });
    }

    public Dialog mostrarDialog() {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Error al crear el usuario")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // FIRE ZE MISSILES!
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        db.close();
    }

    public void inicializarElementos(){
        btnGuardar = findViewById(R.id.btnNuevoRoom);
        nombre = findViewById(R.id.etNewNombreRoom);
        password = findViewById(R.id.etNewPassRoom);
        nombreCompleto = findViewById(R.id.etNewNombreCompletoRoom);
        email = findViewById(R.id.etNewEmailRoom);
    }
}
