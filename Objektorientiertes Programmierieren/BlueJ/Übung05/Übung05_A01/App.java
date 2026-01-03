public class App {
    public static void main(String args[]) {
    }
    
    public static int[] mergeArrays(int[] a, int[] b) {
        int[] c = new int[a.length + b.length];
        int[] d = new int[c.length];

        return d;
        
        /** Funktioniert nicht:
         * Was wenn arrays unterschiedlich lang, was wenn Werte doppelt?
         * 
         * Prinzip von MergeSort benutzen. Kartenstapel!
         */
        /*
        //Wie lang soll for-loop sein:
        
        if(a.length > b.length) {
            length = a.length;
        } else if(a.length < b.length) {
            length = b.length;
        } else if (a.length == b.length) {
            length = a.length;
        }
        
        //Vergleich, kleinsten Wert finden.
        
        for(int i = 0; i < length; i++) {
            if(a[i] < b[i]) {
                c[i] = a[i];
                c[i+1] = b[i];
            } else if(a[i] > b[i]) {
                c[i] = b[i];
                c[i+1] = a[i];
            } else if (a[i] == b[i]) {
                c[i] = a[i];
            }
        }
        */
    }
}