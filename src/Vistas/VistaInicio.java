/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;


import Modelo.Dificultad;
import Modelo.Score;
import controlador.Controlador;
import java.awt.Desktop;
import java.io.FileInputStream;
import utilities.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
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
import javafx.scene.text.Font;

import static javafx.scene.text.Font.font;
import javafx.scene.text.FontWeight;

/**
 * Clase donde se implementara todos los objetos graficos de la primera vista
 * del juego.
 * @author Alex Velez
 */
public class VistaInicio{
    private Pane root;
    private Pane onRoot;
    private final Image bG01;
    private static final String NAMEBUTTON = "Button";
    /**
     * Constructor de la clase donde se ubicara todos los elementos graficos de 
     * la Vista Inicial.
     */
    public VistaInicio(){
        
        root = new Pane();
        onRoot = new Pane();
        
        Controlador.setbGIndex(new ImageView());
        bG01 =new Image(CONSTANTES.RUTA_IMGS+"BG_01.png");
        
            
            Controlador.getbGIndex().setImage(bG01);
            Controlador.setSkin(1);
            Controlador.setSondEsp(true);

            root.getChildren().addAll(Controlador.getbGIndex(),includeTitle(),includeButtons(),includeSN(),includeSettings(),onRoot);
        
        
    }
    
    /**
     * Metodo get que retorna el contenedor principal de la VistaInicio
     * @return Pane que contiene el root actual de la vista
     */
    public Pane getRoot(){
        return root;
    }
    /**
     * Metodo donde se construyen los botones principales de la aplicacion:
     * -INICIAR JUEGO
     * -VER PUNTAJES
     * -Salir
     * Cada uno con su respectiva funcionalidad
     * @return VBox donde estan colocados los botones principales de la aplicacion.
     */
    private VBox includeButtons(){
        VBox buttons = new VBox();
        buttons.setLayoutX(250);
        buttons.setLayoutY(250);
        Label lInicio1 = new Label("INICIAR JUEGO");
        lInicio1.setFont(CONSTANTES.FUENTE2);
        lInicio1.setTextFill(Color.WHITE);
        Pane inicio = Utilities.boton("INICIAR JUEGO",NAMEBUTTON);
        
        inicio.setOnMouseClicked(e->{
            try{
                Pane inicioJuego = createOptions();
                Utilities.bajarCartel(onRoot,inicioJuego,200);
            }catch(Exception ex){
                Utilities.reportError(ex);
            }
            
            
        });

        Pane punt = Utilities.boton("VER PUNTAJES",NAMEBUTTON);
        punt.setOnMouseClicked(e->{
            try{
                Pane puntuaciones = createPunt();
                Utilities.bajarCartel(onRoot, puntuaciones, 200); 
            }catch(Exception ex){
                Utilities.reportError(ex);
            }
            
        });
        
        Pane salir = Utilities.boton("SALIR", NAMEBUTTON);
        salir.setOnMouseClicked(e->System.exit(0));
                  
        buttons.setSpacing(20);
        buttons.getChildren().addAll(inicio,punt,salir);
        return buttons;
    }
    /**
     * Metodo que retorna el pane que contiene el titulo de TyperBalloons que sera ubicado
     * en la interfaz principal
     * @return Pane que contiene el titulo principal de TyperBalloons
     */
    private Pane includeTitle(){
        Pane pTitle = new Pane();
        Controlador.setTitle(new ImageView(new Image(CONSTANTES.RUTA_IMGS+"TyperBallons_Title04.png")));
        Controlador.getTitle().setOnMouseClicked(e->{
            Desktop enlace=Desktop.getDesktop();
            String link = "https://typerballoons.000webhostapp.com";
            
            try {
                enlace.browse(new URI(link));
            } catch (IOException|URISyntaxException ex) {
                Label errorLink = new Label("Ha ocurrido un error con la pagina web!");
                errorLink.setFont(CONSTANTES.FUENTE2);
                errorLink.setTextFill(Color.WHITE);
                root.getChildren().add(errorLink);
            }
        });
        
        
        Controlador.getTitle().setLayoutX(70);
        Controlador.getTitle().setLayoutY(45);
        pTitle.getChildren().add(Controlador.getTitle());
        return pTitle;
    }
    
