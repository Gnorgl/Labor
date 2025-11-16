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
    public void act() {
        // friss erstes Korn
        vor();
        welcheRichtung("links",3);
        vor();
        vor(); 
        linksUm();
        vor();
        vor();
        vor();
        linksUm();
        vor();
        vor();
        nimm();
        rechtsUm();
        vor();
        gib();
        // Stop-Befehl, falls das Programm mittels "Run" anstelle von "Act"
        // ausgeführt werden soll:
        // Greenfoot.stop();
    }
}