
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
        int zeile = groesse;
        int spaceDistance = groesse*2;
        int distance = (spaceDistance - 1)/2;
        int middleSpace = 1;
        //Wenn spaceDistance doppelt so groß ist, dann erster Teil
        //Wenn spaceDistance gleich groß ist, dann mittlerer Teil
        //Wenn spaceDistance kleiner ist, dann erster Teil
        //Wenn spaceDistance halb so groß ist, dann mittlerer Teil
        for(int i = 0; i < zeile; i++) {
            if(spaceDistance > groesse) {
                for(int j = 0; j < distance; j++) {
                    System.out.print(" ");
                }
                System.out.print(".");
                for(int j = 0; j < distance; j++) {
                    System.out.print(" ");
                }
            } else if(spaceDistance < groesse) {
                for(int j = 0; j < distance; j++) {
                    System.out.print(" ");
                }
                System.out.print("/");
                for(int j = 0; j < middleSpace; j++) {
                    System.out.print(" ");
                }
                System.out.print("\\");
                middleSpace += 2;
            }
            System.out.print("\n");
            spaceDistance--;
            zeile--;
            distance--;
        }
    }
    
    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        int input;
        //do-schleife:
        do {
            System.out.println("Gib eine ganze Zahl ein");
            input = Integer.valueOf(scanner.nextLine());
        } while (input < 5 || input > 50 || input % 2 == 0);
        //wenn nummer >= 5 oder nummer <= 50 und ungerade ist:
        Futtertrog futtertrog = new Futtertrog(input);
        futtertrog.zeichnen();
        
    }
    
    
    
    
}