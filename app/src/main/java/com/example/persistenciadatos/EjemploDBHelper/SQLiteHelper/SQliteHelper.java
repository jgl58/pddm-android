package com.example.persistenciadatos.EjemploDBHelper.SQLiteHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class SQliteHelper extends SQLiteOpenHelper {

    String createTable = "CREATE TABLE Usuarios " +
            "(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, nombre_usuario TEXT UNIQUE, password TEXT, nombre_completo TEXT, email TEXT)";

    String createUser = "INSERT INTO Usuarios (id,nombre_usuario, password, nombre_completo, email) VALUES (1,'admin','admin','Administrador','admin@admin.es')";


    public SQliteHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createTable);
        db.execSQL(createUser);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d("Debug","Upgrade");
        Log.d("Debug", String.valueOf(oldVersion));
        Log.d("Debug", String.valueOf(newVersion));

        if(oldVersion < 2){
            String createTlf = "ALTER TABLE Usuarios ADD telefono TEXT";
            db.execSQL(createTlf);
        }
    }

    //Cambiar a que devuelva un cursor
    public Cursor consultarUsuario(String nombre, String password, SQLiteDatabase db){

        String[] columnas = new String[] {"nombre_usuario","password"};
        String[] args = new String[]{nombre,password};

        Cursor result = db.query("UsuarioDAO",columnas,"nombre_usuario=? and password=?",args,null,null,null);
        return result;
    }

    public Cursor getUsuario(String id, SQLiteDatabase db){
        String[] args = new String[]{id};

        //Cursor result = db.query("UsuarioDAO",columnas,"id=?",args,null,null,null);
        Cursor result = db.rawQuery("SELECT * FROM Usuarios WHERE id=?",args);
        return result;
    }

    public void deleteUsuario(String id, SQLiteDatabase db){
        String[] args = new String[]{id};

        db.delete("UsuarioDAO","id=?",args);
    }

    public Cursor consultarUsuarios(SQLiteDatabase db){

        //Cursor result = db.query("UsuarioDAO",columnas,"nombre_usuario=? and password=?",args,null,null,null);
        Cursor result = db.rawQuery("SELECT * FROM Usuarios",null);

        return result;
    }

    public void actualizarUsuario(ContentValues values, String id, SQLiteDatabase db){
        String[] args = new String[]{id};
        db.update("UsuarioDAO",values,"id=?",args);
    }

    public Boolean crearUsuario(ContentValues values, SQLiteDatabase db){
        try {
            db.insertOrThrow("UsuarioDAO", null, values);
            return true;

        }catch (Exception e){
            Log.d("Debug", "No se ha podido crear el usuario");
            return false;
        }
    }
}
