
public class TicTacToe {

    private static final int ROWS = 3;
    private static final int COLUMNS = 3;

    private static final int DRAW = 2;      // (Unentschieden)
    private static final int UNDECIDED = 3; // (Spiel noch nicht entschieden)

    private int playerNo;
    private char[][] matrix;

    /**
     * Konstruktor initialisiert die Matrix.
     */
    public TicTacToe() {
        // TODO: Array anlegen und initialisieren.
        // Hinweis: Normale for-Schleife zur Initialisierung nutzen!
        //          Die for-each-Schleife erlaubt nur lesendes Durchlaufen von Arrays.
        //          (Alternative: implizite Initialisierung bei Deklaration der Variablen matrix.)
    }

    /**
     * Methode zum Anzeigen des Spielfelds.
     * Gestalten Sie eine geeignete Konsolenausgabe, 
     * z.B. wie die im Aufgabenblatt.
     */
    private void printBoard() {
        // TODO: Spielbrett (matrix) auf der Konsole ausgeben.
    }

    /**
     * Prüft, ob der Zug zulässig ist.
     * 
     * @param row Zeile
     * @param column Spalte
     * @return boolean true, wenn zulässig.
     */
    private boolean isValidMove(int row, int column) {
        // TODO: Überprüfung des Zugs.

        // Dummy-Code, damit Klasse übersetzt werden kann:
        return false;
    }

    /**
     * Markiert eine Zelle im Array je nach Spieler mit einem 'x' oder einem 'o'.
     * Zu Beginn der Methode sollte mit isValidMove() geprüft werden, 
     * ob der Zug möglich ist.
     * 
     * @param playerNo Nummer des Spielers
     * @param row Zeile
     * @param column Spalte
     * @return boolean true, wenn der Zug durchführbar war.
     */
    private boolean makeMove(int playerNo, int row, int column) {
        // TODO: Mit isValidMove() prüfen, ob der Zug möglich ist.

        // TODO: Wenn zulässig: Ausführung eines Zuges.

        // TODO: Zurückgeben, ob Zug zulässig war.
        // Dummy-Code, damit Klasse übersetzt werden kann:
        return false;
    }

    /**
     * Methode prüft Gewinnbedingungen nach dem Zug. 
     * Dabei gibt es die folgenden drei Möglichkeiten: 
     *  - ein Spieler hat gewonnen hat,
     *  - es liegt ein Unentschieden (DRAW) vor oder
     *  - das Spiel ist noch nicht entschieden (UNDECIDED).
     * 
     * @return int Spielernummer des Gewinners, DRAW oder UNDECIDED
     */
    private int checkForWinner() {
        // TODO: Überprüfung der Gewinnbedingungen. 
        // Hinweis: Sie können für jede Gewinnmöglichkeit (senkrecht, waagerecht, diagonal)
        // und das Unentschieden eine eigene Methode erstellen und in dieser Methode aufrufen.

        // Dummy-Code, damit Klasse übersetzt werden kann:
        return UNDECIDED;
    }

    /**
     * Hier wird das Spiel gesteuert. Es wird das Spielfeld ausgegeben. 
     * Anschließend wird jeweils ein Spieler gefragt, welche Zelle er markieren möchte.
     * Nach jedem Zug wird geprüft, ob es einen Gewinner gibt.
     * Das Spiel ist beendet, wenn ein Gewinner feststeht oder es ein Unentschieden gibt.
     */
    public void gameLoop() {
        int winner = UNDECIDED;
        playerNo = 1;   // Spieler 0(!!) beginnt (siehe unten)
        do {
            // Spieler ermitteln:
            playerNo = (playerNo+1) % 2;    // wechselt immer zwischen 0 und 1
            
            // Spielbrett zeichnen
            printBoard();

            boolean valid = false;
            do {
                // Nutzerabfrage: Zeile und Spalte eingeben
                int zeile = IO.readInt("Bitte Zeilennummer eingeben (0-2): ");
                int spalte = IO.readInt("Bitte Spaltennummer eingeben (0-2): ");

                // Zug ausführen
                valid = makeMove(playerNo, zeile, spalte);
            } while (!valid);

            // Prüfen, ob jemand gewonnen hat:
            winner = checkForWinner();
        } while (winner == UNDECIDED);

        printBoard();
        
        // Gewinner ausgeben
        if (winner == DRAW) {
            IO.println("Unentschieden");
        } else {
            IO.println("Gewinner ist Spieler " + playerNo);
        }
    }


    /**
     * main()-Methode: 
     * - Erzeugung eines TicTacToe-Objekts
     * - Start des Spiels
     */
    public static void main(String[] args) {
        TicTacToe ttt = new TicTacToe();
        ttt.gameLoop();
    }
}