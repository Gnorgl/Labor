public class Koboldhai extends Tier {
    
    public Koboldhai(String name, int alter) {
        super(name, "Koboldhai", alter);
    }
    
    public Koboldhai(int alter) {
        this("Koboldhai", alter);
    }
    
    @Override
    public void gibLaut() {
        System.out.println(getName() + " blubbert.");
    }
}