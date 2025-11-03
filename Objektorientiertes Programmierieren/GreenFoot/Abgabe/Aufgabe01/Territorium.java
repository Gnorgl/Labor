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
        Mauer mauer = new Mauer();
        addObject(mauer,2,3);
        mauer.setLocation(2,3);
        mauer.setLocation(2,4);
        Mauer mauer2 = new Mauer();
        addObject(mauer2,2,3);
        Mauer mauer3 = new Mauer();
        addObject(mauer3,2,5);
        Mauer mauer4 = new Mauer();
        addObject(mauer4,3,5);
        Mauer mauer5 = new Mauer();
        addObject(mauer5,5,5);
        Mauer mauer6 = new Mauer();
        addObject(mauer6,6,5);
        Mauer mauer7 = new Mauer();
        addObject(mauer7,6,4);
        Mauer mauer8 = new Mauer();
        addObject(mauer8,6,3);
        Mauer mauer9 = new Mauer();
        addObject(mauer9,6,2);
        Mauer mauer10 = new Mauer();
        addObject(mauer10,6,0);
        mauer10.setLocation(6,1);
        Hamster hamster = new Hamster();
        addObject(hamster,3,4);
        Korn korn = new Korn();
        addObject(korn,7,4);
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