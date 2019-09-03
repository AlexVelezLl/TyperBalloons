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

/**
 *
 * @author Valeria Barzola
 */
public class GloboVerde extends Globo {
    private final ImageView globoverdeim;
    
    /**
    * Constructor del globo Verde que setea la imagen definida para este globo y añade las letras creadas
    */
    public GloboVerde(){
        letralabel = new Label();
        Image img = new Image(getClass().getResourceAsStream(
                        CONSTANTES.RUTA_IMGS+"Globo_Verde.png"),
                                170,
                                270,
                                true,
                                true);
        String s = Globo.generarLetra();
        letras.add(s);
        añadirLetras(letras,25,20);
        globoverdeim = new ImageView(img);        
        this.getChildren().addAll(globoverdeim,letralabel); 
    }

    
   
    
}
