package com.example.persistenciadatos.EjemploDBHelper;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.persistenciadatos.EjemploDBHelper.JSONUtlilty.JSONUtility;
import com.example.persistenciadatos.EjemploDBHelper.SQLiteHelper.SQliteHelper;
import com.example.persistenciadatos.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import static com.example.persistenciadatos.EjemploDBHelper.JSONUtlilty.JSONUtility.crearJSONBackup;
import static com.example.persistenciadatos.EjemploDBHelper.JSONUtlilty.JSONUtility.leerJSONBackup;
import static com.example.persistenciadatos.Encriptacion.encode;
import static java.lang.System.out;

public class LoginUsuarios extends AppCompatActivity {

    EditText nombre, password;
    Button login;
    SQliteHelper dbHelper;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_usuarios);

        nombre = findViewById(R.id.etNombre);
        password = findViewById(R.id.etPassword);
        login = findViewById(R.id.btnLogin);

        dbHelper = new SQliteHelper(this,"DBUsuarios",null,1);
        db = dbHelper.getWritableDatabase();


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Boolean login = dbHelper.consultarUsuario(nombre.getText().toString(),password.getText().toString(),db);

                if(!login){

                    mostrarDialog().show();
                }else{
                    startActivity(new Intent(getApplicationContext(),GestionUsuarios.class));
                }

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.backup_settings, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.crear_backup:
                crearBackup();
                return true;
            case R.id.restaurar_backup:
                leerBackup();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void crearBackup(){

        Cursor usuarios = dbHelper.consultarUsuarios(db);
        JSONArray listaUsuarios = new JSONArray();
        if(usuarios.moveToFirst()){
            do{
                try {
                JSONObject usuario = new JSONObject();
                usuario.put("id",usuarios.getString(0));
                usuario.put("nombre",usuarios.getString(1));
                usuario.put("nombre_completo",usuarios.getString(2));
                usuario.put("password",usuarios.getString(3));
                usuario.put("email",usuarios.getString(4));
                listaUsuarios.put(usuario);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }while (usuarios.moveToNext());

        }
        crearJSONBackup(listaUsuarios.toString(),getApplicationContext());

    }

    public void leerBackup(){
        
        JSONArray listaUsuarios = leerJSONBackup(getApplicationContext());
        Log.d("Debug",listaUsuarios.toString());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        db.close();
    }

    public Dialog mostrarDialog() {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Error. Usuario no encontrado")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // FIRE ZE MISSILES!
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }
}