    /**
     * Metodo donde se construye y retorna el contenedor donde estaran ubicados 
     * los respectivos botones de nuestras redes Sociales.
     * @return Vbox con los botones de redes sociales
     */
    private VBox includeSN(){
        VBox sn = new VBox();
        sn.setLayoutX(650);
        sn.setLayoutY(370);
        sn.setSpacing(10);
        Pane pFB = Utilities.boton("FB_img");
        
        pFB.setOnMouseClicked(e->{
            Desktop enlace=Desktop.getDesktop();
            String link = "https://www.facebook.com/pages/category/Video-Game/TyperBallons-POO-110771206958905/";
            try {
                enlace.browse(new URI(link));
            } catch (IOException|URISyntaxException ex) {
                Label errorLink = new Label("Ha ocurrido un error con nuestra pagina de Facebook :(");
                errorLink.setFont(CONSTANTES.FUENTE2);
                errorLink.setTextFill(Color.WHITE);
                root.getChildren().add(errorLink);
            }
        });
        
        Pane pTwitter = Utilities.boton("Twitter_img");
        pTwitter.setOnMouseClicked(e->{
            Desktop enlace=Desktop.getDesktop();
            String link = "https://twitter.com/balloonsTyper";
            try {
                enlace.browse(new URI(link));
            } catch (IOException|URISyntaxException ex) {
                Label errorLink = new Label("Ha ocurrido un error con nuestra pagina de Twitter :(");
                errorLink.setFont(CONSTANTES.FUENTE2);
                errorLink.setTextFill(Color.WHITE);
                root.getChildren().add(errorLink);
            }
        });
        sn.getChildren().addAll(pFB,pTwitter);
        return sn;
    }
    
    /**
     * Metodo que crea y estructura nuestra vista de configuraciones la cual tendra:
     * -Controlador de volumen de la aplicacion
     * -Selector de si el usuario desea o no efectos especiales
     * -Selector de skin para la aplicacion
     * @return Pane con el root de la ventana de las configuarciones
     */
    private Pane includeSettings(){
        Pane eng = Utilities.boton("Engranaje_img");
        eng.setLayoutX(80);
        eng.setLayoutY(400);
        Rectangle rect = new Rectangle(195,150);
            rect.setFill(Color.color(0.039, 0.898, 0));
            rect.setLayoutX(174);
            rect.setLayoutY(390);
        eng.setOnMouseClicked(e->{
            try{
                Pane setingsPane = new Pane();

                Pane pRetro = Utilities.boton("Retro_Button");
                Button b = new Button();
                b.setLayoutX(-20);

                pRetro.setOnMouseClicked(a->Utilities.transition2(setingsPane, root));

                pRetro.setOnKeyPressed(a->{
                    if(a.getCode()==KeyCode.ESCAPE){
                        Utilities.transition2(setingsPane, root);
                    }
                });
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
                if(Controlador.isSondEsp()){
                    pCheckBox.getChildren().addAll(checkBox,check);
                }else{
                    pCheckBox.getChildren().addAll(checkBox);
                }

                pCheckBox.setOnMouseClicked(a->{
                    if(Controlador.isSondEsp()){
                        Controlador.setSondEsp(false);
                        pCheckBox.getChildren().remove(check);
                    }else{
                        Controlador.setSondEsp(true);
                        pCheckBox.getChildren().add(check);
                    }
                });
                hbCheckBox.getChildren().addAll(lSonidos,pCheckBox);
                VBox vBSkin = new VBox();

                Label lSkin = new Label("Skin");

                lSkin.setFont(CONSTANTES.FUENTE);
                lSkin.setTextFill(Color.WHITE);
                Pane pSkin = new Pane();

                ImageView skin1 = new ImageView(bG01);
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

                pSkin1.setOnMouseClicked(e1->{
                    rect.setLayoutX(174);
                    Controlador.getbGIndex().setImage(bG01);
                    Controlador.getTitle().setImage(new Image(CONSTANTES.RUTA_IMGS+"TyperBallons_Title04.png"));
                    Controlador.setSkin(1);

                });
                pSkin2.setOnMouseClicked(e1->{
                    rect.setLayoutX(434);
                    Controlador.getbGIndex().setImage(new Image(CONSTANTES.RUTA_IMGS+"BG_03.png"));
                    Controlador.getTitle().setImage(new Image(CONSTANTES.RUTA_IMGS+"TyperBallons_Title01.png"));
                    Controlador.setSkin(2);
                });

                pSkin.getChildren().addAll(pSkin1,pSkin2);

                vBSkin.getChildren().addAll(lSkin,pSkin);
                vBSkin.setAlignment(Pos.CENTER);
                vBSkin.setSpacing(10);
                config.getChildren().addAll(vbVolumen,hbCheckBox,vBSkin);
                setingsPane.getChildren().addAll(b,bGSettings,pRetro,rect,config);
                Utilities.transition2(root,setingsPane);
            }catch(Exception ex){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("WOOPS!");
                alert.setContentText("Dificultades tecnicas, estamos trabajando en ello.\n"
                    + "Por favor reporta este error en: https://typerballoons.000webhostapp.com\n\n"
                    + "Error: "+ex.toString());
                alert.showAndWait();
            }
        });
        
        return eng;
    }
    
