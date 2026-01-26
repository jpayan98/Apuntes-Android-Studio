package com.example.examen2dejuanpayan;


import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView lista;
    String[] titulos;
    String[] autores;
    int[] imagenes = {
            R.drawable.libro1,
            R.drawable.libro2,
            R.drawable.libro3,
    };
    ImageButton imgbtn;
    EditText etUsuario, etContrasena;
    Button btnAceptar;
    dbHelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

           imgbtn = findViewById(R.id.botonImagen);
           etUsuario=findViewById(R.id.editTextUsuario);
           etContrasena=findViewById(R.id.editTextContrasena);
           btnAceptar=findViewById(R.id.btn_aceptar);



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        imgbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verLibros();
            }
        });

    }
    public void comprobarUsuario(dbHelper db){
        String usuario= etUsuario.getText().toString();
        String contrasena = etContrasena.getText().toString();
        if (usuario.isEmpty() || contrasena.isEmpty()){
            mostrarToastFallo("Debe rellenar todos los campos");
        }
    }
    public void mostrarToastFallo(String mensaje){
        Toast.makeText(this,mensaje,Toast.LENGTH_LONG).show();
    }

    public void verLibros(){
        ListaAdapter adapter = new ListaAdapter(this, titulos, imagenes, autores);
        lista.setAdapter(adapter);
    }

}