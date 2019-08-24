/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;

import Vistas.VistaInicio;
import controlador.Controlador;
import java.applet.AudioClip;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import utilities.CONSTANTES;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

/**
 *
 * @author joangie
 */
public class TyperBalloons extends Application {
    public static MediaPlayer mediaPlayer;
    public static Scene sc;
    @Override
    public void start(Stage primaryStage) {
        
        VistaInicio index = new VistaInicio();
        
        Pane backGround = index.getRoot();
        
        sc = new Scene(backGround);
        //Musica de inicio   
        Media musicFile = new Media(new File(CONSTANTES.RUTA_SOUNDS+"Game_Intro.mp3").toURI().toString());
        mediaPlayer = new MediaPlayer(musicFile);
        mediaPlayer.setAutoPlay(true);
        Controlador.volumen=new Double(0.7);
        mediaPlayer.setVolume(Controlador.volumen);
        
        primaryStage.setTitle("TyperBalloons");
        primaryStage.setHeight(CONSTANTES.HEIGHT);
        primaryStage.setWidth(CONSTANTES.WIDTH);
        primaryStage.setResizable(false);
        primaryStage.setScene(sc);
        primaryStage.getIcons().add(new Image(CONSTANTES.RUTA_IMGS+"ICO_01.png")); 
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
