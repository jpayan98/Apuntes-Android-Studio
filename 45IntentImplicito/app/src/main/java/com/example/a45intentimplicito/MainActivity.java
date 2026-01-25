package com.example.a45intentimplicito;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CALL_PERMISSION = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Configurar listeners para todos los botones
        Button button01 = findViewById(R.id.button01);
        Button button02 = findViewById(R.id.button02);
        Button button03 = findViewById(R.id.button03);
        Button button04 = findViewById(R.id.button04);
        Button button05 = findViewById(R.id.button05);
        Button button06 = findViewById(R.id.button06);
        Button button07 = findViewById(R.id.button07);
        Button button08 = findViewById(R.id.button08);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBotonEjecutar(v);
            }
        };

        button01.setOnClickListener(listener);
        button02.setOnClickListener(listener);
        button03.setOnClickListener(listener);
        button04.setOnClickListener(listener);
        button05.setOnClickListener(listener);
        button06.setOnClickListener(listener);
        button07.setOnClickListener(listener);
        button08.setOnClickListener(listener);
    }

    public void onBotonEjecutar(View v) {
        Intent i = new Intent();

        if (v.getId() == R.id.button01) {
            // Lanzamos la actividad que muestra el resumen
            // de uso y estado de la batería
            i.setAction(Intent.ACTION_POWER_USAGE_SUMMARY);
            startActivity(i);
        }
        else if (v.getId() == R.id.button02) {
            // Lanzamos un navegador web
            i.setAction(Intent.ACTION_VIEW);
            i.setData(Uri.parse("https://www.google.es"));
            startActivity(i);
        }
        else if (v.getId() == R.id.button03) {
            // Abrir la edición de un SMS (simple)
            i.setAction(Intent.ACTION_VIEW);
            i.setData(Uri.parse("sms:5554433"));
            startActivity(i);
        }
        else if (v.getId() == R.id.button04) {
            // Abrir la edición de un SMS con texto predefinido
            i.setAction(Intent.ACTION_VIEW);
            i.setData(Uri.parse("sms:5554433"));
            i.putExtra("sms_body", "Hola! Estoy aprendiendo Android :-)");
            startActivity(i);
        }
        else if (v.getId() == R.id.button05) {
            // Abrir la galería multimedia (imágenes)
            i.setAction(Intent.ACTION_GET_CONTENT);
            i.setType("image/*");
            startActivity(i);
        }
        else if (v.getId() == R.id.button06) {
            // Abrir la galería multimedia (audio)
            i.setAction(Intent.ACTION_GET_CONTENT);
            i.setType("audio/*");
            startActivity(i);
        }
        else if (v.getId() == R.id.button07) {
            // Abrir la ventana de marcación para hacer una llamada
            i.setAction(Intent.ACTION_DIAL);
            i.setData(Uri.parse("tel:5554433"));
            startActivity(i);
        }
        else if (v.getId() == R.id.button08) {
            // Lanzar una llamada de teléfono (requiere permiso)
            hacerLlamada();
        }
    }

    private void hacerLlamada() {
        // Verificar si tenemos el permiso
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)
                != PackageManager.PERMISSION_GRANTED) {
            // No tenemos permiso, lo solicitamos
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CALL_PHONE},
                    REQUEST_CALL_PERMISSION);
        } else {
            // Tenemos permiso, hacer la llamada
            Intent i = new Intent(Intent.ACTION_CALL);
            i.setData(Uri.parse("tel:5554433"));
            startActivity(i);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQUEST_CALL_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permiso concedido, hacer la llamada
                Intent i = new Intent(Intent.ACTION_CALL);
                i.setData(Uri.parse("tel:5554433"));
                startActivity(i);
            } else {
                // Permiso denegado
                Toast.makeText(this, "Permiso de llamada denegado", Toast.LENGTH_SHORT).show();
            }
        }
    }
}