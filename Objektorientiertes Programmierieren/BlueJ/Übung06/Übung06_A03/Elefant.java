public class Elefant extends Tier implements Schwimmfaehig{
    public Elefant(String name, int alter) {
        super(name, "Elefant", alter);
    }
    
    public Elefant(int alter) {
        this("Elefant", alter);
    }
    
    @Override
    public void gibLaut() {
        System.out.println(getName() + " trötet.");
    }
    
    @Override
    public void schwimm() {
        System.out.println(getName() + " schwimmt.");
    }
}