package com.example.persistenciadatos.EjemploRoom.RoomUtilities;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {UsuarioRoom.class}, version = 1)
public abstract class DatabaseRoom extends RoomDatabase {
    public abstract UsuarioDAO usuarioDAO();
}
