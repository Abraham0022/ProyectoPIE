package com.example.proyectopie;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
/**
 * Controla la actividad principal
 * @author Ruben Mena Aparicio
 * @author Abraham Pérez Barrera
 * @version 1.0 05/2022
 */
public class MainActivity extends AppCompatActivity {

    Button btn_iniciar;
    ArrayList<Pregunta> preguntas= new ArrayList<>() ;
    public static final String EXTRA_MESSAGE = "com.example.proyectopie.MESSAGE";
    //direccion donde esta guardado el archivo XML
    private final static String URL = "https://proyectopie.000webhostapp.com/PIE/preguntas.xml";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_iniciar = findViewById(R.id.btn_iniciar);
        //ejecutamos el hilo
        new TareaDescargaXml().execute(URL);

        if (preguntas.isEmpty()) {
            btn_iniciar.setEnabled(false);
        }
        btn_iniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                empezarTest(v);
            }
        });
    }

    /**
     * Crea el intent que arrancará y pasara datos la Actividad @see ActovotyPreguntas
     * @param v
     */
    public void empezarTest(View v){
        EditText nombreTxt = findViewById(R.id.et_nombre);
        String nombreUsuario = nombreTxt.getText().toString();
        Intent actividadPreguntas = new Intent(MainActivity.this, ActivityPreguntas.class);

        actividadPreguntas.putExtra(EXTRA_MESSAGE, nombreUsuario);
        actividadPreguntas.putExtra("listaPreguntas", this.preguntas);
        startActivity(actividadPreguntas);
    }

    /**
     * Crea el hilo encargado de descargar el fichero XML
     */
    private class TareaDescargaXml extends AsyncTask<String, Void, List<Pregunta>> {

        @Override
        protected List<Pregunta> doInBackground(String... urls) {
            try {
                return parsearXmlDeUrl(urls[0]);
            } catch (IOException e) {
               return null; // null si hay error de red
            } catch (XmlPullParserException e) {
               return null; // null si hay error de parsing XMLL
            }
        }

        @Override
        protected void onPostExecute(List<Pregunta> result) {
            // Actualizar contenido del proveedor de datos
            preguntas = (ArrayList<Pregunta>) result;
          if ( preguntas.size() > 0 ){
                btn_iniciar.setEnabled(true);
          }
        }
    }

    /**
     * Traduce el fichero XML @see ParserXML a una lista de perguntas @see Preguntas
     * @param urlString Dirección donde se encuentra el archivo
     * @return Lista con las preguntas que contiene el XML
     * @throws XmlPullParserException si el fichero no se corresponde con la estructura necesaria
     * @throws IOException Si el fichero no se puede descargar
     */
    private List<Pregunta> parsearXmlDeUrl(String urlString) throws XmlPullParserException, IOException {
        InputStream stream = null;
        ParserXML parserXml = new ParserXML();
        List<Pregunta> preguntas = null;

        try {
            stream = descargarContenido(urlString);
            preguntas = parserXml.parsear(stream);

        } finally {
            if (stream != null) {
                stream.close();
            }
        }

        return preguntas;
    }

    /**
     * crea la conexión con la URL
     * @param urlString Dirección donde se encuentra el archivo
     * @return Un flujo de entrada con el contenido del fichero
     * @throws IOException
     */
    private InputStream descargarContenido(String urlString) throws IOException {
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setReadTimeout(10000);
        conn.setConnectTimeout(15000);
        conn.setRequestMethod("GET");
        conn.setDoInput(true);
        // Iniciar la petición
        conn.connect();
        return conn.getInputStream();
    }
}