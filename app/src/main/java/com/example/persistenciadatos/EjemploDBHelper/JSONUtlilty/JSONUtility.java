package com.example.persistenciadatos.EjemploDBHelper.JSONUtlilty;

import android.content.Context;
import android.database.Cursor;
import android.util.JsonWriter;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONStringer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import static com.example.persistenciadatos.Encriptacion.decode;
import static com.example.persistenciadatos.Encriptacion.encode;

public class JSONUtility {

    public static void crearJSONBackup(String cadena, Context context){
        File ruta = context.getFilesDir();

        File f = new File(ruta.getAbsolutePath(),"backup.json");
        try {
            OutputStreamWriter fout = new OutputStreamWriter(
                    new FileOutputStream(f,true)
            );
            fout.write(cadena);//falta encode
            fout.close();
        }catch (Exception ex){
            Log.e("Debug","Error al escribir el fichero interno");
        }
    }

    public static JSONArray leerJSONBackup(Context context){
        JSONArray usuarios = null;
        String cadena = "";
        File ruta = context.getFilesDir();
        File f = new File(ruta.getAbsolutePath(),"backup.json");
        try {

            BufferedReader fin = new BufferedReader(
                    new InputStreamReader(new FileInputStream(f)));
            String line = fin.readLine();

            while (line != null) {
                cadena += line;
                line = fin.readLine();
            }
            fin.close();
            usuarios = new JSONArray(cadena);
        }catch (Exception ex){
            Log.e("Debug","Error al leer el fichero interno");
        }
        return usuarios;
    }
}
