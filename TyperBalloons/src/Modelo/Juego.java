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
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import utilities.Utilities;

/**
 *
 * @author joangie
 */
public class Juego {
    private HashMap<String, Integer> game_words;
    private HashMap<String,Integer> player_l;
    private Score player_score;
    private final Dificultad df; 

    public Dificultad getDf() {
        return df;
    }
    
    private  TreeMap<Dificultad,TreeSet<Score>> scoresF; 

    
    public Juego(Dificultad f){
        scoresF = new TreeMap(); 
        game_words= cargarPalabras();
        player_score= new Score(); 
        player_l = new HashMap();
        df= f;
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
    
    public TreeMap<Dificultad, TreeSet<Score>> getScoresF() {
        return scoresF;
    }
       
    public static String quitarAcento(String s){
    String temp = Normalizer.normalize(s, Normalizer.Form.NFD);
    Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
    return pattern.matcher(temp).replaceAll("");
    }
    public static int obtenerPuntos(String word){
       int wordPoints=0;
        HashMap<Integer, String> p_dict= new HashMap<>();
        //insertando arrays en dict
        p_dict.put(1,"aeoisnrult");
        p_dict.put(2,"dg");
        p_dict.put(3,"cbmp");
        p_dict.put(4,"hfvy");
        p_dict.put(5,"qkw");
        p_dict.put(8,"jlñx");
        p_dict.put(10, "z"); //ver si esto sale mejor con un for pero weno 
       //recorrer la palabra
       for (HashMap.Entry<Integer, String> p : p_dict.entrySet()) {
           for(int i=0; i<word.length();i++){
           String c = Character.toString(word.toLowerCase().charAt(i));
           String value = p.getValue();             
           if(value.contains(c)){
                wordPoints= wordPoints+p.getKey();
                }
           }   
        }
       return wordPoints;
       }
    public static HashMap<String, Integer> cargarPalabras() {
      HashMap<String, Integer> puntos_words = new HashMap<>();
        String nombre_archivo="espanol.csv";
       //diccionaio con puntos 
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
                    Logger.getLogger(Utilities.class.getName()).log(Level.SEVERE, null, ex);
                    utilities.Utilities.reportError(ex);

                }catch (IOException ex) {
                    Logger.getLogger(Utilities.class.getName()).log(Level.SEVERE, null, ex);
                     utilities.Utilities.reportError(ex);
                }
        return puntos_words;
    }
}
