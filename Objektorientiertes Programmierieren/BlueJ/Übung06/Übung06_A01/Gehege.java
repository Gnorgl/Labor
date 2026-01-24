public class Gehege {
    private String name;
    private int groesse;
    private int anzahlTiere;
    private Tier[] tiere;
    
    public Gehege(String name, int groesse) {
        this.name = name;
        this.groesse = groesse;
        this.anzahlTiere = 0;
        this.tiere = new Tier[groesse];
        
    }
    
    public Tier[] getTiere() {
        return this.tiere;
    }
    
    public String getName() {
        return this.name;
    }
    
    public int getGroesse() {
        return this.groesse;
    }
    
    public int getAnzahlTiere() {
        return this.anzahlTiere;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void gibInfoAus() {
        System.out.println(getName() + ": " + getAnzahlTiere() + " von " + getGroesse() + " Tieren");
    }
    
    public void fuegeTierHinzu(Tier tier) {
        if((tiere.length - this.anzahlTiere) > 0) {
            System.out.println(tier.getName() + " hat ein neues Zuhause: " + getName());
            this.tiere[this.anzahlTiere] = tier;
            this.anzahlTiere++;
        } else {
            System.out.println("Das Gehege " + getName() + " ist zu klein für " + tier.getName());
        }
    }
    
    public Tier entferneTier() {
        if(this.anzahlTiere > 0) {
            this.anzahlTiere--;
            Tier entferntesTier = this.tiere[this.anzahlTiere];
            System.out.println(this.tiere[this.anzahlTiere].getName() + " ist aus dem Gehege " + getName() + " ausgebrochen");
            this.tiere[this.anzahlTiere] = null;
            return entferntesTier;
        } else {
            System.out.println("Gehe ist leer!");
            return null;
        }
    }
    
    
    
    
    
    
    
    
}