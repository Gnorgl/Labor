import greenfoot.Actor;
import greenfoot.World;
import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;

import java.util.List;
import java.awt.Color;

/**
 * Repraesentation von objektorientierten Hamstern im Java-Hamster-Modell
 * 
 * @author Dietrich Boles (Universitaet Oldenburg)
 * @version 2.0 (07.06.2008)
 * 
 */
public class Hamster extends BasicHamster {
    /**
     * Tue was: das Hamster-Programm
     */
    int koerner = 1;
    int messung = 0;
    int länge = 1;
    public void act() {
        // friss erstes Korn
        setAnzahlKoerner(koerner);
        
        while(messung < 2) {
            if(messung == 0) {
                gehInDieEcke();
            }
            koernerGeben();
            einFeldNachInnenVonRechts();
            welcheRichtung("links", 2);
            while(vornFrei() && messung < 1) {
                vor();
            }
            if(messung < 1) {
                linksUm();
            }
            messung++;
        } 
        
        if(länge % 2 == 1) {
            steps((länge-1)/2);
            linksUm();
        } else if(länge % 2 == 0) {
            steps((länge/2)-1);
            linksUm();
        }
        
        
        // Stop-Befehl, falls das Programm mittels "Run" anstelle von "Act"
        // ausgeführt werden soll:
        // Greenfoot.stop();
    }
    
    public void gehInDieEcke() {
        int i = -1;
        while(true) {
            while(vornFrei()) {
                vor();
                if(i == 1) {
                    länge++;
                }
            }
            while(!vornFrei()) {
                linksUm();
                i++;
            }
            if(i == 4) {
                break;
            }
        }
    }
    
    //Geh ein Feld nach Innen und leg ein Korn ab:
    public void einFeldNachInnenVonRechts() {
        while(true) {
            if(!vornFrei()) {
                return;
            }
            vor();
            linksUm();
            vor();
            rechtsUm();
            koernerGeben();
        }
    }
    //Gib Körner im Maul
    public void koernerGeben(){
        if(kornDa()) {
            return;
        }
        gib();
        koerner++;
        setAnzahlKoerner(koerner);
    }
}
    