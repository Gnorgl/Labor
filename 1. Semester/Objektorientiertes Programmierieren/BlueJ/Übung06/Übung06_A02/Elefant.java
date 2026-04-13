public class Elefant extends Tier {
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
}