/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;


import vistas.VistaJuego1;
import javafx.application.Platform;

/**
 *
 * @author Valeria Barzola
 */
public class MoverGlobo implements Runnable {
    Globo globo;
    private int tiempo;
    public MoverGlobo(){
        
    }

    public MoverGlobo(Globo globo, int tiempo){
        this.globo=globo;
        this.tiempo=tiempo;       
    }

    @Override
    public void run() {
        for (int i=0;i<875;i++){
        Platform.runLater(()->globo.setLayoutY(globo.getPosicionY()-1));
        if(!VistaJuego1.getActivo()){
            Thread.currentThread().interrupt();
        }
                try {
                    Thread.sleep(tiempo);
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
        }
            globo.onScreen=false;
            
        
        
        
        
    }
}
