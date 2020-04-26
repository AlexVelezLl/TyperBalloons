/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import modelo.Dificultad;
import modelo.Globo;
import modelo.GloboAmarillo;
import modelo.GloboMalo;
import modelo.GloboRojo;
import modelo.GloboVerde;
import modelo.Juego;
import modelo.MoverGlobo;
import controlador.Controlador;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map.Entry;
import utilities.CONSTANTES;
import javafx.scene.layout.Pane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import java.util.Random;
import java.util.SortedMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import utilities.Utilities;

/**
 * Clase VistaJuego: ventana de la primera fase del juego
 * @author Valeria Barzola
 */


public class VistaJuego1 {
    Font theFont = Font.font("Aharoni", FontWeight.BOLD, 20 );
    Juego juego;
    Pane root;
    Pane gpane;
    Pane onRoot;
    Pane pMessage;
    Label tiempo;
    int tiempoJuego;
    private static boolean activo=true;
    Label globos;
    int numeroGlobos;
    private Random random=new Random();
    ImageView i;
    protected ArrayList <Globo> globoslista;   
    protected SortedMap<String,Integer> letrasObtenidas;
    protected Thread mov;
    
    public VistaJuego1(Dificultad f)throws IOException{        
        juego= new Juego(f);
        letrasObtenidas= juego.getPlayerL();
        globoslista = new ArrayList<>();    
        tiempoJuego=60;
        root = crearElementos();
        iniciarJuego(f);
               
        
    }
    /**
     * Crea el contenido de la venta, será el contenedor raíz de la ventana
     * @return un Pane con el contendio de la ventana
     */
    
    public Pane crearElementos(){
        onRoot= new Pane();
        gpane= new Pane();
        pMessage = new Pane();              
        Pane raiz = new Pane();
        ImageView imv=null;
        if (Controlador.getSkin()==1) {
            imv = new ImageView(new Image(CONSTANTES.RUTA_IMGS+"BG_02.png"));
        }else{
            imv = new ImageView(new Image(CONSTANTES.RUTA_IMGS+"BG_04.png"));
        }                              
        
        
        
        
        HBox tiempocontenedor= contenedorTiempo();
        HBox marcador= marcadorCont();
        raiz.getChildren().addAll(new Button(),imv,tiempocontenedor,gpane,onRoot,marcador,pMessage);
        raiz.setOnKeyPressed(e->{
            String letra = e.getText();                      
            Iterator <Globo> iterator = globoslista.iterator();
            while(iterator.hasNext()){
                
                Globo g = iterator.next();
                comprobarGlobo(g,letra);
                
            }});
        return raiz;
    }
    public void comprobarGlobo(Globo g, String letra){
        if(g.getLetras().contains(letra) && g.isOnScreen()){                  
            Platform.runLater(()->onRoot.getChildren().clear());
            juegoGlobos(g,letra);
            if (letrasObtenidas.keySet().contains(letra.toLowerCase())) {
                int cont= letrasObtenidas.get(letra.toLowerCase())+1;
                letrasObtenidas.put(letra.toLowerCase(), cont);
                compGloboMalo(g,letra);                       
            }else{
                letrasObtenidas.put(letra.toLowerCase(), 1);
                compGloboMalo(g,letra);                      
            }
        }
    }
    public void compGloboMalo(Globo g,String letra){
        if (g instanceof GloboMalo) {letrasObtenidas.put(letra.toLowerCase(),0);}
    }
    public HBox marcadorCont(){
        Label lg = new Label("Numero de \n Globos:");
        
        lg.setFont(theFont);
        Image img = new Image(getClass().getResourceAsStream(
                        CONSTANTES.RUTA_IMGS+"Globo_Blanco.png"),
                                100,
                                200,
                                true,
                                true); 
        ImageView imMarcador= new ImageView(img);
        Pane p = new Pane();        
        globos = new Label(String.valueOf(numeroGlobos)); 
        globos.setFont(theFont);
        globos.setLayoutX(20);
        globos.setLayoutY(20);
        p.getChildren().addAll(imMarcador,globos);
        HBox marcador= new HBox();
        marcador.setSpacing(20);
        marcador.getChildren().addAll(lg,p);
        marcador.setLayoutX(540);
        marcador.setLayoutY(40);
        return marcador;
    }

    
    public HBox contenedorTiempo(){
        HBox htiempo= new HBox();
            Image imTiemp = new Image(getClass().getResourceAsStream(
                           CONSTANTES.RUTA_IMGS+"TextBox.png"),
                                   100,
                                   200,
                                   true,
                                   true); 
           ImageView imvTiemp= new ImageView(imTiemp);                
           Label l2= new Label("Tiempo transcurrido: ");
           l2.setFont(theFont);
           StackPane  t = new StackPane();       
           tiempo = new Label(String.valueOf(tiempoJuego));
           tiempo.setFont(theFont);
           tiempo.setLayoutX(20);
           tiempo.setLayoutY(20);
           t.getChildren().addAll(imvTiemp,tiempo);

           htiempo.setSpacing(20);
           htiempo.getChildren().addAll(l2,t);
           htiempo.setLayoutX(40);
           htiempo.setLayoutY(40);
           htiempo.setMaxWidth(400);
        return htiempo;
    }
             
