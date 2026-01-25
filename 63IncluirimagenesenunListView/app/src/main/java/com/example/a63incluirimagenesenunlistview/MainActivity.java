package com.example.a63incluirimagenesenunlistview;


import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    ListView lista;
    String[] titulos;
    String[] descripciones;

    // Array de imágenes (ajusta los nombres según tus archivos)
    int[] imagenes = {
            R.drawable.hommer,
            R.drawable.marge,
            R.drawable.bart,
            R.drawable.lisa,
            R.drawable.magie
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Obtener los arrays de strings desde resources
        Resources res = getResources();
        titulos = res.getStringArray(R.array.titulo);
        descripciones = res.getStringArray(R.array.descripcion);

        // Configurar el ListView
        lista = findViewById(R.id.lista);
        ListaAdapter adapter = new ListaAdapter(this, titulos, imagenes, descripciones);
        lista.setAdapter(adapter);

        // Programar el evento de clic en cada item
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String personaje = titulos[position];
                Toast.makeText(MainActivity.this, "Has pulsado: " + personaje, Toast.LENGTH_SHORT).show();
            }
        });
    }
}