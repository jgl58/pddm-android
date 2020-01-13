package com.example.persistenciadatos.EjemploFicheros;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import static com.example.persistenciadatos.Encriptacion.decode;
import static com.example.persistenciadatos.Encriptacion.encode;

public class FilesAccess {

    public static String FILENAME = "prueba.txt";


    public static void copiarAInterna(Context context){

        String dir = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
            dir = context.getFilesDir().getAbsolutePath();
        }
        File f0 = new File(dir, FILENAME);
        f0.delete();
        escribirFicheroInterna(leerFicheroExterna(context),context);

    }

    public static void copiarAExterna(Context context){

        String dir = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
            dir = context.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS).getAbsolutePath();
        }
        File f0 = new File(dir, FILENAME);
        f0.delete();
        escribirFicheroExterna(leerFicheroInterna(context),context);

    }
    public static String leerFicheroInterna(Context context){
        String cadena = "";
        File ruta = context.getFilesDir();
        File f = new File(ruta.getAbsolutePath(),FILENAME);
        try {

            BufferedReader fin = new BufferedReader(
                    new InputStreamReader(new FileInputStream(f)));
            String line = fin.readLine();

            while (line != null) {
                cadena += "\n" + decode(line);
                line = fin.readLine();
            }
            fin.close();
        }catch (Exception ex){
            Log.e("Debug","Error al leer el fichero interno");
        }
        return cadena;
    }

    public static String leerFicheroExterna(Context context){
        String cadena = "";
        try {
            File ruta_sd = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {

                ruta_sd = context.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS);
            }

            File f = new File(ruta_sd.getAbsolutePath(),FILENAME);

            BufferedReader fin = new BufferedReader(
                    new InputStreamReader(new FileInputStream(f))
            );

            String line = fin.readLine();

            while (line != null) {
                cadena += "\n" + decode(line);
                line = fin.readLine();
            }
            fin.close();
        }catch (Exception ex){
            Log.e("Debug","Error al leer el fichero externo");
        }
        return cadena;
    }


    public static void escribirFicheroInterna(String cadena,Context context){
        File ruta = context.getFilesDir();

        File f = new File(ruta.getAbsolutePath(),FILENAME);
        try {
            OutputStreamWriter fout = new OutputStreamWriter(
                    new FileOutputStream(f,true)
            );
            fout.write(encode(cadena));
            fout.close();
        }catch (Exception ex){
            Log.e("Debug","Error al escribir el fichero interno");
        }
    }

    public static void escribirFicheroExterna(String cadena, Context context){

        try {
            File ruta_sd = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
                ruta_sd = context.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS);
            }

            File f = new File(ruta_sd,FILENAME);

            OutputStreamWriter fout = new OutputStreamWriter(
                    new FileOutputStream(f,true)
            );
            fout.write(encode(cadena));
            fout.close();
        }catch (Exception ex){
            Log.e("Debug","Error al escribir el fichero SD");
        }
    }
}
