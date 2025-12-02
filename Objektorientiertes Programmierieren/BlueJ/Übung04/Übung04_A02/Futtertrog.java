
/**
 * Write a description of class Futtertrog here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import java.util.Scanner;

public class Futtertrog {
    private int groesse;
    
    public Futtertrog(int groesse) {
        this.groesse = groesse;
    }
    
    public void zeichnen() {
        
    }
    
    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        int input;
        
        do {
            System.out.println("Gib eine ganze Zahl ein");
            input = Integer.valueOf(scanner.nextLine());
        } while (input < 5 || input > 50 || input % 2 == 0);
        
        Futtertrog futtertrog = new Futtertrog(input);
        futtertrog.zeichnen();
        
    }
    
    
    
    
}