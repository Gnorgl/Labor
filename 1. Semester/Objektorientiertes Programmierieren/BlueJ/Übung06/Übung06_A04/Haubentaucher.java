public class Haubentaucher extends Tier implements Schwimmfaehig, Flugfaehig{
    
    public Haubentaucher(String name, int alter) {
        super(name, "Haubentaucher", alter);
    }
    
    public Haubentaucher(int alter) {
        this("Haubentaucher", alter);
    }
    
    //müssen getName() benutzen, weil name eine private attribut ist.
    @Override
    public void gibLaut() {
        System.out.println(getName() + " schnattert.");
    }
    
    @Override
    public void schwimm() {
        System.out.println(getName() + " schwimmt.");
    }
    
    @Override
    public void flieg() {
        System.out.println(getName() + " fliegt.");
    }
}