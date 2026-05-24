package com.example.catalogopeliculas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    ImageButton imgbtn;
    EditText etnombre,etcontrasena;
    Button btn;

    dbHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

            imgbtn = findViewById(R.id.imageButton2);
            etnombre = findViewById(R.id.editTextNombre);
            etcontrasena = findViewById(R.id.editTextContraseña);
            btn = findViewById(R.id.button);
            imgbtn.setEnabled(false);

            db = new dbHelper(this);

            ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets)->{
                Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                v.setPadding(systemBars.left,systemBars.top,systemBars.right,systemBars.bottom);
                return insets;
            });

            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (db.comprobarLogin(etnombre.getText().toString(),etcontrasena.getText().toString())){
                        mostrarToast("Bienvenido");
                        imgbtn.setEnabled(true);
                    }
                    else{
                        if(etnombre.getText().toString().isEmpty() || etcontrasena.getText().toString().isEmpty()){
                            mostrarToast("Rellena todos los campos");

                        }
                       else{
                           mostrarToast("Datos incorrectos o incompletos");
                        }
                    }
                }
            });
            imgbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    verCatalogo();
                }
            });
    }

    private void mostrarToast(String mensaje){
        Toast.makeText(this,mensaje,Toast.LENGTH_SHORT).show();
    }
    private void verCatalogo(){
        startActivity(new Intent(this,CatalogoPeliculas.class));
    }

}