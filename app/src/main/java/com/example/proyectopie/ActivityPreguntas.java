package com.example.proyectopie;

import androidx.annotation.ColorInt;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class ActivityPreguntas extends AppCompatActivity {
    private BaseDatos baseDeDatos;
    private int numPregunta=0, aciertos=0;
    TextView preguntaTxt, txtPrueba;
    RadioButton rbResp1, rbResp2, rbResp3;
    RadioGroup rgGrupo;
    Button btn_iniciar;
    String respuestaCorrecta, strResp;
    String nombre; //nombre insertado por el usuario en la activityyPrincipal

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        baseDeDatos=new BaseDatos();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preguntas);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);


        Intent intent = getIntent();
        nombre = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        myToolbar.setTitle("Adelante "+ nombre);
        myToolbar.setTitleTextColor(ContextCompat.getColor(getBaseContext(),R.color.white)); //poner el texto del toolbar de color blanco
        setSupportActionBar(myToolbar);

        preguntaTxt = findViewById(R.id.tv_pregunta);
        rbResp1 = findViewById(R.id.rbResp1);
        rbResp2 = findViewById(R.id.rbResp2);
        rbResp3 = findViewById(R.id.rbResp3);
        cargarPregunta();

        btn_iniciar = (Button) findViewById(R.id.btnComprobar);
        btn_iniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(respuestaCorrecta==strResp){
                    aciertos++;
                }
                numPregunta++;
                rbResp1.setChecked(false);
                rbResp2.setChecked(false);
                rbResp3.setChecked(false);
                cargarPregunta();

            }
        });

        //txtPrueba=findViewById(R.id.txtPrueba);

    }
    protected void cargarPregunta(){
        //Hemos de intentar que esto funcione en random

        if (baseDeDatos.cantidadPreguntas()>numPregunta){
            preguntaTxt.setText(baseDeDatos.obtenerPregunta(numPregunta));

            rbResp1.setText(baseDeDatos.obtenerRespuesta1(numPregunta));
            rbResp2.setText(baseDeDatos.obtenerRespuesta2(numPregunta));
            rbResp3.setText(baseDeDatos.obtenerRespuesta3(numPregunta));
            respuestaCorrecta = baseDeDatos.obtenerRespuestaCorrecta(numPregunta);

        }else{
         /*   Intent mostrarPuntuacion = new Intent (TestActivity.this, PuntuacionActivity.class);
            mostrarPuntuacion.putExtra("nombre",nombreJugador);
            mostrarPuntuacion.putExtra("puntos", puntuacion);
            startActivity(mostrarPuntuacion);
            finish();*/
            preguntaTxt.setText("NO hay mas preguntas "+ nombre +" has acertado: " +aciertos);
        }
    }

    public void onRadioBtnClik(View view){
          // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();
    Toast toast = null;
        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.rbResp1:
                if (checked)
                    // Pirates are the best
                    strResp= (String)rbResp1.getText();
                    //txtPrueba.setText(aciertos);
                    break;
            case R.id.rbResp2:
                if (checked)
                    strResp= (String)rbResp2.getText();
               // txtPrueba.setText(aciertos);
                    break;
            case R.id.rbResp3:
                if (checked)
                    strResp= (String)rbResp3.getText();
                //    txtPrueba.setText(aciertos);
                    break;
        }
    }
}