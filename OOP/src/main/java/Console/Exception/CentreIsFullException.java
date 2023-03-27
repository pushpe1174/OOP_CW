package Console.Exception;

import Model.Person;

public class CentreIsFullException extends Exception{
    public CentreIsFullException(){
        super("\t\t Error: Maximum of 10 doctors reached");
    }
}
