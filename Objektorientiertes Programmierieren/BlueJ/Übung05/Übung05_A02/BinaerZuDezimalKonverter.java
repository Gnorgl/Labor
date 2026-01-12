import java.util.Arrays;

public class BinaerZuDezimalKonverter {
    //Mit jeder Iteration muss Wert der Hochzahl um 1 abnehmen bis 0.
    
    public int[] konvertiere(int[][] a) {
        int[] ausgabeArray = new int[a.length];
        
        for(int zeile = 0; zeile < a.length; zeile++) {
            int hochzahl = a[zeile].length - 1;
            int sum = 0;
            
            for(int spalte = 0; spalte < a[zeile].length; spalte++) {
                sum += a[zeile][spalte]*Math.pow(2, hochzahl);
                hochzahl--;
            }
            ausgabeArray[zeile] = sum;
        }
        
        return ausgabeArray;
    }
    
    public void ausgabeEindimensional(int[][] array) {
        System.out.println(Arrays.toString(konvertiere(array)));
    }
    
    public void ausgabeZweidimensional(int[][] a) {
        for(int zeile = 0; zeile < a.length; zeile++) {
            for(int spalte = 0; spalte < a[zeile].length; spalte++) {
                System.out.print(a[zeile][spalte] + " ");
            }
            System.out.println();
        }
    }
}















