package com.example.appbibliotecajuanpayan;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText isbn, titulo, autor;
    private CheckBox prestado;
    private ImageView portada;
    private Spinner genero;
    private Button btnconsultar, btninsertar, btnactualizar, btnborrar, btnlimpiar, btnvertodo;

    private DataBaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        dbHelper = new DataBaseHelper(this);

        // Inicializar vistas
        isbn = findViewById(R.id.editTextISBN);
        titulo = findViewById(R.id.editTextTitulo);
        autor = findViewById(R.id.editTextAutor);
        prestado = findViewById(R.id.checkBox);
        portada = findViewById(R.id.imageView);
        genero = findViewById(R.id.spinner);
        btnconsultar = findViewById(R.id.btnconsultar);
        btninsertar = findViewById(R.id.btninsertar);
        btnactualizar = findViewById(R.id.btnactualizar);
        btnborrar = findViewById(R.id.btnborrar);
        btnlimpiar = findViewById(R.id.btnlimpiar);
        btnvertodo = findViewById(R.id.btnvertodo);

        // Configurar Spinner con géneros
        String[] generos = {"Ficción", "No Ficción", "Ciencia", "Historia"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, generos);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genero.setAdapter(adapter);

        // Listeners
        btnconsultar.setOnClickListener(v -> consultarLibro());
        btninsertar.setOnClickListener(v -> insertarLibro());
        btnactualizar.setOnClickListener(v -> actualizarLibro());
        btnborrar.setOnClickListener(v -> borrarLibro());
        btnlimpiar.setOnClickListener(v -> limpiarCampos());
        btnvertodo.setOnClickListener(v -> verTodosLosLibros());
    }

    @Override
    protected void onResume() {
        super.onResume();
        limpiarCampos();
        mostrarToast("Datos refrescados");
    }

    private void consultarLibro() {
        String isbnString = isbn.getText().toString().trim();

        if (isbnString.isEmpty()) {
            mostrarToast("Introduce el ISBN");
            return;
        }

        Libro libro = dbHelper.consultar(isbnString);

        if (libro != null) {
            titulo.setText(libro.getTitulo());
            autor.setText(libro.getAutor());
            prestado.setChecked(libro.isPrestado());

            // Buscar posición del género en el spinner
            String[] generos = {"Ficción", "No Ficción", "Ciencia", "Historia"};
            for (int i = 0; i < generos.length; i++) {
                if (generos[i].equals(libro.getGenero())) {
                    genero.setSelection(i);
                    break;
                }
            }

            mostrarToast("Libro encontrado");
        } else {
            mostrarToast("Libro no encontrado");
        }
    }

    private void insertarLibro() {
        String isbnString = isbn.getText().toString().trim();
        String tituloString = titulo.getText().toString().trim();
        String autorString = autor.getText().toString().trim();
        String generoString = genero.getSelectedItem().toString();
        boolean prestadoBoolean = prestado.isChecked();

        if (isbnString.isEmpty() || tituloString.isEmpty() || autorString.isEmpty()) {
            mostrarToast("Todos los campos son obligatorios");
            return;
        }

        boolean exito = dbHelper.insertar(isbnString, tituloString, autorString,
                generoString, prestadoBoolean, "portada_default");

        if (exito) {
            mostrarToast("Libro insertado correctamente");
            limpiarCampos();
        } else {
            mostrarToast("Error al insertar (ISBN duplicado)");
        }
    }

    private void actualizarLibro() {
        String isbnString = isbn.getText().toString().trim();

        if (isbnString.isEmpty()) {
            mostrarToast("Introduce el ISBN");
            return;
        }

        boolean prestadoBoolean = prestado.isChecked();
        boolean exito = dbHelper.actualizar(isbnString, prestadoBoolean);

        if (exito) {
            mostrarToast("Estado actualizado");
        } else {
            mostrarToast("Libro no encontrado");
        }
    }

    private void borrarLibro() {
        String isbnString = isbn.getText().toString().trim();
        String tituloString = titulo.getText().toString().trim();

        if (isbnString.isEmpty()) {
            mostrarToast("Introduce el ISBN del libro a borrar");
            return;
        }

        new AlertDialog.Builder(this)
                .setTitle("Confirmar eliminación")
                .setMessage("¿Estás seguro de que quieres borrar el libro '" +
                        (tituloString.isEmpty() ? isbnString : tituloString) + "'?")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton("Sí", (dialog, which) -> {
                    boolean exito = dbHelper.borrar(isbnString);
                    if (exito) {
                        mostrarToast("Libro borrado con éxito");
                        limpiarCampos();
                    } else {
                        mostrarToast("Error al borrar");
                    }
                })
                .setNegativeButton("Cancelar", null)
                .show();
    }

    private void verTodosLosLibros() {
        List<Libro> libros = dbHelper.listarTodos();

        if (libros.isEmpty()) {
            mostrarToast("No hay libros en la biblioteca");
            return;
        }

        Intent intent = new Intent(this, ListarLibros.class);
        startActivity(intent);
    }

    private void limpiarCampos() {
        isbn.setText("");
        titulo.setText("");
        autor.setText("");
        prestado.setChecked(false);
        genero.setSelection(0);
    }

    public void mostrarToast(String mensaje) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }
}