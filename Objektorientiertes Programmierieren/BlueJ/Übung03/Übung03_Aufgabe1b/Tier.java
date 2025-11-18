public class Tier {
    private String name;
    private String art;
    private int alter;
    private boolean lebendig;
    
    
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
    
    

}