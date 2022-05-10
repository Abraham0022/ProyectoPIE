package com.example.proyectopie;

import android.content.Intent;
import android.content.res.XmlResourceParser;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class ActivityPreguntas extends AppCompatActivity {
    //private BaseDatos baseDeDatos;
    private int numPregunta=0, aciertos=0;
    TextView preguntaTxt;
    RadioButton rbResp1, rbResp2, rbResp3;
    RadioGroup rgGrupo;
    Button btn_comprobar;
    String respuestaCorrecta, strResp;
    String nombre; //nombre insertado por el usuario en la activityyPrincipal
    ArrayList<Pregunta> preguntas;
    Pregunta preguntaAux;
    private final static String URL =
            "https://proyectopie.000webhostapp.com/PIE/preguntas.xml";
          //  "C:\\ProyectoPIE\\preguntas.xml";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //baseDeDatos=new BaseDatos();
        //baseDeDatos.cargarDatos();
        preguntas = new ArrayList<>();

     /*   try {
            cargarPreguntas();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preguntas);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);


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

        btn_comprobar = (Button) findViewById(R.id.btnComprobar);
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

    public void cargarPreguntas() throws XmlPullParserException, IOException {


      //  XmlResourceParser xrp = getResources().getXml(R.xml.cuestions);
        //InputStream inps =descargarXML(URL);
/****************************************************************************/
        InputStream inps= getResources().openRawResource(R.raw.cuestions);
        preguntas=(ArrayList<Pregunta>) ParserXML.parsear(inps);
        for(Pregunta auxPregunta: preguntas){
            System.out.println("**> "+auxPregunta.toString());
        }
/*****************************************************************************/
        /*
        try {
            int eventType = xrp.getEventType();
            while (eventType != XmlResourceParser.END_DOCUMENT)
            {
                 if (eventType == XmlResourceParser.START_TAG) {
                    if (xrp.getName().equalsIgnoreCase("pregunta"))
                    {
                        Pregunta pre = new Pregunta();
                        xrp.next(); //nos movemos al siguiente nodo
                        if (xrp.getName() != null && xrp.getName().equalsIgnoreCase("enunciado")) {
                            eventType = xrp.next();
                            if(eventType == XmlResourceParser.TEXT) {

                                pre.setEnunciado(xrp.getText());
                                eventType = xrp.next();
                                eventType = xrp.next();
                            }
                        }
                        if (xrp.getName() != null && xrp.getName().equalsIgnoreCase("resp1")) {
                            eventType = xrp.next();
                            if(eventType == XmlResourceParser.TEXT) {

                                pre.setResp1(xrp.getText());
                                eventType = xrp.next();
                                eventType = xrp.next();
                            }
                        }
                        if (xrp.getName() != null && xrp.getName().equalsIgnoreCase("resp2")) {
                            eventType = xrp.next();
                            if(eventType == XmlResourceParser.TEXT) {

                                pre.setResp2(xrp.getText());
                                eventType = xrp.next();
                                eventType = xrp.next();
                            }
                        }
                        if (xrp.getName() != null && xrp.getName().equalsIgnoreCase("resp3")) {
                            eventType = xrp.next();
                            if(eventType == XmlResourceParser.TEXT) {

                                pre.setResp3(xrp.getText());
                                eventType = xrp.next();
                                eventType = xrp.next();
                            }
                        }
                        if (xrp.getName() != null && xrp.getName().equalsIgnoreCase("solucion")) {
                            eventType = xrp.next();
                            if(eventType == XmlResourceParser.TEXT) {

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
        }*/
    }

    @Nullable
    private InputStream descargarXML(String direccion) throws IOException {
        InputStream inps=null;
        URL url = new URL(direccion);
        URLConnection connection= url.openConnection();
        HttpURLConnection httpCon=(HttpURLConnection) connection;
        httpCon.setDoInput(true);
        httpCon.setRequestProperty("charset", "utf-8");
        int responseCode = httpCon.getResponseCode();
        if(responseCode !=  HttpURLConnection.HTTP_OK){
             inps= httpCon.getInputStream();
        }
        return inps;


        /***********************************************************************
        DownloadManager manager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
        Uri uri = Uri.parse("https://www.w3.org/WAI/ER/tests/xhtml/testfiles/resources/pdf/dummy.pdf");
        DownloadManager.Request request = new DownloadManager.Request(uri);
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE);
        long reference = manager.enqueue(request);
        /***********************************************************************/
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
