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
public class Score{
    public final String nombre;
    public final int puntaje;
    public Score(String nombre, int puntaje){
        this.nombre = nombre;
        this.puntaje = puntaje;
    }
    public String getNombre() {
        return nombre;
    }

    public int getPuntaje() {
        return puntaje;
    }

}
