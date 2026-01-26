package com.example.examen2dejuanpayan;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ListaAdapter extends ArrayAdapter<String> {

    Context context;
    int[] imagenes;
    String[] titulos;
    String[] autores;

    // Constructor
    ListaAdapter(Context c, String[] tit, int[] imgs, String[] autores) {
        super(c, R.layout.item_lista, R.id.titulo, tit);
        this.context = c;
        this.imagenes = imgs;
        this.titulos = tit;
        this.autores = autores;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // Recuperar LayoutInflater
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // Inflar el layout item_lista
        View fila = inflater.inflate(R.layout.item_lista, parent, false);

        // Obtener referencias a los componentes
        ImageView imagen = fila.findViewById(R.id.imagen);
        TextView titulo = fila.findViewById(R.id.titulo);
        TextView descrip = fila.findViewById(R.id.autor);

        // Asignar valores
        imagen.setImageResource(imagenes[position]);
        titulo.setText(titulos[position]);
        descrip.setText(autores[position]);

        return fila;
    }
}