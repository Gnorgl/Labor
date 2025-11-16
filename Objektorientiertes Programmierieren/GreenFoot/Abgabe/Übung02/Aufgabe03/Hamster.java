import greenfoot.Actor;
import greenfoot.World;
import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;

import java.util.List;
import java.util.ArrayList;
import java.awt.Color;

/**Notizen:
 * 
 * Eine Spalte = 1 Kammer
 * Die einzelnen Körner auf den Felder sind jeweils eine Ziffer der ganzen Zahl,
 * von links nach rechts gelesen.
 * 
 * Was wollen wir: Der Hamster soll all seine Körner abzählen und das Ergebnis in der 
 * untersten Zeile eintragen (In Korn-Form, pro Zahl ein Feld).
*/


public class Hamster extends BasicHamster {
    
    private int addieren = 0;
    private String sum = "";
    
    private int felder = 1;
    private List<String> kammern = new ArrayList<String>();
    
    private int kammerNummer = 0;
    
    public void act() {
        System.out.println("Der Hamster bereitet sich auf den Winter vor!");
        System.out.println("");
        
        geheLinkeObereEcke();
        
        while(felder > 1) {
            koernerZaehlen();
            zeileNachUnten();
            geheLinkeWand();
        }
        
        alleKoerner();
        
        if(addieren > 100000) {
            System.out.println("Der Hamster wird den Winter überleben!");
        } else {
            System.out.println("Der Hamster wird den Winter nicht überleben :(");
        }
    }
    
    
    
    /**Bewegungsmethoden:
    */
   
    public void stepsBisWand() {
        while(vornFrei()) {
            vor();
        }
    }
    
    public void stepsBisKorn() {
        while(!kornDa() && vornFrei()) {
            vor();
        }
    }
    
    public void geheLinkeWand() {
        setBlickrichtung(WEST);
        stepsBisWand();
        setBlickrichtung(OST);
    }
    
    public void geheRechteWand() {
        stepsBisWand();
        setBlickrichtung(WEST);
    }
    
    public void zeileNachUnten() {
        setBlickrichtung(SUED);
        vor();
        this.felder--;
    }
    
    public void geheLinkeObereEcke() {
        setBlickrichtung(SUED);
        while(vornFrei()) {
            vor();
        }
        setBlickrichtung(NORD);
        while(vornFrei()) {
            vor();
            this.felder++;
        }
        System.out.println("Länge des Feldes");
        System.out.println(this.felder);
        System.out.println("");
        geheLinkeWand();
    }
    
    /**Zählmethoden:
    */
   
    public void koernerZaehlen() {
        String kammer = "";
        stepsBisKorn();
        
        while(true) {
            
            int count = koernerAufsammeln();
            kammer += String.valueOf(count);
            
            if(!vornFrei()) {
                kammerNummer++;
                System.out.println("Kammer " + kammerNummer);
                System.out.println(kammer);
                System.out.println("");
                addieren += Integer.valueOf(kammer);
                this.kammern.add(kammer);
                break;
            }
            
            vor();
                   
        }
    }
    
    public int koernerAufsammeln() {
        int koerner = 0;
        
        if(!kornDa()) {
            return koerner;
        }
        
        while(kornDa()) {
                nimm();
                koerner++;
            }

        while(!maulLeer()) {
                gib();
            }
        
        return koerner;
    }
    
    
    /**Ablegemethoden:
     */
    
    public void alleKoerner() {
        koernerUeberblick();
        geheRechteWand();
        
        int length = sum.length();
        int index = length - 1;
        
        System.out.println(index);
        
        while(index >= 0) {
            
            char letzteZahl = sum.charAt(index);
            int dieseKoerner = Character.getNumericValue(letzteZahl);
            
            koernerAblegen(dieseKoerner);
            vor();
            
            index--;
        }
        geheLinkeWand();
        System.out.println("");
        
    }
    
    public void koernerUeberblick() {
        this.sum = String.valueOf(addieren);
        System.out.println("Alle Kammern:");
        System.out.println(kammern);
        System.out.println("");
        System.out.println("Summe der Kammern:");
        System.out.println(sum);
        System.out.println("");
        System.out.println("Werte der einzelnen Felder der Summe von rechts nach links:");
    }
    
    public void koernerAblegen(int dieseKoerner) {
        System.out.println(dieseKoerner);
        setAnzahlKoerner(dieseKoerner);
        while(getAnzahlKoerner() > 0) {
            gib();
        }
        
        
    }
    
}




