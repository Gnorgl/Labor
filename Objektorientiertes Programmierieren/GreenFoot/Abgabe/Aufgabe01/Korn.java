import greenfoot.Actor;
import greenfoot.World;

import java.util.List;

/**
 * Repraesentation von Koernern im Java-Hamster-Modell
 * 
 * @author Dietrich Boles (Universitaet Oldenburg)
 * @version 2.0 (07.06.2008)
 * 
 */
public class Korn extends Actor {

	private int anzahl;

	public Korn() {
	    this(1);
	}
	
	public Korn(int anzahl) {
	    super();
	    this.anzahl = anzahl;
	}

	// wird aufgerufen, wenn das Korn in das Territorium platziert wird
	protected void addedToWorld(World world) {

		// Wenn auf der Kachel schon eine Mauer ist, wird das Korn wieder
		// entfernt
		if (getWorld().getObjectsAt(getX(), getY(), Mauer.class).size() > 0) {
			getWorld().removeObject(this);
			return;
		}
		
	    // bereits Korn auf Kachel?
	    List koerner = getWorld().getObjectsAt(getX(), getY(), Korn.class);
	    if (koerner.size() > 1) {
	        Korn korn = (Korn)koerner.get(0);
            if (korn == this) {
                korn = (Korn)koerner.get(1);
            }
	        getWorld().removeObject(korn);
	        this.anzahl += korn.anzahl;
	     }

		setImage("korn" + Math.min(anzahl, 12) + ".png");
		// es werden maximal 12 Koerner angezeigt

	}

	// liefert die Information, das wie vielte Korn dieses Korn auf der Kachel
	// ist
	protected int getAnzahl() {
		return anzahl;
	}
	
	public void setLocation(int x, int y) {
	    if (getX() == x && getY() == y) {
	        return;
	    }

	    // Mauer auf Kachel?
	    if (getWorld().getObjectsAt(x, y, Mauer.class).size() > 0) {
	         return;
	    }
	    
	    // bereits Korn auf Kachel?
	    List koerner = getWorld().getObjectsAt(x, y, Korn.class);
	    if (koerner.size() > 0) {
	        
	        Korn korn = (Korn)koerner.get(0);
	        getWorld().removeObject(korn);
	        this.anzahl += korn.anzahl;
	        setImage("korn" + Math.min(anzahl, 12) + ".png");
	         
	     }
	    	        	   
	     super.setLocation(x, y);
	}
	
	void inkAnzahl(int n) {
	    this.anzahl += n;
	    if (this.anzahl <= 0) {
	        getWorld().removeObject(this);
	    } else {
	        setImage("korn" + Math.min(anzahl, 12) + ".png");
	    }
	}

}
