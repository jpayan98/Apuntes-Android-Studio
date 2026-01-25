package com.example.a61crearymanipularbbddsqlite;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class myAdapter extends RecyclerView.Adapter<myAdapter.ViewHolder> {

    private ArrayList<String> localDataSet;

    // ViewHolder interno
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView;

        public ViewHolder(View view) {
            super(view);
            textView = view.findViewById(R.id.textView2);
        }

        public TextView getTextView() {
            return textView;
        }
    }

    // Constructor
    public myAdapter(ArrayList<String> dataSet) {
        localDataSet = dataSet;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Crear una nueva vista (inflar el layout item_lista)
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_lista, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Asociar los datos con la vista
        holder.getTextView().setText(localDataSet.get(position));
    }

    @Override
    public int getItemCount() {
        // Retornar el número de items
        return localDataSet.size();
    }
}