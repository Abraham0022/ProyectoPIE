package com.example.proyectopie;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Pregunta implements Serializable {
    private String enunciado;
    private String resp1;
    private String resp2;
    private String resp3;
    private String solucion;


    // Proveedor estático de datos para el adaptador
    public static List<Pregunta> PREGUNTAS = new ArrayList<>();
/*
    public static Pregunta getItem(int id) {
        for (Hotel item : HOTELES) {
            if (item.getIdHotel() == id) {
                return item;
            }
        }
        return null;
    }
*/

    public Pregunta(String enunciado, String resp1, String resp2, String resp3, String solucion) {
        this.enunciado = enunciado;
        this.resp1 = resp1;
        this.resp2 = resp2;
        this.resp3 = resp3;
        this.solucion = solucion;
    }

    public Pregunta(){
        this.enunciado = "";
        this.resp1 = "";
        this.resp2 = "";
        this.resp3 = "";
        this.solucion = "";
    }

    public String getEnunciado() {
        return enunciado;
    }

    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
    }

    public String getResp1() {
        return resp1;
    }

    public void setResp1(String resp1) {
        this.resp1 = resp1;
    }

    public String getResp2() {
        return resp2;
    }

    public void setResp2(String resp2) {
        this.resp2 = resp2;
    }

    public String getResp3() {
        return resp3;
    }

    public void setResp3(String resp3) {
        this.resp3 = resp3;
    }

    public String getSolucion() {
        return solucion;
    }

    public void setSolucion(String solucion) {
        this.solucion = solucion;
    }

    @Override
    public String toString() {
        return "Pregunta{ " +
                "enunciado='" + enunciado + '\'' +
                ", resp1='" + resp1 + '\'' +
                ", resp2='" + resp2 + '\'' +
                ", resp3='" + resp3 + '\'' +
                ", solucion='" + solucion + '\'' +
                '}';
    }
}
