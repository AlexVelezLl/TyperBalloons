/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;

import Modelo.Dificultad;
import Modelo.GloboAmarillo;
import Modelo.Juego;
import controlador.Controlador;
import data.GameData;
import utilities.CONSTANTES;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author PC
 */
public class testVistajuego1 extends Application {
    VistaJuego1 Juego;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Controlador.setSkin(1);
        Juego = new VistaJuego1(Dificultad.FACIL);  
        Pane root = Juego.getRoot();
        Scene sc = new Scene(root);
        primaryStage.setHeight(CONSTANTES.HEIGHT);
        primaryStage.setWidth(CONSTANTES.WIDTH);
        primaryStage.setResizable(false);
        primaryStage.setScene(sc); 
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
    

}
