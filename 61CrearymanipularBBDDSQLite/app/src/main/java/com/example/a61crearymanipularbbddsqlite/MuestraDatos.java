package com.example.a61crearymanipularbbddsqlite;


import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class MuestraDatos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_muestra_datos);

        // Obtener el RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        // Configurar el LayoutManager
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Obtener la lista del Intent
        Intent intent = getIntent();
        ArrayList<String> lista = intent.getStringArrayListExtra("lista");

        // Crear y asignar el adaptador
        myAdapter adapter = new myAdapter(lista);
        recyclerView.setAdapter(adapter);
    }
}