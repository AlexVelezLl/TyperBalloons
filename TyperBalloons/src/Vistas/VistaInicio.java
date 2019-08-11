/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;


import data.CONSTANTES;
import java.io.IOException;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author PC
 */
public class VistaInicio{
    Pane root;
    public VistaInicio()throws IllegalArgumentException{
        root = new Pane();
        Image bG = new Image(CONSTANTES.RUTA_IMGS+"BG-01.jpg");
        ImageView bGView = new ImageView(bG);
        bGView.setFitHeight(CONSTANTES.HEIGHT);
        bGView.setFitWidth(CONSTANTES.WIDTH);
        root.getChildren().addAll(bGView,includeTitle(),includeButtons(),includeSN(),includeSettings());
    }
    
    public Pane getRoot(){
        return root;
    }
    
    private VBox includeButtons(){
        VBox buttons = new VBox();
        buttons.setLayoutX(250);
        buttons.setLayoutY(250);
        ImageView inicio1 = new ImageView(new Image(CONSTANTES.RUTA_IMGS+"IniciarJuego_Button01.png"));
        ImageView inicio2 = new ImageView(new Image(CONSTANTES.RUTA_IMGS+"IniciarJuego_ButtonPressed01.png"));
        ImageView punt1 = new ImageView(new Image(CONSTANTES.RUTA_IMGS+"VerPuntajes_Button01.png"));
        ImageView punt2 = new ImageView(new Image(CONSTANTES.RUTA_IMGS+"VerPuntajes_ButtonPressed01.png"));
        ImageView salir1 = new ImageView(new Image(CONSTANTES.RUTA_IMGS+"Salir_Button01.png"));
        ImageView salir2 = new ImageView(new Image(CONSTANTES.RUTA_IMGS+"Salir_ButtonPressed01.png"));
        Pane inicio = new Pane();
        inicio.getChildren().add(inicio1);
        Pane punt = new Pane();
        punt.getChildren().add(punt1);
        Pane salir = new Pane();
        salir.getChildren().add(salir1);
        buttons.setSpacing(20);
        buttons.getChildren().addAll(inicio,punt,salir);
        return buttons;
    }
    
    private Pane includeTitle(){
        Pane pTitle = new Pane();
        ImageView title = new ImageView(new Image(CONSTANTES.RUTA_IMGS+"TyperBallons_Title.png"));
        title.setLayoutX(200);
        title.setLayoutY(100);
        pTitle.getChildren().add(title);
        return pTitle;
    }
    
    private VBox includeSN(){
        VBox sn = new VBox();
        sn.setLayoutX(650);
        sn.setLayoutY(370);
        sn.setSpacing(10);
        ImageView fb = new ImageView(new Image(CONSTANTES.RUTA_IMGS+"FB_img.png"));
        fb.setFitHeight(70);
        fb.setFitWidth(70);
        ImageView twitter = new ImageView(new Image(CONSTANTES.RUTA_IMGS+"Twitter_img.png"));
        twitter.setFitHeight(70);
        twitter.setFitWidth(70);
        Pane pFB = new Pane();
        pFB.getChildren().add(fb);
        Pane pTwitter = new Pane();
        pTwitter.getChildren().add(twitter);
        sn.getChildren().addAll(pFB,pTwitter);
        return sn;
    }
    
    private Pane includeSettings(){
        Pane eng = new Pane();
        ImageView engView = new ImageView(new Image(CONSTANTES.RUTA_IMGS+"Engranaje_img.png"));
        
        eng.getChildren().add(engView);
        eng.setLayoutX(80);
        eng.setLayoutY(400);
        return eng;
    }
}
