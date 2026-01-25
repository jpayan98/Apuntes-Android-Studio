package com.example.a4enviodatosentreactividades;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class SegundaActividad extends AppCompatActivity {

    private EditText editSaludo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segunda_actividad);

        TextView txtSaludo = findViewById(R.id.txtSaludo);
        editSaludo = findViewById(R.id.editSaludo);
        Button btnDevolver = findViewById(R.id.btnDevolver);

        // Recibir el nombre enviado desde MainActivity
        Intent intent = getIntent();
        String nombre = intent.getStringExtra("nombre");

        // Mostrar el saludo personalizado
        String mensaje = getString(R.string.saludo_segunda, nombre);
        txtSaludo.setText(mensaje);

        // Configurar el botón para devolver el saludo
        btnDevolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context contexto = SegundaActividad.this;
                String saludo = editSaludo.getText().toString();

                Intent intentRespuesta = new Intent();
                intentRespuesta.putExtra("saludo", saludo);
                setResult(RESULT_OK, intentRespuesta);
                finish();
            }
        });
    }
}