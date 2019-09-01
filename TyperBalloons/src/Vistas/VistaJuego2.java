/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;

import Modelo.Dificultad;
import utilities.CONSTANTES;
import Modelo.Juego;
import Modelo.Score;
import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import utilities.Utilities;



/**
 *
 * @author joangie
 */
public class VistaJuego2 {
    private Pane root;
    private Pane onRoot;
    private Juego pd;
    private Label tiempo;
    private int tiempoTranscurrido=0;
    private Score score; 
    private Integer pTotal= 0;
    private VBox playerLetters;
    private boolean terminarJuego=false;
/**
 * Constructor de la vista 2 del juego
 *
 * @param pd 
 */
    public VistaJuego2(Juego pd){
    root = elementos(pd); 
    this.pd=pd;
    }
 
 /**
  * Metodo que finaliza el juego
  */   
    public void finalizarJuego(){
        terminarJuego=true;
    }
    
    /**
     * Crea los elementos del juego
     * @param pd informacion del Juego 
     * @return Pane con elementos del juego
     */
    public Pane elementos(Juego pd){
        onRoot = new Pane();
        
        HashMap<String,Integer> game_words= pd.getGame_words(); 
        HashMap<String,Integer> player_l= pd.getPlayer_l();
        Set <String> player_unique = new HashSet<>();
        TreeMap<Dificultad,TreeSet<Score>> scores_players= pd.getScoresF(); 
        
        Font theFont = Font.font("Helvetica", FontWeight.BOLD,24);
        Font tFont = Font.font("Helvetica", FontWeight.BOLD,32);

        //tiempo
        HBox t= new HBox();
        Label lt = new Label("Tiempo");
        lt.setFont(theFont);
        tiempo = new Label("0");
        tiempo.setFont(theFont);
        t.getChildren().addAll(lt,tiempo); 
        
        //creando espacio
        onRoot.setPadding(new Insets(20,0,20,20));
        //un background
        Image bG = new Image(CONSTANTES.RUTA_IMGS+"BG_03.png");
        ImageView bGView = new ImageView(bG);
        //Creando la parte donde aparecen las letras ((izq))
        ScrollPane sp = new ScrollPane(); //Para facilitar la visualizacion crearemos un scrollpane 
        playerLetters= new VBox(); 
        HBox titulo= new HBox();
        Label letra= new Label("Letra");
        Label stock= new Label("Stock");
        titulo.getChildren().addAll(letra,stock);
        letra.setFont(tFont);
        stock.setFont(tFont);
        //modificaciones
        titulo.setLayoutX(40);
        titulo.setLayoutY(20);
        titulo.setSpacing(40);
        //Parte derecha
        Image tf = new Image(CONSTANTES.RUTA_IMGS+"TextBox.png",200,100,false,false);
        ImageView tfView = new ImageView(tf);
        Label l1 = new Label("Ingrese su palabra");
        TextField player_word= new TextField(); 
        player_word.setPrefSize(100, 200);
        player_word.setBackground(Background.EMPTY);
        Label l2= new Label("");
        l2.setLayoutX(410);
        l2.setLayoutY(160);
        l1.setLayoutX(450);
        l1.setLayoutY(210);
        l1.setFont(theFont); l2.setTextFill(Color.WHITE);
        l2.setFont(theFont); l2.setTextFill(Color.RED);

        
        for(String s: player_l.keySet()){
            HBox box= new HBox();
            Label letter= new Label(s);
            Label num= new Label(Integer.toString(player_l.get(s))); 
            box.getChildren().addAll(letter,num);
            playerLetters.getChildren().add(box);
            box.setSpacing(80); 
            letter.setFont(theFont);
            num.setFont(theFont);
        }
        
        sp.setContent(playerLetters);
        sp.setPrefSize(USE_COMPUTED_SIZE,400);
        sp.setLayoutX(40);
        playerLetters.setSpacing(30);
        sp.setLayoutY(100);
        
         //arreglar para el fondo jajaj
        sp.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        sp.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        playerLetters.setPadding(new Insets(20));
        Pane root = new Pane();
        root.getChildren().addAll(bGView,titulo,sp,tfView,player_word,t,l1,l2,onRoot);
        
        //parte der arreglando probablemente se deba metar en un pane para mejorar vbox pero solo estaba probando cositas
        tfView.setLayoutX(430);
        tfView.setLayoutY(240);
        player_word.setLayoutX(440);
        player_word.setLayoutY(180);
        
        iniciarJuego(player_word,player_unique,game_words,player_l,theFont,l2);
        return root;
        
    }
    
