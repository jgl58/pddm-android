package com.example.persistenciadatos.EjemploRoom.RoomUtilities;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Entity;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UsuarioDAO {
    @Query("SELECT * FROM UsuarioRoom")
    List<UsuarioRoom> loadAll();
    @Query("SELECT * FROM UsuarioRoom WHERE id IN (:userIds)")
    List<UsuarioRoom> loadAllByUserId(int... userIds);
    @Query("SELECT * FROM UsuarioRoom where nombre LIKE :nombre AND password LIKE :contraseña LIMIT 1")
    UsuarioRoom checkLogin(String nombre, String contraseña);
    @Insert
    void insertAll(UsuarioRoom... users);
    @Update
    public void update(UsuarioRoom user);
    @Insert
    void insert(UsuarioRoom user);
    @Delete
    void delete(UsuarioRoom user);
}
