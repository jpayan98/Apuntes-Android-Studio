package com.example.a61crearymanipularbbddsqlite;


import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TextView tvContenido;
    private CrearBD crearBD;
    private SQLiteDatabase bd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvContenido = findViewById(R.id.tvContenido);
        Button btnMostrarLista = findViewById(R.id.btnMostrarLista);

        // Crear instancia de la clase CrearBD
        crearBD = new CrearBD(this);

        // Abrir la base de datos para escritura
        bd = crearBD.getWritableDatabase();

        // PASO 1: INSERTAR DATOS (solo ejecutar UNA VEZ)
        // Después de la primera ejecución, comenta estas líneas
        /*
        bd.execSQL("INSERT INTO articulos VALUES(1,'papel');");
        bd.execSQL("INSERT INTO articulos VALUES(2,'lápiz');");
        bd.execSQL("INSERT INTO articulos VALUES(3,'carpeta');");
        bd.execSQL("INSERT INTO articulos VALUES(4,'bolígrafo');");
        */

        // PASO 2: ACTUALIZAR Y BORRAR DATOS (descomentar cuando quieras probar)
        /*
        bd.execSQL("UPDATE articulos SET nombre='papel charol' WHERE ref=1");
        bd.execSQL("UPDATE articulos SET nombre='cinta adhesiva' WHERE nombre='carpeta'");
        bd.execSQL("DELETE FROM articulos WHERE nombre='lápiz'");
        */

        // Cerrar la base de datos de escritura
        bd.close();

        // Mostrar contenido en el TextView
        mostrarContenidoBD();

        // Configurar el botón para mostrar la lista en RecyclerView
        btnMostrarLista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarLista();
            }
        });
    }

    private void mostrarContenidoBD() {
        bd = crearBD.getReadableDatabase();
        Cursor contenido = bd.rawQuery("SELECT * FROM articulos", null);

        StringBuilder resultado = new StringBuilder();
        resultado.append("Total de artículos: ").append(contenido.getCount()).append("\n\n");

        if (contenido.moveToFirst()) {
            do {
                int ref = contenido.getInt(0);
                String nombre = contenido.getString(1);
                resultado.append("Ref: ").append(ref)
                        .append(" - Nombre: ").append(nombre)
                        .append("\n");
            } while (contenido.moveToNext());
        } else {
            resultado.append("(No hay artículos en la base de datos)");
        }

        contenido.close();
        bd.close();
        tvContenido.setText(resultado.toString());
    }

    private ArrayList<String> crearLista() {
        ArrayList<String> lista = new ArrayList<>();
        bd = crearBD.getReadableDatabase();
        Cursor contenido = bd.rawQuery("SELECT * FROM articulos", null);

        if (contenido.moveToFirst()) {
            do {
                String nombre = contenido.getString(1);
                lista.add(nombre);
            } while (contenido.moveToNext());
        }

        contenido.close();
        bd.close();
        return lista;
    }

    private void mostrarLista() {
        ArrayList<String> lista = crearLista();
        Intent intent = new Intent(this, MuestraDatos.class);
        intent.putStringArrayListExtra("lista", lista);
        startActivity(intent);
    }
}