/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 *
 * @author CORE i7 ULTIMATE
 */
public class TestScore {
    public static void main(String [] args){
        TreeMap<Dificultad,Set<Score>> scoresMax = new TreeMap<>();
        
        TreeSet<Score> medio = new TreeSet<>();
        medio.add(new Score("ALEX",92));
        medio.add(new Score("ANDRES",112));
        medio.add(new Score("JOANGIE",122));
        medio.add(new Score("ALEX",80));
        medio.add(new Score("VALERIA",137));
        medio.add(new Score("ALEX",155));
        medio.add(new Score("JUANITA",90));
        medio.add(new Score("VALERIA",120));
        medio.add(new Score("JOANGIE",140));
        medio.add(new Score("ALEX",112));
        medio.add(new Score("JOANGIE",95));
        
        TreeSet<Score> facil = new TreeSet<>();
        facil.add(new Score("ALEX",82));
        facil.add(new Score("ANDRES",102));
        facil.add(new Score("JOANGIE",112));
        facil.add(new Score("ALEX",100));
        facil.add(new Score("VALERIA",125));
        facil.add(new Score("ALEX",115));
        facil.add(new Score("JUANITA",70));
        facil.add(new Score("VALERIA",80));
        facil.add(new Score("JOANGIE",80));
        facil.add(new Score("ALEX",122));
        facil.add(new Score("JOANGIE",110));
        
        
        TreeSet<Score> dificil = new TreeSet<>();
        dificil.add(new Score("ALEX",95));
        dificil.add(new Score("ANDRES",120));
        dificil.add(new Score("JOANGIE",172));
        dificil.add(new Score("ALEX",150));
        dificil.add(new Score("VALERIA",172));
        dificil.add(new Score("VALERIA",71));
        dificil.add(new Score("JUANITA",90));
        
        scoresMax.put(Dificultad.DIFICIL, dificil);
        
        
        scoresMax.put(Dificultad.FACIL, facil);
        scoresMax.put(Dificultad.MEDIO, medio);
        
        try(ObjectOutputStream ous = new ObjectOutputStream(new FileOutputStream("src/data/scores.dat"))){
            ous.writeObject(scoresMax);
        }catch(Exception ex){
            System.out.println("Hola");
        }
        
    }
}
