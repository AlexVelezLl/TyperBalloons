/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import javafx.application.Platform;

/**
 *
 * @author PC
 */
public class MoverGlobo implements Runnable {
    Globo globo;
    public MoverGlobo(Globo globo){
        this.globo=globo;
    }

    @Override
    public void run() {
        for (int i=0;i<3000;i++){
        Platform.runLater(()->{
            globo.setLayoutY(globo.getPosicionY()-1);
            });
                try {
                    Thread.sleep(5);
                } catch (InterruptedException ex) {
                    System.out.println("Ocurrio un error");
                }
        }
    }
}
