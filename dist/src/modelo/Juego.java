/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.Normalizer;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.regex.Pattern;

/**
 *
 * @author joangie
 */
public class Juego {
    private Map<String, Integer> gameWords;
    private SortedMap<String,Integer> playerL;
    private Score playerScore;
    private final Dificultad df; 

    public Dificultad getDf() {
        return df;
    }
    
    private  TreeMap<Dificultad,TreeSet<Score>> scoresF; 

    
    public Juego(Dificultad f)throws IOException{
        scoresF = new TreeMap(); 
        gameWords= cargarPalabras();
        playerScore= new Score(); 
        playerL = new TreeMap();
        df= f;
    }
    
    public Map<String, Integer> getGameWords() {
        return gameWords;
    }

    public SortedMap<String,Integer> getPlayerL() {
        return  playerL;
    }

    public Score getPlayerScore() {
        return playerScore;
    }
    
    public SortedMap<Dificultad, TreeSet<Score>> getScoresF() {
        return scoresF;
    }
       
    public static String quitarAcento(String s){
    String temp = Normalizer.normalize(s, Normalizer.Form.NFD);
    Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
    return pattern.matcher(temp).replaceAll("");
    }
    public static int obtenerPuntos(String word){
       int wordPoints=0;
        HashMap<Integer, String> pDict= new HashMap<>();
        //insertando arrays en dict
        pDict.put(1,"aeoisnrult");
        pDict.put(2,"dg");
        pDict.put(3,"cbmp");
        pDict.put(4,"hfvy");
        pDict.put(5,"qkw");
        pDict.put(8,"jlñx");
        pDict.put(10, "z"); //ver si esto sale mejor con un for pero weno 
       //recorrer la palabra
       for (HashMap.Entry<Integer, String> p : pDict.entrySet()) {
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
    public static Map<String, Integer> cargarPalabras() throws IOException{
      Map<String, Integer> puntosWords = new HashMap<>();
        String nombreArchivo="espanol.csv";
       //diccionaio con puntos 
        //ahora si lo cool
        //leyendo archivo
        BufferedReader bf = new BufferedReader(new FileReader("src/Data/"+nombreArchivo));
        String linea;
        while((linea = bf.readLine())!=null){
            linea = quitarAcento(linea);
            puntosWords.put(linea,(Integer)obtenerPuntos(linea)); 
        }  
              
        return puntosWords;
    }
}
