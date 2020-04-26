/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author joangie
 */
public class ExcepcionPalabraNoValida extends RuntimeException{
    
    public ExcepcionPalabraNoValida(){
    }
    
    public ExcepcionPalabraNoValida(String msg) {
        super(msg);
    }
    
}
