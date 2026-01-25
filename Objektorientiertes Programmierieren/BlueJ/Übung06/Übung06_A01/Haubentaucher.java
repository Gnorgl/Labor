public class Haubentaucher extends Tier{
    private String art;
    
    public Haubentaucher(String name, int alter) {
        super(name, "Haupentaucher", alter);
    }
    
    public Haubentaucher(int alter) {
        this("Haupentaucher", alter);
    }
}