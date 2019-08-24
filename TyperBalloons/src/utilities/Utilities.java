/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;


import Vistas.TyperBalloons;
import Vistas.VistaInicio;
import controlador.Controlador;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 *
 * @author CORE i7 ULTIMATE
 */
public class Utilities {
    private static MediaPlayer mp;
    
    public static void botonSostenido(Pane pane,String img1, String img2){
        Media musicFile = new Media(new File(CONSTANTES.RUTA_SOUNDS+"chestClick.mp3").toURI().toString());//"file:///C:/Users/CORE%20i7%20ULTIMATE/OneDrive%20-%20Escuela%20Superior%20Politécnica%20del%20Litoral/PROGRAMACION%20ORIENTADA%20A%20OBJETOS/ProyectoSegundoParcial/TyperBalloons/TyperBalloons/src/Recursos/sonidos/undertale_ost_fallen_down.mp3");
        
        
        pane.setOnMousePressed((e)->{
            if(Controlador.sondEsp){
                mp = new MediaPlayer(musicFile);
                mp.play();
            }
                
            pane.getChildren().add(new ImageView(new Image(CONSTANTES.RUTA_IMGS+img2)));
            
        });
        
        pane.setOnMouseReleased((e)->{

            pane.getChildren().add(new ImageView(new Image(CONSTANTES.RUTA_IMGS+img1)));
        });
        
        
    }
    public static void transition(Pane root,Pane root2){
        ImageView block = new ImageView(new Image(CONSTANTES.RUTA_IMGS+"BG_Block.png"));
        block.setOpacity(0);
        ImageView transicion = new ImageView(new Image(CONSTANTES.RUTA_IMGS+"Transicion.png"));
        
            transicion.setLayoutY(600);
            root.getChildren().addAll(block,transicion);
            Runnable transition = new Runnable(){
                @Override
                public void run() {
                    for (int i=0;i<2080;i++){
                        Platform.runLater(()->{
                            transicion.setLayoutY(transicion.getLayoutY()-1);
                        });
                        if(i==1000){
                            Platform.runLater(()->{
                                root.getChildren().remove(transicion);
                                root2.getChildren().add(transicion);
                                TyperBalloons.sc.setRoot(root2);
                                
                                
                            });
                            
                        }
                        try {
                            Thread.sleep(1);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(VistaInicio.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    Platform.runLater(()->{
                        root2.getChildren().remove(transicion);
                    });
                    
                    
                }
            };
            Thread tr = new Thread(transition);
            tr.start();
    }
    
    public static void transition2(Pane root1,Pane root2){
        Runnable opacarse = new Runnable(){
            @Override
            public void run() {
                ImageView block = new ImageView(new Image(CONSTANTES.RUTA_IMGS+"BG_Block.png")); 
                block.setOpacity(0);
                Platform.runLater(()->{
                    root1.getChildren().add(block);
                    
                });
                for(int i =1; i<=10;i++){
                    try {
                        Thread.sleep(40);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Utilities.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    block.setOpacity(i*0.1);
                    
                }
                Platform.runLater(()->{
                    root1.getChildren().remove(block);
                    root2.getChildren().add(block);
                    TyperBalloons.sc.setRoot(root2);
                
                });
                
                
                for(int i =10; i>=0;i--){
                    try {
                        Thread.sleep(40);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Utilities.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    block.setOpacity(i*0.1);
                    
                }
                Platform.runLater(()->{
                    root2.getChildren().remove(block);
                });
                
                
            }
        
        
        
        };
        Thread t = new Thread(opacarse);
        t.start();
    }
    
    public static void bajarCartel(Pane onRoot,Pane cartel,int cantidad){
        ImageView block = new ImageView(new Image(CONSTANTES.RUTA_IMGS+"BG_Block.png"));
            block.setOpacity(0.3);
            onRoot.getChildren().addAll(block,cartel);
            onRoot.setOnKeyPressed((e1)->{
                if(e1.getCode()==KeyCode.ESCAPE)
                onRoot.getChildren().clear();
            });
            Runnable bajar = new Runnable(){
                @Override
                public void run() {
                    for(int i=0;i<cantidad;i++){
                        Platform.runLater(()->{
                            cartel.setLayoutY(cartel.getLayoutY()+3);
                        });
                        try {
                            Thread.sleep(1);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(VistaInicio.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                } 
            };
            Thread t = new Thread(bajar);
            t.start();
    }
}
