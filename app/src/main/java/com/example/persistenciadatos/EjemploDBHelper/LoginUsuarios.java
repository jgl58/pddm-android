package com.example.persistenciadatos.EjemploDBHelper;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.persistenciadatos.EjemploContentProviders.Usuario;
import com.example.persistenciadatos.EjemploContentProviders.UsuarioProvider;
import com.example.persistenciadatos.EjemploDBHelper.SQLiteHelper.SQliteHelper;
import com.example.persistenciadatos.R;


import java.io.File;
import java.io.IOException;

import static com.example.persistenciadatos.EjemploDBHelper.BackupUtlilty.BackupUtility.DB_VERSION;
import static com.example.persistenciadatos.EjemploDBHelper.BackupUtlilty.BackupUtility.exportFile;
import static com.example.persistenciadatos.EjemploDBHelper.BackupUtlilty.BackupUtility.importFile;

public class LoginUsuarios extends AppCompatActivity {

    EditText nombre, password;
    Button login;
    SQliteHelper dbHelper;
    SQLiteDatabase db;
    UsuarioProvider provider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_usuarios);

        nombre = findViewById(R.id.etNombre);
        password = findViewById(R.id.etPassword);
        login = findViewById(R.id.btnLogin);

        dbHelper = new SQliteHelper(this,"DBUsuarios",null,DB_VERSION);
        db = dbHelper.getWritableDatabase();
        provider = new UsuarioProvider();


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Cursor login = dbHelper.consultarUsuario(nombre.getText().toString(),password.getText().toString(),db);

                if(!login.moveToFirst()){

                    mostrarDialog().show();
                }else{
                  //  startActivity(new Intent(getApplicationContext(),GestionUsuarios.class));
                    String [] projection = new String[]{
                            Usuario.COL_NOMBRE
                    };

                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {

                        Cursor result = getContentResolver().query(
                                provider.CONTENT_URI,
                                projection,
                                null,
                                null
                        );

                        if (result.moveToFirst()) {
                            do {
                                Log.d("Debug", result.getString(0));
                            } while (result.moveToNext());

                        }
                    }
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

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
            File src = new File(db.getPath());

            String dstPath = getApplicationContext().getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS) + File.separator + "backup" + File.separator;

            File dst = new File(dstPath);

            try {
                exportFile(src, dst);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void leerBackup(){

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            File dst = new File(db.getPath());

            String srcPath = getApplicationContext().getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS) + File.separator + "backup" + File.separator+"DBUsuarios";


            File src = new File(srcPath);

            try {
                importFile(src, dst);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
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
