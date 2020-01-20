package com.example.persistenciadatos.EjemploDBHelper;

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
import com.example.persistenciadatos.R;

public class NuevoUsuario extends AppCompatActivity {

    SQliteHelper dbHelper;
    SQLiteDatabase db;
    Button btnGuardar;
    EditText nombre, password, nombreCompleto, email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_usuario);
        inicializarElementos();
        dbHelper = new SQliteHelper(this,"DBUsuarios",null,1);
        db = dbHelper.getWritableDatabase();

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues values = new ContentValues();
                values.put("nombre_usuario", String.valueOf(nombre.getText()));
                values.put("nombre_completo", String.valueOf(nombreCompleto.getText()));
                values.put("password", String.valueOf(password.getText()));
                values.put("email", String.valueOf(email.getText()));
                if(dbHelper.crearUsuario(values,db)){
                    finish();
                }else{
                    mostrarDialog().show();
                }

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
        btnGuardar = findViewById(R.id.btnNuevo);
        nombre = findViewById(R.id.etNewNombre);
        password = findViewById(R.id.etNewPass);
        nombreCompleto = findViewById(R.id.etNewNombreCompleto);
        email = findViewById(R.id.etNewEmail);
    }
}
