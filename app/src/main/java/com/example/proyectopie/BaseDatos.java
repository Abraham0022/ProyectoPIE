package com.example.proyectopie;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;


public class BaseDatos {
    //private Pregunta[];


    public BaseDatos(){
            try {
                // creating a constructor of file class and
                // parsing an XML file
                File file = new File("preguntas.xml");
                System.out.println("Fichero cargado");
                // Defines a factory API that enables
                // applications to obtain a parser that produces
                // DOM object trees from XML documents.
               DocumentBuilderFactory dbf
                        = DocumentBuilderFactory.newInstance();

                // we are creating an object of builder to parse
                // the  xml file.
                DocumentBuilder db = dbf.newDocumentBuilder();
                Document doc = db.parse(file);


            /*here normalize method Puts all Text nodes in
            the full depth of the sub-tree underneath this
            Node, including attribute nodes, into a "normal"
            form where only structure separates
            Text nodes, i.e., there are neither adjacent
            Text nodes nor empty Text nodes. */
                doc.getDocumentElement().normalize();
                System.out.println(
                        "Root element: "
                                + doc.getDocumentElement().getNodeName());

                // Here nodeList contains all the nodes with
                // name geek.
                NodeList nodeList
                        = doc.getElementsByTagName("pregunta");

                // Iterate through all the nodes in NodeList
                // using for loop.
                for (int i = 0; i < nodeList.getLength(); ++i) {
                    Node node = nodeList.item(i);
                    System.out.println("\nNode Name :"
                            + node.getNodeName());
                    if (node.getNodeType()
                            == Node.ELEMENT_NODE) {
                        Element tElement = (Element)node;
                        System.out.println(
                                "enunciado: "
                                        + tElement
                                        .getElementsByTagName("enunciado")
                                        .item(0)
                                        .getTextContent());
                        System.out.println(
                                "resp1: "
                                        + tElement
                                        .getElementsByTagName(
                                                "resp1")
                                        .item(0)
                                        .getTextContent());
                        System.out.println(
                                "resp2: "
                                        + tElement
                                        .getElementsByTagName(
                                                "resp2")
                                        .item(0)
                                        .getTextContent());
                        System.out.println(
                                "resp3: "
                                        + tElement
                                        .getElementsByTagName("resp3")
                                        .item(0)
                                        .getTextContent());
                        System.out.println(
                                "solucion: "
                                        + tElement
                                        .getElementsByTagName(
                                                "solucion")
                                        .item(0)
                                        .getTextContent());
                    }
                }
            }

            // This exception block catches all the exception
            // raised.
            // For example if we try to access a element by a
            // TagName that is not there in the XML etc.
            catch (Exception e) {
                System.out.println(e);
            }
        }
}
