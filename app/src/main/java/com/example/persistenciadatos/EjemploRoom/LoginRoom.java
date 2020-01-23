package com.example.persistenciadatos.EjemploRoom;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.persistenciadatos.EjemploRoom.RoomUtilities.DatabaseRoom;
import com.example.persistenciadatos.EjemploRoom.RoomUtilities.UsuarioRoom;
import com.example.persistenciadatos.R;

public class LoginRoom extends AppCompatActivity {
    EditText nombre, password;
    Button login;
    DatabaseRoom db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_room);

        nombre = findViewById(R.id.etNombreRoom);
        password = findViewById(R.id.etPasswordRoom);
        login = findViewById(R.id.btnLoginRoom);

        db = DatabaseRoom.getInstance(getApplicationContext());

        /*db = Room.databaseBuilder(getApplicationContext(),
                DatabaseRoom.class,
                "DBUsuariosRoom").allowMainThreadQueries().build();*/


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               UsuarioRoom user = db.usuarioDAO().checkLogin(nombre.getText().toString(),password.getText().toString());

               if(user != null){
                   Log.d("Debug","Login correcto");
                   startActivity(new Intent(getApplicationContext(),GestionRoom.class));
               }else{
                   Log.d("Debug","Login incorrecto");
               }

            }
        });
    }
}
