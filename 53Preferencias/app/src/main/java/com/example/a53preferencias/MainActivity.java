package com.example.a53preferencias;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final String PREFERENCIA_MELODIA = "melodia";

    private RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Obtener referencias a los controles
        radioGroup = findViewById(R.id.preferenciasMelodia);
        Button btnAceptar = findViewById(R.id.btnAceptar);
        Button btnCancelar = findViewById(R.id.btnCancelar);

        // Cargar preferencias guardadas
        SharedPreferences preferencias = getPreferences(Context.MODE_PRIVATE);
        int idSeleccionado = preferencias.getInt(PREFERENCIA_MELODIA, R.id.radio0);
        radioGroup.check(idSeleccionado);

        // Configurar listener del botón Aceptar
        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onAceptar(v);
            }
        });

        // Configurar listener del botón Cancelar
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCancelar(v);
            }
        });
    }

    public void onAceptar(View v) {
        // Obtener preferencias
        SharedPreferences preferencias = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferencias.edit();

        // Guardar el ID del RadioButton seleccionado
        int idSeleccionado = radioGroup.getCheckedRadioButtonId();
        editor.putInt(PREFERENCIA_MELODIA, idSeleccionado);
        editor.commit();

        // Cerrar la actividad
        finish();
    }

    public void onCancelar(View v) {
        // Cerrar sin guardar
        finish();
    }
}
