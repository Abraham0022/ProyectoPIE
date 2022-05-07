package com.example.proyectopie;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class PuntuacionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puntuacion);

        TextView resultTxt= findViewById(R.id.resulTxt);
        Bundle bundle= getIntent().getExtras();

        String nombreJugador= bundle.getString("nombre");
        int puntuacion= bundle.getInt("puntos");

        resultTxt.setText(nombreJugador+ " has conseguido "+ puntuacion);

    }
}