/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.ArrayList;
import java.util.List;
import utilities.CONSTANTES;
import java.util.Random;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * Clase padre globo que presenta las caracteristicas generales de todos los tipos de globos
 * @author Valeria Barzola
 */
public abstract class Globo extends Pane {      
    protected boolean onScreen; 
    protected Label letralabel;
    List letras = new ArrayList<>();

    public boolean isOnScreen() {
        return onScreen;
    }

    public void setOnScreen(boolean onScreen) {
        this.onScreen = onScreen;
    }

    
   
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
        String alphabet = "abcdefghijklmnñopqrstuvwxyz";
        char a =alphabet.charAt(r.nextInt(alphabet.length()));  
        return String.valueOf(a);
    }
 
    /**
     * Metodo que establece el label del globo con un arreglo de letras
     * @param array Letras a ingresar en el globo
     */
    public void añadirLetras(List<String> array, double x, double y){
        
         StringBuilder b = new StringBuilder();
        for(String s: array){
             b.append(s);
        } 
        String p=b.toString();
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
    public List getLetras() {
        return letras;
    }
    
 
    
}
