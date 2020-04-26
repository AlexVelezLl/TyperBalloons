/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * Clase donde se definen constantes que seran de gran utilidad a lo largo de 
 * la ejecucion de la aplicacion
 * @author Alex Velez
 */
public final class CONSTANTES {
    public static final String RUTA_IMGS = "/Recursos/imagenes/";
    public static final String RUTA_SOUNDS = "src/Recursos/sonidos/";
    public static final double WIDTH =800;
    public static final double HEIGHT =600;
    public static final Font FUENTE = Font.font("Arial", FontWeight.BOLD, 45 );
    public static final Font FUENTE2 = Font.font("Comic Sans MS",FontWeight.BOLD,30);
    public static final String GAMEDATA= "src/data/scores";
    public static final Font FUENTEJ= Font.font("Helvetica", FontWeight.BOLD,24);
    public static final Font FUENTEJ2= Font.font("Helvetica", FontWeight.BOLD,32);

    private CONSTANTES(){}
}
