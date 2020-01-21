package com.example.persistenciadatos.EjemploRoom;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.persistenciadatos.R;

public class LoginRoom extends AppCompatActivity {
    EditText nombre, password;
    Button login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_room);

        nombre = findViewById(R.id.etNombreRoom);
        password = findViewById(R.id.etPasswordRoom);
        login = findViewById(R.id.btnLoginRoom);
    }
}
