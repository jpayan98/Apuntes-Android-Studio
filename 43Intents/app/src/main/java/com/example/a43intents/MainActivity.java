package com.example.a43intents;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnPulsame = findViewById(R.id.btnPulsame);

        btnPulsame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Guardar el contexto
                Context contexto = MainActivity.this;

                // Crear el Intent
                Intent i = new Intent(contexto, SegundaActividad.class);
                startActivity(i);

                // Toast que aparece sobre la segunda ventana
                Toast.makeText(contexto, R.string.toast_mensaje, Toast.LENGTH_LONG).show();
            }
        });
    }
}