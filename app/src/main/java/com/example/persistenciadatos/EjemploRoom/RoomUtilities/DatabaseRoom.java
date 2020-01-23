package com.example.persistenciadatos.EjemploRoom.RoomUtilities;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.Executors;

@Database(entities = {UsuarioRoom.class}, version = 1)
public abstract class DatabaseRoom extends RoomDatabase {
    private static DatabaseRoom INSTANCE;

    public abstract UsuarioDAO usuarioDAO();

    public synchronized static DatabaseRoom getInstance(Context context) {
        if (INSTANCE == null) {
            Log.d("Debug","Cargando datos");
            INSTANCE = buildDatabase(context);
        }
        return INSTANCE;
    }

    private static DatabaseRoom buildDatabase(final Context context) {
        return Room.databaseBuilder(context,
                DatabaseRoom.class,
                "DBUsuariosRoom")
                .allowMainThreadQueries()
                .addCallback(new Callback() {
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
                        super.onCreate(db);
                        Executors.newSingleThreadScheduledExecutor().execute(new Runnable() {
                            @Override
                            public void run() {

                                UsuarioRoom admin = new UsuarioRoom();
                                admin.set(1, "admin", "Administrador", "admin", "admin@admin", "111222333");
                                getInstance(context).usuarioDAO().insert(admin);
                            }
                        });
                    }
                })
                .build();
    }
}
