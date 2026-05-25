package ramirezdelpozocorrales.jesusangel.prueba_examen_jesusangel;

import android.content.res.Resources;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class verLibros extends AppCompatActivity {

    // ── ListView ─────────────────────────────────────────────────────
    ListView listView;
    String[] generos;

    // ── RecyclerView ─────────────────────────────────────────────────
    RecyclerView recyclerView;
    String[] titulos;
    String[] directores;

    int[] imagenes = {
            R.drawable.pelicula1,
            R.drawable.pelicula2,
            R.drawable.pelicula3
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_libros);

        Resources res = getResources();
        generos    = res.getStringArray(R.array.generos);
        titulos    = res.getStringArray(R.array.titulos_peliculas);
        directores = res.getStringArray(R.array.directores);

        Button btnVolver = findViewById(R.id.btnVolver);
        // finish() cierra esta Activity y vuelve a la anterior (MainActivity).
        btnVolver.setOnClickListener(v -> finish());

        configurarListView();
        configurarRecyclerView();
    }

    // ── LISTVIEW ─────────────────────────────────────────────────────
    private void configurarListView() {
        listView = findViewById(R.id.listView);

        // ArrayAdapter: adapter simple incluido en Android, sin necesidad de crear uno propio.
        // android.R.layout.simple_list_item_1: layout predefinido de una sola línea de texto.
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_list_item_1, generos
        );
        listView.setAdapter(adapter);

        // ListView SÍ tiene setOnItemClickListener nativo.
        // DIFERENCIA CLAVE con RecyclerView: en RecyclerView el click se gestiona en el ViewHolder.
        listView.setOnItemClickListener((parent, view, position, id) ->
                Toast.makeText(this, "Género: " + generos[position], Toast.LENGTH_SHORT).show()
        );
    }

    // ── RECYCLERVIEW ─────────────────────────────────────────────────
    private void configurarRecyclerView() {
        recyclerView = findViewById(R.id.recyclerView);

        // DIFERENCIA CLAVE con ListView:
        // RecyclerView necesita un LayoutManager que decide cómo organizar los ítems.
        // LinearLayoutManager → lista vertical (igual que ListView por defecto).
        // Alternativas: GridLayoutManager (cuadrícula), StaggeredGridLayoutManager.
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // El click se gestiona aquí porque RecyclerView NO tiene setOnItemClickListener nativo.
        // Se pasa como lambda al adapter, que lo asigna en el ViewHolder.
        ListaAdapter adapter = new ListaAdapter(
                this, titulos, imagenes, directores,
                position -> Toast.makeText(
                        this,
                        "Película: " + titulos[position],
                        Toast.LENGTH_SHORT
                ).show()
        );
        recyclerView.setAdapter(adapter);
    }
}
