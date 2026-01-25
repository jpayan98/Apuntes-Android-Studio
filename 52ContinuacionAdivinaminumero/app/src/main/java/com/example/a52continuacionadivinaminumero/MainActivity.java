package com.example.a52continuacionadivinaminumero;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    // Constantes para guardar el estado en el Bundle
    private static final String STATE_NUM_ELEGIDO = "numElegido";
    private static final String STATE_NUM_INTENTOS = "numIntentos";
    private static final String STATE_MENSAJE = "mensajeActual";

    // Declaración de variables
    private TextView textViewInicio;
    private TextView textViewIntentalo;
    private TextView textViewIntentos;
    private EditText editText;
    private Button buttonProbar;
    private Button buttonNuevaPartida;

    private int numeroOculto;  // También llamado numElegido
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

        // Configurar listener para la tecla ENTER en el EditText
        editText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    comprobarNumero(v);
                    return true;
                }
                return false;
            }
        });

        // AQUÍ ESTÁ LA CLAVE: Verificar si hay estado guardado
        if (savedInstanceState == null) {
            // Estamos iniciando desde cero. Comenzamos una partida.
            reiniciaPartida();
        } else {
            // Tenemos que reconstruirnos desde el bundle.
            numeroOculto = savedInstanceState.getInt(STATE_NUM_ELEGIDO);
            numIntentos = savedInstanceState.getInt(STATE_NUM_INTENTOS);

            if (numeroOculto == -1) {
                // La partida está terminada.
                partidaAcabada();
            } else {
                // La partida está a mitad. Ponemos el texto de la etiqueta superior.
                textViewInicio.setText(savedInstanceState.getString(STATE_MENSAJE));

                // Actualizamos la etiqueta del número de intentos
                actualizarIntentos();
            }
        }
    }

    /**
     * MÉTODO CRUCIAL: Guarda el estado antes de que la actividad sea destruida
     * Se llama automáticamente cuando Android rota la pantalla o necesita memoria
     */
    @Override
    public void onSaveInstanceState(Bundle estado) {
        super.onSaveInstanceState(estado);

        // Guardamos las tres variables críticas
        estado.putInt(STATE_NUM_ELEGIDO, numeroOculto);
        estado.putInt(STATE_NUM_INTENTOS, numIntentos);
        estado.putString(STATE_MENSAJE, textViewInicio.getText().toString());
    }

    /**
     * Método para inicializar o reiniciar el juego
     */
    private void reiniciaPartida() {
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

    /**
     * Método llamado al pulsar el botón Probar o ENTER
     */
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

            // IMPORTANTE: Marcar que la partida ha terminado
            numeroOculto = -1;

            finalizarPartida();
        }

        // Limpiar el EditText para el siguiente intento
        editText.setText("");
    }

    /**
     * Método para actualizar el contador de intentos
     */
    private void actualizarIntentos() {
        textViewIntentos.setVisibility(View.VISIBLE);
        String textoIntentos = getResources().getQuantityString(
                R.plurals.intentos,
                numIntentos,
                numIntentos
        );
        textViewIntentos.setText(textoIntentos);
    }

    /**
     * Método para finalizar la partida (cuando se acierta)
     */
    private void finalizarPartida() {
        // Ocultar controles de entrada
        editText.setVisibility(View.GONE);
        buttonProbar.setVisibility(View.GONE);
        textViewIntentalo.setVisibility(View.GONE);

        // Mostrar botón de nueva partida
        buttonNuevaPartida.setVisibility(View.VISIBLE);
    }

    /**
     * Método para restaurar la interfaz cuando la partida ya había acabado
     * Se llama desde onCreate cuando detectamos que numeroOculto == -1
     */
    private void partidaAcabada() {
        // El mensaje de "Has acertado" ya está guardado en STATE_MENSAJE
        // y se restaura automáticamente en onCreate

        // Ocultar controles de entrada
        editText.setVisibility(View.GONE);
        buttonProbar.setVisibility(View.GONE);
        textViewIntentalo.setVisibility(View.GONE);

        // Mostrar botón de nueva partida y contador de intentos
        buttonNuevaPartida.setVisibility(View.VISIBLE);
        textViewIntentos.setVisibility(View.VISIBLE);

        // Actualizar el texto de intentos
        actualizarIntentos();
    }

    /**
     * Método llamado al pulsar el botón Nueva Partida
     */
    public void nuevaPartida(View view) {
        reiniciaPartida();
    }
}