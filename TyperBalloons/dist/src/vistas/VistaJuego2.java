/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import modelo.Dificultad;
import modelo.ExcepcionPalabraNoValida;
import utilities.CONSTANTES;
import modelo.Juego;
import modelo.Score;
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
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
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
    private static boolean terminarJuego;
    private final Map<String,Integer> gameWords;
    private Map<String,Integer> playerL; 
    
    
    
/**
 * Constructor de la vista 2 del juego
 *
 * @param pd 
 */
    public VistaJuego2(Juego pd){
    
    tiempoTranscurrido=30;
    pTotal=0;
    gameWords= pd.getGameWords(); 
    root = elementos(pd); 
    this.pd=pd;
    }
 
    /**
     * Metodo que crea un scrollpane para las letras que tendra el usuario
     * @return ScrollPane con las letras y el stock del jugador
     */
    private ScrollPane createSP(){
        ScrollPane sp = new ScrollPane(); //Para facilitar la visualizacion crearemos un scrollpane 
        playerLetters= new VBox(); 
        
        for(HashMap.Entry<String,Integer> s: playerL.entrySet()){
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
    private static void iniciarJuego(){
        terminarJuego = false;
    }
    /**
     * Crea los elementos del juego
     * @param pd informacion del Juego 
     * @return Pane con elementos del juego
     */
    private Pane elementos(Juego pd){
        onRoot = new Pane();
        iniciarJuego();
        playerL= pd.getPlayerL();
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
        TextField playerWord= new TextField(); 
        playerWord.setPrefSize(100, 200);
        playerWord.setBackground(Background.EMPTY);
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
        p2.getChildren().addAll(bGView,titulo,sp,tfView,playerWord,t,l1,l2,onRoot); 
        //parte der arreglando probablemente se deba metar en un pane para mejorar vbox pero solo estaba probando cositas
        tfView.setLayoutX(430);
        tfView.setLayoutY(240);
        playerWord.setLayoutX(440);
        playerWord.setLayoutY(180);
        iniciarJuego(playerWord,l2);
        return p2;
        
    }
    /**
     * Inicia el juego para que el usuario pueda dar enter al escribir una palabra
     * @param playerword palabra que ingresa el usuario en text field
     * @param l2 Label que indicará al jugador si está ingresando palabras correctas
     */
    public void iniciarJuego(TextField playerWord, Label l2){
        Thread timer = new Thread(new HiloTiempo());
        timer.start();
        Set <String> playerUnique = new HashSet<>();
        playerWord.setOnKeyPressed(e->{
            if(e.getCode().equals(KeyCode.ENTER)&&tiempoTranscurrido!=0){
                String userWord = playerWord.getText().toLowerCase(); //obteniendo palabra
                try{
                  if(disponible(userWord,playerUnique)){
                  removeChar(userWord);
                  pTotal= pTotal +gameWords.get(userWord);
                  l2.setText("Nice!");
                  
                  }else{
                     throw(new ExcepcionPalabraNoValida("no ingreso letra valida"));
                  }
                   playerWord.clear();
                }catch(ExcepcionPalabraNoValida ex) {
                    l2.setText("Vuelve a tratar");
                    playerWord.clear();

                } 
            }
        });}
    
    public void removeChar(String userWord){
        for(int w= 0; w<userWord.length();w++){
                String u= Character.toString(userWord.charAt(w)); 
                Iterator<Node> itr= playerLetters.getChildren().iterator();
                while(itr.hasNext()){
                HBox h= (HBox)itr.next();
                if(((Label)h.getChildren().get(0)).getText().equals(u)){ 
                    if (playerL.get(u)!=0){
                    Label n= new Label(String.valueOf(playerL.get(u))); 
                    n.setFont(CONSTANTES.FUENTEJ2);
                    n.setTextFill(Color.BLACK);
                    h.getChildren().set(1, n); 
                    }else{
                        itr.remove(); 
                }}}}
    }
        
    public boolean disponible(String userWord, Set<String> playerUnique){
            int disponible= 0;  //variable que permite si tiene esas letras disponibles
            if(!playerUnique.contains(userWord)&& gameWords.containsKey(userWord)&&userWord!=null){
               playerUnique.add(userWord); 
                for(int w= 0; w<userWord.length();w++){
                String u= Character.toString(userWord.charAt(w)).toLowerCase(); 
                    if(playerL.containsKey(u)&&playerL.get(u)>0){
                    disponible+=1; 
                    playerL.replace(u,playerL.get(u)-1);
                    }}
                
                return userWord.length()==disponible;   
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
                Platform.runLater(()->tiempo.setText(String.valueOf(tiempoTranscurrido)));            
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Utilities.class.getName()).log(Level.SEVERE, null, ex);
                    Thread.currentThread().interrupt();
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
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(CONSTANTES.GAMEDATA+".dat"))){
            scoregame.get(pd.getDf()).add(pd.getPlayerScore()); 
            oos.writeObject(scoregame);
        } catch (IOException ex) {
            Utilities.reportError(ex);  
        }
    }
    
      private void escribir1(){
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(CONSTANTES.GAMEDATA+".dat"))){
            TreeMap<Dificultad,TreeSet<Score>> scoresGvacio = new TreeMap();
            scoresGvacio.put(Dificultad.FACIL, new TreeSet<>());
            scoresGvacio.put(Dificultad.MEDIO, new TreeSet<>());
            scoresGvacio.put(Dificultad.DIFICIL, new TreeSet<>()); 
            scoresGvacio.get(pd.getDf()).add(pd.getPlayerScore()); 
            oos.writeObject(scoresGvacio);
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
     public SortedMap<Dificultad,TreeSet<Score>> leerArchivo() throws FileNotFoundException{
         TreeMap<Dificultad,TreeSet<Score>> scoresG; 
         try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(CONSTANTES.GAMEDATA+".dat"))){
                scoresG = (TreeMap<Dificultad,TreeSet<Score>>)ois.readObject();
            return scoresG;
             
            }catch (IOException ex) {
                
                escribir1();
                        
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
        finalizarJuego2();
        Platform.runLater(()->{
                Pane over = new Pane();
                ImageView ini; 
                if(Controlador.getSkin()==2) ini =  new ImageView(new Image(CONSTANTES.RUTA_IMGS+"BG_IJ01.png"));       
                else ini = new ImageView(new Image(CONSTANTES.RUTA_IMGS+"BG_IJ02.png"));

                Label ltitulo = new Label("Gracias por jugar!");
                ltitulo.setTextFill(Color.WHITE);
                ltitulo.setFont(CONSTANTES.FUENTE);
                
                Font thef= Font.font("Helvetica", FontWeight.BOLD,16);
                VBox vf = new VBox(); 
                vf.setLayoutX(90);
                vf.setLayoutY(60);
                vf.setPadding(new Insets(10, 10, 10, 10));  
                vf.setPrefSize(USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
                String valPtotal = String.valueOf(pTotal);
                Label puntuacion = new Label("Ha alcanzado la siguiente puntuacion: "+valPtotal);
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
              
                buttons.setPadding(new Insets(10, 30, 30, 30));  

                vf.getChildren().addAll(ltitulo,puntuacion,finished,userInfo,buttons);

                continuar.setOnMouseClicked(e->{
                    //sin continuar
                    VistaInicio inP= new VistaInicio();
                    onRoot.getChildren().clear();
                    Utilities.transition2(onRoot, inP.getRoot());
                });
                shareScore.setOnMouseClicked(e->{
                    try {
                    String p= String.valueOf(pTotal);
                    Desktop.getDesktop().browse(new URI("https://twitter.com/intent/tweet?hashtags=TyperBallon&text=He+alcanzado+"+p+"+puntos&via=BalloonsTyper"));
                    }catch (URISyntaxException | IOException ex) {
                    Utilities.reportError(ex);                    
                    }
                });
                Label a = new Label();
                a.setFont(thef);
                userInfo.getChildren().add(a);
                //Guardando info de usuario
                submit.setOnMouseClicked(e->{
                    String nombre = userData.getText();
                    if(nombre.length()<11){
                        submit.setDisable(true);
                        pd.getPlayerScore().setNombre(nombre);
                        pd.getPlayerScore().setPuntaje(pTotal);
                        try{
                            
                            SortedMap<Dificultad,TreeSet<Score>> scoresGame= leerArchivo();
                            escribir2((TreeMap)scoresGame);
                            a.setTextFill(Color.WHITE);
                            a.setText("Datos guardados correctamente!");
                        }catch (FileNotFoundException ex){
      
                            escribir1();
                            
                        }
                    }else{
                        a.setTextFill(Color.LIME);
                        a.setText("Nombre muy largo :(");
                    }
                });
               over.getChildren().addAll(ini,vf);
               over.setLayoutX(100);
               over.setLayoutY(-500);
               Utilities.bajarCartel(onRoot, over, 200);
              });
            
    }
    
    public static void finalizarJuego2(){
        terminarJuego=true;
    }
}
  
