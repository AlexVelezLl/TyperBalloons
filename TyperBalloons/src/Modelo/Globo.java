/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

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
 *
 * @author PC
 */
public abstract class Globo extends Pane {
    protected Label letra;
    private boolean isEmpty;
    public boolean onScreen;
   
   public Globo(){
       
       super();
       onScreen = true;
   }    
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
    public static String generarLetra(){
        Random r= new Random();
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        char a =alphabet.charAt(r.nextInt(alphabet.length()));
        String f = String.valueOf(a);
        return f;
    }
    
    public Label getLetra() {
        return letra;
    }
    
    public void setLetra(String s_letra){
        Font theFont = Font.font(
                "Helvetica", FontWeight.BOLD, 35 );
        Label l = new Label(s_letra);
        l.setFont(theFont);
        l.setTextFill(Color.web("#FFFFFF"));
        l.setLayoutY(15);
        l.setLayoutX(24);
        this.letra = l;
    }
    
    public void EliminarLetra(String letraObtenida){
        if (this.letra.getText().contains(letraObtenida)) {
            String s2= this.letra.getText().replace(letraObtenida, "");
            letra.setText(s2.trim());
            
            
        }

        
    }
    
    
    
    
}
