/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;

import Modelo.Dificultad;
import Modelo.ExcepcionPalabraNoValida;
import utilities.CONSTANTES;
import Modelo.Juego;
import Modelo.Score;
import controlador.Controlador;
import java.awt.Desktop;
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
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;
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
    private final Juego pd;
    private Label tiempo;
    private int tiempoTranscurrido;
    private Integer pTotal;
    private VBox playerLetters;
    private boolean terminarJuego=false;
    private final HashMap<String,Integer> game_words;
    private HashMap<String,Integer> player_l; 
    
    
    
/**
 * Constructor de la vista 2 del juego
 *
 * @param pd 
 */
    public VistaJuego2(Juego pd){
    tiempoTranscurrido=30;
    pTotal=0;
    game_words= pd.getGame_words(); 
    root = elementos(pd); 
    this.pd=pd;
    }
 
    private ScrollPane createSP(){
        ScrollPane sp = new ScrollPane(); //Para facilitar la visualizacion crearemos un scrollpane 
        playerLetters= new VBox(); 
        
        for(HashMap.Entry<String,Integer> s: player_l.entrySet()){
            HBox box= new HBox();
            Label letter= new Label(s.getKey());
            Label num= new Label(Integer.toString(s.getValue())); 
            letter.setId(s.getKey());
            box.setId("box de"+s.getKey());
            box.getChildren().addAll(letter,num);
            playerLetters.getChildren().add(box);
            playerLetters.setSpacing(30);
            box.setSpacing(80); 
            letter.setFont(CONSTANTES.FUENTEJ2);
            num.setFont(CONSTANTES.FUENTEJ2);
        }
            sp.setContent(playerLetters);
            sp.setPrefSize(USE_COMPUTED_SIZE,400);
            sp.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
            sp.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
            playerLetters.setPadding(new Insets(20));
            
        return sp; 
    }
    /**
     * Crea los elementos del juego
     * @param pd informacion del Juego 
     * @return Pane con elementos del juego
     */
    private Pane elementos(Juego pd){
        onRoot = new Pane();
        player_l= pd.getPlayer_l();
        //tiempo
        HBox t= new HBox();
        Label lt = new Label("Tiempo");
        lt.setFont(CONSTANTES.FUENTEJ);
        tiempo = new Label("0");
        tiempo.setFont(CONSTANTES.FUENTEJ);
        t.getChildren().addAll(lt,tiempo); 
        
        //creando espacio
        onRoot.setPadding(new Insets(20,0,20,20));
        //un background
        ImageView bGView=null;
        if (Controlador.getSkin()==1) {
            bGView = new ImageView(new Image(CONSTANTES.RUTA_IMGS+"BG_02.png"));
        }else{
            bGView = new ImageView(new Image(CONSTANTES.RUTA_IMGS+"BG_04.png"));
        }                              
        
        //Creando la parte donde aparecen las letras ((izq))
        HBox titulo= new HBox();
        Label letra= new Label("Letra");
        Label stock= new Label("Stock");
        titulo.getChildren().addAll(letra,stock);
        letra.setFont(CONSTANTES.FUENTEJ);
        stock.setFont(CONSTANTES.FUENTEJ);
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
        l1.setFont(CONSTANTES.FUENTEJ); 
        l2.setTextFill(Color.WHITE);
        l2.setFont(CONSTANTES.FUENTEJ); 
        l2.setTextFill(Color.RED);
        ScrollPane sp= createSP();
        sp.setLayoutX(40);
        sp.setLayoutY(100);
         //arreglar para el fondo jajaj
        Pane p2 = new Pane();
        p2.getChildren().addAll(bGView,titulo,sp,tfView,player_word,t,l1,l2,onRoot); 
        //parte der arreglando probablemente se deba metar en un pane para mejorar vbox pero solo estaba probando cositas
        tfView.setLayoutX(430);
        tfView.setLayoutY(240);
        player_word.setLayoutX(440);
        player_word.setLayoutY(180);
        iniciarJuego(player_word,l2);
        return p2;
        
    }
    /**
     * Inicia el juego para que el usuario pueda dar enter al escribir una palabra
     * @param player_word palabra que ingresa el usuario en text field
     * @param player_unique set de palabras que ha ingresado el usuario
     * @param l2 Label que indicará al jugador si está ingresando palabras correctas
     */
    public void iniciarJuego(TextField player_word, Label l2){
        Thread timer = new Thread(new HiloTiempo());
        timer.start();
        Set <String> player_unique = new HashSet<>();
        player_word.setOnKeyPressed((e)->{
            if(e.getCode().equals(KeyCode.ENTER)&&tiempoTranscurrido!=0){
                String user_word = player_word.getText().toLowerCase(); //obteniendo palabra
                try{
                  if(disponible(user_word,player_unique,l2)){
                  removeChar(user_word);
                  pTotal= pTotal +game_words.get(user_word);
                  l2.setText("Nice!");
                  
                  }else{
                     throw(new ExcepcionPalabraNoValida("no ingreso letra valida"));
                  }
                   player_word.clear();
                }catch(ExcepcionPalabraNoValida ex) {
                    l2.setText("Vuelve a tratar");
                    player_word.clear();

                } 
            }
        });}
    
    public void removeChar(String user_word){
        for(int w= 0; w<user_word.length();w++){
                String u= Character.toString(user_word.charAt(w)); 
                Iterator<Node> itr= playerLetters.getChildren().iterator();
                while(itr.hasNext()){
                HBox h= (HBox)itr.next();
                if(((Label)h.getChildren().get(0)).getText().equals(u)){ 
                    if (player_l.get(u)!=0){
                    Label n= new Label(String.valueOf(player_l.get(u))); 
                    n.setFont(CONSTANTES.FUENTEJ);
                    n.setTextFill(Color.BLACK);
                    h.getChildren().set(1, n); 
                    }else{
                        itr.remove(); 
                }}}}
    }
        
    public boolean disponible(String user_word, Set<String> player_unique,Label l2){
            int disponible= 0;  //variable que permite si tiene esas letras disponibles
            if(!player_unique.contains(user_word)&& game_words.containsKey(user_word)&&user_word!=null){
               player_unique.add(user_word); 
                for(int w= 0; w<user_word.length();w++){
                String u= Character.toString(user_word.charAt(w)).toLowerCase(); 
                    if(player_l.containsKey(u)&&player_l.get(u)>0){
                    disponible+=1; 
                    player_l.replace(u,player_l.get(u)-1);
                    }}
                
                return user_word.length()==disponible;   
                }
                  return false;
                }
            
    
    /**
     * Contador del tiempo para la duracion del juego
     */
    private class HiloTiempo implements Runnable{
        @Override
        public void run() {
            while(!terminarJuego&&tiempoTranscurrido>0){
                tiempoTranscurrido-=1;
                Platform.runLater(()->{
                    tiempo.setText(String.valueOf(tiempoTranscurrido)); 
                });            
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(VistaJuego2.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            finalizarJuego();
                
            }
        }
    
    public Pane getRoot() {
        return root;
    }

     /**
     * Método para sobreescribir sobre los scores del juego
     * @param scoregame Scoregame es un TreeMap que sirve para separar los scores por Dificultad
     * contiene como key la dificultad del juego y como value un TreeSet de scores para ordenarlos 
     * de mayor a menor
     */
     private void escribir2(TreeMap<Dificultad,TreeSet<Score>> scoregame){
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(CONSTANTES.GameData+".dat"))){
            scoregame.get(pd.getDf()).add(pd.getPlayer_score()); 
            oos.writeObject(scoregame);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(VistaJuego2.class.getName()).log(Level.SEVERE, null, ex);
            Utilities.reportError(ex);  
        } catch (IOException ex) {
            Logger.getLogger(VistaJuego2.class.getName()).log(Level.SEVERE, null, ex);
            Utilities.reportError(ex);  
        }
    }
    
      private void escribir1(){
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(CONSTANTES.GameData+".dat"))){
            TreeMap<Dificultad,TreeSet<Score>> scoresGvacio = new TreeMap();
            scoresGvacio.put(Dificultad.FACIL, new TreeSet<>());
            scoresGvacio.put(Dificultad.MEDIO, new TreeSet<>());
            scoresGvacio.put(Dificultad.DIFICIL, new TreeSet<>()); 
            scoresGvacio.get(pd.getDf()).add(pd.getPlayer_score()); 
            oos.writeObject(scoresGvacio);
        }catch (FileNotFoundException ex) {
            Logger.getLogger(VistaJuego2.class.getName()).log(Level.SEVERE, null, ex);
            Utilities.reportError(ex);  
        }catch (IOException ex) {
            Logger.getLogger(VistaJuego2.class.getName()).log(Level.SEVERE, null, ex);
            Utilities.reportError(ex);       
        }
    }
      
      
    /**
     * Método que sirve para Leer un archivo .dat y así agregar los nuevos puntajes de scores
     * @return TreeMap con clave dificulltad y value de TreeSet de Scores para así facilitar la generación
     * de una tabla de resultados
     * @throws FileNotFoundException 
     */
     public TreeMap<Dificultad,TreeSet<Score>> leerArchivo() throws FileNotFoundException{
         TreeMap<Dificultad,TreeSet<Score>> scoresG; 
         try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(CONSTANTES.GameData+".dat"))){
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
                Logger.getLogger(VistaJuego2.class.getName()).log(Level.SEVERE, null, ex);
                Utilities.reportError(ex);               
            }catch (ClassNotFoundException ex){
             Logger.getLogger(VistaJuego2.class.getName()).log(Level.SEVERE, null, ex);
             Utilities.reportError(ex);   
        }
            TreeMap<Dificultad,TreeSet<Score>> scoresGvacio = new TreeMap();
            scoresGvacio.put(Dificultad.FACIL, new TreeSet<>());
            scoresGvacio.put(Dificultad.MEDIO, new TreeSet<>());
            scoresGvacio.put(Dificultad.DIFICIL, new TreeSet<>()); 

            return scoresGvacio;
            
     }
     
      /**
    * Metodo que finaliza el juego
    */   
    public void finalizarJuego(){
        terminarJuego=true;
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
                    //sin continuar
                    VistaInicio inP= new VistaInicio();
                    onRoot.getChildren().clear();
                    utilities.Utilities.transition2(onRoot, inP.getRoot());
                });
                shareScore.setOnMouseClicked(e->{
                    try {
                    String p= String.valueOf(pTotal);
                    Desktop.getDesktop().browse(new URI("https://twitter.com/intent/tweet?hashtags=TyperBallon&text=He+alcanzado+"+p+"+puntos&via=BalloonsTyper"));
                    }catch (URISyntaxException | IOException ex) {
                    Logger.getLogger(VistaJuego2.class.getName()).log(Level.SEVERE, null, ex);
                    Utilities.reportError(ex);                    
                    }
                });

                //Guardando info de usuario
                submit.setOnMouseClicked(e->{
                        submit.setDisable(true);
                        pd.getPlayer_score().setNombre(userData.getText());
                        pd.getPlayer_score().setPuntaje(pTotal);
                        try{
                            TreeMap<Dificultad,TreeSet<Score>> scoresGame= leerArchivo();
                                escribir2(scoresGame);
                            
                        }catch (FileNotFoundException ex){
                            escribir1();
                            Logger.getLogger(VistaJuego2.class.getName()).log(Level.SEVERE, null, ex);
                        }
                });
               Over.getChildren().addAll(ini,vf);
               Over.setLayoutX(100);
               Over.setLayoutY(-500);
               Utilities.bajarCartel(onRoot, Over, 200);
              });
            
    }
}
  
