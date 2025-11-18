public class Gehege {
    private String name;
    private int groesse;
    private int anzahlTiere;
    
    public Gehege(String name, int groesse) {
        this.name = name;
        this.groesse = groesse;
        this.anzahlTiere = 0;
    }
    
    public static void main(String args[]) {
        Gehege raubtierGehege = new Gehege("Löwenkäfig", 5);
        Gehege polarGehege = new Gehege("Eiswelten", 4);
        Gehege baerenGehege = new Gehege("Bärenkäfig", 7);
        
         // Informationen über Gehege ausgeben
        raubtierGehege.gibInfoAus();
        polarGehege.gibInfoAus();
        baerenGehege.gibInfoAus();
         // Das Raubtiergehege wird umbenannt
        raubtierGehege.setName("Tigerkäfig");
         // Informationen über Gehege ausgeben
        raubtierGehege.gibInfoAus();
         // Tiere anlegen
        Tier eisbaer = new Tier("Eisbär", 0);
        Tier braunbaer = new Tier("Teddy", "Braunbär", 4);
        Tier pinguin = new Tier("Frackträger", "Pinguin", 2);
        Tier robbe = new Tier("Robbie", "Robbe", 3);
        Tier nochEinEisbaer = new Tier("Helga", "Eisbär", 0);
        
        eisbaer.setName("Knut");
        eisbaer.geburtstagsFeiern();
        braunbaer.geburtstagsFeiern();
        
        eisbaer.gibInfoAus();
        braunbaer.gibInfoAus();
        // Tiere ziehen ein
        baerenGehege.fuegeTierHinzu(braunbaer);
        polarGehege.fuegeTierHinzu(eisbaer);
        polarGehege.fuegeTierHinzu(pinguin);
        polarGehege.fuegeTierHinzu(robbe);
        polarGehege.fuegeTierHinzu(nochEinEisbaer);
        baerenGehege.gibInfoAus();
        polarGehege.gibInfoAus();
        // Knut stirbt und zieht aus; Helga versucht es noch einmal
        eisbaer.sterben();
        polarGehege.entferneTier(eisbaer);
        polarGehege.fuegeTierHinzu(nochEinEisbaer);
        baerenGehege.gibInfoAus();
        polarGehege.gibInfoAus();
        
        
        
        
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
        if(this.groesse - this.anzahlTiere > 0) {
            this.anzahlTiere++;
            System.out.println(tier.getName() + " hat ein neues Zushause: " + getName());
        } else {
            System.out.println("Das Gehege " + getName() + " ist zu klein für Teddy");
        }
    }
    
    public void entferneTier(Tier tier) {
        if(this.anzahlTiere > 0) {
            this.anzahlTiere--;
            System.out.println(tier.getName() + " ist aus dem Gehege " + getName() + " ausgebrochen");
        } else {
            System.out.println("Das Gehege " + getName() + " ist unbewohnt");
        }
    }
    
    
    
    
    
    
    
    
}