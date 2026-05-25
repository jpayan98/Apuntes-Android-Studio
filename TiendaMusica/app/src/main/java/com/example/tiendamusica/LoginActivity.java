package com.example.tiendamusica;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    EditText etUsuario, etPassword;
    Button btnEntrar, btnRegistrar;
    TextView tvAlbum;

    CrearBD bd;

    // Datos del álbum recibidos por Intent
    String titulo, artista, descripcion;
    int imagen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsuario  = findViewById(R.id.etUsuario);
        etPassword = findViewById(R.id.etPassword);
        btnEntrar  = findViewById(R.id.btnEntrar);
        btnRegistrar = findViewById(R.id.btnRegistrar);
        tvAlbum    = findViewById(R.id.tvAlbum);

        bd = new CrearBD(this);

        // Recoger datos del Intent
        titulo      = getIntent().getStringExtra("titulo");
        artista     = getIntent().getStringExtra("artista");
        descripcion = getIntent().getStringExtra("descripcion");
        imagen      = getIntent().getIntExtra("imagen", 0);

        tvAlbum.setText("Para ver: " + titulo);

        btnEntrar.setOnClickListener(v -> entrar());
        btnRegistrar.setOnClickListener(v -> registrar());
    }

    private void entrar() {
        String usuario  = etUsuario.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        if (usuario.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Rellena todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        if (bd.comprobarLogin(usuario, password)) {
            Toast.makeText(this, "Bienvenido, " + usuario, Toast.LENGTH_SHORT).show();
            irADetalle();
        } else {
            Toast.makeText(this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();
        }
    }

    private void registrar() {
        String usuario  = etUsuario.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        if (usuario.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Rellena todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        if (bd.registrar(usuario, password)) {
            Toast.makeText(this, "Registro exitoso. Bienvenido, " + usuario, Toast.LENGTH_SHORT).show();
            irADetalle();
        } else {
            Toast.makeText(this, "Ese usuario ya existe. Prueba a entrar.", Toast.LENGTH_SHORT).show();
        }
    }

    private void irADetalle() {
        Intent intent = new Intent(this, DetalleActivity.class);
        intent.putExtra("titulo", titulo);
        intent.putExtra("artista", artista);
        intent.putExtra("descripcion", descripcion);
        intent.putExtra("imagen", imagen);
        startActivity(intent);
    }
}
