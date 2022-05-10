package com.example.proyectopie;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import java.util.ArrayList;

public class ActivityPreguntas extends AppCompatActivity {

    private int numPregunta=0, aciertos=0;
    TextView preguntaTxt;
    RadioButton rbResp1, rbResp2, rbResp3;
    RadioGroup rgGrupo;
    Button btn_comprobar;
    String respuestaCorrecta, strResp;
    String nombre; //nombre insertado por el usuario en la activityyPrincipal
    ArrayList<Pregunta> preguntas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        preguntas = new ArrayList<>();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preguntas);

        Toolbar myToolbar = findViewById(R.id.my_toolbar);


        Intent intent = getIntent();
        nombre = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        preguntas = (ArrayList<Pregunta>) intent.getSerializableExtra("listaPreguntas");
        myToolbar.setTitle("Adelante "+ nombre);
        setSupportActionBar(myToolbar);

        preguntaTxt = findViewById(R.id.tv_pregunta);
        rbResp1 = findViewById(R.id.rbResp1);
        rbResp2 = findViewById(R.id.rbResp2);
        rbResp3 = findViewById(R.id.rbResp3);
        rgGrupo = findViewById(R.id.radioGroup);
        mostrarPregunta();

        btn_comprobar = findViewById(R.id.btnComprobar);
        btn_comprobar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(respuestaCorrecta.equalsIgnoreCase(strResp)){
                    aciertos++;
                }
                numPregunta++;
                rgGrupo.clearCheck();
                mostrarPregunta();

            }
        });
    }

    protected void mostrarPregunta(){
        //Hemos de intentar que esto funcione en random

        if (preguntas.size()>numPregunta){
            preguntaTxt.setText(preguntas.get(numPregunta).getEnunciado());

            rbResp1.setText(preguntas.get(numPregunta).getResp1());
            rbResp2.setText(preguntas.get(numPregunta).getResp2());
            rbResp3.setText(preguntas.get(numPregunta).getResp3());
            respuestaCorrecta = preguntas.get(numPregunta).getSolucion();

        }else{
            Intent mostrarPuntuacion = new Intent (ActivityPreguntas.this, PuntuacionActivity.class);
            mostrarPuntuacion.putExtra("nombre",nombre);
            mostrarPuntuacion.putExtra("puntos", aciertos);
            startActivity(mostrarPuntuacion);
            finish();
           // preguntaTxt.setText("NO hay mas preguntas "+ nombre +" has acertado: " +aciertos);
        }
    }

    /*Lo utilizamos para guardar la respuesta marcada y asi despues poder comprobar si es la correcta*/
    public void onRadioBtnClik(View view){
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();
        Toast toast = null;
        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.rbResp1:
                if (checked)
                  strResp= (String)rbResp1.getText();
                break;
            case R.id.rbResp2:
                if (checked)
                    strResp= (String)rbResp2.getText();
                break;
            case R.id.rbResp3:
                if (checked)
                    strResp= (String)rbResp3.getText();
                break;
        }
    }



}
