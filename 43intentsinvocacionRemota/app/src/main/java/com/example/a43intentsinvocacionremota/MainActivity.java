package com.example.a43intentsinvocacionremota;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnInvocar = findViewById(R.id.btnInvocar);

        btnInvocar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                i.setClassName("com.example.a43intents",
                        "com.example.a43intents.SegundaActividad");
                startActivity(i);
            }
        });
    }
}