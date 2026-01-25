package com.example.a41toasts;


import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // 1. Toast básico simple
    public void onToastBasico(View view) {
        Toast toast = Toast.makeText(this, R.string.mensaje, Toast.LENGTH_LONG);
        toast.show();
    }

    // 2. Toast con gravedad y margen personalizado
    public void onToastGravedad(View view) {
        Toast toast = Toast.makeText(this, "Toast en la parte superior", Toast.LENGTH_LONG);
        toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 300);
        toast.show();
    }

    // 3. Toast con DatePicker (Calendario)
    public void onToastCalendario(View view) {
        Context c = getApplicationContext();
        Toast toast = new Toast(c);
        toast.setDuration(Toast.LENGTH_LONG);
        DatePicker calendario = new DatePicker(c);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setView(calendario);
        toast.show();
    }

    // 4. Toast con TimePicker (Reloj)
    public void onToastReloj(View view) {
        Context c = getApplicationContext();
        Toast toast = new Toast(c);
        toast.setDuration(Toast.LENGTH_LONG);
        TimePicker reloj = new TimePicker(c);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setView(reloj);
        toast.show();
    }

    // 5. SnackBar básico con colores personalizados
    public void onSnackbarBasico(View view) {
        Snackbar.make(view, R.string.mensaje, Snackbar.LENGTH_LONG)
                .setTextColor(Color.RED)
                .setBackgroundTint(Color.CYAN)
                .setAction("Action", null)
                .show();
    }

    // 6. SnackBar con acción que muestra el reloj
    public void onSnackbarAccion(View view) {
        Snackbar.make(view, "¿Quieres ver el reloj?", Snackbar.LENGTH_LONG)
                .setTextColor(Color.RED)
                .setBackgroundTint(Color.WHITE)
                .setAction("Aceptar", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast toast = new Toast(v.getContext());
                        toast.setDuration(Toast.LENGTH_LONG);
                        TimePicker reloj = new TimePicker(v.getContext());
                        toast.setGravity(Gravity.TOP, 0, 0);
                        toast.setView(reloj);
                        toast.show();
                    }
                })
                .show();
    }
}