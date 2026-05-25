package ramirezdelpozocorrales.jesusangel.prueba_examen_jesusangel;

import androidx.fragment.app.DialogFragment;

// Interfaz que conecta el Dialogo con la Activity sin que se conozcan directamente.
// Patrón de comunicación Fragment → Activity:
//   - Dialogo NO guarda una referencia directa a MainActivity (evita acoplamientos fuertes).
//   - En cambio, habla con cualquier Activity que implemente esta interfaz.
//   - Así, el Dialogo es reutilizable en cualquier pantalla sin cambiar su código.
//
// Flujo:
//   1. Dialogo.onAttach() comprueba que el Context (la Activity) implementa AccionesDialogo.
//   2. Guarda esa referencia como "listener".
//   3. Cuando el usuario pulsa Aceptar/Cancelar, llama al método correspondiente del listener.
//   4. La Activity decide qué hacer (crear usuario, mostrar mensaje, etc.).
public interface AccionesDialogo {

    // Se dispara cuando el usuario pulsa el botón positivo del diálogo (Aceptar).
    void onDialogPositiveClick(DialogFragment dialog);

    // Se dispara cuando el usuario pulsa el botón negativo del diálogo (Cancelar).
    void onDialogNegativeClick(DialogFragment dialog);
}
