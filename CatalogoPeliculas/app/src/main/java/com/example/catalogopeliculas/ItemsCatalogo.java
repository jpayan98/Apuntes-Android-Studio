package com.example.catalogopeliculas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

public class ItemsCatalogo extends ArrayAdapter<String> {

    Context context;
    String titulos[];
    String directores[];

    public ItemsCatalogo(Context c, String[] titulos, String[] directores) {
        super(c,R.layout.item_lista,R.id.titulo,titulos);
        this.context = c;
        this.titulos = titulos;
        this.directores = directores;
    }
    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.item_lista,parent,false);
        TextView titulo = rowView.findViewById(R.id.titulo);
        TextView autor = rowView.findViewById(R.id.director);

        titulo.setText(titulos[position]);
        autor.setText(directores[position]);
        return rowView;
    }
}
