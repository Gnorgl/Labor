

public class Tierpfleger {
    private String name;
    
    
    public Tierpfleger(String name) {
        this.name = name;
    }
    
    public void pflege(Tier tier) {
        int wert = tier.ermittleBeduerfnis();
        tier.gibBeduerfnisAus(wert);
        
        switch(wert) {
            case Tier.GELANGWEILT -> spielen(tier);
            case Tier.HUNGRIG -> fuettern(tier);
            case Tier.SCHMUTZIG -> reinigen(tier);
        }
        
    }
    
    private void fuettern(Tier tier) {
        System.out.println(this.name + " gibt " + tier.getName() + " Futter.");
    }
    
    private void spielen(Tier tier) {
        System.out.println(this.name + " spielt mit " + tier.getName() + ".");
    }
    
    private void reinigen(Tier tier) {
        System.out.println(this.name + " reinigt das Gehege von " + tier.getName() + ".");
    }
}