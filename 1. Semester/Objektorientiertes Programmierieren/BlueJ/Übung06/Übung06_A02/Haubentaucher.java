public class Haubentaucher extends Tier{
    
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
}