package com.example.a42cuadrosdedialogo;


import androidx.fragment.app.DialogFragment;

public interface AccionesDialogo {
    void onDialogPositiveClick(DialogFragment dialog);
    void onDialogNegativeClick(DialogFragment dialog);
}