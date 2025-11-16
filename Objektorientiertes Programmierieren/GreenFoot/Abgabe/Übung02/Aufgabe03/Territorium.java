import greenfoot.Actor;
import greenfoot.World;
import greenfoot.Greenfoot;
import java.util.List;

/**
 * Die Klasse stellt eine Repraesentation des Hamster-Territoriums dar. Die
 * Methoden dienen zum Abfragen bestimmter Zustandswerte des aktuellen
 * Territoriums.
 * 
 * @author Dietrich Boles (Universitaet Oldenburg)
 * @version 2.0 (07.06.2008)
 * 
 */
public class Territorium extends World {


    /**
     * Erzeugt ein neues Territorium fester Größe, verteilt Körner und 
     * Mauern und setzt den Hamster in das Territorium. 
     */
    public Territorium() {
        this(13, 8);
        prepare();
    }

    /**
     * Erzeugt ein neues Territorium in der angegebenen Groesse
     * 
     * @param spalten
     *            Anzahl an Spalten
     * @param zeilen
     *            Anzahl an Zeilen
     */
    public Territorium(int spalten, int zeilen) {
        super(spalten < 1 ? 10 : spalten, zeilen < 1 ? 10 : zeilen, 35);
        setBackground("kachel.jpg");
        setPaintOrder(Mauer.class, Hamster.class, Korn.class);
    }

    /**
     * Methode zur Erzeugung der Welt auf Basis der im Editor erzeugten
     * und platzierten Hamster, Wände und Körner.
     */
    private void prepare() {
        // Alternative 1:
        //   Programmatische, d.h. programmierte Erzeugung der Welt durch
        //   Erzeugung von Objekten (z.B. new Mauer()) und Hinzufügen zu
        //   Territorium (addObject()). Ein Hamster kann mittels der Methode 
        //   setzeHamster(int spalte, int zeile, int koerner, int richtung)
        //   in das Territorium gesetzt werden.

        // Alternative 2:
        //   Manuelle Erzeugung der Welt im grafischen Editor
        //   (Zur Änderung / Erweiterung:
        //    - Reset drücken
        //    - Objekte (Hamster, Mauern, Körner) zur Territorium hinzufügen
        //    - Kontextmenü (rechte Maustaste) auf Territorium 
        //        -> "Save the World" auswählen
        //    - Greenfoot erweitert _diese_ Methode um Code, 
        //      der die Hamster-Welt aufbaut
        Hamster hamster = new Hamster();
        addObject(hamster,0,3);
        hamster.setLocation(1,5);
        Korn korn = new Korn();
        addObject(korn,12,0);
        Korn korn2 = new Korn();
        addObject(korn2,11,0);
        Korn korn3 = new Korn();
        addObject(korn3,10,0);
        Korn korn4 = new Korn();
        addObject(korn4,9,0);
        Korn korn5 = new Korn();
        addObject(korn5,8,0);
        Korn korn6 = new Korn();
        addObject(korn6,8,0);
        Korn korn7 = new Korn();
        addObject(korn7,7,0);
        korn7.setLocation(7,0);
        Korn korn8 = new Korn();
        addObject(korn8,7,0);
        korn8.setLocation(7,0);
        korn8.setLocation(7,0);
        korn8.setLocation(7,0);
        korn8.setLocation(7,0);
        Korn korn9 = new Korn();
        addObject(korn9,7,0);
        korn9.setLocation(7,0);
        Korn korn10 = new Korn();
        addObject(korn10,7,0);
        korn4.setLocation(9,0);
        Korn korn11 = new Korn();
        addObject(korn11,9,0);
        korn11.setLocation(9,0);
        Korn korn12 = new Korn();
        addObject(korn12,9,0);
        Korn korn13 = new Korn();
        addObject(korn13,9,0);
        korn.setLocation(12,0);
        Korn korn14 = new Korn();
        addObject(korn14,12,0);
        korn14.setLocation(12,0);
        korn14.setLocation(12,0);
        korn14.setLocation(12,0);
        korn14.setLocation(12,0);
        Korn korn15 = new Korn();
        addObject(korn15,12,0);
        Korn korn16 = new Korn();
        addObject(korn16,12,0);
        Korn korn17 = new Korn();
        addObject(korn17,8,1);
        Korn korn18 = new Korn();
        addObject(korn18,12,2);
        Korn korn19 = new Korn();
        addObject(korn19,11,2);
        Korn korn20 = new Korn();
        addObject(korn20,10,2);
        Korn korn21 = new Korn();
        addObject(korn21,12,3);
        korn21.setLocation(12,3);
        korn21.setLocation(12,3);
        korn21.setLocation(12,3);
        korn21.setLocation(12,3);
        korn21.setLocation(12,3);
        Korn korn22 = new Korn();
        addObject(korn22,12,3);
        korn22.setLocation(12,3);
        korn22.setLocation(12,3);
        korn22.setLocation(12,3);
        Korn korn23 = new Korn();
        addObject(korn23,12,3);
        korn23.setLocation(12,3);
        korn23.setLocation(12,3);
        korn23.setLocation(12,3);
        korn23.setLocation(12,3);
        korn23.setLocation(12,3);
        korn23.setLocation(12,3);
        korn23.setLocation(12,3);
        korn23.setLocation(12,3);
        Korn korn24 = new Korn();
        addObject(korn24,12,3);
        korn24.setLocation(12,3);
        Korn korn25 = new Korn();
        addObject(korn25,12,3);
        korn25.setLocation(12,3);
        Korn korn26 = new Korn();
        addObject(korn26,12,2);
        removeObject(korn26);
        addObject(korn26,12,2);
        Korn korn27 = new Korn();
        addObject(korn27,11,3);
        korn27.setLocation(11,3);
        Korn korn28 = new Korn();
        addObject(korn28,11,3);
        Korn korn29 = new Korn();
        addObject(korn29,5,4);
        korn29.setLocation(5,4);
        Korn korn30 = new Korn();
        addObject(korn30,5,4);
        Korn korn31 = new Korn();
        addObject(korn31,5,3);
        korn30.setLocation(5,4);
        Korn korn32 = new Korn();
        addObject(korn32,5,4);
        korn32.setLocation(5,4);
        Korn korn33 = new Korn();
        addObject(korn33,5,4);
        removeObject(korn31);
        addObject(korn31,7,4);
        Korn korn34 = new Korn();
        addObject(korn34,8,4);
        korn34.setLocation(8,4);
        Korn korn35 = new Korn();
        addObject(korn35,8,4);
        korn31.setLocation(7,4);
        Korn korn36 = new Korn();
        addObject(korn36,7,4);
        Korn korn37 = new Korn();
        addObject(korn37,10,4);
        korn37.setLocation(10,4);
        korn37.setLocation(10,4);
        Korn korn38 = new Korn();
        addObject(korn38,10,4);
        removeObject(korn2);
    }
    
    
    // Ab hier Implementierung von Methoden, die Informationen über das
    // Territorium geben (z.B. Größe des Territoriums, Anzahl Körner auf 
    // einem Feld, Mauer auf einem Feld).
    
    
    /**
     * liefert die Anzahl an Zeilen im Territorium
     * 
     * @return die Anzahl an Zeilen im Territorium
     */
    public int getAnzahlZeilen() {
        return getHeight();
    }

