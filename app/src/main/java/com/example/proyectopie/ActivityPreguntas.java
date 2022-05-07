package com.example.proyectopie;

import android.content.Intent;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;

public class ActivityPreguntas extends AppCompatActivity {
    private BaseDatos baseDeDatos;
    private int numPregunta=0, aciertos=0;
    TextView preguntaTxt, txtPrueba;
    RadioButton rbResp1, rbResp2, rbResp3;
    RadioGroup rgGrupo;
    Button btn_comprobar;
    String respuestaCorrecta, strResp;
    String nombre; //nombre insertado por el usuario en la activityyPrincipal
    ArrayList<Pregunta> preguntas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //baseDeDatos=new BaseDatos();
        //baseDeDatos.cargarDatos();
        preguntas = new ArrayList<>();
        cargarPreguntas();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preguntas);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);


        Intent intent = getIntent();
        nombre = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        myToolbar.setTitle("Adelante "+ nombre);
        setSupportActionBar(myToolbar);

        preguntaTxt = findViewById(R.id.tv_pregunta);
        rbResp1 = findViewById(R.id.rbResp1);
        rbResp2 = findViewById(R.id.rbResp2);
        rbResp3 = findViewById(R.id.rbResp3);
        mostrarPregunta();

        btn_comprobar = (Button) findViewById(R.id.btnComprobar);
        btn_comprobar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(respuestaCorrecta==strResp){
                    aciertos++;
                }
                numPregunta++;
                rbResp1.setChecked(false);
                rbResp2.setChecked(false);
                rbResp3.setChecked(false);
                mostrarPregunta();

            }
        });

        //txtPrueba=findViewById(R.id.txtPrueba);

    }

    protected void mostrarPregunta(){
        //Hemos de intentar que esto funcione en random

        if (preguntas.size()>numPregunta){
            preguntaTxt.setText(preguntas.get(numPregunta).getEnunciado());

            rbResp1.setText(preguntas.get(numPregunta).getResp1());
            rbResp2.setText(preguntas.get(numPregunta).getResp2());
            rbResp3.setText(preguntas.get(numPregunta).getResp3());
            respuestaCorrecta = preguntas.get(numPregunta).getSolucion();

        }else{/*
            Intent mostrarPuntuacion = new Intent (TestActivity.this, PuntuacionActivity.class);
            mostrarPuntuacion.putExtra("nombre",nombreJugador);
            mostrarPuntuacion.putExtra("puntos", puntuacion);
            startActivity(mostrarPuntuacion);
            finish();*/
            preguntaTxt.setText("NO hay mas preguntas "+ nombre +" has acertado: " +aciertos);
        }
    }

    public void cargarPreguntas() {


        //ArrayList<Pregunta> preguntas = new ArrayList<>();

        XmlResourceParser xrp = getResources().getXml(R.xml.cuestions);

        try {
            int eventType = xrp.getEventType();
            while (eventType != XmlResourceParser.END_DOCUMENT)
            {
                System.out.println("entramos en el while");
                if (eventType == XmlResourceParser.START_TAG) {
                    if (xrp.getName().equalsIgnoreCase("pregunta"))
                    {
                        Pregunta pre = new Pregunta();
                        xrp.next(); //nos movemos al siguiente nodo
                        if (xrp.getName() != null && xrp.getName().equalsIgnoreCase("enunciado")) {
                            eventType = xrp.next();
                            if(eventType == XmlResourceParser.TEXT) {
                                System.out.println("dentro de .text.............. "+xrp.getText());
                                pre.setEnunciado(xrp.getText());
                                eventType = xrp.next();
                                eventType = xrp.next();
                            }
                        }
                        if (xrp.getName() != null && xrp.getName().equalsIgnoreCase("resp1")) {
                            eventType = xrp.next();
                            if(eventType == XmlResourceParser.TEXT) {
                                System.out.println("dentro de .text.............. "+xrp.getText());
                                pre.setResp1(xrp.getText());
                                eventType = xrp.next();
                                eventType = xrp.next();
                            }
                        }
                        if (xrp.getName() != null && xrp.getName().equalsIgnoreCase("resp2")) {
                            eventType = xrp.next();
                            if(eventType == XmlResourceParser.TEXT) {
                                System.out.println("dentro de .text.............. "+xrp.getText());
                                pre.setResp2(xrp.getText());
                                eventType = xrp.next();
                                eventType = xrp.next();
                            }
                        }
                        if (xrp.getName() != null && xrp.getName().equalsIgnoreCase("resp3")) {
                            eventType = xrp.next();
                            if(eventType == XmlResourceParser.TEXT) {
                                System.out.println("dentro de .text.............. "+xrp.getText());
                                pre.setResp3(xrp.getText());
                                eventType = xrp.next();
                                eventType = xrp.next();
                            }
                        }
                        if (xrp.getName() != null && xrp.getName().equalsIgnoreCase("solucion")) {
                            eventType = xrp.next();
                            if(eventType == XmlResourceParser.TEXT) {
                                System.out.println("dentro de .text.............. "+xrp.getText());
                                pre.setSolucion(xrp.getText());
                                eventType = xrp.next();
                            }
                        }
                        preguntas.add(pre);
                    }
                }
                eventType = xrp.next();
            }
        } catch (XmlPullParserException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }


        /************************************************************************************/
        System.out.println("/*********************************************/");
        System.out.println("hay " + preguntas.size() + " preguntas");

        for (Pregunta pr : preguntas) {
            System.out.println(pr.toString());
        }
                    /*for(int i=0;i<enunciadoList.size();i++){
                        System.out.println(enunciadoList.get(i));
                        auxResp= (String[]) respuestasList.get(i);
                        for(int j=0;j<auxResp.length;j++){
                          System.out.println(auxResp[j]);
                       }
                    }*/
        System.out.println("/*********************************************/");
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
