package com.example.a34adivinaminumero;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    // Declaración de variables
    private TextView textViewInicio;
    private TextView textViewIntentalo;
    private TextView textViewIntentos;
    private EditText editText;
    private Button buttonProbar;
    private Button buttonNuevaPartida;

    private int numeroOculto;
    private int numeroJugado;
    private int numIntentos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Asociar variables con componentes del layout
        textViewInicio = findViewById(R.id.textViewInicio);
        textViewIntentalo = findViewById(R.id.textViewIntentalo);
        textViewIntentos = findViewById(R.id.textViewIntentos);
        editText = findViewById(R.id.editText);
        buttonProbar = findViewById(R.id.buttonProbar);
        buttonNuevaPartida = findViewById(R.id.buttonNuevaPartida);

        // Generar número aleatorio
        inicializarJuego();

        // Configurar listener para la tecla ENTER en el EditText
        editText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // Han pulsado una tecla y la tecla es ENTER
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    comprobarNumero(v);
                    return true;
                }
                return false;
            }
        });
    }

    // Método para inicializar o reiniciar el juego
    private void inicializarJuego() {
        // Generar número aleatorio entre 1 y 100
        Random dado = new Random();
        numeroOculto = dado.nextInt(100) + 1;

        // Para pruebas, puedes usar un número fijo:
        // numeroOculto = 50;

        // Inicializar intentos
        numIntentos = 0;

        // Restaurar texto inicial
        textViewInicio.setText(R.string.textoInicio);

        // Limpiar el EditText
        editText.setText("");

        // Hacer visibles los controles del juego
        editText.setVisibility(View.VISIBLE);
        textViewIntentalo.setVisibility(View.VISIBLE);
        buttonProbar.setVisibility(View.VISIBLE);

        // Ocultar botón de nueva partida e intentos
        buttonNuevaPartida.setVisibility(View.GONE);
        textViewIntentos.setVisibility(View.GONE);
    }

    // Método llamado al pulsar el botón Probar o ENTER
    public void comprobarNumero(View view) {
        // Verificar que hay texto en el EditText
        String textoIntroducido = editText.getText().toString();
        if (textoIntroducido.isEmpty()) {
            return;
        }

        // Obtener el número introducido
        numeroJugado = Integer.parseInt(textoIntroducido);

        // Incrementar intentos
        numIntentos++;
        actualizarIntentos();

        // Comparar con el número oculto
        if (numeroJugado < numeroOculto) {
            // El número es mayor
            String mensaje = getResources().getString(R.string.textoMayor);
            String mensajeFinal = String.format(mensaje, numeroJugado);
            textViewInicio.setText(mensajeFinal);
        } else if (numeroJugado > numeroOculto) {
            // El número es menor
            String mensaje = getResources().getString(R.string.textoMenor);
            String mensajeFinal = String.format(mensaje, numeroJugado);
            textViewInicio.setText(mensajeFinal);
        } else {
            // ¡Ha acertado!
            textViewInicio.setText(R.string.textoAcierto);
            finalizarPartida();
        }

        // Limpiar el EditText para el siguiente intento
        editText.setText("");
    }

    // Método para actualizar el contador de intentos
    private void actualizarIntentos() {
        textViewIntentos.setVisibility(View.VISIBLE);
        String textoIntentos = getResources().getQuantityString(
                R.plurals.intentos,
                numIntentos,
                numIntentos
        );
        textViewIntentos.setText(textoIntentos);
    }

    // Método para finalizar la partida
    private void finalizarPartida() {
        // Ocultar controles de entrada
        editText.setVisibility(View.GONE);
        buttonProbar.setVisibility(View.GONE);
        textViewIntentalo.setVisibility(View.GONE);

        // Mostrar botón de nueva partida
        buttonNuevaPartida.setVisibility(View.VISIBLE);
    }

    // Método llamado al pulsar el botón Nueva Partida
    public void nuevaPartida(View view) {
        inicializarJuego();
    }
}