/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;
import java.util.HashMap;

/**
 *
 * @author joangie
 */
public class Juego {
    private HashMap<String, Integer> player_words;
    private HashMap<String,Integer> player_l;
    private Score player_score;
    
    
    public Juego(){
        player_l= new HashMap();
         player_l.put("a", 3);
         player_l.put("b", 4);
         player_l.put("e", 3);
         player_l.put("j", 3);
         player_l.put("z", 3);
         player_l.put("o", 3);
         player_l.put("r", 3);
         player_l.put("c", 3);
         player_l.put("m", 3);
        
        
        player_score= new Score(); 
    }

    public HashMap<String, Integer> getPlayer_words() {
        return player_words;
    }

    public HashMap getPlayer_l() {
        return  player_l;
    }

    public Score getPlayer_score() {
        return player_score;
    }
}
