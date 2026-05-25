package com.example.tiendamusica;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetalleActivity extends AppCompatActivity {

    ImageView imgAlbum;
    TextView tvTitulo, tvArtista, tvDescripcion;
    Button btnVolver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);

        imgAlbum      = findViewById(R.id.imgDetalle);
        tvTitulo      = findViewById(R.id.tvDetalleTitulo);
        tvArtista     = findViewById(R.id.tvDetalleArtista);
        tvDescripcion = findViewById(R.id.tvDetalleDescripcion);
        btnVolver     = findViewById(R.id.btnVolver);

        // Recoger datos del Intent
        String titulo      = getIntent().getStringExtra("titulo");
        String artista     = getIntent().getStringExtra("artista");
        String descripcion = getIntent().getStringExtra("descripcion");
        int imagen         = getIntent().getIntExtra("imagen", 0);

        tvTitulo.setText(titulo);
        tvArtista.setText(artista);
        tvDescripcion.setText(descripcion);
        if (imagen != 0) imgAlbum.setImageResource(imagen);

        // finish() cierra esta activity y vuelve a la anterior
        btnVolver.setOnClickListener(v -> finish());
    }
}
