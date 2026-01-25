public class Gehege {
    private String name;
    private int groesse;
    private int anzahlTiere;
    private Tier[] tiere;
    private Tierpfleger tierpfleger;
    
    public Gehege(String name, int groesse) {
        this.name = name;
        this.groesse = groesse;
        this.anzahlTiere = 0;
        this.tiere = new Tier[groesse];
        
    }
    
    public void setTierpfleger(Tierpfleger tierpfleger) {
        this.tierpfleger = tierpfleger; 
    }
    
    public Tierpfleger getTierpfleger() {
        return this.tierpfleger;
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
        if(this.anzahlTiere < this.tiere.length) {
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
            System.out.println(entferntesTier.getName() + " ist aus dem Gehege " + getName() + " ausgebrochen");
            this.tiere[this.anzahlTiere] = null;
            return entferntesTier;
        } else {
            System.out.println("Gehege ist leer!");
            return null;
        }
    }
    
    public void pflegeTiere() {
        if(getTierpfleger() == null) {
            System.out.println("Dieses Gehege hat keinen Tierpfleger");
        } else {
            for(int i = 0; i < this.anzahlTiere; i++) {
                this.tierpfleger.pflege(tiere[i]);
            }
        }
    }
    
    //Main-Method:
    public static void main(String args[]) {
        Gehege savanne = new Gehege("Savanne", 6);
        
        Tier haubentaucher1 = new Haubentaucher("Haubentaucher1", 1);
        Tier haubentaucher2 = new Haubentaucher("Haubentaucher2", 2);
        Tier koboldhai1 = new Koboldhai("Koboldhai1", 10);
        Tier elefant1 = new Elefant("Elefant1", 30);
        
        savanne.fuegeTierHinzu(haubentaucher1);
        savanne.fuegeTierHinzu(haubentaucher2);
        savanne.fuegeTierHinzu(koboldhai1);
        savanne.fuegeTierHinzu(elefant1);
        
        //mit equals null check, sonst:
        //java.lang.NullPointerException: Cannot invoke "Tier.gibInfoAus()" because "tier" is null
        
        for(Tier tier : savanne.getTiere()) {
            if(tier != null) {
                tier.gibInfoAus();
                
                if(tier instanceof Haubentaucher) {
                    Haubentaucher dieserHaubentaucher = (Haubentaucher) tier;
                    dieserHaubentaucher.flieg();
                }
                
                if(tier instanceof Koboldhai) {
                    Koboldhai dieserKoboldhai = (Koboldhai) tier;
                    dieserKoboldhai.schwimm();
                }
                
                if(tier instanceof Elefant) {
                    Elefant dieserElefant = (Elefant) tier;
                    dieserElefant.schwimm();
                }
            }
        }
        
        /*
        
        okay, aber mit foreach noch besser:
        
        Tier[] testArray = savanne.getTiere();
        
        for(int i = 0; i < savanne.getAnzahlTiere(); i++) {
            testArray[i].gibInfoAus();
        }
        
        schlecht, weil die Methode getTiere() immer wieder aufgerufen wird:

        for(int i = 0; i < savanne.getAnzahlTiere(); i++) {
            savanne.getTiere()[i].gibInfoAus();
        }
        */
    }  
}