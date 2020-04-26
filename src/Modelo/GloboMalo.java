/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import utilities.CONSTANTES;

/**
 *
 * @author Valeria Barzola
 */
public class GloboMalo extends Globo {
    private final ImageView globomaloim;
    
    /**
    * Constructor del globo malo que setea la imagen definida para este globo y añade las letras creadas
    */
    public GloboMalo(){
        letralabel = new Label();
        Image img = new Image(getClass().getResourceAsStream(
                        CONSTANTES.RUTA_IMGS+"Globo_Malo.png"),
                                170,
                                270,
                                true,
                                true);
        globomaloim= new ImageView(img);
        String s = Globo.generarLetra();        
        letras.add(s);
        añadirLetras(letras,30,40);
        this.getChildren().addAll(globomaloim,letralabel);  
        
    }
    
}
