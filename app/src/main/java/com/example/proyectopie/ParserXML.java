package com.example.proyectopie;

import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ParserXML {
  // Namespace general. null si no existe
    private static final String ns = null;

    // Constantes del archivo Xml

    private static final String ETI_CUESTIONES = "cuestiones";
    private static final String ETI_PREGUNTA= "pregunta";
    private static final String ETI_ENUNCIADO= "enunciado";
    private static final String ETI_RESP1= "resp1";
    private static final String ETI_RESP2= "resp2";
    private static final String ETI_RESP3= "resp3";
    private static final String ETI_SOLU= "solucion";


    /**
     * Parsea un flujo XML a una lista de objetos {@link Pregunta}
     *
     * @param in flujo
     * @return Lista de preguntas
     * @throws XmlPullParserException
     * @throws IOException
     */
    public static List<Pregunta> parsear(InputStream in) throws XmlPullParserException, IOException {
        try {
            XmlPullParser parser = Xml.newPullParser();
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, true);
            parser.setInput(in, null);
            parser.nextTag();
            return leerPreguntas(parser);
        } finally {
            in.close();
        }
    }

    /**
     * Convierte una serie de etiquetas <pregunta> en una lista
     *
     * @param parser
     * @return lista de preguntas
     * @throws XmlPullParserException
     * @throws IOException
     */
    private static List<Pregunta> leerPreguntas(XmlPullParser parser)
            throws XmlPullParserException, IOException {
        List<Pregunta> listaPreguntas = new ArrayList<Pregunta>();

        parser.require(XmlPullParser.START_TAG, ns, ETI_CUESTIONES);
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String nombreEtiqueta = parser.getName();
            // Buscar etiqueta <Pregunta>
            if (nombreEtiqueta.equals(ETI_PREGUNTA)) {
                listaPreguntas.add(leerPregunta(parser));
            } else {
                saltarEtiqueta(parser);
            }
        }
        return listaPreguntas;
    }
    /**
     * Convierte una etiqueta <pregunta> en un objero Pregunta
     *
     * @param parser parser XML
     * @return nuevo objeto Pregunta
     * @throws XmlPullParserException
     * @throws IOException
     */
    private static Pregunta leerPregunta(XmlPullParser parser) throws XmlPullParserException, IOException {
        parser.require(XmlPullParser.START_TAG, ns, ETI_PREGUNTA);
        String enunciado = null;
        String resp1 = null;
        String resp2 = null;
        String resp3 = null;
        String solucion = null;

        //   HashMap<String, String> valoracion = new HashMap<>();

        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();

            switch (name) {
                case ETI_ENUNCIADO:
                    enunciado = leerEnunciado(parser);
                    break;
                case ETI_RESP1:
                    resp1 = leerResp1(parser);
                    break;
                case ETI_RESP2:
                    resp2 = leerResp2(parser);
                    break;
                case ETI_RESP3:
                    resp3=leerResp3(parser);
                    break;
                case ETI_SOLU:
                    solucion = leerSolucion(parser);
                    break;
                default:
                    saltarEtiqueta(parser);
                    break;
            }
        }


        return new Pregunta(enunciado,resp1,resp2,resp3,solucion);
    }

    // Procesa la etiqueta <enunciado> de las preguntas
    private static String leerEnunciado(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, ETI_ENUNCIADO);
        String enunciado = obtenerTexto(parser);
        parser.require(XmlPullParser.END_TAG, ns, ETI_ENUNCIADO);
        return enunciado;
    }

    // Procesa la etiqueta <resp1> de las preguntas
    private static String leerResp1(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, ETI_RESP1);
        String respuesta = obtenerTexto(parser);
        parser.require(XmlPullParser.END_TAG, ns, ETI_RESP1);
        return respuesta;
    }
    // Procesa la etiqueta <resp2> de las preguntas
    private static String leerResp2(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, ETI_RESP2);
        String respuesta = obtenerTexto(parser);
        parser.require(XmlPullParser.END_TAG, ns, ETI_RESP2);
        return respuesta;
    }
    // Procesa la etiqueta <resp3> de las preguntas
    private static  String leerResp3(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, ETI_RESP3);
        String respuesta = obtenerTexto(parser);
        parser.require(XmlPullParser.END_TAG, ns, ETI_RESP3);
        return respuesta;
    }
    // Procesa la etiqueta <solucion> de las preguntas
    private static String leerSolucion(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, ETI_SOLU);
        String respuesta = obtenerTexto(parser);
        parser.require(XmlPullParser.END_TAG, ns, ETI_SOLU);
        return respuesta;
    }
    /*

    // Procesa las etiqueta <urlImagen> de los hoteles
    private String leerUrlImagen(XmlPullParser parser) throws IOException, XmlPullParserException {
        String urlImagen;
        parser.require(XmlPullParser.START_TAG, ns, ETIQUETA_URL_IMAGEN);
        urlImagen = obtenerTexto(parser);
        parser.require(XmlPullParser.END_TAG, ns, ETIQUETA_URL_IMAGEN);
        return urlImagen;
    }

    */
    // Obtiene el texto de los atributos
    private static  String obtenerTexto(XmlPullParser parser) throws IOException, XmlPullParserException {
        String resultado = "";
        if (parser.next() == XmlPullParser.TEXT) {
            resultado = parser.getText();
            parser.nextTag();
        }
        return resultado;
    }

    // Salta aquellos objeteos que no interesen en la jerarquía XML.
    private static void saltarEtiqueta(XmlPullParser parser) throws XmlPullParserException, IOException {
        if (parser.getEventType() != XmlPullParser.START_TAG) {
            throw new IllegalStateException();
        }
        int depth = 1;
        while (depth != 0) {
            switch (parser.next()) {
                case XmlPullParser.END_TAG:
                    depth--;
                    break;
                case XmlPullParser.START_TAG:
                    depth++;
                    break;
            }
        }
    }
}
