package com.example.appbibliotecajuanpayan;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class MiAdaptador extends RecyclerView.Adapter<MiAdaptador.ViewHolder> {

    private List<Libro> listaLibros;

    public MiAdaptador(List<Libro> listaLibros) {
        this.listaLibros = listaLibros;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_lista, parent, false);  // ← CORREGIDO: era activity_listar_libros
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Libro libro = listaLibros.get(position);
        holder.isbnref.setText("ISBN: " + libro.getIsbn());
        holder.tituloref.setText("Título: " + libro.getTitulo());
        holder.autorref.setText("Autor: " + libro.getAutor());

        // Mostrar estado con color
        String estado = libro.isPrestado() ? "PRESTADO" : "DISPONIBLE";
        int color = libro.isPrestado() ? 0xFFFF0000 : 0xFF00AA00; // Rojo o Verde
        holder.estadoref.setText("Estado: " + estado);
        holder.estadoref.setTextColor(color);
    }

    @Override
    public int getItemCount() {
        return listaLibros.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView isbnref, tituloref, autorref, estadoref;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            isbnref = itemView.findViewById(R.id.isbnref);
            tituloref = itemView.findViewById(R.id.tituloref);
            autorref = itemView.findViewById(R.id.autorref);
            estadoref = itemView.findViewById(R.id.estadoref);
        }
    }
}