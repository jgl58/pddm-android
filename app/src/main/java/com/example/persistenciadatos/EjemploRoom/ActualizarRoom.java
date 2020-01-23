package com.example.persistenciadatos.EjemploRoom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.persistenciadatos.EjemploContentProviders.Usuario;
import com.example.persistenciadatos.EjemploDBHelper.SQLiteHelper.SQliteHelper;
import com.example.persistenciadatos.EjemploRoom.RoomUtilities.DatabaseRoom;
import com.example.persistenciadatos.EjemploRoom.RoomUtilities.UsuarioRoom;
import com.example.persistenciadatos.R;

import java.util.List;

public class ActualizarRoom extends AppCompatActivity {

    DatabaseRoom db;
    Button btnGuardar;
    EditText nombre, password, nombreCompleto, email, telefono;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar_room);

        inicializarElementos();
        db = DatabaseRoom.getInstance(getApplicationContext());

        final List<UsuarioRoom> result = db.usuarioDAO().loadAllByUserId(getIntent().getIntExtra("ID",1));

        nombre.setText(result.get(0).getNombre());
        password.setText(result.get(0).getPassword());
        nombreCompleto.setText(result.get(0).getNombreCompleto());
        email.setText(result.get(0).getEmail());

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UsuarioRoom nuevo = new UsuarioRoom();
                nuevo.setNombre(String.valueOf(nombre.getText()));
                nuevo.setPassword(String.valueOf(password.getText()));
                nuevo.setEmail(String.valueOf(email.getText()));
                nuevo.setNombreCompleto(String.valueOf(nombreCompleto.getText()));
                nuevo.setId(result.get(0).getId());
                db.usuarioDAO().update(nuevo);
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
        btnGuardar = findViewById(R.id.btnGuardarRoom);
        nombre = findViewById(R.id.etNewNombreRoom);
        password = findViewById(R.id.etNewPassRoom);
        nombreCompleto = findViewById(R.id.etNewNombreCompletoRoom);
        email = findViewById(R.id.etNewEmailRoom);
    }
}
