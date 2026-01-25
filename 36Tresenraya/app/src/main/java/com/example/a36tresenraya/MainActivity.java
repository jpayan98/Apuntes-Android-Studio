package com.example.a36tresenraya;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    Button b1,b2,b3,b4,b5,b6,b7,b8,b9;
    String jugador="x";
    Switch s1,s2;
    TextView texto=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        b1=findViewById(R.id.button1);
        b2=findViewById(R.id.button2);
        b3=findViewById(R.id.button3);
        b4=findViewById(R.id.button4);
        b5=findViewById(R.id.button5);
        b6=findViewById(R.id.button6);
        b7=findViewById(R.id.button7);
        b8=findViewById(R.id.button8);
        b9=findViewById(R.id.button9);
        s1=findViewById(R.id.switch1);
        s2=findViewById(R.id.switch2);
        texto=findViewById(R.id.textView);


    }
    public void pulsar(View v){
        Button b = (Button) v;
        if(b.getText().equals("")){
            b.setText(jugador);
            comprobarJugada(jugador);
            cambiarJugador();
        }
    }
    public void cambiarJugador(){
        if(jugador.equals("x")){
            jugador="o";
            s1.setChecked(false);
            s2.setChecked(true);
        }else{
            jugador="x";
            s1.setChecked(true);
            s2.setChecked(false);
        }
    }
    public void comprobarJugada(String jugada){
        String casilla1=b1.getText().toString();
        String casilla2=b2.getText().toString();
        String casilla3=b3.getText().toString();
        String casilla4=b4.getText().toString();
        String casilla5=b5.getText().toString();
        String casilla6=b6.getText().toString();
        String casilla7=b7.getText().toString();
        String casilla8=b8.getText().toString();
        String casilla9=b9.getText().toString();

        if(casilla1.equals(jugada) && casilla2.equals(jugada) && casilla3.equals(jugada)){
            ganar(jugada);
        }else if (casilla4.equals(jugada) && casilla5.equals(jugada) && casilla6.equals(jugada)){
            ganar(jugada);
        }else if(casilla7.equals(jugada) && casilla8.equals(jugada) && casilla9.equals(jugada)){
            ganar(jugada);
        }else if(casilla1.equals(jugada) && casilla4.equals(jugada) && casilla7.equals(jugada)){
            ganar(jugada);
        }else if (casilla2.equals(jugada) && casilla5.equals(jugada) && casilla8.equals(jugada)){
            ganar(jugada);
        }else if(casilla3.equals(jugada) && casilla6.equals(jugada) && casilla9.equals(jugada)){
            ganar(jugada);
        }else if(casilla1.equals(jugada) && casilla5.equals(jugada) && casilla9.equals(jugada)){
            ganar(jugada);
        }else if(casilla3.equals(jugada) && casilla5.equals(jugada) && casilla7.equals(jugada)){
            ganar(jugada);
        }
    }
    public void ganar(String jugador){
        texto.setText("El jugador "+jugador+" ha ganado");
        texto.setTextSize(30);
        texto.setTextColor(Color.GREEN);
        b1.setEnabled(false);
        b2.setEnabled(false);
        b3.setEnabled(false);
        b4.setEnabled(false);
        b5.setEnabled(false);
        b6.setEnabled(false);
        b7.setEnabled(false);
        b8.setEnabled(false);
        b9.setEnabled(false);


    }
}
