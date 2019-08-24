/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import Vistas.TyperBalloons;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import utilities.CONSTANTES;

/**
 *
 * @author CORE i7 ULTIMATE
 */
public class Controlador {
    public static boolean sondEsp;
    public static double volumen;
    public static ImageView bGIndex;
    public static ImageView bGGame;
    public static int skin;
    public static Pane volumenControl(){
        ImageView barra = new ImageView(new Image(CONSTANTES.RUTA_IMGS+"Bar.png"));
        ImageView cont = new ImageView(new Image(CONSTANTES.RUTA_IMGS+"BorderBar.png"));
        ImageView circ = new ImageView(new Image(CONSTANTES.RUTA_IMGS+"Circle.png"));
        ImageView barraC = new ImageView(new Image(CONSTANTES.RUTA_IMGS+"BrownBar.png"));
        barraC.setFitWidth(600-TyperBalloons.mediaPlayer.getVolume()*600);
        barraC.setLayoutX(592-(1-TyperBalloons.mediaPlayer.getVolume())*600);
    
        Pane volumen = new Pane();
        
        Pane pcirc = new Pane();
        pcirc.getChildren().add(circ);
        circ.setLayoutX((TyperBalloons.mediaPlayer.getVolume()*600)-33);
        pcirc.setOnMouseDragged((e)->{
            
            if((e.getSceneX()-133)>=-33 && (e.getSceneX()-133)<=567){
                barraC.setFitWidth(592-(circ.getLayoutX()+33));
                barraC.setLayoutX((circ.getLayoutX()+33));
                circ.setLayoutX(e.getSceneX()-133);
                TyperBalloons.mediaPlayer.setVolume((circ.getLayoutX()+33)/600);
            }
        });
        volumen.getChildren().addAll(barra,barraC,cont,pcirc);
        return volumen;
    }
}
