/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;

import utilities.CONSTANTES;
import Modelo.Juego;
import Modelo.Score;
import data.GameData;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;



/**
 *
 * @author joangie
 */
public class VistaJuego2 {
    private Pane root;
    private Juego pd;
    private GameData gameData; 
    private Label tiempo;
    private int tiempoTranscurrido=0;
    private Score score; 
    private Integer pTotal= 0;
    private boolean terminado= false;


    public VistaJuego2(Juego pd, GameData gameData){
        
          
        Font theFont = Font.font("Helvetica", FontWeight.BOLD, 24 );
        this.pd=pd;
        this.gameData= gameData;
        HashMap<String,Integer> game_words= gameData.cargarPalabras(); 
        HashMap<String,Integer> player_l= pd.getPlayer_l();
        Set <String> player_unique = new HashSet<>();
        
        //tiempo
        HBox t= new HBox();
        Label lt = new Label("Tiempo");
        lt.setFont(theFont);
        tiempo = new Label("0");
        tiempo.setFont(theFont);
        t.getChildren().addAll(lt,tiempo); 
        
        
        //creando espacio
        root = new Pane();
        root.setPadding(new Insets(20,0,20,20));
        //un background
        Image bG = new Image(CONSTANTES.RUTA_IMGS+"BG_04.png");
        ImageView bGView = new ImageView(bG);
        
        //Creando la parte donde aparecen las letras ((izq))
        ScrollPane sp = new ScrollPane(); //Para facilitar la visualizacion crearemos un scrollpane 
        VBox playerLetters= new VBox(); 
        HBox titulo= new HBox();
        Label letra= new Label("Letra");
        Label stock= new Label("Stock");
        
        titulo.getChildren().addAll(letra,stock);
        
        //modificaciones
        titulo.setLayoutX(40);
        titulo.setLayoutY(20);
        titulo.setSpacing(40);
        //playerLetters.getChildren().add(titulo); si funciono pero we se puede modificar aun
        
        //Parte derecha
        
        
        Image tf = new Image(CONSTANTES.RUTA_IMGS+"TextBox.png",200,100,false,false);
        ImageView tfView = new ImageView(tf);
        Label l1 = new Label("Ingrese su palabra");
        TextField player_word= new TextField(); 
        player_word.setPrefSize(100, 200);
        player_word.setBackground(Background.EMPTY);
        Button enviar = new Button("Enviar"); 
        Button iniciar= new Button("Iniciar");
        Label l2= new Label("");
        l2.setLayoutX(410);
        l2.setLayoutY(160);
        l1.setLayoutX(450);
        l1.setLayoutY(210);
        //der.getChildren().addAll(l1,player_word);

        try {
            
            Font f = Font.loadFont(new FileInputStream(new File("src/myFonts/Top_p2.ttf")), 24);
            letra.setFont(f);
            stock.setFont(f);
        
        } catch (FileNotFoundException ex) {
            showTrabajando();
            System.exit(0); 
            
        }

        for(String s: player_l.keySet()){
            HBox box= new HBox();
            Label letter= new Label(s);
            Label num= new Label(Integer.toString(player_l.get(s))); 
            box.getChildren().addAll(letter,num);
            playerLetters.getChildren().add(box);
            box.setSpacing(80); 
            try {
            Font play_letter = Font.loadFont(new FileInputStream(new File("src/myFonts/player_letters.ttf")), 50);
            letter.setFont(play_letter);letter.setTextFill(Color.WHEAT);
            num.setFont(play_letter); num.setTextFill(Color.WHEAT);
            
            
            } catch (FileNotFoundException ex) {
                showTrabajando();
                System.exit(0);
            }
            
        }
        
       
        sp.setContent(playerLetters);
        
        
        sp.setPrefSize(USE_COMPUTED_SIZE,500);
        sp.setLayoutX(40);
        playerLetters.setSpacing(30);
        sp.setLayoutY(100);
        ; //arreglar para el fondo jajaj
        sp.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        sp.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        playerLetters.setPadding(new Insets(20));

        root.getChildren().addAll(bGView,titulo,sp,tfView,player_word,enviar,iniciar,t,l1,l2);
        
        //parte der arreglando probablemente se deba metar en un pane para mejorar vbox pero solo estaba probando cositas
        tfView.setLayoutX(430);
        tfView.setLayoutY(240);
        player_word.setLayoutX(440);
        player_word.setLayoutY(180);
        
        enviar.setLayoutX(500); 
        enviar.setLayoutY(340);
        iniciar.setLayoutX(430);
        iniciar.setLayoutY(340);
        
        iniciar.setOnMouseClicked((e)->{
            iniciar.setDisable(true);
            Thread timer = new Thread(new HiloTiempo());
            timer.start();
           
            enviar.setOnMouseClicked((b)->{
            Thread time= new Thread(new HiloTiempo());
            String user_word = player_word.getText(); //validar mejor cuando ya salgan keys negativas

            int disponible= 0; 
                    if(!player_unique.contains(user_word.toLowerCase())&& game_words.containsKey(user_word.toLowerCase()) && tiempoTranscurrido<30){
                        player_unique.add(user_word.toLowerCase());
                         System.out.println(player_unique);
                          for(int w= 0; w<user_word.length();w++){
                               String u= Character.toString(user_word.charAt(w)).toLowerCase(); 
                               
                            if(player_l.containsKey(u)&&player_l.get(u)>0){
                                disponible+=1; 
                                player_l.replace(u,player_l.get(u)-1);
                                
                        }}
                          if(disponible==user_word.length()&& tiempoTranscurrido<30){
                             l2.setText("Nice!");
                             pTotal= pTotal +game_words.get(user_word);
                          }else{
                           l2.setText("No tenias una letra :(");
                          }
                         

                     }else if(!game_words.containsKey(user_word.toLowerCase())){
                                l2.setText("Woops! Esa palabra no existe!");
                     }else if(tiempoTranscurrido==30){
                         l2.setText("Woops! Fuera de tiempo");

                    }else{
                        l2.setText("Woops! ya ingresaste esa palabra");
                    }
            
            player_word.clear();
            System.out.println(pTotal);
            System.out.println(player_l);
                if(terminado){
                enviar.setDisable(true);
                }
            
            }); //el boton enviar
            
            
            }); //boton iniciar
        
    }
             
    private class HiloTiempo implements Runnable{

        @Override
        public void run() {
            while(tiempoTranscurrido<30){
                tiempoTranscurrido+=1;
                Platform.runLater(()->{
                    tiempo.setText(String.valueOf(tiempoTranscurrido)); 
                    
                });
                
                if(tiempoTranscurrido==30){
                    terminado=true;
                }
                                
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    showTrabajando();
                    System.exit(0);

                }
            }
    }
    }


    public Pane getRoot() {
        return root;
    }
    
     private void showTrabajando() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("WOOPS!");
        alert.setContentText("Dificultades tecnicas, estamos trabajando en ello");
        alert.showAndWait();
    }
}
  