    /**
     * liefert die Anzahl an Spalten im Territorium
     * 
     * @return die Anzahl an Spalten im Territorium
     */
    public int getAnzahlSpalten() {
        return getWidth();
    }

    /**
     * ueberprueft, ob sich auf der Kachel (zeile/spalte) eine Mauer befindet;
     * es wird genau dann true geliefert, wenn sich auf der angegebenen Kachel
     * eine Mauer befindet oder wenn sich die angegebenen Werte ausserhalb des
     * Territoriums befinden
     * 
     * @param zeile
     *            Zeile der Kachel
     * @param spalte
     *            Spalte der Kachel
     * @return true geliefert, wenn sich auf der angegebenen Kachel eine Mauer
     *         befindet oder wenn sich die angegebenen Werte ausserhalb des
     *         Territoriums befinden; sonst false
     */
    public boolean mauerDa(int zeile, int spalte) {
        return getObjectsAt(spalte, zeile, Mauer.class).size() > 0;
    }

    /**
     * liefert die Gesamtzahl an Koernern, die im Territorium auf Kacheln
     * herumliegen
     * 
     * @return die Gesamtzahl an Koernern, die im Territorium auf Kacheln
     *         herumliegen
     */
    public int getAnzahlKoerner() {
        int anzahl = 0;
        for (int z = 0; z < getAnzahlZeilen(); z++) {
            for (int s = 0; s < getAnzahlSpalten(); s++) {
                anzahl += getAnzahlKoerner(z, s);
            }
        }
        return anzahl;
    }

    /**
     * liefert die Anzahl an Koernern auf der Kachel (reihe/spalte) oder 0,
     * falls die Kachel nicht existiert oder durch eine Mauer blockiert ist
     * 
     * @param zeile
     *            Zeile der Kachel
     * @param spalte
     *            Spalte der Kachel
     * @return die Anzahl an Koernern auf der Kachel (reihe/spalte) oder 0,
     *         falls die Kachel nicht existiert oder durch eine Mauer blockiert
     *         ist
     */
    public int getAnzahlKoerner(int zeile, int spalte) {
        List actors = getObjectsAt(spalte, zeile, Korn.class);
        if (actors == null || actors.size() == 0) {
            return 0;
        }
        return ((Korn)actors.get(0)).getAnzahl();
    }