    /**
     * Funcion que crea y estructura el pane donde se mostraran las diferentes dificultades 
     * de nuestro juego, cada una con su respectiva funcionalidad de pasar al juego con 
     * la dificultad seleccionada, las dificultades son:
     * -Facil
     * -Medio
     * -Dificil
     * @return Pane con las opciones de juego que tiene el usuario al clickear en iniciar juego
     */
    private Pane createOptions(){

        Pane inicioJuego = new  Pane();
        ImageView ini=null;
        if(Controlador.getSkin()==1){
                ini = new ImageView(new Image(CONSTANTES.RUTA_IMGS+"BG_IJ02.png"));
        }else{
                ini = new ImageView(new Image(CONSTANTES.RUTA_IMGS+"BG_IJ01.png"));            
        }
        
        Pane pSalir = Utilities.boton("X_Button");
        
        pSalir.setLayoutY(-20);
        pSalir.setLayoutX(-20);
        pSalir.setOnMouseClicked(e->onRoot.getChildren().clear());
        

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

        Pane pFacil = Utilities.boton("FACIL","GreenButton");
        pFacil.setOnMouseClicked(e->{
            try{
                VistaJuego1 vj = new VistaJuego1(Dificultad.FACIL);
                Utilities.transition(root,vj.getRoot());
            }catch(Exception ex){
                Utilities.reportError(ex);
            }
            
        });

        Pane pMedio = Utilities.boton("MEDIO","YellowButton");
        pMedio.setOnMouseClicked(e->{
            try{
                VistaJuego1 vj = new VistaJuego1(Dificultad.MEDIO);
                Utilities.transition(root,vj.getRoot());
            }catch(Exception ex){
                Utilities.reportError(ex);
            }
        });
        Pane pDificil = Utilities.boton("DIFICIL", "RedButton");
        pDificil.setOnMouseClicked(e->{
            try{
                VistaJuego1 vj = new VistaJuego1(Dificultad.DIFICIL);
                Utilities.transition(root,vj.getRoot());
            }catch(Exception ex){
                Utilities.reportError(ex);
            }
        });
        dificultad.getChildren().addAll(lDificultad,pFacil,pMedio,pDificil);
        dificultad.setAlignment(Pos.CENTER);
        dificultad.setSpacing(10);
        inicioJuego.getChildren().add(dificultad);  
        return inicioJuego;
    }
    
