package com.example.persistenciadatos.EjemploRoom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.persistenciadatos.EjemploDBHelper.GestionUsuarios;
import com.example.persistenciadatos.EjemploRoom.RoomUtilities.DatabaseRoom;
import com.example.persistenciadatos.EjemploRoom.RoomUtilities.UsuarioRoom;
import com.example.persistenciadatos.R;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static com.example.persistenciadatos.EjemploDBHelper.BackupUtlilty.BackupUtility.exportFile;
import static com.example.persistenciadatos.EjemploDBHelper.BackupUtlilty.BackupUtility.importFile;

public class DatosUsuarioRoom extends AppCompatActivity {
    TextView nombre, email, saludo;
    Button btnVolver;
    DatabaseRoom db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_usuario_room);

        db = DatabaseRoom.getInstance(getApplicationContext());
        nombre = findViewById(R.id.tvNombre);
        email = findViewById(R.id.tvEmail);
        saludo = findViewById(R.id.tvSaludo);
        btnVolver = findViewById(R.id.btnVolver);

        final List<UsuarioRoom> result = db.usuarioDAO().loadAllByUserId(getIntent().getIntExtra("ID",1));

        nombre.setText(result.get(0).getNombreCompleto());
        saludo.setText("Bienvenido "+result.get(0).getNombre());
        email.setText(result.get(0).getEmail());

        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.backup_settings, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.crear_backup:
                crearBackup();
                return true;
            case R.id.restaurar_backup:
                leerBackup();
                return true;
            case R.id.gestion:
                startActivity(new Intent(getApplicationContext(), GestionUsuarios.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void crearBackup(){

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
            File src = new File(getDatabasePath("DBUsuariosRoom").getAbsolutePath());

            String dstPath = getApplicationContext().getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS) + File.separator + "backup" + File.separator;

            File dst = new File(dstPath);

            try {
                exportFile(src, dst,"DBUsuariosRoom");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void leerBackup(){

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            File dst = new File(getDatabasePath("DBUsuariosRoom").getAbsolutePath());

            String srcPath = getApplicationContext().getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS) + File.separator + "backup" + File.separator+"DBUsuariosRoom";


            File src = new File(srcPath);

            try {
                importFile(src, dst);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
