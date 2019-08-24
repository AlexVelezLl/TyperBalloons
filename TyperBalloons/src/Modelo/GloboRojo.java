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
 * @author PC
 */
public class GloboRojo extends Globo {
    private final ImageView globorojo;
    private Label letra;
    ArrayList<String> letras = new ArrayList<>();
    public GloboRojo(){  
        
        Image img = new Image(getClass().getResourceAsStream(
                        CONSTANTES.RUTA_IMGS+"Globo_Rojo.png"),
                                150,
                                250,
                                true,
                                true);
        
        int numLetras = (int) (Math.random() * 2) + 2;
        for (int i=1;i<=numLetras;i++){
            String s = Globo.generarLetra();
            letras.add(s);
        }
        String p="";
        for(String s: letras){
             p+=s;
        }
        letra =  new Label(p);
        Font theFont = Font.font(
                "Helvetica", FontWeight.BOLD, 28);        
        letra.setFont(theFont);
        letra.setTextFill(Color.web("#FFFFFF"));
        letra.setLayoutY(15);
        letra.setLayoutX(24);
        globorojo = new ImageView(img);
        
        this.getChildren().addAll(globorojo,letra);
}

  
}
