public class App {
    public static void main(String args[]) {

        Tier tier = new Tier("Knut", "Eisbaer", 3);
        Tierpfleger tierpfleger = new Tierpfleger("Max");
        
        for(int i = 0; i < 10; i++) {
            tierpfleger.pflege(tier);
        }
    }
}