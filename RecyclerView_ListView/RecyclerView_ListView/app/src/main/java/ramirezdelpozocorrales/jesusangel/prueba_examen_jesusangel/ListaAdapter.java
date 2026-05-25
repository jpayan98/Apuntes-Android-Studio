package ramirezdelpozocorrales.jesusangel.prueba_examen_jesusangel;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ListaAdapter extends RecyclerView.Adapter<ListaAdapter.ViewHolder> {

    Context context;
    String[] titulos;
    int[] imagenes;
    String[] directores;

    public interface OnClickListener{
        void onClick(int position);
    }
    private OnClickListener clickListener;

    public ListaAdapter(Context c, String[]tit, int[] imgs, String[] dirs, OnClickListener listener){
        this.context=c;
        this.titulos=tit;
        this.imagenes=imgs;
        this.directores=dirs;
        this.clickListener=listener;
    }
    //ViewHolder- clase interna que guarda las referencias a las vistas de una fila
    //Se crea una sola vez x fila visible.Cuando la fila desaparece del scroll
    // android recicla el ViewHolder y lo rellena con nuevos datos en onBindViewHolder.
    //Asi evita llamar a findViewById en cada redibujado.

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView textView;
        TextView textView2;

        public ViewHolder(View itemView){
            super(itemView);
            //findViewById se ejecuta SOLO AQUI, una unica vez por ViewHolder creado.
            imageView = itemView.findViewById(R.id.imageView);
            textView = itemView.findViewById(R.id.textView);
            textView2 = itemView.findViewById(R.id.textView2);

            //El click se le asigna a la fila entera desde el ViewHolder
            imageView.setOnClickListener( v -> {
                if(clickListener != null){
                    clickListener.onClick(getAdapterPosition());
                }
            });
        }
    }

    // Equivale al inflate del getView() de ListView.
    // Solo INFLA el XML y CREA el ViewHolder. No pone datos todavía.
    // Android llama a este método solo cuando necesita una fila nueva (no reciclada).
    @NonNull
    @Override
    public ListaAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_libro, parent, false);
        return new ViewHolder(view);
    }

    // Equivale al "asigna los datos" del getView() de ListView.
    // Rellena el ViewHolder reciclado con los datos de la posición concreta.
    // Android llama a este método SIEMPRE que una fila va a aparecer en pantalla.
    @Override
    public void onBindViewHolder(@NonNull ListaAdapter.ViewHolder holder, int position) {
        holder.imageView.setImageResource(imagenes[position]);
        holder.textView.setText(titulos[position]);
        holder.textView2.setText(directores[position]);
    }

    @Override
    public int getItemCount() {
        return titulos.length;
    }
}
