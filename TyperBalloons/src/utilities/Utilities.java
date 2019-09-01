/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;



import java.io.File;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import controlador.Controlador;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase que contiene herramientas utiles que serviran a lo largo del desarrollo del programa.
 * @author Alex Velez
 */
public final class Utilities {
    private static MediaPlayer mp;
    private Utilities(){}
    private static Image imgBlock=new Image(CONSTANTES.RUTA_IMGS+"BG_Block.png");
    /**
     * Metodo que sirve para hacer una trancision entre dos roots pane en la misma escena
     * En la que aparecen muchos globos flotando hacia arriba.
     * @param root Root a reemplazar
     * @param root2 Root que reemplaza
     */
    public static void transition(Pane root,Pane root2){
        ImageView block = new ImageView(imgBlock);
        block.setOpacity(0);
        ImageView transicion = new ImageView(new Image(CONSTANTES.RUTA_IMGS+"Transicion.png"));
        
            transicion.setLayoutY(600);
            root.getChildren().addAll(block,transicion);
            
            Thread tr = new Thread(()->{
                for (int i=0;i<2080;i++){
                        Platform.runLater(()->transicion.setLayoutY(transicion.getLayoutY()-1));
                        if(i==1000){
                            Platform.runLater(()->{
                                root.getChildren().remove(transicion);
                                root2.getChildren().add(transicion);
                                Controlador.getScene().setRoot(root2);
                                
                                
                            });
                            
                        }
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Utilities.class.getName()).log(Level.SEVERE, null, ex);
                        Thread.currentThread().interrupt();
                    }
                }
                Platform.runLater(()->root2.getChildren().remove(transicion));
            });
            tr.start();
    }
    
    /**
     * Metodo que sirve para hacer una trancision entre dos roots pane en la misma escena
     * En la que se va opacando el primer root y luego va aclarandose el otro.
     * @param root1 Root a reemplazar
     * @param root2 Root que reemplaza
     */
    public static void transition2(Pane root1,Pane root2){
        
        Thread t = new Thread(()->{
            ImageView block = new ImageView(imgBlock); 
                block.setOpacity(0);
                Platform.runLater(()->root1.getChildren().add(block));
                for(int i =1; i<=10;i++){
                    try {
                        Thread.sleep(40);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Utilities.class.getName()).log(Level.SEVERE, null, ex);
                        Thread.currentThread().interrupt();
                    }
                    block.setOpacity(i*0.1);
                    
                }
                Platform.runLater(()->{
                    root1.getChildren().remove(block);
                    root2.getChildren().add(block);
                    Controlador.getScene().setRoot(root2);
                
                });
                
                
                for(int i =10; i>=0;i--){
                    try {
                        Thread.sleep(40);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Utilities.class.getName()).log(Level.SEVERE, null, ex);
                        Thread.currentThread().interrupt();
                    }
                    block.setOpacity(i*0.1);
                    
                }
                Platform.runLater(()->root2.getChildren().remove(block));
        });
        t.start();
    }
    
    /**
     * Metodo que sirve para hacer la animacion de bajar un cartel desde arriba
     * @param onRoot Root que en el que estara contenido el cartel
     * @param cartel Pane con el cartel que deseamos mover
     * @param cantidad entero con la cantidad de pixeles multiplicado por 3
     * que deseemos que nuestro cartel baje
     */
    public static void bajarCartel(Pane onRoot,Pane cartel,int cantidad){
        ImageView block = new ImageView(imgBlock);
            block.setOpacity(0.3);
            onRoot.getChildren().addAll(block,cartel);
            onRoot.setOnKeyPressed(e1->{
                if(e1.getCode()==KeyCode.ESCAPE)
                onRoot.getChildren().clear();
            });
            
            Thread t = new Thread(()->{
                for(int i=0;i<cantidad;i++){
                        Platform.runLater(()->cartel.setLayoutY(cartel.getLayoutY()+3));
                        try {
                            Thread.sleep(1);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(Utilities.class.getName()).log(Level.SEVERE, null, ex);
                            Thread.currentThread().interrupt();
                        }
                    }
            });
            t.start();
    }
    
    /**
     * Metodo que crea un pane con la animacion de un boton.
     * @param nameImg String con el nombre de la imagen que tiene la imagen que queremos
     * que haga de boton
     * @return Pane con el boton creado y todas sus funcionalidades
     */
    public static Pane boton(String nameImg){
        Pane pBoton = new  Pane();
        Image imgButton = new Image(CONSTANTES.RUTA_IMGS+nameImg+".png");
        Image imgButtonPressed = new Image(CONSTANTES.RUTA_IMGS+nameImg+"Pressed.png");
        ImageView imgVButton = new ImageView(imgButton);
        pBoton.setOnMousePressed(e->{
            imgVButton.setImage(imgButtonPressed);
            if(Controlador.isSondEsp()){
                Media musicFile = new Media(new File(CONSTANTES.RUTA_SOUNDS+"chestClick.mp3").toURI().toString());
                mp = new MediaPlayer(musicFile);
                mp.play();
            }
        });
        pBoton.setOnMouseReleased(e->imgVButton.setImage(imgButton));
        pBoton.getChildren().add(imgVButton);
        return pBoton;
    }
    
    /**
     * Metodo que crea un pane con la animacion de un boton, en el que tiene texto encima.
     * @param nameImg String con el nombre de la imagen que tiene la imagen que queremos
     * que haga de boton
     * @param msg String con el mensaje que deseemos que ponga encima del boton
     * @return Pane con el boton con el mensaje creado y todas sus funcionalidades
     */
    public static Pane boton(String msg,String nameImg){
        Pane pMsg = boton(nameImg);
        ImageView ima = (ImageView) pMsg.getChildren().get(0);
        Label lmsg = new Label(msg);
        
        lmsg.setMinSize(ima.getImage().getWidth(),ima.getImage().getHeight());
        lmsg.setAlignment(Pos.CENTER);
        lmsg.setFont(CONSTANTES.FUENTE2);
        lmsg.setTextFill(Color.WHITE);
        pMsg.getChildren().add(lmsg);

        
        return pMsg;
    }
}
