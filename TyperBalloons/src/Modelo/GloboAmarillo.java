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
import javafx.scene.paint.Color;

/**
 *
 * @author Valeria Barzola
 */
public class GloboAmarillo extends Globo{
    
    private final ImageView globoamarilloim;
    
   /**
    * Constructor del globo amarillo que setea la imagen definida para este globo y añade las letras creadas
    */
    public GloboAmarillo(){ 
        letralabel = new Label();
        Image img = new Image(getClass().getResourceAsStream(
                        CONSTANTES.RUTA_IMGS+"Globo_Amarillo.png"),
                                170,
                                270,
                                true,
                                true);
        globoamarilloim= new ImageView(img);
        String s = Globo.generarLetra().toUpperCase();
        letras.add(s);
        this.getChildren().addAll(globoamarilloim,letralabel);  
}

    
}
