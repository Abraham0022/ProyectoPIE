package com.example.proyectopie;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

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
        //baseDeDatos=new BaseDatos();
        //baseDeDatos.cargarDatos();
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
        //cargarPregunta();

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
                //cargarPregunta();

            }
        });

        //txtPrueba=findViewById(R.id.txtPrueba);

    }/*
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
            finish();*
            preguntaTxt.setText("NO hay mas preguntas "+ nombre +" has acertado: " +aciertos);
        }
    }*/

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

public void cargarPreguntas() {


            XmlResourceParser xrp = getResources().getXml(R.xml.cuestions);
            ArrayList enunciadoList = new ArrayList();
            ArrayList respuestasList = new ArrayList();
            String auxResp[]= new String[3];

            try {

                int eventType = xrp.getEventType();

                while (eventType != XmlPullParser.END_DOCUMENT) {
                    if (eventType == XmlPullParser.START_DOCUMENT) {
                        //System.out.println("Start document");
                    } else if (eventType == XmlPullParser.START_TAG) {
                      //  System.out.println("Start tag " + xrp.getName());
                        if("enunciado".equalsIgnoreCase(xrp.getName())){
                          //  System.err.println("ENUNCIADO");
                           while(eventType!= XmlPullParser.TEXT)
                               eventType = xrp.next();
                            enunciadoList.add(xrp.getText());
                            System.out.println("*");
                        }else if("resp1".equalsIgnoreCase(xrp.getName())){
                            while(eventType!= XmlPullParser.TEXT)
                                eventType = xrp.next();
                            auxResp[0]=xrp.getText();
                        }else if("resp2".equalsIgnoreCase(xrp.getName())){
                            while(eventType!= XmlPullParser.TEXT)
                                eventType = xrp.next();
                            auxResp[1]=xrp.getText();

                        }else if("resp3".equalsIgnoreCase(xrp.getName())){
                            while(eventType!= XmlPullParser.TEXT)
                                eventType = xrp.next();
                            auxResp[2]=xrp.getText();
                            respuestasList.add(auxResp);
                        }

                    } else if (eventType == XmlPullParser.END_TAG) {
                        //System.out.println("End tag " + xrp.getName());
                    } else if (eventType == XmlPullParser.TEXT) {
                      //  System.out.println("Text " + xrp.getText());
                    }
                   // respuestasList.add(auxResp);
                    eventType = xrp.next();
                }
/************************************************************************************/
                System.out.println("/*********************************************/");


                for(int i=0;i<enunciadoList.size();i++){
                    System.out.println(enunciadoList.get(i));
                    auxResp= (String[]) respuestasList.get(i);
                    for(int j=0;i<auxResp.length;j++){
                      System.out.println(auxResp[j]);
                   }
                }
                System.out.println("/*********************************************/");

            } catch (XmlPullParserException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (IOException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
 }
