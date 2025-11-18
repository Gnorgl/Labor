public class Gehege {
    private String name;
    private int groesse;
    private int anzahlTiere;
    
    public Gehege(String name, int groesse) {
        this.name = name;
        this.groesse = groesse;
        this.anzahlTiere = 0;
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
    
    public static void main(String args[]) {
        Gehege raubtierGehege = new Gehege("Löwenkäfig", 5);
        Gehege polarGehege = new Gehege("Eiswelten", 4);
        Gehege baerenGehege = new Gehege("Bärenkäfig", 7);
        
        //Informationen über Gehege ausgeben
        raubtierGehege.gibInfoAus();
        polarGehege.gibInfoAus();
        baerenGehege.gibInfoAus();
        
        //Das Raubtiergehege wird umbenannt
        raubtierGehege.setName("Tigerkäfig");
        
        //Informationen über Gehege ausgeben
        raubtierGehege.gibInfoAus();
        
        //Tiere:
        Tier eisbär = new Tier("Eisbär", 0);
        Tier teddy = new Tier("Teddy", "Braunbär", 0);
        Tier fracktraeger = new Tier("Frackträger", "Pinguin", 0);
        
        //Eisbär Taufung:
        eisbär.setName("Knut");
        Tier knut = eisbär;
        
        //Knut und Teddy haben Geburtstag:
        knut.geburtstagsFeiern();
        teddy.geburtstagsFeiern();
        
        //Knut stirb :(
        knut.sterben();
        
        //Infos der Tiere:
        knut.geburtstagsFeiern();
        knut.gibInfoAus();
        teddy.gibInfoAus();
        fracktraeger.gibInfoAus();
        
        
        
        
        
    }
    
    
    
    
    
    
    
    
    
}