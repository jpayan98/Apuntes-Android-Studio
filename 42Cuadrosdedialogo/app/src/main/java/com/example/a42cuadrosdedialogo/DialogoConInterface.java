package com.example.a42cuadrosdedialogo;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.fragment.app.DialogFragment;

public class DialogoConInterface extends DialogFragment {

    AccionesDialogo listener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            listener = (AccionesDialogo) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " debe implementar AccionesDialogo");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle(R.string.titulo_juego)
                .setMessage(R.string.mensaje_juego)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        listener.onDialogPositiveClick(DialogoConInterface.this);
                    }
                })
                .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        listener.onDialogNegativeClick(DialogoConInterface.this);
                    }
                });

        return builder.create();
    }
}