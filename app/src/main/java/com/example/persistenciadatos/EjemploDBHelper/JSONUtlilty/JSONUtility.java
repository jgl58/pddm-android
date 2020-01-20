package com.example.persistenciadatos.EjemploDBHelper.JSONUtlilty;

import android.database.Cursor;
import android.util.JsonWriter;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class JSONUtility {

    public static void writeJsonStream(OutputStream out, Cursor usuarios) throws IOException {
        JsonWriter writer = new JsonWriter(new OutputStreamWriter(out));
        writer.setIndent("  ");
        writeUsersArray(writer, usuarios);
        writer.close();
    }

    public static void writeUsersArray(JsonWriter writer, Cursor usuarios) throws IOException {
        writer.beginArray();
        if(usuarios.moveToFirst()){
            do{
                List<String> usuario = new ArrayList<>();
                usuario.add(usuarios.getString(0));
                usuario.add(usuarios.getString(1));
                usuario.add(usuarios.getString(2));
                usuario.add(usuarios.getString(3));
                usuario.add(usuarios.getString(4));

                writeUser(writer,usuario);
            }while (usuarios.moveToNext());

        }
        writer.endArray();
    }

    public static void writeUser(JsonWriter writer, List<String> user) throws IOException {
        writer.beginObject();
        writer.name("id").value(user.get(0));
        writer.name("nombre_usuario").value(user.get(1));
        writer.name("nombre_completo").value(user.get(2));
        writer.name("password").value(user.get(3));
        writer.name("email").value(user.get(4));
        writer.endObject();
    }

  /*  public void writeJsonStream(OutputStream out, List<Message> messages) throws IOException {
        JsonWriter writer = new JsonWriter(new OutputStreamWriter(out, "UTF-8"));
        writer.setIndent("  ");
        writeMessagesArray(writer, messages);
        writer.close();
    }

    public void writeMessagesArray(JsonWriter writer, List<Message> messages) throws IOException {
        writer.beginArray();
        for (Message message : messages) {
            writeMessage(writer, message);
        }
        writer.endArray();
    }

    public void writeMessage(JsonWriter writer, Message message) throws IOException {
        writer.beginObject();
        writer.name("id").value(message.getId());
        writer.name("text").value(message.getText());
        if (message.getGeo() != null) {
            writer.name("geo");
            writeDoublesArray(writer, message.getGeo());
        } else {
            writer.name("geo").nullValue();
        }
        writer.name("user");
        writeUser(writer, message.getUser());
        writer.endObject();
    }

    public void writeUser(JsonWriter writer, User user) throws IOException {
        writer.beginObject();
        writer.name("name").value(user.getName());
        writer.name("followers_count").value(user.getFollowersCount());
        writer.endObject();
    }

    public void writeDoublesArray(JsonWriter writer, List<Double> doubles) throws IOException {
        writer.beginArray();
        for (Double value : doubles) {
            writer.value(value);
        }
        writer.endArray();
    }*/
}
