package com.example.proyectopie;

public class BaseDatos {
        private String [] preguntas;
        private String [][] respuestasPosibles;
        private String [] respuestasCorrectas;

    public BaseDatos(String idioma) {
        if (idioma.equals("es") )
        {
            preguntas = new String[]{
                    "Elemento que permite el almacenamiento de información de forma transitoria",
                    "Elemento principal del ordenador que realiza los cálculos",
                    "Dispositivo que permite mostrar imagenees en pantalla",
                    "La información dentro del ordenador viaja por: ",
                    "En redes una topologia física se corresponde con:",
                    "En un cable de par trenzado, que tipo de señales se utilizan"};
            respuestasPosibles = new String[][]{
                    {"RAM", "VGA", "CPU"},
                    {"RAM", "VGA", "CPU"},
                    {"RAM", "VGA", "CPU"},
                    {"ROM", "BUS", "Placa Base"},
                    {"Como piensan los equipos que están conectados", "Como se conectan los equipos realmente", "el tipo de tecnología que utilizo para conectarlos"},
                    {"Impulsos eléctricos", "Pulsos luminosos", "Ondas de radio"}
            };

            respuestasCorrectas = new String[]{
                    "RAM",
                    "CPU",
                    "VGA",
                    "BUS",
                    "Como se conectan los equipos realmente",
                    "Impulsos eléctricos"
            };
        }
        if (idioma.equals("en") )
        {
            preguntas = new String[]{
                    "Element that allows the temporary storage of information",
                    "Main element of the computer that performs the calculations",
                    "Device that allows images to be displayed on the screen",
                    "The information inside the computer travels through: ",
                    "In networks, a physical topology corresponds to:",
                    "In a twisted pair cable, what kind of signals are used?"
            };
            respuestasPosibles = new String[][]{
                    {"RAM", "VGA", "CPU"},
                    {"RAM", "VGA", "CPU"},
                    {"RAM", "VGA", "CPU"},
                    {"ROM", "BUS", "Main board"},
                    {"How connected hardware think", "How devices are connected", "The type of technology I use to connect them"},
                    {"Electric impulses", "light pulses", "Radio waves"}
            };

            respuestasCorrectas = new String[]{
                    "RAM",
                    "CPU",
                    "VGA",
                    "BUS",
                    "How devices are connected",
                    "Electric impulses"
            };
        }
    }

    public String obtenerPregunta(int posicion){
            String pregunta = preguntas[posicion];
            return pregunta;
        }
        public String obtenerRespuesta1(int posicion){
            String respuesta1= respuestasPosibles[posicion][0];
            return respuesta1;
        }
        public String obtenerRespuesta2(int posicion){
            String respuesta2= respuestasPosibles[posicion][1];
            return respuesta2;
        }
        public String obtenerRespuesta3(int posicion){
            String respuesta3= respuestasPosibles[posicion][2];
            return respuesta3;
        }
        public String obtenerRespuestaCorrecta(int posicion){
            String respuestaCorrecta= respuestasCorrectas[posicion];
            return respuestaCorrecta;
        }
        public int cantidadPreguntas(){
            int cantidad= preguntas.length;
            return cantidad;
        }

}
