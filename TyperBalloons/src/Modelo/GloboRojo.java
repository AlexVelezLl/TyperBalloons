/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import utilities.CONSTANTES;
import java.util.ArrayList;
import java.util.Random;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 *
 * @author Valeria Barzola
 */
public class GloboRojo extends Globo {
    private final ImageView globorojo;
    
    /**
    * Constructor del globo rojo que setea la imagen definida para este globo y añade las letras creadas
    */
    public GloboRojo(){  
        letralabel= new Label();
        
        Image img = new Image(getClass().getResourceAsStream(
                        CONSTANTES.RUTA_IMGS+"Globo_Rojo.png"),
                                170,
                                270,
                                true,
                                true);
        
        int numLetras = (int) (Math.random() * 2) + 2;
        for (int i=1;i<=numLetras;i++){
            String s = Globo.generarLetra();
            letras.add(s);
        }
        
        añadirLetras(letras,22,20);
     
        globorojo = new ImageView(img);
        
        this.getChildren().addAll(globorojo,letralabel);
}
    
    
    
    

  
}
