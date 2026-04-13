import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class BasicHamster here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class BasicHamster extends Actor {

    /**
     * Blickrichtungen
     */
    public final static int NORD = 0;
    public final static int OST = 1;
    public final static int SUED = 2;
    public final static int WEST = 3;

    private int blickrichtung;
    private int koernerImMaul;

    /**
     * Konstruktor zum Erzeugen eines neuen Hamsters mit Blickrichtung OST und
     * keinem Korn im Maul
     */
    public BasicHamster() {
        setImage("hamster.png");
        setBlickrichtung(OST);
        koernerImMaul = 0;
    }
    
    
    /**
     * Konstruktor zum Erzeugen eines neuen Hamsters mit der angegebenen
     * Blickrichtung und Anzahl an Koernern im Maul
     *
     * @param blickrichtung
     *            Blickrichtung des Hamsters
     * @param anzahlKoerner
     *            Anzahl an Koernern im Maul
     */
    public BasicHamster(int blickrichtung, int anzahlKoerner) {
        setImage("hamster.png");
        setBlickrichtung(blickrichtung);
        koernerImMaul = anzahlKoerner;
    }
    
    // Ab hier Implementierung der Hamster-Befehle (vornFrei(), vor(), 
    // linksUm(), kornDa(), nimm(), maulLeer(), gib()). Diesen Teil des Codes
    // müssen Sie insbesondere zu Beginn noch nicht verstehen. Im Verlaufe
    // des Semesters sollten Sie aber auch diese Methoden nachvollziehen 
    // können.
    
    
    /**
     * liefert genau dann true, wenn sich in Blickrichtung vor dem aufgerufenen
     * Hamster keine Mauer befindet (wenn sich der Hamster in Blickrichtung am
     * Rand des Territoriums befindet, wird false geliefert)
     * 
     * @return true, wenn sich in Blickrichtung vor dem aufgerufenen Hamster
     *         keine Mauer befindet; sonst false
     */
    public boolean vornFrei() {
        int x = getX();
        int y = getY();
        switch (blickrichtung) {
        case SUED:
            y++;
            break;
        case OST:
            x++;
            break;
        case NORD:
            y--;
            break;
        case WEST:
            x--;
            break;
        }

        if (x >= getWorld().getWidth() || y >= getWorld().getHeight() || x < 0
                || y < 0) {
            return false;
        }

        return getWorld().getObjectsAt(x, y, Mauer.class).size() == 0;
    }

    /**
     * liefert genau dann true, wenn auf der Kachel, auf der sich der
     * aufgerufene Hamster gerade befindet, mindestens ein Korn liegt
     * 
     * @return true, wenn auf der Kachel, auf der sich der aufgerufene Hamster
     *         gerade befindet, mindestens ein Korn liegt; sonst false
     */
    public boolean kornDa() {
        return getWorld().getObjectsAt(getX(), getY(), Korn.class).size() > 0;
    }

    /**
     * liefert genau dann true, wenn der aufgerufene Hamster keine Koerner im
     * Maul hat
     * 
     * @return true, wenn der aufgerufene Hamster keine Koerner im Maul hat;
     *         sonst false
     */
    public boolean maulLeer() {
        return koernerImMaul == 0;
    }

    /**
     * Der aufgerufene Hamster springt auf die in Blickrichtung vor ihm liegende
     * Kachel.
     * 
     * @throws MauerDaException
     *             wird geworfen, wenn die Kachel in Blickrichtung vor dem
     *             Hamster durch eine Mauer blockiert ist oder der Hamster in
     *             Blickrichtung am Rand des Territoriums steht
     */
    public void vor() throws MauerDaException {
        if (!vornFrei()) {
            throw new MauerDaException(this, getY(), getX());
        }
        switch (blickrichtung) {
        case SUED:
            setLocation(getX(), getY() + 1);
            break;
        case OST:
            setLocation(getX() + 1, getY());
            break;
        case NORD:
            setLocation(getX(), getY() - 1);
            break;
        case WEST:
            setLocation(getX() - 1, getY());
            break;
        }
                
        Greenfoot.delay(1);
    }

    /**
     * Der aufgerufene Hamster dreht sich linksum.
     */
    public void linksUm() {
        switch (blickrichtung) {
        case SUED:
            setBlickrichtung(OST);
            break;
        case OST:
            setBlickrichtung(NORD);
            break;
        case NORD:
            setBlickrichtung(WEST);
            break;
        case WEST:
            setBlickrichtung(SUED);
            break;
        }

        Greenfoot.delay(1);
    }

    /**
     * Der aufgerufene Hamster frisst ein Korn auf der Kachel, auf der er sich
     * gerade befindet.
     * 
     * @throws KachelLeerException
     *             wird geworfen, wenn auf der Kachel, auf der sich der Hamster
     *             gerade befindet, kein Korn liegt
     */
    public void nimm() throws KachelLeerException {
        if (!kornDa()) {
            throw new KachelLeerException(this, getY(), getX());
        }
        koernerImMaul++;
        Korn korn = (Korn)getWorld().getObjectsAt(getX(), getY(), Korn.class).get(0);
        korn.inkAnzahl(-1);

        Greenfoot.delay(1);
    }
    
    /**
     * Der aufgerufene Hamster legt ein Korn auf der Kachel ab, auf der er sich
     * gerade befindet.
     * 
     * @throws MaulLeerException
     *             wird geworfen, wenn der Hamster keine Koerner im Maul hat
     */
    public void gib() throws MaulLeerException {
        if (maulLeer()) {
            throw new MaulLeerException(this);
        }
        koernerImMaul--;
        getWorld().addObject(new Korn(), getX(), getY());

        Greenfoot.delay(1);
    }

    /**
     * liefert die Zeile der Kachel des Territoriums, auf der sich der
     * aufgerufene Hamster gerade befindet
     * 
     * @return die Zeile der Kachel des Territoriums, auf der sich der
     *         aufgerufene Hamster gerade befindet
     */
    public int getZeile() {
        return getY();
    }

    /**
     * liefert die Spalte der Kachel des Territoriums, auf der sich der
     * aufgerufene Hamster gerade befindet
     * 
     * @return die Spalte der Kachel des Territoriums, auf der sich der
     *         aufgerufene Hamster gerade befindet
     */
    public int getSpalte() {
        return getX();
    }

    /**
     * liefert die Blickrichtung, in die der aufgerufene Hamster gerade schaut
     * (die gelieferten Werte entsprechen den obigen Konstanten)
     * 
     * @return die Blickrichtung, in die der aufgerufene Hamster gerade schaut
     */
    public int getBlickrichtung() {
        return blickrichtung;
    }

    /**
     * liefert die Anzahl der Koerner, die der aufgerufene Hamster gerade im
     * Maul hat
     * 
     * @return die Anzahl der Koerner, die der aufgerufene Hamster gerade im
     *         Maul hat
     */
    public int getAnzahlKoerner() {
        return koernerImMaul;
    }

    /**
     * Setzt die Anzahl der Koerner, die der aufgerufene Hamster gerade im
     * Maul hat. Relevant für Initialisierung des Hamsters.
     */
    public void setAnzahlKoerner(int anzahl) {
        koernerImMaul = anzahl;
    }
    
    /**
     * liefert die Gesamtzahl an existierenden Hamstern im
     * Territorium
     * 
     * @return die Gesamtzahl an existierenden Hamstern im
     *         Territorium
     */
    public int getAnzahlHamster() {
        return getWorld().getObjects(BasicHamster.class).size();
    }

    // Copy-Konstruktor
    private BasicHamster(BasicHamster h) {
        setImage(h.getImage());
        setBlickrichtung(h.blickrichtung);
        koernerImMaul = h.koernerImMaul;
    }

    // Blickrichtung setzen
    public void setBlickrichtung(int richtung) {
        blickrichtung = richtung;
        switch (blickrichtung) {
        case SUED:
            setRotation(90);
            break;
        case OST:
            setRotation(0);
            break;
        case NORD:
            setRotation(270);
            break;
        case WEST:
            setRotation(180);
            break;
        default:
            break;
        }
    }

    // wird aufgerufen, wenn der Hamster in das Territorium platziert wird
    protected void addedToWorld(World world) {
        // Hamster kann nicht auf Mauer platziert werden
        if (getWorld().getObjectsAt(getX(), getY(), Mauer.class).size() > 0) {
            getWorld().removeObject(this);
            return;
        }
    }
    
    // nur wenn auf der Kachel keine Mauer steht
    public void setLocation(int x, int y) {
        if (getWorld().getObjectsAt(x, y, Mauer.class).size() == 0) {
            super.setLocation(x, y);
        }
    }   
}
