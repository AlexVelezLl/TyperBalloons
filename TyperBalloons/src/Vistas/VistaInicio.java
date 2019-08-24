/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;


import Modelo.Dificultad;
import controlador.Controlador;
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
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

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
        Controlador.bGIndex = new ImageView();
        Controlador.bGIndex.setImage(new Image(CONSTANTES.RUTA_IMGS+"BG_01.png"));
        Controlador.skin=1;
        Controlador.sondEsp = true;
        root.getChildren().addAll(Controlador.bGIndex,includeTitle(),includeButtons(),includeSN(),includeSettings(),onRoot);
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
            Utilities.bajarCartel(onRoot,inicioJuego,200);
            
        });
        Utilities.botonSostenido(inicio, "IniciarJuego_Button01.png", "IniciarJuego_ButtonPressed01.png");    
        
        Pane punt = new Pane();
        punt.getChildren().add(punt1);
        punt.setOnMouseClicked((e)->{
            punt.getChildren().clear();
            punt.getChildren().add(punt1);
            Pane puntuaciones = createPunt();
            Utilities.bajarCartel(onRoot, puntuaciones, 200);
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
        Rectangle rect = new Rectangle(195,150);
            rect.setFill(Color.color(0.039, 0.898, 0));
            rect.setLayoutX(174);
            rect.setLayoutY(390);
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
            ImageView bGSettings = new ImageView(new Image(CONSTANTES.RUTA_IMGS+"BG_STGNS01.png"));
            VBox config = new VBox();
            config.setSpacing(40);
            config.setMaxWidth(600);
            
            Label lVolumen = new Label("Volumen:");
            lVolumen.setFont(CONSTANTES.FUENTE);
            lVolumen.setTextFill(Color.WHITE);
            Pane pVolumen = Controlador.volumenControl();
            config.setLayoutX(100);
            config.setLayoutY(65);
            Label lSonidos = new Label("Efectos de sonido: ");
            lSonidos.setFont(CONSTANTES.FUENTE);
            lSonidos.setTextFill(Color.WHITE);
            VBox vbVolumen = new VBox();
            vbVolumen.getChildren().addAll(lVolumen,pVolumen);
            ImageView checkBox = new ImageView(new Image(CONSTANTES.RUTA_IMGS+"CheckBox1.png"));
            ImageView check = new ImageView(new Image(CONSTANTES.RUTA_IMGS+"Check.png"));
            HBox hbCheckBox = new HBox();
            Pane pCheckBox = new Pane();
            if(Controlador.sondEsp){
                pCheckBox.getChildren().addAll(checkBox,check);
            }else{
                pCheckBox.getChildren().addAll(checkBox);
            }
            
            pCheckBox.setOnMouseClicked((a)->{
                if(Controlador.sondEsp){
                    Controlador.sondEsp=false;
                    pCheckBox.getChildren().remove(check);
                }else{
                    Controlador.sondEsp=true;
                    pCheckBox.getChildren().add(check);
                }
            });
            hbCheckBox.getChildren().addAll(lSonidos,pCheckBox);
            VBox vBSkin = new VBox();
           
            Label lSkin = new Label("Skin");
           
            lSkin.setFont(CONSTANTES.FUENTE);
            lSkin.setTextFill(Color.WHITE);
            Pane pSkin = new Pane();
            
            ImageView skin1 = new ImageView(new Image(CONSTANTES.RUTA_IMGS+"BG_01.png"));
            skin1.setFitHeight(135);
            skin1.setFitWidth(180);
            ImageView skin2 = new ImageView(new Image(CONSTANTES.RUTA_IMGS+"BG_03.png"));
            skin2.setFitHeight(135);
            skin2.setFitWidth(180);
            
            StackPane pSkin1 = new StackPane();
            StackPane pSkin2 = new StackPane();
            pSkin1.setLayoutX(80);
            pSkin2.setLayoutX(340);
            
            pSkin1.getChildren().addAll(new Rectangle(184,139),skin1);
            pSkin2.getChildren().addAll(new Rectangle(184,139),skin2);
            
            pSkin1.setOnMouseClicked((e1)->{
                rect.setLayoutX(174);
                Controlador.bGIndex.setImage(new Image(CONSTANTES.RUTA_IMGS+"BG_01.png"));
                Controlador.skin=1;
                
            });
            pSkin2.setOnMouseClicked((e1)->{
                rect.setLayoutX(434);
                Controlador.bGIndex.setImage(new Image(CONSTANTES.RUTA_IMGS+"BG_03.png"));
                Controlador.skin=2;
            });
            
            pSkin.getChildren().addAll(pSkin1,pSkin2);
            
           
            vBSkin.getChildren().addAll(lSkin,pSkin);
            vBSkin.setAlignment(Pos.CENTER);
            vBSkin.setSpacing(10);
            config.getChildren().addAll(vbVolumen,hbCheckBox,vBSkin);
            setingsPane.getChildren().addAll(bGSettings,pRetro,rect,config);
            Utilities.transition2(root,setingsPane);
            
        });
        Utilities.botonSostenido(eng, "Engranaje_img.png", "Engranaje_imgPressed.png");
        
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
            VistaJuego1 vj = new VistaJuego1();
            
            Utilities.transition(root,vj.getRoot());
        });
        ImageView medio = new ImageView(new Image(CONSTANTES.RUTA_IMGS+"Medio_Button01.png"));
        
        Pane pMedio = new Pane();
        pMedio.getChildren().add(medio);
        Utilities.botonSostenido(pMedio, "Medio_Button01.png", "Medio_ButtonPressed01.png");
        pMedio.setOnMouseClicked((e)->{
            pMedio.getChildren().clear();
            pMedio.getChildren().add(medio);
            VistaJuego1 vj = new VistaJuego1();
            Utilities.transition(root,vj.getRoot());
        });
        ImageView dificil = new ImageView(new Image(CONSTANTES.RUTA_IMGS+"Dificil_Button01.png"));
        Pane pDificil = new Pane();
        pDificil.getChildren().add(dificil);
        Utilities.botonSostenido(pDificil, "Dificil_Button01.png", "Dificil_ButtonPressed01.png");
        pDificil.setOnMouseClicked((e)->{
            pDificil.getChildren().clear();
            pDificil.getChildren().add(dificil);
            VistaJuego1 vj = new VistaJuego1();
            Utilities.transition(root,vj.getRoot());
        });
        dificultad.getChildren().addAll(lDificultad,pFacil,pMedio,pDificil);
        dificultad.setAlignment(Pos.CENTER);
        dificultad.setSpacing(10);
        inicioJuego.getChildren().add(dificultad);  
        return inicioJuego;
    }
    
    public Pane createPunt(){
        Pane pPunt = new  Pane();
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
        Label lPuntajes = new Label("Puntajes");
        lPuntajes.setFont(CONSTANTES.FUENTE);
        lPuntajes.setTextFill(Color.WHITE);
        pPunt.getChildren().addAll(b,ini,pSalir,lPuntajes);
        pPunt.setLayoutX(100);
        pPunt.setLayoutY(-500);
        
        return pPunt;
    }
    
}
