package com.example.tiendamusica;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.ViewHolder> {

    Context context;
    String[] titulos;
    String[] artistas;
    int[] imagenes;

    // Interfaz para el click — igual que en el proyecto del profesor
    public interface OnClickListener {
        void onClick(int position);
    }
    private OnClickListener clickListener;

    public AlbumAdapter(Context c, String[] titulos, String[] artistas, int[] imagenes, OnClickListener listener) {
        this.context = c;
        this.titulos = titulos;
        this.artistas = artistas;
        this.imagenes = imagenes;
        this.clickListener = listener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imagen;
        TextView titulo;
        TextView artista;

        public ViewHolder(View itemView) {
            super(itemView);
            imagen = itemView.findViewById(R.id.imgAlbum);
            titulo = itemView.findViewById(R.id.tvTitulo);
            artista = itemView.findViewById(R.id.tvArtista);

            // Click en la fila entera
            itemView.setOnClickListener(v -> {
                if (clickListener != null) {
                    clickListener.onClick(getAdapterPosition());
                }
            });
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_album, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.imagen.setImageResource(imagenes[position]);
        holder.titulo.setText(titulos[position]);
        holder.artista.setText(artistas[position]);
    }

    @Override
    public int getItemCount() {
        return titulos.length;
    }
}
