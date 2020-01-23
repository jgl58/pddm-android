package com.example.persistenciadatos.EjemploDBHelper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.persistenciadatos.EjemploDBHelper.SQLiteHelper.SQliteHelper;
import com.example.persistenciadatos.R;

import static com.example.persistenciadatos.EjemploDBHelper.BackupUtlilty.BackupUtility.DB_VERSION;

public class ActualizarActivity extends AppCompatActivity {
    SQliteHelper dbHelper;
    SQLiteDatabase db;
    Button btnGuardar;
    EditText nombre, password, nombreCompleto, email, telefono;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar);
        inicializarElementos();

        dbHelper = new SQliteHelper(this,"DBUsuarios",null,DB_VERSION);
        db = dbHelper.getWritableDatabase();


        Cursor result = dbHelper.getUsuario(String.valueOf(getIntent().getIntExtra("ID",1)),db);
        if(result.moveToFirst()){
            do{
                nombre.setText(result.getString(1));
                password.setText(result.getString(2));
                nombreCompleto.setText(result.getString(3));
                email.setText(result.getString(4));
                telefono.setText(result.getString(5));
            }while (result.moveToNext());

        }

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues values = new ContentValues();
                values.put("nombre_usuario", String.valueOf(nombre.getText()));
                values.put("nombre_completo", String.valueOf(nombreCompleto.getText()));
                values.put("password", String.valueOf(password.getText()));
                values.put("email", String.valueOf(email.getText()));
                values.put("telefono", String.valueOf(telefono.getText()));
                dbHelper.actualizarUsuario(values, String.valueOf(getIntent().getIntExtra("ID",1)),db);
                finish();
            }
        });


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
        telefono = findViewById(R.id.etTelefono);
    }
}
