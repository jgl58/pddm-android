package com.example.persistenciadatos.EjemploDBHelper;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.example.persistenciadatos.EjemploDBHelper.SQLiteHelper.SQliteHelper;
import com.example.persistenciadatos.R;

public class ActualizarActivity extends AppCompatActivity {
    SQliteHelper dbHelper;
    SQLiteDatabase db;
    Button btnGuardar;
    EditText nombre, password, nombreCompleto, email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar);
        inicializarElementos();

        dbHelper = new SQliteHelper(this,"DBUsuarios",null,1);
        db = dbHelper.getWritableDatabase();


        Cursor result = dbHelper.getUsuario(getIntent().getStringExtra("ID"),db);
        if(result.moveToFirst()){
            do{
                nombre.setText(result.getString(1));
                password.setText(result.getString(2));
                nombreCompleto.setText(result.getString(3));
                email.setText(result.getString(4));
            }while (result.moveToNext());
        }

        db.close();
    }

    public void inicializarElementos(){
        btnGuardar = findViewById(R.id.btnGuardarUpdate);
        nombre = findViewById(R.id.etNombreUser);
        password = findViewById(R.id.etPassUser);
        nombreCompleto = findViewById(R.id.etNombreCompletoUser);
        email = findViewById(R.id.etEmailUser);
    }
}
