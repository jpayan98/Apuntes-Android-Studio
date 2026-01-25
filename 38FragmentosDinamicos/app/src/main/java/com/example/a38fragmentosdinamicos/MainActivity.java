package com.example.a38fragmentosdinamicos;


import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import androidx.fragment.app.Fragment;

public class MainActivity extends AppCompatActivity {

    private boolean cargarFragmentoB = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Cargar fragmento inicial
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.container, new Fragmento_a())
                .commit();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment;

                if (cargarFragmentoB) {
                    fragment = new Fragmento_b();
                    cargarFragmentoB = false;
                } else {
                    fragment = new Fragmento_a();
                    cargarFragmentoB = true;
                }

                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.container, fragment)
                        .commit();
            }
        });
    }
}