package com.example.a64ludoteca;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText edref, edtitulo, edautor;
    private Button btnConsultar, btnInsertar, btnBorrar, btnLimpiar, btnListar;
    private DataBaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DataBaseHelper(this);

        edref = findViewById(R.id.edref);
        edtitulo = findViewById(R.id.edtitulo);
        edautor = findViewById(R.id.edautor);
        btnConsultar = findViewById(R.id.btnConsultar);
        btnInsertar = findViewById(R.id.btnInsertar);
        btnBorrar = findViewById(R.id.btnBorrar);
        btnLimpiar = findViewById(R.id.btnLimpiar);
        btnListar = findViewById(R.id.btnListar);

        btnConsultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                consultarVideo();
            }
        });

        btnInsertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertarVideo();
            }
        });

        btnBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                borrarVideo();
            }
        });

        btnLimpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                limpiarCajas();
            }
        });

        btnListar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Video> videos = listarVideos();
                Intent intent = new Intent(MainActivity.this, MostrarVideos.class);
                intent.putExtra("cantidad", videos.size());
                for (int i = 0; i < videos.size(); i++) {
                    intent.putExtra("ref" + i, videos.get(i).getReferencia());
                    intent.putExtra("titulo" + i, videos.get(i).getTitulo());
                    intent.putExtra("autor" + i, videos.get(i).getAutor());
                }
                startActivity(intent);
            }
        });
    }

    private void consultarVideo() {
        String referencia = edref.getText().toString().trim();

        if (referencia.isEmpty()) {
            verMensajeToast("Debe introducir una referencia");
            return;
        }

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(
                DataBaseHelper.TABLE_NAME,
                null,
                DataBaseHelper.COLUMN_REFERENCIA + " = ?",
                new String[]{referencia},
                null, null, null
        );

        if (cursor.moveToFirst()) {
            String titulo = cursor.getString(cursor.getColumnIndexOrThrow(DataBaseHelper.COLUMN_TITULO));
            String autor = cursor.getString(cursor.getColumnIndexOrThrow(DataBaseHelper.COLUMN_AUTOR));

            edtitulo.setText(titulo);
            edautor.setText(autor);
            verMensajeToast("Video encontrado");
        } else {
            verMensajeToast("Video no encontrado");
        }

        cursor.close();
        db.close();
    }

    private void insertarVideo() {
        String referencia = edref.getText().toString().trim();
        String titulo = edtitulo.getText().toString().trim();
        String autor = edautor.getText().toString().trim();

        if (referencia.isEmpty() || titulo.isEmpty() || autor.isEmpty()) {
            verMensajeToast("Todos los campos son obligatorios");
            return;
        }

        try {
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(DataBaseHelper.COLUMN_REFERENCIA, referencia);
            values.put(DataBaseHelper.COLUMN_TITULO, titulo);
            values.put(DataBaseHelper.COLUMN_AUTOR, autor);

            long result = db.insert(DataBaseHelper.TABLE_NAME, null, values);

            if (result != -1) {
                verMensajeToast("Video insertado correctamente");
                limpiarCajas();
            } else {
                verMensajeToast("Error al insertar video");
            }

            db.close();
        } catch (Exception e) {
            verMensajeToast("Error: " + e.getMessage());
        }
    }

    public void borrarVideo() {
        String referencia = edref.getText().toString().trim();
        String titulo = edtitulo.getText().toString().trim();

        if (referencia.isEmpty() && titulo.isEmpty()) {
            verMensajeToast("Debe introducir referencia o título");
            return;
        }

        try {
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            String whereClause = "";
            List<String> whereArgs = new ArrayList<>();

            if (!referencia.isEmpty() && !titulo.isEmpty()) {
                whereClause = DataBaseHelper.COLUMN_REFERENCIA + " = ? AND " +
                        DataBaseHelper.COLUMN_TITULO + " = ?";
                whereArgs.add(referencia);
                whereArgs.add(titulo);
            } else if (!referencia.isEmpty()) {
                whereClause = DataBaseHelper.COLUMN_REFERENCIA + " = ?";
                whereArgs.add(referencia);
            } else {
                whereClause = DataBaseHelper.COLUMN_TITULO + " = ?";
                whereArgs.add(titulo);
            }

            int rowsDeleted = db.delete(
                    DataBaseHelper.TABLE_NAME,
                    whereClause,
                    whereArgs.toArray(new String[0])
            );

            if (rowsDeleted > 0) {
                verMensajeToast("Video(s) borrado(s): " + rowsDeleted);
                limpiarCajas();
            } else {
                verMensajeToast("No se encontró el video");
            }

            db.close();
        } catch (Exception e) {
            verMensajeToast("Error al borrar: " + e.getMessage());
        }
    }

    public List<Video> listarVideos() {
        List<Video> items = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.query(
                DataBaseHelper.TABLE_NAME,
                null, null, null, null, null, null
        );

        while (cursor.moveToNext()) {
            String ref = cursor.getString(cursor.getColumnIndexOrThrow(DataBaseHelper.COLUMN_REFERENCIA));
            String titulo = cursor.getString(cursor.getColumnIndexOrThrow(DataBaseHelper.COLUMN_TITULO));
            String autor = cursor.getString(cursor.getColumnIndexOrThrow(DataBaseHelper.COLUMN_AUTOR));

            items.add(new Video(ref, titulo, autor));
        }

        cursor.close();
        db.close();

        return items;
    }

    public void verMensajeToast(String mensaje) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }

    public void limpiarCajas() {
        edref.setText("");
        edtitulo.setText("");
        edautor.setText("");
    }
}