    /**
     * Inicia el juego para que el usuario pueda dar enter al escribir una palabra
     * @param player_word palabra que ingresa el usuario en text field
     * @param player_unique set de palabras que ha ingresado el usuario
     * @param game_words diccionario de palabras del juego
     * @param player_l un hashMap con las letras del jugador y su stock
     * @param theFont 
     * @param l2 Label que indicará al jugador si está ingresando palabras correctas
     */
    public void iniciarJuego(TextField player_word, Set<String> player_unique, HashMap<String,Integer> game_words,HashMap<String,Integer> player_l,Font theFont, Label l2){
        Thread timer = new Thread(new HiloTiempo());
        timer.start();
        player_word.setOnKeyPressed((e)->{
              if(e.getCode().equals(KeyCode.ENTER)&& tiempoTranscurrido<30){
                  String user_word = player_word.getText().toLowerCase(); //obteniendo palabra
                  int disponible= 0;  //variable que permite si tiene esas letras disponibles
                  if(!player_unique.contains(user_word)&& game_words.containsKey(user_word)){
                  player_unique.add(user_word); 
                  System.out.println(player_unique);
                    for(int w= 0; w<user_word.length();w++){
                    String u= Character.toString(user_word.charAt(w)).toLowerCase(); 
                        if(player_l.containsKey(u)&&player_l.get(u)>0){
                        disponible+=1; 
                        player_l.replace(u,player_l.get(u)-1);
                        Iterator<Node> itr= playerLetters.getChildren().iterator();
                            while(itr.hasNext()){
                            HBox h= (HBox)itr.next();
                            ObservableList<Node> a = h.getChildren();
                               if((((Label)a.get(0)).getText()).equals(u)){
                                   System.out.println(player_l.get(u));
                                   if(player_l.get(u)!=0){
                                    Label n= new Label(String.valueOf(player_l.get(u))); 
                                    n.setFont(theFont);
                                    n.setTextFill(Color.BLACK);
                                    h.getChildren().set(1, n); 
                                   }else{
                                       itr.remove(); 
                                   }
                               }    
                            }}}
                            if(disponible==user_word.length()&& tiempoTranscurrido<30){
                                l2.setText("Nice!");
                                System.out.println(tiempoTranscurrido);
                             pTotal= pTotal +game_words.get(user_word);
                                System.out.println(pTotal);
                            }else{
                            l2.setText("No tenias una letra :(");
                            }
                       }else if(!game_words.containsKey(user_word)){
                                l2.setText("Woops! Esa palabra no existe!");
                       }else if(user_word.length()==0){
                           l2.setText("Vacio! Piensa mejor");
                       }else{
                        l2.setText("Woops! ya ingresaste esa palabra");
                    }
            player_word.clear();
            }});
    }
    
    /**
     * Contador del tiempo para la duracion del juego
     */
    private class HiloTiempo implements Runnable{
        @Override
        public void run() {
            while(!terminarJuego&&tiempoTranscurrido<10){
                tiempoTranscurrido+=1;
                Platform.runLater(()->{
                    tiempo.setText(String.valueOf(tiempoTranscurrido)); 
                });            
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    showTrabajando();
                    System.exit(0);
                }
            }
            finalizarJuego();
                Platform.runLater(()->{

                Pane Over = new Pane();
                ImageView ini = new ImageView(new Image(CONSTANTES.RUTA_IMGS+"BG_IJ02.png"));

                Label ltitulo = new Label("Gracias por jugar!");
                ltitulo.setTextFill(Color.WHITE);
                ltitulo.setFont(CONSTANTES.FUENTE);
                
                Font thef= Font.font("Helvetica", FontWeight.BOLD,16);
                VBox vf = new VBox(); 
                vf.setLayoutX(90);
                vf.setLayoutY(70);
                vf.setPadding(new Insets(10, 10, 10, 10));  
                vf.setPrefSize(USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
                
                Label puntuacion = new Label("Ha alcanzado la siguiente puntuacion: "+String.valueOf(pTotal));
                Label finished= new Label("Ha terminado el juego, por favor ingrese su nombre");
                Button submit= new Button("submit");
                TextField userData = new TextField(); 
                HBox userInfo= new HBox();
                userInfo.getChildren().addAll(userData,submit);
                puntuacion.setFont(thef); puntuacion.setTextFill(Color.WHITE);
                finished.setFont(thef);finished.setTextFill(Color.WHITE);
                HBox buttons = new HBox();
                Pane shareScore = utilities.Utilities.boton("Share","GreenButton");
                Pane continuar= utilities.Utilities.boton("Continuar", "GreenButton");
                buttons.getChildren().addAll(shareScore,continuar); 
                vf.setSpacing(20);
                buttons.setSpacing(20);
                
                buttons.setPadding(new Insets(30, 30, 30, 30));  

                vf.getChildren().addAll(ltitulo,puntuacion,finished,userInfo,buttons);

                continuar.setOnMouseClicked(e->{
                //regreso a ventana principal
                });
                
                shareScore.setOnMouseClicked(e->{
                    
                    try {
                        
                    String p= String.valueOf(pTotal);
                    Desktop.getDesktop().browse(new URI("https://twitter.com/intent/tweet?hashtags=TyperBallon&text=He+alcanzado+"+p+"+puntos&via=BalloonsTyper"));
                    }catch (URISyntaxException | IOException ex) {
                    showTrabajando();
                    }
                });

                //Guardando info de usuario
                submit.setOnMouseClicked(e->{
                        submit.setDisable(true);
                        pd.getPlayer_score().setNombre(userData.getText());
                        pd.getPlayer_score().setPuntaje(pTotal);
                        try {
                            TreeMap<Dificultad,TreeSet<Score>> scoresGame= LeerArchivo();
                            if(scoresGame!=null){
                                System.out.println("Leyendo y escribiendo...");
                                escribir2(scoresGame);
                                System.out.println(scoresGame);
                            }else{
                                System.out.println("Escribiendo...");
                                System.out.println("No se sobreescribio ya fue");
                                System.out.println(scoresGame);
                            escribir1(); 
                            }
                            
                        } catch (FileNotFoundException ex) {
                            showPrimerJugador();
                    }
                });
               Over.getChildren().addAll(ini,vf);

                
                Over.setLayoutX(100);
                Over.setLayoutY(-500);
                Utilities.bajarCartel(onRoot, Over, 200);
                });
            
            }
        }
    
