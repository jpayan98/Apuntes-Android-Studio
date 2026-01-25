package com.example.a4enviodatosentreactividades;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final int SECONDARY_ACTIVITY_TAG = 1;
    private EditText editNombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editNombre = findViewById(R.id.editNombre);
        Button btnEnviar = findViewById(R.id.btnEnviar);

        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context contexto = MainActivity.this;
                String nombre = editNombre.getText().toString();

                if (nombre.isEmpty()) {
                    Toast.makeText(contexto, R.string.error_nombre_vacio,
                            Toast.LENGTH_SHORT).show();
                } else {
                    Intent i = new Intent(contexto, SegundaActividad.class);
                    i.putExtra("nombre", nombre);
                    startActivityForResult(i, SECONDARY_ACTIVITY_TAG);
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == SECONDARY_ACTIVITY_TAG) {
            if (resultCode == RESULT_OK) {
                String saludo = data.getStringExtra("saludo");
                Toast.makeText(this, saludo, Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, R.string.antipático, Toast.LENGTH_LONG).show();
            }
        }
    }
}