package Console.Exception;

public class AgeException extends Exception{
    public AgeException() {
        super("Error : Doctor Age must be 18 to 60");
    }
}
