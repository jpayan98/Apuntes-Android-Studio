package com.example.a64ludoteca;


import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class MostrarVideos extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_videos);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<Video> items = new ArrayList<>();

        int cantidad = getIntent().getIntExtra("cantidad", 0);
        for (int i = 0; i < cantidad; i++) {
            String ref = getIntent().getStringExtra("ref" + i);
            String titulo = getIntent().getStringExtra("titulo" + i);
            String autor = getIntent().getStringExtra("autor" + i);
            items.add(new Video(ref, titulo, autor));
        }

        MiAdaptador adaptador = new MiAdaptador(items);
        recyclerView.setAdapter(adaptador);
    }
}