public class UeberfuellungException extends Exception{

    public UeberfuellungException(Gehege gehege, Tier tier) {
        super("Überfüllung: " + tier.getName() + " kann nicht in das Gehege " + gehege.getName() + " aufgenommen werden.");
    }
}