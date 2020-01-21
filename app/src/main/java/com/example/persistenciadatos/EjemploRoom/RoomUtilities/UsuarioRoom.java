package com.example.persistenciadatos.EjemploRoom.RoomUtilities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class UsuarioRoom {
    @PrimaryKey
    private int id;
    @ColumnInfo(name = "nombre")
    private String nombre;
    @ColumnInfo(name = "nombre_completo")
    private String nombreCompleto;
    @ColumnInfo(name = "password")
    private String password;
    @ColumnInfo(name = "email")
    private String email;
    @ColumnInfo(name = "telefono")
    private String telefono;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }



    public void set(int p_id, String p_name,
                    String p_last_name, String p_password, String p_email, String p_telefono){
        id=p_id;
        nombre=p_name;
        nombreCompleto=p_last_name;
        password = p_password;
        email = p_email;
        telefono = p_telefono;
    }
}
