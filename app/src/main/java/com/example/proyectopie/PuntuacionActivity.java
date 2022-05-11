package com.example.proyectopie;

import static com.example.proyectopie.R.color.white;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
/**
 * Controla la actividad que muestra el resultado del test
 * @author Ruben Mena Aparicio
 * @author Abraham Pérez Barrera
 * @version 1.0 05/2022
 */
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
        Button btn_fin = findViewById(R.id.btn_fin);
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
            btn_fin.setText("Volver a empezar");
        }


        btn_fin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //aquí cerramos el actícity actual
                if (puntuacion >= aprobado) {
                    finish();
                    //creamos un nuevo intent de action_main para el cierre de todo lo que esté abierto
                    Intent intent = new Intent(Intent.ACTION_MAIN);
                    intent.addCategory(Intent.CATEGORY_HOME);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
                else
                {
                    Intent inicio = new Intent (PuntuacionActivity.this, MainActivity.class);
                    startActivity(inicio);
                    finish();
                }
            }
        });
    }

}