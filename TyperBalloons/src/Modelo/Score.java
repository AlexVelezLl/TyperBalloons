/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author CORE i7 ULTIMATE
 */
public class Score implements Comparable<Score>{
    public String nombre;
    public int puntaje;
    
    
    public Score(){
        nombre = null;
        puntaje = 0;
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
       return Integer.compare(puntaje, o.puntaje);
    }

}