    /**
     * Método que realiza la funcion principal del juego, verifica si la letra ingresada coincide con la letra del globo.
     * @param gb El globo sobre el cual se dará la accion de eliminar la letra
     * @param s La letra ingresada por el usuario en el teclado
     */
    
     public void juegoGlobos(Globo gb, String s){
        
        
        Iterator it = gb.getLetras().iterator();
                while(it.hasNext()){
                    if (it.next().equals(s)) {
                        it.remove();
                        gb.añadirLetras(gb.getLetras(),22,20);
                    }
                }
                Media music = new Media(new File(CONSTANTES.RUTA_SOUNDS+"explosion.mp3").toURI().toString());
                MediaPlayer mp = new MediaPlayer(music);
                if (gb.getLetras().isEmpty()) {
                    gpane.getChildren().remove(gb);
                    gb.setOnScreen(false);                   
                    if(Controlador.isSondEsp()){
                        mp.play();
                    }
                    numeroGlobos += 1;
                    globos.setText(String.valueOf(numeroGlobos));            
                }
                if (gb instanceof GloboMalo) {
                            
                            globos.setText(String.valueOf(numeroGlobos)); 
                            Label whoops = new Label("Woops, globo malo");
                            whoops.setFont(CONSTANTES.FUENTE);
                            whoops.setTextFill(Color.RED);
                            whoops.setLayoutX(200);
                            whoops.setLayoutY(70);
                            Thread tMessage = new Thread(()->{
                                Platform.runLater(()->pMessage.getChildren().add(whoops));
                                try {
                                    Thread.sleep(1000);
                                } catch (InterruptedException ex) {
                                    Thread.currentThread().interrupt();
                                }
                                Platform.runLater(()->pMessage.getChildren().remove(whoops));
                            });
                            tMessage.start();
                            
                }
     }
     
     /**
      * Metodo que desactiva la accion OnKeyPressed sobre el globo y presenta los resultados de la primera etapa
      */
    
    public void finalizarJuego(){
        eliminarLetrasAusentes();   
        deshabilitarGlobos();      
        Platform.runLater(()->{
            onRoot.getChildren().clear();
            Pane over = new Pane();
            ImageView ini;
            if (Controlador.getSkin()==2) ini= new ImageView(new Image(CONSTANTES.RUTA_IMGS+"BG_IJ01.png"));
            else ini = new ImageView(new Image(CONSTANTES.RUTA_IMGS+"BG_IJ02.png"));
            
            Label ltitulo = new Label("TIME'S OVER");
            ltitulo.setTextFill(Color.WHITE);
            ltitulo.setFont(CONSTANTES.FUENTE);         
            Pane seguir = Utilities.boton("Seguir");
            seguir.setLayoutX(500);
            seguir.setLayoutY(290); 
            seguir.setOnMouseClicked(e->{
                VistaJuego2 vj2= new VistaJuego2(juego);
                Utilities.transition(root,vj2.getRoot());
            });
            ltitulo.setLayoutX(150);
            ltitulo.setLayoutY(30);
            ScrollPane scrollp= paneLetrasObtenidas();
            
            over.setLayoutX(100);
            over.setLayoutY(-500);
            
            HBox h= new HBox();
            h.setLayoutX(100);
            h.setLayoutY(90);
            Label l1;
            Label l2;
            l1= new Label("Letras");
            l2= new Label ("Stock"); 
            l1.setTextFill(Color.WHITE);
            l2.setTextFill(Color.WHITE);
            
            try {
                Font f = Font.loadFont(new FileInputStream(new File("src/myFonts/Top_p2.ttf")), 24);
                l1.setFont(f);
                l2.setFont(f);
            } catch (FileNotFoundException ex) {
                Utilities.reportError(ex);
            }
            h.getChildren().addAll(l1,l2);
            h.setSpacing(60);
            over.getChildren().addAll(ini,ltitulo,h,scrollp,seguir);
            
            Utilities.bajarCartel(onRoot, over, 200);            
            
        });  
    }
    
