package ramirezdelpozocorrales.jesusangel.prueba_examen_jesusangel;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

public class MainActivity extends AppCompatActivity implements AccionesDialogo {

    // Declarar fuera de onCreate para que todos los métodos de la clase puedan usarlos.
    EditText etUser, etPW;
    ImageButton bImagen;
    Button bAceptar;
    CrearBD bd;
    Boolean autorizacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etUser   = findViewById(R.id.etUser);
        etPW     = findViewById(R.id.etPW);
        bImagen  = findViewById(R.id.imageButton);
        bAceptar = findViewById(R.id.button);
        autorizacion = false;

        bd = new CrearBD(this);

        bAceptar.setOnClickListener(v -> comprobarUsuario());
        // Pulsar la imagen navega a verLibros solo si el usuario está autorizado.
        bImagen.setOnClickListener(v -> verLibros());
    }

    // Se ejecuta automáticamente al volver desde verLibros.
    // Limpia los campos y resetea la autorización por seguridad.
    @Override
    public void onRestart() {
        super.onRestart();
        limpiarCajas();
        autorizacion = false;
    }

    // Navega a verLibros solo si el usuario está autorizado.
    // Intent es la forma de navegar entre Activities.
    private void verLibros() {
        if (autorizacion) {
            startActivity(new Intent(this, verLibros.class));
        }
    }

    private void comprobarUsuario() {
        if (etUser.getText().toString().isEmpty()) {
            verMensajeToast(getString(R.string.campoUser));
            return;
        }
        if (etPW.getText().toString().isEmpty()) {
            verMensajeToast(getString(R.string.campoPassW));
            return;
        }

        // Abre la BD en modo lectura y busca el usuario.
        SQLiteDatabase db = bd.getReadableDatabase();
        Cursor c = db.rawQuery(
                "SELECT password FROM " + CrearBD.TABLA_USUARIO + " WHERE usuario=?",
                // El ? es un comodín que se sustituye por el texto del EditText.
                new String[]{etUser.getText().toString()}
        );

        if (c.moveToFirst()) {
            // El usuario existe. getString(0) coge la columna 0 del SELECT, que es password.
            if (c.getString(0).equals(etPW.getText().toString())) {
                autorizacion = true;
                verMensajeToast(getString(R.string.ok));
            } else {
                autorizacion = false;
                verMensajeToast(getString(R.string.notOK));
                etPW.setText("");
            }
        } else {
            // El usuario no existe → ofrecer crearlo con el Dialogo.
            autorizacion = false;
            verMensajeToast(getString(R.string.notExists));
            mostrarDialogo();
        }

        c.close();
        db.close();
    }

    private void ayadirUsuario() {
        try {
            SQLiteDatabase db = bd.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("usuario", etUser.getText().toString());
            values.put("password", etPW.getText().toString());
            db.insertOrThrow(CrearBD.TABLA_USUARIO, null, values);
            db.close();
            verMensajeToast(getString(R.string.insertadoOk));
        } catch (Exception e) {
            verMensajeToast(getString(R.string.insertadoNotOk));
        }
    }

    public void verMensajeToast(String mensaje) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }

    public void limpiarCajas() {
        etPW.setText("");
        etUser.setText("");
    }

    // Crea el Dialogo y lo muestra. getSupportFragmentManager gestiona los Fragments de la Activity.
    public void mostrarDialogo() {
        Dialogo dialogo = new Dialogo();
        dialogo.show(getSupportFragmentManager(), "tagDialogo");
    }

    // Método de AccionesDialogo: se ejecuta al pulsar Aceptar en el Dialogo.
    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {

        ayadirUsuario();
    }

    // Método de AccionesDialogo: se ejecuta al pulsar Cancelar en el Dialogo.
    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {
        verMensajeToast(getString(R.string.cancelado));
        limpiarCajas();
    }
}
