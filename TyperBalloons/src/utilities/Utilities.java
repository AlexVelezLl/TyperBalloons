/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import Vistas.TyperBalloons;
import Vistas.VistaInicio;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

/**
 *
 * @author CORE i7 ULTIMATE
 */
public class Utilities {
    
    public static void botonSostenido(Pane pane,String img1, String img2){
        pane.setOnMousePressed((e)->{
            pane.getChildren().add(new ImageView(new Image(CONSTANTES.RUTA_IMGS+img2)));
        });
        pane.setOnMouseReleased((e)->{
            pane.getChildren().add(new ImageView(new Image(CONSTANTES.RUTA_IMGS+img1)));
        });
        
    }
    public static void transition(Pane root){
        ImageView block = new ImageView(new Image(CONSTANTES.RUTA_IMGS+"BG-Block.png"));
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
                        try {
                            Thread.sleep(1);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(VistaInicio.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    Platform.runLater(()->{
                        root.getChildren().remove(block);
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
}