    /**
     * Metodo que crea y estructura el contenedor donde se mostraran las 10 mejores
     * puntuaciones de cada dificultad.
     * @return Pane con las mejores puntuaciones guardadas en la aplicacion
     */
    public Pane createPunt(){
        Pane pPunt = new  Pane();
        ImageView ini=null;
        if(Controlador.getSkin()==1){
                ini = new ImageView(new Image(CONSTANTES.RUTA_IMGS+"BG_IJ02.png"));
        }else{
                ini = new ImageView(new Image(CONSTANTES.RUTA_IMGS+"BG_IJ01.png"));      
        }
        
        Pane pSalir = Utilities.boton("X_Button");
        pSalir.setLayoutY(-20);
        pSalir.setLayoutX(-20);
        pSalir.setOnMouseClicked(e->onRoot.getChildren().clear());
        Button b = new Button();
        b.setLayoutY(100);
        Label lPuntajes = new Label("Puntajes");
        lPuntajes.setFont(CONSTANTES.FUENTE);
        lPuntajes.setTextFill(Color.WHITE);

        VBox vBPuntajes = new VBox();
        vBPuntajes.setLayoutX(25);
        vBPuntajes.setLayoutY(20);
        vBPuntajes.setMinWidth(550);
        HBox hBDif = new HBox();
        hBDif.setSpacing(18);
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("src/data/scores.dat"))){
            @SuppressWarnings("unchecked")
            TreeMap<Dificultad,TreeSet<Score>> scores = (TreeMap<Dificultad,TreeSet<Score>>) ois.readObject();
            for(Entry<Dificultad,TreeSet<Score>> a : scores.entrySet()){
                Pane pDifi = new Pane();                
                VBox difi = new VBox();
                
                Label ldifi = new Label(a.getKey().toString());
                ldifi.setFont(font("Arial",FontWeight.BOLD,24));
                ldifi.setTextFill(Color.WHITE);
                difi.getChildren().add(ldifi);
                Set<Score> scoresDif = a.getValue();
                Iterator <Score> iterator = scoresDif.iterator();
                StringBuilder scoresString = new StringBuilder();
                
                int i =1;
                while(iterator.hasNext()&&i<=10){
                    Score s = iterator.next();
                    scoresString.append( i+". "+ s.getNombre()+": "+s.getPuntaje() +"\n");
                    i++;
                    
                }
                String scoresJug =scoresString.toString();
                Label scoreJug = new Label(scoresJug);
                scoreJug.setFont(new Font("Arial",16));
                scoreJug.setTextFill(Color.WHITE);
                difi.getChildren().add(scoreJug);
                
                difi.setSpacing(8);
                difi.setLayoutX(15);
                difi.setLayoutY(10);
                if(Controlador.getSkin()==1){
                        pDifi.getChildren().addAll(new ImageView(new Image(CONSTANTES.RUTA_IMGS+"Punt_Box.png")),difi);
                }else{
                        pDifi.getChildren().addAll(new ImageView(new Image(CONSTANTES.RUTA_IMGS+"Punt_Box2.png")),difi);
                }
        
                
                hBDif.getChildren().add(pDifi);
            }
        }catch(Exception ex){
            
            Label a = new Label("No existen puntajes aun.");
            a.setFont(CONSTANTES.FUENTE2);
            a.setTextFill(Color.WHITE);
            hBDif.getChildren().add(a);
            
        }
        vBPuntajes.getChildren().addAll(lPuntajes,hBDif);
        vBPuntajes.setAlignment(Pos.CENTER);
        vBPuntajes.setSpacing(10);
        pPunt.getChildren().addAll(b,ini,vBPuntajes,pSalir);
        pPunt.setLayoutX(100);
        pPunt.setLayoutY(-500);
        
        return pPunt;
    }
    
}