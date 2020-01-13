package com.example.persistenciadatos.EjemploFicheros;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.CheckBox;

import com.example.persistenciadatos.R;

public class ExternalStorage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_external_storage);

        findViewById(R.id.btnVolverStorage).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        String estado = Environment.getExternalStorageState();

        if(estado.equals(Environment.MEDIA_MOUNTED)){
           CheckBox checkBox = findViewById(R.id.md_mounted);
           checkBox.setChecked(true);
        } else if(estado.equals(Environment.MEDIA_UNKNOWN)){
            CheckBox checkBox = findViewById(R.id.md_unknown);
            checkBox.setChecked(true);
        } else if(estado.equals(Environment.MEDIA_REMOVED)){
            CheckBox checkBox = findViewById(R.id.md_removed);
            checkBox.setChecked(true);
        } else if(estado.equals(Environment.MEDIA_UNMOUNTED)){
            CheckBox checkBox = findViewById(R.id.md_unmounted);
            checkBox.setChecked(true);
        } else if(estado.equals(Environment.MEDIA_CHECKING)){
            CheckBox checkBox = findViewById(R.id.md_checking);
            checkBox.setChecked(true);
        } else if(estado.equals(Environment.MEDIA_NOFS)){
            CheckBox checkBox = findViewById(R.id.mf_nofs);
            checkBox.setChecked(true);
        } else if(estado.equals(Environment.MEDIA_MOUNTED_READ_ONLY)){
            CheckBox checkBox = findViewById(R.id.md_read_only);
            checkBox.setChecked(true);
        } else if(estado.equals(Environment.MEDIA_SHARED)){
            CheckBox checkBox = findViewById(R.id.md_shared);
            checkBox.setChecked(true);
        } else if(estado.equals(Environment.MEDIA_BAD_REMOVAL)){
            CheckBox checkBox = findViewById(R.id.md_bad_removal);
            checkBox.setChecked(true);
        } else if(estado.equals(Environment.MEDIA_UNMOUNTABLE)){
            CheckBox checkBox = findViewById(R.id.md_unmountable);
            checkBox.setChecked(true);
        }


    }
}
