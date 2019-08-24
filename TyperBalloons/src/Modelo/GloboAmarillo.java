/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import utilities.CONSTANTES;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 *
 * @author PC
 */
public class GloboAmarillo extends Globo{
    private final ImageView globoamarillo;
    
   
    public GloboAmarillo(){                        
        Image img = new Image(getClass().getResourceAsStream(
                        CONSTANTES.RUTA_IMGS+"Globo_Amarillo.png"),
                                150,
                                250,
                                true,
                                true);
        globoamarillo= new ImageView(img);
        String s = Globo.generarLetra().toUpperCase();
        setLetra(s);
        this.getChildren().addAll(globoamarillo,letra);  
}

    
    
    public boolean GloboEmpty(){
        if (letra.getText().trim().isEmpty()) {
            return true;
            
        }
        return false;
    }     
    
}
