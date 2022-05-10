package com.example.proyectopie;

import static com.example.proyectopie.R.color.white;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.widget.TextView;

public class PuntuacionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puntuacion);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        myToolbar.setTitle("Puntuación Final");
        myToolbar.setTitleTextColor(ContextCompat.getColor(getApplicationContext(), white));
        setSupportActionBar(myToolbar);

        TextView resultTxt= findViewById(R.id.resulTxt);
        Bundle bundle= getIntent().getExtras();

        String nombreJugador= bundle.getString("nombre");
        int puntuacion= bundle.getInt("puntos");
        int totalPreguntas = bundle.getInt("total");

        int aprobado = totalPreguntas / 2;

        if (puntuacion >= aprobado)
        {
            resultTxt.setText("Enhorabuena! "+nombreJugador+ " has conseguido "+ puntuacion+ " de "+totalPreguntas);
        }
        else
        {
            resultTxt.setText("Ohh "+nombreJugador+ " has conseguido "+ puntuacion+ " de "+totalPreguntas+" intentalo de nuevo!");
        }



    }
}