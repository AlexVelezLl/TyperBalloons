/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Modelo;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.Normalizer;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 *
 * @author joangie
 */
public class Juego {
    private HashMap<String, Integer> game_words;
    private HashMap<String,Integer> player_l;
    private Score player_score;
    private TreeMap<Dificultad,TreeSet<Score>> scoresF; 

    
    public Juego(){
        scoresF = new TreeMap(); 
        game_words= cargarPalabras();
        
    }

    public HashMap<String, Integer> getGame_words() {
        return game_words;
    }

    public HashMap getPlayer_l() {
        return  player_l;
    }

    public Score getPlayer_score() {
        return player_score;
    }
    
       
    public static String quitarAcento(String s) {
    s = Normalizer.normalize(s, Normalizer.Form.NFD);
    s = s.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
    return s;
    }
    
    public static int obtenerPuntos(String word){
       int wordPoints=0;
        HashMap<Integer, String> p_dict= new HashMap<>();
        
        //insertando arrays en dict
        p_dict.put(1,"AEOISNRULT");
        p_dict.put(2,"DG");
        p_dict.put(3,"CBMP");
        p_dict.put(4,"HFVY");
        p_dict.put(5,"CHQ");
        p_dict.put(8,"JLÑX");
        p_dict.put(10, "Z"); //ver si esto sale mejor con un for pero weno 
       //recorrer la palabra
       for(int i=0; i<word.length();i++){
           String c = Character.toString(word.toLowerCase().charAt(i));
           //verificando el puntaje del char
           for (Integer key : p_dict.keySet()) {
                 String value = p_dict.get(key);             
                    if(value.toLowerCase().contains(c)){
                        wordPoints= wordPoints+key;
                    }
           }   
        }
       return wordPoints;
       }
    
    public  HashMap<String, Integer> cargarPalabras() {
      HashMap<String, Integer> puntos_words = new HashMap<>();
        String nombre_archivo="espanol.csv";
        //diccionario con puntos 
        //ahora si lo cool
        //leyendo archivo
                try(BufferedReader bf = new BufferedReader(
                    new FileReader("src/Data/"+nombre_archivo))){
                    String linea;
                    while((linea = bf.readLine())!=null){
                        linea = quitarAcento(linea);
                        puntos_words.put(linea,(Integer)obtenerPuntos(linea)); 
                    }  
                }catch(FileNotFoundException ex){
                System.out.println("error :( ");

                }catch (IOException ex) {
                   System.out.println(ex.getMessage());

                }
        return puntos_words;
    }

    public TreeMap<Dificultad, TreeSet<Score>> getScoresF() {
        return scoresF;
    }
    
}