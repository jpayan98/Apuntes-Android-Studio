package com.example.a42cuadrosdedialogo;


import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

public class MainActivity extends AppCompatActivity implements AccionesDialogo {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // Método para mostrar el diálogo simple (primer ejemplo)
    public void onVerMensaje(View view) {
        mostrarDialogo();
    }

    // Método para mostrar el diálogo con interface (método correcto)
    public void onDialogoInterface(View view) {
        lanzarDialogo();
    }

    // Primer ejemplo - Diálogo simple
    public void mostrarDialogo() {
        Dialogo dialogo = new Dialogo();
        dialogo.show(getSupportFragmentManager(), "dialogo");
    }

    // Método que muestra el Toast (llamado desde Dialogo)
    public void mostrarMensaje(Context contexto) {
        Toast.makeText(contexto, R.string.mensaje_toast, Toast.LENGTH_LONG).show();
    }

    // Segundo ejemplo - Diálogo con Interface (método correcto)
    public void lanzarDialogo() {
        DialogoConInterface dialogo = new DialogoConInterface();
        dialogo.show(getSupportFragmentManager(), "tagDialogo");
    }

    // Implementación de la interface AccionesDialogo
    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        iniciarJuego();
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {
        finalizarJuego();
    }

    // Métodos del juego (simulados)
    private void iniciarJuego() {
        Toast.makeText(this, "Iniciando nuevo juego...", Toast.LENGTH_SHORT).show();
    }

    private void finalizarJuego() {
        Toast.makeText(this, R.string.fin_partida, Toast.LENGTH_LONG).show();
        // Opcional: cerrar la aplicación
        // finish();
    }
}
