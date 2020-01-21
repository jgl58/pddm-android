package com.example.persistenciadatos.EjemploContentProviders;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.persistenciadatos.EjemploDBHelper.SQLiteHelper.SQliteHelper;
import static com.example.persistenciadatos.EjemploDBHelper.BackupUtlilty.BackupUtility.DB_VERSION;

public class UsuarioProvider extends ContentProvider {

    SQliteHelper dbHelper;

    private static final int USUARIOS = 1;
    private static final int USUARIOS_ID = 2;
    private static final UriMatcher uriMatcher;

    public static String PACKAGE = "com.example.persistenciadatos";

    public static final String uri = "content://"+PACKAGE+"/usuarios";
    public static final Uri CONTENT_URI = Uri.parse(uri);

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(PACKAGE,"usuarios",USUARIOS);
        uriMatcher.addURI(PACKAGE,"usuarios/#",USUARIOS_ID);
    }


    @Override
    public boolean onCreate() {

        dbHelper = new SQliteHelper(getContext(),"DBUsuarios",null,DB_VERSION);

        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {

        Log.d("Debug","Query");
        Cursor c = null;
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        switch (uriMatcher.match(uri)){
            case USUARIOS:
                c = dbHelper.consultarUsuarios(db);
                break;

            case USUARIOS_ID:
                c = dbHelper.getUsuario(uri.getLastPathSegment(),db);
                break;
        }

        return c;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }
}