    /**
     * Metodo que crea el scrollPane con las letras obtenidas durante la partida
     * @return Scrollpane con la letra y el sotck de cada una
     */
    
    public ScrollPane paneLetrasObtenidas(){          
        ScrollPane scrollp = new ScrollPane();
        VBox playerLetters= new VBox();
        for(Entry<String,Integer> s: letrasObtenidas.entrySet()){
            HBox box= new HBox();
            Label letter= new Label(s.getKey());
            Label num= new Label(Integer.toString(letrasObtenidas.get(s.getKey()))); 
            letter.setFont(theFont);
            num.setFont(theFont);
            box.getChildren().addAll(letter,num);
            playerLetters.getChildren().add(box);          
            box.setSpacing(100);
        }   
        scrollp.setContent(playerLetters);     
        scrollp.setPrefSize(300,180);
        scrollp.setLayoutX(100);
        scrollp.setFitToWidth(true);
        playerLetters.setSpacing(30);
        scrollp.setLayoutY(180); 
        scrollp.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollp.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        playerLetters.setPadding(new Insets(20));              
        return scrollp;
  
    }
    
    /**
     * Metodo que retorna el root de la VentanaJuego1
     * @return Pane que cumple la función de ser el root
     */
    
    public Pane getRoot(){
        return root;
    }
    
    /**
     * Método de genera una posición random en el eje x
     * @return random double 
     */
    private double generarPosicionX(){        
        return  random.nextDouble()*(CONSTANTES.WIDTH-150);
    }
    
    
    /**
     * Metodo que crea un objeto GloboRojo, lo situa en la ventana y genera el movimiento del mismo
     */
    public void crearGloboRojo(int tiempo){        
        GloboRojo globor = new GloboRojo();
        double posicionx = generarPosicionX();
        globor.fijarPosicion(posicionx);
        globoslista.add(globor);
        gpane.getChildren().addAll(globor);        
        MoverGlobo mv = new MoverGlobo(globor,tiempo);
        mov= new Thread(mv);
        mov.start();    
    }
    
    /**
     * Metodo que crea un objeto GloboVerde, lo situa en la ventana y genera el movimiento del mismo
     */
    public void crearGloboVerde(int tiempo){        
        GloboVerde globov = new GloboVerde();
        globoslista.add(globov);
        double posicionx = generarPosicionX();
        globov.fijarPosicion(posicionx);
           
        gpane.getChildren().addAll(globov);
        MoverGlobo mv = new MoverGlobo(globov,tiempo);
        mov= new Thread(mv);
        mov.start();          
    }
    
    
    /**
     * Metodo que crea un objeto GloboAmarillo, lo situa en la ventana y genera el movimiento del mismo
     */
    public void crearGloboAmarillo(int tiempo){        
        GloboAmarillo globoa = new GloboAmarillo();
        globoslista.add(globoa);
        double posicionx = generarPosicionX();
        globoa.fijarPosicion(posicionx);
        gpane.getChildren().addAll(globoa);
        MoverGlobo mv = new MoverGlobo(globoa,tiempo);
        mov= new Thread(mv);
        mov.start();   
    }
    
    
    
