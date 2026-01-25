package com.example.a55leeryescribirenficherosexternos;

import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    private static final String NOMBRE_FICHERO = "visitantes.txt";
    private EditText etNombre;
    private TextView etVisitantes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNombre = findViewById(R.id.etNombre);
        etVisitantes = findViewById(R.id.etVisitantes);

        // Configurar evento para detectar cuando se pulsa Enter en el EditText
        etNombre.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE ||
                        (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER
                                && event.getAction() == KeyEvent.ACTION_DOWN)) {
                    onNuevoNombre();
                    return true;
                }
                return false;
            }
        });

        // Cargar los visitantes anteriores
        actualizarVisitantes();
    }

    private void onNuevoNombre() {
        String nombre = etNombre.getText().toString();

        if (nombre.isEmpty()) {
            mostrarToast("Debes escribir un nombre");
            return;
        }

        try {
            // Abrir el fichero en modo append (añadir al final)
            FileOutputStream fos = openFileOutput(NOMBRE_FICHERO, Context.MODE_APPEND);

            // Escribir el nombre seguido de salto de línea
            String textoAGuardar = nombre + "\n";
            fos.write(textoAGuardar.getBytes());
            fos.close();

            // Limpiar el EditText
            etNombre.setText("");

            // Actualizar la lista de visitantes
            actualizarVisitantes();

            mostrarToast("Nombre guardado: " + nombre);

        } catch (Exception e) {
            mostrarToast(getString(R.string.errorEscritura));
            e.printStackTrace();
        }
    }

    private void actualizarVisitantes() {
        StringBuilder visitantes = new StringBuilder();

        try {
            // Abrir el fichero para lectura
            FileInputStream fis = openFileInput(NOMBRE_FICHERO);
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));

            String linea;
            while ((linea = br.readLine()) != null) {
                visitantes.append(linea).append("\n");
            }

            br.close();
            fis.close();

        } catch (Exception e) {
            // Si el fichero no existe aún, no pasa nada
            visitantes.append("(No hay visitantes aún)");
        }

        // Actualizar el TextView con los visitantes
        etVisitantes.setText(visitantes.toString());
    }

    private void mostrarToast(String mensaje) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }
}
