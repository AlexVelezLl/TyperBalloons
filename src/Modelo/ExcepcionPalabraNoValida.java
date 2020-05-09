/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author joangie
 */
public class ExcepcionPalabraNoValida extends RuntimeException{
    
    /**
     *
     */
    private static final long serialVersionUID = -7440434776836247585L;

    public ExcepcionPalabraNoValida() {
    }
    
    public ExcepcionPalabraNoValida(String msg) {
        super(msg);
    }
    
}