    /**
     * Metodo que crea un objeto GloboMalo, lo situa en la ventana y genera el movimiento del mismo
     */
    public void crearGloboMalo(int tiempo){
        GloboMalo globom = new GloboMalo();
        globoslista.add(globom); 
        double posicionx = generarPosicionX();
        globom.fijarPosicion(posicionx);
        gpane.getChildren().addAll(globom);
        MoverGlobo mv = new MoverGlobo(globom,tiempo);
        mov= new Thread(mv);
        mov.start();
    }
    
    private class HiloCrearGlobosMalos implements Runnable{
        @Override
        public void run() {
            while(tiempoJuego!=0&&activo){
                Platform.runLater(()->crearGloboMalo(7));
                    try {
                    Thread.sleep(5000);
                } catch (InterruptedException ex) {
                     Utilities.reportError(ex);
                     Thread.currentThread().interrupt();
                }                                
            }
        }        
    }
    
    /**
     * Hilo que crea varios globos al mismo tiempo según las especificaiciones del nivel FACIL
     */
         
    private class HiloCrearGlobosFacil implements Runnable{        
        @Override
        public void run() {
            while(tiempoJuego!=0&&activo){
                Platform.runLater(()->{                     
                    crearGloboVerde(10);
                    crearGloboAmarillo(10);
                    });
                    try {
                    Thread.sleep(2000);
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }                                
            }
        }        
    }   
    /**
     * Hilo que crea varios globos al mismo tiempo según las especificaiciones del nivel MEDIO
     */         
    private class HiloCrearGlobosMedio implements Runnable{

        @Override
        public void run() {
            while(tiempoJuego!=0&&activo){
                Platform.runLater(()->{                                         
                    crearGloboRojo(9);
                    crearGloboVerde(9);
                    crearGloboAmarillo(9);
                    });
                    try {
                    Thread.sleep(2000);
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }                                
            }
        }        
    }    
    /**
     * Hilo que crea varios globos al mismo tiempo según las especificaiciones del nivel DIFICIL
     */         
    private class HiloCrearGlobosDificil implements Runnable{
        @Override
        public void run() {
            while(tiempoJuego!=0&&activo){
                Platform.runLater(()->{                     
                    crearGloboVerde(7);
                    crearGloboAmarillo(7);
                    crearGloboRojo(7);                    
                    });
                    try {
                    Thread.sleep(2000);
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }                               
            }
        }        
    }
    
    /**
     * Hilo que crea cuenta el tiempo de juego y lo muestra por la ventana
     */
         
    private class HiloTiempo implements Runnable{

        @Override
        public void run() {
            
            
            while(tiempoJuego!=0&&activo){
                tiempoJuego-=1;
                Platform.runLater(()->tiempo.setText(String.valueOf(tiempoJuego)));
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
            }
            finalizarJuego();
        }
    }
    /**
     * Inicia el juego según la dificultad pasada como parámetro
     * @param d Dificultad del nivel 
     */
    public void iniciarJuego(Dificultad d){
        Thread cuentareg= new Thread(new HiloTiempo());
        cuentareg.start(); 
        switch(d){
            case FACIL:                 
                Thread thfacil = new Thread(new HiloCrearGlobosFacil());                                
                thfacil.start();                                                           
                break;
            case MEDIO:
                Thread thmedio = new Thread(new HiloCrearGlobosMedio());                                
                thmedio.start();                 
                break;                
            default :
                Thread thdif = new Thread(new HiloCrearGlobosDificil()); 
                Thread gm = new Thread(new HiloCrearGlobosMalos() );
                gm.start();
                thdif.start();                  
                            
        }                                           
    }
    
    public void eliminarLetrasAusentes(){        
        Iterator it = letrasObtenidas.keySet().iterator();
        while(it.hasNext()){
            if (letrasObtenidas.get(it.next())==0) {
                it.remove();
            }
            
        }
    }
    
    public void deshabilitarGlobos(){
        Iterator <Globo> iterator = globoslista.iterator();
            while(iterator.hasNext()){
                Globo g = iterator.next();
                g.setOnScreen(false);
                
            }
    }    
    
    public static void finalizarJuego1(){
        activo = false;        
    }
    
    public static boolean getActivo(){
        return activo;
    }
    
}

