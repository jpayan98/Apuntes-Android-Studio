package com.example.a64ludoteca;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class MiAdaptador extends RecyclerView.Adapter<MiAdaptador.ViewHolder> {

    private List<Video> listaVideos;

    public MiAdaptador(List<Video> listaVideos) {
        this.listaVideos = listaVideos;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_lista, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Video video = listaVideos.get(position);
        holder.tvReferencia.setText("Ref: " + video.getReferencia());
        holder.tvTitulo.setText("Título: " + video.getTitulo());
        holder.tvAutor.setText("Autor: " + video.getAutor());
    }

    @Override
    public int getItemCount() {
        return listaVideos.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvReferencia, tvTitulo, tvAutor;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvReferencia = itemView.findViewById(R.id.tvReferencia);
            tvTitulo = itemView.findViewById(R.id.tvTitulo);
            tvAutor = itemView.findViewById(R.id.tvAutor);
        }
    }
}
