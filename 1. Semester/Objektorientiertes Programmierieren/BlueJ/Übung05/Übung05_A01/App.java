public class App {
    public static void main(String args[]) {
        int[] a = {5, 6, 7, 10, 20};
        int[] b = {3, 4, 5, 9};
        
        System.out.println(java.util.Arrays.toString(mergeArrays(a, b)));
    }
    
    public static int[] mergeArrays(int[] a, int[] b) {

        int[] neuesArray = new int[a.length + b.length]; 
        
        int i = 0; //Für a
        int j = 0; //Für b
        
        int k = 0; //Für neuesArray der Counter
        
        while(i < a.length && j < b.length) {
            if(a[i] <= b[j]) {
                neuesArray[k] = a[i]; //Frage: mit [k++]? 
                k++;
                i++;
            } else {
                neuesArray[k] = b[j];
                k++;
                j++;
            }
        }
        
        //Was wenn ein Array länger ist als das andere?
        //Müssen kontrollieren:
        
        while(i < a.length) {
            neuesArray[k] = a[i];
            k++;
            i++;
        }
        
        while(j < b.length) {
            neuesArray[k] = b[j];
            k++;
            j++;
        }
        
        return neuesArray;

        /**
         * Was wenn arrays unterschiedlich lang, was wenn Werte doppelt?
         * 
         * Prinzip von MergeSort benutzen. Kartenstapel!
         */
    }
}