import java.util.Random;

public class Tier {
    private String name;
    private String art;
    private int alter;
    private boolean lebendig;
    
    public static final int GELANGWEILT = 0;
    public static final int HUNGRIG = 1;
    public static final int SCHMUTZIG = 2;
    
    
    public Tier(String name, String art, int alter) {
        this.name = name;
        this.art = art;
        this.alter = alter;
        this.lebendig = true;
    }
    
    public Tier(String art, int alter) {
        this.art = art;
        this.alter = alter;
        this.lebendig = true;
        this.name = art;
    }
    
    public String getName() {
        return this.name;
    }
     
    public String getArt() {
        return this.art;
    }
    
    public int getAlter() {
        return this.alter;
    }
    
    public boolean getLebendig() {
        return this.lebendig;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void sterben() {
        this.lebendig = false;
    }
    
    public void geburtstagsFeiern() {
        if(this.lebendig == false) {
            System.out.println(getName() + " ist tot");
            return;
        } else {
            System.out.println(getName() + " hat Geburtstag!");
            this.alter++;
        }
    }
    
    public void gibInfoAus() {
        if(this.lebendig = false) {
            System.out.println(getName() + " war ein " + getArt());
            return;
        }
        System.out.println(getName() + " ist ein " + getArt() + " im Alter von  " + getAlter() + " Jahren");
    }
    
    //Neue Methoden: Bedürfnisse:
    public int ermittleBeduerfnis() {
        Random generator = new Random();
        int zufaelligesBeduerfnis = generator.nextInt(3);
        return zufaelligesBeduerfnis;
    }
    
    public void gibBeduerfnisAus(int wert) {
        switch(wert) {
            case GELANGWEILT -> System.out.println(this.name + " will spielen.");
            case HUNGRIG -> System.out.println(this.name + " ist hungrig");
            case SCHMUTZIG -> System.out.println(this.name + " will ein sauberes Gehege");
        }   
    }

}