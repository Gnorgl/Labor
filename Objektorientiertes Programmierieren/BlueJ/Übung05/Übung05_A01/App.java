public class App {
    public static void main(String args[]) {
        
    }
    
    public static int[] mergeArrays(int[] a, int[] b) {
        int[] c = new int[a.length + b.length];
        int[] d = new int[c.length];
        
        for(int i = 0; i < a.length; i++) {
            c[i] = a[i];
        }
        
        for(int i = a.length - 1; i < c.length; i++) {
            c[i] = b[i];
        }
        
        //Vergleich, kleinsten Wert finden.
        
        for(int i = 0; i < c.length; i++) {
            d[i] = c[i];
        }
        
        
        return c;
    }
}