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
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 *
 * @author PC
 */
public class GloboVerde extends Globo {
    private final ImageView globoverde;
    
    public GloboVerde(){               
        Image img = new Image(getClass().getResourceAsStream(
                        CONSTANTES.RUTA_IMGS+"Globo_Verde.png"),
                                150,
                                250,
                                true,
                                true);
        String s = Globo.generarLetra();
        setLetra(s);
        globoverde = new ImageView(img);        
        this.getChildren().addAll(globoverde,letra);
    }

    
   
    
}
