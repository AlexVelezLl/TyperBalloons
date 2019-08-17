/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;


import data.CONSTANTES;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

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
        Image bG = new Image(CONSTANTES.RUTA_IMGS+"BG-01.jpg");
        ImageView bGView = new ImageView(bG);
        bGView.setFitHeight(CONSTANTES.HEIGHT);
        bGView.setFitWidth(CONSTANTES.WIDTH);
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
           
            
            
            onRoot.getChildren().addAll(new ImageView(new Image(CONSTANTES.RUTA_IMGS+"BG-Block.png")),inicioJuego);
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
            ImageView transicion = new ImageView(new Image(CONSTANTES.RUTA_IMGS+"Transicion.png"));
            transicion.setLayoutY(600);
            root.getChildren().add(transicion);
            Runnable transition = new Runnable(){
                @Override
                public void run() {
                    for (int i=0;i<2080;i++){
                        Platform.runLater(()->{
                            transicion.setLayoutY(transicion.getLayoutY()-1);
                        });
                        try {
                            Thread.sleep(1);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(VistaInicio.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            };
            Thread tr = new Thread(transition);
            tr.start();
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
            
            System.exit(0);
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
        ImageView title = new ImageView(new Image(CONSTANTES.RUTA_IMGS+"TyperBallons_Title03.png"));
        
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
    private Pane createOptions(){
        
        Font theFont = Font.font("Comic Sans", FontWeight.BOLD, 45 );
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
        pSalir.setOnMousePressed((e)->{
            pSalir.getChildren().add(new ImageView(new Image(CONSTANTES.RUTA_IMGS+"X_ButtonPressed.png")));
        });
        pSalir.setOnMouseReleased((e)->{
            pSalir.getChildren().add(new ImageView(new Image(CONSTANTES.RUTA_IMGS+"X_Button.png")));
        });
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
        lDificultad.setFont(theFont);
        
        
        ImageView facil = new ImageView(new Image(CONSTANTES.RUTA_IMGS+"Facil_Button.png"));
        ImageView medio = new ImageView(new Image(CONSTANTES.RUTA_IMGS+"Medio_Button.png"));
        ImageView dificil = new ImageView(new Image(CONSTANTES.RUTA_IMGS+"Dificil_Button.png"));
        dificultad.getChildren().addAll(lDificultad,facil,medio,dificil);
        dificultad.setAlignment(Pos.CENTER);
        dificultad.setSpacing(10);
        inicioJuego.getChildren().add(dificultad);
        
        return inicioJuego;
    }
}
