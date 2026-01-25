package com.example.a51reconstrucciondeunaactividad;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final String VALOR_GUARDADO = "valor";

    private int valor;
    private TextView tvContador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Recoger referencias a los controles
        tvContador = findViewById(R.id.tvContador);
        Button btnMas = findViewById(R.id.btnMas);
        Button btnMenos = findViewById(R.id.btnMenos);

        // Verificar si estamos restaurando estado
        if (savedInstanceState == null) {
            // Inicialización nueva - valor inicial es 0
            valor = 0;
            tvContador.setText(String.valueOf(valor));
        } else {
            // Restaurar estado guardado
            valor = savedInstanceState.getInt(VALOR_GUARDADO);
            tvContador.setText(String.valueOf(valor));
        }

        // Configurar listeners de los botones
        btnMas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onMas();
            }
        });

        btnMenos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onMenos();
            }
        });
    }

    public void onMas() {
        valor++;
        tvContador.setText(String.valueOf(valor));
    }

    public void onMenos() {
        valor--;
        tvContador.setText(String.valueOf(valor));
    }

    @Override
    public void onSaveInstanceState(Bundle estado) {
        // Llamamos al método de la superclase
        super.onSaveInstanceState(estado);

        // Guardamos el único valor que nos interesa
        estado.putInt(VALOR_GUARDADO, valor);
    }
}
