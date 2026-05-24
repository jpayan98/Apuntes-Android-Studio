package com.example.catalogopeliculas;

import android.content.res.Resources;
import android.os.Bundle;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CatalogoPeliculas extends AppCompatActivity {

    ListView lista;
    String[] titulos;
    String[] directores;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_catalogo_peliculas);
        Resources res = getResources();
        titulos = res.getStringArray(R.array.titulos);
        directores=res.getStringArray(R.array.directores);
        ItemsCatalogo adapter = new ItemsCatalogo(this,titulos,directores);
        lista = findViewById(R.id.ListView);
        lista.setAdapter(adapter);


    }
}