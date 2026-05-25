package com.example.gestortareas;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import java.util.List;

public class TareaAdapter extends ArrayAdapter<Tarea> {

    private Context context;
    private List<Tarea> tareas;
    private OnTareaListener listener;

    // Interfaz para comunicar eventos al Activity
    public interface OnTareaListener {
        void onEliminar(int posicion);
        void onCompletar(int posicion, boolean completada);
    }

    public TareaAdapter(Context context, List<Tarea> tareas, OnTareaListener listener) {
        super(context, 0, tareas);
        this.context = context;
        this.tareas = tareas;
        this.listener = listener;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Inflar el layout del item
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_tarea, parent, false);
        }

        Tarea tarea = tareas.get(position);

        TextView tvNombre = convertView.findViewById(R.id.tvNombreTarea);
        CheckBox checkBox = convertView.findViewById(R.id.checkBoxTarea);
        Button btnEliminar = convertView.findViewById(R.id.btnEliminar);

        // Poner nombre
        tvNombre.setText(tarea.getNombre());

        // Tachar si está completada
        if (tarea.isCompletada()) {
            tvNombre.setPaintFlags(tvNombre.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        } else {
            tvNombre.setPaintFlags(tvNombre.getPaintFlags() & ~Paint.STRIKE_THRU_TEXT_FLAG);
        }

        // Evitar que el CheckBox dispare eventos al reciclar vistas
        checkBox.setOnCheckedChangeListener(null);
        checkBox.setChecked(tarea.isCompletada());

        // Evento CheckBox
        checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            listener.onCompletar(position, isChecked);
        });

        // Evento botón eliminar
        btnEliminar.setOnClickListener(v -> listener.onEliminar(position));

        return convertView;
    }
}