    public Pane getRoot() {
        return root;
    }
   
    /**
     * Método que sirve para Leer un archivo .dat y así agregar los nuevos puntajes de scores
     * @return TreeMap con clave dificulltad y value de TreeSet de Scores para así facilitar la generación
     * de una tabla de resultados
     * @throws FileNotFoundException 
     */
     public TreeMap<Dificultad,TreeSet<Score>> LeerArchivo() throws FileNotFoundException{
         TreeMap<Dificultad,TreeSet<Score>> scoresG; 
         try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("src/data/scores.dat"))){
                Score f= pd.getPlayer_score();
                scoresG = (TreeMap<Dificultad,TreeSet<Score>>)ois.readObject();
                Dificultad df= pd.getDf(); 
                if(scoresG.containsKey(df)){
                    scoresG.get(df).add(f); 
                }else{
                    TreeSet<Score> scorep= new TreeSet();
                    scorep.add(f); 
                    scoresG.put(df, scorep); 
                }
            return scoresG;
            
            }catch (IOException ex) {
            showPrimerJugador();
            } catch (ClassNotFoundException ex) {
            showTrabajando();
        }
         return null;
     }
     
     /**
     * Método que sirve para escribir los resultados en un archivo .dat para asi generar una tabla de puntajes
     */
    private void escribir1(){
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("src/data/scores.dat"))){
            Score f= pd.getPlayer_score();
            
            TreeMap<Dificultad,TreeSet<Score>> scoresG = new TreeMap();
            scoresG.put(Dificultad.FACIL, new TreeSet<>());
            scoresG.put(Dificultad.MEDIO, new TreeSet<>());
            scoresG.put(Dificultad.DIFICIL, new TreeSet<>());
            scoresG.get(pd.getDf()).add(f); 
            oos.writeObject(scoresG);
            
            
        }catch (FileNotFoundException ex) {
                showTrabajando();
        }catch (IOException ex) {
                showTrabajando();
        }
    }
    
    /**
     * Método para sobreescribir sobre los scores del juego
     * @param scoregame Scoregame es un TreeMap que sirve para separar los scores por Dificultad
     * contiene como key la dificultad del juego y como value un TreeSet de scores para ordenarlos 
     * de mayor a menor
     */
     private void escribir2(TreeMap<Dificultad,TreeSet<Score>> scoregame){
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("src/data/scores.dat"))){
            oos.writeObject(scoregame);
        } catch (FileNotFoundException ex) {
            showTrabajando();
        } catch (IOException ex) {
            showTrabajando();
        }
    }
     
    /**
     * Método para manejar ciertas excepciones
     */
    private void showTrabajando() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("WOOPS!");
        alert.setContentText("Dificultades tecnicas, estamos trabajando en ello");
        alert.showAndWait();
    }
    
    /**
     * Método para mostrar mensaje a nuestro primer jugador
     */
    private void showPrimerJugador() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("GRACIAS!");
        alert.setContentText("Eres nuestro primer jugador!");
        alert.showAndWait();
    }
}
  
