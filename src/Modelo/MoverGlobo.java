/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Vistas.VistaJuego1;
import javafx.application.Platform;

/**
 *
 * @author Valeria Barzola
 */
public class MoverGlobo implements Runnable {
    Globo globo;
    private int tiempo;
    private static boolean pausa;

    public MoverGlobo() {

    }

    public MoverGlobo(final Globo globo, final int tiempo) {
        this.globo = globo;
        this.tiempo = tiempo;
    }

    @Override
    public void run() {
        int i = 0;
        while (i < 875) {
            if (!pausa) {
                Platform.runLater(() -> globo.setLayoutY(globo.getPosicionY() - 1));
                if (!VistaJuego1.getActivo()) {
                    Thread.currentThread().interrupt();
                }
                try {
                    Thread.sleep(tiempo);
                } catch (final InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
                i++;
            } else {
                try {
                    Thread.sleep(100);
                } catch (final InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
            }
        }
        globo.onScreen = false;

    }

    public static boolean getPausa() {
        return pausa;
    }

    public static void pausar() {
        pausa = true;
    }

    public static void reanudar() {
        pausa = false;
    }
}
