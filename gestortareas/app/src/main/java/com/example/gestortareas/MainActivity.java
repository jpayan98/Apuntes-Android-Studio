package com.example.gestortareas;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements TareaAdapter.OnTareaListener {

    private EditText editTextTarea;
    private Button btnAnadir;
    private ListView listViewTareas;
    private TextView tvContador;

    private List<Tarea> listaTareas;
    private TareaAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Vincular vistas
        editTextTarea = findViewById(R.id.editTextTarea);
        btnAnadir = findViewById(R.id.btnAnadir);
        listViewTareas = findViewById(R.id.listViewTareas);
        tvContador = findViewById(R.id.tvContador);

        // Inicializar lista y adaptador
        listaTareas = new ArrayList<>();
        adapter = new TareaAdapter(this, listaTareas, this);
        listViewTareas.setAdapter(adapter);

        // Evento botón añadir
        btnAnadir.setOnClickListener(v -> añadirTarea());

        actualizarContador();
    }

    private void añadirTarea() {
        String nombre = editTextTarea.getText().toString().trim();

        if (nombre.isEmpty()) {
            Toast.makeText(this, "⚠️ Escribe una tarea primero", Toast.LENGTH_SHORT).show();
            return;
        }

        listaTareas.add(new Tarea(nombre));
        adapter.notifyDataSetChanged();
        editTextTarea.setText(""); // Limpiar campo
        actualizarContador();
    }

    @Override
    public void onEliminar(int posicion) {
        listaTareas.remove(posicion);
        adapter.notifyDataSetChanged();
        actualizarContador();
        Toast.makeText(this, "Tarea eliminada", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCompletar(int posicion, boolean completada) {
        listaTareas.get(posicion).setCompletada(completada);
        adapter.notifyDataSetChanged();
        actualizarContador();
    }

    private void actualizarContador() {
        int pendientes = 0;
        for (Tarea t : listaTareas) {
            if (!t.isCompletada()) pendientes++;
        }
        tvContador.setText("Pendientes: " + pendientes);
    }
}