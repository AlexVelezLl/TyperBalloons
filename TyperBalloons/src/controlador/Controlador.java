/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import Vistas.TyperBalloons;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.media.MediaPlayer;
import utilities.CONSTANTES;

/**
 * Clase mediadora entre varios aspectos tecnicos del juego y el usuario.
 * @author Alex Velez
 */
public final class Controlador {
    
    private static boolean sondEsp;
    private static ImageView bGIndex;
    private static ImageView title;
    private static ImageView bGGame;
    private static int skin;
    private static MediaPlayer mediaPlayer;
    private static Scene sc;
    /**
     * Constructor vacio con modificador de acceso private para evitar que 
     * se puedan crear instancias de esta clase.
     */
    private Controlador(){}
    
    /**
     * Metodo que crea y estructura una interfaz para poder controlar el volumen 
     * de la aplicacion.
     * @return Pane con el diseño del controlador de volumen
     */
    public static Pane volumenControl(){
        ImageView barra = new ImageView(new Image(CONSTANTES.RUTA_IMGS+"Bar.png"));
        ImageView cont = new ImageView(new Image(CONSTANTES.RUTA_IMGS+"BorderBar.png"));
        ImageView circ = new ImageView(new Image(CONSTANTES.RUTA_IMGS+"Circle.png"));
        ImageView barraC = new ImageView(new Image(CONSTANTES.RUTA_IMGS+"BrownBar.png"));
        barraC.setFitWidth(600-mediaPlayer.getVolume()*600);
        barraC.setLayoutX(592-(1-mediaPlayer.getVolume())*600);
    
        Pane pVolumen = new Pane();
        
        Pane pcirc = new Pane();
        pcirc.getChildren().add(circ);
        circ.setLayoutX((mediaPlayer.getVolume()*600)-33);
        pcirc.setOnMouseDragged(e->{
            
            if((e.getSceneX()-133)>=-33 && (e.getSceneX()-133)<=567){
                barraC.setFitWidth(592-(circ.getLayoutX()+33));
                barraC.setLayoutX((circ.getLayoutX()+33));
                circ.setLayoutX(e.getSceneX()-133);
                mediaPlayer.setVolume((circ.getLayoutX()+33)/600);
            }
        });
        pVolumen.getChildren().addAll(barra,barraC,cont,pcirc);
        return pVolumen;
    }
    
    /**
     * Metodo que comprueba si la aplicacion debe tener o no sonidos especiales
     * @return valor de verdad de si el usuario desea sonidos especiales
     */
    public static boolean isSondEsp() {
        return sondEsp;
    }
    
    /**
     * Metodo que obtiene el imageView del background principal de la aplicacion
     * @return ImageView con el background principal de la aplicacion
     */
    public static ImageView getbGIndex() {
        return bGIndex;
    }
    
    /**
     * Metodo que obtiene el imageView con el titulo de TyperBalloons
     * @return Image view con el titulo de TyperBalloons
     */
    public static ImageView getTitle() {
        return title;
    }
    
    /**
     * Metodo que obtiene el imageView del background de la vista donde se ejecuta
     * el nuevo juego.
     * @return ImageView con el background de la pantalla donde se ejecuta el juego nuevo
     */
    public static ImageView getbGGame() {
        return bGGame;
    }
    
    /**
     * Metodo que obtiene que skin se esta ejecutando en la aplicacio
     * @return int con el numero identificador de la skin que se esta utilizando
     */
    public static int getSkin() {
        return skin;
    }
    
    /**
     * Metodo que obtiene el MediaPlayer de la aplicacion donde se esta ejecutando 
     * el sonido principal
     * @return Media Player de la aplicacion donde se obtiene el sonido de la vista
     * Principal
     */
    public static MediaPlayer getMediaPlayer(){
        return mediaPlayer;
    }
    
    /**
     * Obtiene la escena de la aplicacion
     * @return Scene con la escena de la aplicacion
     */
    public static Scene getScene(){
        return sc;
    }
    
    /**
     * Metodo que modifica el mediaPlayer de la aplicacion
     * @param mediaPlayer Media Player que desea insertar al MediaPlayer de la 
     * aplicacion principal
     */
    public static void setMediaPlayer(MediaPlayer mediaPlayer){
        Controlador.mediaPlayer=mediaPlayer;
    }
    
    /**
     * Metodo que modifica la escena de la interfaz Principal
     * @param sc Scena con la que se modificara la escena de la interfaz Principal
     */
    public static void setScene(Scene sc){
        Controlador.sc=sc;
    }
    
    /**
     * Metodo que modifica el  nivel de verdad de los sonidos especiales de la aplicacion
     * @param sondEsp Valor de verdad para permitir o no los sonidos especiales en 
     * el juego 
     */
    public static void setSondEsp(boolean sondEsp) {
        Controlador.sondEsp = sondEsp;
    }
    
    /**
     * Metodo que modifica el imageView de la imagen de fondo de la pantalla principal
     * @param bGIndex Image View con el imageView que se desee poner de fondo
     */
    public static void setbGIndex(ImageView bGIndex) {
        Controlador.bGIndex = bGIndex;
    }
    
    /**
     * Metodo con el que se modifica el tituo de TyperBalloons
     * @param title ImageView con el elemento por el cual se desea cambiar el titulo 
     * de la aplicacion Principal TyperBalloons
     */
    public static void setTitle(ImageView title) {
        Controlador.title = title;
    }
    
    /**
     * Metodo que modifica el imageView de la imagen de fondo de la vista de Juego
     * @param bGGame ImageView por el cual se cambia el backgroundd de la aplicacion
     */
    public static void setbGGame(ImageView bGGame) {
        Controlador.bGGame = bGGame;
    }
    
    /**
     * Metodo que modifica el numero identificador de la skin que se desea poner.
     * @param skin Int con el numero identificador de la skin que se desee elegir.
     */
    public static void setSkin(int skin) {
        Controlador.skin = skin;
    }
    
}
