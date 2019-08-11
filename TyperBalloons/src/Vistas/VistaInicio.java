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
        
        inicio.setOnMouseClicked((e)->{
            inicio.getChildren().clear();
            inicio.getChildren().add(inicio1);
        });
        inicio.setOnMousePressed((e)->{
            
            inicio.getChildren().add(new ImageView(new Image(CONSTANTES.RUTA_IMGS+"IniciarJuego_ButtonPressed01.png")));
        });
        inicio.setOnMouseReleased((e)->{
            
            inicio.getChildren().add(new ImageView(new Image(CONSTANTES.RUTA_IMGS+"IniciarJuego_Button01.png")));
        });
        
        
        Pane punt = new Pane();
        punt.getChildren().add(punt1);
        punt.setOnMouseClicked((e)->{
            punt.getChildren().clear();
            punt.getChildren().add(punt1);
        });
        punt.setOnMousePressed((e)->{
            punt.getChildren().add(new ImageView(new Image(CONSTANTES.RUTA_IMGS+"VerPuntajes_ButtonPressed01.png")));
        });
        punt.setOnMouseReleased((e)->{
            punt.getChildren().add(new ImageView(new Image(CONSTANTES.RUTA_IMGS+"VerPuntajes_Button01.png")));
        });
        
        Pane salir = new Pane();
        salir.getChildren().add(salir1);
        salir.setOnMouseClicked((e)->{
            salir.getChildren().clear();
            salir.getChildren().add(salir1);
        });
        salir.setOnMousePressed((e)->{
            salir.getChildren().add(new ImageView(new Image(CONSTANTES.RUTA_IMGS+"Salir_ButtonPressed01.png")));
        });
        salir.setOnMouseReleased((e)->{
            salir.getChildren().add(new ImageView(new Image(CONSTANTES.RUTA_IMGS+"Salir_Button01.png")));
        });
        
        
        
        
        
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
        
        ImageView twitter = new ImageView(new Image(CONSTANTES.RUTA_IMGS+"Twitter_img.png"));
        
        Pane pFB = new Pane();
        pFB.getChildren().add(fb);
        
        pFB.setOnMouseClicked((e)->{
            pFB.getChildren().clear();
            pFB.getChildren().add(fb);
        });
        pFB.setOnMousePressed((e)->{
            pFB.getChildren().add(new ImageView(new Image(CONSTANTES.RUTA_IMGS+"FB_imgPressed.png")));
        });
        pFB.setOnMouseReleased((e)->{
            pFB.getChildren().add(new ImageView(new Image(CONSTANTES.RUTA_IMGS+"FB_img.png")));
        });
        
        Pane pTwitter = new Pane();
        pTwitter.getChildren().add(twitter);
        pTwitter.setOnMouseClicked((e)->{
            pTwitter.getChildren().clear();
            pTwitter.getChildren().add(twitter);
        });
        pTwitter.setOnMousePressed((e)->{
            pTwitter.getChildren().add(new ImageView(new Image(CONSTANTES.RUTA_IMGS+"Twitter_imgPressed.png")));
        });
        pTwitter.setOnMouseReleased((e)->{
            pTwitter.getChildren().add(new ImageView(new Image(CONSTANTES.RUTA_IMGS+"Twitter_img.png")));
        });
        sn.getChildren().addAll(pFB,pTwitter);
        return sn;
    }
    
    private Pane includeSettings(){
        Pane eng = new Pane();
        ImageView engView = new ImageView(new Image(CONSTANTES.RUTA_IMGS+"Engranaje_img.png"));
        eng.getChildren().add(engView);
        eng.setLayoutX(80);
        eng.setLayoutY(400);
        eng.setOnMouseClicked((e)->{
            eng.getChildren().clear();
            eng.getChildren().add(engView);         
            
            
        });
        eng.setOnMousePressed((e)->{
            
            eng.getChildren().add(new ImageView(new Image(CONSTANTES.RUTA_IMGS+"Engranaje_imgPressed.png")));
            
        });
        eng.setOnMouseReleased((e)->{
            
            eng.getChildren().add(new ImageView(new Image(CONSTANTES.RUTA_IMGS+"Engranaje_img.png")));
            
        });
        return eng;
    }
}
