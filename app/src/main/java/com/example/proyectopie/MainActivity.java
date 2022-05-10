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
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    Button btn_iniciar;
    ArrayList<Pregunta> preguntas= new ArrayList<>() ;
    public static final String EXTRA_MESSAGE = "com.example.proyectopie.MESSAGE";
    private final static String URL = "https://proyectopie.000webhostapp.com/PIE/preguntas.xml";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_iniciar = (Button) findViewById(R.id.btn_iniciar);
        //ejecutamos el hilo

        new TareaDescargaXml().execute(URL);

        System.out.println("***************************************el arrya de preguntas tiene.................. "+Pregunta.PREGUNTAS.size());

        if (preguntas.isEmpty())
        {
            btn_iniciar.setEnabled(false);
        }



        btn_iniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            /*    Intent empezarTest=new Intent(MainActivity.this, ActivityPreguntas.class);
                startActivity(empezarTest);*/
                empezarTest(v);
            }
        });
    }

    public void empezarTest(View v){

        EditText editText = (EditText) findViewById(R.id.et_nombre);
        String message = editText.getText().toString();
        Intent intent = new Intent(MainActivity.this, ActivityPreguntas.class);
        intent.putExtra(EXTRA_MESSAGE, message);
        intent.putExtra("listaPreguntas",preguntas);

        startActivity(intent);
    }

    private class TareaDescargaXml extends AsyncTask<String, Void, List<Pregunta>> {

        @Override
        protected List<Pregunta> doInBackground(String... urls) {
            try {
                return parsearXmlDeUrl(urls[0]);
            } catch (IOException e) {
                System.out.println("**********************ERROR RED*********"+e);
                return null; // null si hay error de red
            } catch (XmlPullParserException e) {
                System.err.println("*************ERROR PARCHEANDO XML*********"+e);
                return null; // null si hay error de parsing XM
            }
        }

        @Override
        protected void onPostExecute(List<Pregunta> result) {
            // Actualizar contenido del proveedor de datos
            preguntas = (ArrayList<Pregunta>) result;
            System.out.println("*****************************onPostExecute result vale:"+result);
            //Pregunta.PREGUNTAS = result;
            System.out.println("*****************************onPostExecute preguntas tiene:"+preguntas);
            // Actualizar la vista del adaptador
           // adaptador.notifyDataSetChanged();
            if ( preguntas.size() > 0 )
            {
                btn_iniciar.setEnabled(true);
            }
        }
    }

    private List<Pregunta> parsearXmlDeUrl(String urlString)
            throws XmlPullParserException, IOException {
        InputStream stream = null;
        ParserXML parserXml = new ParserXML();
        List<Pregunta> entries = null;

        try {
            stream = descargarContenido(urlString);
            entries = parserXml.parsear(stream);

        } finally {
            if (stream != null) {
                stream.close();
            }
        }

        return entries;
    }

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