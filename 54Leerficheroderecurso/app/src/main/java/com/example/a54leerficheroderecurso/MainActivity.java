package com.example.a54leerficheroderecurso;

import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import androidx.appcompat.app.AppCompatActivity;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RadioGroup radioGroup = findViewById(R.id.radioGroup);

        // Leer el fichero de recursos raw y crear RadioButtons dinámicamente
        try (InputStream inputStream = getResources().openRawResource(R.raw.melodias);
             InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
             BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {

            String linea;
            boolean primerBoton = true;

            while ((linea = bufferedReader.readLine()) != null) {
                // Crear un nuevo RadioButton
                RadioButton radioButton = new RadioButton(this);
                radioButton.setText(linea);
                radioButton.setTextSize(18);
                radioButton.setPadding(8, 8, 8, 8);

                // Añadir el RadioButton al RadioGroup
                radioGroup.addView(radioButton);

                // Marcar el primer botón como seleccionado
                if (primerBoton) {
                    radioButton.setChecked(true);
                    primerBoton = false;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
