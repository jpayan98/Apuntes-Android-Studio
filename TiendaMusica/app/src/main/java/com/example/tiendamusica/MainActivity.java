package com.example.tiendamusica;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    String[] titulos;
    String[] artistas;
    String[] descripciones;

    // Pon aquí tus imágenes en drawable con estos nombres: album1, album2, album3
    int[] imagenes = {
            R.drawable.album1,
            R.drawable.album2,
            R.drawable.album3
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Resources res = getResources();
        titulos      = res.getStringArray(R.array.titulos);
        artistas     = res.getStringArray(R.array.artistas);
        descripciones = res.getStringArray(R.array.descripciones);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        AlbumAdapter adapter = new AlbumAdapter(
                this, titulos, artistas, imagenes,
                position -> {
                    // Al pulsar un álbum, mandamos sus datos a LoginActivity
                    Intent intent = new Intent(this, LoginActivity.class);
                    intent.putExtra("titulo", titulos[position]);
                    intent.putExtra("artista", artistas[position]);
                    intent.putExtra("descripcion", descripciones[position]);
                    intent.putExtra("imagen", imagenes[position]);
                    startActivity(intent);
                }
        );
        recyclerView.setAdapter(adapter);
    }
}
