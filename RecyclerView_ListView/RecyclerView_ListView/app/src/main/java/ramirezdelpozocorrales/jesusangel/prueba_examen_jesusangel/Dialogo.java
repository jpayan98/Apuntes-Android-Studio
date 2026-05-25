package ramirezdelpozocorrales.jesusangel.prueba_examen_jesusangel;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

// DialogFragment: forma recomendada de mostrar un diálogo en Android moderno.
// Ventajas frente a crear un AlertDialog directamente en la Activity:
//   - Sobrevive a rotaciones de pantalla (el sistema lo recrea automáticamente).
//   - Tiene su propio ciclo de vida (onAttach, onCreateDialog, onDetach…).
//   - Se puede reutilizar en cualquier Activity que implemente AccionesDialogo.
public class Dialogo extends DialogFragment {

    // Referencia a la Activity que implementa AccionesDialogo.
    // Se inicializa en onAttach y se usa en los listeners de los botones.
    private AccionesDialogo listener;

    // onAttach se ejecuta cuando el Fragment se asocia a una Activity.
    // Es el momento más seguro para obtener la referencia, porque el Context
    // ya existe pero el Fragment todavía no ha inflado ninguna vista.
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        // Comprobamos en tiempo de ejecución que la Activity implementa la interfaz.
        // Si no lo hace, lanzamos una excepción clara en lugar de un NullPointerException
        // silencioso más adelante cuando se intente llamar al listener.
        if (context instanceof AccionesDialogo) {
            listener = (AccionesDialogo) context;
        } else {
            throw new RuntimeException(context.toString() + " debe implementar AccionesDialogo");
        }
    }

    // onCreateDialog reemplaza a onCreate cuando el Fragment es un diálogo.
    // Debe devolver el Dialog que se mostrará al usuario.
    // @NonNull garantiza que nunca devolvemos null (el sistema lo exige).
    // @Nullable en savedInstanceState indica que puede llegar null si es la primera vez.
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        // AlertDialog.Builder: patrón Builder para construir el diálogo paso a paso.
        // Permite encadenar llamadas (setTitle, setMessage, setPositiveButton…)
        // sin necesidad de variables intermedias.
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle(getString(R.string.titulo_dialogo))
                .setMessage(getString(R.string.texto_dialogo))

                // Botón positivo (Aceptar): llama al método de la interfaz.
                // onClick recibe el Dialog y el índice del botón pulsado (which),
                // pero aquí no los necesitamos, solo notificamos a la Activity.
                .setPositiveButton(getString(R.string.aceptar), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // "Dialogo.this" referencia la instancia del Fragment desde dentro
                        // de la clase anónima (donde "this" sería el OnClickListener).
                        listener.onDialogPositiveClick(Dialogo.this);
                    }
                })

                // Botón negativo (Cancelar): notifica a la Activity para que limpie campos, etc.
                .setNegativeButton(getString(R.string.cancelar), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        listener.onDialogNegativeClick(Dialogo.this);
                    }
                });

        // builder.create() construye el Dialog sin mostrarlo todavía.
        // El sistema lo mostrará cuando llamemos a dialogo.show() en la Activity.
        // NO usar builder.show() aquí porque DialogFragment gestiona él solo la visibilidad.
        return builder.create();
    }
}