    /**
     * liefert die Gesamtzahl an existierenden Hamstern im Territorium
     * 
     * @return die Gesamtzahl an existierenden Hamstern im Territorium
     */
    public int getAnzahlHamster() {
        return getObjects(Hamster.class).size();
    }

    /**
     * liefert alle existierenden Hamster im Territorium
     * 
     * @return alle existierenden Hamster im Territorium
     */
    public Hamster[] getHamster() {
        return (Hamster[]) getObjects(Hamster.class).toArray(new Hamster[0]);
    }

    /**
     * liefert die Anzahl an Hamstern auf der Kachel (zeile/spalte) oder 0,
     * falls die Kachel nicht existiert oder durch eine Mauer blockiert ist
     * 
     * @param zeile
     *            Zeile der Kachel
     * @param spalte
     *            Spalte der Kachel
     * @return die Anzahl an Hamstern auf der Kachel (zeile/spalte) oder 0,
     *         falls die Kachel nicht existiert oder durch eine Mauer blockiert
     *         ist
     */
    public int getAnzahlHamster(int zeile, int spalte) {
        return getObjectsAt(spalte, zeile, Hamster.class).size();
    }

    /**
     * liefert alle Hamster, die aktuell auf der Kachel (zeile/spalte) stehen
     * (inkl. dem Standard-Hamster)
     * 
     * @param zeile
     *            Zeile der Kachel
     * @param spalte
     *            Spalte der Kachel
     * @return alle Hamster, die aktuell auf der Kachel (zeile/spalte) stehen
     */
    public Hamster[] getHamster(int zeile, int spalte) {
        return (Hamster[]) getObjectsAt(spalte, zeile, Hamster.class).toArray(
                new Hamster[0]);
    }

    
    // Ab hier Implementierung von Hilfsmethoden zur programmatischen
    // Erzeugung einer Hamster-Welt (Gegenstück: manuelle Erzeugung im 
    // grafischen Editor; siehe dazu auch Hinweise in Methode prepare()).
    
    
    /*
     * Codierung der Hamster-Welt:
     * -1: Mauer
     *  0: normales Feld, keine Körner
     *  1: normales Feld, ein Korn
     *  2: normales Feld, zwei Körner (usw.)
     */
    private int[][] welt = {
        { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
        { -1,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, -1 },
        { -1,  0,  0,  0,  0,  0,  1,  0, -1,  0,  0,  0, -1 },
        { -1, -1, -1, -1,  0, -1,  0,  0, -1,  0,  0,  1, -1 },
        { -1,  0,  0,  0,  1,  0,  1,  0,  0,  0,  0,  0, -1 },
        { -1,  0,  0, -1, -1, -1,  0, -1, -1, -1, -1, -1, -1 },
        { -1,  1,  1, -1,  0,  0,  0,  0,  0,  0,  0,  0, -1 },
        { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 }
    };
    
    /**
     * Erzeugung der Welt:
     * - Mauern
     * - Körner
     */
    public void erzeugeHamsterWelt() {
        for (int zeile=0; zeile<getAnzahlZeilen(); zeile++) {
            for (int spalte=0; spalte<getAnzahlSpalten(); spalte++) {
                int feldTyp = welt[zeile][spalte];
                switch (feldTyp) {
                    case -1: Mauer m = new Mauer();
                        addObject(m, spalte, zeile);
                        break;
                    case 0: // nichts zu tun, normales Feld
                        break;
                    default: // alle anderen Fälle
                        int anzahlKoerner = feldTyp;
                        for (int i = 0; i < anzahlKoerner; i++) {
                            Korn korn = new Korn();
                            addObject(korn, spalte, zeile);
                        }
                }
            }
        }
    }
    
    /**
     * Hamster mit gewünschter
     * - Anzahl Körner im Maul
     * - an gewünschte Position und 
     * - mit gewünschter Blickrichtung 
     * setzen
     */
    public void setzeHamster(int spalte, int zeile, int koerner, int richtung) {
        Hamster goldi = new Hamster();
        goldi.setAnzahlKoerner(koerner);
        goldi.setBlickrichtung(richtung);
        addObject(goldi, spalte, zeile);
    }

    /**
     * Platziert zufaellig eine angegebene Anzahl von Koernern im Territorium
     * 
     * @param wieviele
     *            Anzahl der zu platzierenden Koerner
     */
    public void koernerGenerieren(int wieviele) {
        for (int i = 0; i < wieviele; i++) {
            Korn korn = new Korn();
            int x = Greenfoot.getRandomNumber(getWidth());
            int y = Greenfoot.getRandomNumber(getHeight());
            addObject(korn, x, y);
        }
    }
}