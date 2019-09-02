/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.util.ArrayList;
import utilities.CONSTANTES;
import java.util.Random;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * Clase padre globo que presenta las caracteristicas generales de todos los tipos de globos
 * @author Valeria Barzola
 */
public abstract class Globo extends Pane {      
    public boolean onScreen; 
    protected Label letralabel;
    ArrayList<String> letras = new ArrayList<>();

    
   
   /**
    * Constructor que inicializa los globos con OnScreen true
    */
   public Globo(){
       
       super();
       onScreen = true;      
   }    
   /**
    * Metodo que fija la posición en la ventana de un globo
    * @param x double, posición
    */
    public void fijarPosicion(double x){
        this.setLayoutX(x);
        this.setLayoutY(CONSTANTES.HEIGHT);
    }

    
    
    public double getPosicionX(){
        return this.getLayoutX();
    }
    
    public double getPosicionY(){
        return this.getLayoutY();
    }
    
    /**
     * Metodo que genera una letra del abedecedario para ingresarla al globo
     * @return String letra obtenida
     */
    public static String generarLetra(){
        Random r= new Random();
        String alphabet = "abcd";
        char a =alphabet.charAt(r.nextInt(alphabet.length()));
        String f = String.valueOf(a);
        return f;
    }
    
    /**
     * Metodo que establece el label del globo con un arreglo de letras
     * @param array Letras a ingresar en el globo
     */
    public void añadirLetras(ArrayList<String> array, double x, double y){
         String p="";
        for(String s: array){
             p+=s;
        }        
        letralabel.setText(p);
        Font theFont = Font.font(
                "Helvetica", FontWeight.BOLD, 28);        
        letralabel.setFont(theFont);
        letralabel.setTextFill(Color.web("#FFFFFF"));
        letralabel.setLayoutY(y);
        letralabel.setLayoutX(x);
    }
    
    /**
     * Metodo que devuelve el array de letras de cada globo
     * @return ArrayList<String> perteneciente al globo
     */
    public ArrayList<String> getLetras() {
        return letras;
    }
    
 
    
}
