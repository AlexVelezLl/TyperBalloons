/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;


import Modelo.Dificultad;
import utilities.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import java.applet.AudioClip;

/**
 *
 * @author PC
 */
public class VistaInicio{
    Pane root;
    Pane onRoot;
    public VistaInicio()throws IllegalArgumentException{
        
        root = new Pane();
        onRoot = new Pane();
        Image bG = new Image(CONSTANTES.RUTA_IMGS+"BG_01.png");
       
        ImageView bGView = new ImageView(bG);
        //bGView.setLayoutY(5);
        root.getChildren().addAll(bGView,includeTitle(),includeButtons(),includeSN(),includeSettings(),onRoot);
    }
    
    public Pane getRoot(){
        return root;
    }
    
    private VBox includeButtons(){
        VBox buttons = new VBox();
        buttons.setLayoutX(250);
        buttons.setLayoutY(250);
        ImageView inicio1 = new ImageView(new Image(CONSTANTES.RUTA_IMGS+"IniciarJuego_Button01.png"));
        ImageView punt1 = new ImageView(new Image(CONSTANTES.RUTA_IMGS+"VerPuntajes_Button01.png"));
        ImageView salir1 = new ImageView(new Image(CONSTANTES.RUTA_IMGS+"Salir_Button01.png"));
        Pane inicio = new Pane();
        inicio.getChildren().add(inicio1);
        
        inicio.setOnMouseClicked((e)->{
            inicio.getChildren().clear();
            inicio.getChildren().add(inicio1);
            Pane inicioJuego = createOptions();
           
            
            ImageView block = new ImageView(new Image(CONSTANTES.RUTA_IMGS+"BG_Block.png"));
            block.setOpacity(0.3);
            onRoot.getChildren().addAll(block,inicioJuego);
            onRoot.setOnKeyPressed((e1)->{
                if(e1.getCode()==KeyCode.ESCAPE)
                onRoot.getChildren().clear();
            });
            Runnable bajar = new Runnable(){
                @Override
                public void run() {
                    for(int i=0;i<200;i++){
                        Platform.runLater(()->{
                            inicioJuego.setLayoutY(inicioJuego.getLayoutY()+3);
                        });
                        try {
                            Thread.sleep(1);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(VistaInicio.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                } 
            };
            Thread bajarCartel = new Thread(bajar);
            bajarCartel.start();
        });
        Utilities.botonSostenido(inicio, "IniciarJuego_Button01.png", "IniciarJuego_ButtonPressed01.png");    
        
        Pane punt = new Pane();
        punt.getChildren().add(punt1);
        punt.setOnMouseClicked((e)->{
            punt.getChildren().clear();
            punt.getChildren().add(punt1);
            
        });
        Utilities.botonSostenido(punt, "VerPuntajes_Button01.png", "VerPuntajes_ButtonPressed01.png");
        
        Pane salir = new Pane();
        salir.getChildren().add(salir1);
        salir.setOnMouseClicked((e)->{
            
            System.exit(0);
        });
        Utilities.botonSostenido(salir, "Salir_Button01.png", "Salir_ButtonPressed01.png");
                  
        buttons.setSpacing(20);
        buttons.getChildren().addAll(inicio,punt,salir);
        return buttons;
    }
    
    private Pane includeTitle(){
        Pane pTitle = new Pane();
        ImageView title = new ImageView(new Image(CONSTANTES.RUTA_IMGS+"TyperBallons_Title04.png"));
        
        title.setLayoutX(70);
        title.setLayoutY(45);
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
        Utilities.botonSostenido(pFB, "FB_img.png", "FB_imgPressed.png");
        
        Pane pTwitter = new Pane();
        pTwitter.getChildren().add(twitter);
        pTwitter.setOnMouseClicked((e)->{
            pTwitter.getChildren().clear();
            pTwitter.getChildren().add(twitter);
        });
        Utilities.botonSostenido(pTwitter, "Twitter_img.png", "Twitter_imgPressed.png");
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
            Pane setingsPane = new Pane();
            ImageView retro = new ImageView(new Image(CONSTANTES.RUTA_IMGS+"Retro_Button.png"));
            Pane pRetro = new Pane();
            Button b = new Button();
            b.setLayoutX(-20);
            pRetro.getChildren().addAll(b,retro);
            pRetro.setOnMouseClicked((a)->{
                pRetro.getChildren().clear();
                pRetro.getChildren().add(retro);
                Utilities.transition2(setingsPane, root);
                
            });
            pRetro.setOnKeyPressed((a)->{
                if(a.getCode()==KeyCode.ESCAPE){
                    Utilities.transition2(setingsPane, root);
                }
            });
            Utilities.botonSostenido(pRetro, "Retro_Button.png", "Retro_ButtonPressed.png");
            
            setingsPane.getChildren().addAll(new ImageView(new Image(CONSTANTES.RUTA_IMGS+"BG_STGNS01.png")),pRetro);
            Utilities.transition2(root,setingsPane);
            
        });
        Utilities.botonSostenido(eng, "Engranaje_img.png", "Engranaje_imgPressed.png");
        eng.setOnMousePressed((e)->{
            
            eng.getChildren().add(new ImageView(new Image(CONSTANTES.RUTA_IMGS+"Engranaje_imgPressed.png")));
            
        });
        eng.setOnMouseReleased((e)->{
            
            eng.getChildren().add(new ImageView(new Image(CONSTANTES.RUTA_IMGS+"Engranaje_img.png")));
            
        });
        return eng;
    }
    private Pane createOptions(){

        Pane inicioJuego = new  Pane();
        ImageView ini = new ImageView(new Image(CONSTANTES.RUTA_IMGS+"BG_IJ02.png"));
        Pane pSalir = new Pane();
        ImageView salir = new ImageView(new Image(CONSTANTES.RUTA_IMGS+"X_Button.png"));
        pSalir.getChildren().add(salir);
        pSalir.setLayoutY(-20);
        pSalir.setLayoutX(-20);
        pSalir.setOnMouseClicked((e)->{
            onRoot.getChildren().clear();
            pSalir.getChildren().clear();
        });
        Utilities.botonSostenido(pSalir,"X_Button.png","X_ButtonPressed.png");
        Button b = new Button();
        b.setLayoutY(100);
        inicioJuego.getChildren().addAll(b,ini,pSalir);
        inicioJuego.setLayoutX(100);
        inicioJuego.setLayoutY(-500);
        
        VBox dificultad = new VBox();
        Label lDificultad = new Label("Dificultad");
        dificultad.setLayoutX(200);
        dificultad.setLayoutY(40);
        lDificultad.setTextFill(Color.WHITE);
        lDificultad.setFont(CONSTANTES.FUENTE);
   
        ImageView facil = new ImageView(new Image(CONSTANTES.RUTA_IMGS+"Facil_Button.png"));
        Pane pFacil = new Pane();
        pFacil.getChildren().add(facil);
        Utilities.botonSostenido(pFacil, "Facil_Button.png", "Facil_ButtonPressed.png");
        pFacil.setOnMouseClicked((e)->{
            pFacil.getChildren().clear();
            pFacil.getChildren().add(facil);
            Utilities.transition(root);
        });
        ImageView medio = new ImageView(new Image(CONSTANTES.RUTA_IMGS+"Medio_Button01.png"));
        
        Pane pMedio = new Pane();
        pMedio.getChildren().add(medio);
        Utilities.botonSostenido(pMedio, "Medio_Button01.png", "Medio_ButtonPressed01.png");
        pMedio.setOnMouseClicked((e)->{
            pMedio.getChildren().clear();
            pMedio.getChildren().add(medio);
            Utilities.transition(root);
        });
        ImageView dificil = new ImageView(new Image(CONSTANTES.RUTA_IMGS+"Dificil_Button01.png"));
        Pane pDificil = new Pane();
        pDificil.getChildren().add(dificil);
        Utilities.botonSostenido(pDificil, "Dificil_Button01.png", "Dificil_ButtonPressed01.png");
        pDificil.setOnMouseClicked((e)->{
            pDificil.getChildren().clear();
            pDificil.getChildren().add(dificil);
            Utilities.transition(root);
        });
        dificultad.getChildren().addAll(lDificultad,pFacil,pMedio,pDificil);
        dificultad.setAlignment(Pos.CENTER);
        dificultad.setSpacing(10);
        inicioJuego.getChildren().add(dificultad);  
        return inicioJuego;
    }
    
}
