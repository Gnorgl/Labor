import java.util.Scanner;

public class App {
    
        public static void main(String args[]) {
            Scanner scanner = new Scanner(System.in);
            
            BinaerZuDezimalKonverter objekt = new BinaerZuDezimalKonverter();
            
            int[][] array = {
                {1,0,0,1},
                {1,1,1},
                {1,1,0,1},
                {1,0,0,0,1}
            };
            
            System.out.println("Soll das Eingabe-Array ausgegeben werden? (y/n)");
            
            String antwort = scanner.nextLine();
            
            if(antwort.equals("y")) {
                objekt.ausgabeZweidimensional(array);
            }
            
            System.out.println("Soll das Konvertierte-Array ausgegeben werden? (y/n)");
            
            antwort = scanner.nextLine();
            
            if(antwort.equals("y")) {
                objekt.ausgabeEindimensional(array);
            }
    }
    
    
    
    
}