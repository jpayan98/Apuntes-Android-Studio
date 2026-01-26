package com.example.appbibliotecajuanpayan;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ListarLibros extends AppCompatActivity {

    private RecyclerView recyclerView;
    private DataBaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_listar_libros);  // ← Cambiado el nombre del layout

        dbHelper = new DataBaseHelper(this);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<Libro> libros = dbHelper.listarTodos();

        MiAdaptador adaptador = new MiAdaptador(libros);
        recyclerView.setAdapter(adaptador);
    }
}