package Console.Exception;

public class FutureDateException extends Exception{
    public FutureDateException() {
        super("Error: BirthDay cannot be Future");
    }
}
