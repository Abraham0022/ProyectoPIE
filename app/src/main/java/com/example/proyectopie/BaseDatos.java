package com.example.myapplication;

public class BaseDatos {
        private String preguntas[] = {
                "Elemento que permite el almacenamiento de información de forma transitoria",
                "Elemento principal del ordenador que realiza los cálculos",
                "Dispositivo que permite mostrar imagenees en pantalla",
                "La información dentro del ordenador viaja por ",
                "En redes una topologia física se corresponde con",
                "en un cable de par trenzado, que tipo de señales se utilizan"
        };
        private String respuestasPosibles[][] = {
                {"RAM","VGA","CPU"},
                {"RAM","VGA","CPU"},
                {"RAM","VGA","CPU"},
                {"ROM","BUS","Placa Base"},
                {"Como piensan los equipos que están conectados","Como se conectan los equipos realmente","el tipo de tecnología que utilizo para conectarlos"},
                {"Impulsos eléctricos","Pulsos luminosos","Ondas de radio"}
        };
        private String respuestasCorrectas[] = {
                "RAM",
                "CPU",
                "VGA",
                "BUS",
                "Como se conectan los equipos realmente",
                "Impulsos eléctricos"
        };

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
