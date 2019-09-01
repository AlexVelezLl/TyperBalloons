/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.Serializable;

/**
 *
 * @author CORE i7 ULTIMATE
 */
public class Score implements Comparable<Score>,Serializable{
    public String nombre;
    public int puntaje;
    
    public Score(){
        nombre = null;
        puntaje=0;
    }
    
    public Score(String nombre, int puntaje){
        this.nombre = nombre;
        this.puntaje = puntaje;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }
    public String getNombre() {
        return nombre;
    }

    public int getPuntaje() {
        return puntaje;
    }


    @Override
    public int compareTo(Score o) {
        Integer punt = puntaje;
        int c = -1*(punt.compareTo(((Score)o).puntaje));
        if(c==0){
            c = nombre.compareTo(o.nombre);
            if (c==0){
                c = hashCode();
            }
        }
        return c;
    }


}



