/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;

import Modelo.Globo;
import Modelo.GloboAmarillo;
import Modelo.GloboRojo;
import Modelo.GloboVerde;
import Modelo.MoverGlobo;
import utilities.CONSTANTES;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.EventType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 *
 * @author PC
 */
public class VistaJuego1 {
    Pane root;
    Pane onRoot;    
    Label tiempo;
    int tiempoJuego;
    Label Globos;
    int numeroGlobos;
    private Random random=new Random();
    ImageView i;
    
    
    public VistaJuego1(){
        tiempoJuego=60;
        root = CrearElementos();
        iniciarJuego("facil");
        Thread tiempo= new Thread(new HiloTiempo());
        tiempo.start();
    
    }
    
    public Pane CrearElementos(){
        Font theFont = Font.font("Aharoni", FontWeight.BOLD, 20 );
        Pane root = new Pane();
        Image im = new Image(CONSTANTES.RUTA_IMGS+"BG-01.jpg");
        ImageView imv = new ImageView(im);
        imv.setFitHeight(CONSTANTES.HEIGHT);
        imv.setFitWidth(CONSTANTES.WIDTH);
        
        Image im_tiemp = new Image(getClass().getResourceAsStream(
                        CONSTANTES.RUTA_IMGS+"TextBox.png"),
                                100,
                                200,
                                true,
                                true); 
        ImageView imv_tiemp= new ImageView(im_tiemp);
        
        
        Label l2= new Label("Tiempo transcurrido: ");
        l2.setFont(theFont);
        StackPane  t = new StackPane();       
        tiempo = new Label(String.valueOf(tiempoJuego));
        tiempo.setFont(theFont);
        tiempo.setLayoutX(20);
        tiempo.setLayoutY(20);;
        t.getChildren().addAll(imv_tiemp,tiempo);
        HBox htiempo= new HBox();
        htiempo.setSpacing(20);
        htiempo.getChildren().addAll(l2,t);
        htiempo.setLayoutX(40);
        htiempo.setLayoutY(40);
        htiempo.setMaxWidth(400);
        
        Label lg = new Label("Numero de \n Globos:");
        
        lg.setFont(theFont);
        Image img = new Image(getClass().getResourceAsStream(
                        CONSTANTES.RUTA_IMGS+"Globo_Blanco.png"),
                                100,
                                200,
                                true,
                                true); 
        ImageView im_marcador= new ImageView(img);
        Pane p = new Pane();        
        Globos = new Label(String.valueOf(numeroGlobos)); 
        Globos.setFont(theFont);
        Globos.setLayoutX(20);
        Globos.setLayoutY(20);
        p.getChildren().addAll(im_marcador,Globos);
        HBox marcador= new HBox();
        marcador.setSpacing(20);
        marcador.getChildren().addAll(lg,p);
        marcador.setLayoutX(540);
        marcador.setLayoutY(40);
        
        Button b = new Button();
        b.setLayoutY(100);
        root.getChildren().addAll(b,imv,htiempo,marcador);
        return root;
    }
    
    
    public Pane getRoot(){
        return root;
    }
    private double generarPosicionX(){
        double x = random.nextDouble()*(CONSTANTES.WIDTH-15);
        return x;
    }
    
    
    
    public void CrearGloboRojo(){        
        GloboRojo globor = new GloboRojo();
        double posicionx = generarPosicionX();
        globor.fijarPosicion(posicionx);
        root.getChildren().addAll(globor);
        MoverGlobo mv = new MoverGlobo(globor);
        Thread th= new Thread(mv);
        th.start();
        globor.setOnKeyPressed((e1)->{ 
            
            
        });
   
    };
    
    
    public void CrearGloboVerde(){        
        GloboVerde globov = new GloboVerde();
        
        double posicionx = generarPosicionX();
        globov.fijarPosicion(posicionx);
           
        root.getChildren().addAll(globov);
        MoverGlobo mv = new MoverGlobo(globov);
        Thread th= new Thread(mv);
        th.start();
        root.setOnKeyPressed((e1)->{              
                if (e1.getCode().toString().toLowerCase().trim().equals(globov.getLetra().getText().trim())){ 
                    root.getChildren().remove(globov);                                       
                    numeroGlobos += 1;
                    Globos.setText(String.valueOf(numeroGlobos));                    
            }               
            });
             
    };
    
    
    
    public void CrearGloboAmarillo(){        
        GloboAmarillo globoa = new GloboAmarillo();
        
        double posicionx = generarPosicionX();
        globoa.fijarPosicion(posicionx);
        root.getChildren().addAll(globoa);
        MoverGlobo mv = new MoverGlobo(globoa);
        Thread th= new Thread(mv);
        th.start();
        globoa.setOnKeyPressed((e1)->{
            System.out.println(e1.getText());  
                if (e1.getCode().toString().toUpperCase().equals(globoa.getLetra().getText())){
                    
                    root.getChildren().remove(globoa);
                    numeroGlobos += 1;
                    Globos.setText(String.valueOf(numeroGlobos));
                    
            }
                
            });       
        
        
    }
    
    /*
    private  class HiloCrearGlobosRojos implements Runnable{
        int tiempo;
        public HiloCrearGlobosRojos(int tiempo){
            this.tiempo=tiempo;
        }

        @Override
        public void run() {
            while(tiempoJuego!=0){
                Platform.runLater(()->{
                CrearGloboRojo();
            });
                try {
                    Thread.sleep(tiempo);
                } catch (InterruptedException ex) {
                    System.out.println("Ocurrio un error");
                }
            }
        }
    }
    
    private  class HiloCrearGlobosVerdes implements Runnable{
        int tiempo;
        public HiloCrearGlobosVerdes(int tiempo){
            this.tiempo=tiempo;
        }

        @Override
        public void run() {
            while(tiempoJuego!=0){
                Platform.runLater(()->{
                CrearGloboVerde();
            });
                try {
                    Thread.sleep(tiempo);
                } catch (InterruptedException ex) {
                    System.out.println("Ocurrio un error");
                }
            }
        }
    }
    
    private  class HiloCrearGlobosAmarillos implements Runnable{
        int tiempo;
        public HiloCrearGlobosAmarillos(int tiempo){
            this.tiempo=tiempo;
        }

        @Override
        public void run() {
            while(tiempoJuego!=0){
                Platform.runLater(()->{
                CrearGloboAmarillo();
            });
                try {
                    Thread.sleep(tiempo);
                } catch (InterruptedException ex) {
                    System.out.println("Ocurrio un error");
                }
            }
        }
    }*/
    
    private class HiloCrearGlobos implements Runnable{

        @Override
        public void run() {
            while(tiempoJuego!=0){
                Platform.runLater(()->{                     
                    CrearGloboVerde();
                    CrearGloboAmarillo();
                    });
                    try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    System.out.println("Ocurrio un error");
                }
                
                
            }
        }
        
    }
    
    private class HiloTiempo implements Runnable{

        @Override
        public void run() {
            
            while(tiempoJuego!=0){
                tiempoJuego-=1;
                Platform.runLater(()->{                
                tiempo.setText(String.valueOf(tiempoJuego));
                    
                    
                });
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    
                }
                
            }
            
        }
        
    }
 
    
    public void iniciarJuego(String d){
        switch(d){
            case "facil":                             
                Thread th2 = new Thread(new HiloCrearGlobos());                                
                th2.start();               
                
                break;
            case "medio":
                
                
                break;
                
            case "dificil":
                
                
                break;
        }
        
        
    }


    
    
}

