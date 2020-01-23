package com.example.persistenciadatos.EjemploDBHelper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.persistenciadatos.EjemploDBHelper.SQLiteHelper.SQliteHelper;
import com.example.persistenciadatos.R;

import java.io.File;
import java.io.IOException;

import static com.example.persistenciadatos.EjemploDBHelper.BackupUtlilty.BackupUtility.DB_VERSION;
import static com.example.persistenciadatos.EjemploDBHelper.BackupUtlilty.BackupUtility.exportFile;
import static com.example.persistenciadatos.EjemploDBHelper.BackupUtlilty.BackupUtility.importFile;

public class DatosUsuario extends AppCompatActivity {
    SQliteHelper dbHelper;
    SQLiteDatabase db;
    TextView nombre, email, saludo;
    Button btnVolver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_usuario);

        nombre = findViewById(R.id.tvNombre);
        email = findViewById(R.id.tvEmail);
        saludo = findViewById(R.id.tvSaludo);
        btnVolver = findViewById(R.id.btnVolver);

        dbHelper = new SQliteHelper(this,"DBUsuarios",null,DB_VERSION);
        db = dbHelper.getWritableDatabase();
        Cursor result = dbHelper.getUsuario(String.valueOf(getIntent().getIntExtra("ID",1)),db);
        if(result.moveToFirst()){
            do{
                nombre.setText(result.getString(3));
                saludo.setText("Bienvenido "+result.getString(1));
                email.setText(result.getString(4));
            }while (result.moveToNext());
        }

        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
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
            case R.id.gestion:
                startActivity(new Intent(getApplicationContext(),GestionUsuarios.class));
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
                exportFile(src, dst,"DBUsuarios");
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

}
