
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
        //Erste Hälfte Malen:
        
        int haelfte = (this.groesse - 1)/2;
        int count = 1;
        int space = 1;
        
        for(int i = haelfte; i > 0; i--) {
            if(i == haelfte) {
                for(int j = groesse; j > 0; j--) {
                    if(j == haelfte + 1) {
                        System.out.print(".");
                    } else {
                        System.out.print(" ");
                    }
                }
            } else {
                for(int j = haelfte - space; j > 0; j--) {
                    System.out.print(" ");
                    if(j == 1) {
                        System.out.print("/");
                        for(int k = count; k > 0; k--) {
                            System.out.print(" ");
                        }
                        System.out.print("\\");
                    }
                }
                space += 1;
                count += 2;
            }

            System.out.println("");

        }
        
        //Mitte Malen:
        
        System.out.print(".");
        for(int i = groesse - 2; i > 0; i--) {
            System.out.print(" ");
        }
        System.out.print(".");
        
        System.out.println("");
        
        //Zweite Hälfte Malen:
        
        for(int i = haelfte; i > 0; i--) {
            if(i == 1) {
                for(int j = groesse; j > 0; j--) {
                    if(j == haelfte + 1) {
                        System.out.print(".");
                    } else {
                        System.out.print(" ");
                    }
                }
            } else {
                space -= 1;
                count -= 2;
                for(int j = haelfte - space; j > 0; j--) {
                    System.out.print(" ");
                    if(j == 1) {
                        System.out.print("\\");
                        for(int k = count; k > 0; k--) {
                            System.out.print(" ");
                        }
                        System.out.print("/");
                    }
                }
            }

            System.out.println("");

        }
        
    }
    
    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        Tier tier = new Tier("Knut", "Eisbaer", 3);
        
        for(int i = 0; i < 10; i++) {
            int wert = tier.ermittleBeduerfnis();
            tier.gibBeduerfnisAus(wert);
        }
    }
    
    
    
    
}