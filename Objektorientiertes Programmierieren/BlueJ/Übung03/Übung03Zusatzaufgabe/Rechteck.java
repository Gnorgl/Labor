public class Rechteck {
    
    private int x1;
    private int x2;
    private int y1;
    private int y2;
    
    public Rechteck(int x1,int x2,int y1,int y2) {
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
    }
    
    public static void main(String args[]) {
        Rechteck rechtEck1 = new Rechteck(2, 5, 1, 4);
        Rechteck rechtEck2 = new Rechteck(2, 5, 1, 4);
        if(rechtEck2.enthaelt(rechtEck1)) {
            System.out.println("Das erste Rechteck enthält das zweite Rechteck!");
        } else {
            System.out.println("Das erste Rechteck enthält das zweite Rechteck nicht!");
        }
    }
    
    public int getx1() {
        return x1;
    }
    
    public int getx2() {
        return x2;
    }
    
    public int gety1() {
        return y1;
    }
    
    public int gety2() {
        return y2;
    }
    
    public boolean enthaelt(Rechteck rechtEck) {
        return x1 <= rechtEck.getx1() && y1 <= rechtEck.gety1() 
                && x2 >= rechtEck.getx2() && y2 >= rechtEck.gety2();
        // if(x1 < rechtEck.getx1() && y1 < rechtEck.gety1()) {
            // if(x2 > rechtEck.getx2() && y2 > rechtEck.gety2()) {
                // return true;
            // } else {
                // return false;
            // }
        // } else {
            // return false;
        // }
    }
}