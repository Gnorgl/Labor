public class Koboldhai extends Tier implements Schwimmfaehig{
    
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
    
    @Override
    public void schwimm() {
        System.out.println(getName() + " schwimmt.");
    }
}