/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;

import controlador.Controlador;
import java.io.File;
import utilities.CONSTANTES;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

/**
 * Clase principal de la aplicacion desde donde se crea la ventana principal donde
 * sera ejecutado nuestro juego.
 * @author Alex Velez
 */
public class TyperBalloons extends Application {
    /**
     * Metodo sobreEscrito a la clase Application donde se desarrollara la 
     * estructura de la aplicacion
     * @param primaryStage Stage Principal donde se vera la aplicacion.
     */
    @Override
    public void start(Stage primaryStage) {
        try{
            VistaInicio index = new VistaInicio();
        
            Pane backGround = index.getRoot();
        
            Controlador.setScene(new Scene(backGround));
            Media musicFile = new Media(new File(CONSTANTES.RUTA_SOUNDS+"Game_Intro.mp3").toURI().toString());
            Controlador.setMediaPlayer(new MediaPlayer(musicFile));
            Controlador.getMediaPlayer().setAutoPlay(true);
  
            Controlador.getMediaPlayer().setVolume(0.7);
            primaryStage.setTitle("TyperBalloons");
            primaryStage.setHeight(CONSTANTES.HEIGHT);
            primaryStage.setWidth(CONSTANTES.WIDTH);
            primaryStage.setResizable(false);
            primaryStage.setScene(Controlador.getScene());
            primaryStage.getIcons().add(new Image(CONSTANTES.RUTA_IMGS+"ICO_01.png")); 
            primaryStage.show();
        }catch(IllegalArgumentException ex){
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("WOOPS!");
            alert.setContentText("Dificultades tecnicas, por favor revisa que todos los archivos del juego "
                    + "se encuentren en tu computadora." );
            alert.showAndWait();
        }catch(Exception ex){
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("WOOPS!");
            alert.setContentText("Dificultades tecnicas, estamos trabajando en ello.\n"
                    + "Por favor reporta este error en: https://typerballoons.000webhostapp.com\n\n"
                    + "Error: "+ex.toString());
            alert.showAndWait();
        }
        
   
        
    }
     @Override
         public void stop(){
            VistaJuego1.finalizarJuego1();
        }

    /**
     * Metodo principal de la aplicacion
     * @param args 
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
