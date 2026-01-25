package com.example.a35botonesderadio;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

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
    }
    public void botonAceptar(View v){
        RadioGroup botones=(RadioGroup)findViewById(R.id.botones);
        TextView tx=(TextView)findViewById(R.id.textOculto);
        if(botones.getCheckedRadioButtonId()==R.id.radio1){
            tx.setText(R.string.respAndr);
        }
        if(botones.getCheckedRadioButtonId()==R.id.radio2){
            tx.setText(R.string.respIOS);
        }
        if(botones.getCheckedRadioButtonId()==R.id.radio3){
            tx.setText(R.string.respWind);
        }
        if(botones.getCheckedRadioButtonId()==R.id.radio4){
            tx.setText(R.string.respSimb);
        }
        if(botones.getCheckedRadioButtonId()==R.id.radio5){
            tx.setText(R.string.respOtro);
        }

    }
}